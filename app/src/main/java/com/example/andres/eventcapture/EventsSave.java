package com.example.andres.eventcapture;

import android.app.Application;

/**
 * Created by andres on 14/06/2016.
 */
public class EventsSave extends Application {
    private int primerEvento;
    private int segundoEvento;
    private static EventsSave mInstance;

    public static synchronized EventsSave getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public int getPrimerEvento() {
        return primerEvento;
    }

    public void setPrimerEvento(int primerEvento) {
        this.primerEvento = primerEvento;
    }

    public int getSegundoEvento() {
        return segundoEvento;
    }

    public void setSegundoEvento(int segundoEvento) {
        this.segundoEvento = segundoEvento;
    }
}
