/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insgestion;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.model.insgestion.Admission;
import fr.univlorraine.pegase.model.insgestion.CibleInscription;
import fr.univlorraine.pegase.model.insgestion.Cvec;
import fr.univlorraine.pegase.model.insgestion.OccurrenceNomenclature;
import fr.univlorraine.pegase.model.insgestion.Origine;
import fr.univlorraine.pegase.model.insgestion.SituationPersonnelleInscription;
import fr.univlorraine.pegase.model.insgestion.SituationPrecedente;
import fr.univlorraine.pegase.model.insgestion.SituationUniversitaire;
import fr.univlorraine.pegase.model.insgestion.StatutInscriptionVoeu;
import fr.univlorraine.pegase.model.insgestion.StatutPaiementVoeu;
import fr.univlorraine.pegase.model.insgestion.StatutPiecesVoeu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * InscriptionComplete
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-06-30T15:25:34.956+02:00[Europe/Paris]")
public class InscriptionComplete {
  public static final String SERIALIZED_NAME_CIBLE = "cible";
  @SerializedName(SERIALIZED_NAME_CIBLE)
  private CibleInscription cible = null;

  public static final String SERIALIZED_NAME_ORIGINE = "origine";
  @SerializedName(SERIALIZED_NAME_ORIGINE)
  private Origine origine;

  public static final String SERIALIZED_NAME_STATUT_INSCRIPTION = "statutInscription";
  @SerializedName(SERIALIZED_NAME_STATUT_INSCRIPTION)
  private StatutInscriptionVoeu statutInscription;

  public static final String SERIALIZED_NAME_STATUT_PAIEMENT = "statutPaiement";
  @SerializedName(SERIALIZED_NAME_STATUT_PAIEMENT)
  private StatutPaiementVoeu statutPaiement;

  public static final String SERIALIZED_NAME_STATUT_PIECES = "statutPieces";
  @SerializedName(SERIALIZED_NAME_STATUT_PIECES)
  private StatutPiecesVoeu statutPieces;

  public static final String SERIALIZED_NAME_REGIME_INSCRIPTION = "regimeInscription";
  @SerializedName(SERIALIZED_NAME_REGIME_INSCRIPTION)
  private OccurrenceNomenclature regimeInscription;

  public static final String SERIALIZED_NAME_BOURSES_ET_AIDES = "boursesEtAides";
  @SerializedName(SERIALIZED_NAME_BOURSES_ET_AIDES)
  private List<OccurrenceNomenclature> boursesEtAides = null;

  public static final String SERIALIZED_NAME_SITUATION_PERSONNELLE_INSCRIPTION = "situationPersonnelleInscription";
  @SerializedName(SERIALIZED_NAME_SITUATION_PERSONNELLE_INSCRIPTION)
  private SituationPersonnelleInscription situationPersonnelleInscription;

  public static final String SERIALIZED_NAME_CVEC = "cvec";
  @SerializedName(SERIALIZED_NAME_CVEC)
  private Cvec cvec;

  public static final String SERIALIZED_NAME_ADMISSION = "admission";
  @SerializedName(SERIALIZED_NAME_ADMISSION)
  private Admission admission;

  public static final String SERIALIZED_NAME_SITUATION_UNIVERSITAIRE = "situationUniversitaire";
  @SerializedName(SERIALIZED_NAME_SITUATION_UNIVERSITAIRE)
  private SituationUniversitaire situationUniversitaire;

  public static final String SERIALIZED_NAME_SITUATION_PRECEDENTE = "situationPrecedente";
  @SerializedName(SERIALIZED_NAME_SITUATION_PRECEDENTE)
  private SituationPrecedente situationPrecedente;


  public InscriptionComplete cible(CibleInscription cible) {
    
    this.cible = cible;
    return this;
  }

