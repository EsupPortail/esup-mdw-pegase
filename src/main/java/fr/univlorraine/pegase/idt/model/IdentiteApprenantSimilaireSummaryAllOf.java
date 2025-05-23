/*
 * IDT V1 
 *  # Introduction  Liste l'ensemble des services et des opérations disponibles dans le module IDT  Description service IDT  # Gestion des erreurs  ## StatusCode  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | |         | Cas particulier: Dans le cas d'APIs de     | |         | type bulk, un 200 peut aussi être retourné | |         | si des données de la requête sont          | |         | considérées en erreur                      | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 403     | Accès refusé                               | | 404     | Ressource inexistante                      | | 409     | donnée déjà existante                      | | 500     | Erreur technique rencontrée par le serveur |   ## Codes d'erreurs  | Code      | Description                                | |-----------|--------------------------------------------| | notNull   | la propriété est obligatoire               | | notBlank  | la propriété ne doit pas être vide         | | size      | la longueur de la propriété est invalide   | | pattern   | les caractères ou la syntaxe de            | |           | la propriété est invalide                  | 
 *
 * The version of the OpenAPI document: 1.0.0-rc.20250415115823
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.idt.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * IdentiteApprenantSimilaireSummaryAllOf
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:08:07.402704300+02:00[Europe/Paris]")
public class IdentiteApprenantSimilaireSummaryAllOf {
  public static final String SERIALIZED_NAME_SCORE_SIMILARITE = "scoreSimilarite";
  @SerializedName(SERIALIZED_NAME_SCORE_SIMILARITE)
  private Float scoreSimilarite;

  public IdentiteApprenantSimilaireSummaryAllOf() { 
  }

  public IdentiteApprenantSimilaireSummaryAllOf scoreSimilarite(Float scoreSimilarite) {
    
    this.scoreSimilarite = scoreSimilarite;
    return this;
  }

   /**
   * Get scoreSimilarite
   * @return scoreSimilarite
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Float getScoreSimilarite() {
    return scoreSimilarite;
  }


  public void setScoreSimilarite(Float scoreSimilarite) {
    this.scoreSimilarite = scoreSimilarite;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdentiteApprenantSimilaireSummaryAllOf identiteApprenantSimilaireSummaryAllOf = (IdentiteApprenantSimilaireSummaryAllOf) o;
    return Objects.equals(this.scoreSimilarite, identiteApprenantSimilaireSummaryAllOf.scoreSimilarite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scoreSimilarite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IdentiteApprenantSimilaireSummaryAllOf {\n");
    sb.append("    scoreSimilarite: ").append(toIndentedString(scoreSimilarite)).append("\n");
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

