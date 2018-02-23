package com.example.sayed.againwebapipostpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private LongInAPI longInAPI;
    private EditText userNameET, userPassET;
    private String baseUrl = "http://dotnet.nerdcastlebd.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = (EditText) findViewById(R.id.user_name_et);
        userPassET = (EditText) findViewById(R.id.user_password_et);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        longInAPI = retrofit.create(LongInAPI.class);
    }

    public void logInBT(View view) {
        LogInModel logInModel = new LogInModel(userNameET.getText()
                .toString(), userPassET.getText().toString());

        Call<LogInResponse> logInResponseCall = longInAPI.logInUser(logInModel);

        logInResponseCall.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                LogInResponse logInResponse = response.body();
                Toast.makeText(MainActivity.this, ""+logInResponse.getResultState(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
