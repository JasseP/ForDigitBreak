package ru.digitbreak.mobileapp.service.network.api;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @POST("api/info")
    Single<Response<ResponseBody>> sendCashPayments(@Body List<String> sendList);

    @GET("api/info")
    Single<List<String>> getReceivables(@Query("isActive") Boolean isActive,
                                            @Query("lastModified") Long timeStamp);
}
