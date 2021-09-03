/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 2.1.2
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pai;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.annotations.ApiModel;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * définition du statut de la facture : * facture_emise * facture_enCours * facture_aquittee * facture_annulee 
 */
@JsonAdapter(StatutFacture.Adapter.class)
public enum StatutFacture {
  
  EMISE("emise"),
  
  ENCOURS("enCours"),
  
  ACQUITTEE("acquittee"),
  
  ANNULEE("annulee");

  private String value;

  StatutFacture(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static StatutFacture fromValue(String value) {
    for (StatutFacture b : StatutFacture.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<StatutFacture> {
    @Override
    public void write(final JsonWriter jsonWriter, final StatutFacture enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public StatutFacture read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return StatutFacture.fromValue(value);
    }
  }
}

