package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.postingan.absenssiswasmkn1bantul.Model.Student;
import com.postingan.absenssiswasmkn1bantul.Respository.DetailRepository;

public class HomeFragmentViewModel extends AndroidViewModel {
    DetailRepository detailRepository;
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        detailRepository = new DetailRepository(application);
    }

    public LiveData<Student> detail(){
        return detailRepository.apiDetail();
    }
}
