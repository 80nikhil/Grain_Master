package com.mpcz.grainmaster;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.Retropack.Data;
import com.mpcz.grainmaster.Retropack.List;

import java.util.ArrayList;

public class Retroresponse {

    @SerializedName(Constants.STATUS)
    @Expose
    private Boolean status;

    @SerializedName(Constants.MESSAGE)
    @Expose
    private String message;

    @SerializedName(Constants.DATA)
    @Expose
    public ArrayList<Data> data;

    @SerializedName(Constants.LIST)
    @Expose
    private List list;

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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
