package com.nikhilthota.testmistapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Switch deterrSwitch;
    Switch lightsSwitch;
    Switch soundSwitch;
    SeekBar seekBar;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deterrSwitch = (Switch) findViewById(R.id.detter_enable);
        lightsSwitch = (Switch) findViewById(R.id.light_enable);
        soundSwitch = (Switch) findViewById(R.id.sound_enable);
        saveButton = (Button) findViewById(R.id.save_button);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        deterrSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("yee", "uddee");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deterrVal, lightsVal, soundVal;
                Boolean deterrBool = deterrSwitch.isChecked();
                if(deterrBool == false){
                    deterrVal = 0;
                }
                else{
                    deterrVal = 1;
                }
                Boolean lightsBool = lightsSwitch.isChecked();
                if(lightsBool == false){
                    lightsVal = 0;
                }
                else{
                    lightsVal = 1;
                }
                Boolean soundBool = soundSwitch.isChecked();
                if(soundBool == false){
                    soundVal = 0;
                }
                else{
                    soundVal = 1;
                }
                int volumeVal = seekBar.getProgress();


                new WriteAsyncTask().execute(volumeVal, deterrVal, soundVal, lightsVal);
            }
        });
    }

    class WriteAsyncTask extends AsyncTask<Integer, Void, Command>
    {
        @Override
        protected Command doInBackground(Integer... arg0) {
            Command command = null;
            try {
                CloudantClient client = ClientBuilder.url(new URL("https://16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix.cloudant.com"))
                    .username("16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix")
                    .password("b6c560599d6bfd7e205d0942a1a67ccdba3c11dd6d86029aa315d14cd068216a")
                    .build();
                Database db = client.database("test", false);
                command = db.find(Command.class, "command");
                command.a_volume = arg0[0];
                command.deterrence = arg0[1];
                command.sound = arg0[2];
                command.lights = arg0[3];
                db.update(command);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return command;
        }
        @Override
        protected void onPostExecute(Command command) {
            super.onPostExecute(command);
        }
    }

    class ReadAsyncTask extends AsyncTask<String, Void, Command>
    {
        @Override
        protected Command doInBackground(String... arg0) {
            System.out.println("doing stuff");
            Command command = null;
            String s = "";
            try {
                // Create a new CloudantClient instance for account endpoint <ACCOUNT>.cloudant.com
                CloudantClient client = ClientBuilder.url(new URL("https://16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix:b6c560599d6bfd7e205d0942a1a67ccdba3c11dd6d86029aa315d14cd068216a@16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix.cloudant.com"))
                        .username("16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix")
                        .password("b6c560599d6bfd7e205d0942a1a67ccdba3c11dd6d86029aa315d14cd068216a")
                        .build();
                // Get a Database instance to interact with. Do not create it if it doesn't already exist
                Database db = client.database("test", false);
                // Get an ExampleDocument out of the database and deserialize the JSON into a Java type
                command = db.find(Command.class, "command");
            } catch (Exception e){
                e.printStackTrace();
            }
            return command;
        }
        @Override
        protected void onPostExecute(Command command) {
            super.onPostExecute(command);
            if (command != null) {
                System.out.println("Read command from DB:\n"
                        + "ID: " + command._id + "\n"
                        + "Rev: " + command._rev + "\n"
                        + "Volume: " + command.a_volume + "\n"
                        + "Lights: " + command.lights + "\n"
                        + "Deterrence: " + command.deterrence + "\n"
                        + "Sound: " + command.sound + "\n"
                );
            }
        }
    }
}
