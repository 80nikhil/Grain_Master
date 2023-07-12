package com.mpcz.grainmaster.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mpcz.grainmaster.Retropack.Constants;

import java.util.ArrayList;

public class UpdatesModel {

    @SerializedName(Constants.STATUS)
    @Expose
    private Boolean status;

    @SerializedName(Constants.MESSAGE)
    @Expose
    private String message;

    @SerializedName(Constants.DATA)
    @Expose
    public ArrayList<Data> data;

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

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data {
        @SerializedName(Constants.ID)
        @Expose
        private String id;

        @SerializedName(Constants.CREATED_AT)
        @Expose
        private String created_at;

        @SerializedName(Constants.SMS_DATA)
        @Expose
        private String sms_data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getSms_data() {
            return sms_data;
        }

        public void setSms_data(String sms_data) {
            this.sms_data = sms_data;
        }
    }
}
