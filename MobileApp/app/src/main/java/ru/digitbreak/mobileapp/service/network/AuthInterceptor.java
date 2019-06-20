package ru.digitbreak.mobileapp.service.network;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final AuthState authState;
    private final String authorization = "Authorization";

    @Inject
    public AuthInterceptor(AuthState authState) {
        this.authState = authState;
           }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (authState.getJwtToken() == null || chain.request().header(authorization) != null)
            return chain.proceed(chain.request());

        final Request.Builder builder = chain.request().newBuilder();
        builder.addHeader(authorization, "Bearer " + authState.getJwtToken());

        return chain.proceed(builder.build());
    }

}
