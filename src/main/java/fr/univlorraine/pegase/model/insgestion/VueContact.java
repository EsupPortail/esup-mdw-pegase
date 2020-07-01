/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.0.0
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
 * VueContact
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-06-30T15:25:34.956+02:00[Europe/Paris]")

public class VueContact {
  /**
   * type de contact
   */
  @JsonAdapter(CanalCommunicationEnum.Adapter.class)
  public enum CanalCommunicationEnum {
    VUECONTACTADRESSE("VueContactAdresse"),
    
    VUECONTACTMEL("VueContactMel"),
    
    VUECONTACTTELEPHONE("VueContactTelephone");

    private String value;

    CanalCommunicationEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static CanalCommunicationEnum fromValue(String value) {
      for (CanalCommunicationEnum b : CanalCommunicationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<CanalCommunicationEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CanalCommunicationEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CanalCommunicationEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return CanalCommunicationEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_CANAL_COMMUNICATION = "canalCommunication";
  @SerializedName(SERIALIZED_NAME_CANAL_COMMUNICATION)
  protected CanalCommunicationEnum canalCommunication;

  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE = "libelle";
  @SerializedName(SERIALIZED_NAME_LIBELLE)
  private String libelle;

  public static final String SERIALIZED_NAME_PROPRIETAIRE = "proprietaire";
  @SerializedName(SERIALIZED_NAME_PROPRIETAIRE)
  private String proprietaire;

  public VueContact() {
	    this.canalCommunication = CanalCommunicationEnum.fromValue(this.getClass().getSimpleName());
  }

  public VueContact canalCommunication(CanalCommunicationEnum canalCommunication) {
    
    this.canalCommunication = canalCommunication;
    return this;
  }

   /**
   * type de contact
   * @return canalCommunication
  **/
  @ApiModelProperty(required = true, value = "type de contact")

  public CanalCommunicationEnum getCanalCommunication() {
    return canalCommunication;
  }


  public void setCanalCommunication(CanalCommunicationEnum canalCommunication) {
    this.canalCommunication = canalCommunication;
  }


  public VueContact code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Code de la demande de contact
   * @return code
  **/
  @ApiModelProperty(required = true, value = "Code de la demande de contact")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public VueContact libelle(String libelle) {
    
    this.libelle = libelle;
    return this;
  }

   /**
   * Libellé d&#39;affichage de la demande de contact
   * @return libelle
  **/
  @ApiModelProperty(required = true, value = "Libellé d'affichage de la demande de contact")

  public String getLibelle() {
    return libelle;
  }


  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  public VueContact proprietaire(String proprietaire) {
    
    this.proprietaire = proprietaire;
    return this;
  }

   /**
   * Personne à contacter si différent de l&#39;apprenant
   * @return proprietaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Personne à contacter si différent de l'apprenant")

  public String getProprietaire() {
    return proprietaire;
  }


  public void setProprietaire(String proprietaire) {
    this.proprietaire = proprietaire;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VueContact vueContact = (VueContact) o;
    return Objects.equals(this.canalCommunication, vueContact.canalCommunication) &&
        Objects.equals(this.code, vueContact.code) &&
        Objects.equals(this.libelle, vueContact.libelle) &&
        Objects.equals(this.proprietaire, vueContact.proprietaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canalCommunication, code, libelle, proprietaire);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VueContact {\n");
    sb.append("    canalCommunication: ").append(toIndentedString(canalCommunication)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
    sb.append("    proprietaire: ").append(toIndentedString(proprietaire)).append("\n");
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

