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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * VueInscriptions
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-07-12T11:53:29.862+02:00[Europe/Paris]")
public class VueInscriptions {
  public static final String SERIALIZED_NAME_TAILLE = "taille";
  @SerializedName(SERIALIZED_NAME_TAILLE)
  private Long taille;

  public static final String SERIALIZED_NAME_RESULTATS = "resultats";
  @SerializedName(SERIALIZED_NAME_RESULTATS)
  private List<VueInscription> resultats = new ArrayList<VueInscription>();

  public static final String SERIALIZED_NAME_TOTAL_ELEMENTS = "totalElements";
  @SerializedName(SERIALIZED_NAME_TOTAL_ELEMENTS)
  private Long totalElements;

  public static final String SERIALIZED_NAME_PAGE = "page";
  @SerializedName(SERIALIZED_NAME_PAGE)
  private Long page;

  public static final String SERIALIZED_NAME_TOTAL_PAGES = "totalPages";
  @SerializedName(SERIALIZED_NAME_TOTAL_PAGES)
  private Long totalPages;

  public VueInscriptions() { 
  }

  public VueInscriptions taille(Long taille) {
    
    this.taille = taille;
    return this;
  }

   /**
   * Get taille
   * @return taille
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Long getTaille() {
    return taille;
  }


  public void setTaille(Long taille) {
    this.taille = taille;
  }


  public VueInscriptions resultats(List<VueInscription> resultats) {
    
    this.resultats = resultats;
    return this;
  }

  public VueInscriptions addResultatsItem(VueInscription resultatsItem) {
    this.resultats.add(resultatsItem);
    return this;
  }

   /**
   * Get resultats
   * @return resultats
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public List<VueInscription> getResultats() {
    return resultats;
  }


  public void setResultats(List<VueInscription> resultats) {
    this.resultats = resultats;
  }


  public VueInscriptions totalElements(Long totalElements) {
    
    this.totalElements = totalElements;
    return this;
  }

   /**
   * Get totalElements
   * @return totalElements
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Long getTotalElements() {
    return totalElements;
  }


  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }


  public VueInscriptions page(Long page) {
    
    this.page = page;
    return this;
  }

   /**
   * Get page
   * @return page
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Long getPage() {
    return page;
  }


  public void setPage(Long page) {
    this.page = page;
  }


  public VueInscriptions totalPages(Long totalPages) {
    
    this.totalPages = totalPages;
    return this;
  }

   /**
   * Get totalPages
   * @return totalPages
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Long getTotalPages() {
    return totalPages;
  }


  public void setTotalPages(Long totalPages) {
    this.totalPages = totalPages;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VueInscriptions vueInscriptions = (VueInscriptions) o;
    return Objects.equals(this.taille, vueInscriptions.taille) &&
        Objects.equals(this.resultats, vueInscriptions.resultats) &&
        Objects.equals(this.totalElements, vueInscriptions.totalElements) &&
        Objects.equals(this.page, vueInscriptions.page) &&
        Objects.equals(this.totalPages, vueInscriptions.totalPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taille, resultats, totalElements, page, totalPages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VueInscriptions {\n");
    sb.append("    taille: ").append(toIndentedString(taille)).append("\n");
    sb.append("    resultats: ").append(toIndentedString(resultats)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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
