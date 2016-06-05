package io.taweesoft.wonglhao.ui.views;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.Constant;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.managers.Load;
import io.taweesoft.wonglhao.managers.Utility;
import io.taweesoft.wonglhao.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements Observer {

    @Bind(R.id.etUsername) EditText etUsername;
    @Bind(R.id.etPassword) EditText etPassword;

    private ProgressDialog dialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void signin() {
        //Show loading dialog
        dialog = ProgressDialog.show(this,null,getString(R.string.pleaseWait));
        String username = etUsername.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString().trim();
        Map<String,String> map = new HashMap<>();
        map.put("username" , username);
        map.put("password" , password);
        Load load = Load.newInstance();
        load.addObserver(this);
        load.signIn(map);
    }

    @OnClick(R.id.btnSignup)
    public void signup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    /**
     * Receive data from Load signIn()
     * @param observable
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {
        if (o == null);
        Response<User> response = (Response<User>) o;
        if(response.isSuccessful()){
            // TODO: 4/13/16 AD  Save into SharedPreference
            User user = response.body();

            SharedPreferences.Editor editor = getSharedPreferences(Constant.APP_NAME, MODE_PRIVATE).edit();
            Gson gson = new Gson();
            editor.putString(Constant.USER, gson.toJson(user));
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            DataStorage.user = user;
            startActivity(intent);
            finish();
        }else{
            try {
                Utility.showToastDialog(this,response.errorBody().string(), false).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("errors" , response.raw().toString());
        }
        dialog.dismiss();
    }
}
