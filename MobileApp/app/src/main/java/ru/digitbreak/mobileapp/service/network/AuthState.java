package ru.digitbreak.mobileapp.service.network;

import io.reactivex.Completable;

public interface AuthState {

    String getJwtToken();

    Completable initToken(String login, String pass);
}
