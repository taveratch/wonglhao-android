package io.taweesoft.wonglhao.managers;

import io.taweesoft.wonglhao.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by TAWEESOFT on 4/13/16 AD.
 */
public interface APIService {
    @POST("signin")
    Call<User> getUser(@Query("username") String username , @Query("password") String password);

    @POST("signup")
    Call<User> signup(@Body User user);

}
