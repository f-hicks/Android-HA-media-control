package com.fhicks.androidhamediacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.fhicks.androidhamediacontrol.backend.HomeAssistantConnector;

public class MainActivity extends AppCompatActivity {

    public static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Home Assistant Connector
        HomeAssistantConnector connector = new HomeAssistantConnector(
                "REDACTED",
                "REDACTED"
        );
        appContext = getApplicationContext();

        connector.testConnection();



    }
}