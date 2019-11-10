package com.gautam.projectsdl;


public class Infodata {

    private String quotes;
    private int image;
    public Infodata(){

    }
    public Infodata(String quotes,int image){
        this.quotes = quotes;

        this.image = image;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
