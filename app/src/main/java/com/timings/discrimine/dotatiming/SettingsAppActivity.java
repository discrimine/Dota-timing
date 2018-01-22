package com.timings.discrimine.dotatiming;



import android.content.DialogInterface;

import android.content.Intent;

import android.content.SharedPreferences;

import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.view.Window;

import android.view.WindowManager;

import android.widget.ImageButton;

import android.widget.Toast;



import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;



public class SettingsAppActivity extends AppCompatActivity {



    SharedPreferences sPref;



    String sound_save,vibro_save;



    int did;





    ImageButton ImgButSound,ImgButVibra,ImgButScheme,ImgButBack, ImgButAbout;







    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);





        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_settings_app);



        MobileAds.initialize(getApplicationContext(),"ca-app-pub-8974634454536111/7069146904");



        AdView adView = (AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()


                .build();

        adView.loadAd(adRequest);



        ImgButSound = (ImageButton) findViewById(R.id.imageButtonSound);

        ImgButVibra = (ImageButton) findViewById(R.id.imageButtonVibration);

        ImgButScheme = (ImageButton) findViewById(R.id.imageButtonScheme);

        ImgButBack = (ImageButton) findViewById(R.id.imageButtonBack);

        ImgButAbout = (ImageButton) findViewById(R.id.imageButtonAbout);



        loadText();



        loadTextVibro();



        if (sound_save.equals("true") | sound_save.equals("false")) {



            if (sound_save.equals("true")) {

                ImgButSound.setBackgroundResource(R.drawable.ic_empty_enable_svg);

            } else if (sound_save.equals("false")) {



                ImgButSound.setBackgroundResource(R.drawable.ic_empty_but_svg);

            }

        }

        else{

            ImgButSound.setBackgroundResource(R.drawable.ic_empty_enable_svg);



        }



        if (vibro_save.equals("true") | vibro_save.equals("false")) {



            if (vibro_save.equals("true")) {

                ImgButVibra.setBackgroundResource(R.drawable.ic_empty_enable_svg);

            } else if (vibro_save.equals("false")) {



                ImgButVibra.setBackgroundResource(R.drawable.ic_empty_but_svg);

            }

        }

        else{

            ImgButVibra.setBackgroundResource(R.drawable.ic_empty_enable_svg);



        }





        ImgButBack.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        Intent intent = new Intent(SettingsAppActivity.this, IndexActivity.class);

                        startActivity(intent);

                    }

                });









        ImgButSound.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        loadText();



                        if (sound_save.equals("true") | sound_save.equals("false")){

                            if (sound_save.equals("true")) {

                                ImgButSound.setBackgroundResource(R.drawable.ic_empty_but_svg);

                                saveText1();

                            }

                            else if (sound_save.equals("false")){





                                ImgButSound.setBackgroundResource(R.drawable.ic_empty_enable_svg);

                                saveText();





                                //Вы можете иметь любое количество операторов case.

                            }

                        }

                        else{

                            saveText();

                        }



                    }

                });



        ImgButVibra.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        loadTextVibro();

                        if (vibro_save.equals("true") | vibro_save.equals("false")){

                            if (vibro_save.equals("true")) {

                                ImgButVibra.setBackgroundResource(R.drawable.ic_empty_but_svg);

                                saveText1Vibro1();

                            }

                            else if (vibro_save.equals("false")){





                                ImgButVibra.setBackgroundResource(R.drawable.ic_empty_enable_svg);

                                saveTextVibro();





                                //Вы можете иметь любое количество операторов case.

                            }

                        }

                        else{

                            saveTextVibro();

                        }



                    }

                });







        ImgButScheme.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsAppActivity.this);

                        builder.setTitle("Whoops!")

                                .setMessage("Coming soon :)")

                                .setIcon(R.drawable.ic_logo)

                                .setNegativeButton("Close",

                                        new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int id) {

                                                dialog.cancel();

                                            }

                                        });

                        AlertDialog alert = builder.create();

                        alert.show();

                    }



                });



        ImgButAbout.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsAppActivity.this);

                        builder.setTitle("About")

                                .setMessage("DotaTiming Developers: \n" +

                                        "Andrew Huliuk \n" +

                                        "Vladimir Skebalo \n" +

                                        "If you have any suggestions or propositions, please e-mail me: discrimine96@gmail.com")

                                .setIcon(R.drawable.ic_logo)

                                .setNegativeButton("Close",

                                        new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int id) {

                                                dialog.cancel();

                                            }

                                        });

                        AlertDialog alert = builder.create();

                        alert.show();

                    }







                }

        );













    }

    void saveText() {

        sPref = getSharedPreferences("prefs",MODE_PRIVATE);

        SharedPreferences.Editor ed = sPref.edit();

        ed.putString("sound_save", "true");

        ed.commit();

        Toast.makeText(this, "Sound is on", Toast.LENGTH_SHORT).show();

    }



    void saveText1() {

        sPref = getSharedPreferences("prefs",MODE_PRIVATE);

        SharedPreferences.Editor ed = sPref.edit();

        ed.putString("sound_save", "false");

        ed.commit();

        Toast.makeText(this, "Sound is off", Toast.LENGTH_SHORT).show();

    }



    void loadText() {

        sPref = getSharedPreferences("prefs",MODE_PRIVATE);

        sound_save = sPref.getString("sound_save", "");



    }

    void saveTextVibro() {

        sPref = getSharedPreferences("prefs",MODE_PRIVATE);

        SharedPreferences.Editor ed = sPref.edit();

        ed.putString("vibro_save", "true");

        ed.commit();

        Toast.makeText(this, "Vibration is on", Toast.LENGTH_SHORT).show();

    }



    void saveText1Vibro1() {

        sPref = getSharedPreferences("prefs",MODE_PRIVATE);

        SharedPreferences.Editor ed = sPref.edit();

        ed.putString("vibro_save", "false");

        ed.commit();

        Toast.makeText(this, "Vibration is off", Toast.LENGTH_SHORT).show();

    }



    void loadTextVibro() {

        sPref = getSharedPreferences("prefs",MODE_PRIVATE);

        vibro_save = sPref.getString("vibro_save", "");



    }









}