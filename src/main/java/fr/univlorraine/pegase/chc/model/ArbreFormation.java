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


package fr.univlorraine.pegase.chc.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.chc.model.ArbreGroupement;
import fr.univlorraine.pegase.chc.model.ArbreObjetFormation;
import fr.univlorraine.pegase.chc.model.CalendrierResponse;
import fr.univlorraine.pegase.chc.model.ObjetLibelle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ArbreFormation
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:29:19.714087400+02:00[Europe/Paris]")
public class ArbreFormation {
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  public static final String SERIALIZED_NAME_CREDIT_ECTS = "creditEcts";
  public static final String SERIALIZED_NAME_OUVERTURE_AU_CHOIX_DU_CURSUS = "ouvertureAuChoixDuCursus";
  public static final String SERIALIZED_NAME_TYPE_OBJET_MAQUETTE = "typeObjetMaquette";
  public static final String SERIALIZED_NAME_ID_CHEMIN_PEDAGOGIQUE = "idCheminPedagogique";
  public static final String SERIALIZED_NAME_TYPE = "type";
  public static final String SERIALIZED_NAME_LISTE_OBJET_FORMATION = "listeObjetFormation";
  public static final String SERIALIZED_NAME_LISTE_GROUPEMENT = "listeGroupement";
  public static final String SERIALIZED_NAME_LISTE_CALENDRIER = "listeCalendrier";
  public static final String SERIALIZED_NAME_ID_PERIODE = "idPeriode";
  public static final String SERIALIZED_NAME_ETAT_PIA = "etatPia";
  public static final String SERIALIZED_NAME_OUVERTURE_CHC_LIGNE = "ouvertureChcLigne";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;
  @SerializedName(SERIALIZED_NAME_CREDIT_ECTS)
  private String creditEcts;
  @SerializedName(SERIALIZED_NAME_OUVERTURE_AU_CHOIX_DU_CURSUS)
  private OuvertureAuChoixDuCursusEnum ouvertureAuChoixDuCursus;
  @SerializedName(SERIALIZED_NAME_TYPE_OBJET_MAQUETTE)
  private TypeObjetMaquetteEnum typeObjetMaquette;
  @SerializedName(SERIALIZED_NAME_ID_CHEMIN_PEDAGOGIQUE)
  private UUID idCheminPedagogique;
  @SerializedName(SERIALIZED_NAME_TYPE)
  private ObjetLibelle type;
  @SerializedName(SERIALIZED_NAME_LISTE_OBJET_FORMATION)
  private List<ArbreObjetFormation> listeObjetFormation = null;
  @SerializedName(SERIALIZED_NAME_LISTE_GROUPEMENT)
  private List<ArbreGroupement> listeGroupement = null;
  @SerializedName(SERIALIZED_NAME_LISTE_CALENDRIER)
  private List<CalendrierResponse> listeCalendrier = null;
  @SerializedName(SERIALIZED_NAME_ID_PERIODE)
  private UUID idPeriode;
  @SerializedName(SERIALIZED_NAME_ETAT_PIA)
  private EtatPiaEnum etatPia;
  @SerializedName(SERIALIZED_NAME_OUVERTURE_CHC_LIGNE)
  private OuvertureChcLigneEnum ouvertureChcLigne;
  public ArbreFormation() {
  }

  public ArbreFormation code(String code) {

    this.code = code;
    return this;
  }

