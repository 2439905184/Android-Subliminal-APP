package zyia.alarm.zyia.zyia;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;


public class MainActivity extends FragmentActivity {

    String s1;



    int i=0;
    int superX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runThread();

        s1="hello world";








        ImageView img = (ImageView)findViewById(R.id.logo);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://zyia.me/"));
                startActivity(intent);
            }
        });

        ImageView top = (ImageView)findViewById(R.id.top_button);
        top.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            confirmFireMissiles();

            }
        });

    }

    public void confirmFireMissiles() {
        DialogFragment newFragment = new FireMissiles();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

    private void runThread() {

        new Thread() {
            public void run() {
                while (i++ < 10) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                MessageList sample = new MessageList();
                                sample.MessageMethod();
                                String dumb = sample.message[i];
                                TextView msg = (TextView)findViewById(R.id.messages);
                                msg.setText(dumb);
                                if(i==10)
                                {
                                    i=0;
                                }
                            }
                        });
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }






    public void settings(View view)
    {
        startActivity(new Intent(MainActivity.this, Settings.class));
        finish();

    }

    public void easySleepAct(View view)
    {
        startActivity(new Intent(MainActivity.this, EasySleep.class));
        finish();

    }

    public void alarmSet1(View view)
    {

        int a1=1;
        int idTime = (int) System.currentTimeMillis();
        Log.w("myApp", "SAVED VALUE IS:"+idTime);



        if (((ToggleButton) view).isChecked()) {
            Intent intent = new Intent(MainActivity.this, AddAlarm.class);
            intent.putExtra("pendInt", idTime);
            intent.putExtra("tts", a1);
            startActivity(intent);
        }

        else
        {


            SharedPreferences sa = getSharedPreferences("TimesList", Context.MODE_PRIVATE);
            int rec = sa.getInt("ID1",0);
            Log.w("myApp", "RECEIVED VALUE IS:"+rec);


            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            myIntent.setAction("my.action.string");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                    rec, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            pendingIntent.cancel();
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);

        }
    }
    public void alarmSet2(View view)
    {
        int a2=2;
        int idTime = (int) System.currentTimeMillis();

        if (((ToggleButton) view).isChecked()) {
            Intent intent = new Intent(MainActivity.this, AddAlarm.class);
            intent.putExtra("pendInt", idTime);
            intent.putExtra("tts", a2);
            startActivity(intent);
        }
        else
        {
            SharedPreferences sa = getSharedPreferences("TimesList", Context.MODE_PRIVATE);
            int rec = sa.getInt("ID2",0);
            Log.w("myApp", "RECEIVED VALUE IS:"+rec);


            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            myIntent.setAction("my.action.string");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                    rec, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            pendingIntent.cancel();
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }
    public void alarmSet3(View view)
    {
        int a3=3;
        int idTime = (int) System.currentTimeMillis();

        if (((ToggleButton) view).isChecked()) {
            Intent intent = new Intent(MainActivity.this, AddAlarm.class);
            intent.putExtra("pendInt", idTime);
            intent.putExtra("tts", a3);
            startActivity(intent);
        }
        else
        {
            SharedPreferences sa = getSharedPreferences("TimesList", Context.MODE_PRIVATE);
            int rec = sa.getInt("ID3",0);
            Log.w("myApp", "RECEIVED VALUE IS:"+rec);


            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            myIntent.setAction("my.action.string");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                    rec, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            pendingIntent.cancel();
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }

    public void alarmSet4(View view)
    {
        int a4=4;
        int idTime = (int) System.currentTimeMillis();

        if (((ToggleButton) view).isChecked()) {
            Intent intent = new Intent(MainActivity.this, AddAlarm.class);
            intent.putExtra("pendInt", idTime);
            intent.putExtra("tts", a4);
            startActivity(intent);
        }
        else
        {
            SharedPreferences sa = getSharedPreferences("TimesList", Context.MODE_PRIVATE);
            int rec = sa.getInt("ID4",0);
            Log.w("myApp", "RECEIVED VALUE IS:"+rec);


            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            myIntent.setAction("my.action.string");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                    rec, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            pendingIntent.cancel();
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


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
