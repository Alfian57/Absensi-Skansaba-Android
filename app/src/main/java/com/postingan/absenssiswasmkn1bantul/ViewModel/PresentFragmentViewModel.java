package com.postingan.absenssiswasmkn1bantul.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.postingan.absenssiswasmkn1bantul.Respository.PresentRepository;

public class PresentFragmentViewModel extends AndroidViewModel {
    private PresentRepository presentRepository;
    public PresentFragmentViewModel(@NonNull Application application) {
        super(application);

        presentRepository = new PresentRepository(application);
    }

    public void present(String key){
        presentRepository.present(key);
    }
}
