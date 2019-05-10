package com.estm.GR_Location.Manager;

public class Trajet {
	
	private int id;
	private String LAT_Arr;
	private String Date_Arr_Reel;
	private String Date_Depart;
	private String Date_Arr_Attendue;
	private String LNG_Arr;
	private String LNG_Depart;
	private String LAT_Depart;
	
	public  Trajet(){

	}
	
	
	
	
	public Trajet(int id, String lAT_Arr, String date_Arr_Reel, String date_Depart, String date_Arr_Attendue,
			String lNG_Arr, String lNG_Depart, String lAT_Depart) {
		super();
		this.id = id;
		LAT_Arr = lAT_Arr;
		Date_Arr_Reel = date_Arr_Reel;
		Date_Depart = date_Depart;
		Date_Arr_Attendue = date_Arr_Attendue;
		LNG_Arr = lNG_Arr;
		LNG_Depart = lNG_Depart;
		LAT_Depart = lAT_Depart;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLAT_Arr() {
		return LAT_Arr;
	}
	public void setLAT_Arr(String lAT_Arr) {
		LAT_Arr = lAT_Arr;
	}
	public String getDate_Arr_Reel() {
		return Date_Arr_Reel;
	}
	public void setDate_Arr_Reel(String date_Arr_Reel) {
		Date_Arr_Reel = date_Arr_Reel;
	}
	public String getDate_Depart() {
		return Date_Depart;
	}
	public void setDate_Depart(String date_Depart) {
		Date_Depart = date_Depart;
	}
	public String getDate_Arr_Attendue() {
		return Date_Arr_Attendue;
	}
	public void setDate_Arr_Attendue(String date_Arr_Attendue) {
		Date_Arr_Attendue = date_Arr_Attendue;
	}
	public String getLNG_Arr() {
		return LNG_Arr;
	}
	public void setLNG_Arr(String lNG_Arr) {
		LNG_Arr = lNG_Arr;
	}
	public String getLNG_Depart() {
		return LNG_Depart;
	}
	public void setLNG_Depart(String lNG_Depart) {
		LNG_Depart = lNG_Depart;
	}
	public String getLAT_Depart() {
		return LAT_Depart;
	}
	public void setLAT_Depart(String lAT_Depart) {
		LAT_Depart = lAT_Depart;
	}


	@Override
	public String toString() {
		return "Trajet [id=" + id + ", LAT_Arr=" + LAT_Arr + ", Date_Arr_Reel=" + Date_Arr_Reel + ", Date_Depart="
				+ Date_Depart + ", Date_Arr_Attendue=" + Date_Arr_Attendue + ", LNG_Arr=" + LNG_Arr + ", LNG_Depart="
				+ LNG_Depart + ", LAT_Depart=" + LAT_Depart + "]";
	}
	  
	
    
}