   /**
   * Get code
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ArbreFormation libelleCourt(String libelleCourt) {

    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Get libelleCourt
   * @return libelleCourt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getLibelleCourt() {
    return libelleCourt;
  }

  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }

  public ArbreFormation creditEcts(String creditEcts) {

    this.creditEcts = creditEcts;
    return this;
  }

   /**
   * Les crédits E.C.T.S. positionnés sur la formation
   * @return creditEcts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Les crédits E.C.T.S. positionnés sur la formation")

  public String getCreditEcts() {
    return creditEcts;
  }

  public void setCreditEcts(String creditEcts) {
    this.creditEcts = creditEcts;
  }

  public ArbreFormation ouvertureAuChoixDuCursus(OuvertureAuChoixDuCursusEnum ouvertureAuChoixDuCursus) {

    this.ouvertureAuChoixDuCursus = ouvertureAuChoixDuCursus;
    return this;
  }

   /**
   * Get ouvertureAuChoixDuCursus
   * @return ouvertureAuChoixDuCursus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OuvertureAuChoixDuCursusEnum getOuvertureAuChoixDuCursus() {
    return ouvertureAuChoixDuCursus;
  }

  public void setOuvertureAuChoixDuCursus(OuvertureAuChoixDuCursusEnum ouvertureAuChoixDuCursus) {
    this.ouvertureAuChoixDuCursus = ouvertureAuChoixDuCursus;
  }

  public ArbreFormation typeObjetMaquette(TypeObjetMaquetteEnum typeObjetMaquette) {

    this.typeObjetMaquette = typeObjetMaquette;
    return this;
  }

   /**
   * Get typeObjetMaquette
   * @return typeObjetMaquette
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public TypeObjetMaquetteEnum getTypeObjetMaquette() {
    return typeObjetMaquette;
  }

  public void setTypeObjetMaquette(TypeObjetMaquetteEnum typeObjetMaquette) {
    this.typeObjetMaquette = typeObjetMaquette;
  }

  public ArbreFormation idCheminPedagogique(UUID idCheminPedagogique) {

    this.idCheminPedagogique = idCheminPedagogique;
    return this;
  }

   /**
   * identifiant technique du chemin pédagogique
   * @return idCheminPedagogique
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2c1e120f-4799-414e-90d8-bbda2b869704", value = "identifiant technique du chemin pédagogique")

  public UUID getIdCheminPedagogique() {
    return idCheminPedagogique;
  }

  public void setIdCheminPedagogique(UUID idCheminPedagogique) {
    this.idCheminPedagogique = idCheminPedagogique;
  }

  public ArbreFormation type(ObjetLibelle type) {

    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ObjetLibelle getType() {
    return type;
  }

  public void setType(ObjetLibelle type) {
    this.type = type;
  }

  public ArbreFormation listeObjetFormation(List<ArbreObjetFormation> listeObjetFormation) {

    this.listeObjetFormation = listeObjetFormation;
    return this;
  }

  public ArbreFormation addListeObjetFormationItem(ArbreObjetFormation listeObjetFormationItem) {
    if (this.listeObjetFormation == null) {
      this.listeObjetFormation = new ArrayList<ArbreObjetFormation>();
    }
    this.listeObjetFormation.add(listeObjetFormationItem);
    return this;
  }

   /**
   * Get listeObjetFormation
   * @return listeObjetFormation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<ArbreObjetFormation> getListeObjetFormation() {
    return listeObjetFormation;
  }

  public void setListeObjetFormation(List<ArbreObjetFormation> listeObjetFormation) {
    this.listeObjetFormation = listeObjetFormation;
  }

  public ArbreFormation listeGroupement(List<ArbreGroupement> listeGroupement) {

    this.listeGroupement = listeGroupement;
    return this;
  }

  public ArbreFormation addListeGroupementItem(ArbreGroupement listeGroupementItem) {
    if (this.listeGroupement == null) {
      this.listeGroupement = new ArrayList<ArbreGroupement>();
    }
    this.listeGroupement.add(listeGroupementItem);
    return this;
  }

   /**
   * Get listeGroupement
   * @return listeGroupement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<ArbreGroupement> getListeGroupement() {
    return listeGroupement;
  }

  public void setListeGroupement(List<ArbreGroupement> listeGroupement) {
    this.listeGroupement = listeGroupement;
  }

  public ArbreFormation listeCalendrier(List<CalendrierResponse> listeCalendrier) {

    this.listeCalendrier = listeCalendrier;
    return this;
  }

  public ArbreFormation addListeCalendrierItem(CalendrierResponse listeCalendrierItem) {
    if (this.listeCalendrier == null) {
      this.listeCalendrier = new ArrayList<CalendrierResponse>();
    }
    this.listeCalendrier.add(listeCalendrierItem);
    return this;
  }

   /**
   * Get listeCalendrier
   * @return listeCalendrier
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<CalendrierResponse> getListeCalendrier() {
    return listeCalendrier;
  }

  public void setListeCalendrier(List<CalendrierResponse> listeCalendrier) {
    this.listeCalendrier = listeCalendrier;
  }

  public ArbreFormation idPeriode(UUID idPeriode) {

    this.idPeriode = idPeriode;
    return this;
  }

   /**
   * identifiant de la periode du chemin pedagogique
   * @return idPeriode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "identifiant de la periode du chemin pedagogique")

  public UUID getIdPeriode() {
    return idPeriode;
  }

  public void setIdPeriode(UUID idPeriode) {
    this.idPeriode = idPeriode;
  }

  public ArbreFormation etatPia(EtatPiaEnum etatPia) {

    this.etatPia = etatPia;
    return this;
  }

   /**
   * Get etatPia
   * @return etatPia
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public EtatPiaEnum getEtatPia() {
    return etatPia;
  }

  public void setEtatPia(EtatPiaEnum etatPia) {
    this.etatPia = etatPia;
  }

  public ArbreFormation ouvertureChcLigne(OuvertureChcLigneEnum ouvertureChcLigne) {

    this.ouvertureChcLigne = ouvertureChcLigne;
    return this;
  }

   /**
   * Get ouvertureChcLigne
   * @return ouvertureChcLigne
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OuvertureChcLigneEnum getOuvertureChcLigne() {
    return ouvertureChcLigne;
  }

  public void setOuvertureChcLigne(OuvertureChcLigneEnum ouvertureChcLigne) {
    this.ouvertureChcLigne = ouvertureChcLigne;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArbreFormation arbreFormation = (ArbreFormation) o;
    return Objects.equals(this.code, arbreFormation.code) &&
        Objects.equals(this.libelleCourt, arbreFormation.libelleCourt) &&
        Objects.equals(this.creditEcts, arbreFormation.creditEcts) &&
        Objects.equals(this.ouvertureAuChoixDuCursus, arbreFormation.ouvertureAuChoixDuCursus) &&
        Objects.equals(this.typeObjetMaquette, arbreFormation.typeObjetMaquette) &&
        Objects.equals(this.idCheminPedagogique, arbreFormation.idCheminPedagogique) &&
        Objects.equals(this.type, arbreFormation.type) &&
        Objects.equals(this.listeObjetFormation, arbreFormation.listeObjetFormation) &&
        Objects.equals(this.listeGroupement, arbreFormation.listeGroupement) &&
        Objects.equals(this.listeCalendrier, arbreFormation.listeCalendrier) &&
        Objects.equals(this.idPeriode, arbreFormation.idPeriode) &&
        Objects.equals(this.etatPia, arbreFormation.etatPia) &&
        Objects.equals(this.ouvertureChcLigne, arbreFormation.ouvertureChcLigne);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, creditEcts, ouvertureAuChoixDuCursus, typeObjetMaquette, idCheminPedagogique, type, listeObjetFormation, listeGroupement, listeCalendrier, idPeriode, etatPia, ouvertureChcLigne);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArbreFormation {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    creditEcts: ").append(toIndentedString(creditEcts)).append("\n");
    sb.append("    ouvertureAuChoixDuCursus: ").append(toIndentedString(ouvertureAuChoixDuCursus)).append("\n");
    sb.append("    typeObjetMaquette: ").append(toIndentedString(typeObjetMaquette)).append("\n");
    sb.append("    idCheminPedagogique: ").append(toIndentedString(idCheminPedagogique)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    listeObjetFormation: ").append(toIndentedString(listeObjetFormation)).append("\n");
    sb.append("    listeGroupement: ").append(toIndentedString(listeGroupement)).append("\n");
    sb.append("    listeCalendrier: ").append(toIndentedString(listeCalendrier)).append("\n");
    sb.append("    idPeriode: ").append(toIndentedString(idPeriode)).append("\n");
    sb.append("    etatPia: ").append(toIndentedString(etatPia)).append("\n");
    sb.append("    ouvertureChcLigne: ").append(toIndentedString(ouvertureChcLigne)).append("\n");
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


  /**
   * Gets or Sets ouvertureAuChoixDuCursus
   */
  @JsonAdapter(OuvertureAuChoixDuCursusEnum.Adapter.class)
  public enum OuvertureAuChoixDuCursusEnum {
    OUVERT("OUVERT"),

