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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ApprenantChoixPedagogiqueViaChemin
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:29:19.714087400+02:00[Europe/Paris]")
public class ApprenantChoixPedagogiqueViaChemin {
  public static final String SERIALIZED_NAME_CODE_APPRENANT = "codeApprenant";
  public static final String SERIALIZED_NAME_NOM_DE_FAMILLE = "nomDeFamille";
  public static final String SERIALIZED_NAME_PRENOM = "prenom";
  public static final String SERIALIZED_NAME_DATE_DE_NAISSANCE = "dateDeNaissance";
  public static final String SERIALIZED_NAME_INE = "ine";
  public static final String SERIALIZED_NAME_CURSUS_UUID = "cursusUuid";
  public static final String SERIALIZED_NAME_TYPE_CHOIX_PEDAGOGIQUE_ACTUEL = "typeChoixPedagogiqueActuel";
  public static final String SERIALIZED_NAME_AMENAGEMENTS_CODES = "amenagementsCodes";
  public static final String SERIALIZED_NAME_ACQUIS_DISPONIBLE = "acquisDisponible";
  public static final String SERIALIZED_NAME_ACQUIS_UTILISE = "acquisUtilise";
  @SerializedName(SERIALIZED_NAME_CODE_APPRENANT)
  private String codeApprenant;
  @SerializedName(SERIALIZED_NAME_NOM_DE_FAMILLE)
  private String nomDeFamille;
  @SerializedName(SERIALIZED_NAME_PRENOM)
  private String prenom;
  @SerializedName(SERIALIZED_NAME_DATE_DE_NAISSANCE)
  private Date dateDeNaissance;
  @SerializedName(SERIALIZED_NAME_INE)
  private String ine;
  @SerializedName(SERIALIZED_NAME_CURSUS_UUID)
  private UUID cursusUuid;
  @SerializedName(SERIALIZED_NAME_TYPE_CHOIX_PEDAGOGIQUE_ACTUEL)
  private TypeChoixPedagogiqueActuelEnum typeChoixPedagogiqueActuel;
  @SerializedName(SERIALIZED_NAME_AMENAGEMENTS_CODES)
  private List<String> amenagementsCodes = null;
  @SerializedName(SERIALIZED_NAME_ACQUIS_DISPONIBLE)
  private Boolean acquisDisponible;
  @SerializedName(SERIALIZED_NAME_ACQUIS_UTILISE)
  private Boolean acquisUtilise;
  public ApprenantChoixPedagogiqueViaChemin() {
  }

  public ApprenantChoixPedagogiqueViaChemin codeApprenant(String codeApprenant) {

    this.codeApprenant = codeApprenant;
    return this;
  }

