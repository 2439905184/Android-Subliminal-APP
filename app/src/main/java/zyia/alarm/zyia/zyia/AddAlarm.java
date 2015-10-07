package zyia.alarm.zyia.zyia;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class AddAlarm extends FragmentActivity {


    int i=10,pen=0;
    int tempx,pass;
    Calendar calendar;

    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        calendar = Calendar.getInstance();


        String parser="";
        Intent receive = getIntent();

        pen = receive.getIntExtra("pendInt",0);
        tempx = receive.getIntExtra("tts",0);
        pass=tempx;

        switch (tempx)
        {
            case 1:
                parser="ID1";
                break;
            case 2:
                parser="ID2";
                break;
            case 3:
                parser="ID3";
                break;
            case 4:
                parser="ID4";
                break;
        }

        SharedPreferences sx = getSharedPreferences("TimesList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sx.edit();
        editor.putInt(parser, pen);
        editor.commit();







        runThread();

        Button Sample = (Button)findViewById(R.id.samplex);
        Sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                finish();


            }


        });









        ImageView top = (ImageView)findViewById(R.id.top_button2);
        top.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startHelp();

            }
        });



        ImageView img = (ImageView)findViewById(R.id.logo2);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://zyia.me/"));
                startActivity(intent);
            }
        });

        ImageButton snoozeButton = (ImageButton)findViewById(R.id.snooze);
        snoozeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snoozer();

            }
        });




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
                                TextView msg = (TextView)findViewById(R.id.messages2);
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


    private void startHelp() {
        DialogFragment newFragment = new DialogTwo();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

    public void layThree(View view){

        startActivity(new Intent(AddAlarm.this, Settings.class));
        finish();
    }

    public void convertText(View view)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("TEXT TO SPEECH");




        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setSpeechRate(0.5f);
                }
            }
        });




      final   EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);


        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                    File directory = new File(Environment.getExternalStorageDirectory()+"/myAppCache/");
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                HashMap<String, String> myHashRender = new HashMap();
                String toSpeak = input.getText().toString();
                t1.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);


                if(tempx==1)
                {
                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, toSpeak);
                    t1.synthesizeToFile(toSpeak, myHashRender, destFileName);

                }
                else if(tempx==2)
                {
                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp2.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, toSpeak);
                    t1.synthesizeToFile(toSpeak, myHashRender, destFileName);

                }
                else if(tempx==3)
                {
                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp3.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, toSpeak);
                    t1.synthesizeToFile(toSpeak, myHashRender, destFileName);

                }
                else if(tempx==4)
                {
                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp4.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, toSpeak);
                    t1.synthesizeToFile(toSpeak, myHashRender, destFileName);

                }








            }
        });


        builder.show();
    }

    public void calendarAlarm(View view)
    {
        int mon = calendar.get(Calendar.MONTH);
        int yr = calendar.get(Calendar.YEAR);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog date = new DatePickerDialog(AddAlarm.this,new DatePickerDialog.OnDateSetListener()
        {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
            {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            }

            }, yr,mon,dd);
        date.show();


    }


    public void clicker (View view)
    {

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddAlarm.this,new TimePickerDialog.OnTimeSetListener()
        {
                    @Override
                    public void onTimeSet(TimePicker timePicker,
                                          int selectedHour, int selectedMinute)
                    {


                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);

                        int c_hour,c_min;
                        String format;
                        c_hour=selectedHour;
                        c_min=selectedMinute;

                        if (c_hour == 0) {
                            c_hour += 12;
                            format = "AM";
                        } else if (c_hour == 12) {
                            format = "PM";
                        } else if (c_hour > 12) {
                            c_hour -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }

                        String formatTime = String.format("%02d : %02d %s", c_hour, c_min, format);

                        TextView dimple = (TextView)findViewById(R.id.timeText);
                        dimple.setText(formatTime);
                        setAlarm(calendar);

                    }

        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();



    }

    private void setAlarm(Calendar targetCal)
    {
        Intent alarmintent = new Intent(AddAlarm.this, AlarmReceiver.class);
        alarmintent.putExtra("ALARM_NO", tempx);
        alarmintent.setAction("my.action.string");
        PendingIntent sender = PendingIntent.getBroadcast(AddAlarm.this, pen, alarmintent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), sender);

        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                //AlarmManager.INTERVAL_DAY, sender);


    }

    public void recordActivity(View view)
    {
        Intent intent = new Intent(AddAlarm.this, VoiceRecord.class);



        intent.putExtra("recordX",pass);
        startActivity(intent);



    }

    public void recMessages(View view){


        Intent intent = new Intent(AddAlarm.this, VoiceLister.class);

        File directory = new File(Environment.getExternalStorageDirectory()+"/myAppCache2/");
        directory.mkdirs();
        intent.putExtra("VOC",tempx);

        startActivity(intent);




    }

    public void texMessages(View view){

        Intent intent = new Intent(AddAlarm.this, QuoteMenu.class);

        intent.putExtra("ALM",tempx);

        startActivity(intent);

    }

    public void vibrator(View view){
        if (((ToggleButton) view).isChecked()) {

            Toast.makeText(getApplicationContext(), "Vibration ON",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AddAlarm.this, Time_Date.class);
            intent.putExtra("vibrator",1);

        }

        else{
            Toast.makeText(getApplicationContext(), "Vibration OFF",
                    Toast.LENGTH_SHORT).show();

        }
    }


    public void mediaClick(View view){

        Intent intentx = new Intent(AddAlarm.this, MusicActivity.class);

        Toast.makeText(getApplicationContext(), "Num:"+tempx,
        Toast.LENGTH_SHORT).show();
        intentx.putExtra("MUS",tempx);
        startActivity(intentx);




    }

    public void snoozer(){

        final CharSequence[] items = {" 2 min "," 3 min "," 5 min "," 10 min "};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Snooze Options");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                SharedPreferences sno = getSharedPreferences("SnoozeList", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sno.edit();





                switch(item)
                {
                    case 0:

                        Toast.makeText(getApplicationContext(), "Snooze after 2 Min",
                                Toast.LENGTH_LONG).show();
                        editor.putInt("SnoozeX", 2);
                        editor.commit();

                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "Snooze after 3 Min",
                                Toast.LENGTH_LONG).show();
                        editor.putInt("SnoozeX", 3);
                        editor.commit();


                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "Snooze after 5 Min",
                                Toast.LENGTH_LONG).show();
                        editor.putInt("SnoozeX", 5);
                        editor.commit();

                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "Snooze after 10 Min",
                                Toast.LENGTH_LONG).show();
                        editor.putInt("SnoozeX", 10);
                        editor.commit();

                        break;

                }

                dialog.dismiss();

            }
        });

         AlertDialog levelDialog  = builder.create();
        levelDialog.show();


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
