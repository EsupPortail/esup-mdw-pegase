/**
 *
 *  ESUP-Portail ESUP-MONDOSSIERWEB-PEGASE - Copyright (c) 2021 ESUP-Portail consortium
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
/*
 * API CHC v5
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v5 
 *
 * The version of the OpenAPI document: 5.0.0
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
import fr.univlorraine.pegase.model.chc.ErreurMessageDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Erreur
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:38:20.872+02:00[Europe/Paris]")
public class Erreur {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_MESSAGE = "message";
  @SerializedName(SERIALIZED_NAME_MESSAGE)
  private String message;

  public static final String SERIALIZED_NAME_CHAMP = "champ";
  @SerializedName(SERIALIZED_NAME_CHAMP)
  private String champ;

  public static final String SERIALIZED_NAME_MESSAGE_DETAILS = "messageDetails";
  @SerializedName(SERIALIZED_NAME_MESSAGE_DETAILS)
  private ErreurMessageDetails messageDetails;


  public Erreur code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Code d&#39;erreur.  Les codes autres que ceux indiqués ci-dessous doivent être documentés dans l&#39;API.  Exemple: champ_manquant, invalide. 
   * @return code
  **/
  @ApiModelProperty(required = true, value = "Code d'erreur.  Les codes autres que ceux indiqués ci-dessous doivent être documentés dans l'API.  Exemple: champ_manquant, invalide. ")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public Erreur message(String message) {
    
    this.message = message;
    return this;
  }

   /**
   * Message d&#39;erreur à destination des utilisateurs. 
   * @return message
  **/
  @ApiModelProperty(required = true, value = "Message d'erreur à destination des utilisateurs. ")

  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }


  public Erreur champ(String champ) {
    
    this.champ = champ;
    return this;
  }

   /**
   * Champ optionnel désignant l&#39;attribut en erreur.  Il est valorisé si l&#39;erreur est dûe à un seul attribut en erreur.  Cet attribut pourra être utilisé par les applications pour placer le message d&#39;erreur à côté du champ de saisie erronné.  A noter, cet attribut est fourni en &#39;best-effort&#39;, certaines valeurs ne correpondront pas à un attribut existant. Dans ce cas, ignorer la valeur de cet attribut et placer le message d&#39;erreur en tant que message d&#39;erreur général. 
   * @return champ
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Champ optionnel désignant l'attribut en erreur.  Il est valorisé si l'erreur est dûe à un seul attribut en erreur.  Cet attribut pourra être utilisé par les applications pour placer le message d'erreur à côté du champ de saisie erronné.  A noter, cet attribut est fourni en 'best-effort', certaines valeurs ne correpondront pas à un attribut existant. Dans ce cas, ignorer la valeur de cet attribut et placer le message d'erreur en tant que message d'erreur général. ")

  public String getChamp() {
    return champ;
  }


  public void setChamp(String champ) {
    this.champ = champ;
  }


  public Erreur messageDetails(ErreurMessageDetails messageDetails) {
    
    this.messageDetails = messageDetails;
    return this;
  }

   /**
   * Get messageDetails
   * @return messageDetails
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ErreurMessageDetails getMessageDetails() {
    return messageDetails;
  }


  public void setMessageDetails(ErreurMessageDetails messageDetails) {
    this.messageDetails = messageDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Erreur erreur = (Erreur) o;
    return Objects.equals(this.code, erreur.code) &&
        Objects.equals(this.message, erreur.message) &&
        Objects.equals(this.champ, erreur.champ) &&
        Objects.equals(this.messageDetails, erreur.messageDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, champ, messageDetails);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Erreur {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    champ: ").append(toIndentedString(champ)).append("\n");
    sb.append("    messageDetails: ").append(toIndentedString(messageDetails)).append("\n");
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
