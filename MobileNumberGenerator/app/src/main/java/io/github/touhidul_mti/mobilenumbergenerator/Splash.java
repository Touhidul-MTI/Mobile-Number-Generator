package io.github.touhidul_mti.mobilenumbergenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();//to hide actionbar title

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();

                }catch (InterruptedException e){

                }
            }
        };
        thread.start();
    }
}
