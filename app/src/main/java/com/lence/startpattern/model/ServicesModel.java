package com.lence.startpattern.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("description")
@Expose
private Object description;
@SerializedName("section_id")
@Expose
private Integer sectionId;
@SerializedName("sort")
@Expose
private Integer sort;
@SerializedName("price")
@Expose
private Integer price;

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

public Object getDescription() {
return description;
}

public void setDescription(Object description) {
this.description = description;
}

public Integer getSectionId() {
return sectionId;
}

public void setSectionId(Integer sectionId) {
this.sectionId = sectionId;
}

public Integer getSort() {
return sort;
}

public void setSort(Integer sort) {
this.sort = sort;
}

public Integer getPrice() {
return price;
}

public void setPrice(Integer price) {
this.price = price;
}

}