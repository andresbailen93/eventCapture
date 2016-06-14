package com.example.andres.eventcapture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    public TextView primerEvento;
    public TextView segundoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        primerEvento = (TextView) findViewById(R.id.primerEvento);
        segundoEvento = (TextView) findViewById(R.id.segundoEvento);
    }

    public void continuaActivity(View v){
        Intent intent = new Intent(this, EventosActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        count++;
        if(count == 1) {
            EventsSave.getInstance().setPrimerEvento(keyCode);
            Toast.makeText(this.getApplicationContext(), String.valueOf(keyCode), Toast.LENGTH_SHORT).show();
            primerEvento.setText(String.valueOf(keyCode));

        }
        if(count == 2) {
            EventsSave.getInstance().setSegundoEvento(keyCode);
            Toast.makeText(this.getApplicationContext(), String.valueOf(keyCode), Toast.LENGTH_SHORT).show();
            segundoEvento.setText(String.valueOf(keyCode));
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

    }
}
