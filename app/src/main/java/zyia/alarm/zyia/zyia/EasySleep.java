package zyia.alarm.zyia.zyia;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;


public class EasySleep extends FragmentActivity {

    Calendar calendar;

    int i=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_sleep);
        calendar = Calendar.getInstance();

        runThread();

        ImageButton im1 = (ImageButton)findViewById(R.id.easyBrain);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Subliminal Mode",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton im2 = (ImageButton)findViewById(R.id.easyAudible);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Loud Mode Activated",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton im3 = (ImageButton)findViewById(R.id.allEasy);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Multiple Mode Activated",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton im4 = (ImageButton)findViewById(R.id.easyRepeat);
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Repeat Mode Activated",
                        Toast.LENGTH_SHORT).show();
            }
        });

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

    private void runThread() {

        new Thread() {
            public void run() {
                while (i-- > 0) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                MessageList sample = new MessageList();
                                sample.MessageMethod();
                                String parse = sample.message[i];
                                TextView msg = (TextView)findViewById(R.id.messagesEasy);
                                msg.setText(parse);
                                if(i==0)
                                {
                                    i=10;
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

    public void settingsEasy(View view)
    {
        Intent intentz = new Intent(EasySleep.this, Settings.class);
        startActivity(intentz);

    }

    public void easyButton(View view)
    {
        LinearLayout easyLi = (LinearLayout)findViewById(R.id.lineEasy);

        if (((ToggleButton) view).isChecked())
        {

            Toast.makeText(getApplicationContext(), "EASY SLEEP ON",
                    Toast.LENGTH_SHORT).show();

            easyLi.setVisibility(View.VISIBLE);
        }

        else
        {
            Toast.makeText(getApplicationContext(), "EASY SLEEP OFF",
                    Toast.LENGTH_SHORT).show();
            easyLi.setVisibility(View.GONE);
        }


    }

    public void plusTime (View view)
    {
        //Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(EasySleep.this,new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker,
                                  int selectedHour, int selectedMinute)
            {

                //calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                calendar.set(Calendar.MINUTE, selectedMinute);



                setAlarm(calendar);

            }

        }, hour, minute, false);// Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void setAlarm(Calendar targetCal)
    {
        int pen = 21;
        int tempx = 1;
        Intent alarmintent = new Intent(EasySleep.this, Alarm2Receiver.class);
        alarmintent.setAction("my.action.string");
        alarmintent.putExtra("ALARM_NO", tempx);

        PendingIntent sender = PendingIntent.getBroadcast(EasySleep.this, pen, alarmintent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), sender);
    }

    public void mediaEasy(View view)
    {
        int patch=9;
        Intent intentx = new Intent(EasySleep.this, MusicActivity.class);
        intentx.putExtra("MUS", patch);
        startActivity(intentx);
    }

    public void textEasy(View view)
    {
        int patch=9;
        Intent intenty = new Intent(EasySleep.this, QuoteMenu.class);
        intenty.putExtra("ALM", patch);
        startActivity(intenty);
    }

}
