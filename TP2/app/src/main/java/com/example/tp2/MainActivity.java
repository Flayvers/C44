package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Surface s;
    LinearLayout parent;
    Button b;
    EditText champX, champY;
    Path p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = findViewById(R.id.parent);
        champX = findViewById(R.id.x);
        champY = findViewById(R.id.y);
        b = findViewById(R.id.button);


        s = new Surface(this);
        s.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        s.setBackgroundColor(Color.LTGRAY);
        parent.addView(s);

        p = new Path();

        Ecouteur ec = new Ecouteur();
        b.setOnClickListener(ec);
    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String textX = champX.getText().toString();
            String textY = champY.getText().toString();

            int x = Integer.parseInt(textX);
            int y = Integer.parseInt(textY);
            if (p.isEmpty())
                p.moveTo(x,y);
            else
                p.lineTo(x,y);
            s.invalidate();
        }
    }

    public class Surface extends View {
        Paint crayon;
        public Surface(Context context){
            super(context);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setStyle(Paint.Style.STROKE);
            crayon.setStrokeWidth(12);
        }

        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            canvas.drawPath(p,crayon);
        }
    }
}