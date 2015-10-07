package zyia.alarm.zyia.zyia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class VoiceLister extends FragmentActivity {

    SharedPreferences voiceList;
    private List<String> myList;
    File file;
    private int received;
    private String VoiceKey="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_lister);

        Intent receive = getIntent();

        received = receive.getIntExtra("VOC",0);

        if(received==1)
        {
            VoiceKey="VOICEONE";
        }

        if(received==2)
        {
            VoiceKey="VOICETWO";
        }
        if(received==3)
        {
            VoiceKey="VOICETHREE";
        }
        if(received==4)
        {
            VoiceKey="VOICEFOUR";
        }

        voiceList = getSharedPreferences("recordList", Context.MODE_PRIVATE);




        ListView listView = (ListView) findViewById(R.id.listY);
        myList = new ArrayList<String>();

        File directory = Environment.getExternalStorageDirectory();
        file = new File( directory + "/myAppCache2/" );
        File list[] = file.listFiles();

        for( int i=0; i< list.length; i++)
        {
            myList.add( list[i].getName() );
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public final void run()
                            {

                                SharedPreferences.Editor editor = voiceList.edit();
                                editor.putString(VoiceKey, item);
                                //editor.putString(Phone, ph);
                                //editor.putString(Email, e);
                                editor.commit();
                                finish();

                            }
                        });
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, myList);


        listView.setAdapter(adapter); //Set all the file in the list.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voice_lister, menu);
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
