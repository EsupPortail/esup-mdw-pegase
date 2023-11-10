/*
 * CHC v6 - Choix du cursus
 * <font color='red'>***Statut***: DRAFT (brouillon/preview)</font> Ne pas utiliser cette version d'API. Elle est au statut DRAFT, donc sujette à changements sans aucune garantie de compatibilité ascendante. Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v6 ### Introduction l’API liste l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus). Le module CHC permet d’affecter les apprenants aux Objets maquettes qu’ils doivent suivre pour une période de mise en œuvre donnée pendant leur cursus, de leur saisir des aménagements avec différentes prises en compte et de les affecter à des groupes. ### Authentification/autorisation obligatoire Pour tout appel à une opération vous devez être authentifié/authorisé à l’aide d’un token jwt. Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`. Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz` Vous pouvez recevoir un des ces codes retours si vous n’êtes pas authentifié ou autorisé : * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pû être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a aboutie à la création d'une ressource                                                                 |     | 400     | Un ou des paramètres d'entrées sont erronées                                                                        |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvé                                                                              |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers ### Objet Maquette (OM) Un Objet Maquette représente n'importe quel nœud d'un arbre de Formation: Formation, Objet de Formation ou Groupement. Un objet Maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure  ### Formation Une Formation est un arbre dont les nœuds sont des Objets de formation et dont la racine est la Formation elle-même. Pour apparaître dans le Module CHC, la formation doit être mise en œuvre, actualisée, ouverte à l’inscription et ouverte au CHC dans MOF. Il est également nécessaire qu’il y ait au moins une inscription valide sur un objet maquette de l’arbre de la formation. ### Objet formation Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc.(hors groupement). Pour apparaître dans le Module CHC, un objet de formation doit être ouvert au CHC dans MOF. ### Groupement Un groupement est une possibilité de structurer et d'organiser une formation.Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix. ### Plage de choix Une plage de choix permet de contraindre l’apprenant lorsque  qu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC. ### Groupe Un Groupe est une entité permettant de diviser une population d’étudiants ou d’identifier une population spécifique d’étudiants inscrits administrativement ou pédagogiquement ### Composition Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.  ### Période Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation, du début des cours jusqu’à la délibération des jurys. Elle est le point d’entrée du module puisque le CHC se fait pour une période donnée. ### Apprenant Un apprenant est un usager qui suit un cursus et pour lequel le CHC va être saisi. ### Inscription L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant. Elle doit être vérifiée et validée par le gestionnaire.Au 25/03/21, l’inscription doit être validée pour que l’apprenant puisse arriver dans le module CHC. ### Cursus Le cursus est l’ensemble des Objets Maquette auxquels l’apprenant va être affecté ou pour lesquels des aménagements vont être saisis, le tout pour une période donnée. Un cursus est défini par le code de l’apprenant et un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure. ### Acquis capitalisé Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances attendent un résultat capitalisable. Pour être identifié dans CHC comme acquis capitalisé, cet objet doit posséder un résultat positif obtenu sur une période passée, pour laquelle une délibération de jury a statué. ### Chemin pédagogique Un chemin pédagogique identifie le lien d'un Objet Maquette à un autre Objet maquette de sa descendance. **Exemple** ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ``` ### Affectation en masse (Dépréciée) L'affectation en masse permet, pour une période donnée,  d’affecter ou de désaffecter des apprenants sur un Objet Maquette ouvert au choix du cursus et éventuellement sur sa descendance obligatoire.Les affectations ne sont possibles que si le père de l'objet maquette a été affecté ou acquis => contrôle du chemin pédagogique. Les aménagements-acquis sont conservés lors de la désaffectation. ### Affectation individuelle (Dépréciée) L'affectation individuelle permet, pour une période et un apprenant donnés de saisir, modifier ou supprimer pour cet apprenant les affectations, les acquis et les aménagements aux Objets de formations souhaités. Un Objet de formation est soit affecté soit acquis : il ne peut pas être les deux en même temps. Des contrôles sont effectués pour la cohérence aménagement-acquis ou aménagement-affectation ou aménagement-aucun. Les affectations ou la saisie des aménagements ne sont possibles sur un OM que si le père a été affecté (contrôle du chemin pédagogique). ### Paramétrage Un paramétrage est une personnalisation de concepts spécifiques pour des processus métiers. Ils sont gérés dans le Référentiel ou dans chacun des modules. Ils peuvent être utilisés par les différents modules. Le Type d’aménagement est un paramétrage du module REF. ## Informations techniques Toutes les actions de cursus (affectation, désaffectation, acquis, non-acquis, type d’aménagement) de l’apprenant dans CHC seront envoyées au module COC. Toutes les actions de cursus sont en mode upsert (créer si ça n’existe pas ou modifier si ça existe). Seule la liste des types aménagements dans l’entrée sera remplacée par ses anciennes valeurs. ### Règles communes pour réaliser un choix de cursus * L’affectation peut seulement se faire s’il y a une inscription valide sur l’objet maquette ou un des objets maquette de son ascendance. Les statuts de l’inscription proviennent du module INS. * Un CHC sur un Objet Maquette peut se faire uniquement si cet objet Maquette a le témoin ouvertChoixCursus à true. * Lors de la désaffectation d’un apprenant  à un Objet Maquette, l’apprenant sera également désaffecté automatiquement à tous les Objets Maquette de sa descendance. * Un CHC sur un Objet Maquette dans un groupement à plage de choix peut être fait seulement si le nombre de CHC de l’apprenant dans ce groupement ne dépasse pas le maximum autorisé (la plageMax). On ne contrôle pas la valeur mininum de plage de choix. * L’affectation/acquis/type aménagement sur un Objet Maquette mutualisé présent plusieurs fois dans un arbre ne peut être réalisée qu’une fois, c’est-à-dire que l’Objet Maquette (avec un certain code chemin) ne peut être qu’une seule fois avec une affectation / un acquis ou un type aménagement sur le même cursus * La capacité d’accueil d’un Objet Maquette n’est pas contrôlée dans l’API car non bloquante. Cela peut donc entraîner des capacités d’accueil négatives. * Les aménagements avec prise en compte Acquis et Aucun ne sont pas décomptés de la capacité d’accueil d’un Objet Maquette. * La récupération d'un acquis capitalisé empêche son affectation et celle de sa descendance.
 *
 * The version of the OpenAPI document: 6.0.0
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
 * AffectationIndividuelleAcquisUtilisableParEvaluation
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:41:21.810253900+01:00[Europe/Paris]")
public class AffectationIndividuelleAcquisUtilisableParEvaluation {
  public static final String SERIALIZED_NAME_CODE_CHEMIN_OBJET_FORMATION_CHOIX_PEDAGOGIQUE = "codeCheminObjetFormationChoixPedagogique";
  @SerializedName(SERIALIZED_NAME_CODE_CHEMIN_OBJET_FORMATION_CHOIX_PEDAGOGIQUE)
  private String codeCheminObjetFormationChoixPedagogique;

  public static final String SERIALIZED_NAME_CODE_OBJET_FORMATION_CHOIX_PEDAGOGIQUE = "codeObjetFormationChoixPedagogique";
  @SerializedName(SERIALIZED_NAME_CODE_OBJET_FORMATION_CHOIX_PEDAGOGIQUE)
  private String codeObjetFormationChoixPedagogique;

  public static final String SERIALIZED_NAME_LIBELLE_LONG_OBJET_MAQUETTE_ACQUISITION = "libelleLongObjetMaquetteAcquisition";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG_OBJET_MAQUETTE_ACQUISITION)
  private String libelleLongObjetMaquetteAcquisition;

  public static final String SERIALIZED_NAME_CODE_PERIODE_ACQUISITION = "codePeriodeAcquisition";
  @SerializedName(SERIALIZED_NAME_CODE_PERIODE_ACQUISITION)
  private String codePeriodeAcquisition;

  public static final String SERIALIZED_NAME_LIBELLE_COURT_PERIODE_ACQUISITION = "libelleCourtPeriodeAcquisition";
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT_PERIODE_ACQUISITION)
  private String libelleCourtPeriodeAcquisition;

  public static final String SERIALIZED_NAME_CODE_OBJET_FORMATION_ACQUISITION = "codeObjetFormationAcquisition";
  @SerializedName(SERIALIZED_NAME_CODE_OBJET_FORMATION_ACQUISITION)
  private String codeObjetFormationAcquisition;

  public static final String SERIALIZED_NAME_UTILISABLE_VIA_LCC = "utilisableViaLcc";
  @SerializedName(SERIALIZED_NAME_UTILISABLE_VIA_LCC)
  private Boolean utilisableViaLcc;

  public static final String SERIALIZED_NAME_NOTE_FINALE = "noteFinale";
  @SerializedName(SERIALIZED_NAME_NOTE_FINALE)
  private String noteFinale;

  public static final String SERIALIZED_NAME_BAREME = "bareme";
  @SerializedName(SERIALIZED_NAME_BAREME)
  private Integer bareme;

  public static final String SERIALIZED_NAME_RESULTAT_FINAL_ACQUISITION_LIBELLE_LONG = "resultatFinalAcquisitionLibelleLong";
  @SerializedName(SERIALIZED_NAME_RESULTAT_FINAL_ACQUISITION_LIBELLE_LONG)
  private String resultatFinalAcquisitionLibelleLong;

  public static final String SERIALIZED_NAME_NUMERO_SESSION_RETENUE_ACQUISITION = "numeroSessionRetenueAcquisition";
  @SerializedName(SERIALIZED_NAME_NUMERO_SESSION_RETENUE_ACQUISITION)
  private Integer numeroSessionRetenueAcquisition;

  public AffectationIndividuelleAcquisUtilisableParEvaluation() { 
  }

  public AffectationIndividuelleAcquisUtilisableParEvaluation codeCheminObjetFormationChoixPedagogique(String codeCheminObjetFormationChoixPedagogique) {
    
    this.codeCheminObjetFormationChoixPedagogique = codeCheminObjetFormationChoixPedagogique;
    return this;
  }

   /**
   * le code chemin vers l&#39;objet formation dans le contexte de cet arbre pour lequel l&#39;acquis utilisable est utilisable
   * @return codeCheminObjetFormationChoixPedagogique
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "LIC-CHIMIE>LIC-CHIMIE-A1>LIC-CHIMIE-S1>UE-CHIMIE", required = true, value = "le code chemin vers l'objet formation dans le contexte de cet arbre pour lequel l'acquis utilisable est utilisable")

  public String getCodeCheminObjetFormationChoixPedagogique() {
    return codeCheminObjetFormationChoixPedagogique;
  }


  public void setCodeCheminObjetFormationChoixPedagogique(String codeCheminObjetFormationChoixPedagogique) {
    this.codeCheminObjetFormationChoixPedagogique = codeCheminObjetFormationChoixPedagogique;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation codeObjetFormationChoixPedagogique(String codeObjetFormationChoixPedagogique) {
    
    this.codeObjetFormationChoixPedagogique = codeObjetFormationChoixPedagogique;
    return this;
  }

   /**
   * le code de l&#39;objet formation dans le contexte de cet arbre pour lequel l&#39;acquis utilisable est utilisable
   * @return codeObjetFormationChoixPedagogique
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le code de l'objet formation dans le contexte de cet arbre pour lequel l'acquis utilisable est utilisable")

  public String getCodeObjetFormationChoixPedagogique() {
    return codeObjetFormationChoixPedagogique;
  }


  public void setCodeObjetFormationChoixPedagogique(String codeObjetFormationChoixPedagogique) {
    this.codeObjetFormationChoixPedagogique = codeObjetFormationChoixPedagogique;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation libelleLongObjetMaquetteAcquisition(String libelleLongObjetMaquetteAcquisition) {
    
    this.libelleLongObjetMaquetteAcquisition = libelleLongObjetMaquetteAcquisition;
    return this;
  }

   /**
   * Libelle long de l&#39;objet maquette à laquelle il y a eu l&#39;acquisition
   * @return libelleLongObjetMaquetteAcquisition
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Libelle long de l'objet maquette à laquelle il y a eu l'acquisition")

  public String getLibelleLongObjetMaquetteAcquisition() {
    return libelleLongObjetMaquetteAcquisition;
  }


  public void setLibelleLongObjetMaquetteAcquisition(String libelleLongObjetMaquetteAcquisition) {
    this.libelleLongObjetMaquetteAcquisition = libelleLongObjetMaquetteAcquisition;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation codePeriodeAcquisition(String codePeriodeAcquisition) {
    
    this.codePeriodeAcquisition = codePeriodeAcquisition;
    return this;
  }

   /**
   * Code de la période à laquelle il y a eu l&#39;acquisition
   * @return codePeriodeAcquisition
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Code de la période à laquelle il y a eu l'acquisition")

  public String getCodePeriodeAcquisition() {
    return codePeriodeAcquisition;
  }


  public void setCodePeriodeAcquisition(String codePeriodeAcquisition) {
    this.codePeriodeAcquisition = codePeriodeAcquisition;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation libelleCourtPeriodeAcquisition(String libelleCourtPeriodeAcquisition) {
    
    this.libelleCourtPeriodeAcquisition = libelleCourtPeriodeAcquisition;
    return this;
  }

   /**
   * Libelle Court de la période à laquelle il y a eu l&#39;acquisition
   * @return libelleCourtPeriodeAcquisition
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Libelle Court de la période à laquelle il y a eu l'acquisition")

  public String getLibelleCourtPeriodeAcquisition() {
    return libelleCourtPeriodeAcquisition;
  }


  public void setLibelleCourtPeriodeAcquisition(String libelleCourtPeriodeAcquisition) {
    this.libelleCourtPeriodeAcquisition = libelleCourtPeriodeAcquisition;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation codeObjetFormationAcquisition(String codeObjetFormationAcquisition) {
    
    this.codeObjetFormationAcquisition = codeObjetFormationAcquisition;
    return this;
  }

   /**
   * L&#39;objet sur lequel il y a eu l&#39;acquisition (peut être différent de l&#39;objet de l&#39;arbre actuel si l&#39;acquis est utilisable via LCC)
   * @return codeObjetFormationAcquisition
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "L'objet sur lequel il y a eu l'acquisition (peut être différent de l'objet de l'arbre actuel si l'acquis est utilisable via LCC)")

  public String getCodeObjetFormationAcquisition() {
    return codeObjetFormationAcquisition;
  }


  public void setCodeObjetFormationAcquisition(String codeObjetFormationAcquisition) {
    this.codeObjetFormationAcquisition = codeObjetFormationAcquisition;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation utilisableViaLcc(Boolean utilisableViaLcc) {
    
    this.utilisableViaLcc = utilisableViaLcc;
    return this;
  }

   /**
   * Vrai si l&#39;acquis est utilisable pour ce choix pedagogique via un lcc
   * @return utilisableViaLcc
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Vrai si l'acquis est utilisable pour ce choix pedagogique via un lcc")

  public Boolean getUtilisableViaLcc() {
    return utilisableViaLcc;
  }


  public void setUtilisableViaLcc(Boolean utilisableViaLcc) {
    this.utilisableViaLcc = utilisableViaLcc;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation noteFinale(String noteFinale) {
    
    this.noteFinale = noteFinale;
    return this;
  }

   /**
   * La note finale
   * @return noteFinale
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "12.456", value = "La note finale")

  public String getNoteFinale() {
    return noteFinale;
  }


  public void setNoteFinale(String noteFinale) {
    this.noteFinale = noteFinale;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation bareme(Integer bareme) {
    
    this.bareme = bareme;
    return this;
  }

   /**
   * Le barème (le maximum) de la note Obligatoirement renseigné si la note finale est présente.
   * @return bareme
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "20", value = "Le barème (le maximum) de la note Obligatoirement renseigné si la note finale est présente.")

  public Integer getBareme() {
    return bareme;
  }


  public void setBareme(Integer bareme) {
    this.bareme = bareme;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation resultatFinalAcquisitionLibelleLong(String resultatFinalAcquisitionLibelleLong) {
    
    this.resultatFinalAcquisitionLibelleLong = resultatFinalAcquisitionLibelleLong;
    return this;
  }

   /**
   * Libelle long du résultat final
   * @return resultatFinalAcquisitionLibelleLong
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Libelle long du résultat final")

  public String getResultatFinalAcquisitionLibelleLong() {
    return resultatFinalAcquisitionLibelleLong;
  }


  public void setResultatFinalAcquisitionLibelleLong(String resultatFinalAcquisitionLibelleLong) {
    this.resultatFinalAcquisitionLibelleLong = resultatFinalAcquisitionLibelleLong;
  }


  public AffectationIndividuelleAcquisUtilisableParEvaluation numeroSessionRetenueAcquisition(Integer numeroSessionRetenueAcquisition) {
    
    this.numeroSessionRetenueAcquisition = numeroSessionRetenueAcquisition;
    return this;
  }

   /**
   * Le numéro de session
   * @return numeroSessionRetenueAcquisition
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "1", required = true, value = "Le numéro de session")

  public Integer getNumeroSessionRetenueAcquisition() {
    return numeroSessionRetenueAcquisition;
  }


  public void setNumeroSessionRetenueAcquisition(Integer numeroSessionRetenueAcquisition) {
    this.numeroSessionRetenueAcquisition = numeroSessionRetenueAcquisition;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectationIndividuelleAcquisUtilisableParEvaluation affectationIndividuelleAcquisUtilisableParEvaluation = (AffectationIndividuelleAcquisUtilisableParEvaluation) o;
    return Objects.equals(this.codeCheminObjetFormationChoixPedagogique, affectationIndividuelleAcquisUtilisableParEvaluation.codeCheminObjetFormationChoixPedagogique) &&
        Objects.equals(this.codeObjetFormationChoixPedagogique, affectationIndividuelleAcquisUtilisableParEvaluation.codeObjetFormationChoixPedagogique) &&
        Objects.equals(this.libelleLongObjetMaquetteAcquisition, affectationIndividuelleAcquisUtilisableParEvaluation.libelleLongObjetMaquetteAcquisition) &&
        Objects.equals(this.codePeriodeAcquisition, affectationIndividuelleAcquisUtilisableParEvaluation.codePeriodeAcquisition) &&
        Objects.equals(this.libelleCourtPeriodeAcquisition, affectationIndividuelleAcquisUtilisableParEvaluation.libelleCourtPeriodeAcquisition) &&
        Objects.equals(this.codeObjetFormationAcquisition, affectationIndividuelleAcquisUtilisableParEvaluation.codeObjetFormationAcquisition) &&
        Objects.equals(this.utilisableViaLcc, affectationIndividuelleAcquisUtilisableParEvaluation.utilisableViaLcc) &&
        Objects.equals(this.noteFinale, affectationIndividuelleAcquisUtilisableParEvaluation.noteFinale) &&
        Objects.equals(this.bareme, affectationIndividuelleAcquisUtilisableParEvaluation.bareme) &&
        Objects.equals(this.resultatFinalAcquisitionLibelleLong, affectationIndividuelleAcquisUtilisableParEvaluation.resultatFinalAcquisitionLibelleLong) &&
        Objects.equals(this.numeroSessionRetenueAcquisition, affectationIndividuelleAcquisUtilisableParEvaluation.numeroSessionRetenueAcquisition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeCheminObjetFormationChoixPedagogique, codeObjetFormationChoixPedagogique, libelleLongObjetMaquetteAcquisition, codePeriodeAcquisition, libelleCourtPeriodeAcquisition, codeObjetFormationAcquisition, utilisableViaLcc, noteFinale, bareme, resultatFinalAcquisitionLibelleLong, numeroSessionRetenueAcquisition);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectationIndividuelleAcquisUtilisableParEvaluation {\n");
    sb.append("    codeCheminObjetFormationChoixPedagogique: ").append(toIndentedString(codeCheminObjetFormationChoixPedagogique)).append("\n");
    sb.append("    codeObjetFormationChoixPedagogique: ").append(toIndentedString(codeObjetFormationChoixPedagogique)).append("\n");
    sb.append("    libelleLongObjetMaquetteAcquisition: ").append(toIndentedString(libelleLongObjetMaquetteAcquisition)).append("\n");
    sb.append("    codePeriodeAcquisition: ").append(toIndentedString(codePeriodeAcquisition)).append("\n");
    sb.append("    libelleCourtPeriodeAcquisition: ").append(toIndentedString(libelleCourtPeriodeAcquisition)).append("\n");
    sb.append("    codeObjetFormationAcquisition: ").append(toIndentedString(codeObjetFormationAcquisition)).append("\n");
    sb.append("    utilisableViaLcc: ").append(toIndentedString(utilisableViaLcc)).append("\n");
    sb.append("    noteFinale: ").append(toIndentedString(noteFinale)).append("\n");
    sb.append("    bareme: ").append(toIndentedString(bareme)).append("\n");
    sb.append("    resultatFinalAcquisitionLibelleLong: ").append(toIndentedString(resultatFinalAcquisitionLibelleLong)).append("\n");
    sb.append("    numeroSessionRetenueAcquisition: ").append(toIndentedString(numeroSessionRetenueAcquisition)).append("\n");
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

