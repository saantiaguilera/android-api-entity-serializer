package com.santiago.example.entity;

import com.santiago.entity.BaseEntity;

/**
 * Created by santiago on 19/03/16.
 */
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

    /*----------------------------------Getters and Setters----------------------------*/

    public String getString2() {
        return string2;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

}
