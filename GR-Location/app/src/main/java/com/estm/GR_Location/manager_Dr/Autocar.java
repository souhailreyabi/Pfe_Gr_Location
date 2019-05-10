package com.estm.GR_Location.manager_Dr;

import android.os.Parcel;
import android.os.Parcelable;

public class Autocar implements Parcelable{
	
	
	private int id;
	private String nb_place ;
	private String km;
	private String Matricul ;
	private String Model;
	private String Vitre_teinte;
	private String Chouffage ;
	private String image;
	public Autocar(){

	}
	public Autocar(int id, String nb_place, String km, String matricul, String model, String vitre_teinte, String chouffage, String image) {
		this.id = id;
		this.nb_place = nb_place;
		this.km = km;
		Matricul = matricul;
		Model = model;
		Vitre_teinte = vitre_teinte;
		Chouffage = chouffage;
		this.image = image;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(nb_place);
		dest.writeString(km);
		dest.writeString(Matricul);
		dest.writeString(Model);
		dest.writeString(Vitre_teinte + "");
		dest.writeString(Chouffage +"") ;
	}
	// Creator
	public static final Parcelable.Creator CREATOR
			= new Parcelable.Creator() {
		public Autocar createFromParcel(Parcel in) {
			return new Autocar(in);
		}

		public Autocar[] newArray(int size) {
			return new Autocar[size];
		}
	};

	// "De-parcel object
	public Autocar(Parcel in) {
		id = in.readInt();
		nb_place = in.readString();
		km = in.readString();
		Matricul = in.readString();
		Model = in.readString();
		Vitre_teinte = in.readString();
		Chouffage = in.readString();
		image = in.readString();
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
	public String isVitre_teinte() {
		return Vitre_teinte;
	}
	public void setVitre_teinte(String vitre_teinte) {
		Vitre_teinte = vitre_teinte;
	}
	public String isChouffage() {
		return Chouffage;
	}
	public void setChouffage(String chouffage) {
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


	@Override
	public int describeContents() {
		return 0;
	}

}
