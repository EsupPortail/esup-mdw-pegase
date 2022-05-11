/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 16.0.0
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Profession
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-05-11T10:46:47.317+02:00[Europe/Paris]")
public class Profession {
  public static final String SERIALIZED_NAME_ETUDIANT = "etudiant";
  @SerializedName(SERIALIZED_NAME_ETUDIANT)
  private String etudiant;

  public static final String SERIALIZED_NAME_PARENT1 = "parent1";
  @SerializedName(SERIALIZED_NAME_PARENT1)
  private String parent1;

  public static final String SERIALIZED_NAME_PARENT2 = "parent2";
  @SerializedName(SERIALIZED_NAME_PARENT2)
  private String parent2;

  public static final String SERIALIZED_NAME_QUOTITE = "quotite";
  @SerializedName(SERIALIZED_NAME_QUOTITE)
  private String quotite;

  public Profession() { 
  }

  public Profession etudiant(String etudiant) {
    
    this.etudiant = etudiant;
    return this;
  }

   /**
   * Le code profession de l&#39;étudiant issu de la nomenclature des Professions et catégories socioprofessionnelles. Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS 
   * @return etudiant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code profession de l'étudiant issu de la nomenclature des Professions et catégories socioprofessionnelles. Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS ")

  public String getEtudiant() {
    return etudiant;
  }


  public void setEtudiant(String etudiant) {
    this.etudiant = etudiant;
  }


  public Profession parent1(String parent1) {
    
    this.parent1 = parent1;
    return this;
  }

   /**
   * Le code profession du parent 1 issu de la nomenclature des Professions et catégories socioprofessionnelles  Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS 
   * @return parent1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code profession du parent 1 issu de la nomenclature des Professions et catégories socioprofessionnelles  Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS ")

  public String getParent1() {
    return parent1;
  }


  public void setParent1(String parent1) {
    this.parent1 = parent1;
  }


  public Profession parent2(String parent2) {
    
    this.parent2 = parent2;
    return this;
  }

   /**
   * Le code profession du parent 2 issu de la nomenclature des Professions et catégories socioprofessionnelles  Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS 
   * @return parent2
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code profession du parent 2 issu de la nomenclature des Professions et catégories socioprofessionnelles  Codes de la nomenclature sur 6 caractères. Préfixe des codes : PCS ")

  public String getParent2() {
    return parent2;
  }


  public void setParent2(String parent2) {
    this.parent2 = parent2;
  }


  public Profession quotite(String quotite) {
    
    this.quotite = quotite;
    return this;
  }

   /**
   * Le code de la quotité d&#39;activité de l&#39;étudiant issu de la nomenclature des Quotité d&#39;activités  Codes de la nomenclature sur 6 caractères. Préfixe des codes : QUA 
   * @return quotite
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la quotité d'activité de l'étudiant issu de la nomenclature des Quotité d'activités  Codes de la nomenclature sur 6 caractères. Préfixe des codes : QUA ")

  public String getQuotite() {
    return quotite;
  }


  public void setQuotite(String quotite) {
    this.quotite = quotite;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profession profession = (Profession) o;
    return Objects.equals(this.etudiant, profession.etudiant) &&
        Objects.equals(this.parent1, profession.parent1) &&
        Objects.equals(this.parent2, profession.parent2) &&
        Objects.equals(this.quotite, profession.quotite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(etudiant, parent1, parent2, quotite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profession {\n");
    sb.append("    etudiant: ").append(toIndentedString(etudiant)).append("\n");
    sb.append("    parent1: ").append(toIndentedString(parent1)).append("\n");
    sb.append("    parent2: ").append(toIndentedString(parent2)).append("\n");
    sb.append("    quotite: ").append(toIndentedString(quotite)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

