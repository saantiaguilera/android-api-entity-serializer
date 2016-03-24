package com.santiago.entity.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Interface for serializer and inflator of JSON
 * Created by santiago on 19/03/16.
 */
public interface JSONSerializer<T> {

    public JSONObject asJSONObject();
    public JSONArray listAsJSONArray(List<T> list);
    public T setValuesFrom(JSONObject jobj) throws JSONException;
    public List<T> listFromJSONArray(JSONArray jarr) throws JSONException;

}
