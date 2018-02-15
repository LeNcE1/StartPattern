package com.lence.startpattern.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SectionsModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("description")
@Expose
private Object description;
@SerializedName("sort")
@Expose
private Integer sort;
@SerializedName("parent_id")
@Expose
private Integer parentId;
@SerializedName("childrens")
@Expose
private List<Object> childrens = null;

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

public Integer getSort() {
return sort;
}

public void setSort(Integer sort) {
this.sort = sort;
}

public Integer getParentId() {
return parentId;
}

public void setParentId(Integer parentId) {
this.parentId = parentId;
}

public List<Object> getChildrens() {
return childrens;
}

public void setChildrens(List<Object> childrens) {
this.childrens = childrens;
}

}