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
 * The version of the OpenAPI document: 16.0.0
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
import fr.univlorraine.pegase.model.insgestion.Calendrier;
import fr.univlorraine.pegase.model.insgestion.Periode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Objet avec dates de début et de fin
 */
@ApiModel(description = "Objet avec dates de début et de fin")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-05-11T10:46:47.317+02:00[Europe/Paris]")
public class ObjetAvecDates {
  public static final String SERIALIZED_NAME_EST1 = "est1";
  @SerializedName(SERIALIZED_NAME_EST1)
  protected String est1;

  public static final String SERIALIZED_NAME_DATE_DEBUT = "dateDebut";
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT)
  private String dateDebut;

  public static final String SERIALIZED_NAME_DATE_FIN = "dateFin";
  @SerializedName(SERIALIZED_NAME_DATE_FIN)
  private String dateFin;

  public ObjetAvecDates() { 
    this.est1 = this.getClass().getSimpleName();
  }

  public ObjetAvecDates est1(String est1) {
    
    this.est1 = est1;
    return this;
  }

   /**
   * Get est1
   * @return est1
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getEst1() {
    return est1;
  }


  public void setEst1(String est1) {
    this.est1 = est1;
  }


  public ObjetAvecDates dateDebut(String dateDebut) {
    
    this.dateDebut = dateDebut;
    return this;
  }

   /**
   * date de début de validité au format AAAA-MM-JJ
   * @return dateDebut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "date de début de validité au format AAAA-MM-JJ")

  public String getDateDebut() {
    return dateDebut;
  }


  public void setDateDebut(String dateDebut) {
    this.dateDebut = dateDebut;
  }


  public ObjetAvecDates dateFin(String dateFin) {
    
    this.dateFin = dateFin;
    return this;
  }

   /**
   * date de fin de validité au format AAAA-MM-JJ
   * @return dateFin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "date de fin de validité au format AAAA-MM-JJ")

  public String getDateFin() {
    return dateFin;
  }


  public void setDateFin(String dateFin) {
    this.dateFin = dateFin;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetAvecDates objetAvecDates = (ObjetAvecDates) o;
    return Objects.equals(this.est1, objetAvecDates.est1) &&
        Objects.equals(this.dateDebut, objetAvecDates.dateDebut) &&
        Objects.equals(this.dateFin, objetAvecDates.dateFin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(est1, dateDebut, dateFin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetAvecDates {\n");
    sb.append("    est1: ").append(toIndentedString(est1)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

