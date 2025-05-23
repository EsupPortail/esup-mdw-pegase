/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 27.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.pai.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.pai.model.DroitTarification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * SuiviFormation
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:28:00.271684800+02:00[Europe/Paris]")
public class SuiviFormation {
  public static final String SERIALIZED_NAME_TARIFICATION = "tarification";
  public static final String SERIALIZED_NAME_TYPE = "type";
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  public static final String SERIALIZED_NAME_ANNEE_UNIVERSITAIRE = "anneeUniversitaire";
  public static final String SERIALIZED_NAME_PERIODE = "periode";
  public static final String SERIALIZED_NAME_CODES_CHEMIN = "codesChemin";
  public static final String SERIALIZED_NAME_STRUCTURE_BUDGETAIRE = "structureBudgetaire";
  public static final String SERIALIZED_NAME_TEMOIN_JAMAIS_OUVERTE_INSCRIPTION = "temoinJamaisOuverteInscription";
  @SerializedName(SERIALIZED_NAME_TARIFICATION)
  private DroitTarification tarification;
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;
  @SerializedName(SERIALIZED_NAME_ANNEE_UNIVERSITAIRE)
  private Integer anneeUniversitaire;
  @SerializedName(SERIALIZED_NAME_PERIODE)
  private String periode;
  @SerializedName(SERIALIZED_NAME_CODES_CHEMIN)
  private String codesChemin;
  @SerializedName(SERIALIZED_NAME_STRUCTURE_BUDGETAIRE)
  private String structureBudgetaire;
  @SerializedName(SERIALIZED_NAME_TEMOIN_JAMAIS_OUVERTE_INSCRIPTION)
  private Boolean temoinJamaisOuverteInscription;

  public SuiviFormation() { 
  }

  public SuiviFormation tarification(DroitTarification tarification) {
    
    this.tarification = tarification;
    return this;
  }

   /**
   * Get tarification
   * @return tarification
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DroitTarification getTarification() {
    return tarification;
  }


  public void setTarification(DroitTarification tarification) {
    this.tarification = tarification;
  }


  public SuiviFormation type(String type) {
    
    this.type = type;
    return this;
  }

   /**
   * Le type
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le type")

  public String getType() {
    return type;
  }


  public void setType(String type) {
    this.type = type;
  }


  public SuiviFormation code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code du Pia
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du Pia")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public SuiviFormation libelleCourt(String libelleCourt) {
    
    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Le libellé court du Pia
   * @return libelleCourt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé court du Pia")

  public String getLibelleCourt() {
    return libelleCourt;
  }


  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public SuiviFormation anneeUniversitaire(Integer anneeUniversitaire) {
    
    this.anneeUniversitaire = anneeUniversitaire;
    return this;
  }

   /**
   * L&#39;année universitaire
   * @return anneeUniversitaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'année universitaire")

  public Integer getAnneeUniversitaire() {
    return anneeUniversitaire;
  }


  public void setAnneeUniversitaire(Integer anneeUniversitaire) {
    this.anneeUniversitaire = anneeUniversitaire;
  }


  public SuiviFormation periode(String periode) {
    
    this.periode = periode;
    return this;
  }

   /**
   * La période
   * @return periode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La période")

  public String getPeriode() {
    return periode;
  }


  public void setPeriode(String periode) {
    this.periode = periode;
  }


  public SuiviFormation codesChemin(String codesChemin) {
    
    this.codesChemin = codesChemin;
    return this;
  }

   /**
   * La liste des codes chemins séparés par des chevrons
   * @return codesChemin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La liste des codes chemins séparés par des chevrons")

  public String getCodesChemin() {
    return codesChemin;
  }


  public void setCodesChemin(String codesChemin) {
    this.codesChemin = codesChemin;
  }


  public SuiviFormation structureBudgetaire(String structureBudgetaire) {
    
    this.structureBudgetaire = structureBudgetaire;
    return this;
  }

   /**
   * La structure budgétaire définie dans la formation
   * @return structureBudgetaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La structure budgétaire définie dans la formation")

  public String getStructureBudgetaire() {
    return structureBudgetaire;
  }


  public void setStructureBudgetaire(String structureBudgetaire) {
    this.structureBudgetaire = structureBudgetaire;
  }


  public SuiviFormation temoinJamaisOuverteInscription(Boolean temoinJamaisOuverteInscription) {
    
    this.temoinJamaisOuverteInscription = temoinJamaisOuverteInscription;
    return this;
  }

   /**
   * Formation jamais ouverte à l&#39;inscription
   * @return temoinJamaisOuverteInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Formation jamais ouverte à l'inscription")

  public Boolean getTemoinJamaisOuverteInscription() {
    return temoinJamaisOuverteInscription;
  }


  public void setTemoinJamaisOuverteInscription(Boolean temoinJamaisOuverteInscription) {
    this.temoinJamaisOuverteInscription = temoinJamaisOuverteInscription;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuiviFormation suiviFormation = (SuiviFormation) o;
    return Objects.equals(this.tarification, suiviFormation.tarification) &&
        Objects.equals(this.type, suiviFormation.type) &&
        Objects.equals(this.code, suiviFormation.code) &&
        Objects.equals(this.libelleCourt, suiviFormation.libelleCourt) &&
        Objects.equals(this.anneeUniversitaire, suiviFormation.anneeUniversitaire) &&
        Objects.equals(this.periode, suiviFormation.periode) &&
        Objects.equals(this.codesChemin, suiviFormation.codesChemin) &&
        Objects.equals(this.structureBudgetaire, suiviFormation.structureBudgetaire) &&
        Objects.equals(this.temoinJamaisOuverteInscription, suiviFormation.temoinJamaisOuverteInscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tarification, type, code, libelleCourt, anneeUniversitaire, periode, codesChemin, structureBudgetaire, temoinJamaisOuverteInscription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuiviFormation {\n");
    sb.append("    tarification: ").append(toIndentedString(tarification)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    anneeUniversitaire: ").append(toIndentedString(anneeUniversitaire)).append("\n");
    sb.append("    periode: ").append(toIndentedString(periode)).append("\n");
    sb.append("    codesChemin: ").append(toIndentedString(codesChemin)).append("\n");
    sb.append("    structureBudgetaire: ").append(toIndentedString(structureBudgetaire)).append("\n");
    sb.append("    temoinJamaisOuverteInscription: ").append(toIndentedString(temoinJamaisOuverteInscription)).append("\n");
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

