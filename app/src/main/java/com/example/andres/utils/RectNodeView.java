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
        setWillNotDraw(false);
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
        this.canvas = canvas;
        Log.d("TamañoNodo","Estamos en el onDraw");
        Log.i("TamañoNodo","Alto R: " + getR().top + " ;Bajo R: " + getR().bottom +
                " ;Izq R: " + getR().left + " ;Der R: " + getR().right);

        Paint pq = new Paint();
        pq.setColor(Color.RED);
        canvas.drawRect(r,pq);
    }


    @SuppressLint("WrongCall")
    @Override
    protected void drawableStateChanged(){
        onDraw(canvas);
        super.drawableStateChanged();

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