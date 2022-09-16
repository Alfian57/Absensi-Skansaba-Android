package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.LoginDetailResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.LogoutResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;
import com.postingan.absenssiswasmkn1bantul.MainActivity;
import com.postingan.absenssiswasmkn1bantul.UI.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private ApiRequest apiRequest;
    User user;
    MutableLiveData<Boolean> loginMutableLiveData;
    MutableLiveData<Boolean> logoutMutableLiveData;

    public LoginRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        this.user = new User(application);
        loginMutableLiveData = new MutableLiveData<>();
        logoutMutableLiveData = new MutableLiveData<>();
    }

    public void login(String nis, String password){
        Call<LoginDetailResponse> call = apiRequest.Login(nis, password);
        call.enqueue(new Callback<LoginDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginDetailResponse> call, @NonNull Response<LoginDetailResponse> response) {
                if (response.body() != null){
                    if (response.body().getData() != null){
                        if (response.body().getData().getStudent() != null){
                            user.setId(response.body().getData().getStudent().getId());
                            user.setToken(response.body().getData().getAccessToken());
                            loginMutableLiveData.postValue(true);
                        }
                        else {
                            loginMutableLiveData.postValue(false);
                        }
                    } else {
                        loginMutableLiveData.postValue(false);
                    }
                } else {
                    loginMutableLiveData.postValue(false);
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginDetailResponse> call, @NonNull Throwable t) {
                Log.e("login", t.toString());
                loginMutableLiveData.postValue(false);
            }
        });
    }

    public MutableLiveData<Boolean> getLogin(){
        return loginMutableLiveData;
    }

    public void logout(){
        Call<LogoutResponse> call = apiRequest.Logout(user.getToken());
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call,@NonNull Response<LogoutResponse> response) {
                if (response.body() != null){
                    if (response.body().getMessage() != null) {
                        logoutMutableLiveData.postValue(true);
                    } else {
                        logoutMutableLiveData.postValue(false);
                    }
                } else {
                    logoutMutableLiveData.postValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call,@NonNull Throwable t) {
                Log.e("logout", t.toString());
                logoutMutableLiveData.postValue(false);
            }
        });
    }

    public MutableLiveData<Boolean> getLogout(){
        return logoutMutableLiveData;
    }

}
