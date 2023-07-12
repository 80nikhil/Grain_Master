package com.mpcz.grainmaster;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Patterns;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

public class Util {

    public static void requestPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, 0);
        }
    }

    public static void copyFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    public static File getFileFromAssets(Activity activity, String filename) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File f = new File(activity.getExternalCacheDir(),path + filename);

        //File f = new File(activity.getCacheDir() + filename);
        try {
            // Make sure the Pictures directory exists.
            path.mkdirs();
            if (!f.exists()) {
                InputStream is = activity.getAssets().open(filename);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                FileOutputStream fos = new FileOutputStream(f);
                fos.write(buffer);
                fos.close();
            }
        }
            catch (IOException e) {
                e.printStackTrace();
            }

        return f;
    }

    public static boolean isStartByZero(String input){
        return input.startsWith("0");
    }

    // This will hide soft keyboard when touch outside
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final static boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static void ShowToastMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void ShowToastMessage(Context context, int message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

//    public static String GetAddress(Context context, Double latitude, Double longi) {
//        List<Address> addresses = null;
//        String address = "";
//        try {
//            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
//            addresses = geocoder.getFromLocation(latitude, longi, 1);
//            if (addresses.size() > 0) {
//                address = addresses.get(0).getAddressLine(0);
//                SharedPref.setSharedPreference(context, Constants.ADDRESS, addresses.get(0).getAddressLine(0));
//                //ctv_title_location_name.setText(addresses.get(0).getAddressLine(0));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return address;
//    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static String MakeDir(String filepath, Context appContext) {
        File path;
        if (!isExternalStorageAvailable() && !isExternalStorageReadOnly()) {
            path = new File(SaveFileIntoDir(filepath, appContext), filepath);
            if (!path.exists()) {
                path.mkdirs();
            }
        } else {
            path = new File(appContext.getExternalFilesDir(filepath), filepath);
            if (!path.exists()) {
                path.mkdirs();
            }
        }
        return path.toString();
    }

    public static File SaveFileIntoDir(String filepath, Context appContext) {
        File directory = appContext.getDir(filepath, Context.MODE_PRIVATE);
        return directory;
    }

    public boolean hasPermissions(Context context, String... perms) {
        for (String perm : perms) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }
        return true;
    }
}