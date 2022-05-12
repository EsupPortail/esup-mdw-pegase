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
 * CHC v5 - Choix du cursus
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v5  ### Introduction  l’API liste l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus). Le module CHC permet d’affecter les apprenants aux Objets maquettes qu’ils doivent suivre pour une période de mise en œuvre donnée pendant leur cursus, de leur saisir des aménagements avec différentes prises en compte et de les affecter à des groupes.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l’aide d’un token jwt. Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`  Vous pouvez recevoir un des ces codes retours si vous n’êtes pas authentifié ou autorisé :  * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pû être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a aboutie à la création d'une ressource                                                                 |     | 400     | Un ou des paramètres d'entrées sont erronées                                                                        |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvé                                                                              |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers  ### Objet Maquette (OM)  Un Objet Maquette représente n'importe quel nœud d'un arbre de Formation: Formation, Objet de Formation ou Groupement.  Un objet Maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure   ### Formation  Une Formation est un arbre dont les nœuds sont des Objets de formation et dont la racine est la Formation elle-même. Pour apparaître dans le Module CHC, la formation doit être mise en œuvre, actualisée, ouverte à l’inscription et ouverte au CHC dans MOF. Il est également nécessaire qu’il y ait au moins une inscription valide sur un objet maquette de l’arbre de la formation.  ### Objet formation  Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc.(hors groupement). Pour apparaître dans le Module CHC, un objet de formation doit être ouvert au CHC dans MOF.  ### Groupement  Un groupement est une possibilité de structurer et d'organiser une formation.Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix.  ### Plage de choix  Une plage de choix permet de contraindre l’apprenant lorsque  qu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC.  ### Groupe  Un Groupe est une entité permettant de diviser une population d’étudiants ou d’identifier une population spécifique d’étudiants inscrits administrativement ou pédagogiquement  ### Composition  Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.   ### Période  Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation, du début des cours jusqu’à la délibération des jurys. Elle est le point d’entrée du module puisque le CHC se fait pour une période donnée.  ### Apprenant  Un apprenant est un usager qui suit un cursus et pour lequel le CHC va être saisi.  ### Inscription  L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant. Elle doit être vérifiée et validée par le gestionnaire.Au 25/03/21, l’inscription doit être validée pour que l’apprenant puisse arriver dans le module CHC.  ### Cursus  Le cursus est l’ensemble des Objets Maquette auxquels l’apprenant va être affecté ou pour lesquels des aménagements vont être saisis, le tout pour une période donnée.  Un cursus est défini par le code de l’apprenant et un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure.  ### Acquis capitalisé  Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances attendent un résultat capitalisable. Pour être identifié dans CHC comme acquis capitalisé, cet objet doit posséder un résultat positif obtenu sur une période passée, pour laquelle une délibération de jury a statué.  ### Chemin pédagogique  Un chemin pédagogique identifie le lien d'un Objet Maquette à un autre Objet maquette de sa descendance.  **Exemple**  ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ```  ### Affectation en masse (Dépréciée)  L'affectation en masse permet, pour une période donnée,  d’affecter ou de désaffecter des apprenants sur un Objet Maquette ouvert au choix du cursus et éventuellement sur sa descendance obligatoire.Les affectations ne sont possibles que si le père de l'objet maquette a été affecté ou acquis => contrôle du chemin pédagogique.  Les aménagements-acquis sont conservés lors de la désaffectation.  ### Affectation individuelle (Dépréciée)  L'affectation individuelle permet, pour une période et un apprenant donnés de saisir, modifier ou supprimer pour cet apprenant les affectations, les acquis et les aménagements aux Objets de formations souhaités. Un Objet de formation est soit affecté soit acquis : il ne peut pas être les deux en même temps.  Des contrôles sont effectués pour la cohérence aménagement-acquis ou aménagement-affectation ou aménagement-aucun.  Les affectations ou la saisie des aménagements ne sont possibles sur un OM que si le père a été affecté (contrôle du chemin pédagogique).  ### Paramétrage  Un paramétrage est une personnalisation de concepts spécifiques pour des processus métiers. Ils sont gérés dans le Référentiel ou dans chacun des modules. Ils peuvent être utilisés par les différents modules. Le Type d’aménagement est un paramétrage du module REF.  ## Informations techniques  Toutes les actions de cursus (affectation, désaffectation, acquis, non-acquis, type d’aménagement) de l’apprenant dans CHC seront envoyées au module COC.  Toutes les actions de cursus sont en mode upsert (créer si ça n’existe pas ou modifier si ça existe). Seule la liste des types aménagements dans l’entrée sera remplacée par ses anciennes valeurs.  ### Règles communes pour réaliser un choix de cursus  * L’affectation peut seulement se faire s’il y a une inscription valide sur l’objet maquette ou un des objets maquette de son ascendance. Les statuts de l’inscription proviennent du module INS. * Un CHC sur un Objet Maquette peut se faire uniquement si cet objet Maquette a le témoin ouvertChoixCursus à true. * Lors de la désaffectation d’un apprenant  à un Objet Maquette, l’apprenant sera également désaffecté automatiquement à tous les Objets Maquette de sa descendance. * Un CHC sur un Objet Maquette dans un groupement à plage de choix peut être fait seulement si le nombre de CHC de l’apprenant dans ce groupement ne dépasse pas le maximum autorisé (la plageMax). On ne contrôle pas la valeur mininum de plage de choix. * L’affectation/acquis/type aménagement sur un Objet Maquette mutualisé présent plusieurs fois dans un arbre ne peut être réalisée qu’une fois, c’est-à-dire que l’Objet Maquette (avec un certain code chemin) ne peut être qu’une seule fois avec une affectation / un acquis ou un type aménagement sur le même cursus * La capacité d’accueil d’un Objet Maquette n’est pas contrôlée dans l’API car non bloquante. Cela peut donc entraîner des capacités d’accueil négatives. * Les aménagements avec prise en compte Acquis et Aucun ne sont pas décomptés de la capacité d’accueil d’un Objet Maquette. * La récupération d'un acquis capitalisé empêche son affectation et celle de sa descendance. 
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
import fr.univlorraine.pegase.model.chc.FormationAllOf;
import fr.univlorraine.pegase.model.chc.ObjetFormationOuFormation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Formation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-05-11T10:49:35.950+02:00[Europe/Paris]")
public class Formation extends ObjetFormationOuFormation {
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

  public Formation() { 
    this.type = ObjetFormationOuFormation.TypeEnum.fromValue(this.getClass().getSimpleName());
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Formation formation = (Formation) o;
    return Objects.equals(this.codeTypeDiplome, formation.codeTypeDiplome) &&
        Objects.equals(this.codeCursus, formation.codeCursus) &&
        Objects.equals(this.codeNiveauFormation, formation.codeNiveauFormation) &&
        Objects.equals(this.codeNiveauDiplome, formation.codeNiveauDiplome) &&
        Objects.equals(this.codeNatureDiplome, formation.codeNatureDiplome) &&
        Objects.equals(this.codeChampFormation, formation.codeChampFormation) &&
        Objects.equals(this.codeDomaineFormation, formation.codeDomaineFormation) &&
        Objects.equals(this.codeMention, formation.codeMention) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeTypeDiplome, codeCursus, codeNiveauFormation, codeNiveauDiplome, codeNatureDiplome, codeChampFormation, codeDomaineFormation, codeMention, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Formation {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

