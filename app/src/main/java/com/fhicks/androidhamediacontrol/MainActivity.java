package com.fhicks.androidhamediacontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fhicks.androidhamediacontrol.backend.HomeAssistantConnector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Home Assistant Connector
        HomeAssistantConnector connector = new HomeAssistantConnector(
                "REDACTED",
                "REDACTED"
        );

        System.out.println(connector.testConnection());


    }
}