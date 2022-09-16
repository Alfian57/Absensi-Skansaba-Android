package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Api.Response.ChangePasswordResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.LoginDetailResponse;
import com.postingan.absenssiswasmkn1bantul.Model.Student;
import com.postingan.absenssiswasmkn1bantul.Respository.ChangePassRepository;
import com.postingan.absenssiswasmkn1bantul.Respository.DetailRepository;

public class PasswordFragmentViewModel extends AndroidViewModel {
    private DetailRepository detailRepository;
    private ChangePassRepository changePassRepository;
    public PasswordFragmentViewModel(@NonNull Application application) {
        super(application);
        detailRepository = new DetailRepository(application);
        changePassRepository = new ChangePassRepository(application);
    }

    public void changePassword(String oldPass, String newPass){
        changePassRepository.changePass(oldPass, newPass);
    }

    public MutableLiveData<ChangePasswordResponse> getChangePassword(){
        return changePassRepository.getChangePass();
    }

    public LiveData<LoginDetailResponse> detail(){
        return detailRepository.userDetail();
    }
}
