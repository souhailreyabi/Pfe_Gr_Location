package com.estm.GR_Location.Manager;

public class Pause {

	private int id ;
	private String LNG;
	private String LAT ;
	private int Duree;
	private String nom ;
	public Pause() {
	/*	super();
		this.id = id;
		LNG = lNG;
		LAT = lAT;
		Duree = duree;
		this.nom = nom;*/
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLNG() {
		return LNG;
	}
	public void setLNG(String lNG) {
		LNG = lNG;
	}
	public String getLAT() {
		return LAT;
	}
	public void setLAT(String lAT) {
		LAT = lAT;
	}
	public int getDuree() {
		return Duree;
	}
	public void setDuree(int duree) {
		Duree = duree;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Pause [id=" + id + ", LNG=" + LNG + ", LAT=" + LAT + ", Duree=" + Duree + ", nom=" + nom + "]";
	}
	
	
	
	   
}
