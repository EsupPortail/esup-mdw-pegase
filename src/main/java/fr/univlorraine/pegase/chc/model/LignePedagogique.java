/*
 * CHC Externe v1 - API Externe choix du cursus
 * ### Introduction L’API répertorie l'ensemble des services et des opérations disponibles dans le module CHC (Choix du Cursus).  ### Authentification/autorisation obligatoire Pour tout appel à une opération, vous devez être authentifié/autorisé à l’aide d’un token JWT. Ainsi, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`. #### Répertoire de partage contenant la documentation décrivant l'authentification Pégase https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1  Le format est `Authorization: Bearer <token-jwt>`. Par exemple : `Authorization: Bearer xxxx.yyyy.zzzz`. Vous pouvez recevoir l'un de ces codes retour si vous n’êtes pas authentifié ou autorisé : * 401 - Unauthorized - Vous n’êtes pas authentifié     * Il n’y a pas de token passé dans le header HTTP `Authorization`     * Le token passé n’est pas au bon format (Bearer <token-jwt>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération     * La signature du token est incorrecte / n’a pas pu être vérifiée     * Le token est expiré     * Vérifier les droits de l’utilisateur * 500 - Internal Server Error     * Il n’est pas encore actif  ### Type de données Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * number($double) - Un nombre à virgule flottante en double précision  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour      | Code    | Description                                                                                                         |     |---------|---------------------------------------------------------------------------------------------------------------------|     | 200     | L'opération s'est déroulée avec succès                                                                              |     | 201     | L'opération a abouti à la création d'une ressource                                                                  |     | 400     | Un ou des paramètres d'entrée sont erronés                                                                          |     |         | Une erreur fonctionnelle s'est produite                                                                             |     | 404     | La ressource demandée n'est pas trouvée                                                                             |     |         | Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  |     | 500     | Erreur technique rencontrée par le serveur                                                                          |   ## Notions métiers  ### Objet Maquette (OM) Un objet maquette représente n'importe quel nœud d'un arbre de formation : Formation, objet de formation ou groupement.  Un objet maquette est identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure.  ### Formation Une formation est un arbre dont les nœuds sont des objets de formation et dont la racine est la formation elle-même.  Pour apparaître dans le module CHC, la formation doit être validée dans ODF.  Pour l'utiliser dans les différents actes métiers, il faut que chaque noeud et sa descendance soit ouvert au choix du cursus et qu'au moins une inscription administrative soit validée sur un objet maquette de l’arbre de la formation.  ### Objet formation Un objet de formation est l’un des nœuds de l’arbre de formation : année, semestre, UE, EC, enseignement, etc. (hors groupement).  Pour apparaître dans le Module CHC, un objet de formation doit être validé dans ODF.  ### Groupement Un groupement est une possibilité de structurer et d'organiser une formation. Il peut contenir des objets de formations de tous les types, être lié pour décomposer des objets pères de tous les types, être avec ou sans plage de choix.  ### Plage de choix Une plage de choix permet de contraindre l’apprenant lorsqu’il effectue son CHC dans Pégase. Cette plage de choix est matérialisée par un nombre minimum et un nombre maximum d’objets de formation à sélectionner. La plage de choix est contrôlée au cours du CHC.  ### Groupe Un Groupe est une entité permettant de diviser une population d’étudiants ou d'identifier une population spécifique d’étudiants inscrits administrativement et pédagogiquement.  ### Composition Une composition est une entité permettant de rassembler des groupes. Une composition contient obligatoirement au moins un groupe.  ### Période Une période de mise en œuvre correspond à la période pendant laquelle se déroule la formation.  Elle est le point d’entrée pour chaque acte métier du module CHC.  ### Apprenant Un apprenant est un usager qui suit un cursus et pour lequel des choix pédagogiques devront être réalisés.  ### Inscription L’inscription est l’ensemble des étapes de saisie des données concernant l’apprenant : état-civil, coordonnées, situation précédente, cursus, montant de l’inscription, mode de paiement. Cette saisie peut être faite par le gestionnaire ou l’apprenant.  Elle doit être vérifiée et validée par le gestionnaire. Une inscription n'est prise en compte dans CHC qu'à partir du moment où elle est validée ou annulée.  ### Cursus Le cursus est un arbre (une arborescence) composé d'objets maquette pour lequel des choix pédagogiques doivent être réalisés à chaque période de mise en oeuvre.  Un cursus est défini par le code de l’apprenant, un objet maquette lui-même identifié par le codeChemin (chemin pédagogique), le codePeriode et le codeStructure. Un choix pédagogique est une association entre un objet maquette et un apprenant. On recense parmi les choix pédagogiques des affectations, des aménagements et des acquis capitalisés.  ### Acquis capitalisé Un acquis capitalisé est un objet de formation dont les modalités de contrôle des connaissances sont capitalisables et dont le résultat positif a été obtenu sur une période antérieure. L'acquis capitalisé est créé et stocké dans CHC après délibération de jury du module COC. Il est utilisable sur une période postérieure à son acquisition  ### Chemin pédagogique Un chemin pédagogique identifie le lien d'un objet maquette à un autre objet maquette de sa descendance.  **Exemple** ``` MASTER-RH>MASTER-1>SEMESTRE-1>UE-OPTIONS>ESPAGNOL ```  ### Règles communes pour réaliser un choix de cursus * L’affectation est possible à partir de l'objet maquette porteur du point d'inscription administrative et sur les objets maquette de sa descendance à condition que l'inscription administrative soit validée dans le module INS. * Un choix du cursus sur un objet maquette est réalisable si le témoin ouvertChoixCursus est  à true. * La désaffectation d’un apprenant à un objet maquette provoque sa désaffectation automatique à tous les objets maquette de la descendance. * Pour chaque apprenant, il est possible de réaliser un choix du cursus sur un objet maquette dans un groupement à plage de choix tant que le nombre maximum autorisé (de la plage de choix) n'est pas atteint. La valeur minimum de plage de choix n'est  pas contrôlée. * Un choix pédagogique sur un objet maquette présent plusieurs fois dans un arbre de formation ne peut être réalisée qu’une fois pour un même cursus. * La capacité d’accueil d’un objet maquette n’est pas contrôlée dans l'API car non bloquante. Les capacités d’accueil dépassées sont négatives. * Les aménagements avec prise en compte acquis et aucun ne sont pas décomptés de la capacité d’accueil d’un objet maquette. * L'utilisation d'un acquis capitalisé empêche son affectation et supprime la branche de sa descendance.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.chc.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.chc.model.AcquisCapitalise;
import fr.univlorraine.pegase.chc.model.Amenagement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.univlorraine.pegase.chc.invoker.JSON;

/**
 * Informations pédagogiques pour un code chemin
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-02-27T16:57:51.872239500+01:00[Europe/Paris]", comments = "Generator version: 7.20.0")
public class LignePedagogique {
  public static final String SERIALIZED_NAME_CODE_OBJET_MAQUETTE = "codeObjetMaquette";
  @SerializedName(SERIALIZED_NAME_CODE_OBJET_MAQUETTE)
  @jakarta.annotation.Nonnull
  private String codeObjetMaquette;

  public static final String SERIALIZED_NAME_LIBELLE_LONG_OBJET_MAQUETTE = "libelleLongObjetMaquette";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG_OBJET_MAQUETTE)
  @jakarta.annotation.Nonnull
  private String libelleLongObjetMaquette;

  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE_TYPE_OBJET_FORMATION = "libelleAffichageTypeObjetFormation";
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE_TYPE_OBJET_FORMATION)
  @jakarta.annotation.Nonnull
  private String libelleAffichageTypeObjetFormation;

  public static final String SERIALIZED_NAME_EST_OBLIGATOIRE = "estObligatoire";
  @SerializedName(SERIALIZED_NAME_EST_OBLIGATOIRE)
  @jakarta.annotation.Nonnull
  private Boolean estObligatoire;

  public static final String SERIALIZED_NAME_CREDIT_ECTS = "creditEcts";
  @SerializedName(SERIALIZED_NAME_CREDIT_ECTS)
  @jakarta.annotation.Nullable
  private Double creditEcts;

  public static final String SERIALIZED_NAME_AMENAGEMENTS = "amenagements";
  @SerializedName(SERIALIZED_NAME_AMENAGEMENTS)
  @jakarta.annotation.Nonnull
  private List<Amenagement> amenagements = new ArrayList<>();

  public static final String SERIALIZED_NAME_ACQUIS_CAPITALISE = "acquisCapitalise";
  @SerializedName(SERIALIZED_NAME_ACQUIS_CAPITALISE)
  @jakarta.annotation.Nullable
  private AcquisCapitalise acquisCapitalise;

  /**
   * Enumération des différents types de choix pédagogique
   */
  @JsonAdapter(TypeChoixPedagogiqueEnum.Adapter.class)
  public enum TypeChoixPedagogiqueEnum {
    PAS_DE_CHC("PAS_DE_CHC"),
    
    DISPENSE_CHOISIE("DISPENSE_CHOISIE"),
    
    AFFECTATION_CHOISIE("AFFECTATION_CHOISIE"),
    
    ACQUIS("ACQUIS"),
    
    CAPITALISE_CHOISI("CAPITALISE_CHOISI"),
    
    RENONCIATION_CHOISIE("RENONCIATION_CHOISIE");

    private String value;

    TypeChoixPedagogiqueEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeChoixPedagogiqueEnum fromValue(String value) {
      for (TypeChoixPedagogiqueEnum b : TypeChoixPedagogiqueEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TypeChoixPedagogiqueEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeChoixPedagogiqueEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeChoixPedagogiqueEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TypeChoixPedagogiqueEnum.fromValue(value);
      }
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      String value = jsonElement.getAsString();
      TypeChoixPedagogiqueEnum.fromValue(value);
    }
  }

  public static final String SERIALIZED_NAME_TYPE_CHOIX_PEDAGOGIQUE = "typeChoixPedagogique";
  @SerializedName(SERIALIZED_NAME_TYPE_CHOIX_PEDAGOGIQUE)
  @jakarta.annotation.Nonnull
  private TypeChoixPedagogiqueEnum typeChoixPedagogique;

  public static final String SERIALIZED_NAME_ENFANTS = "enfants";
  @SerializedName(SERIALIZED_NAME_ENFANTS)
  @jakarta.annotation.Nonnull
  private List<LignePedagogique> enfants = new ArrayList<>();

  public LignePedagogique() {
  }

  public LignePedagogique codeObjetMaquette(@jakarta.annotation.Nonnull String codeObjetMaquette) {
    this.codeObjetMaquette = codeObjetMaquette;
    return this;
  }

  /**
   * Le code de l&#39;objet maquette (formation, objet formation ou groupement)
   * @return codeObjetMaquette
   */
  @jakarta.annotation.Nonnull
  public String getCodeObjetMaquette() {
    return codeObjetMaquette;
  }

  public void setCodeObjetMaquette(@jakarta.annotation.Nonnull String codeObjetMaquette) {
    this.codeObjetMaquette = codeObjetMaquette;
  }


  public LignePedagogique libelleLongObjetMaquette(@jakarta.annotation.Nonnull String libelleLongObjetMaquette) {
    this.libelleLongObjetMaquette = libelleLongObjetMaquette;
    return this;
  }

  /**
   * Le libellé long de l&#39;objet maquette (formation, objet formation ou groupement)
   * @return libelleLongObjetMaquette
   */
  @jakarta.annotation.Nonnull
  public String getLibelleLongObjetMaquette() {
    return libelleLongObjetMaquette;
  }

  public void setLibelleLongObjetMaquette(@jakarta.annotation.Nonnull String libelleLongObjetMaquette) {
    this.libelleLongObjetMaquette = libelleLongObjetMaquette;
  }


  public LignePedagogique libelleAffichageTypeObjetFormation(@jakarta.annotation.Nonnull String libelleAffichageTypeObjetFormation) {
    this.libelleAffichageTypeObjetFormation = libelleAffichageTypeObjetFormation;
    return this;
  }

  /**
   * Le libellé d&#39;affichage du type d&#39;objet de formation
   * @return libelleAffichageTypeObjetFormation
   */
  @jakarta.annotation.Nonnull
  public String getLibelleAffichageTypeObjetFormation() {
    return libelleAffichageTypeObjetFormation;
  }

  public void setLibelleAffichageTypeObjetFormation(@jakarta.annotation.Nonnull String libelleAffichageTypeObjetFormation) {
    this.libelleAffichageTypeObjetFormation = libelleAffichageTypeObjetFormation;
  }


  public LignePedagogique estObligatoire(@jakarta.annotation.Nonnull Boolean estObligatoire) {
    this.estObligatoire = estObligatoire;
    return this;
  }

  /**
   * Si l&#39;objet maquette est obligatoire pour ce code chemin
   * @return estObligatoire
   */
  @jakarta.annotation.Nonnull
  public Boolean getEstObligatoire() {
    return estObligatoire;
  }

  public void setEstObligatoire(@jakarta.annotation.Nonnull Boolean estObligatoire) {
    this.estObligatoire = estObligatoire;
  }


  public LignePedagogique creditEcts(@jakarta.annotation.Nullable Double creditEcts) {
    this.creditEcts = creditEcts;
    return this;
  }

  /**
   * Le nombre de crédits ECTS de l&#39;objet maquette pour ce code chemin
   * @return creditEcts
   */
  @jakarta.annotation.Nullable
  public Double getCreditEcts() {
    return creditEcts;
  }

  public void setCreditEcts(@jakarta.annotation.Nullable Double creditEcts) {
    this.creditEcts = creditEcts;
  }


  public LignePedagogique amenagements(@jakarta.annotation.Nonnull List<Amenagement> amenagements) {
    this.amenagements = amenagements;
    return this;
  }

  public LignePedagogique addAmenagementsItem(Amenagement amenagementsItem) {
    if (this.amenagements == null) {
      this.amenagements = new ArrayList<>();
    }
    this.amenagements.add(amenagementsItem);
    return this;
  }

  /**
   * La liste des aménagements du choix pédagogique
   * @return amenagements
   */
  @jakarta.annotation.Nonnull
  public List<Amenagement> getAmenagements() {
    return amenagements;
  }

  public void setAmenagements(@jakarta.annotation.Nonnull List<Amenagement> amenagements) {
    this.amenagements = amenagements;
  }


  public LignePedagogique acquisCapitalise(@jakarta.annotation.Nullable AcquisCapitalise acquisCapitalise) {
    this.acquisCapitalise = acquisCapitalise;
    return this;
  }

  /**
   * Get acquisCapitalise
   * @return acquisCapitalise
   */
  @jakarta.annotation.Nullable
  public AcquisCapitalise getAcquisCapitalise() {
    return acquisCapitalise;
  }

  public void setAcquisCapitalise(@jakarta.annotation.Nullable AcquisCapitalise acquisCapitalise) {
    this.acquisCapitalise = acquisCapitalise;
  }


  public LignePedagogique typeChoixPedagogique(@jakarta.annotation.Nonnull TypeChoixPedagogiqueEnum typeChoixPedagogique) {
    this.typeChoixPedagogique = typeChoixPedagogique;
    return this;
  }

  /**
   * Enumération des différents types de choix pédagogique
   * @return typeChoixPedagogique
   */
  @jakarta.annotation.Nonnull
  public TypeChoixPedagogiqueEnum getTypeChoixPedagogique() {
    return typeChoixPedagogique;
  }

  public void setTypeChoixPedagogique(@jakarta.annotation.Nonnull TypeChoixPedagogiqueEnum typeChoixPedagogique) {
    this.typeChoixPedagogique = typeChoixPedagogique;
  }


  public LignePedagogique enfants(@jakarta.annotation.Nonnull List<LignePedagogique> enfants) {
    this.enfants = enfants;
    return this;
  }

  public LignePedagogique addEnfantsItem(LignePedagogique enfantsItem) {
    if (this.enfants == null) {
      this.enfants = new ArrayList<>();
    }
    this.enfants.add(enfantsItem);
    return this;
  }

  /**
   * Les branches pédagogiques dans l&#39;arbre
   * @return enfants
   */
  @jakarta.annotation.Nonnull
  public List<LignePedagogique> getEnfants() {
    return enfants;
  }

  public void setEnfants(@jakarta.annotation.Nonnull List<LignePedagogique> enfants) {
    this.enfants = enfants;
  }

  /**
   * A container for additional, undeclared properties.
   * This is a holder for any undeclared properties as specified with
   * the 'additionalProperties' keyword in the OAS document.
   */
  private Map<String, Object> additionalProperties;

  /**
   * Set the additional (undeclared) property with the specified name and value.
   * If the property does not already exist, create it otherwise replace it.
   *
   * @param key name of the property
   * @param value value of the property
   * @return the LignePedagogique instance itself
   */
  public LignePedagogique putAdditionalProperty(String key, Object value) {
    if (this.additionalProperties == null) {
        this.additionalProperties = new HashMap<String, Object>();
    }
    this.additionalProperties.put(key, value);
    return this;
  }

  /**
   * Return the additional (undeclared) property.
   *
   * @return a map of objects
   */
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  /**
   * Return the additional (undeclared) property with the specified name.
   *
   * @param key name of the property
   * @return an object
   */
  public Object getAdditionalProperty(String key) {
    if (this.additionalProperties == null) {
        return null;
    }
    return this.additionalProperties.get(key);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LignePedagogique lignePedagogique = (LignePedagogique) o;
    return Objects.equals(this.codeObjetMaquette, lignePedagogique.codeObjetMaquette) &&
        Objects.equals(this.libelleLongObjetMaquette, lignePedagogique.libelleLongObjetMaquette) &&
        Objects.equals(this.libelleAffichageTypeObjetFormation, lignePedagogique.libelleAffichageTypeObjetFormation) &&
        Objects.equals(this.estObligatoire, lignePedagogique.estObligatoire) &&
        Objects.equals(this.creditEcts, lignePedagogique.creditEcts) &&
        Objects.equals(this.amenagements, lignePedagogique.amenagements) &&
        Objects.equals(this.acquisCapitalise, lignePedagogique.acquisCapitalise) &&
        Objects.equals(this.typeChoixPedagogique, lignePedagogique.typeChoixPedagogique) &&
        Objects.equals(this.enfants, lignePedagogique.enfants)&&
        Objects.equals(this.additionalProperties, lignePedagogique.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeObjetMaquette, libelleLongObjetMaquette, libelleAffichageTypeObjetFormation, estObligatoire, creditEcts, amenagements, acquisCapitalise, typeChoixPedagogique, enfants, additionalProperties);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LignePedagogique {\n");
    sb.append("    codeObjetMaquette: ").append(toIndentedString(codeObjetMaquette)).append("\n");
    sb.append("    libelleLongObjetMaquette: ").append(toIndentedString(libelleLongObjetMaquette)).append("\n");
    sb.append("    libelleAffichageTypeObjetFormation: ").append(toIndentedString(libelleAffichageTypeObjetFormation)).append("\n");
    sb.append("    estObligatoire: ").append(toIndentedString(estObligatoire)).append("\n");
    sb.append("    creditEcts: ").append(toIndentedString(creditEcts)).append("\n");
    sb.append("    amenagements: ").append(toIndentedString(amenagements)).append("\n");
    sb.append("    acquisCapitalise: ").append(toIndentedString(acquisCapitalise)).append("\n");
    sb.append("    typeChoixPedagogique: ").append(toIndentedString(typeChoixPedagogique)).append("\n");
    sb.append("    enfants: ").append(toIndentedString(enfants)).append("\n");
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>(Arrays.asList("codeObjetMaquette", "libelleLongObjetMaquette", "libelleAffichageTypeObjetFormation", "estObligatoire", "creditEcts", "amenagements", "acquisCapitalise", "typeChoixPedagogique", "enfants"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("codeObjetMaquette", "libelleLongObjetMaquette", "libelleAffichageTypeObjetFormation", "estObligatoire", "amenagements", "typeChoixPedagogique", "enfants"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to LignePedagogique
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!LignePedagogique.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field(s) %s in LignePedagogique is not found in the empty JSON string", LignePedagogique.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : LignePedagogique.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("codeObjetMaquette").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `codeObjetMaquette` to be a primitive type in the JSON string but got `%s`", jsonObj.get("codeObjetMaquette").toString()));
      }
      if (!jsonObj.get("libelleLongObjetMaquette").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `libelleLongObjetMaquette` to be a primitive type in the JSON string but got `%s`", jsonObj.get("libelleLongObjetMaquette").toString()));
      }
      if (!jsonObj.get("libelleAffichageTypeObjetFormation").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `libelleAffichageTypeObjetFormation` to be a primitive type in the JSON string but got `%s`", jsonObj.get("libelleAffichageTypeObjetFormation").toString()));
      }
      if (jsonObj.get("amenagements") != null) {
        if (!jsonObj.get("amenagements").isJsonArray()) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `amenagements` to be an array in the JSON string but got `%s`", jsonObj.get("amenagements").toString()));
        }
        JsonArray jsonArrayamenagements = jsonObj.getAsJsonArray("amenagements");
        // validate the required field `amenagements` (array)
        for (int i = 0; i < jsonArrayamenagements.size(); i++) {
          Amenagement.validateJsonElement(jsonArrayamenagements.get(i));
        }
      }
      // validate the optional field `acquisCapitalise`
      if (jsonObj.get("acquisCapitalise") != null && !jsonObj.get("acquisCapitalise").isJsonNull()) {
        AcquisCapitalise.validateJsonElement(jsonObj.get("acquisCapitalise"));
      }
      if (!jsonObj.get("typeChoixPedagogique").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `typeChoixPedagogique` to be a primitive type in the JSON string but got `%s`", jsonObj.get("typeChoixPedagogique").toString()));
      }
      // validate the required field `typeChoixPedagogique`
      TypeChoixPedagogiqueEnum.validateJsonElement(jsonObj.get("typeChoixPedagogique"));
      if (jsonObj.get("enfants") != null) {
        if (!jsonObj.get("enfants").isJsonArray()) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `enfants` to be an array in the JSON string but got `%s`", jsonObj.get("enfants").toString()));
        }
        JsonArray jsonArrayenfants = jsonObj.getAsJsonArray("enfants");
        // validate the required field `enfants` (array)
        for (int i = 0; i < jsonArrayenfants.size(); i++) {
          LignePedagogique.validateJsonElement(jsonArrayenfants.get(i));
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!LignePedagogique.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'LignePedagogique' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<LignePedagogique> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(LignePedagogique.class));

       return (TypeAdapter<T>) new TypeAdapter<LignePedagogique>() {
           @Override
           public void write(JsonWriter out, LignePedagogique value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             obj.remove("additionalProperties");
             // serialize additional properties
             if (value.getAdditionalProperties() != null) {
               for (Map.Entry<String, Object> entry : value.getAdditionalProperties().entrySet()) {
                 if (entry.getValue() instanceof String)
                   obj.addProperty(entry.getKey(), (String) entry.getValue());
                 else if (entry.getValue() instanceof Number)
                   obj.addProperty(entry.getKey(), (Number) entry.getValue());
                 else if (entry.getValue() instanceof Boolean)
                   obj.addProperty(entry.getKey(), (Boolean) entry.getValue());
                 else if (entry.getValue() instanceof Character)
                   obj.addProperty(entry.getKey(), (Character) entry.getValue());
                 else {
                   JsonElement jsonElement = gson.toJsonTree(entry.getValue());
                   if (jsonElement.isJsonArray()) {
                     obj.add(entry.getKey(), jsonElement.getAsJsonArray());
                   } else {
                     obj.add(entry.getKey(), jsonElement.getAsJsonObject());
                   }
                 }
               }
             }
             elementAdapter.write(out, obj);
           }

           @Override
           public LignePedagogique read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             LignePedagogique instance = thisAdapter.fromJsonTree(jsonObj);
             for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
               if (!openapiFields.contains(entry.getKey())) {
                 if (entry.getValue().isJsonPrimitive()) { // primitive type
                   if (entry.getValue().getAsJsonPrimitive().isString())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsString());
                   else if (entry.getValue().getAsJsonPrimitive().isNumber())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsNumber());
                   else if (entry.getValue().getAsJsonPrimitive().isBoolean())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsBoolean());
                   else
                     throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                 } else if (entry.getValue().isJsonArray()) {
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                 } else { // JSON object
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                 }
               }
             }
             return instance;
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of LignePedagogique given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of LignePedagogique
   * @throws IOException if the JSON string is invalid with respect to LignePedagogique
   */
  public static LignePedagogique fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, LignePedagogique.class);
  }

  /**
   * Convert an instance of LignePedagogique to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

