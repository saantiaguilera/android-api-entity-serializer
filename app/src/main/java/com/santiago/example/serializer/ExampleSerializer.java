package com.santiago.example.serializer;

import com.santiago.example.entity.ExampleEntity;
import com.santiago.serializer.BaseJSONSerializer;
import com.santiago.shared_preferences.JSONSharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by santiago on 10/04/16.
 */
public class ExampleSerializer extends BaseJSONSerializer<ExampleEntity> implements JSONSharedPreferences.JSONSharedPreferencesSerializer<ExampleEntity>,                  JSONSharedPreferences.JSONSharedPreferencesHidrater<ExampleEntity>,
        JSONSharedPreferences.JSONSharedPreferencesListHidrater<ExampleEntity>,
        JSONSharedPreferences.JSONSharedPreferencesListSerializer<ExampleEntity> {
    private static final String JSON_KEY_STRING1 = "string_1";
    private static final String JSON_KEY_STRING2 = "string_2";

    @Override
    public ExampleEntity hidrate(JSONObject jobj) {
        ExampleEntity entity = new ExampleEntity();

        try {
            entity.setString1(jobj.getString(JSON_KEY_STRING1));
            entity.setString2(jobj.getString(JSON_KEY_STRING2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public JSONObject serialize(ExampleEntity exampleEntity) {
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
    public ExampleEntity hidrateFromSP(String string) {
        try {
            return hidrate(new JSONObject(string));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String serializeFromSP(ExampleEntity entity) {
        return serialize(entity).toString();
    }

    @Override
    public List<ExampleEntity> hidrateListFromSP(String string) {
        try {
            return hidrate(new JSONArray(string));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String serializeListFromSP(List<ExampleEntity> tList) {
        return serialize(tList).toString();
    }

}
