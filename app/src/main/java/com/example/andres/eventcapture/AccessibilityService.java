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
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.Locale;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
    private boolean broadcastActivate = false;

    public AccessibilityService() {
    }


    @Override
    protected boolean onGesture(int gestureId) {
        Log.e("GESTURE", String.valueOf(gestureId));
        return true;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        String  eventText = null;

        switch(eventType){
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Log.e("EVENT", "clicker " + event.getEventType());
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                Log.e("EVENT", "type view focused " + event.getEventType());
                AccessibilityNodeInfo source = event.getSource();
                Log.e("FOCUS",String.valueOf(source.getText()));
                if(source!=null){
                    AccessibilityNodeInfo source2 = source.focusSearch(View.FOCUS_DOWN);
                    if(source2!=null) {
                        Log.e("FOCUS2", String.valueOf(source2.getText()));
                    }
                }
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.e("EVENT", "Type view focused " + event.getEventType());
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED:
                Log.e("EVENT", "Type view FocusACcc");
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
                Log.e("EVENT", "Type View FOCUSSED ACC");
                break;
        }
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            if(broadcastActivate) {
                //Aqui probamos el broadcast que envia
                if (event.getKeyCode() == EventsSave.getInstance().getPrimerEvento()) {
                    Log.e("KeyEvent", "Primer pulsador");
                    Intent intent = new Intent();
                    intent.setClassName("com.example.andres.receiver", "com.example.andres.receiver.BackgroudService");
                    intent.setAction("pulsador 1");
                    this.sendBroadcast(intent);
                    return true;
                }
                if (event.getKeyCode() == EventsSave.getInstance().getSegundoEvento()) {
                    Log.e("KeyEvent", "Segundo pulsador");
                    Intent intent = new Intent();
                    intent.setClassName("com.example.andres.receiver", "com.example.andres.receiver.BackgroudService");
                    intent.setAction("pulsador 2");
                    this.sendBroadcast(intent);
                    return true;
                }
            }
            if(!broadcastActivate){
                //Aqui probamos el barrido de pantalla por componentes
             //   Log.e("Barrido","Empiza el barrido por componentes");
                //Log.e("BARRIDO", "Empezamos el barrido por componentes");
                if(event.getKeyCode() == EventsSave.getInstance().getPrimerEvento()){
                    Log.e("BARRIDO", String.valueOf(event.getDisplayLabel()));

                    //this.onKeyEvent(keyEvent);

                }
                if(event.getKeyCode() == EventsSave.getInstance().getSegundoEvento()){
                    Log.e("BARRIDO", String.valueOf(event.getDisplayLabel()));
                    KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER);
                    event.getSource();
                   // this.onKeyEvent(keyEvent);
                }
                if(event.getKeyCode() == KeyEvent.KEYCODE_SPACE){
                    Log.e("SPACE", "PRESS SPACe " + event.getKeyCode());
                }
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    Log.e("ENTER", "PRESS ENTER " + event.getKeyCode());
                }

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