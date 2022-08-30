package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.ChangePasswordResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassRepository {
    private Context context;
    private ApiRequest apiRequest;
    User user;

    public ChangePassRepository(Application application) {
        this.context = application.getApplicationContext();
        this.apiRequest = ApiConfig.getClient(context).create(ApiRequest.class);
        user = new User(context);
    }

    public void changePass(String oldPass, String newPass){
        Call<ChangePasswordResponse> call = apiRequest.UpdatePass(user.getToken(), Integer.valueOf(user.getId()), oldPass, newPass);
        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ChangePasswordResponse> call, @NonNull Response<ChangePasswordResponse> response) {
                if (response.body() != null){
                    if(response.body().getMessage() != null){
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Gagal Mengganti Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Gagal Mengganti Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChangePasswordResponse> call, @NonNull Throwable t) {
                Log.e("changePass", t.toString());
                Toast.makeText(context, "Terjadi Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
