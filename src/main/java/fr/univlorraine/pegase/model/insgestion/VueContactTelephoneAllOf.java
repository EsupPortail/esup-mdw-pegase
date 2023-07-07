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

import io.swagger.annotations.ApiModelProperty;

/**
 * VueContactTelephoneAllOf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-06-06T15:07:55.881+02:00[Europe/Paris]")
public class VueContactTelephoneAllOf {
  public static final String SERIALIZED_NAME_TELEPHONE = "telephone";
  @SerializedName(SERIALIZED_NAME_TELEPHONE)
  private String telephone;

  public VueContactTelephoneAllOf() { 
  }

  public VueContactTelephoneAllOf telephone(String telephone) {
    
    this.telephone = telephone;
    return this;
  }

   /**
   * Numéro de téléphone
   * @return telephone
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Numéro de téléphone")

  public String getTelephone() {
    return telephone;
  }


  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VueContactTelephoneAllOf vueContactTelephoneAllOf = (VueContactTelephoneAllOf) o;
    return Objects.equals(this.telephone, vueContactTelephoneAllOf.telephone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(telephone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VueContactTelephoneAllOf {\n");
    sb.append("    telephone: ").append(toIndentedString(telephone)).append("\n");
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

