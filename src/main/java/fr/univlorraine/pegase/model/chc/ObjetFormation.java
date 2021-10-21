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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ObjetFormation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:38:20.872+02:00[Europe/Paris]")
public class ObjetFormation {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;

  public static final String SERIALIZED_NAME_LIBELLE_LONG = "libelleLong";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG)
  private String libelleLong;

  public static final String SERIALIZED_NAME_ID_IMMUABLE = "idImmuable";
  @SerializedName(SERIALIZED_NAME_ID_IMMUABLE)
  private String idImmuable;

  public static final String SERIALIZED_NAME_CODE_NATURE = "codeNature";
  @SerializedName(SERIALIZED_NAME_CODE_NATURE)
  private String codeNature;

  public static final String SERIALIZED_NAME_ID_DEFINITION = "idDefinition";
  @SerializedName(SERIALIZED_NAME_ID_DEFINITION)
  private String idDefinition;

  public static final String SERIALIZED_NAME_MUTUALISE = "mutualise";
  @SerializedName(SERIALIZED_NAME_MUTUALISE)
  private Boolean mutualise;

  public static final String SERIALIZED_NAME_STAGE = "stage";
  @SerializedName(SERIALIZED_NAME_STAGE)
  private Boolean stage;


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


  public ObjetFormation mutualise(Boolean mutualise) {
    
    this.mutualise = mutualise;
    return this;
  }

   /**
   * Est ce que l&#39;objet formation est mutualise?
   * @return mutualise
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est ce que l'objet formation est mutualise?")

  public Boolean getMutualise() {
    return mutualise;
  }


  public void setMutualise(Boolean mutualise) {
    this.mutualise = mutualise;
  }


  public ObjetFormation stage(Boolean stage) {
    
    this.stage = stage;
    return this;
  }

   /**
   * Est-ce que l’objet de formation est un stage?
   * @return stage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est-ce que l’objet de formation est un stage?")

  public Boolean getStage() {
    return stage;
  }


  public void setStage(Boolean stage) {
    this.stage = stage;
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
        Objects.equals(this.libelleCourt, objetFormation.libelleCourt) &&
        Objects.equals(this.libelleLong, objetFormation.libelleLong) &&
        Objects.equals(this.idImmuable, objetFormation.idImmuable) &&
        Objects.equals(this.codeNature, objetFormation.codeNature) &&
        Objects.equals(this.idDefinition, objetFormation.idDefinition) &&
        Objects.equals(this.mutualise, objetFormation.mutualise) &&
        Objects.equals(this.stage, objetFormation.stage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong, idImmuable, codeNature, idDefinition, mutualise, stage);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetFormation {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    idImmuable: ").append(toIndentedString(idImmuable)).append("\n");
    sb.append("    codeNature: ").append(toIndentedString(codeNature)).append("\n");
    sb.append("    idDefinition: ").append(toIndentedString(idDefinition)).append("\n");
    sb.append("    mutualise: ").append(toIndentedString(mutualise)).append("\n");
    sb.append("    stage: ").append(toIndentedString(stage)).append("\n");
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

