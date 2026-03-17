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
import java.io.IOException;
import java.util.Arrays;
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
 * AcquisCapitalise
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-02-27T16:57:51.872239500+01:00[Europe/Paris]", comments = "Generator version: 7.20.0")
public class AcquisCapitalise {
  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE_PERIODE_ACQUISITION = "libelleAffichagePeriodeAcquisition";
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE_PERIODE_ACQUISITION)
  @jakarta.annotation.Nonnull
  private String libelleAffichagePeriodeAcquisition;

  public static final String SERIALIZED_NAME_CODE_OBJET_FORMATION_ACQUISITION = "codeObjetFormationAcquisition";
  @SerializedName(SERIALIZED_NAME_CODE_OBJET_FORMATION_ACQUISITION)
  @jakarta.annotation.Nonnull
  private String codeObjetFormationAcquisition;

  public static final String SERIALIZED_NAME_TROUVE_VIA_LIEN_CORRESPONDANCE_POUR_CALCUL = "trouveViaLienCorrespondancePourCalcul";
  @SerializedName(SERIALIZED_NAME_TROUVE_VIA_LIEN_CORRESPONDANCE_POUR_CALCUL)
  @jakarta.annotation.Nonnull
  private Boolean trouveViaLienCorrespondancePourCalcul;

  public static final String SERIALIZED_NAME_NOTE_FINALE = "noteFinale";
  @SerializedName(SERIALIZED_NAME_NOTE_FINALE)
  @jakarta.annotation.Nullable
  private String noteFinale;

  public static final String SERIALIZED_NAME_BAREME = "bareme";
  @SerializedName(SERIALIZED_NAME_BAREME)
  @jakarta.annotation.Nullable
  private Integer bareme;

  public static final String SERIALIZED_NAME_LIBELLE_RESULTAT_FINAL = "libelleResultatFinal";
  @SerializedName(SERIALIZED_NAME_LIBELLE_RESULTAT_FINAL)
  @jakarta.annotation.Nonnull
  private String libelleResultatFinal;

  public AcquisCapitalise() {
  }

  public AcquisCapitalise libelleAffichagePeriodeAcquisition(@jakarta.annotation.Nonnull String libelleAffichagePeriodeAcquisition) {
    this.libelleAffichagePeriodeAcquisition = libelleAffichagePeriodeAcquisition;
    return this;
  }

  /**
   * Code de la période à laquelle il y a eu l&#39;acquisition
   * @return libelleAffichagePeriodeAcquisition
   */
  @jakarta.annotation.Nonnull
  public String getLibelleAffichagePeriodeAcquisition() {
    return libelleAffichagePeriodeAcquisition;
  }

  public void setLibelleAffichagePeriodeAcquisition(@jakarta.annotation.Nonnull String libelleAffichagePeriodeAcquisition) {
    this.libelleAffichagePeriodeAcquisition = libelleAffichagePeriodeAcquisition;
  }


  public AcquisCapitalise codeObjetFormationAcquisition(@jakarta.annotation.Nonnull String codeObjetFormationAcquisition) {
    this.codeObjetFormationAcquisition = codeObjetFormationAcquisition;
    return this;
  }

  /**
   * L&#39;objet sur lequel il y a eu l&#39;acquisition (différent de l&#39;objet du chemin dans le cas des LCC par exemple)
   * @return codeObjetFormationAcquisition
   */
  @jakarta.annotation.Nonnull
  public String getCodeObjetFormationAcquisition() {
    return codeObjetFormationAcquisition;
  }

  public void setCodeObjetFormationAcquisition(@jakarta.annotation.Nonnull String codeObjetFormationAcquisition) {
    this.codeObjetFormationAcquisition = codeObjetFormationAcquisition;
  }


  public AcquisCapitalise trouveViaLienCorrespondancePourCalcul(@jakarta.annotation.Nonnull Boolean trouveViaLienCorrespondancePourCalcul) {
    this.trouveViaLienCorrespondancePourCalcul = trouveViaLienCorrespondancePourCalcul;
    return this;
  }

  /**
   * Vrai si le code de l&#39;OF acquis est différent du code OM
   * @return trouveViaLienCorrespondancePourCalcul
   */
  @jakarta.annotation.Nonnull
  public Boolean getTrouveViaLienCorrespondancePourCalcul() {
    return trouveViaLienCorrespondancePourCalcul;
  }

  public void setTrouveViaLienCorrespondancePourCalcul(@jakarta.annotation.Nonnull Boolean trouveViaLienCorrespondancePourCalcul) {
    this.trouveViaLienCorrespondancePourCalcul = trouveViaLienCorrespondancePourCalcul;
  }


  public AcquisCapitalise noteFinale(@jakarta.annotation.Nullable String noteFinale) {
    this.noteFinale = noteFinale;
    return this;
  }

  /**
   * La note finale
   * @return noteFinale
   */
  @jakarta.annotation.Nullable
  public String getNoteFinale() {
    return noteFinale;
  }

  public void setNoteFinale(@jakarta.annotation.Nullable String noteFinale) {
    this.noteFinale = noteFinale;
  }


  public AcquisCapitalise bareme(@jakarta.annotation.Nullable Integer bareme) {
    this.bareme = bareme;
    return this;
  }

  /**
   * Le barème (le maximum) de la note Obligatoirement renseigné si la note finale est présente.
   * @return bareme
   */
  @jakarta.annotation.Nullable
  public Integer getBareme() {
    return bareme;
  }

  public void setBareme(@jakarta.annotation.Nullable Integer bareme) {
    this.bareme = bareme;
  }


  public AcquisCapitalise libelleResultatFinal(@jakarta.annotation.Nonnull String libelleResultatFinal) {
    this.libelleResultatFinal = libelleResultatFinal;
    return this;
  }

  /**
   * Le libellé long du résultat final
   * @return libelleResultatFinal
   */
  @jakarta.annotation.Nonnull
  public String getLibelleResultatFinal() {
    return libelleResultatFinal;
  }

  public void setLibelleResultatFinal(@jakarta.annotation.Nonnull String libelleResultatFinal) {
    this.libelleResultatFinal = libelleResultatFinal;
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
   * @return the AcquisCapitalise instance itself
   */
  public AcquisCapitalise putAdditionalProperty(String key, Object value) {
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
    AcquisCapitalise acquisCapitalise = (AcquisCapitalise) o;
    return Objects.equals(this.libelleAffichagePeriodeAcquisition, acquisCapitalise.libelleAffichagePeriodeAcquisition) &&
        Objects.equals(this.codeObjetFormationAcquisition, acquisCapitalise.codeObjetFormationAcquisition) &&
        Objects.equals(this.trouveViaLienCorrespondancePourCalcul, acquisCapitalise.trouveViaLienCorrespondancePourCalcul) &&
        Objects.equals(this.noteFinale, acquisCapitalise.noteFinale) &&
        Objects.equals(this.bareme, acquisCapitalise.bareme) &&
        Objects.equals(this.libelleResultatFinal, acquisCapitalise.libelleResultatFinal)&&
        Objects.equals(this.additionalProperties, acquisCapitalise.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(libelleAffichagePeriodeAcquisition, codeObjetFormationAcquisition, trouveViaLienCorrespondancePourCalcul, noteFinale, bareme, libelleResultatFinal, additionalProperties);
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
    sb.append("class AcquisCapitalise {\n");
    sb.append("    libelleAffichagePeriodeAcquisition: ").append(toIndentedString(libelleAffichagePeriodeAcquisition)).append("\n");
    sb.append("    codeObjetFormationAcquisition: ").append(toIndentedString(codeObjetFormationAcquisition)).append("\n");
    sb.append("    trouveViaLienCorrespondancePourCalcul: ").append(toIndentedString(trouveViaLienCorrespondancePourCalcul)).append("\n");
    sb.append("    noteFinale: ").append(toIndentedString(noteFinale)).append("\n");
    sb.append("    bareme: ").append(toIndentedString(bareme)).append("\n");
    sb.append("    libelleResultatFinal: ").append(toIndentedString(libelleResultatFinal)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("libelleAffichagePeriodeAcquisition", "codeObjetFormationAcquisition", "trouveViaLienCorrespondancePourCalcul", "noteFinale", "bareme", "libelleResultatFinal"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("libelleAffichagePeriodeAcquisition", "codeObjetFormationAcquisition", "trouveViaLienCorrespondancePourCalcul", "libelleResultatFinal"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to AcquisCapitalise
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!AcquisCapitalise.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field(s) %s in AcquisCapitalise is not found in the empty JSON string", AcquisCapitalise.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : AcquisCapitalise.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("libelleAffichagePeriodeAcquisition").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `libelleAffichagePeriodeAcquisition` to be a primitive type in the JSON string but got `%s`", jsonObj.get("libelleAffichagePeriodeAcquisition").toString()));
      }
      if (!jsonObj.get("codeObjetFormationAcquisition").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `codeObjetFormationAcquisition` to be a primitive type in the JSON string but got `%s`", jsonObj.get("codeObjetFormationAcquisition").toString()));
      }
      if ((jsonObj.get("noteFinale") != null && !jsonObj.get("noteFinale").isJsonNull()) && !jsonObj.get("noteFinale").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `noteFinale` to be a primitive type in the JSON string but got `%s`", jsonObj.get("noteFinale").toString()));
      }
      if (!jsonObj.get("libelleResultatFinal").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `libelleResultatFinal` to be a primitive type in the JSON string but got `%s`", jsonObj.get("libelleResultatFinal").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!AcquisCapitalise.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'AcquisCapitalise' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<AcquisCapitalise> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(AcquisCapitalise.class));

       return (TypeAdapter<T>) new TypeAdapter<AcquisCapitalise>() {
           @Override
           public void write(JsonWriter out, AcquisCapitalise value) throws IOException {
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
           public AcquisCapitalise read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             AcquisCapitalise instance = thisAdapter.fromJsonTree(jsonObj);
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
   * Create an instance of AcquisCapitalise given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of AcquisCapitalise
   * @throws IOException if the JSON string is invalid with respect to AcquisCapitalise
   */
  public static AcquisCapitalise fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, AcquisCapitalise.class);
  }

  /**
   * Convert an instance of AcquisCapitalise to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

