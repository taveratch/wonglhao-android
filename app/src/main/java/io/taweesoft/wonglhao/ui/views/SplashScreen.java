package io.taweesoft.wonglhao.ui.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.liulishuo.magicprogresswidget.MagicProgressCircle;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.Constant;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.models.User;

public class SplashScreen extends AppCompatActivity {

    @Bind(R.id.circularProgressBar) MagicProgressCircle progressCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        progressCircle.setSmoothPercent(1f);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(Constant.APP_NAME, MODE_PRIVATE);
                String user = sharedPreferences.getString(Constant.USER , null);
                if(user == null){
                    navigateSignIn();
                }else{
                    Gson gson = new Gson();
                    DataStorage.user = gson.fromJson(user , User.class);
                    navigateToMain();
                }
                finish();
            }
        },2000);

    }

    public void navigateSignIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void navigateToMain() {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }
}

