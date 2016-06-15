package com.example.andres.eventcapture;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

import android.widget.Toast;


public class MyService extends Service  {

    public int eventUno;
    public int eventDos;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.eventUno = EventsSave.getInstance().getPrimerEvento();
        this.eventDos = EventsSave.getInstance().getSegundoEvento();

        if(Integer.parseInt(intent.getAction()) == this.eventUno) {
            Toast.makeText(this.getApplicationContext(), "Pulsador 1 pulsado", Toast.LENGTH_SHORT).show();
            Log.e("INTENT","inten 1 funciona");
        }
        if(Integer.parseInt(intent.getAction()) == this.eventDos){
            Toast.makeText(this.getApplicationContext(), "Pulsador 2 pulsado", Toast.LENGTH_SHORT).show();
            Log.e("INTENT","inten 2 funciona");
        }
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



}
