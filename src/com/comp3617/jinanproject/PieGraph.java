package com.comp3617.jinanproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class PieGraph extends View {

    Paint paint = new Paint();

    int c[] = {Color.parseColor("#66FF66"), Color.parseColor("#FF99FF")};

    Canvas mCanvas = new Canvas();

    private final int[] values;

    Context context;

    public PieGraph(Context context, int[] values) {
        super(context);
        paint = new Paint();
        this.values = values;
        super.draw(mCanvas);
    }

    @Override
    public void draw(Canvas canvas) {
        int x = getWidth();
        int y = getHeight();
        float t = getTotal();

        paint.setColor(Color.parseColor("#78777D"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        canvas.drawRect(0, 0, x - 1, y - 1, paint);
        int n = values.length;
        float cuurPos = -90;
        paint.setStyle(Paint.Style.FILL);
        RectF rect = new RectF(20,20,x-20,y-20);

        for (int i = 0; i < n; i++) {
            paint.setColor(c[i]);
            float thita =(t==0)?0: 360*values[i]/t;
            canvas.drawArc(rect, cuurPos, thita, true, paint);
            cuurPos = cuurPos+thita;
        }
    }

    private float getTotal() {
        int total = 0;
        for (int i = 0; i < values.length; i++) {
            total = total + values[i];
        }
        return total;
    }
}