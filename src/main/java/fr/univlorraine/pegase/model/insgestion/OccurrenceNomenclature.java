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
import fr.univlorraine.pegase.model.insgestion.OccurrenceNomenclatureType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * une occurrence de nomenclature
 */
@ApiModel(description = "une occurrence de nomenclature")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-11T16:15:29.973+01:00[Europe/Paris]")
public class OccurrenceNomenclature {
  public static final String SERIALIZED_NAME_NOMENCLATURE = "nomenclature";
  @SerializedName(SERIALIZED_NAME_NOMENCLATURE)
  private OccurrenceNomenclatureType nomenclature;

  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE = "libelle";
  @SerializedName(SERIALIZED_NAME_LIBELLE)
  private String libelle;

  public static final String SERIALIZED_NAME_CONTEXTE_CONSOMMATION = "contexteConsommation";
  @SerializedName(SERIALIZED_NAME_CONTEXTE_CONSOMMATION)
  private String contexteConsommation;


  public OccurrenceNomenclature nomenclature(OccurrenceNomenclatureType nomenclature) {
    
    this.nomenclature = nomenclature;
    return this;
  }

   /**
   * Get nomenclature
   * @return nomenclature
  **/
  @ApiModelProperty(required = true, value = "")

  public OccurrenceNomenclatureType getNomenclature() {
    return nomenclature;
  }


  public void setNomenclature(OccurrenceNomenclatureType nomenclature) {
    this.nomenclature = nomenclature;
  }


  public OccurrenceNomenclature code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * le code de l&#39;occurrence de nomenclature
   * @return code
  **/
  @ApiModelProperty(required = true, value = "le code de l'occurrence de nomenclature")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public OccurrenceNomenclature libelle(String libelle) {
    
    this.libelle = libelle;
    return this;
  }

   /**
   * le libellé d&#39;affichage de l&#39;occurrence de nomenclature
   * @return libelle
  **/
  @ApiModelProperty(required = true, value = "le libellé d'affichage de l'occurrence de nomenclature")

  public String getLibelle() {
    return libelle;
  }


  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  public OccurrenceNomenclature contexteConsommation(String contexteConsommation) {
    
    this.contexteConsommation = contexteConsommation;
    return this;
  }

   /**
   * La date de consommation de l&#39;occurrence de nomenclature au format AAAA-MM-JJ
   * @return contexteConsommation
  **/
  @ApiModelProperty(required = true, value = "La date de consommation de l'occurrence de nomenclature au format AAAA-MM-JJ")

  public String getContexteConsommation() {
    return contexteConsommation;
  }


  public void setContexteConsommation(String contexteConsommation) {
    this.contexteConsommation = contexteConsommation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OccurrenceNomenclature occurrenceNomenclature = (OccurrenceNomenclature) o;
    return Objects.equals(this.nomenclature, occurrenceNomenclature.nomenclature) &&
        Objects.equals(this.code, occurrenceNomenclature.code) &&
        Objects.equals(this.libelle, occurrenceNomenclature.libelle) &&
        Objects.equals(this.contexteConsommation, occurrenceNomenclature.contexteConsommation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nomenclature, code, libelle, contexteConsommation);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OccurrenceNomenclature {\n");
    sb.append("    nomenclature: ").append(toIndentedString(nomenclature)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
    sb.append("    contexteConsommation: ").append(toIndentedString(contexteConsommation)).append("\n");
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

