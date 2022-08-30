package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.postingan.absenssiswasmkn1bantul.Respository.LoginRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private LoginRepository loginRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        loginRepository = new LoginRepository(application);
    }

    public void logout(){
        loginRepository.apiLogout();
    }
}
