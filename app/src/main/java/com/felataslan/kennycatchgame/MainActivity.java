package com.felataslan.kennycatchgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
   int score;
   TextView timetext;
   TextView scoretext;
   ImageView imageView;
   ImageView imageView1;
   ImageView imageView2;
   ImageView imageView3;
   ImageView imageView4;
   ImageView imageView5;
   ImageView imageView6;
   ImageView imageView7;
   ImageView imageView8;

   ImageView[] imageArray;

   Handler handler;
   Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView1);
        imageView1=findViewById(R.id.imageView2);
        imageView2=findViewById(R.id.imageView3);
        imageView3=findViewById(R.id.imageView4);
        imageView4=findViewById(R.id.imageView5);
        imageView5=findViewById(R.id.imageView6);
        imageView6=findViewById(R.id.imageView7);
        imageView7=findViewById(R.id.imageView8);
        imageView8=findViewById(R.id.imageView9);

     imageArray= new  ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        hidekenny();

        scoretext=findViewById(R.id.scoreText);
        timetext=findViewById(R.id.timeText);

        score=0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timetext.setText("Time: "+ (l/1000));
            }

            @Override
            public void onFinish() {
                timetext.setText("Time Over..");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Resart?");
                alert.setMessage("are you sure resart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Resart

                        Intent intent =getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();

            }
        }.start();


    }
    public void incriseScore(View view){
        score++;
        scoretext.setText("Score: "+ score);


    }
    public void hidekenny(){

        handler =new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,500);

            }
        };
        handler.post(runnable);


    }
}