package zyia.alarm.zyia.zyia;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class Alarm2Receiver extends WakefulBroadcastReceiver {

    int state;

    @Override
    public void onReceive(final Context context, Intent intent) {
        int vibrator = intent.getIntExtra("vibrator", 1);

        String action = intent.getAction();

        if(action.equals("my.action.string"))
        {
            state = intent.getIntExtra("ALARM_NO",0);

        }

        Log.i("Receiver", "Broadcast received: " + action);

        if(state==1)
        {
            Intent myIntent = new Intent(context, EasyAlarm.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        }


        //display that alarm is ringing
        Toast.makeText(context, "Alarm Ringing...!!!", Toast.LENGTH_LONG).show();

        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);




    }


}