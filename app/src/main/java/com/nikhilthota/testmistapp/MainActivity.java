package com.nikhilthota.testmistapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.sync.documentstore.DocumentStore;
import com.cloudant.sync.documentstore.DocumentStoreException;



import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Switch detterSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        detterSwitch = (Switch) findViewById(R.id.detter_enable);

        detterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("yee", "uddee");
            }
        });

        CloudantClient client = null;

        try {
            client = ClientBuilder.url(new URL("https://16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix.cloudant.com"))
                  .username("corigby2013@ufl.edu")
                  .password("SmartGarden2017!")
                  .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Database db = client.database("test", false);

        new ReadAsyncTask().execute("");
//        System.out.println("Server Version: " + db.find("command"));
    }

    class ReadAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... arg0) {
            System.out.println("doing stuff");
            String s = "";
            try {
                String id = arg0[0];
                // Create a new CloudantClient instance for account endpoint <ACCOUNT>.cloudant.com
                CloudantClient client = ClientBuilder.url(new URL("https://16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix.cloudant.com"))
                        .username("corigby2013@ufl.edu")
                        .password("SmartGarden2017!")
                        .build();
                // Get a Database instance to interact with. Do not create it if it doesn't already exist
                Database db = client.database("test", false);
                // Get an ExampleDocument out of the database and deserialize the JSON into a Java type
                s += db.find("command");
            } catch (Exception e){
                e.printStackTrace();
            }
            return s;
        }
//        @Override
//        protected void onPostExecute() {
//            super.onPostExecute(user);
//            if (user != null) {
//                responseEditText.setText("Read user from DB:\n"
//                        + "ID: " + user.getId() + "\n"
//                        + "FirstName: " + user.getFirstName() + "\n"
//                        + "LastName: " + user.getLastName() + "\n"
//                        + "Creation Date: " + user.getCreationDate().toString() + "\n"
//                        + "Age: " + user.getAge() + "\n"
//                );
//            }
//            else
//            {
//                responseEditText.setText("User not found");
//            }
//        }
    }
}
