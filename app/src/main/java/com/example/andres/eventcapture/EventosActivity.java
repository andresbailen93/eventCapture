package com.example.andres.eventcapture;

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
            Toast.makeText(this.getApplicationContext(), "Pulsador 1 pulsado", Toast.LENGTH_SHORT).show();
        }
        if(keyCode == EventsSave.getInstance().getSegundoEvento()){
            Toast.makeText(this.getApplicationContext(), "Pulsador 2 pulsado", Toast.LENGTH_SHORT).show();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

    }
}
