package com.mpcz.grainmaster.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mpcz.grainmaster.Retropack.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class YellowPagesModel implements Serializable {
    @SerializedName(Constants.STATUS)
    @Expose
    private Boolean status;

    @SerializedName(Constants.MESSAGE)
    @Expose
    private String message;

    @SerializedName(Constants.LIST)
    @Expose
    public ArrayList<List> list;

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

    public ArrayList<List> getList() {
        return list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }

    public class List implements Serializable {
        @SerializedName(Constants.CID)
        @Expose
        public String cid;

        @SerializedName(Constants.NAME)
        @Expose
        public String name;

        @SerializedName(Constants.ADDRESS)
        @Expose
        public String address;

        @SerializedName(Constants.COUNTRY)
        @Expose
        public String country;

        @SerializedName(Constants.STATE)
        @Expose
        public String state;

        @SerializedName(Constants.CITY)
        @Expose
        public String city;

        @SerializedName(Constants.MOBILENO)
        @Expose
        public String mobileno;

        @SerializedName(Constants.PASSWORD)
        @Expose
        public String password;

        @SerializedName(Constants.ALTMOBILENO)
        @Expose
        public String altmobileno;

        @SerializedName(Constants.USER_PIC)
        @Expose
        public String user_pic;

        @SerializedName(Constants.BUSSINESS_CARD_PIC)
        @Expose
        public String bussiness_card_pic;

        @SerializedName(Constants.EMAIL)
        @Expose
        public String email;

        @SerializedName(Constants.BUSSINESS_NAME)
        @Expose
        public String business_name;

        @SerializedName(Constants.BUSSINESS_TYPE)
        @Expose
        public String business_type;

        @SerializedName(Constants.GSTIN)
        @Expose
        public String gstin;

        @SerializedName(Constants.DEALSIN)
        @Expose
        public String dealsin;

        @SerializedName(Constants.WALLET)
        @Expose
        public String wallet;

        @SerializedName(Constants.ACTIVE_PACK)
        @Expose
        public String active_pack;

        @SerializedName(Constants.PACK_START_DATE)
        @Expose
        public String pack_start_date;

        @SerializedName(Constants.PACK_END_DATE)
        @Expose
        public String pack_end_date;

        @SerializedName(Constants.LANGUAGE)
        @Expose
        public String language;

        @SerializedName(Constants.CREATEDDATE)
        @Expose
        public String createdDate;

        @SerializedName(Constants.STATUS)
        @Expose
        public String status;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMobileno() {
            return mobileno;
        }

        public void setMobileno(String mobileno) {
            this.mobileno = mobileno;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAltmobileno() {
            return altmobileno;
        }

        public void setAltmobileno(String altmobileno) {
            this.altmobileno = altmobileno;
        }

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }

        public String getBussiness_card_pic() {
            return bussiness_card_pic;
        }

        public void setBussiness_card_pic(String bussiness_card_pic) {
            this.bussiness_card_pic = bussiness_card_pic;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getBusiness_type() {
            return business_type;
        }

        public void setBusiness_type(String business_type) {
            this.business_type = business_type;
        }

        public String getGstin() {
            return gstin;
        }

        public void setGstin(String gstin) {
            this.gstin = gstin;
        }

        public String getDealsin() {
            return dealsin;
        }

        public void setDealsin(String dealsin) {
            this.dealsin = dealsin;
        }

        public String getWallet() {
            return wallet;
        }

        public void setWallet(String wallet) {
            this.wallet = wallet;
        }

        public String getActive_pack() {
            return active_pack;
        }

        public void setActive_pack(String active_pack) {
            this.active_pack = active_pack;
        }

        public String getPack_start_date() {
            return pack_start_date;
        }

        public void setPack_start_date(String pack_start_date) {
            this.pack_start_date = pack_start_date;
        }

        public String getPack_end_date() {
            return pack_end_date;
        }

        public void setPack_end_date(String pack_end_date) {
            this.pack_end_date = pack_end_date;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
