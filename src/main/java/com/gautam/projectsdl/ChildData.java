package com.gautam.projectsdl;

public class ChildData
{
    String Height,Weight,Gender,Result,mob,Lat,Lon,ddate;

    public ChildData() {
        Height=Weight=Gender=Result=mob=Lat=Lon=ddate="";
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public ChildData(String height, String weight, String gender, String result, String mob, String Lat, String Lon, String  ddate) {
        Height = height;
        Weight = weight;
        Gender = gender;
        Result = result;
        this.mob=mob;
        this.Lat=Lat;
        this.Lon=Lon;
        this.ddate=ddate;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public String getPID() {
        return mob;
    }

    public void setPID(String PID) {
        this.mob= PID;
    }

    public String getHeight() {
        return Height;
    }

    public String getWeight() {
        return Weight;
    }

    public String getGender() {
        return Gender;
    }

    public String getResult() {
        return Result;
    }

    public void setHeight(String height) {
        this.Height = height;
    }

    public void setWeight(String weight) {
        this.Weight = weight;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public void setResult(String result) {
        this.Result = result;
    }
}
