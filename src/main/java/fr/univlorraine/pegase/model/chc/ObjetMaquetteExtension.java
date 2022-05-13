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
import fr.univlorraine.pegase.model.chc.Formation;
import fr.univlorraine.pegase.model.chc.Groupement;
import fr.univlorraine.pegase.model.chc.ObjetFormation;
import fr.univlorraine.pegase.model.chc.Periode;
import fr.univlorraine.pegase.model.chc.RegimeInscription;
import fr.univlorraine.pegase.model.chc.TypeAmenagement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ObjetMaquetteExtension
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-05-11T10:49:35.950+02:00[Europe/Paris]")
public class ObjetMaquetteExtension {
  public static final String SERIALIZED_NAME_CODE_CHEMIN = "codeChemin";
  @SerializedName(SERIALIZED_NAME_CODE_CHEMIN)
  private String codeChemin;

  public static final String SERIALIZED_NAME_CODE_PERIODE = "codePeriode";
  @SerializedName(SERIALIZED_NAME_CODE_PERIODE)
  private String codePeriode;

  public static final String SERIALIZED_NAME_CODE_STRUCTURE = "codeStructure";
  @SerializedName(SERIALIZED_NAME_CODE_STRUCTURE)
  private String codeStructure;

  public static final String SERIALIZED_NAME_CODE_TYPE = "codeType";
  @SerializedName(SERIALIZED_NAME_CODE_TYPE)
  private String codeType;

  public static final String SERIALIZED_NAME_CODE_CATEGORIE = "codeCategorie";
  @SerializedName(SERIALIZED_NAME_CODE_CATEGORIE)
  private String codeCategorie;

  public static final String SERIALIZED_NAME_CAPACITE_ACCUEIL = "capaciteAccueil";
  @SerializedName(SERIALIZED_NAME_CAPACITE_ACCUEIL)
  private Integer capaciteAccueil;

  public static final String SERIALIZED_NAME_CARACTERE_OBLIGATOIRE = "caractereObligatoire";
  @SerializedName(SERIALIZED_NAME_CARACTERE_OBLIGATOIRE)
  private Boolean caractereObligatoire;

  public static final String SERIALIZED_NAME_VERSION_MAQUETTE = "versionMaquette";
  @SerializedName(SERIALIZED_NAME_VERSION_MAQUETTE)
  private Integer versionMaquette;

  public static final String SERIALIZED_NAME_OUVERTE_CHOIX_CURSUS = "ouverteChoixCursus";
  @SerializedName(SERIALIZED_NAME_OUVERTE_CHOIX_CURSUS)
  private Boolean ouverteChoixCursus;

  public static final String SERIALIZED_NAME_CREDIT_ECTS = "creditEcts";
  @SerializedName(SERIALIZED_NAME_CREDIT_ECTS)
  private Double creditEcts;

  public static final String SERIALIZED_NAME_TEMOIN_PLAGE_CHOIX = "temoinPlageChoix";
  @SerializedName(SERIALIZED_NAME_TEMOIN_PLAGE_CHOIX)
  private Boolean temoinPlageChoix;

  public static final String SERIALIZED_NAME_PLAGE_MIN = "plageMin";
  @SerializedName(SERIALIZED_NAME_PLAGE_MIN)
  private Integer plageMin;

  public static final String SERIALIZED_NAME_PLAGE_MAX = "plageMax";
  @SerializedName(SERIALIZED_NAME_PLAGE_MAX)
  private Integer plageMax;

  public static final String SERIALIZED_NAME_REGIME_INSCRIPTION_LST = "regimeInscriptionLst";
  @SerializedName(SERIALIZED_NAME_REGIME_INSCRIPTION_LST)
  private List<RegimeInscription> regimeInscriptionLst = null;

  public static final String SERIALIZED_NAME_PERIODE = "periode";
  @SerializedName(SERIALIZED_NAME_PERIODE)
  private Periode periode;

  public static final String SERIALIZED_NAME_FORMATION = "formation";
  @SerializedName(SERIALIZED_NAME_FORMATION)
  private Formation formation;

