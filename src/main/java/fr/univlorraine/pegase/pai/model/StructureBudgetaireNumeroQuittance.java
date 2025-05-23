/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 27.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.pai.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.pai.model.StructureBudgetaire;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Une structure budgétaire associée à un numéro de quittance 
 */
@ApiModel(description = "Une structure budgétaire associée à un numéro de quittance ")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:28:00.271684800+02:00[Europe/Paris]")
public class StructureBudgetaireNumeroQuittance {
  public static final String SERIALIZED_NAME_STRUCTURE_BUDGETAIRE = "structureBudgetaire";
  public static final String SERIALIZED_NAME_NUMERO_QUITTANCE = "numeroQuittance";
  @SerializedName(SERIALIZED_NAME_STRUCTURE_BUDGETAIRE)
  private StructureBudgetaire structureBudgetaire;
  @SerializedName(SERIALIZED_NAME_NUMERO_QUITTANCE)
  private Long numeroQuittance;

  public StructureBudgetaireNumeroQuittance() { 
  }

  public StructureBudgetaireNumeroQuittance structureBudgetaire(StructureBudgetaire structureBudgetaire) {
    
    this.structureBudgetaire = structureBudgetaire;
    return this;
  }

   /**
   * Get structureBudgetaire
   * @return structureBudgetaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StructureBudgetaire getStructureBudgetaire() {
    return structureBudgetaire;
  }


  public void setStructureBudgetaire(StructureBudgetaire structureBudgetaire) {
    this.structureBudgetaire = structureBudgetaire;
  }


  public StructureBudgetaireNumeroQuittance numeroQuittance(Long numeroQuittance) {
    
    this.numeroQuittance = numeroQuittance;
    return this;
  }

   /**
   * Le numéro de quittance associé à cette structure budgétaire
   * @return numeroQuittance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le numéro de quittance associé à cette structure budgétaire")

  public Long getNumeroQuittance() {
    return numeroQuittance;
  }


  public void setNumeroQuittance(Long numeroQuittance) {
    this.numeroQuittance = numeroQuittance;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StructureBudgetaireNumeroQuittance structureBudgetaireNumeroQuittance = (StructureBudgetaireNumeroQuittance) o;
    return Objects.equals(this.structureBudgetaire, structureBudgetaireNumeroQuittance.structureBudgetaire) &&
        Objects.equals(this.numeroQuittance, structureBudgetaireNumeroQuittance.numeroQuittance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(structureBudgetaire, numeroQuittance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StructureBudgetaireNumeroQuittance {\n");
    sb.append("    structureBudgetaire: ").append(toIndentedString(structureBudgetaire)).append("\n");
    sb.append("    numeroQuittance: ").append(toIndentedString(numeroQuittance)).append("\n");
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

