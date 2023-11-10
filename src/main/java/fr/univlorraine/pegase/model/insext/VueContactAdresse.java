/*
 * INSCRIPTION Externe V1 [EXTERNE]
 * Liste l'ensemble des services et des opérations Ins (Module Inscription) marquées comme [EXTERNE].  Ils peuvent être utilisés par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l'aide d'un [token jwt](https://jwt.io/). Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`.  Lien vers la documentation qui décrit l'authentification aux APIs de Pegase : [pdf](https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1)  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :   * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)     * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés     * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)   * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)   * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)   * boolean - Un booléen représenté par `true` ou `false`  ### Code retour  * 200 - Ok : L'opération s'est déroulée avec succès * 201 - Created : L'opération a aboutie à la création d'une ressource * 400 - Bad request :   * Un ou des paramètres d'entrées sont erronées   * Une erreur fonctionnelle s'est produite * 401 - Unauthorized - Vous n'êtes pas authentifié   * Il n'y a pas de token passé dans le header HTTP `Authorization`   * Le token passé n'est pas au bon format (Bearer <[token-jwt](https://jwt.io/)>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération   * La signature du token est incorrecte / n'a pas pû être vérifiée   * Le token est expiré   * Les habilitations de l'utilisateur ne permettent pas d'exécuter cette opération. Vérifier les droits de l'utilisateur * 404 - Not Found : La ressource demandée n'est pas trouvé     * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 1.2.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insext;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.model.insext.VueContact;
import fr.univlorraine.pegase.model.insext.VueContactAdresseAllOf;
import fr.univlorraine.pegase.model.insext.ContactComplet.CanalCommunicationEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * VueContactAdresse
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T10:08:44.291562300+01:00[Europe/Paris]")
public class VueContactAdresse extends VueContact {
  public static final String SERIALIZED_NAME_PAYS = "pays";
  @SerializedName(SERIALIZED_NAME_PAYS)
  private String pays;

  public static final String SERIALIZED_NAME_LIGNE1_OU_ETAGE = "ligne1OuEtage";
  @SerializedName(SERIALIZED_NAME_LIGNE1_OU_ETAGE)
  private String ligne1OuEtage;

  public static final String SERIALIZED_NAME_LIGNE2_OU_BATIMENT = "ligne2OuBatiment";
  @SerializedName(SERIALIZED_NAME_LIGNE2_OU_BATIMENT)
  private String ligne2OuBatiment;

  public static final String SERIALIZED_NAME_LIGNE3_OU_VOIE = "ligne3OuVoie";
  @SerializedName(SERIALIZED_NAME_LIGNE3_OU_VOIE)
  private String ligne3OuVoie;

  public static final String SERIALIZED_NAME_LIGNE4_OU_COMPLEMENT = "ligne4OuComplement";
  @SerializedName(SERIALIZED_NAME_LIGNE4_OU_COMPLEMENT)
  private String ligne4OuComplement;

  public static final String SERIALIZED_NAME_LIGNE5_ETRANGER = "ligne5Etranger";
  @SerializedName(SERIALIZED_NAME_LIGNE5_ETRANGER)
  private String ligne5Etranger;

  public static final String SERIALIZED_NAME_CODE_POSTAL = "codePostal";
  @SerializedName(SERIALIZED_NAME_CODE_POSTAL)
  private String codePostal;

  public static final String SERIALIZED_NAME_COMMUNE = "commune";
  @SerializedName(SERIALIZED_NAME_COMMUNE)
  private String commune;

  public VueContactAdresse() { 
    this.canalCommunication = CanalCommunicationEnum.fromValue(this.getClass().getSimpleName());
  }

  public VueContactAdresse pays(String pays) {
    
    this.pays = pays;
    return this;
  }

   /**
   * Code INSEE du pays (100 pour la France, sinon l&#39;adresse est à l&#39;étranger)
   * @return pays
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Code INSEE du pays (100 pour la France, sinon l'adresse est à l'étranger)")

  public String getPays() {
    return pays;
  }


  public void setPays(String pays) {
    this.pays = pays;
  }


  public VueContactAdresse ligne1OuEtage(String ligne1OuEtage) {
    
    this.ligne1OuEtage = ligne1OuEtage;
    return this;
  }

   /**
   * Le n°app/étage/couloir/esc/chez ou ligne 1 de l&#39;adresse étrangère
   * @return ligne1OuEtage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le n°app/étage/couloir/esc/chez ou ligne 1 de l'adresse étrangère")

  public String getLigne1OuEtage() {
    return ligne1OuEtage;
  }


  public void setLigne1OuEtage(String ligne1OuEtage) {
    this.ligne1OuEtage = ligne1OuEtage;
  }


  public VueContactAdresse ligne2OuBatiment(String ligne2OuBatiment) {
    
    this.ligne2OuBatiment = ligne2OuBatiment;
    return this;
  }

   /**
   * L&#39;entrée/bâtiment/immeuble/résidence ou ligne 2 de l&#39;adresse étrangère
   * @return ligne2OuBatiment
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'entrée/bâtiment/immeuble/résidence ou ligne 2 de l'adresse étrangère")

  public String getLigne2OuBatiment() {
    return ligne2OuBatiment;
  }


  public void setLigne2OuBatiment(String ligne2OuBatiment) {
    this.ligne2OuBatiment = ligne2OuBatiment;
  }


  public VueContactAdresse ligne3OuVoie(String ligne3OuVoie) {
    
    this.ligne3OuVoie = ligne3OuVoie;
    return this;
  }

   /**
   * Le numéro et le libellé de la voie ou ligne 3 de l&#39;adresse étrangère
   * @return ligne3OuVoie
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le numéro et le libellé de la voie ou ligne 3 de l'adresse étrangère")

  public String getLigne3OuVoie() {
    return ligne3OuVoie;
  }


  public void setLigne3OuVoie(String ligne3OuVoie) {
    this.ligne3OuVoie = ligne3OuVoie;
  }


  public VueContactAdresse ligne4OuComplement(String ligne4OuComplement) {
    
    this.ligne4OuComplement = ligne4OuComplement;
    return this;
  }

   /**
   * Le lieu-dit ou service particulier de distribution ou ligne 4 de l&#39;adresse étrangère
   * @return ligne4OuComplement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le lieu-dit ou service particulier de distribution ou ligne 4 de l'adresse étrangère")

  public String getLigne4OuComplement() {
    return ligne4OuComplement;
  }


  public void setLigne4OuComplement(String ligne4OuComplement) {
    this.ligne4OuComplement = ligne4OuComplement;
  }


  public VueContactAdresse ligne5Etranger(String ligne5Etranger) {
    
    this.ligne5Etranger = ligne5Etranger;
    return this;
  }

   /**
   * Ligne 5 de l&#39;adresse étrangère (vide si adresse en France)
   * @return ligne5Etranger
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Ligne 5 de l'adresse étrangère (vide si adresse en France)")

  public String getLigne5Etranger() {
    return ligne5Etranger;
  }


  public void setLigne5Etranger(String ligne5Etranger) {
    this.ligne5Etranger = ligne5Etranger;
  }


  public VueContactAdresse codePostal(String codePostal) {
    
    this.codePostal = codePostal;
    return this;
  }

   /**
   * Le code postal de l&#39;adresse en France (vide si adresse étrangère)
   * @return codePostal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code postal de l'adresse en France (vide si adresse étrangère)")

  public String getCodePostal() {
    return codePostal;
  }


  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }


  public VueContactAdresse commune(String commune) {
    
    this.commune = commune;
    return this;
  }

   /**
   * Le code INSEE de la commune de l&#39;adresse en France (vide si adresse étrangère)
   * @return commune
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code INSEE de la commune de l'adresse en France (vide si adresse étrangère)")

  public String getCommune() {
    return commune;
  }


  public void setCommune(String commune) {
    this.commune = commune;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VueContactAdresse vueContactAdresse = (VueContactAdresse) o;
    return Objects.equals(this.pays, vueContactAdresse.pays) &&
        Objects.equals(this.ligne1OuEtage, vueContactAdresse.ligne1OuEtage) &&
        Objects.equals(this.ligne2OuBatiment, vueContactAdresse.ligne2OuBatiment) &&
        Objects.equals(this.ligne3OuVoie, vueContactAdresse.ligne3OuVoie) &&
        Objects.equals(this.ligne4OuComplement, vueContactAdresse.ligne4OuComplement) &&
        Objects.equals(this.ligne5Etranger, vueContactAdresse.ligne5Etranger) &&
        Objects.equals(this.codePostal, vueContactAdresse.codePostal) &&
        Objects.equals(this.commune, vueContactAdresse.commune) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pays, ligne1OuEtage, ligne2OuBatiment, ligne3OuVoie, ligne4OuComplement, ligne5Etranger, codePostal, commune, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VueContactAdresse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    pays: ").append(toIndentedString(pays)).append("\n");
    sb.append("    ligne1OuEtage: ").append(toIndentedString(ligne1OuEtage)).append("\n");
    sb.append("    ligne2OuBatiment: ").append(toIndentedString(ligne2OuBatiment)).append("\n");
    sb.append("    ligne3OuVoie: ").append(toIndentedString(ligne3OuVoie)).append("\n");
    sb.append("    ligne4OuComplement: ").append(toIndentedString(ligne4OuComplement)).append("\n");
    sb.append("    ligne5Etranger: ").append(toIndentedString(ligne5Etranger)).append("\n");
    sb.append("    codePostal: ").append(toIndentedString(codePostal)).append("\n");
    sb.append("    commune: ").append(toIndentedString(commune)).append("\n");
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

