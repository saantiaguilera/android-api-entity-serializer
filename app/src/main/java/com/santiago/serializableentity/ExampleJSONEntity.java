package com.santiago.serializableentity;

import com.santiago.entity.JSONEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by santiago on 19/03/16.
 */
public class ExampleJSONEntity extends JSONEntity {

    private static final String JSON_KEY_STRING1 = "string_1";
    private static final String JSON_KEY_STRING2 = "string_2";

    private String string1;
    private String string2;

    public ExampleJSONEntity() {
        super();
    }

    public ExampleJSONEntity(JSONEntity JSONEntity) {
        super(JSONEntity);
    }

    public ExampleJSONEntity(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }

    public ExampleJSONEntity(String json) throws JSONException {
        super(json);
    }

    /*---------------------------Overrides of mutables----------------------------------*/

    /**
     * By overriding this 3 when mutating it will also add the params of this class
     * and not only the super ones
     */

    @Override
    public void setValuesFrom(JSONEntity JSONEntity) {
        super.setValuesFrom(JSONEntity);

        ExampleJSONEntity object = (ExampleJSONEntity) JSONEntity;

        string1 = object.getString1();
        string2 = object.getString2();
    }

    @Override
    public ExampleJSONEntity setValuesFrom(JSONObject jsonObject) throws JSONException {
        super.setValuesFrom(jsonObject);

        string1 = jsonObject.getString(JSON_KEY_STRING1);
        string2 = jsonObject.getString(JSON_KEY_STRING2);

        return this;
    }

    @Override
    public void setDefaultValues() {
        super.setDefaultValues();

        string1 = "defaultval1";
        string2 = "defaultval2";
    }

    /*---------------------------------------JSON Serializer--------------------------------------*/

    /**
     * We must override this so our json also has the particularities of this class
     * @return
     */
    @Override
    public JSONObject asJSONObject() {
        try {
            JSONObject jobj =  super.asJSONObject();

            jobj.put(JSON_KEY_STRING1, string1);
            jobj.put(JSON_KEY_STRING2, string2);

            return jobj;
        } catch (JSONException e) { }

        return null;
    }

    /*----------------------------------Getters and Setters----------------------------*/

    public String getString2() {
        return string2;
    }

    public String getString1() {
        return string1;
    }
}
