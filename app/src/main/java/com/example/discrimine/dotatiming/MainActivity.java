package com.example.discrimine.dotatiming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimerRosh, countDownTimerMid, countDownTimerCarry, countDownTimerGlyph;

    private static final String FORMAT = "%02d:%02d";

    ImageButton imgButRosh,imgButGlyph,imgButCarry,imgButMid,imgButBack,imgButStop;

    TextView textViewRosh,textViewGlyph,textViewCarry,textViewMid;

    private MediaPlayer Roshan2Sound,GlyphSound, CarrySound, MidSound;

    boolean flagMid=(false), flagRosh=(false), flagCarry=(false), flagGlyph=(false), glag=(true);

    private Vibrator Vibro;

    SharedPreferences sPref;

    String sound_save,vibro_save;

    int vibro_timer=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);



        Vibro = (Vibrator)this.getSystemService(VIBRATOR_SERVICE);

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-8974634454536111/7069146904");

        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("504F195B4D14EFBEB49006E21491C888")
                .build();
        adView.loadAd(adRequest);

        loadText();
        loadTextVibro();

        imgButRosh = (ImageButton) findViewById(R.id.imgButRosh);

        textViewRosh = (TextView) findViewById(R.id.textViewRosh);

        imgButGlyph = (ImageButton) findViewById(R.id.imgButGlyph);

        textViewGlyph = (TextView) findViewById(R.id.textViewGlyph);

        imgButCarry = (ImageButton) findViewById(R.id.imgButCarry);

        textViewCarry = (TextView) findViewById(R.id.textViewCarry);

        imgButMid = (ImageButton) findViewById(R.id.imgButMid);

        textViewMid = (TextView) findViewById(R.id.textViewMid);

        imgButBack = (ImageButton) findViewById(R.id.imageButtonBack);

        imgButStop = (ImageButton) findViewById(R.id.imageButtonStop);


        Roshan2Sound = MediaPlayer.create(this, R.raw.roshan2);

        MidSound = MediaPlayer.create(this, R.raw.mid);

        GlyphSound= MediaPlayer.create(this, R.raw.glyph);

        CarrySound = MediaPlayer.create(this, R.raw.carry);



        if (sound_save.equals("true")){
            Roshan2Sound = MediaPlayer.create(this, R.raw.roshan2);
            MidSound = MediaPlayer.create(this, R.raw.mid);
            GlyphSound= MediaPlayer.create(this, R.raw.glyph);
            CarrySound = MediaPlayer.create(this, R.raw.carry);
        }
        else if(sound_save.equals("false")){
            Roshan2Sound = MediaPlayer.create(this, R.raw.silence);
            MidSound = MediaPlayer.create(this, R.raw.silence);
            GlyphSound= MediaPlayer.create(this, R.raw.silence);
            CarrySound= MediaPlayer.create(this, R.raw.silence);
        }

        if (vibro_save.equals("true")){
          vibro_timer=1;
        }
        else if(vibro_save.equals("false")){
           vibro_timer=10000;
        }


        imgButBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                        startActivity(intent);
                        if (flagRosh){ countDownTimerRosh.cancel(); }
                        if (flagGlyph){ countDownTimerGlyph.cancel(); }
                        if (flagMid){ countDownTimerMid.cancel(); }
                        if (flagCarry){ countDownTimerCarry.cancel(); }
                    }
                });

        imgButStop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadText();
                        if (flagRosh){ countDownTimerRosh.cancel();  textViewRosh.setText(""); imgButRosh.setBackgroundResource(R.drawable.ic_roshan_svg); }
                        if (flagGlyph){ countDownTimerGlyph.cancel(); textViewGlyph.setText(""); imgButGlyph.setBackgroundResource(R.drawable.ic_glyph_svg); }
                        if (flagMid){ countDownTimerMid.cancel(); textViewMid.setText(""); imgButMid.setBackgroundResource(R.drawable.ic_mid_svg); }
                        if (flagCarry){ countDownTimerCarry.cancel();  textViewCarry.setText(""); imgButCarry.setBackgroundResource(R.drawable.ic_carry_svg); }

                    }
                });


        // Button Roshan


        imgButRosh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagRosh){
                    countDownTimerRosh.cancel();
                    textViewRosh.setText("");
                    flagRosh=false;
                    imgButRosh.setBackgroundResource(R.drawable.ic_roshan_svg);
                }
                else{
                    countDownTimerRosh = new CountDownTimer(3 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            flagRosh=(true);
                            imgButRosh.setBackgroundResource(R.drawable.ic_roshan_enable_svg);
                            textViewRosh.setText("");
                            textViewRosh.setText(""+String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));


                        }

                        @Override
                        public void onFinish() {
                            imgButRosh.setBackgroundResource(R.drawable.ic_roshan_svg);
                            textViewRosh.setText("");
                            soundPlay(Roshan2Sound);
                            Vibro.vibrate(1500/vibro_timer);
                        }
                    }.start();

                }}
        });

        // Button Glyph

        imgButGlyph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagGlyph){
                    countDownTimerGlyph.cancel();
                    textViewGlyph.setText("");
                    flagGlyph=false;
                    imgButGlyph.setBackgroundResource(R.drawable.ic_glyph_svg);
                }
                else{
                    countDownTimerGlyph = new CountDownTimer(300 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            flagGlyph=(true);
                            imgButGlyph.setBackgroundResource(R.drawable.ic_glyph_enable_svg);
                            textViewGlyph.setText("");
                            textViewGlyph.setText(""+String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));


                        }

                        @Override
                        public void onFinish() {
                            imgButGlyph.setBackgroundResource(R.drawable.ic_glyph_svg);
                            textViewGlyph.setText("");
                            soundPlay(GlyphSound);
                            Vibro.vibrate(1500/vibro_timer);
                        }
                    }.start();

                }}
        });

        // Button Mid

        imgButMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagMid){
                    countDownTimerMid.cancel();
                    textViewMid.setText("");
                    flagMid=false;
                    imgButMid.setBackgroundResource(R.drawable.ic_mid_svg);
                }
                else{
                    countDownTimerMid = new CountDownTimer(360 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            flagMid=(true);
                            imgButMid.setBackgroundResource(R.drawable.ic_mid_enable_svg);
                            textViewMid.setText("");
                            textViewMid.setText(""+String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));


                        }

                        @Override
                        public void onFinish() {
                            imgButMid.setBackgroundResource(R.drawable.ic_mid_svg);
                            textViewMid.setText("");
                            soundPlay(MidSound);
                            Vibro.vibrate(1500/vibro_timer);
                        }
                    }.start();

                }}
        });


        // Button Carry

        imgButCarry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagCarry){
                    countDownTimerCarry.cancel();
                    textViewCarry.setText("");
                    flagCarry=false;
                    imgButCarry.setBackgroundResource(R.drawable.ic_carry_svg);
                }
                else{
                    countDownTimerCarry = new CountDownTimer(360 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            flagCarry=(true);
                            imgButCarry.setBackgroundResource(R.drawable.ic_carry_enable_svg);
                            textViewCarry.setText("");
                            textViewCarry.setText(""+String.format(FORMAT,

                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));


                        }

                        @Override
                        public void onFinish() {
                            imgButCarry.setBackgroundResource(R.drawable.ic_carry_svg);
                            textViewCarry.setText("");
                            soundPlay(CarrySound);
                            Vibro.vibrate(1500/vibro_timer);
                        }
                    }.start();

                }}
        });


    }


    public void soundPlay(MediaPlayer sound){
        sound.start();
    }



    void loadText() {
        sPref = getSharedPreferences("prefs",MODE_PRIVATE);
        sound_save = sPref.getString("sound_save", "");

    }

    void loadTextVibro() {
        sPref = getSharedPreferences("prefs",MODE_PRIVATE);
        vibro_save = sPref.getString("vibro_save", "");

    }


}



