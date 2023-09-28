/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 20.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insgestion;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * MetaDonnee
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-07-12T11:53:29.862+02:00[Europe/Paris]")
public class MetaDonnee {
  public static final String SERIALIZED_NAME_CONTEXTE_INSCRIPTION = "contexteInscription";
  @SerializedName(SERIALIZED_NAME_CONTEXTE_INSCRIPTION)
  private ContexteInscription contexteInscription;

  public static final String SERIALIZED_NAME_CODE_STRUCTURE = "codeStructure";
  @SerializedName(SERIALIZED_NAME_CODE_STRUCTURE)
  private String codeStructure;

  public static final String SERIALIZED_NAME_CODE_APPRENANT = "codeApprenant";
  @SerializedName(SERIALIZED_NAME_CODE_APPRENANT)
  private String codeApprenant;

  public MetaDonnee() { 
  }

  public MetaDonnee contexteInscription(ContexteInscription contexteInscription) {
    
    this.contexteInscription = contexteInscription;
    return this;
  }

   /**
   * Get contexteInscription
   * @return contexteInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ContexteInscription getContexteInscription() {
    return contexteInscription;
  }


  public void setContexteInscription(ContexteInscription contexteInscription) {
    this.contexteInscription = contexteInscription;
  }


  public MetaDonnee codeStructure(String codeStructure) {
    
    this.codeStructure = codeStructure;
    return this;
  }

   /**
   * Le code UAI
   * @return codeStructure
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code UAI")

  public String getCodeStructure() {
    return codeStructure;
  }


  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public MetaDonnee codeApprenant(String codeApprenant) {
    
    this.codeApprenant = codeApprenant;
    return this;
  }

   /**
   * Le code apprenant
   * @return codeApprenant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code apprenant")

  public String getCodeApprenant() {
    return codeApprenant;
  }


  public void setCodeApprenant(String codeApprenant) {
    this.codeApprenant = codeApprenant;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetaDonnee metaDonnee = (MetaDonnee) o;
    return Objects.equals(this.contexteInscription, metaDonnee.contexteInscription) &&
        Objects.equals(this.codeStructure, metaDonnee.codeStructure) &&
        Objects.equals(this.codeApprenant, metaDonnee.codeApprenant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contexteInscription, codeStructure, codeApprenant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetaDonnee {\n");
    sb.append("    contexteInscription: ").append(toIndentedString(contexteInscription)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    codeApprenant: ").append(toIndentedString(codeApprenant)).append("\n");
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

