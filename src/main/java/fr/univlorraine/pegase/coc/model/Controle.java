/*
 * COC Publication v2 - Contrôle du cursus - Bloc «publication» - [EXTERNE]
 * Liste l'ensemble des services et des opérations disponible pour la publication de notes et des résultats à partir des données du module COC (Contrôle du cursus)  **Note importante :** Cette API est marquée [EXTERNE], elle peut être utilisée par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé (voir le paragraphe [Authentification](#section/Authentication) pour les détails).  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés    * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour   * 200 - Ok : L'opération s'est déroulée avec succès  * 201 - Created : L'opération a aboutie à la création d'une ressource  * 400 - Bad request :    * Un ou des paramètres d'entrées sont erronées    * Une erreur fonctionnelle s'est produite  * 404 - Not Found : La ressource demandée n'est pas trouvé    * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.coc.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.coc.model.Absence;
import fr.univlorraine.pegase.coc.model.Resultat;
import fr.univlorraine.pegase.coc.model.TypeControle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * Controle
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:30:29.354622700+02:00[Europe/Paris]")
public class Controle {
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_TYPE = "type";
  public static final String SERIALIZED_NAME_LIBELLE = "libelle";
  public static final String SERIALIZED_NAME_NUMERO_SESSION = "numeroSession";
  public static final String SERIALIZED_NAME_NOTE = "note";
  public static final String SERIALIZED_NAME_RESULTAT = "resultat";
  public static final String SERIALIZED_NAME_ABSENCE = "absence";
  public static final String SERIALIZED_NAME_COEFFICIENT_SESSION1 = "coefficientSession1";
  public static final String SERIALIZED_NAME_COEFFICIENT_SESSION2 = "coefficientSession2";
  public static final String SERIALIZED_NAME_COEFFICIENT_FINAL = "coefficientFinal";
  public static final String SERIALIZED_NAME_DELIBERE = "delibere";
  public static final String SERIALIZED_NAME_PUBLIE = "publie";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeControle type;
  @SerializedName(SERIALIZED_NAME_LIBELLE)
  private String libelle;
  @SerializedName(SERIALIZED_NAME_NUMERO_SESSION)
  private Integer numeroSession;
  @SerializedName(SERIALIZED_NAME_NOTE)
  private BigDecimal note;
  @SerializedName(SERIALIZED_NAME_RESULTAT)
  private Resultat resultat;
  @SerializedName(SERIALIZED_NAME_ABSENCE)
  private Absence absence;
  @SerializedName(SERIALIZED_NAME_COEFFICIENT_SESSION1)
  private BigDecimal coefficientSession1;
  @SerializedName(SERIALIZED_NAME_COEFFICIENT_SESSION2)
  private BigDecimal coefficientSession2;
  @SerializedName(SERIALIZED_NAME_COEFFICIENT_FINAL)
  private BigDecimal coefficientFinal;
  @SerializedName(SERIALIZED_NAME_DELIBERE)
  private Boolean delibere;
  @SerializedName(SERIALIZED_NAME_PUBLIE)
  private Boolean publie;

  public Controle() { 
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  public Controle code(String code) {

    this.code = code;
    return this;
  }

   /**
   * Le code du contrôle
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "CTRL27", required = true, value = "Le code du contrôle")

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Controle type(TypeControle type) {

    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public TypeControle getType() {
    return type;
  }

  public void setType(TypeControle type) {
    this.type = type;
  }

  public Controle libelle(String libelle) {

    this.libelle = libelle;
    return this;
  }

   /**
   * Le libellé du contrôle
   * @return libelle
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "Oral de biochimie", required = true, value = "Le libellé du contrôle")

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public Controle numeroSession(Integer numeroSession) {

    this.numeroSession = numeroSession;
    return this;
  }

   /**
   * Le numéro de session du contrôle
   * @return numeroSession
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "1", required = true, value = "Le numéro de session du contrôle")

  public Integer getNumeroSession() {
    return numeroSession;
  }

  public void setNumeroSession(Integer numeroSession) {
    this.numeroSession = numeroSession;
  }

  public Controle note(BigDecimal note) {

    this.note = note;
    return this;
  }

   /**
   * La note obtenue lors de ce contrôle
   * @return note
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "15", value = "La note obtenue lors de ce contrôle")

  public BigDecimal getNote() {
    return note;
  }

  public void setNote(BigDecimal note) {
    this.note = note;
  }

  public Controle resultat(Resultat resultat) {

    this.resultat = resultat;
    return this;
  }

   /**
   * Get resultat
   * @return resultat
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Resultat getResultat() {
    return resultat;
  }

  public void setResultat(Resultat resultat) {
    this.resultat = resultat;
  }

  public Controle absence(Absence absence) {

    this.absence = absence;
    return this;
  }

   /**
   * Get absence
   * @return absence
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Absence getAbsence() {
    return absence;
  }

  public void setAbsence(Absence absence) {
    this.absence = absence;
  }

  public Controle coefficientSession1(BigDecimal coefficientSession1) {

    this.coefficientSession1 = coefficientSession1;
    return this;
  }

   /**
   * Le coefficient utilisé pour le calcul de la note de session 1 de l&#39;objet.
   * @return coefficientSession1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2", value = "Le coefficient utilisé pour le calcul de la note de session 1 de l'objet. ")

  public BigDecimal getCoefficientSession1() {
    return coefficientSession1;
  }

  public void setCoefficientSession1(BigDecimal coefficientSession1) {
    this.coefficientSession1 = coefficientSession1;
  }

  public Controle coefficientSession2(BigDecimal coefficientSession2) {

    this.coefficientSession2 = coefficientSession2;
    return this;
  }

   /**
   * Le coefficient utilisé pour le calcul de la note de session 2 de l&#39;objet si l&#39;apprenant est concerné par la session 2 sur l&#39;objet.
   * @return coefficientSession2
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "Le coefficient utilisé pour le calcul de la note de session 2 de l'objet si l'apprenant est concerné par la session 2 sur l'objet. ")

  public BigDecimal getCoefficientSession2() {
    return coefficientSession2;
  }

  public void setCoefficientSession2(BigDecimal coefficientSession2) {
    this.coefficientSession2 = coefficientSession2;
  }

  public Controle coefficientFinal(BigDecimal coefficientFinal) {

    this.coefficientFinal = coefficientFinal;
    return this;
  }

   /**
   * Le coefficient utilisé pour le calcul de la note de l&#39;objet.  C&#39;est soit le &#x60;coefficientSession1&#x60; ou le &#x60;coefficientSession2&#x60; en fonction de la session retenue de l&#39;objet.  Voir aussi &#x60;numeroSessionRetenue&#x60;.
   * @return coefficientFinal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "Le coefficient utilisé pour le calcul de la note de l'objet.  C'est soit le `coefficientSession1` ou le `coefficientSession2` en fonction de la session retenue de l'objet.  Voir aussi `numeroSessionRetenue`. ")

  public BigDecimal getCoefficientFinal() {
    return coefficientFinal;
  }

  public void setCoefficientFinal(BigDecimal coefficientFinal) {
    this.coefficientFinal = coefficientFinal;
  }

  public Controle delibere(Boolean delibere) {

    this.delibere = delibere;
    return this;
  }

   /**
   * Est-ce que ce contrôle a été délibéré ?
   * @return delibere
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "true", value = "Est-ce que ce contrôle a été délibéré ? ")

  public Boolean getDelibere() {
    return delibere;
  }

  public void setDelibere(Boolean delibere) {
    this.delibere = delibere;
  }

  public Controle publie(Boolean publie) {

    this.publie = publie;
    return this;
  }

   /**
   * Est-ce que la publication des données de ce contrôle est autorisée ?  Si ce booléen est à &#x60;false&#x60;, alors les attributs suivants ne sont jamais renseignés (toujours à &#x60;null&#x60;) :   * note   * resultat   * absence   * coefficientSession1   * coefficientSession2   * coefficientFinal   * delibere
   * @return publie
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "true", required = true, value = "Est-ce que la publication des données de ce contrôle est autorisée ?  Si ce booléen est à `false`, alors les attributs suivants ne sont jamais renseignés (toujours à `null`) :   * note   * resultat   * absence   * coefficientSession1   * coefficientSession2   * coefficientFinal   * delibere ")

  public Boolean getPublie() {
    return publie;
  }

  public void setPublie(Boolean publie) {
    this.publie = publie;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Controle controle = (Controle) o;
    return Objects.equals(this.code, controle.code) &&
        Objects.equals(this.type, controle.type) &&
        Objects.equals(this.libelle, controle.libelle) &&
        Objects.equals(this.numeroSession, controle.numeroSession) &&
        Objects.equals(this.note, controle.note) &&
        Objects.equals(this.resultat, controle.resultat) &&
        Objects.equals(this.absence, controle.absence) &&
        Objects.equals(this.coefficientSession1, controle.coefficientSession1) &&
        Objects.equals(this.coefficientSession2, controle.coefficientSession2) &&
        Objects.equals(this.coefficientFinal, controle.coefficientFinal) &&
        Objects.equals(this.delibere, controle.delibere) &&
        Objects.equals(this.publie, controle.publie);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, type, libelle, numeroSession, note, resultat, absence, coefficientSession1, coefficientSession2, coefficientFinal, delibere, publie);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Controle {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
    sb.append("    numeroSession: ").append(toIndentedString(numeroSession)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    resultat: ").append(toIndentedString(resultat)).append("\n");
    sb.append("    absence: ").append(toIndentedString(absence)).append("\n");
    sb.append("    coefficientSession1: ").append(toIndentedString(coefficientSession1)).append("\n");
    sb.append("    coefficientSession2: ").append(toIndentedString(coefficientSession2)).append("\n");
    sb.append("    coefficientFinal: ").append(toIndentedString(coefficientFinal)).append("\n");
    sb.append("    delibere: ").append(toIndentedString(delibere)).append("\n");
    sb.append("    publie: ").append(toIndentedString(publie)).append("\n");
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

