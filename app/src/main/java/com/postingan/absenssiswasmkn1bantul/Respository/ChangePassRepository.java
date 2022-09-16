package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.ChangePasswordResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassRepository {
    private final ApiRequest apiRequest;
    User user;
    MutableLiveData<ChangePasswordResponse> changePassMutableLiveData;

    public ChangePassRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        user = new User(application);
        changePassMutableLiveData = new MutableLiveData<>();
    }

    public void changePass(String oldPass, String newPass){
        Call<ChangePasswordResponse> call = apiRequest.UpdatePass(user.getToken(), oldPass, newPass);
        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ChangePasswordResponse> call, @NonNull Response<ChangePasswordResponse> response) {
                if (response.body() != null){
                    changePassMutableLiveData.postValue(response.body());
                } else {
                    changePassMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChangePasswordResponse> call, @NonNull Throwable t) {
                Log.e("changePass", t.toString());
                changePassMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<ChangePasswordResponse> getChangePass(){
        return changePassMutableLiveData;
    }

}
