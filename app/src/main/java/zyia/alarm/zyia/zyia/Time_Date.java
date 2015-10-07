package zyia.alarm.zyia.zyia;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;


public class Time_Date extends FragmentActivity
{
    //int a=12000;

    int vibInt = 0;
    MediaPlayer mp = new MediaPlayer();
    MediaPlayer songPlayer = new MediaPlayer();
    MediaPlayer voicePlayer = new MediaPlayer();
    String song,voice,text;
    int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__date);
        String temp;
        Intent wow = getIntent();

        state = wow.getIntExtra("finisher",0);



        if(state==1) {

            SharedPreferences preferences1 = getSharedPreferences("musicList", MODE_PRIVATE);

            song = preferences1.getString("MUSICONE", "");


            SharedPreferences preferences2 = getSharedPreferences("recordList", MODE_PRIVATE);
            temp = preferences2.getString("VOICEONE", "");
            voice = Environment.getExternalStorageDirectory() + "/myAppCache2/" + temp;


            SharedPreferences preferences3 = getSharedPreferences("TextList", MODE_PRIVATE);
            temp = preferences3.getString("ALARMONE", "");
            text = Environment.getExternalStorageDirectory() + "/myAppCache/" + temp;

        }
        if(state==2)
        {
            SharedPreferences preferences1 = getSharedPreferences("musicList", MODE_PRIVATE);

            song = preferences1.getString("MUSICTWO", "");

            Toast.makeText(getApplicationContext(), "adipoli:"+state,
                    Toast.LENGTH_SHORT).show();

            SharedPreferences preferences2 = getSharedPreferences("recordList", MODE_PRIVATE);
            temp = preferences2.getString("VOICETWO", "");
            voice = Environment.getExternalStorageDirectory() + "/myAppCache2/" + temp;


            SharedPreferences preferences3 = getSharedPreferences("TextList", MODE_PRIVATE);
            temp = preferences3.getString("ALARMTWO", "");
            text = Environment.getExternalStorageDirectory() + "/myAppCache/" + temp;


        }

        if(state==3)
        {
            SharedPreferences preferences1 = getSharedPreferences("musicList", MODE_PRIVATE);

            song = preferences1.getString("MUSICTHREE", "");


            SharedPreferences preferences2 = getSharedPreferences("recordList", MODE_PRIVATE);
            temp = preferences2.getString("VOICETHREE", "");
            voice = Environment.getExternalStorageDirectory() + "/myAppCache2/" + temp;


            SharedPreferences preferences3 = getSharedPreferences("TextList", MODE_PRIVATE);
            temp = preferences3.getString("ALARMTHREE", "");
            text = Environment.getExternalStorageDirectory() + "/myAppCache/" + temp;



        }

        if(state==4)
        {
            SharedPreferences preferences1 = getSharedPreferences("musicList", MODE_PRIVATE);

            song = preferences1.getString("MUSICFOUR", "");


            SharedPreferences preferences2 = getSharedPreferences("recordList", MODE_PRIVATE);
            temp = preferences2.getString("VOICEFOUR", "");
            voice = Environment.getExternalStorageDirectory() + "/myAppCache2/" + temp;


            SharedPreferences preferences3 = getSharedPreferences("TextList", MODE_PRIVATE);
            temp = preferences3.getString("ALARMFOUR", "");
           text = Environment.getExternalStorageDirectory() + "/myAppCache/" + temp;


        }


        try {
            mp.setDataSource(text);
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            mp.prepare();
            mp.setVolume(0.4f, 0.4f);





            songPlayer.setDataSource(song);
            songPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer songPlayer) {
                    songPlayer.start();
                }
            });

            songPlayer.prepare();
            songPlayer.setLooping(true);


            voicePlayer.setDataSource(voice);

            voicePlayer.setLooping(true);
            voicePlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer voicePlayer) {
                    voicePlayer.start();
                }
            });
            voicePlayer.prepare();
            voicePlayer.setVolume(0.5f,0.5f);

        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (IllegalMonitorStateException e)
        {
            e.printStackTrace();
        }



            final Handler hlr = new Handler();

            final Runnable looper = new Runnable() {
                @Override
                public void run()
                {
                    try {

                        if (mp != null && mp.isPlaying()) {
                            Log.d("TAG------->", "mp is running");
                            mp.stop();
                            Log.d("TAG------->", "mp is stopped");
                            mp.release();
                            Log.d("TAG------->", "mp is released");

                        }


                        mp.start();
                    }
                    catch(Exception e)
                    {
                        Log.d("Thenga------->", "Here is the error buddy!");

                    }

                }
            };


            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    hlr.postDelayed(looper, 5000);
                }
            });





        Intent receive = getIntent();

        vibInt = receive.getIntExtra("vibrator",1);

        if(vibInt==1){

            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

            v.vibrate(5000);
            vibInt=0;


        }








    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {


                    finish();

                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {


                    finish();
                }
                return true;
            case KeyEvent.KEYCODE_CAMERA:
                if (action == KeyEvent.ACTION_DOWN) {


                    finish();
                }
            default:
                return super.dispatchKeyEvent(event);
        }
    }



    public void dismissButton(View view)
    {
        finishAffinity();
    }

    public void snoozeup(View view)
    {
        SharedPreferences sa = getSharedPreferences("SnoozeList", Context.MODE_PRIVATE);
        int snox = sa.getInt("SnoozeX",0);



        if(snox==2) {




            Handler handler = new Handler();
            Runnable x = new Runnable() {
                @Override
                public void run()
                {
                    Intent intent = new Intent(Time_Date.this, Time_Date.class);
                    intent.putExtra("finisher", state);

                    startActivity(intent);

                }
            };
            handler.postDelayed(x, 120000);

        }

        else if(snox==3) {

            Handler handler = new Handler();
            Runnable x = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Time_Date.this, Time_Date.class);
                    intent.putExtra("finisher", state);

                    startActivity(intent);
                }
            };
            handler.postDelayed(x, 180000);

        }
        else if(snox==5) {

            Handler handler = new Handler();
            Runnable x = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Time_Date.this, Time_Date.class);
                    intent.putExtra("finisher", state);

                    startActivity(intent);
                }
            };
            handler.postDelayed(x, 300000);

        }
        else if(snox==10) {

            Handler handler = new Handler();
            Runnable x = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Time_Date.this, Time_Date.class);
                    intent.putExtra("finisher", state);

                    startActivity(intent);
                }
            };
            handler.postDelayed(x, 600000);

        }
        else
        {
            Handler handler = new Handler();
            Runnable x = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Time_Date.this, Time_Date.class);
                    intent.putExtra("finisher", state);

                    startActivity(intent);
                }
            };
            handler.postDelayed(x, 180000);

        }



        finish();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        try
        {
            if (mp.isPlaying()) {
                mp.stop();
            }

            mp.release();

            if (voicePlayer.isPlaying()) {
                voicePlayer.stop();
            }

            voicePlayer.release();

            if (songPlayer.isPlaying()) {
                songPlayer.stop();
            }

            songPlayer.release();
        }
        catch(Exception e)
        {
            Log.d("TAG------->", "Here is the error buddy!");

        }




    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


