package zyia.alarm.zyia.zyia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.IOException;


public class VoiceRecord extends FragmentActivity {

    private MediaRecorder myRecorder;
    private MediaPlayer myPlayer;
    private String outputFile = null;
    private ImageButton startBtn;
    private ImageButton stopBtn;
    private ToggleButton playBtn;
    int blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_record);

        Intent receive = getIntent();

        blue = receive.getIntExtra("recordX",0);

        File directory = new File(Environment.getExternalStorageDirectory()+"/myAppCache2/");
        directory.mkdirs();
        myRecorder = new MediaRecorder();
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);




        if(blue==1)
        {
            outputFile = Environment.getExternalStorageDirectory() + "/myAppCache2/rec1.3gp";
        }
        if(blue==2)
        {
            outputFile = Environment.getExternalStorageDirectory() + "/myAppCache2/rec2.3gp";
        }
        if(blue==3)
        {
            outputFile = Environment.getExternalStorageDirectory() + "/myAppCache2/rec3.3gp";
        }
        if(blue==4)
        {
            outputFile = Environment.getExternalStorageDirectory() + "/myAppCache2/rec4.3gp";
        }
            myRecorder.setOutputFile(outputFile);

        startBtn =(ImageButton)findViewById(R.id.recordPlus);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
                stopBtn.setVisibility(View.VISIBLE);
                startBtn.setVisibility(View.INVISIBLE);
            }
        });

        stopBtn = (ImageButton)findViewById(R.id.recordStop);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop(v);

                stopBtn.setVisibility(View.INVISIBLE);

                playBtn.setVisibility(View.VISIBLE);


            }
        });

        playBtn = (ToggleButton)findViewById(R.id.playButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if (((ToggleButton) v).isChecked())
                    {
                        try{
                            myPlayer = new MediaPlayer();
                            myPlayer.setDataSource(outputFile);
                            myPlayer.prepare();
                            myPlayer.start();
                            Toast.makeText(getApplicationContext(), "Playing",
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();

                        }



                    }

                    else
                    {
                        try {

                            if (myPlayer != null)
                            {
                               myPlayer.stop();
                               myPlayer.release();
                               myPlayer = null;
                               playBtn.setEnabled(true);

                            }

                        } catch (Exception e)
                        {
                           // TODO Auto-generated catch block
                            e.printStackTrace();

                        }


                    }
                }

        });





    }



    public void closeAct(View view)
    {
        finish();
    }

    public void start(View view){

        try
        {

            myRecorder.prepare();
            myRecorder.start();

        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Recording...",
        Toast.LENGTH_SHORT).show();

    }


    public void stop(View view){

        try {

            myRecorder.stop();
            myRecorder.release();
            myRecorder  = null;
            Toast.makeText(getApplicationContext(), "Saving...",
                    Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void play(View view)
    {

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
