package com.example.andres.eventcapture;

/**
 * Created by andres on 15/06/2016.
 */
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.Locale;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {

    public AccessibilityService() {
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        switch(event.getEventType()){
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Log.e("EVENT", "clicker " +event.getEventType());
                break;
        }
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            if(event.getKeyCode() == EventsSave.getInstance().getPrimerEvento()){
                Log.e("KeyEvent", "Primer pulsador");
                Intent intent = new Intent();
                intent.setClassName("com.example.andres.receiver","com.example.andres.receiver.BackgroudService");
                intent.setAction("pulsador 1");
                this.sendBroadcast(intent);
                return true;
            }
            if(event.getKeyCode() == EventsSave.getInstance().getSegundoEvento()){
                Log.e("KeyEvent", "Segundo pulsador");
                Intent intent = new Intent();
                intent.setClassName("com.example.andres.receiver","com.example.andres.receiver.BackgroudService");
                intent.setAction("pulsador 2");
                this.sendBroadcast(intent);
                return true;
            }
        }
        return super.onKeyEvent(event);
        //return super.onKeyEvent(event);
    }

    @Override
    public void onInterrupt() {
        Log.v("TAG", "onInterrupt");
    }

}