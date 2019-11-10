package com.gautam.projectsdl;

import java.io.Serializable;

public class ChildInfo implements Serializable
{
    String fname,mothname,dob,add,deliv,occup,gen,food,Lat,Lon,mob;

    public ChildInfo()
    {


    }
    public ChildInfo(String fname, String mothname, String dob, String add, String deliv, String occup, String gen, String food,String Lon,String Lat,String mob) {
        this.fname = fname;
        this.mothname = mothname;
        this.dob = dob;
        this.add = add;
        this.deliv = deliv;
        this.occup = occup;
        this.gen = gen;
        this.food = food;

        this.Lat=Lat;
        this.Lon=Lon;
        this.mob=mob;

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




    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMothname() {
        return mothname;
    }

    public void setMothname(String mothname) {
        this.mothname = mothname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getDeliv() {
        return deliv;
    }

    public void setDeliv(String deliv) {
        this.deliv = deliv;
    }

    public String getOccup() {
        return occup;
    }

    public void setOccup(String occup) {
        this.occup = occup;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}
