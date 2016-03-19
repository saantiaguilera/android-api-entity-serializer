package com.santiago.serializableentity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.santiago.shared_preferences.JSONSharedPreferences;

import org.json.JSONException;

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
    private ExampleJSONEntity entity;

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

        entity = new ExampleJSONEntity();
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
        sharedPreferences.openDocument(DOCUMENT_KEY_EXAMPLE);

        sharedPreferences.startEditing()
                .put(ENTITY_KEY_EXAMPLE, entity)
                .commit();

        try {
            ExampleJSONEntity newEntity = (ExampleJSONEntity) sharedPreferences.get(ENTITY_KEY_EXAMPLE, entity);
            finalContainer.setText(newEntity.getString1() + " - Reached the other side - " + newEntity.getString2());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*---------------------------------------------Example with intents-----------------------------------------*/

    private void createAnIntentWithMyEntity() {
        Intent intent = new Intent();
        intent.putExtra(ENTITY_KEY_EXAMPLE, entity.asJSONObject().toString());
    }

    private void createAnEntityWithMyIntent() {
        try {
            Intent intent = getIntent();
            entity = new ExampleJSONEntity(intent.getStringExtra(ENTITY_KEY_EXAMPLE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
