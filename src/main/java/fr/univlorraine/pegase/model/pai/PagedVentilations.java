/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 21.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pai;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.model.pai.Paging;
import fr.univlorraine.pegase.model.pai.VentilationDeDroits;
import fr.univlorraine.pegase.model.pai.VentilationsDeDroits;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PagedVentilations
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:45:05.470760100+01:00[Europe/Paris]")
public class PagedVentilations {
  public static final String SERIALIZED_NAME_ITEMS = "items";
  @SerializedName(SERIALIZED_NAME_ITEMS)
  private List<VentilationDeDroits> items = null;

  public static final String SERIALIZED_NAME_TOTAL_ELEMENTS = "totalElements";
  @SerializedName(SERIALIZED_NAME_TOTAL_ELEMENTS)
  private Long totalElements;

  public static final String SERIALIZED_NAME_TOTAL_PAGES = "totalPages";
  @SerializedName(SERIALIZED_NAME_TOTAL_PAGES)
  private Integer totalPages;

  public static final String SERIALIZED_NAME_TAILLE = "taille";
  @SerializedName(SERIALIZED_NAME_TAILLE)
  private Integer taille;

  public static final String SERIALIZED_NAME_PAGE = "page";
  @SerializedName(SERIALIZED_NAME_PAGE)
  private Integer page;

  public PagedVentilations() { 
  }

  public PagedVentilations items(List<VentilationDeDroits> items) {
    
    this.items = items;
    return this;
  }

  public PagedVentilations addItemsItem(VentilationDeDroits itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<VentilationDeDroits>();
    }
    this.items.add(itemsItem);
    return this;
  }

   /**
   * Get items
   * @return items
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<VentilationDeDroits> getItems() {
    return items;
  }


  public void setItems(List<VentilationDeDroits> items) {
    this.items = items;
  }


  public PagedVentilations totalElements(Long totalElements) {
    
    this.totalElements = totalElements;
    return this;
  }

   /**
   * Nombre total de d&#39;enregistrements existants en base 
   * @return totalElements
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Nombre total de d'enregistrements existants en base ")

  public Long getTotalElements() {
    return totalElements;
  }


  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }


  public PagedVentilations totalPages(Integer totalPages) {
    
    this.totalPages = totalPages;
    return this;
  }

   /**
   * Nombre de pages totales existantes 
   * @return totalPages
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Nombre de pages totales existantes ")

  public Integer getTotalPages() {
    return totalPages;
  }


  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }


  public PagedVentilations taille(Integer taille) {
    
    this.taille = taille;
    return this;
  }

   /**
   * Nombre d&#39;enregistrements demandés par page 
   * @return taille
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Nombre d'enregistrements demandés par page ")

  public Integer getTaille() {
    return taille;
  }


  public void setTaille(Integer taille) {
    this.taille = taille;
  }


  public PagedVentilations page(Integer page) {
    
    this.page = page;
    return this;
  }

   /**
   * Numéro de la page retournée (commence à 0) 
   * @return page
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Numéro de la page retournée (commence à 0) ")

  public Integer getPage() {
    return page;
  }


  public void setPage(Integer page) {
    this.page = page;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PagedVentilations pagedVentilations = (PagedVentilations) o;
    return Objects.equals(this.items, pagedVentilations.items) &&
        Objects.equals(this.totalElements, pagedVentilations.totalElements) &&
        Objects.equals(this.totalPages, pagedVentilations.totalPages) &&
        Objects.equals(this.taille, pagedVentilations.taille) &&
        Objects.equals(this.page, pagedVentilations.page);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, totalElements, totalPages, taille, page);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PagedVentilations {\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    taille: ").append(toIndentedString(taille)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
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
