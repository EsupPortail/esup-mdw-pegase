/*
 * INS Gestion V5
 * Il s'agit de l'API v5 de gestion - INS  __Apprenant :__ une personne qui a au moins une inscription validée dans Pegase.  __Inscription :__ se définit par une cible sur une période de mise en œuvre pour un apprenant. Une inscription peut prendre deux états : soit validée, soit annulée.  __Actualisation :__ permet de modifier les données liées à l’apprenant ou à l’inscription alors que la piste a déjà été payée ou validée.   __Gestion des erreurs :__   __200, 201 :__ opération effectuée   __400 :__ erreur de données sur les formats   __403 :__ accès refusé   __404 :__ contenu introuvable   __409 :__ donnée déjà existante   __500 :__ erreur serveur  # Changement majeur/cassant par rapport à V4  1. Suppression de `Inscription.noCandidat`.  1. Ajout de `VoeuBase.noCandidat` et `InscriptionComplete.noCandidat`.  1. Ajout de `VoeuBase.choisi`. 
 *
 * The version of the OpenAPI document: 2.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insgestion;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.model.insgestion.DemandeDeContactSimple;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ContactComplet
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-07-23T12:02:22.714+02:00[Europe/Paris]")

public class ContactComplet {
  /**
   * type de contact
   */
  @JsonAdapter(CanalCommunicationEnum.Adapter.class)
  public enum CanalCommunicationEnum {
    CONTACTADRESSECOMPLET("ContactAdresseComplet"),
    
    CONTACTMELCOMPLET("ContactMelComplet"),
    
    CONTACTTELEPHONECOMPLET("ContactTelephoneComplet");

    private String value;

    CanalCommunicationEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static CanalCommunicationEnum fromValue(String value) {
      for (CanalCommunicationEnum b : CanalCommunicationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<CanalCommunicationEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CanalCommunicationEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CanalCommunicationEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return CanalCommunicationEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_CANAL_COMMUNICATION = "canalCommunication";
  @SerializedName(SERIALIZED_NAME_CANAL_COMMUNICATION)
  protected CanalCommunicationEnum canalCommunication;

  public static final String SERIALIZED_NAME_DEMANDE_DE_CONTACT = "demandeDeContact";
  @SerializedName(SERIALIZED_NAME_DEMANDE_DE_CONTACT)
  private DemandeDeContactSimple demandeDeContact;

  public static final String SERIALIZED_NAME_PROPRIETAIRE = "proprietaire";
  @SerializedName(SERIALIZED_NAME_PROPRIETAIRE)
  private String proprietaire;

  public ContactComplet() {
    this.canalCommunication = CanalCommunicationEnum.fromValue(this.getClass().getSimpleName());
  }

  public ContactComplet canalCommunication(CanalCommunicationEnum canalCommunication) {
    
    this.canalCommunication = canalCommunication;
    return this;
  }

   /**
   * type de contact
   * @return canalCommunication
  **/
  @ApiModelProperty(required = true, value = "type de contact")

  public CanalCommunicationEnum getCanalCommunication() {
    return canalCommunication;
  }


  public void setCanalCommunication(CanalCommunicationEnum canalCommunication) {
    this.canalCommunication = canalCommunication;
  }


  public ContactComplet demandeDeContact(DemandeDeContactSimple demandeDeContact) {
    
    this.demandeDeContact = demandeDeContact;
    return this;
  }

   /**
   * Get demandeDeContact
   * @return demandeDeContact
  **/
  @ApiModelProperty(required = true, value = "")

  public DemandeDeContactSimple getDemandeDeContact() {
    return demandeDeContact;
  }


  public void setDemandeDeContact(DemandeDeContactSimple demandeDeContact) {
    this.demandeDeContact = demandeDeContact;
  }


  public ContactComplet proprietaire(String proprietaire) {
    
    this.proprietaire = proprietaire;
    return this;
  }

   /**
   * Personne à contacter si différent de l&#39;apprenant
   * @return proprietaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Personne à contacter si différent de l'apprenant")

  public String getProprietaire() {
    return proprietaire;
  }


  public void setProprietaire(String proprietaire) {
    this.proprietaire = proprietaire;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactComplet contactComplet = (ContactComplet) o;
    return Objects.equals(this.canalCommunication, contactComplet.canalCommunication) &&
        Objects.equals(this.demandeDeContact, contactComplet.demandeDeContact) &&
        Objects.equals(this.proprietaire, contactComplet.proprietaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(canalCommunication, demandeDeContact, proprietaire);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactComplet {\n");
    sb.append("    canalCommunication: ").append(toIndentedString(canalCommunication)).append("\n");
    sb.append("    demandeDeContact: ").append(toIndentedString(demandeDeContact)).append("\n");
    sb.append("    proprietaire: ").append(toIndentedString(proprietaire)).append("\n");
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

