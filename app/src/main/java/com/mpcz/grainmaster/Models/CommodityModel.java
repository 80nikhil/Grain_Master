package com.mpcz.grainmaster.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mpcz.grainmaster.Retropack.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class CommodityModel {

    @SerializedName(Constants.STATUS)
    @Expose
    private Boolean status;

    @SerializedName(Constants.MESSAGE)
    @Expose
    private String message;

    @SerializedName(Constants.LIST)
    @Expose
    public ArrayList<CommodityModel.List> list;

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

    public ArrayList<CommodityModel.List> getList() {
        return list;
    }

    public void setList(ArrayList<CommodityModel.List> list) {
        this.list = list;
    }

    public class List implements Serializable {
        @SerializedName(Constants.ID)
        @Expose
        public String id;

        @SerializedName(Constants.NAME)
        @Expose
        public String name;

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
    }
}
