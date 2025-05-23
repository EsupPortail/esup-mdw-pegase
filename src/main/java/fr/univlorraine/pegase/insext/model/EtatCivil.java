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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * EtatCivil
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-05-21T11:48:43.188945300+02:00[Europe/Paris]")
public class EtatCivil {
  public static final String SERIALIZED_NAME_NOM_DE_NAISSANCE = "nomDeNaissance";
  public static final String SERIALIZED_NAME_NOM_USUEL = "nomUsuel";
  public static final String SERIALIZED_NAME_PRENOM = "prenom";
  public static final String SERIALIZED_NAME_DEUXIEME_PRENOM = "deuxiemePrenom";
  public static final String SERIALIZED_NAME_TROISIEME_PRENOM = "troisiemePrenom";
  public static final String SERIALIZED_NAME_GENRE = "genre";
  @SerializedName(SERIALIZED_NAME_NOM_DE_NAISSANCE)
  private String nomDeNaissance;
  @SerializedName(SERIALIZED_NAME_NOM_USUEL)
  private String nomUsuel;
  @SerializedName(SERIALIZED_NAME_PRENOM)
  private String prenom;
  @SerializedName(SERIALIZED_NAME_DEUXIEME_PRENOM)
  private String deuxiemePrenom;
  @SerializedName(SERIALIZED_NAME_TROISIEME_PRENOM)
  private String troisiemePrenom;
  @SerializedName(SERIALIZED_NAME_GENRE)
  private String genre;

  public EtatCivil() { 
  }

  public EtatCivil nomDeNaissance(String nomDeNaissance) {
    
    this.nomDeNaissance = nomDeNaissance;
    return this;
  }

   /**
   * Le nom de naissance
   * @return nomDeNaissance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le nom de naissance")

  public String getNomDeNaissance() {
    return nomDeNaissance;
  }


  public void setNomDeNaissance(String nomDeNaissance) {
    this.nomDeNaissance = nomDeNaissance;
  }


  public EtatCivil nomUsuel(String nomUsuel) {
    
    this.nomUsuel = nomUsuel;
    return this;
  }

   /**
   * Le nom usuel
   * @return nomUsuel
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le nom usuel")

  public String getNomUsuel() {
    return nomUsuel;
  }


  public void setNomUsuel(String nomUsuel) {
    this.nomUsuel = nomUsuel;
  }


  public EtatCivil prenom(String prenom) {
    
    this.prenom = prenom;
    return this;
  }

   /**
   * Le prénom
   * @return prenom
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le prénom")

  public String getPrenom() {
    return prenom;
  }


  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }


  public EtatCivil deuxiemePrenom(String deuxiemePrenom) {
    
    this.deuxiemePrenom = deuxiemePrenom;
    return this;
  }

   /**
   * Le deuxième prénom
   * @return deuxiemePrenom
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le deuxième prénom")

  public String getDeuxiemePrenom() {
    return deuxiemePrenom;
  }


  public void setDeuxiemePrenom(String deuxiemePrenom) {
    this.deuxiemePrenom = deuxiemePrenom;
  }


  public EtatCivil troisiemePrenom(String troisiemePrenom) {
    
    this.troisiemePrenom = troisiemePrenom;
    return this;
  }

   /**
   * Le troisième prénom
   * @return troisiemePrenom
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le troisième prénom")

  public String getTroisiemePrenom() {
    return troisiemePrenom;
  }


  public void setTroisiemePrenom(String troisiemePrenom) {
    this.troisiemePrenom = troisiemePrenom;
  }


  public EtatCivil genre(String genre) {
    
    this.genre = genre;
    return this;
  }

   /**
   * Les valeurs &#x60;M&#x60; ou &#x60;F&#x60; sont acceptées
   * @return genre
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Les valeurs `M` ou `F` sont acceptées")

  public String getGenre() {
    return genre;
  }


  public void setGenre(String genre) {
    this.genre = genre;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EtatCivil etatCivil = (EtatCivil) o;
    return Objects.equals(this.nomDeNaissance, etatCivil.nomDeNaissance) &&
        Objects.equals(this.nomUsuel, etatCivil.nomUsuel) &&
        Objects.equals(this.prenom, etatCivil.prenom) &&
        Objects.equals(this.deuxiemePrenom, etatCivil.deuxiemePrenom) &&
        Objects.equals(this.troisiemePrenom, etatCivil.troisiemePrenom) &&
        Objects.equals(this.genre, etatCivil.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nomDeNaissance, nomUsuel, prenom, deuxiemePrenom, troisiemePrenom, genre);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EtatCivil {\n");
    sb.append("    nomDeNaissance: ").append(toIndentedString(nomDeNaissance)).append("\n");
    sb.append("    nomUsuel: ").append(toIndentedString(nomUsuel)).append("\n");
    sb.append("    prenom: ").append(toIndentedString(prenom)).append("\n");
    sb.append("    deuxiemePrenom: ").append(toIndentedString(deuxiemePrenom)).append("\n");
    sb.append("    troisiemePrenom: ").append(toIndentedString(troisiemePrenom)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
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

