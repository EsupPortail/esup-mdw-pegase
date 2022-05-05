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
 * Formation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:38:20.872+02:00[Europe/Paris]")
public class Formation {
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

  public static final String SERIALIZED_NAME_CODE_TYPE_DIPLOME = "codeTypeDiplome";
  @SerializedName(SERIALIZED_NAME_CODE_TYPE_DIPLOME)
  private String codeTypeDiplome;

  public static final String SERIALIZED_NAME_CODE_CURSUS = "codeCursus";
  @SerializedName(SERIALIZED_NAME_CODE_CURSUS)
  private String codeCursus;

  public static final String SERIALIZED_NAME_CODE_NIVEAU_FORMATION = "codeNiveauFormation";
  @SerializedName(SERIALIZED_NAME_CODE_NIVEAU_FORMATION)
  private String codeNiveauFormation;

  public static final String SERIALIZED_NAME_CODE_NIVEAU_DIPLOME = "codeNiveauDiplome";
  @SerializedName(SERIALIZED_NAME_CODE_NIVEAU_DIPLOME)
  private String codeNiveauDiplome;

  public static final String SERIALIZED_NAME_CODE_NATURE_DIPLOME = "codeNatureDiplome";
  @SerializedName(SERIALIZED_NAME_CODE_NATURE_DIPLOME)
  private String codeNatureDiplome;

  public static final String SERIALIZED_NAME_CODE_CHAMP_FORMATION = "codeChampFormation";
  @SerializedName(SERIALIZED_NAME_CODE_CHAMP_FORMATION)
  private String codeChampFormation;

  public static final String SERIALIZED_NAME_CODE_DOMAINE_FORMATION = "codeDomaineFormation";
  @SerializedName(SERIALIZED_NAME_CODE_DOMAINE_FORMATION)
  private String codeDomaineFormation;

  public static final String SERIALIZED_NAME_CODE_MENTION = "codeMention";
  @SerializedName(SERIALIZED_NAME_CODE_MENTION)
  private String codeMention;


  public Formation code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de la formation - identifiant unique
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la formation - identifiant unique")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public Formation libelleCourt(String libelleCourt) {
    
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


