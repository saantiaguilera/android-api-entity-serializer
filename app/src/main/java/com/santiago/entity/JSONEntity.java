package com.santiago.entity;

import com.santiago.json.JSONSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity capable of inflating and serializing from JSONs
 *
 * Created by santiago on 19/03/16.
 */
public class JSONEntity implements JSONSerializer<JSONEntity> {

    /*-------------------------------------------------JSON Fields References-------------------------------------------------*/

    public static final String ID_JSON = "id";

	/*-------------------------------------------------Fields-----------------------------------------------------------------*/

    private long id;

	/*------------------------------------------------Constructors------------------------------------------------------------*/

    public JSONEntity() {
        setDefaultValues();
    }

    public JSONEntity(JSONEntity JSONEntity) {
        setValuesFrom(JSONEntity);
    }

    public JSONEntity(JSONObject jsonObject) throws JSONException {
        setValuesFrom(jsonObject);
    }

    public JSONEntity(String json) throws JSONException {
        this(new JSONObject(json));
    }

	/*------------------------------------------------Getters------------------------------------------------------------*/

    public long getId() {
        return id;
    }

	/*------------------------------------------------Setters------------------------------------------------------------*/

    public void setId(long id) {
        this.id = id;
    }

    public void setValuesFrom(JSONEntity JSONEntity){
        if(JSONEntity != null)
            setId(JSONEntity.getId());
        else setDefaultValues();
    }

    public JSONEntity setValuesFrom(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null)
            throw new JSONException("JSONObject cannot be null");

        setId(jsonObject.optLong(ID_JSON));

        return this;
    }

    public void setDefaultValues(){
        setId(0);
    }

	/*----------------------------------------------------Methods---------------------------------------------------------*/

    @Override
    public String toString() {
        return asJson();
    }

    public String asJson(){
        return asJSONObject().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof JSONEntity))
            return false;

        if (((JSONEntity) obj).getId() == getId())
            return true;
        else return false;

    }

    /*---------------------------------------------------JSON Serializer--------------------------------------------------------*/

    @Override
    public JSONObject asJSONObject(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(ID_JSON, getId());
        } catch(JSONException e){}

        return jsonObject;
    }

    @Override
    public JSONArray listAsJSONArray(List<JSONEntity> list) {
        if(list == null)
            throw new NullPointerException("list cannot be null in listAsJSONArray inside class " + getClass().getSimpleName());

        JSONArray result = new JSONArray();

        for(JSONEntity JSONEntity : list)
            result.put(JSONEntity.asJSONObject());

        return result;
    }

    @Override
    public List<JSONEntity> listFromJSONArray(JSONArray jarr) throws JSONException {
        if (jarr == null)
            throw new NullPointerException("JSONArray cannot be null");

        List<JSONEntity> result = new ArrayList<>();

        for (int i = 0; i < jarr.length(); i++) {
            try {
                result.add(new JSONEntity(jarr.getJSONObject(i)));
            } catch (JSONException e) { }
        }

        return result;

    }

}
