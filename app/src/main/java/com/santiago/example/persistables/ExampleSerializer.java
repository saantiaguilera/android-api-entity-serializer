package com.santiago.example.persistables;

import android.support.annotation.NonNull;

import com.santiago.example.entity.ExampleEntity;
import com.santiago.persistables.serializer.BaseJSONSerializer;
import com.santiago.shared_preferences.JSONSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by santiago on 10/04/16.
 */
public class ExampleSerializer extends BaseJSONSerializer<ExampleEntity> implements JSONSharedPreferences.JSONSharedPreferencesSerializer<ExampleEntity>,
JSONSharedPreferences.JSONSharedPreferencesListSerializer<ExampleEntity> {

    private static final String JSON_KEY_STRING1 = "string_1";
    private static final String JSON_KEY_STRING2 = "string_2";

    @Override
    public @NonNull JSONObject serialize(@NonNull ExampleEntity exampleEntity) {
        JSONObject jobj = new JSONObject();

        try {
            jobj.put(JSON_KEY_STRING1, exampleEntity.getString1());
            jobj.put(JSON_KEY_STRING2, exampleEntity.getString2());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jobj;
    }

    /*----------------------This below are just for the Shared Prefs example----------------------*/

    @Override
    public String serializeFromSP(ExampleEntity entity) {
        return serialize(entity).toString();
    }

    @Override
    public String serializeListFromSP(List<ExampleEntity> tList) {
        return serialize(tList).toString();
    }

}
