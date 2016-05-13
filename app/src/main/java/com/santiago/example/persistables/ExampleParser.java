package com.santiago.example.persistables;

import android.support.annotation.NonNull;

import com.santiago.example.entity.ExampleEntity;
import com.santiago.persistables.parser.BaseJSONParser;
import com.santiago.shared_preferences.JSONSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by santiago on 13/05/16.
 */
public class ExampleParser extends BaseJSONParser<ExampleEntity> implements JSONSharedPreferences.JSONSharedPreferencesParser<ExampleEntity>,
        JSONSharedPreferences.JSONSharedPreferencesListParser<ExampleEntity> {

    private static final String JSON_KEY_STRING1 = "string_1";
    private static final String JSON_KEY_STRING2 = "string_2";

    @Override
    public @NonNull ExampleEntity parse(@NonNull JSONObject jobj) {
        ExampleEntity entity = new ExampleEntity();

        try {
            entity.setString1(jobj.getString(JSON_KEY_STRING1));
            entity.setString2(jobj.getString(JSON_KEY_STRING2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entity;
    }

    /*-----------------------This below are just for the Shared Prefs example----------------------*/

    @Override
    public ExampleEntity parseFromSP(String string) {
        try {
            return parse(new JSONObject(string));
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public List<ExampleEntity> parseListFromSP(String string) {
        try {
            return parse(new JSONArray(string));
        } catch (JSONException e) {
            return null;
        }
    }

}
