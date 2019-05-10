package com.estm.GR_Location.Manager;

public class Societe {

	private int id;
	private String nom;
	private String email ;
	private String adresse ;
	private String logo	;
	private String tel ;
	private String fix ;
	public  Societe(){}
	public Societe(int id, String nom, String email, String adresse, String logo, String tel, String fix) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.logo = logo;
		this.tel = tel;
		this.fix = fix;
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
