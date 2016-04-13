package io.taweesoft.wonglhao.managers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TAWEESOFT on 4/13/16 AD.
 */
public class HttpManager {
    private static HttpManager httpManager = null;
    private HttpManager() {}
    public static HttpManager getInstance() {
        if(httpManager == null)
            httpManager = new HttpManager();
        return httpManager;
    }

    public APIService getAPIService(Class<APIService> apiServiceClass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(apiServiceClass);
    }
}
