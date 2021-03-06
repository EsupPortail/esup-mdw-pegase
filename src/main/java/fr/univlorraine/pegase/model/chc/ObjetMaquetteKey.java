/*
 * API CHC v4
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v4
 *
 * The version of the OpenAPI document: 4.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.chc;

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
 * ObjetMaquetteKey
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-11T17:44:47.769+01:00[Europe/Paris]")
public class ObjetMaquetteKey {
  public static final String SERIALIZED_NAME_CODE_CHEMIN = "codeChemin";
  @SerializedName(SERIALIZED_NAME_CODE_CHEMIN)
  private String codeChemin;

  public static final String SERIALIZED_NAME_CODE_PERIODE = "codePeriode";
  @SerializedName(SERIALIZED_NAME_CODE_PERIODE)
  private String codePeriode;

  public static final String SERIALIZED_NAME_CODE_STRUCTURE = "codeStructure";
  @SerializedName(SERIALIZED_NAME_CODE_STRUCTURE)
  private String codeStructure;

  public static final String SERIALIZED_NAME_VERSION_MAQUETTE = "versionMaquette";
  @SerializedName(SERIALIZED_NAME_VERSION_MAQUETTE)
  private Integer versionMaquette;


  public ObjetMaquetteKey codeChemin(String codeChemin) {
    
    this.codeChemin = codeChemin;
    return this;
  }

   /**
   * Le code chemin de l&#39;objet maquette - identifiant unique
   * @return codeChemin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code chemin de l'objet maquette - identifiant unique")

  public String getCodeChemin() {
    return codeChemin;
  }


  public void setCodeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
  }


  public ObjetMaquetteKey codePeriode(String codePeriode) {
    
    this.codePeriode = codePeriode;
    return this;
  }

   /**
   * Le code de la période  - identifiant unique
   * @return codePeriode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la période  - identifiant unique")

  public String getCodePeriode() {
    return codePeriode;
  }


  public void setCodePeriode(String codePeriode) {
    this.codePeriode = codePeriode;
  }


  public ObjetMaquetteKey codeStructure(String codeStructure) {
    
    this.codeStructure = codeStructure;
    return this;
  }

   /**
   * Le code structure  - identifiant unique
   * @return codeStructure
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code structure  - identifiant unique")

  public String getCodeStructure() {
    return codeStructure;
  }


  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public ObjetMaquetteKey versionMaquette(Integer versionMaquette) {
    
    this.versionMaquette = versionMaquette;
    return this;
  }

   /**
   * La version de la maquette
   * @return versionMaquette
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La version de la maquette")

  public Integer getVersionMaquette() {
    return versionMaquette;
  }


  public void setVersionMaquette(Integer versionMaquette) {
    this.versionMaquette = versionMaquette;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjetMaquetteKey objetMaquetteKey = (ObjetMaquetteKey) o;
    return Objects.equals(this.codeChemin, objetMaquetteKey.codeChemin) &&
        Objects.equals(this.codePeriode, objetMaquetteKey.codePeriode) &&
        Objects.equals(this.codeStructure, objetMaquetteKey.codeStructure) &&
        Objects.equals(this.versionMaquette, objetMaquetteKey.versionMaquette);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeChemin, codePeriode, codeStructure, versionMaquette);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjetMaquetteKey {\n");
    sb.append("    codeChemin: ").append(toIndentedString(codeChemin)).append("\n");
    sb.append("    codePeriode: ").append(toIndentedString(codePeriode)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    versionMaquette: ").append(toIndentedString(versionMaquette)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

