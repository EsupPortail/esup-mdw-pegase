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
import fr.univlorraine.pegase.model.insext.DocumentAApprouver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * L&#39;état d&#39;un accord et sa référence
 */
@ApiModel(description = "L'état d'un accord et sa référence")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T10:08:44.291562300+01:00[Europe/Paris]")
public class AccordComplet {
  public static final String SERIALIZED_NAME_DATE_VALIDATION = "dateValidation";
  @SerializedName(SERIALIZED_NAME_DATE_VALIDATION)
  private String dateValidation;

  public static final String SERIALIZED_NAME_EST_ACCEPTE = "estAccepte";
  @SerializedName(SERIALIZED_NAME_EST_ACCEPTE)
  private Boolean estAccepte;

  public static final String SERIALIZED_NAME_DOCUMENT_A_APPROUVER = "documentAApprouver";
  @SerializedName(SERIALIZED_NAME_DOCUMENT_A_APPROUVER)
  private DocumentAApprouver documentAApprouver;

  public AccordComplet() { 
  }

  public AccordComplet dateValidation(String dateValidation) {
    
    this.dateValidation = dateValidation;
    return this;
  }

   /**
   * Date de validation de l&#39;accord
   * @return dateValidation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Date de validation de l'accord")

  public String getDateValidation() {
    return dateValidation;
  }


  public void setDateValidation(String dateValidation) {
    this.dateValidation = dateValidation;
  }


  public AccordComplet estAccepte(Boolean estAccepte) {
    
    this.estAccepte = estAccepte;
    return this;
  }

   /**
   * Témoin pour savoir si le document a été accepté ou refusé
   * @return estAccepte
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Témoin pour savoir si le document a été accepté ou refusé")

  public Boolean getEstAccepte() {
    return estAccepte;
  }


  public void setEstAccepte(Boolean estAccepte) {
    this.estAccepte = estAccepte;
  }


  public AccordComplet documentAApprouver(DocumentAApprouver documentAApprouver) {
    
    this.documentAApprouver = documentAApprouver;
    return this;
  }

   /**
   * Get documentAApprouver
   * @return documentAApprouver
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public DocumentAApprouver getDocumentAApprouver() {
    return documentAApprouver;
  }


  public void setDocumentAApprouver(DocumentAApprouver documentAApprouver) {
    this.documentAApprouver = documentAApprouver;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccordComplet accordComplet = (AccordComplet) o;
    return Objects.equals(this.dateValidation, accordComplet.dateValidation) &&
        Objects.equals(this.estAccepte, accordComplet.estAccepte) &&
        Objects.equals(this.documentAApprouver, accordComplet.documentAApprouver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateValidation, estAccepte, documentAApprouver);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccordComplet {\n");
    sb.append("    dateValidation: ").append(toIndentedString(dateValidation)).append("\n");
    sb.append("    estAccepte: ").append(toIndentedString(estAccepte)).append("\n");
    sb.append("    documentAApprouver: ").append(toIndentedString(documentAApprouver)).append("\n");
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

