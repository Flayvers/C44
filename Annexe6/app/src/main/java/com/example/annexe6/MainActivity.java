package com.example.annexe6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout parent;
    Surface surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);

        //1
        surface = new Surface(this);
        //2
        //surface.setLayoutParams(new ConstraintLayout.LayoutParams(dpToPixel(400), dpToPixel(300)));
        surface.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,-1)); //match_parent = -1
        surface.setBackgroundColor(Color.RED);
        //3
        parent.addView(surface);

    }

    public int dpToPixel (int dp) {
        float densite = this.getResources().getDisplayMetrics().density;


        return (Math.round(dp*densite));
    }

    private class Surface extends View {

        Paint crayon;
        Paint crayon2;
        Paint crayon3;
        RectF box;

        Vector<Integer> colors;

        public Surface(Context context) {
            super(context);

            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.GREEN);
            crayon.setStyle(Paint.Style.STROKE); //default = fill -> fill(plein) != stroke("creux")
            crayon.setStrokeWidth(12);

            crayon2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon2.setColor(Color.YELLOW);

            crayon3 = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon3.setColor(Color.BLUE);
            crayon3.setStrokeWidth(10);
            crayon3.setStyle(Paint.Style.FILL);

            box = new RectF(100,100,1000,1000);

            colors = new Vector<>();

            colors.add(Color.GREEN);
            colors.add(Color.BLUE);
            colors.add(Color.YELLOW);
            colors.add(Color.WHITE);
            colors.add(Color.BLACK);
            colors.add(Color.CYAN);
            colors.add(Color.MAGENTA);
            colors.add(Color.LTGRAY);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawCircle((float) dpToPixel(75), (float) dpToPixel(75) ,(float) dpToPixel(50), crayon);
            canvas.drawCircle((float) dpToPixel(100), (float) dpToPixel(100) ,(float) dpToPixel(50), crayon2);
            canvas.drawRect(box, crayon);
            float start = 0;
            //draw slices
            for(int i =0; i < colors.size(); i++){
                crayon3.setColor(colors.get(i));
                float angle;
                angle = ((360.0f / colors.size()));
                canvas.drawArc(box, start, angle, true, crayon3);
                start += angle;
            }
        }
    }
}