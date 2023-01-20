package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.Response.GetAttendanceResponse;
import com.postingan.absenssiswasmkn1bantul.Respository.AttendanceRepository;

public class AttendanceHistoryActivityViewModel extends AndroidViewModel {
    private final AttendanceRepository presentRepository;

    public AttendanceHistoryActivityViewModel(@NonNull Application application) {
        super(application);
        presentRepository = new AttendanceRepository(application);
    }

    public void getMyAttendance(Integer month, Integer year){
        presentRepository.getMyAttendace(month, year);
    }

    public MutableLiveData<GetAttendanceResponse> getMyAttendance(){
        return presentRepository.getMyAttendance();
    }
}
