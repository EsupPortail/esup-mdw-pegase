/*
 * INSCRIPTION Externe V2
 *  # Introduction  Liste l'ensemble des services et des opérations Ins (Module Inscription) identifiés pour un usage externe.  La documentation d'intégration de Pégase est publiée par version dans  [ce répertoire](https://share.pc-scol.fr/d/d98bdddb6485406b9422/).  Vous y retrouverez notamment des informations sur le modèle objet métier,  le versionning des APIs, les cas d'usage des APIs externes.  # Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l'aide d'un [token jwt](https://jwt.io/). Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`.  Lien vers [la documentation](https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1) qui décrit l'authentification aux APIs de Pegase.  # Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :   * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)     * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés     * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)   * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)   * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)   * boolean - Un booléen représenté par `true` ou `false`  # Gestion des erreurs  ## StatusCode  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | |         | Cas particulier: Dans le cas d'APIs de     | |         | type bulk, un 200 peut aussi être retourné | |         | si des données de la requête sont          | |         | considérées en erreur                      | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 403     | Accès refusé                               | | 404     | Ressource inexistante                      | | 409     | donnée déjà existante                      | | 500     | Erreur technique rencontrée par le serveur |   ## Codes d'erreurs  | Code      | Description                                | |-----------|--------------------------------------------| | notNull   | la propriété est obligatoire               | | notBlank  | la propriété ne doit pas être vide         | | size      | la longueur de la propriété est invalide   | | pattern   | les caractères ou la syntaxe de            | |           | la propriété est invalide                  | | genre     | le genre de la personne est invalide       | | dateEntre | la date est invalide                       | | telephone | le téléphone est invalide                  | | email     | le mail est invalide                       | 
 *
 * The version of the OpenAPI document: 2.2.4
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.insext.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.insext.model.TemoinSaisie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * DemandeDeContactSimple
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-05-21T11:48:43.188945300+02:00[Europe/Paris]")
public class DemandeDeContactSimple {
  public static final String SERIALIZED_NAME_EST1 = "est1";
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_CODE_STRUCTURE = "codeStructure";
  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE = "libelleAffichage";
  public static final String SERIALIZED_NAME_CANAL_COMMUNICATION = "canalCommunication";
  public static final String SERIALIZED_NAME_TEMOIN_SAISIE = "temoinSaisie";
  public static final String SERIALIZED_NAME_TEMOIN_SAISIE_PROPRIETAIRE = "temoinSaisieProprietaire";
  @SerializedName(SERIALIZED_NAME_EST1)
  protected String est1;
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_CODE_STRUCTURE)
  private String codeStructure;
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE)
  private String libelleAffichage;
  @SerializedName(SERIALIZED_NAME_CANAL_COMMUNICATION)
  private CanalCommunicationEnum canalCommunication;
  @SerializedName(SERIALIZED_NAME_TEMOIN_SAISIE)
  private TemoinSaisie temoinSaisie;
  @SerializedName(SERIALIZED_NAME_TEMOIN_SAISIE_PROPRIETAIRE)
  private TemoinSaisie temoinSaisieProprietaire;
  public DemandeDeContactSimple() {
    this.est1 = this.getClass().getSimpleName();
  }

  public DemandeDeContactSimple est1(String est1) {

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

  public DemandeDeContactSimple code(String code) {

    this.code = code;
    return this;
  }

   /**
   * Le code metier en saisie libre
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code metier en saisie libre")

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public DemandeDeContactSimple codeStructure(String codeStructure) {

    this.codeStructure = codeStructure;
    return this;
  }

   /**
   * Le code de l&#39;établissement (structure)
   * @return codeStructure
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code de l'établissement (structure)")

  public String getCodeStructure() {
    return codeStructure;
  }

  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }

  public DemandeDeContactSimple libelleAffichage(String libelleAffichage) {

    this.libelleAffichage = libelleAffichage;
    return this;
  }

   /**
   * Le libellé d&#39;affichage
   * @return libelleAffichage
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé d'affichage")

  public String getLibelleAffichage() {
    return libelleAffichage;
  }

  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }

  public DemandeDeContactSimple canalCommunication(CanalCommunicationEnum canalCommunication) {

    this.canalCommunication = canalCommunication;
    return this;
  }

   /**
   * le canal de communication
   * @return canalCommunication
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "le canal de communication")

  public CanalCommunicationEnum getCanalCommunication() {
    return canalCommunication;
  }

  public void setCanalCommunication(CanalCommunicationEnum canalCommunication) {
    this.canalCommunication = canalCommunication;
  }

  public DemandeDeContactSimple temoinSaisie(TemoinSaisie temoinSaisie) {

    this.temoinSaisie = temoinSaisie;
    return this;
  }

   /**
   * Get temoinSaisie
   * @return temoinSaisie
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public TemoinSaisie getTemoinSaisie() {
    return temoinSaisie;
  }

  public void setTemoinSaisie(TemoinSaisie temoinSaisie) {
    this.temoinSaisie = temoinSaisie;
  }

  public DemandeDeContactSimple temoinSaisieProprietaire(TemoinSaisie temoinSaisieProprietaire) {

    this.temoinSaisieProprietaire = temoinSaisieProprietaire;
    return this;
  }

   /**
   * Get temoinSaisieProprietaire
   * @return temoinSaisieProprietaire
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public TemoinSaisie getTemoinSaisieProprietaire() {
    return temoinSaisieProprietaire;
  }

  public void setTemoinSaisieProprietaire(TemoinSaisie temoinSaisieProprietaire) {
    this.temoinSaisieProprietaire = temoinSaisieProprietaire;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DemandeDeContactSimple demandeDeContactSimple = (DemandeDeContactSimple) o;
    return Objects.equals(this.est1, demandeDeContactSimple.est1) &&
        Objects.equals(this.code, demandeDeContactSimple.code) &&
        Objects.equals(this.codeStructure, demandeDeContactSimple.codeStructure) &&
        Objects.equals(this.libelleAffichage, demandeDeContactSimple.libelleAffichage) &&
        Objects.equals(this.canalCommunication, demandeDeContactSimple.canalCommunication) &&
        Objects.equals(this.temoinSaisie, demandeDeContactSimple.temoinSaisie) &&
        Objects.equals(this.temoinSaisieProprietaire, demandeDeContactSimple.temoinSaisieProprietaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(est1, code, codeStructure, libelleAffichage, canalCommunication, temoinSaisie, temoinSaisieProprietaire);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DemandeDeContactSimple {\n");
    sb.append("    est1: ").append(toIndentedString(est1)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    canalCommunication: ").append(toIndentedString(canalCommunication)).append("\n");
    sb.append("    temoinSaisie: ").append(toIndentedString(temoinSaisie)).append("\n");
    sb.append("    temoinSaisieProprietaire: ").append(toIndentedString(temoinSaisieProprietaire)).append("\n");
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
   * le canal de communication
   */
  @JsonAdapter(CanalCommunicationEnum.Adapter.class)
  public enum CanalCommunicationEnum {
    MAIL("MAIL"),

    TEL("TEL"),

    ADRESSE("ADRESSE");

    private String value;

    CanalCommunicationEnum(String value) {
      this.value = value;
    }

    public static CanalCommunicationEnum fromValue(String value) {
      for (CanalCommunicationEnum b : CanalCommunicationEnum.values()) {
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

    public static class Adapter extends TypeAdapter<CanalCommunicationEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CanalCommunicationEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CanalCommunicationEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return CanalCommunicationEnum.fromValue(value);
      }
    }
  }

}

