package com.postingan.absenssiswasmkn1bantul.Api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.postingan.absenssiswasmkn1bantul.Model.ChangePassData;

public class ChangePasswordResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private Object errors;
    @SerializedName("data")
    @Expose
    private ChangePassData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public ChangePassData getData() {
        return data;
    }

    public void setData(ChangePassData data) {
        this.data = data;
    }
}
