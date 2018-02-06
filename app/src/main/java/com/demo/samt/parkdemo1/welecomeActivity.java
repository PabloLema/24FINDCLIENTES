package com.demo.samt.parkdemo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class welecomeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout l1,l2;
    Button btntodos,btnparqueadero;
    Animation uptodown,downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welecome);
        btntodos = (Button)findViewById(R.id.buttonsub);
        btntodos.setOnClickListener(this);

        btnparqueadero = (Button)findViewById(R.id.btnpar);
        btnparqueadero.setOnClickListener(this);
        l1 = (LinearLayout) findViewById(R.id.l1);
        //l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
       // l2.setAnimation(downtoup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonsub:
                Intent intent1 = new Intent(this,LoginLocales.class);
                startActivity(intent1);
                break;
            case R.id.btnpar:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
