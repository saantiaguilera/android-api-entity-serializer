Serializer & Entity


---------------------------------------------------------
TODO
---------------------------------------------------------
- Refactor Serializer name since it also parses


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
Serializer (and parser, I should refactor it)
---------------------------------------------------------

Serializer is used for hidrating or serializing a model.

```Java
public class ExampleSerializer extends BaseJSONSerializer<ExampleEntity> {

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

}

```
