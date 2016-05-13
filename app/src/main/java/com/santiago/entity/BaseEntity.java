package com.santiago.entity;

import android.support.annotation.Nullable;

/**
 * BaseEntity
 *
 * Created by santiago on 19/03/16.
 */
public abstract class BaseEntity {

	/*-------------------------------------------------Fields-----------------------------------------------------------------*/

    private long id;

	/*------------------------------------------------Constructors------------------------------------------------------------*/

    public BaseEntity() {
        setDefaultValues();
    }

    public BaseEntity(@Nullable BaseEntity Entity) {
        setValuesFrom(Entity);
    }
	/*------------------------------------------------Getters------------------------------------------------------------*/

    public long getId() {
        return id;
    }

	/*------------------------------------------------Setters------------------------------------------------------------*/

    public void setId(long id) {
        this.id = id;
    }

    public void setValuesFrom(@Nullable BaseEntity Entity){
        if(Entity != null)
            setId(Entity.getId());
        else setDefaultValues();
    }

    public void setDefaultValues(){
        setId(0);
    }

	/*----------------------------------------------------Methods---------------------------------------------------------*/

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof BaseEntity))
            return false;

        if (((BaseEntity) obj).getId() == getId())
            return true;
        else return false;
    }

}
