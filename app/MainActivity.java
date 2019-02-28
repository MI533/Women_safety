package com.example.Mi5.womensafety;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    GPSTracker gps;
    MediaPlayer mediaPlayer;
    MyDBHandler dbHandler;
Button e;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.app_name);
e=(Button) findViewById(R.id.button8);
       e.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              finish();
               Toast.makeText(MainActivity.this, "Thank you for using our app", Toast.LENGTH_SHORT).show();
           }
       });
        mediaPlayer = MediaPlayer.create(this,R.raw.siren);
        dbHandler = new MyDBHandler(this,null,null,1);

    }

    public void playMusic(View v)
    {
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }

    public void sendHelp(View v) {
        String msg = "I AM IN DANGER!";
        String contacts = dbHandler.getData();
        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {
String d;
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
msg=msg.concat(" ");
            msg=msg.concat("Latitude:");
d=Double.toString(latitude);
            msg=msg.concat(d);
            msg=msg.concat(", ");
            msg=msg.concat("Longitude:");
            d=Double.toString(longitude);
            msg=msg.concat(d);

            // \n is for new line

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();}
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + contacts));
            smsIntent.putExtra("sms_body", msg);
            startActivity(smsIntent);

        }


    public void safeClicked(View v)
    {
        String msg = "I AM SAFE!";
        String contacts = dbHandler.getData();

        Intent smsIntent=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+contacts));
        smsIntent.putExtra("sms_body",msg);
        startActivity(smsIntent);
    }

    public void nextActivity(View v)
    {
        Intent i =new Intent(this,Activity2.class);
        startActivity(i);
    }
}
