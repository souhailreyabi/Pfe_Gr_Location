package com.estm.GR_Location.manager_Dr;

import android.os.Parcel;
import android.os.Parcelable;

public class Employer implements Parcelable {
	private int id;
	private String role;
	private String email;
	private String nom ;
	private String avatar;
	private String tel;

	public Employer() {
	}

	public Employer(int id, String role, String email, String nom, String avatar, String tel) {
		this.id = id;
		this.role = role;
		this.email = email;
		this.nom = nom;
		this.avatar = avatar;
		this.tel = tel;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(role);
		dest.writeString(email);
		dest.writeString(nom);
		dest.writeString(avatar);
		dest.writeString(tel );
	}

	// Creator
	public static final Parcelable.Creator CREATOR
			= new Parcelable.Creator() {
		public Employer createFromParcel(Parcel in) {
			return new Employer(in);
		}

		public Employer[] newArray(int size) {
			return new Employer[size];
		}
	};

	// "De-parcel object
	public Employer(Parcel in) {
		id = in.readInt();
		role = in.readString();
		email = in.readString();
		nom = in.readString();
		avatar = in.readString();
		tel = in.readString();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}


	@Override
	public String toString() {
		return "Employer [id=" + id + ", role=" + role + ", email=" + email + ", nom=" + nom + ", avatar=" + avatar
				+ ", tel=" + tel + "]";
	}
	
	
	
}
