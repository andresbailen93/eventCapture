package com.example.andres.eventcapture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class EventosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == EventsSave.getInstance().getPrimerEvento()){
            Intent intent = new Intent(this, MyService.class);
            intent.setAction(String.valueOf(keyCode));
            startService(intent);
        }
        if(keyCode == EventsSave.getInstance().getSegundoEvento()){
            Intent intent = new Intent(this, MyService.class);
            intent.setAction(String.valueOf(keyCode));
            startService(intent);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

    }
}
