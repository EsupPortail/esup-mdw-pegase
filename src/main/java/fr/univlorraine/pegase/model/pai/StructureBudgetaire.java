/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 16.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pai;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Une structure budgétaire associée à un compte budgétaire. 
 */
@ApiModel(description = "Une structure budgétaire associée à un compte budgétaire. ")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-05-11T10:50:10.652+02:00[Europe/Paris]")
public class StructureBudgetaire {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_CODE_UAI = "codeUai";
  @SerializedName(SERIALIZED_NAME_CODE_UAI)
  private String codeUai;

  public static final String SERIALIZED_NAME_LIBELLE = "libelle";
  @SerializedName(SERIALIZED_NAME_LIBELLE)
  private String libelle;

  public StructureBudgetaire() { 
  }

  public StructureBudgetaire code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de la structure budgétaire.
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la structure budgétaire.")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public StructureBudgetaire codeUai(String codeUai) {
    
    this.codeUai = codeUai;
    return this;
  }

   /**
   * Le code UAI de la structure budgétaire.
   * @return codeUai
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code UAI de la structure budgétaire.")

  public String getCodeUai() {
    return codeUai;
  }


  public void setCodeUai(String codeUai) {
    this.codeUai = codeUai;
  }


  public StructureBudgetaire libelle(String libelle) {
    
    this.libelle = libelle;
    return this;
  }

   /**
   * Le libelle de la structure budgétaire.
   * @return libelle
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libelle de la structure budgétaire.")

  public String getLibelle() {
    return libelle;
  }


  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StructureBudgetaire structureBudgetaire = (StructureBudgetaire) o;
    return Objects.equals(this.code, structureBudgetaire.code) &&
        Objects.equals(this.codeUai, structureBudgetaire.codeUai) &&
        Objects.equals(this.libelle, structureBudgetaire.libelle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, codeUai, libelle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StructureBudgetaire {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codeUai: ").append(toIndentedString(codeUai)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
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