   /**
   * Le code de l&#39;apprenant
   * @return codeApprenant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'apprenant")

  public String getCodeApprenant() {
    return codeApprenant;
  }

  public void setCodeApprenant(String codeApprenant) {
    this.codeApprenant = codeApprenant;
  }

  public ApprenantChoixPedagogiqueViaChemin nomDeFamille(String nomDeFamille) {

    this.nomDeFamille = nomDeFamille;
    return this;
  }

   /**
   * Le nom de famille de l&#39;apprenant
   * @return nomDeFamille
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le nom de famille de l'apprenant")

  public String getNomDeFamille() {
    return nomDeFamille;
  }

  public void setNomDeFamille(String nomDeFamille) {
    this.nomDeFamille = nomDeFamille;
  }

  public ApprenantChoixPedagogiqueViaChemin prenom(String prenom) {

    this.prenom = prenom;
    return this;
  }

   /**
   * Le prenom de l&#39;apprenant
   * @return prenom
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le prenom de l'apprenant")

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public ApprenantChoixPedagogiqueViaChemin dateDeNaissance(Date dateDeNaissance) {

    this.dateDeNaissance = dateDeNaissance;
    return this;
  }

   /**
   * La date de naissance de l&#39;apprenant
   * @return dateDeNaissance
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La date de naissance de l'apprenant")

  public Date getDateDeNaissance() {
    return dateDeNaissance;
  }

  public void setDateDeNaissance(Date dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
  }

  public ApprenantChoixPedagogiqueViaChemin ine(String ine) {

    this.ine = ine;
    return this;
  }

   /**
   * l&#39;identifiant national etudiant de l&#39;apprenant
   * @return ine
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "l'identifiant national etudiant de l'apprenant")

  public String getIne() {
    return ine;
  }

  public void setIne(String ine) {
    this.ine = ine;
  }

  public ApprenantChoixPedagogiqueViaChemin cursusUuid(UUID cursusUuid) {

    this.cursusUuid = cursusUuid;
    return this;
  }

   /**
   * L&#39;id technique du cursus
   * @return cursusUuid
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'id technique du cursus")

  public UUID getCursusUuid() {
    return cursusUuid;
  }

  public void setCursusUuid(UUID cursusUuid) {
    this.cursusUuid = cursusUuid;
  }

  public ApprenantChoixPedagogiqueViaChemin typeChoixPedagogiqueActuel(TypeChoixPedagogiqueActuelEnum typeChoixPedagogiqueActuel) {

    this.typeChoixPedagogiqueActuel = typeChoixPedagogiqueActuel;
    return this;
  }

   /**
   * Type de choix pédagogique actuel
   * @return typeChoixPedagogiqueActuel
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Type de choix pédagogique actuel")

  public TypeChoixPedagogiqueActuelEnum getTypeChoixPedagogiqueActuel() {
    return typeChoixPedagogiqueActuel;
  }

  public void setTypeChoixPedagogiqueActuel(TypeChoixPedagogiqueActuelEnum typeChoixPedagogiqueActuel) {
    this.typeChoixPedagogiqueActuel = typeChoixPedagogiqueActuel;
  }

  public ApprenantChoixPedagogiqueViaChemin amenagementsCodes(List<String> amenagementsCodes) {

    this.amenagementsCodes = amenagementsCodes;
    return this;
  }

  public ApprenantChoixPedagogiqueViaChemin addAmenagementsCodesItem(String amenagementsCodesItem) {
    if (this.amenagementsCodes == null) {
      this.amenagementsCodes = new ArrayList<String>();
    }
    this.amenagementsCodes.add(amenagementsCodesItem);
    return this;
  }

   /**
   * La liste des code aménagements
   * @return amenagementsCodes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La liste des code aménagements")

  public List<String> getAmenagementsCodes() {
    return amenagementsCodes;
  }

  public void setAmenagementsCodes(List<String> amenagementsCodes) {
    this.amenagementsCodes = amenagementsCodes;
  }

  public ApprenantChoixPedagogiqueViaChemin acquisDisponible(Boolean acquisDisponible) {

    this.acquisDisponible = acquisDisponible;
    return this;
  }

   /**
   * Des acquis sont-ils disponibles
   * @return acquisDisponible
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Des acquis sont-ils disponibles")

  public Boolean getAcquisDisponible() {
    return acquisDisponible;
  }

  public void setAcquisDisponible(Boolean acquisDisponible) {
    this.acquisDisponible = acquisDisponible;
  }

  public ApprenantChoixPedagogiqueViaChemin acquisUtilise(Boolean acquisUtilise) {

    this.acquisUtilise = acquisUtilise;
    return this;
  }

   /**
   * Un acquis est-il utilisé
   * @return acquisUtilise
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Un acquis est-il utilisé")

  public Boolean getAcquisUtilise() {
    return acquisUtilise;
  }

  public void setAcquisUtilise(Boolean acquisUtilise) {
    this.acquisUtilise = acquisUtilise;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApprenantChoixPedagogiqueViaChemin apprenantChoixPedagogiqueViaChemin = (ApprenantChoixPedagogiqueViaChemin) o;
    return Objects.equals(this.codeApprenant, apprenantChoixPedagogiqueViaChemin.codeApprenant) &&
        Objects.equals(this.nomDeFamille, apprenantChoixPedagogiqueViaChemin.nomDeFamille) &&
        Objects.equals(this.prenom, apprenantChoixPedagogiqueViaChemin.prenom) &&
        Objects.equals(this.dateDeNaissance, apprenantChoixPedagogiqueViaChemin.dateDeNaissance) &&
        Objects.equals(this.ine, apprenantChoixPedagogiqueViaChemin.ine) &&
        Objects.equals(this.cursusUuid, apprenantChoixPedagogiqueViaChemin.cursusUuid) &&
        Objects.equals(this.typeChoixPedagogiqueActuel, apprenantChoixPedagogiqueViaChemin.typeChoixPedagogiqueActuel) &&
        Objects.equals(this.amenagementsCodes, apprenantChoixPedagogiqueViaChemin.amenagementsCodes) &&
        Objects.equals(this.acquisDisponible, apprenantChoixPedagogiqueViaChemin.acquisDisponible) &&
        Objects.equals(this.acquisUtilise, apprenantChoixPedagogiqueViaChemin.acquisUtilise);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeApprenant, nomDeFamille, prenom, dateDeNaissance, ine, cursusUuid, typeChoixPedagogiqueActuel, amenagementsCodes, acquisDisponible, acquisUtilise);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApprenantChoixPedagogiqueViaChemin {\n");
    sb.append("    codeApprenant: ").append(toIndentedString(codeApprenant)).append("\n");
    sb.append("    nomDeFamille: ").append(toIndentedString(nomDeFamille)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    dateDeNaissance: ").append(toIndentedString(dateDeNaissance)).append("\n");
    sb.append("    ine: ").append(toIndentedString(ine)).append("\n");
    sb.append("    cursusUuid: ").append(toIndentedString(cursusUuid)).append("\n");
    sb.append("    typeChoixPedagogiqueActuel: ").append(toIndentedString(typeChoixPedagogiqueActuel)).append("\n");
    sb.append("    amenagementsCodes: ").append(toIndentedString(amenagementsCodes)).append("\n");
    sb.append("    acquisDisponible: ").append(toIndentedString(acquisDisponible)).append("\n");
    sb.append("    acquisUtilise: ").append(toIndentedString(acquisUtilise)).append("\n");
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
   * Type de choix pédagogique actuel
   */
  @JsonAdapter(TypeChoixPedagogiqueActuelEnum.Adapter.class)
  public enum TypeChoixPedagogiqueActuelEnum {
    EMPTY(""),

    PAS_DE_CHC("PAS_DE_CHC"),

    AUCUN("AUCUN"),

    AFFECTE("AFFECTE"),

    ACQUIS("ACQUIS"),

    CAPITALISATION("CAPITALISATION"),

    RENONCIATION("RENONCIATION");

    private String value;

    TypeChoixPedagogiqueActuelEnum(String value) {
      this.value = value;
    }

    public static TypeChoixPedagogiqueActuelEnum fromValue(String value) {
      for (TypeChoixPedagogiqueActuelEnum b : TypeChoixPedagogiqueActuelEnum.values()) {
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

    public static class Adapter extends TypeAdapter<TypeChoixPedagogiqueActuelEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeChoixPedagogiqueActuelEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeChoixPedagogiqueActuelEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TypeChoixPedagogiqueActuelEnum.fromValue(value);
      }
    }
  }

}

