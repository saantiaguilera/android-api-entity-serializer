package com.santiago.persistables.serializer;

import com.santiago.entity.BaseEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by santiago on 10/04/16.
 */
public abstract class BaseJSONSerializer<T extends BaseEntity> {

    /**
     * Serializes the information that T has
     *
     * @param t entity to be serialized to JSON
     * @return a JSONObject that holds the information of T
     */
    public abstract JSONObject serialize(T t);

    /**
     * Creates a JSONArray from a list of entities T.
     * By default it creates a JSONArray that holds all the entities
     * in its root. Override if JSONArray needs to have a different
     * structure.
     *
     * @param tList list that holds all the entities to be serialized
     * @return JSONArray with the list serialized
     */
    public JSONArray serialize(List<T> tList) {
        JSONArray jarr = new JSONArray();

        for (T t : tList)
            jarr.put(serialize(t));

        return jarr;
    }

}
