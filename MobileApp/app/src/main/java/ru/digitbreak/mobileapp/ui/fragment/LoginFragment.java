package ru.digitbreak.mobileapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.digitbreak.mobileapp.App;
import ru.digitbreak.mobileapp.R;
import ru.digitbreak.mobileapp.service.network.AuthRepo;

public class LoginFragment extends Fragment {

    @Inject
    AuthRepo authRepo;

    private EditText edPass;
    private EditText edLogin;

    private static Callback callback;

    public static LoginFragment newInstance(Callback cl) {
        callback = cl;
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        edPass = root.findViewById(R.id.pass);
        edLogin = root.findViewById(R.id.login);
        root.findViewById(R.id.submit).setOnClickListener(v -> authRepo.initToken(edLogin.getText().toString(),edPass.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable ->  Toast.makeText(getContext(),"Error "+throwable.getLocalizedMessage(),Toast.LENGTH_SHORT).show())
                .subscribe(() -> callback.back(), Log::getStackTraceString));
        return root;
    }

    public interface Callback{
        void back();
    }
}
