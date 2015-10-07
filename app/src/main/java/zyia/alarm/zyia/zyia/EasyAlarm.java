package zyia.alarm.zyia.zyia;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;


public class EasyAlarm extends FragmentActivity {

    MediaPlayer mp = new MediaPlayer();
    MediaPlayer songPlayer = new MediaPlayer();
    String temp;
    String song,text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_alarm);


        SharedPreferences preferences1 = getSharedPreferences("musicList", MODE_PRIVATE);
        song = preferences1.getString("EASYMUSIC", "");





        SharedPreferences preferences3 = getSharedPreferences("TextList", MODE_PRIVATE);
        temp = preferences3.getString("EASYTEXT", "");
        text = Environment.getExternalStorageDirectory() + "/myAppCache/" + temp;



        try {
            mp.setDataSource(text);
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            mp.prepare();
            mp.setVolume(0.2f, 0.2f);





            songPlayer.setDataSource(song);
            songPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer songPlayer) {
                    songPlayer.start();
                }
            });

            songPlayer.prepare();
            songPlayer.setVolume(0.7f,0.7f);
            songPlayer.setLooping(true);




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

    }




    public void alarmClose(View view)
    {
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
