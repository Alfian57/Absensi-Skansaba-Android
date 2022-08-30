package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.postingan.absenssiswasmkn1bantul.Respository.LoginRepository;

public class LoginActivityViewModel extends AndroidViewModel {
    private LoginRepository loginRepository;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);

        loginRepository = new LoginRepository(application);
    }

    public void login(String nis, String password){
        loginRepository.apiLogin(nis, password);
    }
}
