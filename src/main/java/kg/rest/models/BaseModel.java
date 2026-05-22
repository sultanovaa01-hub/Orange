package kg.rest.models;

import kg.rest.jsonUtils;

public abstract class BaseModel {
    public String toJSON (){
        return jsonUtils.toJson(this);
    }
}
