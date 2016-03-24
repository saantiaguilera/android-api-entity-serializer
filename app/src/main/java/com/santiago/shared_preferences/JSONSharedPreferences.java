package com.santiago.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.santiago.entity.json.JSONSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Custom shared prefs that interact easier and features json processing
 *
 * 
 * @note Just as this works, you can simply do it for passing jsons in intents or any place you like
 * @note TODO Refactor. Class should work in a bg thread
 *
 * Created by santiago on 19/03/16.
 */
public class JSONSharedPreferences {

    private Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public JSONSharedPreferences(Context context) {
        this.context = context;
    }

    /*-------------------------------------------Access Methods----------------------------------------*/

    public void openDocument(String key) {
        sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    private void validateSharedPreferences() {
        if(sharedPreferences==null)
            throw new NullPointerException("SharedPreferences==null. Forgot to openDocument() ?");
    }

    private void validateEditor() {
        if (sharedPreferences == null)
            throw new NullPointerException("Editor==null. Forgot to startEditing() ?");
    }

    /*----------------------------------------------Getters--------------------------------------------*/

    /**
     * Common methods the shared preferences has with the primitives, nothing savage
     */

    public int getInteger(String key, int defValue) {
        validateSharedPreferences();

        return sharedPreferences.getInt(key, defValue);
    }

    public String getString(String key, String defValue) {
        validateSharedPreferences();

        return sharedPreferences.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        validateSharedPreferences();

        return sharedPreferences.getBoolean(key, defValue);
    }

    public long getLong(String key, long defValue) {
        validateSharedPreferences();

        return sharedPreferences.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        validateSharedPreferences();

        return sharedPreferences.getFloat(key, defValue);
    }

    /**
     * Generic one for just getting whatever
     * @param key
     * @return
     */
    public Object get(String key) {
        validateSharedPreferences();

        Map<String, ?> objects = sharedPreferences.getAll();

        for(Map.Entry entry : objects.entrySet()) {
            if(entry.getKey() == key)
                return entry.getValue();
        }

        return null;
    }

    /**
     * getter of an object that can be JSONified
     * @throws JSONException
     */
    public <T extends JSONSerializer> Object get(String key, T t) throws JSONException {
        validateSharedPreferences();

        String jsonString = sharedPreferences.getString(key, null);

        if(jsonString == null)
            return null;

        return t.setValuesFrom(new JSONObject(jsonString));
    }

    /**
     * getter of a list of objects that can be JSONified
     * @throws JSONException
     */
    public <T extends JSONSerializer> Object getList(String key, T t) throws JSONException {
        validateSharedPreferences();

        String jsonString = sharedPreferences.getString(key, null);

        if(jsonString == null)
            return null;

        return t.listFromJSONArray(new JSONArray(jsonString));
    }

    /*----------------------------------------------Setters-------------------------------------------*/

    /**
     * Works similar to a builder but inside the JSONSP
     * flow:
     * 1. start editing
     * 2. put / remove whatever you need to
     * 3. commit
     * 4. start editing again if needed
     *
     * @throws com.santiago.shared_preferences.JSONSharedPreferences.JSONSharedPreferencesEditorException if trying to edit twice without discarding or commiting.
     */
    public JSONSharedPreferences startEditing() {
        if (editor != null)
            throw new JSONSharedPreferencesEditorException("Editor session active, please discard or commit changes before starting a new one");

        validateSharedPreferences();

        editor = sharedPreferences.edit();
        return this;
    }

    /**
     * Puts in the same way of the Editor class, primitive types.
     */

    public JSONSharedPreferences put(String key, int value) {
        validateEditor();

        editor.putInt(key, value);
        return this;
    }

    public JSONSharedPreferences put(String key, long value) {
        validateEditor();

        editor.putLong(key, value);
        return this;
    }

    public JSONSharedPreferences put(String key, float value) {
        validateEditor();

        editor.putFloat(key, value);
        return this;
    }

    public JSONSharedPreferences put(String key, boolean value) {
        validateEditor();

        editor.putBoolean(key, value);
        return this;
    }

    public JSONSharedPreferences put(String key, String value) {
        validateEditor();

        editor.putString(key, value);
        return this;
    }

    /**
     * Method for serializing an object inside the SP. Object must implement JSONSerializer
     */
    public <T extends JSONSerializer> JSONSharedPreferences put(String key, T value) {
        validateEditor();

        editor.putString(key, value.asJSONObject().toString());
        return this;
    }

    /**
     * Method for serializing a list of object inside the SP. Objects must implement JSONSerializer
     */
    public <T extends JSONSerializer> JSONSharedPreferences put(String key, List<T> values) {
        validateEditor();

        if(!values.isEmpty()) //TODO WORKAROUND, REFACTOR.
            editor.putString(key, values.get(0).listAsJSONArray(values).toString());

        return this;
    }

    /**
     * Remove a key
     */
    public JSONSharedPreferences remove(String key) {
        validateEditor();

        editor.remove(key);
        return this;
    }

    /**
     * Commit the changes
     * @Synchronous
     */
    public void commit() {
        validateEditor();

        editor.commit();
        discard();
    }

    /**
     * Discard changes
     */
    public void discard() {
        editor = null;
    }

    /*------------------------------------Exceptions-------------------------------------------------*/

    public class JSONSharedPreferencesEditorException extends RuntimeException {

        public JSONSharedPreferencesEditorException(String message) {
            super(message);
        }

    }

}
