package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.Response.LoginDetailResponse;
import com.postingan.absenssiswasmkn1bantul.Model.Student;
import com.postingan.absenssiswasmkn1bantul.Respository.DetailRepository;

public class HomeFragmentViewModel extends AndroidViewModel {
    DetailRepository detailRepository;
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        detailRepository = new DetailRepository(application);
    }

    public MutableLiveData<LoginDetailResponse> userDetail(){
        return detailRepository.userDetail();
    }
}
