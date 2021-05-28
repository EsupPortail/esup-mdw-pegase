/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.4.0
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
import fr.univlorraine.pegase.model.insgestion.ContactComplet;
import fr.univlorraine.pegase.model.insgestion.DemandeDeContactSimple;
import fr.univlorraine.pegase.model.insgestion.VueContactMelAllOf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ContactMelComplet
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:35:31.587+02:00[Europe/Paris]")
public class ContactMelComplet extends ContactComplet {
  public static final String SERIALIZED_NAME_MAIL = "mail";
  @SerializedName(SERIALIZED_NAME_MAIL)
  private String mail;


  public ContactMelComplet mail(String mail) {
    
    this.mail = mail;
    return this;
  }

   /**
   * Adresse de messagerie électronique
   * @return mail
  **/
  @ApiModelProperty(required = true, value = "Adresse de messagerie électronique")

  public String getMail() {
    return mail;
  }


  public void setMail(String mail) {
    this.mail = mail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactMelComplet contactMelComplet = (ContactMelComplet) o;
    return Objects.equals(this.mail, contactMelComplet.mail) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mail, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactMelComplet {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
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

