package com.santiago.persistables.parser;

import android.support.annotation.NonNull;

import com.santiago.entity.BaseEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santiago on 13/05/16.
 */
public abstract class BaseJSONParser<T extends BaseEntity> {

    /**
     * Create an instance of the entity T already inflated
     * with the data that holds the JSONObject
     *
     * @param jobj JSONObject that holds the information of the entity T
     * @return instance of T
     */
    public abstract @NonNull T parse(@NonNull JSONObject jobj);

    /**
     * By default it considers a JSONArray that holds all the T objects
     * in its root. Override if JSONArray has a different flow.
     *
     * @param jarr JSONArray that holds the information of a list of entities T
     * @return A list of instances of T
     */
    public @NonNull List<T> parse(@NonNull JSONArray jarr) {
        List<T> list = new ArrayList<>();

        try {
            for (int i = 0 ; i < jarr.length() ; ++i)
                list.add(parse(jarr.getJSONObject(i)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

}
