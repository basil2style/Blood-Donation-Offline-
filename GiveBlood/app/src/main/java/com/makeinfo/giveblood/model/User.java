package com.makeinfo.giveblood.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Basil on 8/9/2015.
 */
@JsonObject
public class User {

    @JsonField(name="slNo")
    public Integer slNo;

    @JsonField(name="name")
    public String name;

    @JsonField(name="contactNo")
    public String contactNo;
}
