package com.example.appexamen2;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private Ecouteur ec;
    private TraitContinu traitContinu;
    private LinearLayout parent;
    ConstraintLayout drawing;
    SeekBar seekBar;
    Surface surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ec = new Ecouteur();


        drawing = findViewById(R.id.sectionHaut);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setProgress(3);
        seekBar.setMax(9);
        traitContinu = new TraitContinu(seekBar.getProgress());

        surface = new Surface(this);
        surface.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1)); //match_parent = -1
        surface.setBackgroundColor(getColor(R.color.sand));
        drawing.addView(surface);


        parent = findViewById(R.id.sectionBas);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout layout = (LinearLayout) child;
                for (int j = 0; j < layout.getChildCount(); j++) {
                    View component = layout.getChildAt(j);
                    if (component instanceof ImageButton) {
                        ImageButton btn = (ImageButton) component;
                        btn.setOnClickListener(ec);
                    }
                    if (component instanceof SeekBar) {
                        SeekBar sb = (SeekBar) component;
                        sb.setOnSeekBarChangeListener(ec);
                    }
                }
            }
        }
    }

    private class Ecouteur implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag();
            switch (tag){
                case "haut":
                    traitContinu.moveY(-20);
                    break;
                case "bas":
                    traitContinu.moveY(20);
                    break;
                case "gauche":
                    traitContinu.moveX(-20);
                    break;
                case "droite":
                    traitContinu.moveX(20);
                    break;
            }
            surface.invalidate();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            traitContinu.setPathWidth(seekBar.getProgress() +1);
            surface.invalidate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private class Surface extends View {

        public Surface(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            traitContinu.dessiner(canvas);
        }
    }
}