  public Formation libelleLong(String libelleLong) {
    
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


  public Formation idImmuable(String idImmuable) {
    
    this.idImmuable = idImmuable;
    return this;
  }

   /**
   * Le code unique de la formation - identifiant unique
   * @return idImmuable
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code unique de la formation - identifiant unique")

  public String getIdImmuable() {
    return idImmuable;
  }


  public void setIdImmuable(String idImmuable) {
    this.idImmuable = idImmuable;
  }


  public Formation codeTypeDiplome(String codeTypeDiplome) {
    
    this.codeTypeDiplome = codeTypeDiplome;
    return this;
  }

   /**
   * Le code du type de diplome
   * @return codeTypeDiplome
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du type de diplome")

  public String getCodeTypeDiplome() {
    return codeTypeDiplome;
  }


  public void setCodeTypeDiplome(String codeTypeDiplome) {
    this.codeTypeDiplome = codeTypeDiplome;
  }


  public Formation codeCursus(String codeCursus) {
    
    this.codeCursus = codeCursus;
    return this;
  }

   /**
   * Le code du cursus
   * @return codeCursus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du cursus")

  public String getCodeCursus() {
    return codeCursus;
  }


  public void setCodeCursus(String codeCursus) {
    this.codeCursus = codeCursus;
  }


  public Formation codeNiveauFormation(String codeNiveauFormation) {
    
    this.codeNiveauFormation = codeNiveauFormation;
    return this;
  }

   /**
   * Le code du niveau de formation
   * @return codeNiveauFormation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du niveau de formation")

  public String getCodeNiveauFormation() {
    return codeNiveauFormation;
  }


  public void setCodeNiveauFormation(String codeNiveauFormation) {
    this.codeNiveauFormation = codeNiveauFormation;
  }


  public Formation codeNiveauDiplome(String codeNiveauDiplome) {
    
    this.codeNiveauDiplome = codeNiveauDiplome;
    return this;
  }

   /**
   * Le code du niveau de diplome
   * @return codeNiveauDiplome
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du niveau de diplome")

  public String getCodeNiveauDiplome() {
    return codeNiveauDiplome;
  }


  public void setCodeNiveauDiplome(String codeNiveauDiplome) {
    this.codeNiveauDiplome = codeNiveauDiplome;
  }


  public Formation codeNatureDiplome(String codeNatureDiplome) {
    
    this.codeNatureDiplome = codeNatureDiplome;
    return this;
  }

   /**
   * Le code de la nature du diplome
   * @return codeNatureDiplome
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la nature du diplome")

  public String getCodeNatureDiplome() {
    return codeNatureDiplome;
  }


  public void setCodeNatureDiplome(String codeNatureDiplome) {
    this.codeNatureDiplome = codeNatureDiplome;
  }


  public Formation codeChampFormation(String codeChampFormation) {
    
    this.codeChampFormation = codeChampFormation;
    return this;
  }

   /**
   * Le code du champ de formation
   * @return codeChampFormation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du champ de formation")

  public String getCodeChampFormation() {
    return codeChampFormation;
  }


  public void setCodeChampFormation(String codeChampFormation) {
    this.codeChampFormation = codeChampFormation;
  }


  public Formation codeDomaineFormation(String codeDomaineFormation) {
    
    this.codeDomaineFormation = codeDomaineFormation;
    return this;
  }

   /**
   * Le code du domaine de formation
   * @return codeDomaineFormation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du domaine de formation")

  public String getCodeDomaineFormation() {
    return codeDomaineFormation;
  }


  public void setCodeDomaineFormation(String codeDomaineFormation) {
    this.codeDomaineFormation = codeDomaineFormation;
  }


  public Formation codeMention(String codeMention) {
    
    this.codeMention = codeMention;
    return this;
  }

   /**
   * Le code mention de la formation
   * @return codeMention
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code mention de la formation")

  public String getCodeMention() {
    return codeMention;
  }


  public void setCodeMention(String codeMention) {
    this.codeMention = codeMention;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Formation formation = (Formation) o;
    return Objects.equals(this.code, formation.code) &&
        Objects.equals(this.libelleCourt, formation.libelleCourt) &&
        Objects.equals(this.libelleLong, formation.libelleLong) &&
        Objects.equals(this.idImmuable, formation.idImmuable) &&
        Objects.equals(this.codeTypeDiplome, formation.codeTypeDiplome) &&
        Objects.equals(this.codeCursus, formation.codeCursus) &&
        Objects.equals(this.codeNiveauFormation, formation.codeNiveauFormation) &&
        Objects.equals(this.codeNiveauDiplome, formation.codeNiveauDiplome) &&
        Objects.equals(this.codeNatureDiplome, formation.codeNatureDiplome) &&
        Objects.equals(this.codeChampFormation, formation.codeChampFormation) &&
        Objects.equals(this.codeDomaineFormation, formation.codeDomaineFormation) &&
        Objects.equals(this.codeMention, formation.codeMention);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong, idImmuable, codeTypeDiplome, codeCursus, codeNiveauFormation, codeNiveauDiplome, codeNatureDiplome, codeChampFormation, codeDomaineFormation, codeMention);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Formation {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    idImmuable: ").append(toIndentedString(idImmuable)).append("\n");
    sb.append("    codeTypeDiplome: ").append(toIndentedString(codeTypeDiplome)).append("\n");
    sb.append("    codeCursus: ").append(toIndentedString(codeCursus)).append("\n");
    sb.append("    codeNiveauFormation: ").append(toIndentedString(codeNiveauFormation)).append("\n");
    sb.append("    codeNiveauDiplome: ").append(toIndentedString(codeNiveauDiplome)).append("\n");
    sb.append("    codeNatureDiplome: ").append(toIndentedString(codeNatureDiplome)).append("\n");
    sb.append("    codeChampFormation: ").append(toIndentedString(codeChampFormation)).append("\n");
    sb.append("    codeDomaineFormation: ").append(toIndentedString(codeDomaineFormation)).append("\n");
    sb.append("    codeMention: ").append(toIndentedString(codeMention)).append("\n");
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

