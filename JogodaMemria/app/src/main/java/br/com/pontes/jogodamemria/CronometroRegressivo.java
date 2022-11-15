package br.com.pontes.jogodamemria;

import android.os.Build;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class CronometroRegressivo {
    public static int TIME;
    private boolean timeIsRunning;
    private long timeLeftInMilles;
    private CountDownTimer countDownTimer;
    private TextView tv_time;
    CronometroRegressivo(int tempo, TextView textView, boolean isRunning){
        TIME = tempo;
        this.timeLeftInMilles = TIME*1000;
        this.timeIsRunning = isRunning;
        this.tv_time = textView;
        this.updateCountDown();
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(this.timeLeftInMilles, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilles = millisUntilFinished;
                updateCountDown();
            }
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFinish() {
                MainActivity ma = new MainActivity();

                timeIsRunning = false;
            }
        }.start();
    }

    private void updateCountDown() {
        int seconds = (int)timeLeftInMilles/1000;
        int tenthsSeconds = ((int)timeLeftInMilles - seconds*1000)/100;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", seconds,tenthsSeconds);
        tv_time.setText(timeLeftFormatted);
    }

    public void pauseTimer(){
        try {
            countDownTimer.cancel();
        }catch (Exception e){

        }
        timeIsRunning = false;
    }

    public void turnZeroTimer(){
        timeLeftInMilles = 0;
        updateCountDown();
    }



    public long getTimeLeftInMilles() {
        return timeLeftInMilles;
    }
}
