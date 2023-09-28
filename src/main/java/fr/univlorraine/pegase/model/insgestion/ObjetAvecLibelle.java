/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 20.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insgestion;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Objet avec code, libelleCourt et libelleLong
 */
@ApiModel(description = "Objet avec code, libelleCourt et libelleLong")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-07-12T11:53:29.862+02:00[Europe/Paris]")
public class ObjetAvecLibelle {
  public static final String SERIALIZED_NAME_EST1 = "est1";
  @SerializedName(SERIALIZED_NAME_EST1)
  protected String est1;

  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;

  public static final String SERIALIZED_NAME_LIBELLE_LONG = "libelleLong";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG)
  private String libelleLong;

  public ObjetAvecLibelle() { 
    this.est1 = this.getClass().getSimpleName();
  }

  public ObjetAvecLibelle est1(String est1) {
    
    this.est1 = est1;
    return this;
  }

   /**
   * Get est1
   * @return est1
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getEst1() {
    return est1;
  }


  public void setEst1(String est1) {
    this.est1 = est1;
  }


  public ObjetAvecLibelle code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de l&#39;objet
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'objet")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public ObjetAvecLibelle libelleCourt(String libelleCourt) {
    
    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Le libellé court
   * @return libelleCourt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé court")

  public String getLibelleCourt() {
    return libelleCourt;
  }


  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public ObjetAvecLibelle libelleLong(String libelleLong) {
    
    this.libelleLong = libelleLong;
    return this;
  }

   /**
   * Le libellé long
   * @return libelleLong
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé long")

  public String getLibelleLong() {
    return libelleLong;
  }


  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetAvecLibelle objetAvecLibelle = (ObjetAvecLibelle) o;
    return Objects.equals(this.est1, objetAvecLibelle.est1) &&
        Objects.equals(this.code, objetAvecLibelle.code) &&
        Objects.equals(this.libelleCourt, objetAvecLibelle.libelleCourt) &&
        Objects.equals(this.libelleLong, objetAvecLibelle.libelleLong);
  }

  @Override
  public int hashCode() {
    return Objects.hash(est1, code, libelleCourt, libelleLong);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetAvecLibelle {\n");
    sb.append("    est1: ").append(toIndentedString(est1)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
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

