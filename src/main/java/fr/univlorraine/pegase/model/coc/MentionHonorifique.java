/*
 * COC Publication v1 - Contrôle du cursus - Bloc «publication» - [EXTERNE]
 * Liste l'ensemble des services et des opérations disponible pour la publication de notes et des résultats à partir des données du module COC (Contrôle du cursus)  **Note importante :** Cette API est marquée [EXTERNE], elle peut être utilisée par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé (voir le paragraphe [Authentification](#section/Authentication) pour les détails).  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés    * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour   * 200 - Ok : L'opération s'est déroulée avec succès  * 201 - Created : L'opération a aboutie à la création d'une ressource  * 400 - Bad request :    * Un ou des paramètres d'entrées sont erronées    * Une erreur fonctionnelle s'est produite  * 404 - Not Found : La ressource demandée n'est pas trouvé    * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 1.4.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.coc;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * La mention honorifique (paramétrage \&quot;Mentions honorifiques\&quot; venant du référentiel)
 */
@ApiModel(description = "La mention honorifique (paramétrage \"Mentions honorifiques\" venant du référentiel)")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-06-06T11:03:30.102+02:00[Europe/Paris]")
public class MentionHonorifique {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;

  public static final String SERIALIZED_NAME_LIBELLE_LONG = "libelleLong";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG)
  private String libelleLong;

  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE = "libelleAffichage";
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE)
  private String libelleAffichage;

  public MentionHonorifique() { 
  }

  public MentionHonorifique code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de la mention honorifique
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "MHO002", required = true, value = "Le code de la mention honorifique")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public MentionHonorifique libelleCourt(String libelleCourt) {
    
    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Le libellé court de la mention honorifique
   * @return libelleCourt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "AB", required = true, value = "Le libellé court de la mention honorifique")

  public String getLibelleCourt() {
    return libelleCourt;
  }


  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public MentionHonorifique libelleLong(String libelleLong) {
    
    this.libelleLong = libelleLong;
    return this;
  }

   /**
   * Le libellé long de la mention honorifique
   * @return libelleLong
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "ASSEZ BIEN", required = true, value = "Le libellé long de la mention honorifique")

  public String getLibelleLong() {
    return libelleLong;
  }


  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }


  public MentionHonorifique libelleAffichage(String libelleAffichage) {
    
    this.libelleAffichage = libelleAffichage;
    return this;
  }

   /**
   * Le libellé d&#39;affichage (celui présenté aux apprenants) de la mention honorifique
   * @return libelleAffichage
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "Assez Bien", required = true, value = "Le libellé d'affichage (celui présenté aux apprenants) de la mention honorifique")

  public String getLibelleAffichage() {
    return libelleAffichage;
  }


  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MentionHonorifique mentionHonorifique = (MentionHonorifique) o;
    return Objects.equals(this.code, mentionHonorifique.code) &&
        Objects.equals(this.libelleCourt, mentionHonorifique.libelleCourt) &&
        Objects.equals(this.libelleLong, mentionHonorifique.libelleLong) &&
        Objects.equals(this.libelleAffichage, mentionHonorifique.libelleAffichage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong, libelleAffichage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MentionHonorifique {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
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

