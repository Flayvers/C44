package com.example.annexe4b;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    String code = "4110";
    Ecouteur ec;
    EditText password;
    LinearLayout bg;
    TableLayout buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = findViewById(R.id.table);
        ec = new Ecouteur();
        bg = findViewById(R.id.background);
        password = findViewById(R.id.Password);

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 3; j++){
                TableRow row = (TableRow)buttons.getChildAt(i);
                row.getChildAt(j).setOnClickListener(ec);
                if(i == 3) break;
            }
    }

    class Ecouteur implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            Button b = (Button)v;
            String text = String.valueOf(password.getText())  + String.valueOf(b.getText());
            password.setText(text);


            if(password.length() == 4){
                if(String.valueOf(password.getText()).equals(code)){
                    bg.setBackgroundColor(Color.rgb(131,255,152));
                }
                else{
                    bg.setBackgroundColor(Color.rgb(255,131,131));
                }
                password.setText("");
            }



        }
    }
}