/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 2.2.0
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
import fr.univlorraine.pegase.model.insgestion.Nomenclature;
import fr.univlorraine.pegase.model.insgestion.RegimeInscriptionAllOf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * RegimeInscription
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-09-23T16:13:33.468+02:00[Europe/Paris]")
public class RegimeInscription extends Nomenclature {
  public static final String SERIALIZED_NAME_TEMOIN_C_V_E_C = "temoinCVEC";
  @SerializedName(SERIALIZED_NAME_TEMOIN_C_V_E_C)
  private Boolean temoinCVEC;

  public static final String SERIALIZED_NAME_FINANCEMENT_POSSIBLE = "financementPossible";
  @SerializedName(SERIALIZED_NAME_FINANCEMENT_POSSIBLE)
  private Boolean financementPossible;

  public static final String SERIALIZED_NAME_DROIT_A_BOURSE = "droitABourse";
  @SerializedName(SERIALIZED_NAME_DROIT_A_BOURSE)
  private Boolean droitABourse;


  public RegimeInscription temoinCVEC(Boolean temoinCVEC) {
    
    this.temoinCVEC = temoinCVEC;
    return this;
  }

   /**
   * Régime d&#39;inscription nécessitant le paiement de la CVEC
   * @return temoinCVEC
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Régime d'inscription nécessitant le paiement de la CVEC")

  public Boolean getTemoinCVEC() {
    return temoinCVEC;
  }


  public void setTemoinCVEC(Boolean temoinCVEC) {
    this.temoinCVEC = temoinCVEC;
  }


  public RegimeInscription financementPossible(Boolean financementPossible) {
    
    this.financementPossible = financementPossible;
    return this;
  }

   /**
   * Régime d&#39;inscription permettant un financement
   * @return financementPossible
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Régime d'inscription permettant un financement")

  public Boolean getFinancementPossible() {
    return financementPossible;
  }


  public void setFinancementPossible(Boolean financementPossible) {
    this.financementPossible = financementPossible;
  }


  public RegimeInscription droitABourse(Boolean droitABourse) {
    
    this.droitABourse = droitABourse;
    return this;
  }

   /**
   * Régime d&#39;inscription donnant droit à une bourse
   * @return droitABourse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Régime d'inscription donnant droit à une bourse")

  public Boolean getDroitABourse() {
    return droitABourse;
  }


  public void setDroitABourse(Boolean droitABourse) {
    this.droitABourse = droitABourse;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegimeInscription regimeInscription = (RegimeInscription) o;
    return Objects.equals(this.temoinCVEC, regimeInscription.temoinCVEC) &&
        Objects.equals(this.financementPossible, regimeInscription.financementPossible) &&
        Objects.equals(this.droitABourse, regimeInscription.droitABourse) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(temoinCVEC, financementPossible, droitABourse, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegimeInscription {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    temoinCVEC: ").append(toIndentedString(temoinCVEC)).append("\n");
    sb.append("    financementPossible: ").append(toIndentedString(financementPossible)).append("\n");
    sb.append("    droitABourse: ").append(toIndentedString(droitABourse)).append("\n");
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

