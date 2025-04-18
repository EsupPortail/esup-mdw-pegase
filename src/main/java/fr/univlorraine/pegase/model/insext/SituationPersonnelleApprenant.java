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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * SituationPersonnelleApprenant
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-29T09:55:35.009513400+01:00[Europe/Paris]")
public class SituationPersonnelleApprenant {
  public static final String SERIALIZED_NAME_SITUATION_FAMILIALE = "situationFamiliale";
  public static final String SERIALIZED_NAME_ENFANTS = "enfants";
  public static final String SERIALIZED_NAME_SITUATION_MILITAIRE = "situationMilitaire";
  @SerializedName(SERIALIZED_NAME_SITUATION_FAMILIALE)
  private String situationFamiliale;
  @SerializedName(SERIALIZED_NAME_ENFANTS)
  private Integer enfants;
  @SerializedName(SERIALIZED_NAME_SITUATION_MILITAIRE)
  private String situationMilitaire;

  public SituationPersonnelleApprenant() { 
  }

  public SituationPersonnelleApprenant situationFamiliale(String situationFamiliale) {
    
    this.situationFamiliale = situationFamiliale;
    return this;
  }

   /**
   * Le code de la situation familiale de l&#39;étudiant issu de la nomenclature Situations familiales  Codes de la nomenclature sur 6 caractères. Préfixe des codes : SIF 
   * @return situationFamiliale
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la situation familiale de l'étudiant issu de la nomenclature Situations familiales  Codes de la nomenclature sur 6 caractères. Préfixe des codes : SIF ")

  public String getSituationFamiliale() {
    return situationFamiliale;
  }


  public void setSituationFamiliale(String situationFamiliale) {
    this.situationFamiliale = situationFamiliale;
  }


  public SituationPersonnelleApprenant enfants(Integer enfants) {
    
    this.enfants = enfants;
    return this;
  }

   /**
   * Le nombre d&#39;enfants à charge
   * @return enfants
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le nombre d'enfants à charge")

  public Integer getEnfants() {
    return enfants;
  }


  public void setEnfants(Integer enfants) {
    this.enfants = enfants;
  }


  public SituationPersonnelleApprenant situationMilitaire(String situationMilitaire) {
    
    this.situationMilitaire = situationMilitaire;
    return this;
  }

   /**
   * Le code de la situation militaire de l&#39;étudiant issu de la nomenclature Situations militaires  Codes de la nomenclature sur 6 caractères. Préfixe des codes : SIM. 
   * @return situationMilitaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la situation militaire de l'étudiant issu de la nomenclature Situations militaires  Codes de la nomenclature sur 6 caractères. Préfixe des codes : SIM. ")

  public String getSituationMilitaire() {
    return situationMilitaire;
  }


  public void setSituationMilitaire(String situationMilitaire) {
    this.situationMilitaire = situationMilitaire;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SituationPersonnelleApprenant situationPersonnelleApprenant = (SituationPersonnelleApprenant) o;
    return Objects.equals(this.situationFamiliale, situationPersonnelleApprenant.situationFamiliale) &&
        Objects.equals(this.enfants, situationPersonnelleApprenant.enfants) &&
        Objects.equals(this.situationMilitaire, situationPersonnelleApprenant.situationMilitaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(situationFamiliale, enfants, situationMilitaire);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SituationPersonnelleApprenant {\n");
    sb.append("    situationFamiliale: ").append(toIndentedString(situationFamiliale)).append("\n");
    sb.append("    enfants: ").append(toIndentedString(enfants)).append("\n");
    sb.append("    situationMilitaire: ").append(toIndentedString(situationMilitaire)).append("\n");
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