    FERME("FERME"),

    JAMAIS("JAMAIS");

    private String value;

    OuvertureAuChoixDuCursusEnum(String value) {
      this.value = value;
    }

    public static OuvertureAuChoixDuCursusEnum fromValue(String value) {
      for (OuvertureAuChoixDuCursusEnum b : OuvertureAuChoixDuCursusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static class Adapter extends TypeAdapter<OuvertureAuChoixDuCursusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OuvertureAuChoixDuCursusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OuvertureAuChoixDuCursusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return OuvertureAuChoixDuCursusEnum.fromValue(value);
      }
    }
  }

  /**
   * Gets or Sets typeObjetMaquette
   */
  @JsonAdapter(TypeObjetMaquetteEnum.Adapter.class)
  public enum TypeObjetMaquetteEnum {
    FORMATION("FORMATION"),

    OBJET_FORMATION("OBJET_FORMATION"),

    GROUPEMENT("GROUPEMENT");

    private String value;

    TypeObjetMaquetteEnum(String value) {
      this.value = value;
    }

    public static TypeObjetMaquetteEnum fromValue(String value) {
      for (TypeObjetMaquetteEnum b : TypeObjetMaquetteEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static class Adapter extends TypeAdapter<TypeObjetMaquetteEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeObjetMaquetteEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeObjetMaquetteEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TypeObjetMaquetteEnum.fromValue(value);
      }
    }
  }

