package com.mpcz.grainmaster.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mpcz.grainmaster.Retropack.Constants;

import java.util.ArrayList;

public class NewsModel {
    @SerializedName(Constants.STATUS)
    @Expose
    private Boolean status;

    @SerializedName(Constants.MESSAGE)
    @Expose
    private String message;

    @SerializedName(Constants.DATA)
    @Expose
    public ArrayList<NewsModel2> data;

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

    public ArrayList<NewsModel2> getData() {
        return data;
    }

    public void setData(ArrayList<NewsModel2> data) {
        this.data = data;
    }

    public class NewsModel2 {
        @SerializedName(Constants.TITLE)
        @Expose
        public String title;

        @SerializedName(Constants.IMAGE)
        @Expose
        public String image;

        @SerializedName(Constants.DESCRIPTION)
        @Expose
        public String description;

        @SerializedName(Constants.CREATEDDATE)
        @Expose
        public String createddate;

        @SerializedName(Constants.COUNTRY)
        @Expose
        public String country;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreateddate() {
            return createddate;
        }

        public void setCreateddate(String createddate) {
            this.createddate = createddate;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
