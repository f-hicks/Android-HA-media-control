package com.fhicks.androidhamediacontrol.backend;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fhicks.androidhamediacontrol.MainActivity;

public class HomeAssistantConnector {

    private String baseUrl;
    private String accessToken;

    public HomeAssistantConnector(String baseUrl, String accessToken) {
        this.baseUrl = baseUrl;
        this.accessToken = accessToken;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void testConnection() {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(baseUrl + "/api/config")
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.out.println("Connection failed: " + response.code() + " " + response.message());
                }

                JSONObject json = new JSONObject(response.body().string());

                CharSequence text = (CharSequence) "Connection successful: " + json.getString("version");
                int duration = Toast.LENGTH_LONG;

                android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());
                mainHandler.post(() -> {
                    Toast.makeText(MainActivity.appContext, text, duration).show();
                });

                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Connection failed: " + e.getMessage());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

}