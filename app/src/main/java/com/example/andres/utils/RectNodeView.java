package com.example.andres.utils;

/**
 * Created by andres on 23/06/2016.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Toast;

public class RectNodeView extends ViewGroup{

    private Rect r;
    private Paint p;
    private Canvas canvas;
    protected int widthstroke = 10;
    protected String linecolor = "red";

    private static final String tag = "RectNodeView";

    public RectNodeView(Context context) {
        super(context);
        r = new Rect();
        p = new Paint();
        canvas = new Canvas();
//		Paint p= new Paint();

    }

    public Rect getR() {
        return r;
    }

    public void setR(Rect r) {
        this.r = r;
    }


    protected void onLayout(boolean changed, int l, int t, int r, int b) {


    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        Log.d("TamañoNodo","Estamos en el onDraw");
        Log.i("TamañoNodo","Alto R: " + getR().top + " ;Bajo R: " + getR().bottom +
                " ;Izq R: " + getR().left + " ;Der R: " + getR().right);
//		Paint p= new Paint();
//		p.
        try{
            p.setColor(Color.parseColor(linecolor));
        }catch (Exception e){
            e.printStackTrace();
        }
        p.setStrokeWidth(widthstroke);
        p.setStyle(Style.STROKE);
//		p.setStyle(Style.FILL_AND_STROKE);
//		p.setStyle(Style.FILL);
//		p.setAlpha(int a);
//		p.setStrokeCap(null);
//		p.setStrokeJoin(null);
//		p.setStrokeMiter();
        //canvas.drawRect(getR(), p);
        Paint pq = new Paint();
        pq.setColor(Color.RED);
        canvas.drawRect(getR(),pq);
    }


    @SuppressLint("WrongCall")
    @Override
    protected void drawableStateChanged(){
//		Log.d("TamañoNodo","En el DRAWABLESTATECHANGED");
//		Log.i("RectNodeView","Alto R: " + getR().top + " ;Bajo R: " + getR().bottom +
//				" ;Izq R: " + getR().left + " ;Der R: " + getR().right);
//		p.setColor(Color.rgb(0, 0, 255));
//		p.setStrokeWidth(10);
//		p.setStyle(Style.STROKE);
//		canvas.drawRect(getR(), p);
        onDraw(canvas);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("DetectandoBack","El BACK vale ---> " + keyCode);
        // TODO Auto-generated method stub
        return processKeyEvent (keyCode, event, true);
//		return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d("DetectandoBack","El BACK vale ---> " + keyCode);
        // TODO Auto-generated method stub
        return processKeyEvent (keyCode, event, true);
//		return super.onKeyUp(keyCode, event);
    }


    boolean processKeyEvent (int keycode, KeyEvent event, boolean down)
    {

        if(keycode == event.KEYCODE_BACK){
            Toast.makeText(getContext(), "Función deshabilitada", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public int getWidthstroke() {
        return widthstroke;
    }

    public void setWidthstroke(int widthstroke) {
        this.widthstroke = widthstroke;
    }

    public String getLinecolor() {
        return linecolor;
    }

    public void setLinecolor(String linecolor) {
        this.linecolor = linecolor;
    }



}