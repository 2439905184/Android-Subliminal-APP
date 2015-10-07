package zyia.alarm.zyia.zyia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Settings extends FragmentActivity {

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        runThread();

        ImageButton Volume = (ImageButton)findViewById(R.id.volCam);
        Volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor();
            }
        });

        ImageView img = (ImageView)findViewById(R.id.logo3);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://zyia.me/"));
                startActivity(intent);
            }
        });


        ImageView top = (ImageView)findViewById(R.id.top_button3);
        top.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                launchDialog();

            }
        });

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
                                TextView msg = (TextView)findViewById(R.id.messages3);
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

    public void executor()
    {

        // Strings to Show In Dialog with Radio Buttons
        final CharSequence[] items = {" Snooze "," Dismiss "," none "};

        // Creating and Building the Dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Settings");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {




                switch(item)
                {
                    case 0:

                        Toast.makeText(getApplicationContext(), "Buttons set to Snooze",
                                Toast.LENGTH_LONG).show();

                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "Buttons set to Dismiss",
                                Toast.LENGTH_LONG).show();


                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "Buttons set to Default",
                                Toast.LENGTH_LONG).show();

                        break;


                }

                dialog.dismiss();

            }
        });

        AlertDialog levelDialog  = builder.create();
        levelDialog.show();

    }


    public void launchDialog(){

    }

    public void silence(View view){
        if (((ToggleButton) view).isChecked()) {

            Toast.makeText(getApplicationContext(), "ACTIVATED",
                    Toast.LENGTH_LONG).show();

        }
        else{

            Toast.makeText(getApplicationContext(), "DE-ACTIVATED",
                    Toast.LENGTH_LONG).show();

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
