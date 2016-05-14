Serializer/Parser & Entity


---------------------------------------------------------
Entity (or Model)
---------------------------------------------------------

Just extend your model class from BaseEntity

```Java
public class ExampleEntity extends BaseEntity {

    private String string1;
    private String string2;

    public ExampleEntity() {
        super();
    }

    public ExampleEntity(ExampleEntity Entity) {
        super(Entity);
    }

    /*---------------------------Overrides of mutables----------------------------------*/

    public void setValuesFrom(ExampleEntity Entity) {
        super.setValuesFrom(Entity);

        string1 = Entity.getString1();
        string2 = Entity.getString2();
    }

    @Override
    public void setDefaultValues() {
        super.setDefaultValues();

        string1 = "defaultval1";
        string2 = "defaultval2";
    }

    //...Getters and setters...

}
```

---------------------------------------------------------
Serializer & Parser
---------------------------------------------------------

NOTE: Both of them work for JSON only currently.

Serializer is used for serializing a model into a JSONObject.

```Java
public class ExampleSerializer extends BaseJSONSerializer<ExampleEntity> {

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
    
}
```

Parser is used to parse a model from a JSONObject
```Java
public class ExampleParser extends BaseJSONParser<ExampleEntity> {

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
    
}
```
By default theres already implemented a:
```Java
//In the parser...
public @NonNull List<AClass> parse(@NonNull JSONArray jarr);
//In the serializer...
public @NonNull JSONArray serialize(@NonNull List<AClass> listOfAClass);
```
You can also override them if you need another logic for them, but by default it just uses the case were each JSONObject from a JSONArray is AClass object.
