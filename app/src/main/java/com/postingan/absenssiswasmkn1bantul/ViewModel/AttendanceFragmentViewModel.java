package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.Response.PresentResponse;
import com.postingan.absenssiswasmkn1bantul.Respository.AttendanceRepository;

public class AttendanceFragmentViewModel extends AndroidViewModel {
    private AttendanceRepository presentRepository;
    public AttendanceFragmentViewModel(@NonNull Application application) {
        super(application);

        presentRepository = new AttendanceRepository(application);
    }

    public void present(String key){
        presentRepository.present(key);
    }

    public MutableLiveData<PresentResponse> getPresent(){
        return presentRepository.getPresent();
    }
}
