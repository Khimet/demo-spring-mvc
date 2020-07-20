package dev.hotel.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Client extends BaseEntite {

	@Column(name = "NOM")
    private String nom;
	
	@Column(name = "PRENOMS")
    private String prenoms;

    public Client() {
    }

    public Client(String nom, String prenoms) {
        this.nom = nom;
        this.prenoms = prenoms;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }
}
