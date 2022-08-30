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
import com.postingan.absenssiswasmkn1bantul.Helper.User;
import com.postingan.absenssiswasmkn1bantul.MainActivity;
import com.postingan.absenssiswasmkn1bantul.Model.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository {
    private Context context;
    private ApiRequest apiRequest;
    private MutableLiveData<Student> mutableLiveData = new MutableLiveData<>();
    private User user;

    public DetailRepository(Application application) {
        this.context = application.getApplicationContext();
        this.apiRequest = ApiConfig.getClient(context).create(ApiRequest.class);
        user = new User(context);
    }

    public MutableLiveData<Student> apiDetail() {
        Call<LoginDetailResponse> call = apiRequest.Me(user.getToken(), Integer.valueOf(user.getId()));
        call.enqueue(new Callback<LoginDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginDetailResponse> call, @NonNull Response<LoginDetailResponse> response) {
                if (response.body() != null) {
                    if (response.body().getData() != null) {
                        if (response.body().getData().getStudent() != null) {
                            mutableLiveData.setValue(response.body().getData().getStudent());
                        } else {
                            if (response.body().getMessage() != null) {
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Gagal Mendapatkan Data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        if (response.body().getMessage() != null) {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginDetailResponse> call, @NonNull Throwable t) {
                Log.e("detail", t.toString());
                Toast.makeText(context, "Terjadi Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        });
        return mutableLiveData;
    }
}