   /**
   * Get cible
   * @return cible
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public CibleInscription getCible() {
    return cible;
  }


  public void setCible(CibleInscription cible) {
    this.cible = cible;
  }


  public InscriptionComplete origine(Origine origine) {
    
    this.origine = origine;
    return this;
  }

   /**
   * Get origine
   * @return origine
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Origine getOrigine() {
    return origine;
  }


  public void setOrigine(Origine origine) {
    this.origine = origine;
  }


  public InscriptionComplete statutInscription(StatutInscriptionVoeu statutInscription) {
    
    this.statutInscription = statutInscription;
    return this;
  }

   /**
   * Get statutInscription
   * @return statutInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StatutInscriptionVoeu getStatutInscription() {
    return statutInscription;
  }


  public void setStatutInscription(StatutInscriptionVoeu statutInscription) {
    this.statutInscription = statutInscription;
  }


  public InscriptionComplete statutPaiement(StatutPaiementVoeu statutPaiement) {
    
    this.statutPaiement = statutPaiement;
    return this;
  }

   /**
   * Get statutPaiement
   * @return statutPaiement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StatutPaiementVoeu getStatutPaiement() {
    return statutPaiement;
  }


  public void setStatutPaiement(StatutPaiementVoeu statutPaiement) {
    this.statutPaiement = statutPaiement;
  }


  public InscriptionComplete statutPieces(StatutPiecesVoeu statutPieces) {
    
    this.statutPieces = statutPieces;
    return this;
  }

   /**
   * Get statutPieces
   * @return statutPieces
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StatutPiecesVoeu getStatutPieces() {
    return statutPieces;
  }


  public void setStatutPieces(StatutPiecesVoeu statutPieces) {
    this.statutPieces = statutPieces;
  }


  public InscriptionComplete regimeInscription(OccurrenceNomenclature regimeInscription) {
    
    this.regimeInscription = regimeInscription;
    return this;
  }

   /**
   * Get regimeInscription
   * @return regimeInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OccurrenceNomenclature getRegimeInscription() {
    return regimeInscription;
  }


  public void setRegimeInscription(OccurrenceNomenclature regimeInscription) {
    this.regimeInscription = regimeInscription;
  }


  public InscriptionComplete boursesEtAides(List<OccurrenceNomenclature> boursesEtAides) {
    
    this.boursesEtAides = boursesEtAides;
    return this;
  }

  public InscriptionComplete addBoursesEtAidesItem(OccurrenceNomenclature boursesEtAidesItem) {
    if (this.boursesEtAides == null) {
      this.boursesEtAides = new ArrayList<>();
    }
    this.boursesEtAides.add(boursesEtAidesItem);
    return this;
  }

   /**
   * Les bourses et aides financières
   * @return boursesEtAides
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Les bourses et aides financières")

  public List<OccurrenceNomenclature> getBoursesEtAides() {
    return boursesEtAides;
  }


  public void setBoursesEtAides(List<OccurrenceNomenclature> boursesEtAides) {
    this.boursesEtAides = boursesEtAides;
  }


  public InscriptionComplete situationPersonnelleInscription(SituationPersonnelleInscription situationPersonnelleInscription) {
    
    this.situationPersonnelleInscription = situationPersonnelleInscription;
    return this;
  }

   /**
   * Get situationPersonnelleInscription
   * @return situationPersonnelleInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SituationPersonnelleInscription getSituationPersonnelleInscription() {
    return situationPersonnelleInscription;
  }


  public void setSituationPersonnelleInscription(SituationPersonnelleInscription situationPersonnelleInscription) {
    this.situationPersonnelleInscription = situationPersonnelleInscription;
  }


  public InscriptionComplete cvec(Cvec cvec) {
    
    this.cvec = cvec;
    return this;
  }

   /**
   * Get cvec
   * @return cvec
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Cvec getCvec() {
    return cvec;
  }


  public void setCvec(Cvec cvec) {
    this.cvec = cvec;
  }


  public InscriptionComplete admission(Admission admission) {
    
    this.admission = admission;
    return this;
  }

   /**
   * Get admission
   * @return admission
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Admission getAdmission() {
    return admission;
  }


  public void setAdmission(Admission admission) {
    this.admission = admission;
  }


  public InscriptionComplete situationUniversitaire(SituationUniversitaire situationUniversitaire) {
    
    this.situationUniversitaire = situationUniversitaire;
    return this;
  }

   /**
   * Get situationUniversitaire
   * @return situationUniversitaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SituationUniversitaire getSituationUniversitaire() {
    return situationUniversitaire;
  }


  public void setSituationUniversitaire(SituationUniversitaire situationUniversitaire) {
    this.situationUniversitaire = situationUniversitaire;
  }


  public InscriptionComplete situationPrecedente(SituationPrecedente situationPrecedente) {
    
    this.situationPrecedente = situationPrecedente;
    return this;
  }

   /**
   * Get situationPrecedente
   * @return situationPrecedente
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SituationPrecedente getSituationPrecedente() {
    return situationPrecedente;
  }


  public void setSituationPrecedente(SituationPrecedente situationPrecedente) {
    this.situationPrecedente = situationPrecedente;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InscriptionComplete inscriptionComplete = (InscriptionComplete) o;
    return Objects.equals(this.cible, inscriptionComplete.cible) &&
        Objects.equals(this.origine, inscriptionComplete.origine) &&
        Objects.equals(this.statutInscription, inscriptionComplete.statutInscription) &&
        Objects.equals(this.statutPaiement, inscriptionComplete.statutPaiement) &&
        Objects.equals(this.statutPieces, inscriptionComplete.statutPieces) &&
        Objects.equals(this.regimeInscription, inscriptionComplete.regimeInscription) &&
        Objects.equals(this.boursesEtAides, inscriptionComplete.boursesEtAides) &&
        Objects.equals(this.situationPersonnelleInscription, inscriptionComplete.situationPersonnelleInscription) &&
        Objects.equals(this.cvec, inscriptionComplete.cvec) &&
        Objects.equals(this.admission, inscriptionComplete.admission) &&
        Objects.equals(this.situationUniversitaire, inscriptionComplete.situationUniversitaire) &&
        Objects.equals(this.situationPrecedente, inscriptionComplete.situationPrecedente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cible, origine, statutInscription, statutPaiement, statutPieces, regimeInscription, boursesEtAides, situationPersonnelleInscription, cvec, admission, situationUniversitaire, situationPrecedente);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InscriptionComplete {\n");
    sb.append("    cible: ").append(toIndentedString(cible)).append("\n");
    sb.append("    origine: ").append(toIndentedString(origine)).append("\n");
    sb.append("    statutInscription: ").append(toIndentedString(statutInscription)).append("\n");
    sb.append("    statutPaiement: ").append(toIndentedString(statutPaiement)).append("\n");
    sb.append("    statutPieces: ").append(toIndentedString(statutPieces)).append("\n");
    sb.append("    regimeInscription: ").append(toIndentedString(regimeInscription)).append("\n");
    sb.append("    boursesEtAides: ").append(toIndentedString(boursesEtAides)).append("\n");
    sb.append("    situationPersonnelleInscription: ").append(toIndentedString(situationPersonnelleInscription)).append("\n");
    sb.append("    cvec: ").append(toIndentedString(cvec)).append("\n");
    sb.append("    admission: ").append(toIndentedString(admission)).append("\n");
    sb.append("    situationUniversitaire: ").append(toIndentedString(situationUniversitaire)).append("\n");
    sb.append("    situationPrecedente: ").append(toIndentedString(situationPrecedente)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
