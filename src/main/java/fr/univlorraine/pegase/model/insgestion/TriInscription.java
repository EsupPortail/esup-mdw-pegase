/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.3.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insgestion;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets TriInscription
 */
@JsonAdapter(TriInscription.Adapter.class)
public enum TriInscription {
  
  UUIDASC("uuidASC"),
  
  UUIDDESC("uuidDESC"),
  
  NOCANDIDATASC("noCandidatASC"),
  
  NOCANDIDATDESC("noCandidatDESC"),
  
  ETATCIVIL_NOMDENAISSANCEASC("etatCivil.nomDeNaissanceASC"),
  
  ETATCIVIL_NOMDENAISSANCEDESC("etatCivil.nomDeNaissanceDESC"),
  
  ETATCIVIL_PRENOMASC("etatCivil.prenomASC"),
  
  ETATCIVIL_PRENOMDESC("etatCivil.prenomDESC"),
  
  NAISSANCE_DATEDENAISSANCEASC("naissance.dateDeNaissanceASC"),
  
  NAISSANCE_DATEDENAISSANCEDESC("naissance.dateDeNaissanceDESC"),
  
  NAISSANCE_PAYSDENAISSANCEASC("naissance.paysDeNaissanceASC"),
  
  NAISSANCE_PAYSDENAISSANCEDESC("naissance.paysDeNaissanceDESC"),
  
  NAISSANCE_COMMUNEDENAISSANCEASC("naissance.communeDeNaissanceASC"),
  
  NAISSANCE_COMMUNEDENAISSANCEDESC("naissance.communeDeNaissanceDESC"),
  
  BAC_INEASC("bac.ineASC"),
  
  BAC_INEDESC("bac.ineDESC"),
  
  VOEUASC("voeuASC"),
  
  VOEUDESC("voeuDESC"),
  
  VOEU_STATUTINSCRIPTIONASC("voeu.statutInscriptionASC"),
  
  VOEU_STATUTINSCRIPTIONDESC("voeu.statutInscriptionDESC"),
  
  VOEU_STATUTPAIEMENTASC("voeu.statutPaiementASC"),
  
  VOEU_STATUTPAIEMENTDESC("voeu.statutPaiementDESC"),
  
  VOEU_STATUTPIECESASC("voeu.statutPiecesASC"),
  
  VOEU_STATUTPIECESDESC("voeu.statutPiecesDESC");

  private String value;

  TriInscription(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static TriInscription fromValue(String value) {
    for (TriInscription b : TriInscription.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<TriInscription> {
    @Override
    public void write(final JsonWriter jsonWriter, final TriInscription enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public TriInscription read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return TriInscription.fromValue(value);
    }
  }
}

