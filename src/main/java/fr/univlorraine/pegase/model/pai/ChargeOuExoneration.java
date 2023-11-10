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
import fr.univlorraine.pegase.model.pai.Montant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ChargeOuExoneration
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:45:05.470760100+01:00[Europe/Paris]")
public class ChargeOuExoneration {
  public static final String SERIALIZED_NAME_TITRE = "titre";
  @SerializedName(SERIALIZED_NAME_TITRE)
  private String titre;

  public static final String SERIALIZED_NAME_MONTANT = "montant";
  @SerializedName(SERIALIZED_NAME_MONTANT)
  private Montant montant;

  public ChargeOuExoneration() { 
  }

  public ChargeOuExoneration titre(String titre) {
    
    this.titre = titre;
    return this;
  }

   /**
   * Titre de la charge ou de l&#39;exonération
   * @return titre
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Titre de la charge ou de l'exonération")

  public String getTitre() {
    return titre;
  }


  public void setTitre(String titre) {
    this.titre = titre;
  }


  public ChargeOuExoneration montant(Montant montant) {
    
    this.montant = montant;
    return this;
  }

   /**
   * Get montant
   * @return montant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Montant getMontant() {
    return montant;
  }


  public void setMontant(Montant montant) {
    this.montant = montant;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargeOuExoneration chargeOuExoneration = (ChargeOuExoneration) o;
    return Objects.equals(this.titre, chargeOuExoneration.titre) &&
        Objects.equals(this.montant, chargeOuExoneration.montant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titre, montant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChargeOuExoneration {\n");
    sb.append("    titre: ").append(toIndentedString(titre)).append("\n");
    sb.append("    montant: ").append(toIndentedString(montant)).append("\n");
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

