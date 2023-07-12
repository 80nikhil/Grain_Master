package com.mpcz.grainmaster.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mpcz.grainmaster.Retropack.Constants;

import java.util.ArrayList;

public class PlansModel {
    @SerializedName(Constants.STATUS)
    @Expose
    private Boolean status;

    @SerializedName(Constants.MESSAGE)
    @Expose
    private String message;

    @SerializedName(Constants.DATA)
    @Expose
    public ArrayList<PlansModel2> data;


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

    public ArrayList<PlansModel2> getData() {
        return data;
    }

    public void setData(ArrayList<PlansModel2> data) {
        this.data = data;
    }
    public class PlansModel2 {
        @SerializedName(Constants.ID)
        @Expose
        private String  id;

        @SerializedName(Constants.NAME)
        @Expose
        private String name;

        @SerializedName(Constants.DURATION)
        @Expose
        private String duration;

        @SerializedName(Constants.PRICE)
        @Expose
        private String  price;


        @SerializedName(Constants.CREATED_AT)
        @Expose
        private String created_date;

        @SerializedName(Constants.STATUS)
        @Expose
        private String status;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
