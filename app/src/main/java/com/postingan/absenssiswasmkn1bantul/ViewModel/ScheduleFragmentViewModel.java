package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Respository.ScheduleRepository;

public class ScheduleFragmentViewModel extends AndroidViewModel {
    private ScheduleRepository scheduleRepository;

    public ScheduleFragmentViewModel(@NonNull Application application) {
        super(application);
        scheduleRepository = new ScheduleRepository(application);
    }

    public void schedule(String day){
        scheduleRepository.schedules(day);
    }

    public MutableLiveData<String> getSchedule(){
        return scheduleRepository.getSchedule();
    }
}