  public static final String SERIALIZED_NAME_OBJET_FORMATION = "objetFormation";
  @SerializedName(SERIALIZED_NAME_OBJET_FORMATION)
  private ObjetFormation objetFormation;

  public static final String SERIALIZED_NAME_GROUPEMENT = "groupement";
  @SerializedName(SERIALIZED_NAME_GROUPEMENT)
  private Groupement groupement;

  public static final String SERIALIZED_NAME_TEMOIN_I_A_VALIDE = "temoinIAValide";
  @SerializedName(SERIALIZED_NAME_TEMOIN_I_A_VALIDE)
  private Boolean temoinIAValide;

  public static final String SERIALIZED_NAME_TEMOIN_DISPENSE = "temoinDispense";
  @SerializedName(SERIALIZED_NAME_TEMOIN_DISPENSE)
  private Boolean temoinDispense = false;

  public static final String SERIALIZED_NAME_TEMOIN_AFFECTE = "temoinAffecte";
  @SerializedName(SERIALIZED_NAME_TEMOIN_AFFECTE)
  private Boolean temoinAffecte;

  public static final String SERIALIZED_NAME_TEMOIN_ACQUIS = "temoinAcquis";
  @SerializedName(SERIALIZED_NAME_TEMOIN_ACQUIS)
  private Boolean temoinAcquis;

  public static final String SERIALIZED_NAME_TEMOIN_CAPITALISE = "temoinCapitalise";
  @SerializedName(SERIALIZED_NAME_TEMOIN_CAPITALISE)
  private Boolean temoinCapitalise = false;

  public static final String SERIALIZED_NAME_TYPE_AMENAGEMENT_LST = "typeAmenagementLst";
  @SerializedName(SERIALIZED_NAME_TYPE_AMENAGEMENT_LST)
  private List<TypeAmenagement> typeAmenagementLst = null;

  public static final String SERIALIZED_NAME_CAPACITE_ACCUEIL_DISPONIBLE = "capaciteAccueilDisponible";
  @SerializedName(SERIALIZED_NAME_CAPACITE_ACCUEIL_DISPONIBLE)
  private Integer capaciteAccueilDisponible;

  public ObjetMaquetteExtension() { 
  }

  public ObjetMaquetteExtension codeChemin(String codeChemin) {
    
    this.codeChemin = codeChemin;
    return this;
  }

