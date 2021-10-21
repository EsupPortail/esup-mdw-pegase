/**
 *
 *  ESUP-Portail ESUP-MONDOSSIERWEB-PEGASE - Copyright (c) 2021 ESUP-Portail consortium
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
/*
 * API CHC v5
 * Liste l'ensemble des services et des opérations disponibles dans le module choix des cursus v5 
 *
 * The version of the OpenAPI document: 5.0.0
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
 * ApprenantKeyExtention
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-05-27T10:38:20.872+02:00[Europe/Paris]")
public class ApprenantKeyExtention {
  public static final String SERIALIZED_NAME_CODE_APPRENANT = "codeApprenant";
  @SerializedName(SERIALIZED_NAME_CODE_APPRENANT)
  private String codeApprenant;

  public static final String SERIALIZED_NAME_TEMOIN_AFFECTE = "temoinAffecte";
  @SerializedName(SERIALIZED_NAME_TEMOIN_AFFECTE)
  private Boolean temoinAffecte;


  public ApprenantKeyExtention codeApprenant(String codeApprenant) {
    
    this.codeApprenant = codeApprenant;
    return this;
  }

   /**
   * Le code de l&#39;apprenant - identifiant unique
   * @return codeApprenant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'apprenant - identifiant unique")

  public String getCodeApprenant() {
    return codeApprenant;
  }


  public void setCodeApprenant(String codeApprenant) {
    this.codeApprenant = codeApprenant;
  }


  public ApprenantKeyExtention temoinAffecte(Boolean temoinAffecte) {
    
    this.temoinAffecte = temoinAffecte;
    return this;
  }

   /**
   * Est ce que l&#39;apprenant est affecté sur l&#39;objet maquette?
   * @return temoinAffecte
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est ce que l'apprenant est affecté sur l'objet maquette?")

  public Boolean getTemoinAffecte() {
    return temoinAffecte;
  }


  public void setTemoinAffecte(Boolean temoinAffecte) {
    this.temoinAffecte = temoinAffecte;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApprenantKeyExtention apprenantKeyExtention = (ApprenantKeyExtention) o;
    return Objects.equals(this.codeApprenant, apprenantKeyExtention.codeApprenant) &&
        Objects.equals(this.temoinAffecte, apprenantKeyExtention.temoinAffecte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeApprenant, temoinAffecte);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApprenantKeyExtention {\n");
    sb.append("    codeApprenant: ").append(toIndentedString(codeApprenant)).append("\n");
    sb.append("    temoinAffecte: ").append(toIndentedString(temoinAffecte)).append("\n");
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

