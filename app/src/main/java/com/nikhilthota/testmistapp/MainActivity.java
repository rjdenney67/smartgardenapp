package com.nikhilthota.testmistapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.cloudant.sync.documentstore.DocumentBodyFactory;
import com.cloudant.sync.documentstore.DocumentRevision;
import com.cloudant.sync.documentstore.DocumentStore;
import com.cloudant.sync.documentstore.DocumentStoreException;
import com.cloudant.sync.documentstore.UnsavedFileAttachment;



import java.io.File;
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
                Log.d("yee", "uee");
            }
        });

        File path = getApplicationContext().getDir("documentstores", Context.MODE_PRIVATE);

        try {
            // Obtain reference to DocumentStore instance, creating it if doesn't exist
            DocumentStore ds = DocumentStore.getInstance(new File(path, "my_document_store"));


//            // Create a document
//            DocumentRevision revision = new DocumentRevision();
//            Map<String, Object> body = new HashMap<String, Object>();
//            body.put("animal", "cat");
//            revision.setBody(DocumentBodyFactory.create(body));
//            DocumentRevision saved = ds.database().create(revision);
//
//            // Add an attachment -- binary data like a JPEG
//            UnsavedFileAttachment att1 =
//                    new UnsavedFileAttachment(new File("/path/to/image.jpg"), "image/jpeg");
//            saved.getAttachments().put("image.jpg", att1);
//            DocumentRevision updated = ds.database().update(saved);
//
//            // Read a document
//            DocumentRevision aRevision = ds.database().read(updated.getId());
        } catch (DocumentStoreException dse) {
            System.err.println("Problem opening or accessing DocumentStore: "+dse);
        }
    }


}
