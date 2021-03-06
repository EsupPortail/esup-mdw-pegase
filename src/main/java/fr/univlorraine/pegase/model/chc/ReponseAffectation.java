/*
 * API CHC v4
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v4
 *
 * The version of the OpenAPI document: 4.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.chc;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.model.chc.Cursus;
import fr.univlorraine.pegase.model.chc.CursusErreurMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ReponseAffectation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-11T17:44:47.769+01:00[Europe/Paris]")
public class ReponseAffectation {
  public static final String SERIALIZED_NAME_CURSUS = "cursus";
  @SerializedName(SERIALIZED_NAME_CURSUS)
  private List<Cursus> cursus = null;

  public static final String SERIALIZED_NAME_RESULTAT_ACQUIS = "resultatAcquis";
  @SerializedName(SERIALIZED_NAME_RESULTAT_ACQUIS)
  private Integer resultatAcquis;

  public static final String SERIALIZED_NAME_RESULTAT_AFFECTATION = "resultatAffectation";
  @SerializedName(SERIALIZED_NAME_RESULTAT_AFFECTATION)
  private Integer resultatAffectation;

  public static final String SERIALIZED_NAME_RESULTAT_DESAFFECTATION = "resultatDesaffectation";
  @SerializedName(SERIALIZED_NAME_RESULTAT_DESAFFECTATION)
  private Integer resultatDesaffectation;

  public static final String SERIALIZED_NAME_RESULTAT_ERREUR = "resultatErreur";
  @SerializedName(SERIALIZED_NAME_RESULTAT_ERREUR)
  private List<CursusErreurMessage> resultatErreur = null;

  public static final String SERIALIZED_NAME_RESULTAT_NON_ACQUIS = "resultatNonAcquis";
  @SerializedName(SERIALIZED_NAME_RESULTAT_NON_ACQUIS)
  private Integer resultatNonAcquis;


  public ReponseAffectation cursus(List<Cursus> cursus) {
    
    this.cursus = cursus;
    return this;
  }

  public ReponseAffectation addCursusItem(Cursus cursusItem) {
    if (this.cursus == null) {
      this.cursus = new ArrayList<>();
    }
    this.cursus.add(cursusItem);
    return this;
  }

   /**
   * Get cursus
   * @return cursus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<Cursus> getCursus() {
    return cursus;
  }


  public void setCursus(List<Cursus> cursus) {
    this.cursus = cursus;
  }


  public ReponseAffectation resultatAcquis(Integer resultatAcquis) {
    
    this.resultatAcquis = resultatAcquis;
    return this;
  }

   /**
   * le nombre de l&#39;objet maquette acquis
   * @return resultatAcquis
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le nombre de l'objet maquette acquis")

  public Integer getResultatAcquis() {
    return resultatAcquis;
  }


  public void setResultatAcquis(Integer resultatAcquis) {
    this.resultatAcquis = resultatAcquis;
  }


  public ReponseAffectation resultatAffectation(Integer resultatAffectation) {
    
    this.resultatAffectation = resultatAffectation;
    return this;
  }

   /**
   * le nombre de l&#39;objet maquette affecté
   * @return resultatAffectation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le nombre de l'objet maquette affecté")

  public Integer getResultatAffectation() {
    return resultatAffectation;
  }


  public void setResultatAffectation(Integer resultatAffectation) {
    this.resultatAffectation = resultatAffectation;
  }


  public ReponseAffectation resultatDesaffectation(Integer resultatDesaffectation) {
    
    this.resultatDesaffectation = resultatDesaffectation;
    return this;
  }

   /**
   * le nombre de l&#39;objet maquette désaffecté
   * @return resultatDesaffectation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le nombre de l'objet maquette désaffecté")

  public Integer getResultatDesaffectation() {
    return resultatDesaffectation;
  }


  public void setResultatDesaffectation(Integer resultatDesaffectation) {
    this.resultatDesaffectation = resultatDesaffectation;
  }


  public ReponseAffectation resultatErreur(List<CursusErreurMessage> resultatErreur) {
    
    this.resultatErreur = resultatErreur;
    return this;
  }

  public ReponseAffectation addResultatErreurItem(CursusErreurMessage resultatErreurItem) {
    if (this.resultatErreur == null) {
      this.resultatErreur = new ArrayList<>();
    }
    this.resultatErreur.add(resultatErreurItem);
    return this;
  }

   /**
   * Get resultatErreur
   * @return resultatErreur
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<CursusErreurMessage> getResultatErreur() {
    return resultatErreur;
  }


  public void setResultatErreur(List<CursusErreurMessage> resultatErreur) {
    this.resultatErreur = resultatErreur;
  }


  public ReponseAffectation resultatNonAcquis(Integer resultatNonAcquis) {
    
    this.resultatNonAcquis = resultatNonAcquis;
    return this;
  }

   /**
   * le nombre de l&#39;objet maquette non acquis
   * @return resultatNonAcquis
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le nombre de l'objet maquette non acquis")

  public Integer getResultatNonAcquis() {
    return resultatNonAcquis;
  }


  public void setResultatNonAcquis(Integer resultatNonAcquis) {
    this.resultatNonAcquis = resultatNonAcquis;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReponseAffectation reponseAffectation = (ReponseAffectation) o;
    return Objects.equals(this.cursus, reponseAffectation.cursus) &&
        Objects.equals(this.resultatAcquis, reponseAffectation.resultatAcquis) &&
        Objects.equals(this.resultatAffectation, reponseAffectation.resultatAffectation) &&
        Objects.equals(this.resultatDesaffectation, reponseAffectation.resultatDesaffectation) &&
        Objects.equals(this.resultatErreur, reponseAffectation.resultatErreur) &&
        Objects.equals(this.resultatNonAcquis, reponseAffectation.resultatNonAcquis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cursus, resultatAcquis, resultatAffectation, resultatDesaffectation, resultatErreur, resultatNonAcquis);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReponseAffectation {\n");
    sb.append("    cursus: ").append(toIndentedString(cursus)).append("\n");
    sb.append("    resultatAcquis: ").append(toIndentedString(resultatAcquis)).append("\n");
    sb.append("    resultatAffectation: ").append(toIndentedString(resultatAffectation)).append("\n");
    sb.append("    resultatDesaffectation: ").append(toIndentedString(resultatDesaffectation)).append("\n");
    sb.append("    resultatErreur: ").append(toIndentedString(resultatErreur)).append("\n");
    sb.append("    resultatNonAcquis: ").append(toIndentedString(resultatNonAcquis)).append("\n");
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

