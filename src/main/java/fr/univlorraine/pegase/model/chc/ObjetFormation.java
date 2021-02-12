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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ObjetFormation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-11T17:44:47.769+01:00[Europe/Paris]")
public class ObjetFormation {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_CODE_NATURE = "codeNature";
  @SerializedName(SERIALIZED_NAME_CODE_NATURE)
  private String codeNature;

  public static final String SERIALIZED_NAME_ID_DEFINITION = "idDefinition";
  @SerializedName(SERIALIZED_NAME_ID_DEFINITION)
  private String idDefinition;

  public static final String SERIALIZED_NAME_ID_IMMUABLE = "idImmuable";
  @SerializedName(SERIALIZED_NAME_ID_IMMUABLE)
  private String idImmuable;

  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;

  public static final String SERIALIZED_NAME_LIBELLE_LONG = "libelleLong";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG)
  private String libelleLong;

  public static final String SERIALIZED_NAME_MUTUALISABLE = "mutualisable";
  @SerializedName(SERIALIZED_NAME_MUTUALISABLE)
  private Boolean mutualisable;


  public ObjetFormation code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de l&#39;objet de formation - identifiant unique
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'objet de formation - identifiant unique")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public ObjetFormation codeNature(String codeNature) {
    
    this.codeNature = codeNature;
    return this;
  }

   /**
   * Le code de la nature de l&#39;objet de formation
   * @return codeNature
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la nature de l'objet de formation")

  public String getCodeNature() {
    return codeNature;
  }


  public void setCodeNature(String codeNature) {
    this.codeNature = codeNature;
  }


  public ObjetFormation idDefinition(String idDefinition) {
    
    this.idDefinition = idDefinition;
    return this;
  }

   /**
   * L’identifiant définition de l&#39;objet
   * @return idDefinition
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L’identifiant définition de l'objet")

  public String getIdDefinition() {
    return idDefinition;
  }


  public void setIdDefinition(String idDefinition) {
    this.idDefinition = idDefinition;
  }


  public ObjetFormation idImmuable(String idImmuable) {
    
    this.idImmuable = idImmuable;
    return this;
  }

   /**
   * Le code unique de la l&#39;objet de formation - identifiant unique
   * @return idImmuable
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code unique de la l'objet de formation - identifiant unique")

  public String getIdImmuable() {
    return idImmuable;
  }


  public void setIdImmuable(String idImmuable) {
    this.idImmuable = idImmuable;
  }


  public ObjetFormation libelleCourt(String libelleCourt) {
    
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


  public ObjetFormation libelleLong(String libelleLong) {
    
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


  public ObjetFormation mutualisable(Boolean mutualisable) {
    
    this.mutualisable = mutualisable;
    return this;
  }

   /**
   * Est ce que l&#39;objet formation est mutualisable?
   * @return mutualisable
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est ce que l'objet formation est mutualisable?")

  public Boolean getMutualisable() {
    return mutualisable;
  }


  public void setMutualisable(Boolean mutualisable) {
    this.mutualisable = mutualisable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetFormation objetFormation = (ObjetFormation) o;
    return Objects.equals(this.code, objetFormation.code) &&
        Objects.equals(this.codeNature, objetFormation.codeNature) &&
        Objects.equals(this.idDefinition, objetFormation.idDefinition) &&
        Objects.equals(this.idImmuable, objetFormation.idImmuable) &&
        Objects.equals(this.libelleCourt, objetFormation.libelleCourt) &&
        Objects.equals(this.libelleLong, objetFormation.libelleLong) &&
        Objects.equals(this.mutualisable, objetFormation.mutualisable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, codeNature, idDefinition, idImmuable, libelleCourt, libelleLong, mutualisable);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetFormation {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codeNature: ").append(toIndentedString(codeNature)).append("\n");
    sb.append("    idDefinition: ").append(toIndentedString(idDefinition)).append("\n");
    sb.append("    idImmuable: ").append(toIndentedString(idImmuable)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    mutualisable: ").append(toIndentedString(mutualisable)).append("\n");
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
