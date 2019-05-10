package com.estm.GR_Location.manager_Dr;

import android.os.Parcel;
import android.os.Parcelable;

public class Pauses implements Parcelable{

	private int id ;
	private String LNG;
	private String LAT ;
	private int Duree;
	private String nom ;

	public Pauses() {
	}

	public Pauses(int id, String LNG, String LAT, int duree, String nom) {
		this.id = id;
		this.LNG = LNG;
		this.LAT = LAT;
		Duree = duree;
		this.nom = nom;
	}

	// Creator
	public static final Parcelable.Creator CREATOR
			= new Parcelable.Creator() {
		public Pauses createFromParcel(Parcel in) {
			return new Pauses(in);
		}

		public Pauses[] newArray(int size) {
			return new Pauses[size];
		}
	};

	// "De-parcel object
	public Pauses(Parcel in) {
		id = in.readInt();
		LNG = in.readString();
		LAT = in.readString();
		Duree = in.readInt();
		nom = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(LNG);
		dest.writeString(LAT);
		dest.writeInt(Duree);
		dest.writeString(nom);
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


	@Override
	public int describeContents() {
		return 0;
	}
}
