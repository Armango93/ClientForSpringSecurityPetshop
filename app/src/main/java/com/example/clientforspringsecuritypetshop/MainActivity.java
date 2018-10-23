package com.example.clientforspringsecuritypetshop;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientforspringsecuritypetshop.managers.DataManager;
import com.example.clientforspringsecuritypetshop.model.Login;
import com.example.clientforspringsecuritypetshop.model.Pet;
import com.example.clientforspringsecuritypetshop.service.UserClient;
import com.example.clientforspringsecuritypetshop.utils.NetworkStatusChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private DataManager mDataManager = null;
    String token = null;
    EditText enterLogin;
    EditText enterPassword;
    Button loginBtn;
    String loginStr;
    String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button loginBtn = findViewById(R.id.loginBtn);
//        Button petListBtn = findViewById(R.id.listPetBtn);
        mDataManager = DataManager.getInstance();
        enterLogin = findViewById(R.id.login);
        enterPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.log_in);

//        System.out.println(enterLogin.getText());
//        if(enterLogin.getText()!=null){
//            loginStr = enterLogin.getText().toString();
//        }
//
//        if(enterPassword.getText()!=null){
//            passwordStr = enterPassword.getText().toString();
//        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterLogin.getText() == null || enterPassword.getText() == null) {
                    Toast.makeText(MainActivity.this, "Enter login and password!", Toast.LENGTH_LONG);
                } else {
                    login(enterLogin.getText().toString(), enterPassword.getText().toString());
                    System.out.println(loginStr + " " + passwordStr);
//                petList();
                }


            }
        });

//        petListBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                petList();
//            }
//        });
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                login();
//            }
//        });
    }

    private void login(String loginString, String password) {
        if (NetworkStatusChecker.isNetworkAvailable(this)) {

        Login login = new Login(loginString, password);
            System.out.println(login.toString());

        Call<ResponseBody> call = mDataManager.getmUserClient().login(login);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
//                    List<Header> headerList = response.headers();
//                    for(Header header : headerList) {
//                        Log.d(TAG, header.getName() + " " + header.getValue());
//                    }
                    token = response.headers().get("access-token");
                    mDataManager.getmPreferencesManager().saveToken(token);
                    Toast.makeText(MainActivity.this, response.headers().get("access-token"), Toast.LENGTH_LONG).show();
                    System.out.println(token+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    petList();
                } else {
                    Toast.makeText(MainActivity.this, "Not Ok =(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "We couldn't do it(", Toast.LENGTH_SHORT).show();
            }
        });

        } else{
                showToast("No connection!");
        }

    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }

    private void petList() {
        Call<List<Pet>> petList = mDataManager.getmUserClient().petList();
        final List<Pet> listPet = new ArrayList<>();

        petList.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if(response.isSuccessful()){
                    listPet.addAll(response.body());
                    mDataManager.getDaoSession().getPetDao().deleteAll();
                    mDataManager.getDaoSession().getPetDao().insertOrReplaceInTx(listPet);
                    Intent intent = new Intent(getApplicationContext(), PetActivity.class);
//                    intent.putExtra("petList", (Parcelable) listPet);
//                    intent.putParcelableArrayListExtra("petList", (ArrayList<? extends Parcelable>) listPet);
                    startActivity(intent);
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
