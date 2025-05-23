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
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets Devise
 */
@JsonAdapter(Devise.Adapter.class)
public enum Devise {
  
  IGNORE("ignore"),
  
  EUROS("euros"),
  
  FRANCS_PACIFIQUES("francs pacifiques");

  private String value;

  Devise(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static Devise fromValue(String value) {
    for (Devise b : Devise.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<Devise> {
    @Override
    public void write(final JsonWriter jsonWriter, final Devise enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public Devise read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return Devise.fromValue(value);
    }
  }
}

