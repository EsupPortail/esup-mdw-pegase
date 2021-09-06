/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 2.1.2
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pai;

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
import java.time.LocalDate;

/**
 * La nationalité
 */
@ApiModel(description = "La nationalité")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-08-23T14:09:11.765+02:00[Europe/Paris]")
public class PaysNationalite {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_DATE_CONSOMMATION = "dateConsommation";
  @SerializedName(SERIALIZED_NAME_DATE_CONSOMMATION)
  private LocalDate dateConsommation;

  public static final String SERIALIZED_NAME_LIBELLE_NATIONAL = "libelleNational";
  @SerializedName(SERIALIZED_NAME_LIBELLE_NATIONAL)
  private String libelleNational;

  public static final String SERIALIZED_NAME_UNION_EURO = "unionEuro";
  @SerializedName(SERIALIZED_NAME_UNION_EURO)
  private Boolean unionEuro;

  public static final String SERIALIZED_NAME_ACCORDS = "accords";
  @SerializedName(SERIALIZED_NAME_ACCORDS)
  private Boolean accords;


  public PaysNationalite code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code du pays
   * @return code
  **/
  @ApiModelProperty(required = true, value = "Le code du pays")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public PaysNationalite dateConsommation(LocalDate dateConsommation) {
    
    this.dateConsommation = dateConsommation;
    return this;
  }

   /**
   * La date de consommation
   * @return dateConsommation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La date de consommation")

  public LocalDate getDateConsommation() {
    return dateConsommation;
  }


  public void setDateConsommation(LocalDate dateConsommation) {
    this.dateConsommation = dateConsommation;
  }


  public PaysNationalite libelleNational(String libelleNational) {
    
    this.libelleNational = libelleNational;
    return this;
  }

   /**
   * Le libellé national
   * @return libelleNational
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé national")

  public String getLibelleNational() {
    return libelleNational;
  }


  public void setLibelleNational(String libelleNational) {
    this.libelleNational = libelleNational;
  }


  public PaysNationalite unionEuro(Boolean unionEuro) {
    
    this.unionEuro = unionEuro;
    return this;
  }

   /**
   * Le témoin union européen
   * @return unionEuro
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le témoin union européen")

  public Boolean getUnionEuro() {
    return unionEuro;
  }


  public void setUnionEuro(Boolean unionEuro) {
    this.unionEuro = unionEuro;
  }


  public PaysNationalite accords(Boolean accords) {
    
    this.accords = accords;
    return this;
  }

   /**
   * Le témoin accord
   * @return accords
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le témoin accord")

  public Boolean getAccords() {
    return accords;
  }


  public void setAccords(Boolean accords) {
    this.accords = accords;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaysNationalite paysNationalite = (PaysNationalite) o;
    return Objects.equals(this.code, paysNationalite.code) &&
        Objects.equals(this.dateConsommation, paysNationalite.dateConsommation) &&
        Objects.equals(this.libelleNational, paysNationalite.libelleNational) &&
        Objects.equals(this.unionEuro, paysNationalite.unionEuro) &&
        Objects.equals(this.accords, paysNationalite.accords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, dateConsommation, libelleNational, unionEuro, accords);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaysNationalite {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    dateConsommation: ").append(toIndentedString(dateConsommation)).append("\n");
    sb.append("    libelleNational: ").append(toIndentedString(libelleNational)).append("\n");
    sb.append("    unionEuro: ").append(toIndentedString(unionEuro)).append("\n");
    sb.append("    accords: ").append(toIndentedString(accords)).append("\n");
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
