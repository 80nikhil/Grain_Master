package com.mpcz.grainmaster;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mpcz.grainmaster.Adapter.NotificationAdapter;
import com.mpcz.grainmaster.Models.CommodityModel;
import com.mpcz.grainmaster.Models.LanguageModel;
import com.mpcz.grainmaster.Models.NotificationModel;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ActivityRegisterBinding;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    public static String TAG = RegisterActivity.class.getCanonicalName();
    ActivityRegisterBinding binding;
    ProgressDialog progressDialog;
    private static final int CAMERA_CODE = 10;
    private static final int GALLERY_CODE = 20;
    final int PERMISSION_REQUEST_CODE = 100;
    ArrayList<LanguageModel.List> languagearraylist = new ArrayList<>();
    ArrayList<CommodityModel.List> commodityarraylist = new ArrayList<>();
    ArrayList<String> commodity = new ArrayList<>();
    ArrayList<String> language = new ArrayList<>();
    private String[] requestedPermissions = {CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE};
    private File profileimg = null, bussinesscardimg = null;
    int flag;
    Dialog dialog;
    String bussiness_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getcommodities();
        getlanguage();
        dialog = new Dialog(RegisterActivity.this);
        progressDialog = new ProgressDialog(RegisterActivity.this);

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
            }
        });
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateform()) {
                    loginuser();
                }
            }
        });
        binding.profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(RegisterActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(RegisterActivity.this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(RegisterActivity.this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        pickImageDialog();
                    } else {
                        requestCameraPermission();
                    }
                } else {
                    pickImageDialog();
                }
            }
        });
        binding.imgBusinessCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(RegisterActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(RegisterActivity.this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(RegisterActivity.this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        pickImageDialog();
                    } else {
                        requestCameraPermission();
                    }
                } else {
                    pickImageDialog();
                }
            }
        });
        binding.radiogroupBussinessType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.radioRetailer.isChecked()) {
                    bussiness_type = "Retailer";
                } else if (binding.radioBroker.isChecked()) {
                    bussiness_type = "Broker";
                } else if (binding.radioManufactrer.isChecked()) {
                    bussiness_type = "Manufacturer";
                } else if (binding.radioTrader.isChecked()) {
                    bussiness_type = "Trader";
                } else if (binding.radioImportExport.isChecked()) {
                    bussiness_type = "Import/Export";
                }
            }
        });
        binding.edtDealsIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(1);
                System.out.println("yes");
            }
        });
        binding.edtLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(2);
                System.out.println("yes");
            }
        });
    }

    private boolean validateform() {
        if (binding.edtName.getText().toString().isEmpty()) {
            binding.edtName.setError("field is empty");
            binding.edtName.requestFocus();
            return false;
        } else if (binding.edtMobile.getText().toString().isEmpty()) {
            binding.edtMobile.setError("field is empty");
            binding.edtMobile.requestFocus();
            return false;
        } else if (binding.edtEmail.getText().toString().isEmpty()) {
            binding.edtEmail.setError("field is empty");
            binding.edtEmail.requestFocus();
            return false;
        } else if (binding.edtBussinessName.getText().toString().isEmpty()) {
            binding.edtBussinessName.setError("field is empty");
            binding.edtBussinessName.requestFocus();
            return false;
        } else if (binding.edtGstin.getText().toString().isEmpty()) {
            binding.edtGstin.setError("field is empty");
            binding.edtGstin.requestFocus();
            return false;
        } else if (binding.edtDealsIn.getText().toString().isEmpty()) {
            binding.edtDealsIn.setError("field is empty");
            binding.edtDealsIn.requestFocus();
            return false;
        } else if (binding.edtCountry.getText().toString().isEmpty()) {
            binding.edtCountry.setError("field is empty");
            binding.edtCountry.requestFocus();
            return false;
        } else if (binding.edtState.getText().toString().isEmpty()) {
            binding.edtState.setError("field is empty");
            binding.edtState.requestFocus();
            return false;
        } else if (binding.edtCity.getText().toString().isEmpty()) {
            binding.edtCity.setError("field is empty");
            binding.edtCity.requestFocus();
            return false;
        } else if (binding.edtLanguage.getText().toString().isEmpty()) {
            binding.edtLanguage.setError("field is empty");
            binding.edtLanguage.requestFocus();
            return false;
        } else if (profileimg == null) {
            Toast.makeText(RegisterActivity.this, "Please Capture Profile Image", Toast.LENGTH_SHORT).show();
        } else if (bussinesscardimg == null) {
            Toast.makeText(RegisterActivity.this, "Please Capture Bussiness Card Image", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return true;
    }

    // Camera,Device read write Permissions
    private void checkRunTimePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(RegisterActivity.this, CAMERA) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(RegisterActivity.this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(RegisterActivity.this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestCameraPermission();
            }
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, CAMERA)
                | ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, WRITE_EXTERNAL_STORAGE)
                | ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, READ_EXTERNAL_STORAGE)) {

            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage(getString(R.string.camera_permission_needed));
            builder.setPositiveButton(R.string.grant, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ActivityCompat.requestPermissions(RegisterActivity.this, requestedPermissions, PERMISSION_REQUEST_CODE);
                }
            }).create().show();

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageDialog();
            } else {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage(getString(R.string.camera_permission_needed));

                builder.setPositiveButton(R.string.enable, (dialog, id) -> {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }).create().show();
            }
        }
    }

    private void pickImageDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
        dialog.setCancelable(true);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_choose_cameragallery, null, false);

        ImageView closeIv = view.findViewById(R.id.ivbChooseClose);
        LinearLayout cameraLayout = view.findViewById(R.id.llCamera);
        LinearLayout galleryLayout = view.findViewById(R.id.llGallery);

        dialog.setView(view);
        dialog.setCancelable(true);

        AlertDialog show = dialog.show();
        show.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        show.getWindow().setGravity(Gravity.BOTTOM);
        show.setCanceledOnTouchOutside(false);

        cameraLayout.setOnClickListener(view1 -> {
            show.dismiss();
            // Pick from Camera
            Uri outputFileUri = getCaptureImageOutputUri();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, CAMERA_CODE);
        });

        galleryLayout.setOnClickListener(view12 -> {
            show.dismiss();
            // Pick from Gallery
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALLERY_CODE);
        });

        closeIv.setOnClickListener(v -> {
            dialog.setCancelable(true);
            show.dismiss();
        });
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            File getImage = getExternalCacheDir();
            if (!getImage.exists()) {
                getImage.mkdirs();
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
            }
        } else {
            File getImage = getExternalCacheDir();
            if (!getImage.exists()) {
                getImage.mkdirs();
                outputFileUri = FileProvider.getUriForFile(RegisterActivity.this, BuildConfig.APPLICATION_ID + ".provider",
                        new File(getImage.getPath(), "pickImageResult.jpeg"));
            } else {
                outputFileUri = FileProvider.getUriForFile(RegisterActivity.this, BuildConfig.APPLICATION_ID + ".provider",
                        new File(getImage.getPath(), "pickImageResult.jpeg"));
            }
        }
        return outputFileUri;
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    private void beginCrop(Uri source) {
        File pro = new File(Util.MakeDir("", RegisterActivity.this), System.currentTimeMillis() + ".jpg");
        Uri destination1 = Uri.fromFile(pro);
        Crop.of(source, destination1).asSquare().withAspect(160, 160).start(RegisterActivity.this);
        Log.e("TAG", "BeginCrop: A ");
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            String imagePath = Crop.getOutput(result).getPath();
            if (flag == 0) {
                profileimg = new File(imagePath);
                Glide.with(RegisterActivity.this).load(profileimg).into(binding.profilepic);
                Log.e(TAG, profileimg.getPath());
            } else if (flag == 1) {
                bussinesscardimg = new File(imagePath);
                Glide.with(RegisterActivity.this).load(bussinesscardimg).into(binding.imgBusinessCard);
                Log.e(TAG, bussinesscardimg.getPath());
            }
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(RegisterActivity.this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == CAMERA_CODE) {
                Uri imageUri = getPickImageResultUri(data);
                beginCrop(imageUri);
            } else if (requestCode == GALLERY_CODE) {

                Uri selectedImage = data.getData();
                beginCrop(selectedImage);

            } else if (requestCode == Crop.REQUEST_CROP) {
                handleCrop(resultCode, data);
            }
        }
    }

    public void loginuser() {
        progressDialog.show();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(Constants.NAME, binding.edtName.getText().toString());
        builder.addFormDataPart(Constants.MOBILE_NO, binding.edtMobile.getText().toString());
        builder.addFormDataPart(Constants.EMAIL, binding.edtEmail.getText().toString());
        builder.addFormDataPart(Constants.BUSSINESS_NAME, binding.edtBussinessName.getText().toString());
        builder.addFormDataPart(Constants.BUSSINESS_TYPE, bussiness_type);
        builder.addFormDataPart(Constants.GSTIN, binding.edtGstin.getText().toString());
        builder.addFormDataPart(Constants.DEALS_IN, binding.edtDealsIn.getText().toString());
        builder.addFormDataPart(Constants.COUNTRY, binding.edtCountry.getText().toString());
        builder.addFormDataPart(Constants.STATE, binding.edtState.getText().toString());
        builder.addFormDataPart(Constants.CITY, binding.edtCity.getText().toString());
        builder.addFormDataPart(Constants.LANGUAGE, binding.edtLanguage.getText().toString());
        if (profileimg != null) {
            builder.addFormDataPart(Constants.USER_PIC, profileimg.getName(), RequestBody.create(profileimg, MediaType.parse("multipart/form-data")));
        }
        if (bussinesscardimg != null) {
            builder.addFormDataPart(Constants.BUSSINESS_CARD_PIC, bussinesscardimg.getName(), RequestBody.create(bussinesscardimg, MediaType.parse("multipart/form-data")));
        }

        MultipartBody requestBody = builder.build();
        RetrofitClient.getAPIService().loginuser(requestBody).enqueue(new Callback<Retroresponse>() {
            @Override
            public void onResponse(Call<Retroresponse> call, Response<Retroresponse> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus() == true) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 400) {
                    progressDialog.dismiss();
                    Log.e(TAG, "INSIDE FAILED BLOCK OF API");
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Retroresponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "fail");
                Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getlanguage() {
        RetrofitClient.getAPIService().get_languages().enqueue(new Callback<LanguageModel>() {
            @Override
            public void onResponse(Call<LanguageModel> call, Response<LanguageModel> response) {
                if (response.code() == 200) {
                    languagearraylist.clear();
                    languagearraylist = response.body().getList();
                    if (languagearraylist.size() > 0) {
                        for (int i = 0; i < languagearraylist.size(); i++) {
                            language.add(languagearraylist.get(i).getName());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LanguageModel> call, Throwable t) {

            }
        });
    }

    public void getcommodities() {
        RetrofitClient.getAPIService().get_commodity().enqueue(new Callback<CommodityModel>() {
            @Override
            public void onResponse(Call<CommodityModel> call, Response<CommodityModel> response) {
                if (response.code() == 200) {
                    commodityarraylist.clear();
                    commodityarraylist = response.body().getList();
                    if (commodityarraylist.size() > 0) {
                        for (int i = 0; i < commodityarraylist.size(); i++) {
                            commodity.add(commodityarraylist.get(i).getName());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CommodityModel> call, Throwable t) {

            }
        });
    }

    public void opendialog(int type) {
        dialog.setContentView(R.layout.dialog_searchable_spinner);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 400);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);

        ArrayAdapter<String> adapter;
        if (type == 1) {
            adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_list_item_1, commodity);
        } else {
            adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_list_item_1, language);
        }
        listView.setAdapter(adapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type == 1) {
                    binding.edtDealsIn.setText(adapter.getItem(position));
                } else {
                    binding.edtLanguage.setText(adapter.getItem(position));
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}