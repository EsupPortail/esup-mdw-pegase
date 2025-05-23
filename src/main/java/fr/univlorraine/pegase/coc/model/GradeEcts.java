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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Le grade ECTS (European Credits Transfer System) (paramétrage venant du référentiel)
 */
@ApiModel(description = "Le grade ECTS (European Credits Transfer System) (paramétrage venant du référentiel)")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:30:29.354622700+02:00[Europe/Paris]")
public class GradeEcts {
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_VALEUR = "valeur";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_VALEUR)
  private String valeur;

  public GradeEcts() { 
  }

  public GradeEcts code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de la notation ECTS
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "NEC002", required = true, value = "Le code de la notation ECTS")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public GradeEcts valeur(String valeur) {
    
    this.valeur = valeur;
    return this;
  }

   /**
   * La valeur de la notation ECTS
   * @return valeur
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "B", required = true, value = "La valeur de la notation ECTS")

  public String getValeur() {
    return valeur;
  }


  public void setValeur(String valeur) {
    this.valeur = valeur;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GradeEcts gradeEcts = (GradeEcts) o;
    return Objects.equals(this.code, gradeEcts.code) &&
        Objects.equals(this.valeur, gradeEcts.valeur);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, valeur);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GradeEcts {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    valeur: ").append(toIndentedString(valeur)).append("\n");
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

