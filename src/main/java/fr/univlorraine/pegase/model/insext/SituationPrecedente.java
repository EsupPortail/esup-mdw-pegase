/*
 * INSCRIPTION Externe V1 [EXTERNE]
 * Liste l'ensemble des services et des opérations Ins (Module Inscription) marquées comme [EXTERNE].  Ils peuvent être utilisés par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l'aide d'un [token jwt](https://jwt.io/). Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`.  Lien vers la documentation qui décrit l'authentification aux APIs de Pegase : [pdf](https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1)  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :   * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)     * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés     * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)   * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)   * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)   * boolean - Un booléen représenté par `true` ou `false`  ### Code retour  * 200 - Ok : L'opération s'est déroulée avec succès * 201 - Created : L'opération a aboutie à la création d'une ressource * 400 - Bad request :   * Un ou des paramètres d'entrées sont erronées   * Une erreur fonctionnelle s'est produite * 401 - Unauthorized - Vous n'êtes pas authentifié   * Il n'y a pas de token passé dans le header HTTP `Authorization`   * Le token passé n'est pas au bon format (Bearer <[token-jwt](https://jwt.io/)>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération   * La signature du token est incorrecte / n'a pas pû être vérifiée   * Le token est expiré   * Les habilitations de l'utilisateur ne permettent pas d'exécuter cette opération. Vérifier les droits de l'utilisateur * 404 - Not Found : La ressource demandée n'est pas trouvé     * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 1.3.1
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
import fr.univlorraine.pegase.model.insext.SituationAnneePrecedente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * SituationPrecedente
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-29T09:55:35.009513400+01:00[Europe/Paris]")
public class SituationPrecedente {
  public static final String SERIALIZED_NAME_ANNEE_PRECEDENTE = "anneePrecedente";
  public static final String SERIALIZED_NAME_SITUATION_PRECEDENTE = "situationPrecedente";
  public static final String SERIALIZED_NAME_ANNEE_OBTENTION_DU_DERNIER_DIPLOME = "anneeObtentionDuDernierDiplome";
  public static final String SERIALIZED_NAME_DERNIER_DIPLOME = "dernierDiplome";
  @SerializedName(SERIALIZED_NAME_ANNEE_PRECEDENTE)
  private BigDecimal anneePrecedente;
  @SerializedName(SERIALIZED_NAME_SITUATION_PRECEDENTE)
  private SituationAnneePrecedente situationPrecedente;
  @SerializedName(SERIALIZED_NAME_ANNEE_OBTENTION_DU_DERNIER_DIPLOME)
  private BigDecimal anneeObtentionDuDernierDiplome;
  @SerializedName(SERIALIZED_NAME_DERNIER_DIPLOME)
  private String dernierDiplome;

  public SituationPrecedente() { 
  }

  public SituationPrecedente anneePrecedente(BigDecimal anneePrecedente) {
    
    this.anneePrecedente = anneePrecedente;
    return this;
  }

   /**
   * L&#39;année universitaire précédant l&#39;inscription en cours au format AAAA
   * @return anneePrecedente
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'année universitaire précédant l'inscription en cours au format AAAA")

  public BigDecimal getAnneePrecedente() {
    return anneePrecedente;
  }


  public void setAnneePrecedente(BigDecimal anneePrecedente) {
    this.anneePrecedente = anneePrecedente;
  }


  public SituationPrecedente situationPrecedente(SituationAnneePrecedente situationPrecedente) {
    
    this.situationPrecedente = situationPrecedente;
    return this;
  }

   /**
   * Get situationPrecedente
   * @return situationPrecedente
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SituationAnneePrecedente getSituationPrecedente() {
    return situationPrecedente;
  }


  public void setSituationPrecedente(SituationAnneePrecedente situationPrecedente) {
    this.situationPrecedente = situationPrecedente;
  }


  public SituationPrecedente anneeObtentionDuDernierDiplome(BigDecimal anneeObtentionDuDernierDiplome) {
    
    this.anneeObtentionDuDernierDiplome = anneeObtentionDuDernierDiplome;
    return this;
  }

   /**
   * L&#39;année d&#39;obtention du dernier diplôme au format AAAA
   * @return anneeObtentionDuDernierDiplome
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'année d'obtention du dernier diplôme au format AAAA")

  public BigDecimal getAnneeObtentionDuDernierDiplome() {
    return anneeObtentionDuDernierDiplome;
  }


  public void setAnneeObtentionDuDernierDiplome(BigDecimal anneeObtentionDuDernierDiplome) {
    this.anneeObtentionDuDernierDiplome = anneeObtentionDuDernierDiplome;
  }


  public SituationPrecedente dernierDiplome(String dernierDiplome) {
    
    this.dernierDiplome = dernierDiplome;
    return this;
  }

   /**
   * Le code du type du dernier diplôme obtenu issu de la nomenclature Types du dernier diplôme obtenu.  Codes de la nomenclature sur 6 caractères. Préfixe des codes : DDO 
   * @return dernierDiplome
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du type du dernier diplôme obtenu issu de la nomenclature Types du dernier diplôme obtenu.  Codes de la nomenclature sur 6 caractères. Préfixe des codes : DDO ")

  public String getDernierDiplome() {
    return dernierDiplome;
  }


  public void setDernierDiplome(String dernierDiplome) {
    this.dernierDiplome = dernierDiplome;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SituationPrecedente situationPrecedente = (SituationPrecedente) o;
    return Objects.equals(this.anneePrecedente, situationPrecedente.anneePrecedente) &&
        Objects.equals(this.situationPrecedente, situationPrecedente.situationPrecedente) &&
        Objects.equals(this.anneeObtentionDuDernierDiplome, situationPrecedente.anneeObtentionDuDernierDiplome) &&
        Objects.equals(this.dernierDiplome, situationPrecedente.dernierDiplome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(anneePrecedente, situationPrecedente, anneeObtentionDuDernierDiplome, dernierDiplome);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SituationPrecedente {\n");
    sb.append("    anneePrecedente: ").append(toIndentedString(anneePrecedente)).append("\n");
    sb.append("    situationPrecedente: ").append(toIndentedString(situationPrecedente)).append("\n");
    sb.append("    anneeObtentionDuDernierDiplome: ").append(toIndentedString(anneeObtentionDuDernierDiplome)).append("\n");
    sb.append("    dernierDiplome: ").append(toIndentedString(dernierDiplome)).append("\n");
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

