package io.taweesoft.wonglhao.managers;

import io.taweesoft.wonglhao.models.Element;
import io.taweesoft.wonglhao.models.Review;
import io.taweesoft.wonglhao.models.User;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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

    @GET("getAllPromotions")
    Call<Element> getAllPromotions(@Query("limit") int limit);

    @GET("getAllBar")
    Call<Element> getAllBar(@Query("limit") int limit);

    @POST("searchBar")
    Call<Element> searchBar(@Body RequestBody requestBody);

    @POST("getBar")
    Call<Element> getBar(@Body RequestBody requestBody);

    @POST("checkin")
    Call<Element> checkin(@Body RequestBody requestBody);

    @POST("getCheckedin")
    Call<Element> getCheckedIn(@Body RequestBody requestBody);

    @POST("reviewPub")
    Call<Review> reviewBar(@Body RequestBody requestBody);

    @POST("getNearBy")
    Call<Element> getNearBy(@Body RequestBody requestBody);
}
