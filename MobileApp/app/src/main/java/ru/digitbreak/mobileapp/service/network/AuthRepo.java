package ru.digitbreak.mobileapp.service.network;

import android.util.Base64;

import javax.inject.Inject;

import io.reactivex.Completable;
import ru.digitbreak.mobileapp.di.AppScope;
import ru.digitbreak.mobileapp.service.SharedPreferenceService;
import ru.digitbreak.mobileapp.service.network.api.AuthApi;

@AppScope
public class AuthRepo implements AuthState {

    private final SharedPreferenceService sharedPreferenceService;
    private final AuthApi authApi;
    private String token;


    @Inject
    public AuthRepo(SharedPreferenceService sharedPreferenceService, AuthApi authApi) {
        this.sharedPreferenceService = sharedPreferenceService;
        this.authApi = authApi;
    }

    @Override
    public String getJwtToken() {
        return token != null ? token : sharedPreferenceService.getSavedValue("token","");
    }

    private void saveJwtToken(String jwtToken) {
        if (!jwtToken.equals(getJwtToken())) {
            token = jwtToken;
            sharedPreferenceService.setSavedValue("token", jwtToken);
        }
    }

    public Completable initToken(String login, String pass) {
        return Completable.fromSingle(
                authApi.logIn(new TokenRequest(login, pass))
                        .retry(3)
                        .map(token -> {
                            saveJwtToken(token.id_token);
                            return token;
                        }));
    }
}
