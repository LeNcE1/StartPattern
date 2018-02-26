package com.lence.startpattern.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorReviewsModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("text")
@Expose
private String text;
@SerializedName("rate")
@Expose
private Integer rate;

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

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

public Integer getRate() {
return rate;
}

public void setRate(Integer rate) {
this.rate = rate;
}

}