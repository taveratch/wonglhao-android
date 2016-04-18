package io.taweesoft.wonglhao.ui.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.managers.Load;
import io.taweesoft.wonglhao.managers.Utility;
import io.taweesoft.wonglhao.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements Observer{

    @Bind(R.id.etUsername) EditText etUsername;
    @Bind(R.id.etPassword) EditText etPassword;
    @Bind(R.id.etConfirmPassword) EditText etConfirmPassword;
    @Bind(R.id.etEmail) EditText etEmail;
    @Bind(R.id.etFirstname) EditText etFirstname;
    @Bind(R.id.etLastname) EditText etLastname;
    @Bind(R.id.rbMale) RadioButton rbMale;
    @Bind(R.id.rbFemale) RadioButton rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
        rbMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    rbFemale.setChecked(false);
            }
        });

        rbFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    rbMale.setChecked(false);
            }
        });
    }
    @OnClick(R.id.btnSignup)
    public void signup() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String rePassword = etConfirmPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String firstname = etFirstname.getText().toString().trim();
        String lastname = etLastname.getText().toString().trim();
        String gender;
        if(rbMale.isChecked())
            gender = "male";
        else
            gender = "female";
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setConfirmPassword(rePassword);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setGender(gender);
        Load load = Load.newInstance();
        load.addObserver(this);
        load.signUp(user);

    }

    @OnClick(R.id.btnBack)
    public void back() {
        finish();
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o == null) return;
        Response<User> response = (Response<User>)o;
        if(response.isSuccessful()) {
            User user = response.body();
            Intent intent = new Intent(this, MainActivity.class);
            DataStorage.user = user;
            startActivity(intent);
            finish();
        }else{
            try {
                Utility.showToastDialog(this,response.errorBody().string() , false).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
