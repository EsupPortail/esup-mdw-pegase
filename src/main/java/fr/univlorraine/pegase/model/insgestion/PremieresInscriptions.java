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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * PremieresInscriptions
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-05-11T10:46:47.317+02:00[Europe/Paris]")
public class PremieresInscriptions {
  public static final String SERIALIZED_NAME_ANNEE_ENSEIGNEMENT_SUPERIEUR = "anneeEnseignementSuperieur";
  @SerializedName(SERIALIZED_NAME_ANNEE_ENSEIGNEMENT_SUPERIEUR)
  private String anneeEnseignementSuperieur;

  public static final String SERIALIZED_NAME_ANNEE_UNIVERSITE = "anneeUniversite";
  @SerializedName(SERIALIZED_NAME_ANNEE_UNIVERSITE)
  private String anneeUniversite;

  public static final String SERIALIZED_NAME_ANNEE_ETABLISSEMENT = "anneeEtablissement";
  @SerializedName(SERIALIZED_NAME_ANNEE_ETABLISSEMENT)
  private String anneeEtablissement;

  public PremieresInscriptions() { 
  }

  public PremieresInscriptions anneeEnseignementSuperieur(String anneeEnseignementSuperieur) {
    
    this.anneeEnseignementSuperieur = anneeEnseignementSuperieur;
    return this;
  }

   /**
   * Année de 1ère inscription dans l&#39;enseignement supérieur français au format AAAA
   * @return anneeEnseignementSuperieur
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Année de 1ère inscription dans l'enseignement supérieur français au format AAAA")

  public String getAnneeEnseignementSuperieur() {
    return anneeEnseignementSuperieur;
  }


  public void setAnneeEnseignementSuperieur(String anneeEnseignementSuperieur) {
    this.anneeEnseignementSuperieur = anneeEnseignementSuperieur;
  }


  public PremieresInscriptions anneeUniversite(String anneeUniversite) {
    
    this.anneeUniversite = anneeUniversite;
    return this;
  }

   /**
   * Année de 1ère inscription en université française au format AAAA
   * @return anneeUniversite
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Année de 1ère inscription en université française au format AAAA")

  public String getAnneeUniversite() {
    return anneeUniversite;
  }


  public void setAnneeUniversite(String anneeUniversite) {
    this.anneeUniversite = anneeUniversite;
  }


  public PremieresInscriptions anneeEtablissement(String anneeEtablissement) {
    
    this.anneeEtablissement = anneeEtablissement;
    return this;
  }

   /**
   * Année de 1ère inscription dans l&#39;établissement au format AAAA
   * @return anneeEtablissement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Année de 1ère inscription dans l'établissement au format AAAA")

  public String getAnneeEtablissement() {
    return anneeEtablissement;
  }


  public void setAnneeEtablissement(String anneeEtablissement) {
    this.anneeEtablissement = anneeEtablissement;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PremieresInscriptions premieresInscriptions = (PremieresInscriptions) o;
    return Objects.equals(this.anneeEnseignementSuperieur, premieresInscriptions.anneeEnseignementSuperieur) &&
        Objects.equals(this.anneeUniversite, premieresInscriptions.anneeUniversite) &&
        Objects.equals(this.anneeEtablissement, premieresInscriptions.anneeEtablissement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(anneeEnseignementSuperieur, anneeUniversite, anneeEtablissement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PremieresInscriptions {\n");
    sb.append("    anneeEnseignementSuperieur: ").append(toIndentedString(anneeEnseignementSuperieur)).append("\n");
    sb.append("    anneeUniversite: ").append(toIndentedString(anneeUniversite)).append("\n");
    sb.append("    anneeEtablissement: ").append(toIndentedString(anneeEtablissement)).append("\n");
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