  /**
   * Gets or Sets etatPia
   */
  @JsonAdapter(EtatPiaEnum.Adapter.class)
  public enum EtatPiaEnum {
    PIA_ACTIF("PIA_ACTIF"),

    PIA_INACTIF("PIA_INACTIF"),

    NON_PIA("NON_PIA");

    private String value;

    EtatPiaEnum(String value) {
      this.value = value;
    }

    public static EtatPiaEnum fromValue(String value) {
      for (EtatPiaEnum b : EtatPiaEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static class Adapter extends TypeAdapter<EtatPiaEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final EtatPiaEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public EtatPiaEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return EtatPiaEnum.fromValue(value);
      }
    }
  }

  /**
   * Gets or Sets ouvertureChcLigne
   */
  @JsonAdapter(OuvertureChcLigneEnum.Adapter.class)
  public enum OuvertureChcLigneEnum {
    NON_CONCERNE("NON_CONCERNE"),

    FERME("FERME"),

    OUVERT("OUVERT");

    private String value;

    OuvertureChcLigneEnum(String value) {
      this.value = value;
    }

    public static OuvertureChcLigneEnum fromValue(String value) {
      for (OuvertureChcLigneEnum b : OuvertureChcLigneEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static class Adapter extends TypeAdapter<OuvertureChcLigneEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OuvertureChcLigneEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OuvertureChcLigneEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return OuvertureChcLigneEnum.fromValue(value);
      }
    }
  }

}

