package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
    private ApiRequest apiRequest;
    User user;
    MutableLiveData<Boolean> changePassMutableLiveData;

    public ChangePassRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        user = new User(application);
        changePassMutableLiveData = new MutableLiveData<>();
    }

    public void changePass(String oldPass, String newPass){
        Call<ChangePasswordResponse> call = apiRequest.UpdatePass(user.getToken(), Integer.valueOf(user.getId()), oldPass, newPass);
        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ChangePasswordResponse> call, @NonNull Response<ChangePasswordResponse> response) {
                if (response.body() != null){
                    if(response.body().getData() != null){
                        changePassMutableLiveData.postValue(true);
                    } else {
                        changePassMutableLiveData.postValue(false);
                    }
                } else {
                    changePassMutableLiveData.postValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChangePasswordResponse> call, @NonNull Throwable t) {
                Log.e("changePass", t.toString());
                changePassMutableLiveData.postValue(false);
            }
        });
    }

    public MutableLiveData<Boolean> getChangePass(){
        return changePassMutableLiveData;
    }

}
