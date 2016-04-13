package io.taweesoft.wonglhao.managers;

import io.taweesoft.wonglhao.models.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by TAWEESOFT on 4/13/16 AD.
 */
public interface APIService {

    @POST("signin")
    Call<User> signin(@Body RequestBody requestBody);

    @POST("signup")
    Call<User> signup(@Body User user);

}
