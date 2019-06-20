package ru.digitbreak.mobileapp.service.network.api;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.digitbreak.mobileapp.service.network.Token;
import ru.digitbreak.mobileapp.service.network.TokenRequest;

public interface AuthApi {
    @POST("api/authenticate")
    Single<Token> logIn(@Body TokenRequest tokenRequest);
}
