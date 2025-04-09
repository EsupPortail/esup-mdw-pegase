/*
 * PIECE Externe V1
 *  # Introduction  Liste l'ensemble des services et des opérations externes disponibles dans le module Piece  La documentation d'intégration de Pégase est publiée par version dans  [ce répertoire](https://share.pc-scol.fr/d/d98bdddb6485406b9422/).  Vous y retrouverez notamment des informations sur le modèle objet métier,  le versionning des APIs, les cas d'usage des APIs externes.  # Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l'aide d'un [token jwt](https://jwt.io/). Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`.  Lien vers [la documentation](https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1) qui décrit l'authentification aux APIs de Pegase.  # Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :   * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)     * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés     * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)   * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)   * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)   * boolean - Un booléen représenté par `true` ou `false`  # Gestion des erreurs  ## StatusCode  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | |         | Cas particulier: Dans le cas d'APIs de     | |         | type bulk, un 200 peut aussi être retourné | |         | si des données de la requête sont          | |         | considérées en erreur                      | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 403     | Accès refusé                               | | 404     | Ressource inexistante                      | | 409     | donnée déjà existante                      | | 500     | Erreur technique rencontrée par le serveur |   ## Codes d'erreurs  | Code      | Description                                | |-----------|--------------------------------------------| | notNull   | la propriété est obligatoire               | | notBlank  | la propriété ne doit pas être vide         | | size      | la longueur de la propriété est invalide   | | pattern   | les caractères ou la syntaxe de            | |           | la propriété est invalide                  | | genre     | le genre de la personne est invalide       | | dateEntre | la date est invalide                       | | telephone | le téléphone est invalide                  | | email     | le mail est invalide                       | 
 *
 * The version of the OpenAPI document: 1.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pieceext;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * ValiditePeriode
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-04T15:38:18.494892400+02:00[Europe/Paris]")
public class ValiditePeriode {
  public static final String SERIALIZED_NAME_DATE_DEBUT = "dateDebut";
  public static final String SERIALIZED_NAME_DATE_FIN = "dateFin";
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT)
  private Date dateDebut;
  @SerializedName(SERIALIZED_NAME_DATE_FIN)
  private Date dateFin;

  public ValiditePeriode() { 
  }

  public ValiditePeriode dateDebut(Date dateDebut) {
    
    this.dateDebut = dateDebut;
    return this;
  }

   /**
   * Date au format yyyy-mm-dd
   * @return dateDebut
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "Sat Jan 30 01:00:00 CET 2021", required = true, value = "Date au format yyyy-mm-dd")

  public Date getDateDebut() {
    return dateDebut;
  }


  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }


  public ValiditePeriode dateFin(Date dateFin) {
    
    this.dateFin = dateFin;
    return this;
  }

   /**
   * Date au format yyyy-mm-dd
   * @return dateFin
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "Sat Jan 30 01:00:00 CET 2021", required = true, value = "Date au format yyyy-mm-dd")

  public Date getDateFin() {
    return dateFin;
  }


  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValiditePeriode validitePeriode = (ValiditePeriode) o;
    return Objects.equals(this.dateDebut, validitePeriode.dateDebut) &&
        Objects.equals(this.dateFin, validitePeriode.dateFin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateDebut, dateFin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValiditePeriode {\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
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

