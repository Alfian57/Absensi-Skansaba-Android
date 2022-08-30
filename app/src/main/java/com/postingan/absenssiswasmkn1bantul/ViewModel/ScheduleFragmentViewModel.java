package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.postingan.absenssiswasmkn1bantul.Respository.ScheduleRepository;

public class ScheduleFragmentViewModel extends AndroidViewModel {
    private ScheduleRepository scheduleRepository;
    private Context context;

    public ScheduleFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void scheduleRepositoryCreate(){
        scheduleRepository = new ScheduleRepository(context);
    }

    public void getSchedule(String day){
        scheduleRepository.showSchedules(day);
    }
}
