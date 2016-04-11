package com.santiago.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.santiago.example.entity.ExampleEntity;
import com.santiago.example.serializer.ExampleSerializer;
import com.santiago.shared_preferences.JSONSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * I show an example with the sharedprefs, but know that you can do this practically with anything
 * since I just serialize them to Strings.
 *
 * This means you can do this with intents or whatever.
 *
 * Created by santiago on 19/03/16.
 */
public class ExampleActivity extends Activity {

    private static final String DOCUMENT_KEY_EXAMPLE = "com.santiago.DOCUMENT_KEY_EXAMPLE";
    private static final String ENTITY_KEY_EXAMPLE = "com.santiago.ENTITY_KEY_EXAMPLE";

    private JSONSharedPreferences sharedPreferences;
    private ExampleEntity entity;
    private ExampleSerializer serializer;

    private TextView initialContainer;
    private TextView button;
    private TextView finalContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.example_activity);

        initialContainer = (TextView) findViewById(R.id.example_activity_text1);
        button = (TextView) findViewById(R.id.example_activity_button);
        finalContainer = (TextView) findViewById(R.id.example_activity_text2);

        entity = new ExampleEntity();
        serializer = new ExampleSerializer();
        sharedPreferences = new JSONSharedPreferences(this);

        initialContainer.setText(entity.getString1() + " - " + entity.getString2());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }

    private void onButtonClick() {
        List<ExampleEntity> entities = new ArrayList<>();
        entities.add(entity);

        sharedPreferences.openDocument(DOCUMENT_KEY_EXAMPLE);

        sharedPreferences.startEditing()
                .put(ENTITY_KEY_EXAMPLE, entities, serializer)
                .commit();

        try {
            List<ExampleEntity> newEntities = sharedPreferences.get(ENTITY_KEY_EXAMPLE,
                    (JSONSharedPreferences.JSONSharedPreferencesListHidrater<ExampleEntity>) serializer);
            finalContainer.setText(newEntities.get(0).getString1() + " - Reached the other side - " + newEntities.get(0).getString2());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*---------------------------------------------Example with intents-----------------------------------------*/

    private void createAnIntentWithMyEntity() {
        Intent intent = new Intent();
        intent.putExtra(ENTITY_KEY_EXAMPLE, serializer.serialize(entity).toString());
    }

    private void createAnEntityWithMyIntent() {
        try {
            Intent intent = getIntent();
            entity = serializer.hidrate(new JSONObject(intent.getStringExtra(ENTITY_KEY_EXAMPLE)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