   /**
   * Le code chemin de l&#39;objet maquette - identifiant unique
   * @return codeChemin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code chemin de l'objet maquette - identifiant unique")

  public String getCodeChemin() {
    return codeChemin;
  }


  public void setCodeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
  }


  public ObjetMaquetteExtension codePeriode(String codePeriode) {
    
    this.codePeriode = codePeriode;
    return this;
  }

   /**
   * Le code de la période  - identifiant unique
   * @return codePeriode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la période  - identifiant unique")

  public String getCodePeriode() {
    return codePeriode;
  }


  public void setCodePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
  }


  public ObjetMaquetteExtension codeStructure(String codeStructure) {
    
    this.codeStructure = codeStructure;
    return this;
  }

   /**
   * Le code structure  - identifiant unique
   * @return codeStructure
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code structure  - identifiant unique")

  public String getCodeStructure() {
    return codeStructure;
  }


  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public ObjetMaquetteExtension codeType(String codeType) {
    
    this.codeType = codeType;
    return this;
  }

   /**
   * Le code type  - identifiant unique
   * @return codeType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code type  - identifiant unique")

  public String getCodeType() {
    return codeType;
  }


  public void setCodeType(String codeType) {
    this.codeType = codeType;
  }


  public ObjetMaquetteExtension codeCategorie(String codeCategorie) {
    
    this.codeCategorie = codeCategorie;
    return this;
  }

   /**
   * Le code de la catégorie
   * @return codeCategorie
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la catégorie")

  public String getCodeCategorie() {
    return codeCategorie;
  }


  public void setCodeCategorie(String codeCategorie) {
    this.codeCategorie = codeCategorie;
  }


  public ObjetMaquetteExtension capaciteAccueil(Integer capaciteAccueil) {
    
    this.capaciteAccueil = capaciteAccueil;
    return this;
  }

   /**
   * La capacité d&#39;accueil
   * @return capaciteAccueil
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La capacité d'accueil")

  public Integer getCapaciteAccueil() {
    return capaciteAccueil;
  }


  public void setCapaciteAccueil(Integer capaciteAccueil) {
    this.capaciteAccueil = capaciteAccueil;
  }


  public ObjetMaquetteExtension caractereObligatoire(Boolean caractereObligatoire) {
    
    this.caractereObligatoire = caractereObligatoire;
    return this;
  }

   /**
   * Est ce que l&#39;objet maquette est obligatoire au choix de cursus?
   * @return caractereObligatoire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est ce que l'objet maquette est obligatoire au choix de cursus?")

  public Boolean getCaractereObligatoire() {
    return caractereObligatoire;
  }


  public void setCaractereObligatoire(Boolean caractereObligatoire) {
    this.caractereObligatoire = caractereObligatoire;
  }


  public ObjetMaquetteExtension versionMaquette(Integer versionMaquette) {
    
    this.versionMaquette = versionMaquette;
    return this;
  }

   /**
   * La version de maquette
   * @return versionMaquette
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La version de maquette")

  public Integer getVersionMaquette() {
    return versionMaquette;
  }


  public void setVersionMaquette(Integer versionMaquette) {
    this.versionMaquette = versionMaquette;
  }


  public ObjetMaquetteExtension ouverteChoixCursus(Boolean ouverteChoixCursus) {
    
    this.ouverteChoixCursus = ouverteChoixCursus;
    return this;
  }

   /**
   * Est ce que l&#39;objet maquette est ouverte au choix de cursus?
   * @return ouverteChoixCursus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est ce que l'objet maquette est ouverte au choix de cursus?")

  public Boolean getOuverteChoixCursus() {
    return ouverteChoixCursus;
  }


  public void setOuverteChoixCursus(Boolean ouverteChoixCursus) {
    this.ouverteChoixCursus = ouverteChoixCursus;
  }


  public ObjetMaquetteExtension creditEcts(Double creditEcts) {
    
    this.creditEcts = creditEcts;
    return this;
  }

   /**
   * Les crédits ECTS de la cible d&#39;inscription au format &lt;4 chiffres maximum&gt;.&lt;2 chiffres maximum&gt;
   * @return creditEcts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Les crédits ECTS de la cible d'inscription au format <4 chiffres maximum>.<2 chiffres maximum>")

  public Double getCreditEcts() {
    return creditEcts;
  }


  public void setCreditEcts(Double creditEcts) {
    this.creditEcts = creditEcts;
  }


  public ObjetMaquetteExtension temoinPlageChoix(Boolean temoinPlageChoix) {
    
    this.temoinPlageChoix = temoinPlageChoix;
    return this;
  }

   /**
   * Est ce que le plage de choix est obligatoire?
   * @return temoinPlageChoix
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est ce que le plage de choix est obligatoire?")

  public Boolean getTemoinPlageChoix() {
    return temoinPlageChoix;
  }


  public void setTemoinPlageChoix(Boolean temoinPlageChoix) {
    this.temoinPlageChoix = temoinPlageChoix;
  }


  public ObjetMaquetteExtension plageMin(Integer plageMin) {
    
    this.plageMin = plageMin;
    return this;
  }

   /**
   * Le nombre minimum d&#39;objets de formation à sélectionner
   * @return plageMin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le nombre minimum d'objets de formation à sélectionner")

  public Integer getPlageMin() {
    return plageMin;
  }


  public void setPlageMin(Integer plageMin) {
    this.plageMin = plageMin;
  }


  public ObjetMaquetteExtension plageMax(Integer plageMax) {
    
    this.plageMax = plageMax;
    return this;
  }

   /**
   * Le nombre maximum d&#39;objets de formation à sélectionner
   * @return plageMax
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le nombre maximum d'objets de formation à sélectionner")

  public Integer getPlageMax() {
    return plageMax;
  }


  public void setPlageMax(Integer plageMax) {
    this.plageMax = plageMax;
  }


  public ObjetMaquetteExtension regimeInscriptionLst(List<RegimeInscription> regimeInscriptionLst) {
    
    this.regimeInscriptionLst = regimeInscriptionLst;
    return this;
  }

  public ObjetMaquetteExtension addRegimeInscriptionLstItem(RegimeInscription regimeInscriptionLstItem) {
    if (this.regimeInscriptionLst == null) {
      this.regimeInscriptionLst = new ArrayList<>();
    }
    this.regimeInscriptionLst.add(regimeInscriptionLstItem);
    return this;
  }

   /**
   * Get regimeInscriptionLst
   * @return regimeInscriptionLst
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<RegimeInscription> getRegimeInscriptionLst() {
    return regimeInscriptionLst;
  }


  public void setRegimeInscriptionLst(List<RegimeInscription> regimeInscriptionLst) {
    this.regimeInscriptionLst = regimeInscriptionLst;
  }


  public ObjetMaquetteExtension periode(Periode periode) {
    
    this.periode = periode;
    return this;
  }

   /**
   * Get periode
   * @return periode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Periode getPeriode() {
    return periode;
  }


  public void setPeriode(Periode periode) {
    this.periode = periode;
  }


  public ObjetMaquetteExtension formation(Formation formation) {
    
    this.formation = formation;
    return this;
  }

   /**
   * Get formation
   * @return formation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Formation getFormation() {
    return formation;
  }


  public void setFormation(Formation formation) {
    this.formation = formation;
  }


  public ObjetMaquetteExtension objetFormation(ObjetFormation objetFormation) {
    
    this.objetFormation = objetFormation;
    return this;
  }

   /**
   * Get objetFormation
   * @return objetFormation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ObjetFormation getObjetFormation() {
    return objetFormation;
  }


  public void setObjetFormation(ObjetFormation objetFormation) {
    this.objetFormation = objetFormation;
  }


  public ObjetMaquetteExtension groupement(Groupement groupement) {
    
    this.groupement = groupement;
    return this;
  }

   /**
   * Get groupement
   * @return groupement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Groupement getGroupement() {
    return groupement;
  }


  public void setGroupement(Groupement groupement) {
    this.groupement = groupement;
  }


  public ObjetMaquetteExtension temoinIAValide(Boolean temoinIAValide) {
    
    this.temoinIAValide = temoinIAValide;
    return this;
  }

   /**
   * Témoin indiquant si l&#39;étudiant a un IA valide sur l&#39;objet maquette
   * @return temoinIAValide
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Témoin indiquant si l'étudiant a un IA valide sur l'objet maquette")

  public Boolean getTemoinIAValide() {
    return temoinIAValide;
  }


  public void setTemoinIAValide(Boolean temoinIAValide) {
    this.temoinIAValide = temoinIAValide;
  }


  public ObjetMaquetteExtension temoinDispense(Boolean temoinDispense) {
    
    this.temoinDispense = temoinDispense;
    return this;
  }

   /**
   * Témoin indiquant si l&#39;étudiant est dispensé de l&#39;objet maquette
   * @return temoinDispense
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Témoin indiquant si l'étudiant est dispensé de l'objet maquette")

  public Boolean getTemoinDispense() {
    return temoinDispense;
  }


  public void setTemoinDispense(Boolean temoinDispense) {
    this.temoinDispense = temoinDispense;
  }


  public ObjetMaquetteExtension temoinAffecte(Boolean temoinAffecte) {
    
    this.temoinAffecte = temoinAffecte;
    return this;
  }

   /**
   * Témoin indiquant si l&#39;étudiant a une affectation CHC sur l&#39;objet maquette
   * @return temoinAffecte
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Témoin indiquant si l'étudiant a une affectation CHC sur l'objet maquette")

  public Boolean getTemoinAffecte() {
    return temoinAffecte;
  }


  public void setTemoinAffecte(Boolean temoinAffecte) {
    this.temoinAffecte = temoinAffecte;
  }


  public ObjetMaquetteExtension temoinAcquis(Boolean temoinAcquis) {
    
    this.temoinAcquis = temoinAcquis;
    return this;
  }

   /**
   * Témoin indiquant si l&#39;étudiant a validé son CHC sur l&#39;objet maquette
   * @return temoinAcquis
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Témoin indiquant si l'étudiant a validé son CHC sur l'objet maquette")

  public Boolean getTemoinAcquis() {
    return temoinAcquis;
  }


  public void setTemoinAcquis(Boolean temoinAcquis) {
    this.temoinAcquis = temoinAcquis;
  }


  public ObjetMaquetteExtension temoinCapitalise(Boolean temoinCapitalise) {
    
    this.temoinCapitalise = temoinCapitalise;
    return this;
  }

   /**
   * Témoin indiquant si l&#39;étudiant a capitalisé l&#39;acquis son CHC sur l&#39;objet maquette
   * @return temoinCapitalise
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Témoin indiquant si l'étudiant a capitalisé l'acquis son CHC sur l'objet maquette")

  public Boolean getTemoinCapitalise() {
    return temoinCapitalise;
  }


  public void setTemoinCapitalise(Boolean temoinCapitalise) {
    this.temoinCapitalise = temoinCapitalise;
  }


  public ObjetMaquetteExtension typeAmenagementLst(List<TypeAmenagement> typeAmenagementLst) {
    
    this.typeAmenagementLst = typeAmenagementLst;
    return this;
  }

  public ObjetMaquetteExtension addTypeAmenagementLstItem(TypeAmenagement typeAmenagementLstItem) {
    if (this.typeAmenagementLst == null) {
      this.typeAmenagementLst = new ArrayList<>();
    }
    this.typeAmenagementLst.add(typeAmenagementLstItem);
    return this;
  }

   /**
   * les types amenagements choisis pour un objet maquette
   * @return typeAmenagementLst
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "les types amenagements choisis pour un objet maquette")

  public List<TypeAmenagement> getTypeAmenagementLst() {
    return typeAmenagementLst;
  }


  public void setTypeAmenagementLst(List<TypeAmenagement> typeAmenagementLst) {
    this.typeAmenagementLst = typeAmenagementLst;
  }


  public ObjetMaquetteExtension capaciteAccueilDisponible(Integer capaciteAccueilDisponible) {
    
    this.capaciteAccueilDisponible = capaciteAccueilDisponible;
    return this;
  }

   /**
   * La capacité d&#39;accueil disponible
   * @return capaciteAccueilDisponible
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La capacité d'accueil disponible")

  public Integer getCapaciteAccueilDisponible() {
    return capaciteAccueilDisponible;
  }


  public void setCapaciteAccueilDisponible(Integer capaciteAccueilDisponible) {
    this.capaciteAccueilDisponible = capaciteAccueilDisponible;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetMaquetteExtension objetMaquetteExtension = (ObjetMaquetteExtension) o;
    return Objects.equals(this.codeChemin, objetMaquetteExtension.codeChemin) &&
        Objects.equals(this.codePeriode, objetMaquetteExtension.codePeriode) &&
        Objects.equals(this.codeStructure, objetMaquetteExtension.codeStructure) &&
        Objects.equals(this.codeType, objetMaquetteExtension.codeType) &&
        Objects.equals(this.codeCategorie, objetMaquetteExtension.codeCategorie) &&
        Objects.equals(this.capaciteAccueil, objetMaquetteExtension.capaciteAccueil) &&
        Objects.equals(this.caractereObligatoire, objetMaquetteExtension.caractereObligatoire) &&
        Objects.equals(this.versionMaquette, objetMaquetteExtension.versionMaquette) &&
        Objects.equals(this.ouverteChoixCursus, objetMaquetteExtension.ouverteChoixCursus) &&
        Objects.equals(this.creditEcts, objetMaquetteExtension.creditEcts) &&
        Objects.equals(this.temoinPlageChoix, objetMaquetteExtension.temoinPlageChoix) &&
        Objects.equals(this.plageMin, objetMaquetteExtension.plageMin) &&
        Objects.equals(this.plageMax, objetMaquetteExtension.plageMax) &&
        Objects.equals(this.regimeInscriptionLst, objetMaquetteExtension.regimeInscriptionLst) &&
        Objects.equals(this.periode, objetMaquetteExtension.periode) &&
        Objects.equals(this.formation, objetMaquetteExtension.formation) &&
        Objects.equals(this.objetFormation, objetMaquetteExtension.objetFormation) &&
        Objects.equals(this.groupement, objetMaquetteExtension.groupement) &&
        Objects.equals(this.temoinIAValide, objetMaquetteExtension.temoinIAValide) &&
        Objects.equals(this.temoinDispense, objetMaquetteExtension.temoinDispense) &&
        Objects.equals(this.temoinAffecte, objetMaquetteExtension.temoinAffecte) &&
        Objects.equals(this.temoinAcquis, objetMaquetteExtension.temoinAcquis) &&
        Objects.equals(this.temoinCapitalise, objetMaquetteExtension.temoinCapitalise) &&
        Objects.equals(this.typeAmenagementLst, objetMaquetteExtension.typeAmenagementLst) &&
        Objects.equals(this.capaciteAccueilDisponible, objetMaquetteExtension.capaciteAccueilDisponible);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeChemin, codePeriode, codeStructure, codeType, codeCategorie, capaciteAccueil, caractereObligatoire, versionMaquette, ouverteChoixCursus, creditEcts, temoinPlageChoix, plageMin, plageMax, regimeInscriptionLst, periode, formation, objetFormation, groupement, temoinIAValide, temoinDispense, temoinAffecte, temoinAcquis, temoinCapitalise, typeAmenagementLst, capaciteAccueilDisponible);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetMaquetteExtension {\n");
    sb.append("    codeChemin: ").append(toIndentedString(codeChemin)).append("\n");
    sb.append("    codePeriode: ").append(toIndentedString(codePeriode)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    codeType: ").append(toIndentedString(codeType)).append("\n");
    sb.append("    codeCategorie: ").append(toIndentedString(codeCategorie)).append("\n");
    sb.append("    capaciteAccueil: ").append(toIndentedString(capaciteAccueil)).append("\n");
    sb.append("    caractereObligatoire: ").append(toIndentedString(caractereObligatoire)).append("\n");
    sb.append("    versionMaquette: ").append(toIndentedString(versionMaquette)).append("\n");
    sb.append("    ouverteChoixCursus: ").append(toIndentedString(ouverteChoixCursus)).append("\n");
    sb.append("    creditEcts: ").append(toIndentedString(creditEcts)).append("\n");
    sb.append("    temoinPlageChoix: ").append(toIndentedString(temoinPlageChoix)).append("\n");
    sb.append("    plageMin: ").append(toIndentedString(plageMin)).append("\n");
    sb.append("    plageMax: ").append(toIndentedString(plageMax)).append("\n");
    sb.append("    regimeInscriptionLst: ").append(toIndentedString(regimeInscriptionLst)).append("\n");
    sb.append("    periode: ").append(toIndentedString(periode)).append("\n");
    sb.append("    formation: ").append(toIndentedString(formation)).append("\n");
    sb.append("    objetFormation: ").append(toIndentedString(objetFormation)).append("\n");
    sb.append("    groupement: ").append(toIndentedString(groupement)).append("\n");
    sb.append("    temoinIAValide: ").append(toIndentedString(temoinIAValide)).append("\n");
    sb.append("    temoinDispense: ").append(toIndentedString(temoinDispense)).append("\n");
    sb.append("    temoinAffecte: ").append(toIndentedString(temoinAffecte)).append("\n");
    sb.append("    temoinAcquis: ").append(toIndentedString(temoinAcquis)).append("\n");
    sb.append("    temoinCapitalise: ").append(toIndentedString(temoinCapitalise)).append("\n");
    sb.append("    typeAmenagementLst: ").append(toIndentedString(typeAmenagementLst)).append("\n");
    sb.append("    capaciteAccueilDisponible: ").append(toIndentedString(capaciteAccueilDisponible)).append("\n");
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

