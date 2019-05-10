package com.estm.GR_Location.manager_Dr;

import android.os.Parcel;
import android.os.Parcelable;

public class Societe implements Parcelable {

	private int id;
	private String nom;
	private String email ;
	private String adresse ;
	private String logo	;
	private String tel ;
	private String fix ;

	public Societe(){

	}
	@Override
	public int describeContents() {
		return 0;
	}

	public Societe(int id, String nom, String email, String adresse, String logo, String tel, String fix) {
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.logo = logo;
		this.tel = tel;
		this.fix = fix;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(nom);
		dest.writeString(email);
		dest.writeString(adresse);
		dest.writeString(tel);
		dest.writeString(fix);
	}
	// Creator
	public static final Parcelable.Creator CREATOR
			= new Parcelable.Creator() {
		public Societe createFromParcel(Parcel in) {
			return new Societe(in);
		}

		public Societe[] newArray(int size) {
			return new Societe[size];
		}
	};

	// "De-parcel object
	public Societe(Parcel in) {
		id = in.readInt();
		nom = in.readString();
		email = in.readString();
		adresse = in.readString();
		logo = in.readString();
		tel = in.readString();
		fix = in.readString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFix() {
		return fix;
	}

	public void setFix(String fix) {
		this.fix = fix;
	}

	@Override
	public String toString() {
		return "Societe [id=" + id + ", nom=" + nom + ", email=" + email + ", adresse=" + adresse + ", logo=" + logo
				+ ", tel=" + tel + ", fix=" + fix + "]";
	}



}
