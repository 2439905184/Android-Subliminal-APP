package zyia.alarm.zyia.zyia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;


public class Quotes2 extends FragmentActivity {

    int tempx;
    TextToSpeech t1;
    SharedPreferences sp;
    String RecKey="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes2);
        sp = getSharedPreferences("TextList", Context.MODE_PRIVATE);

        Intent receive = getIntent();

        tempx = receive.getIntExtra("ALM",0);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.setSpeechRate(0.5f);
                }
            }
        });

        ListView quoteOne = (ListView)findViewById(R.id.quoteX2);

        String[] backup = getResources().getStringArray(R.array.quote2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, backup);

        quoteOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String)parent.getItemAtPosition(position);

                HashMap<String, String> myHashRender = new HashMap();

                t1.speak(selectedItem, TextToSpeech.QUEUE_ADD, null);

                if(tempx==1)
                {
                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, selectedItem);
                    t1.synthesizeToFile(selectedItem, myHashRender, destFileName);


                    RecKey="ALARMONE";
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(RecKey, "wakeUp.wav");
                    editor.commit();



                }
                else if(tempx==2)
                {

                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp2.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, selectedItem);
                    t1.synthesizeToFile(selectedItem, myHashRender, destFileName);

                    RecKey="ALARMTWO";
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(RecKey, "wakeUp2.wav");
                    editor.commit();

                }
                else if(tempx==3)
                {

                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp3.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, selectedItem);
                    t1.synthesizeToFile(selectedItem, myHashRender, destFileName);

                    RecKey="ALARMTHREE";
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(RecKey, "wakeUp3.wav");
                    editor.commit();

                }
                else if(tempx==4)
                {

                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/wakeUp4.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, selectedItem);
                    t1.synthesizeToFile(selectedItem, myHashRender, destFileName);

                    RecKey="ALARMFOUR";
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(RecKey, "wakeUp4.wav");
                    editor.commit();

                }

                else if(tempx==9)
                {
                    String destFileName = Environment.getExternalStorageDirectory()+"/myAppCache/EasyText.wav";
                    myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, selectedItem);
                    t1.synthesizeToFile(selectedItem, myHashRender, destFileName);

                    RecKey="EASYTEXT";
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(RecKey, "EasyText.wav");
                    editor.commit();

                }

                else
                {
                    finish();
                }




                finish();
            }
        });


        quoteOne.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quotes2, menu);
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
