package fr.univlorraine.mondossierweb.model.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the historique_recherche database table.
 * 
 */
@Embeddable
public class HistoriqueRecherchePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String username;

	@Column(name="date_create")
	private LocalDateTime dateCreate;

	public HistoriqueRecherchePK() {
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDateTime getDateCreate() {
		return this.dateCreate;
	}
	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HistoriqueRecherchePK)) {
			return false;
		}
		HistoriqueRecherchePK castOther = (HistoriqueRecherchePK)other;
		return 
			this.username.equals(castOther.username)
			&& this.dateCreate.equals(castOther.dateCreate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.dateCreate.hashCode();
		
		return hash;
	}
}