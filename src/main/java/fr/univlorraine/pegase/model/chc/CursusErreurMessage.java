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
import fr.univlorraine.pegase.model.chc.ObjetMaquetteKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * CursusErreurMessage
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:38:20.872+02:00[Europe/Paris]")
public class CursusErreurMessage {
  public static final String SERIALIZED_NAME_APPRENANT = "apprenant";
  @SerializedName(SERIALIZED_NAME_APPRENANT)
  private String apprenant;

  public static final String SERIALIZED_NAME_OBJET_MAQUETTE = "objetMaquette";
  @SerializedName(SERIALIZED_NAME_OBJET_MAQUETTE)
  private ObjetMaquetteKey objetMaquette;

  public static final String SERIALIZED_NAME_TYPE_ERREUR = "typeErreur";
  @SerializedName(SERIALIZED_NAME_TYPE_ERREUR)
  private String typeErreur;

  public static final String SERIALIZED_NAME_MESSAGE_ERREUR = "messageErreur";
  @SerializedName(SERIALIZED_NAME_MESSAGE_ERREUR)
  private String messageErreur;


  public CursusErreurMessage apprenant(String apprenant) {
    
    this.apprenant = apprenant;
    return this;
  }

   /**
   * le code de l&#39;apprenant
   * @return apprenant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le code de l'apprenant")

  public String getApprenant() {
    return apprenant;
  }


  public void setApprenant(String apprenant) {
    this.apprenant = apprenant;
  }


  public CursusErreurMessage objetMaquette(ObjetMaquetteKey objetMaquette) {
    
    this.objetMaquette = objetMaquette;
    return this;
  }

   /**
   * Get objetMaquette
   * @return objetMaquette
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ObjetMaquetteKey getObjetMaquette() {
    return objetMaquette;
  }


  public void setObjetMaquette(ObjetMaquetteKey objetMaquette) {
    this.objetMaquette = objetMaquette;
  }


  public CursusErreurMessage typeErreur(String typeErreur) {
    
    this.typeErreur = typeErreur;
    return this;
  }

   /**
   * le type d&#39;erreur
   * @return typeErreur
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le type d'erreur")

  public String getTypeErreur() {
    return typeErreur;
  }


  public void setTypeErreur(String typeErreur) {
    this.typeErreur = typeErreur;
  }


  public CursusErreurMessage messageErreur(String messageErreur) {
    
    this.messageErreur = messageErreur;
    return this;
  }

   /**
   * le message d&#39;erreur
   * @return messageErreur
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le message d'erreur")

  public String getMessageErreur() {
    return messageErreur;
  }


  public void setMessageErreur(String messageErreur) {
    this.messageErreur = messageErreur;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CursusErreurMessage cursusErreurMessage = (CursusErreurMessage) o;
    return Objects.equals(this.apprenant, cursusErreurMessage.apprenant) &&
        Objects.equals(this.objetMaquette, cursusErreurMessage.objetMaquette) &&
        Objects.equals(this.typeErreur, cursusErreurMessage.typeErreur) &&
        Objects.equals(this.messageErreur, cursusErreurMessage.messageErreur);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apprenant, objetMaquette, typeErreur, messageErreur);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CursusErreurMessage {\n");
    sb.append("    apprenant: ").append(toIndentedString(apprenant)).append("\n");
    sb.append("    objetMaquette: ").append(toIndentedString(objetMaquette)).append("\n");
    sb.append("    typeErreur: ").append(toIndentedString(typeErreur)).append("\n");
    sb.append("    messageErreur: ").append(toIndentedString(messageErreur)).append("\n");
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

