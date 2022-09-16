package com.postingan.absenssiswasmkn1bantul.UI;

import android.Manifest;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.postingan.absenssiswasmkn1bantul.ViewModel.PresentFragmentViewModel;
import com.postingan.absenssiswasmkn1bantul.databinding.FragmentPresentBinding;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class PresentFragment extends Fragment {
    FragmentPresentBinding binding;
    PresentFragmentViewModel presentFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBinding.inflate(inflater, container, false);

        presentFragmentViewModel = new ViewModelProvider(this).get(PresentFragmentViewModel.class);

        presentFragmentViewModel.getPresent().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(binding.getRoot().getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        binding.zxingScan.setResultHandler(new ZXingScannerView.ResultHandler() {
                            @Override
                            public void handleResult(Result result) {
                                presentFragmentViewModel.present(result.getText());
                                binding.zxingScan.resumeCameraPreview(this);
                            }
                        });
                        binding.zxingScan.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(binding.getRoot().getContext(), "Akses Kamera Ditolak", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        return binding.getRoot();
    }
}