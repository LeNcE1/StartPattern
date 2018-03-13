package com.lence.startpattern;


import android.util.Log;

public class SingletonStorage {
    private int servicesId = 0;
    private String servicesDescription = "";
    private int servicesPrice = 0;
    private int associateId = 0;
    private String associateName = "";
    private String associateDescription = "";
    private String associateImage = "";
    private int associateRate = 0;
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

    public void setServices(int servicesId,
                            String servicesDescription,
                            int servicesPrice) {
        setServicesId(servicesId);
        setServicesDescription(servicesDescription);
        setServicesPrice(servicesPrice);
        setAssociateId(0);
        setAssociateName("");
        setAssociateDescription("");
        setAssociateImage("");
        setAssociateRate(0);
        setDate("");
        setTime("");
    }

    public void setAssociate(int associateId,
                             String associateName,
                             String associateDescription,
                             String associateImage,
                             int associateRate) {
        setServicesId(servicesId);
        setServicesDescription(servicesDescription);
        setServicesPrice(servicesPrice);
        setAssociateId(associateId);
        setAssociateName(associateName);
        setAssociateDescription(associateDescription);
        setAssociateImage(associateImage);
        setAssociateRate(associateRate);
        setDate("");
        setTime("");
    }

    public void setAll(int servicesId,
                       String servicesDescription,
                       int servicesPrice,
                       int associateId,
                       String associateName,
                       String associateDescription,
                       int associateRate,
                       String date,
                       String time) {
        setServicesId(servicesId);
        setServicesDescription(servicesDescription);
        setServicesPrice(servicesPrice);
        setAssociateId(associateId);
        setAssociateName(associateName);
        setAssociateDescription(associateDescription);
        setAssociateRate(associateRate);
        setDate(date);
        setTime(time);
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

    public int getServicesPrice() {
        return servicesPrice;
    }

    public void setServicesPrice(int servicesPrice) {
        this.servicesPrice = servicesPrice;
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

    public String getAssociateDescription() {
        return associateDescription;
    }

    public void setAssociateDescription(String associateDescription) {
        this.associateDescription = associateDescription;
    }

    public String getAssociateImage() {
        return associateImage;
    }

    public void setAssociateImage(String associateImage) {
        this.associateImage = associateImage;
    }

    public int getAssociateRate() {
        return associateRate;
    }

    public void setAssociateRate(int associateRate) {
        this.associateRate = associateRate;
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
