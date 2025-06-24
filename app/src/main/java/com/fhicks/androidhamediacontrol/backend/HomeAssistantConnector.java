package com.fhicks.androidhamediacontrol.backend;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public String testConnection() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseUrl + "/api/discovery_info")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return "Connection failed: " + response.code() + " " + response.message();
            }
            JSONObject json = new JSONObject(response.message());

            return "Connection successful: " + json.getString("version");

        }
         catch (IOException e) {
            e.printStackTrace();
            return "Connection failed: " + e.getMessage();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}