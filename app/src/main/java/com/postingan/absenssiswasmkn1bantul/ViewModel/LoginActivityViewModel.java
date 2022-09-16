package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.postingan.absenssiswasmkn1bantul.Respository.LoginRepository;

public class LoginActivityViewModel extends AndroidViewModel {
    private LoginRepository loginRepository;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);

        loginRepository = new LoginRepository(application);
    }

    public MutableLiveData<Boolean> getlogin(){
        return loginRepository.getLogin();
    }

    public void login(String nis, String password){
        loginRepository.login(nis, password);
    }
}
