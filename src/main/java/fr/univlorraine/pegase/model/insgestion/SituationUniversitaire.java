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
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 2.2.0
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
import fr.univlorraine.pegase.model.insgestion.Cesure;
import fr.univlorraine.pegase.model.insgestion.Mobilite;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * SituationUniversitaire
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-09-23T16:13:33.468+02:00[Europe/Paris]")
public class SituationUniversitaire {
  public static final String SERIALIZED_NAME_CESURE = "cesure";
  @SerializedName(SERIALIZED_NAME_CESURE)
  private Cesure cesure;

  public static final String SERIALIZED_NAME_MOBILITE = "mobilite";
  @SerializedName(SERIALIZED_NAME_MOBILITE)
  private Mobilite mobilite;


  public SituationUniversitaire cesure(Cesure cesure) {
    
    this.cesure = cesure;
    return this;
  }

   /**
   * Get cesure
   * @return cesure
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Cesure getCesure() {
    return cesure;
  }


  public void setCesure(Cesure cesure) {
    this.cesure = cesure;
  }


  public SituationUniversitaire mobilite(Mobilite mobilite) {
    
    this.mobilite = mobilite;
    return this;
  }

   /**
   * Get mobilite
   * @return mobilite
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Mobilite getMobilite() {
    return mobilite;
  }


  public void setMobilite(Mobilite mobilite) {
    this.mobilite = mobilite;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SituationUniversitaire situationUniversitaire = (SituationUniversitaire) o;
    return Objects.equals(this.cesure, situationUniversitaire.cesure) &&
        Objects.equals(this.mobilite, situationUniversitaire.mobilite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cesure, mobilite);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SituationUniversitaire {\n");
    sb.append("    cesure: ").append(toIndentedString(cesure)).append("\n");
    sb.append("    mobilite: ").append(toIndentedString(mobilite)).append("\n");
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

