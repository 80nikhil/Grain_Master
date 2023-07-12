package com.mpcz.grainmaster.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mpcz.grainmaster.Retropack.Constants;

import java.util.ArrayList;

public class BannerModel {

    @SerializedName(Constants.STATUS)
    @Expose
    private Boolean status;

    @SerializedName(Constants.MESSAGE)
    @Expose
    private String message;

    @SerializedName(Constants.DATA)
    @Expose
    public ArrayList<BannerModel2> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<BannerModel2> getData() {
        return data;
    }

    public void setData(ArrayList<BannerModel2> data) {
        this.data = data;
    }

    public class BannerModel2{
        @SerializedName(Constants.ID)
        @Expose
        private String id;

        @SerializedName(Constants.IMAGE_PATH)
        @Expose
        private String image_path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }
    }
}
