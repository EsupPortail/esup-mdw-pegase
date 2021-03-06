/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.3.0
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
 * Objet avec code, libelleCourt et libelleLong
 */
@ApiModel(description = "Objet avec code, libelleCourt et libelleLong")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-11T16:15:29.973+01:00[Europe/Paris]")

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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetAvecLibelle objetAvecLibelle = (ObjetAvecLibelle) o;
    return  Objects.equals(this.est1, objetAvecLibelle.est1) &&
    	Objects.equals(this.code, objetAvecLibelle.code) &&
        Objects.equals(this.libelleCourt, objetAvecLibelle.libelleCourt) &&
        Objects.equals(this.libelleLong, objetAvecLibelle.libelleLong);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

