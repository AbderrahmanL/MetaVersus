package com.tsp.shared;

public class City {
    private double x;
    private double y;
    private String cityName;            

    //Constructor

	public City(String cityName, double x, double y){
    	this.cityName = cityName;
        this.x = x;
        this.y = y;
    }        
	
	public City( double x, double y){
    	this.cityName = "city";
        this.x = x;
        this.y = y;
    }   
        

	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
