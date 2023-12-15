package com.task.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.MessageFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView stopwatchDisplay;
    private Button startButton;
    private Button holdButton;
    private Button stopButton;
    private Handler handler;
    private boolean isRunning;
    private int seconds;
    private int milliseconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        stopwatchDisplay = findViewById(R.id.stopwatch_display);
        startButton = findViewById(R.id.start_button);
        holdButton = findViewById(R.id.hold_button);
        stopButton = findViewById(R.id.stop_button);
        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopwatch();
            }
        });
        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdStopwatch();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopwatch();
            }
        });

    }
        private void stopStopwatch(){
            isRunning=false;
            seconds=0;
            milliseconds=0;
            stopwatchDisplay.setText("00:00:00.00");
        }
        private void holdStopwatch(){
            isRunning=false;
        }
        private void startStopwatch(){
            isRunning=true;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    int hours = seconds/3600;
                    int minutes = (seconds%3600)/60;
                    int secs = seconds%60;

                    String time = String.format("%d:%02d:%02d.%03d",hours,minutes,secs,milliseconds);
                    stopwatchDisplay.setText(time);
                    if (isRunning){
                        milliseconds++;
                        if (milliseconds==100){
                            seconds++;
                            milliseconds=0;
                        }
                    }
                    handler.postDelayed(this,0);
                }
            });






        }


}
