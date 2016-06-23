package com.example.andres.eventcapture;

/**
 * Created by andres on 15/06/2016.
 */
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.example.andres.utils.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.example.andres.utils.AccessibilityServiceCompatUtils;
import com.example.andres.utils.LogUtils;
import com.example.andres.utils.RectNodeView;

import java.util.Locale;

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
    private boolean broadcastActivate = false;

    private boolean firstAction = true;
    AccessibilityNodeInfo source;
    AccessibilityNodeInfo currentNode;
    private int parent = 0;
    public boolean debug = true;
    public String position;
    long initTime;
    RectNodeView rnv;

    AccessibilityNodeInfo root;
    public boolean firstEvent = true;
    AccessibilityNodeInfoCompat rootWithFocus;


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
        String eventText = null;

        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                if (source != null) {
                    AccessibilityNodeInfo source2 = source.focusSearch(View.FOCUS_DOWN);
                    if (source2 != null) {
                        //   Log.e("FOCUS2", String.valueOf(source2.getText()));
                    }
                }

                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                //Log.e("EVENT", "type view focused22 " + event.getEventType());
                break;
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                this.root = getRootInActiveWindow();
                rnv = new RectNodeView(this);
                firstAction = true;
                Log.e("CAMBIO","cambio de ventana");
        }
    }


    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (broadcastActivate) {
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
            if (!broadcastActivate) {
                 if(event.getKeyCode() == KeyEvent.KEYCODE_SPACE){

                }
                if (event.getKeyCode() == EventsSave.getInstance().getSegundoEvento()) {
                    Log.e("BARRIDO", String.valueOf(event.getDisplayLabel()));
                    KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER);
                    event.getSource();
                    // this.onKeyEvent(keyEvent);
                }
            }

        }
        if(event.getAction() == KeyEvent.ACTION_UP){
            if(event.getKeyCode() == KeyEvent.KEYCODE_SPACE) {
                if(!root.isClickable()) {
                    if (firstAction) {
                        AccessibilityNodeInfoCompat node = new AccessibilityNodeInfoCompat(this.root);
                        AccessibilityNodeInfoCompat nextNode = findFocus(node);
                        this.rootWithFocus = nextNode;
                        this.firstAction = false;

                        Log.e("Root", String.valueOf(nextNode.isClickable()));
                        Log.e("Root", String.valueOf(nextNode.getContentDescription() + nextNode.getInfo().toString()));

                        Rect rect = new Rect();
                        nextNode.getBoundsInScreen(rect);

                        rnv.setR(rect);
                        //nextNode.
                        // AccessibilityServiceCompatUtils.setFocusOnFirstElement(root);
                        //AccessibilityNodeInfo accessibilityNodeInfoCompat = findFocus(AccessibilityNodeInfo.FOCUS_ACCESSIBILITY);
                    }else{
                        AccessibilityNodeInfoCompat nextNode = findFocus(this.rootWithFocus);
                        this.rootWithFocus = nextNode;

                        Log.e("Root", String.valueOf(nextNode.isClickable()));
                        Log.e("Root", String.valueOf(nextNode.getContentDescription() +" / " + nextNode.getInfo().toString()));

                        Rect rect = new Rect();
                        nextNode.getBoundsInScreen(rect);

                        rnv.setR(rect);
                    }
                }

            }
            if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                if(rootWithFocus != null) {
                    this.rootWithFocus.performAction(AccessibilityNodeInfoCompat.ACTION_CLICK);
                }

            }
        }
        return super.onKeyEvent(event);
        //return super.onKeyEvent(event);
    }

    private AccessibilityNodeInfoCompat findFocus(AccessibilityNodeInfoCompat node){
        AccessibilityNodeInfoCompat nextNode = node.focusSearch(View.FOCUS_FORWARD);
        return nextNode;
    }


    @Override
    public void onInterrupt() {
        Log.v("TAG", "onInterrupt");
    }

}