package fr.univlorraine.mondossierweb.model.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the historique_recherche database table.
 * 
 */
@Entity
@Table(name="historique_recherche")
@NamedQuery(name="HistoriqueRecherche.findAll", query="SELECT h FROM HistoriqueRecherche h")
public class HistoriqueRecherche implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HistoriqueRecherchePK id;

	@Column(name="uid_apprenant")
	private String uidApprenant;
	
	@Column(name="code_apprenant")
	private String codeApprenant;

	@Column(name="display_name")
	private String displayName;

	private String mail;

	public HistoriqueRecherche() {
	}

	public HistoriqueRecherchePK getId() {
		return this.id;
	}

	public void setId(HistoriqueRecherchePK id) {
		this.id = id;
	}
	
	
	public String getUidApprenant() {
		return uidApprenant;
	}

	public void setUidApprenant(String uidApprenant) {
		this.uidApprenant = uidApprenant;
	}

	public String getCodeApprenant() {
		return this.codeApprenant;
	}

	public void setCodeApprenant(String codeApprenant) {
		this.codeApprenant = codeApprenant;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}