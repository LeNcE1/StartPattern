package com.lence.startpattern.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssociateModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("surname")
@Expose
private String surname;
@SerializedName("secondname")
@Expose
private String secondname;
@SerializedName("sort")
@Expose
private Integer sort;
@SerializedName("description")
@Expose
private Object description;
@SerializedName("image")
@Expose
private String image;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getSurname() {
return surname;
}

public void setSurname(String surname) {
this.surname = surname;
}

public String getSecondname() {
return secondname;
}

public void setSecondname(String secondname) {
this.secondname = secondname;
}

public Integer getSort() {
return sort;
}

public void setSort(Integer sort) {
this.sort = sort;
}

public Object getDescription() {
return description;
}

public void setDescription(Object description) {
this.description = description;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

}