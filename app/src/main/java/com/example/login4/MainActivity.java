package com.example.login4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Base64;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String email = "abc@gmail.com";
        String password = "Nepalnepal12!";
        // Sending side
        String base64Password = Base64.getEncoder().encodeToString(password.getBytes());
        JsonObject json = new JsonObject();
        json.addProperty("email", email);
        json.addProperty("password", base64Password);

        Ion.with(getBaseContext())
                .load("http://103.69.126.198/Api/Token/Create")
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        System.out.println(result.toString());
                    }
                });
    }
}