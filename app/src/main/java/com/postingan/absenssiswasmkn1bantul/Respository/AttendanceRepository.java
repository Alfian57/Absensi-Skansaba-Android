package com.postingan.absenssiswasmkn1bantul.Respository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.ApiConfig;
import com.postingan.absenssiswasmkn1bantul.Api.ApiRequest;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetAttendanceResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.PresentResponse;
import com.postingan.absenssiswasmkn1bantul.Helper.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceRepository {
    private final ApiRequest apiRequest;
    private final User user;
    MutableLiveData<PresentResponse> presentMutableLiveData;
    MutableLiveData<GetAttendanceResponse> myAttendanceMutableLiveData;

    public AttendanceRepository(Application application) {
        this.apiRequest = ApiConfig.getClient(application).create(ApiRequest.class);
        this.user = new User(application);
        presentMutableLiveData = new MutableLiveData<>();
        myAttendanceMutableLiveData = new MutableLiveData<>();
    }

    public void getMyAttendace(Integer month, Integer year){
        Call<GetAttendanceResponse> call = apiRequest.MyAttendance(user.getToken(), month, year);
        call.enqueue(new Callback<GetAttendanceResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetAttendanceResponse> call, @NonNull Response<GetAttendanceResponse> response) {
                if (response.body() != null){
                    myAttendanceMutableLiveData.postValue(response.body());
                } else {
                    myAttendanceMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAttendanceResponse> call, @NonNull Throwable t) {
                Log.e("myAttendance", t.toString());
                myAttendanceMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<GetAttendanceResponse> getMyAttendance(){
        return myAttendanceMutableLiveData;
    }

    public void present(String key){
        Call<PresentResponse> call = apiRequest.Absen(user.getToken(), key);
        call.enqueue(new Callback<PresentResponse>() {
            @Override
            public void onResponse(@NonNull Call<PresentResponse> call,@NonNull Response<PresentResponse> response) {
                if (response.body() != null){
                    presentMutableLiveData.postValue(response.body());
                } else {
                    presentMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PresentResponse> call,@NonNull Throwable t) {
                Log.e("Present", t.toString());
                presentMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<PresentResponse> getPresent(){
        return presentMutableLiveData;
    }
}
