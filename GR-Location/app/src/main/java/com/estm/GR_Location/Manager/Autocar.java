package com.estm.GR_Location.Manager;

public class Autocar {
	
	
	private int id;
	private String nb_place ;
	private String km;
	private String Matricul ;
	private String Model;
	private boolean Vitre_teinte;
	private boolean Chouffage ;
	private String image;
	public Autocar(){}
	public Autocar(int id, String nb_place, String km, String matricul, String model, boolean vitre_teinte,
			boolean chouffage, String image) {
		super();
		this.id = id;
		this.nb_place = nb_place;
		this.km = km;
		Matricul = matricul;
		Model = model;
		Vitre_teinte = vitre_teinte;
		Chouffage = chouffage;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNb_place() {
		return nb_place;
	}
	public void setNb_place(String nb_place) {
		this.nb_place = nb_place;
	}
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
	}
	public String getMatricul() {
		return Matricul;
	}
	public void setMatricul(String matricul) {
		Matricul = matricul;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public boolean isVitre_teinte() {
		return Vitre_teinte;
	}
	public void setVitre_teinte(boolean vitre_teinte) {
		Vitre_teinte = vitre_teinte;
	}
	public boolean isChouffage() {
		return Chouffage;
	}
	public void setChouffage(boolean chouffage) {
		Chouffage = chouffage;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Autocar [id=" + id + ", nb_place=" + nb_place + ", km=" + km + ", Matricul=" + Matricul + ", Model="
				+ Model + ", Vitre_teinte=" + Vitre_teinte + ", Chouffage=" + Chouffage + ", image=" + image + "]";
	}
	
	
	
	
	
	

}
