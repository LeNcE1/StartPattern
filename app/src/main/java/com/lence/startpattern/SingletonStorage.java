package com.lence.startpattern;


import android.util.Log;

public class SingletonStorage {
    private int sectionsId = 0;
    private int servicesId = 0;
    private String servicesDescription = "";
    private int associateId = 0;
    private String associateName = "";
    private String date = "";
    private String time = "";
    private static SingletonStorage instance;

    public static void initInstance() {
        Log.d("MY", "MySingleton::InitInstance()");
        if (instance == null) {
            instance = new SingletonStorage();
        }
    }

    public static SingletonStorage getInstance() {
        Log.d("MY", "MySingleton::getInstance()");
        return instance;
    }

    public SingletonStorage() {

    }

    public int getSectionsId() {
        return sectionsId;
    }

    public void setSectionsId(int sectionsId) {
        this.sectionsId = sectionsId;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }

    public String getServicesDescription() {
        return servicesDescription;
    }

    public void setServicesDescription(String servicesDescription) {
        this.servicesDescription = servicesDescription;
    }

    public int getAssociateId() {
        return associateId;
    }

    public void setAssociateId(int associateId) {
        this.associateId = associateId;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
