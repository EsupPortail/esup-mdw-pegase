/*
 * COC Publication v2 - Contrôle du cursus - Bloc «publication» - [EXTERNE]
 * Liste l'ensemble des services et des opérations disponible pour la publication de notes et des résultats à partir des données du module COC (Contrôle du cursus)  **Note importante :** Cette API est marquée [EXTERNE], elle peut être utilisée par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé (voir le paragraphe [Authentification](#section/Authentication) pour les détails).  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés    * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour   * 200 - Ok : L'opération s'est déroulée avec succès  * 201 - Created : L'opération a aboutie à la création d'une ressource  * 400 - Bad request :    * Un ou des paramètres d'entrées sont erronées    * Une erreur fonctionnelle s'est produite  * 404 - Not Found : La ressource demandée n'est pas trouvé    * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.coc;

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
import java.util.Date;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * Periode
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:41:41.641107+01:00[Europe/Paris]")
public class Periode {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;

  public static final String SERIALIZED_NAME_LIBELLE_LONG = "libelleLong";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG)
  private String libelleLong;

  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE = "libelleAffichage";
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE)
  private String libelleAffichage;

  public static final String SERIALIZED_NAME_DATE_DEBUT = "dateDebut";
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT)
  private Date dateDebut;

  public static final String SERIALIZED_NAME_DATE_FIN = "dateFin";
  @SerializedName(SERIALIZED_NAME_DATE_FIN)
  private Date dateFin;

  public static final String SERIALIZED_NAME_ANNEE_UNIVERSITAIRE = "anneeUniversitaire";
  @SerializedName(SERIALIZED_NAME_ANNEE_UNIVERSITAIRE)
  private Integer anneeUniversitaire;

  public static final String SERIALIZED_NAME_ACTIVE = "active";
  @SerializedName(SERIALIZED_NAME_ACTIVE)
  private Boolean active;

  public Periode() { 
  }

  public Periode code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de la période
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code de la période")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public Periode libelleCourt(String libelleCourt) {
    
    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Le libellé court de la période à afficher
   * @return libelleCourt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé court de la période à afficher")

  public String getLibelleCourt() {
    return libelleCourt;
  }


  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public Periode libelleLong(String libelleLong) {
    
    this.libelleLong = libelleLong;
    return this;
  }

   /**
   * Le libellé long de la période à afficher
   * @return libelleLong
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé long de la période à afficher")

  public String getLibelleLong() {
    return libelleLong;
  }


  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }


  public Periode libelleAffichage(String libelleAffichage) {
    
    this.libelleAffichage = libelleAffichage;
    return this;
  }

   /**
   * Le libellé d&#39;affichage de la période à afficher
   * @return libelleAffichage
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé d'affichage de la période à afficher")

  public String getLibelleAffichage() {
    return libelleAffichage;
  }


  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }


  public Periode dateDebut(Date dateDebut) {
    
    this.dateDebut = dateDebut;
    return this;
  }

   /**
   * La date de début de la période
   * @return dateDebut
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La date de début de la période")

  public Date getDateDebut() {
    return dateDebut;
  }


  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }


  public Periode dateFin(Date dateFin) {
    
    this.dateFin = dateFin;
    return this;
  }

   /**
   * La date de fin de la période
   * @return dateFin
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La date de fin de la période")

  public Date getDateFin() {
    return dateFin;
  }


  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }


  public Periode anneeUniversitaire(Integer anneeUniversitaire) {
    
    this.anneeUniversitaire = anneeUniversitaire;
    return this;
  }

   /**
   * L&#39;année universitaire sur laquelle débute la période
   * @return anneeUniversitaire
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'année universitaire sur laquelle débute la période")

  public Integer getAnneeUniversitaire() {
    return anneeUniversitaire;
  }


  public void setAnneeUniversitaire(Integer anneeUniversitaire) {
    this.anneeUniversitaire = anneeUniversitaire;
  }


  public Periode active(Boolean active) {
    
    this.active = active;
    return this;
  }

   /**
   * La période est-elle active ?
   * @return active
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La période est-elle active ?")

  public Boolean getActive() {
    return active;
  }


  public void setActive(Boolean active) {
    this.active = active;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Periode periode = (Periode) o;
    return Objects.equals(this.code, periode.code) &&
        Objects.equals(this.libelleCourt, periode.libelleCourt) &&
        Objects.equals(this.libelleLong, periode.libelleLong) &&
        Objects.equals(this.libelleAffichage, periode.libelleAffichage) &&
        Objects.equals(this.dateDebut, periode.dateDebut) &&
        Objects.equals(this.dateFin, periode.dateFin) &&
        Objects.equals(this.anneeUniversitaire, periode.anneeUniversitaire) &&
        Objects.equals(this.active, periode.active);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleCourt, libelleLong, libelleAffichage, dateDebut, dateFin, anneeUniversitaire, active);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Periode {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    dateDebut: ").append(toIndentedString(dateDebut)).append("\n");
    sb.append("    dateFin: ").append(toIndentedString(dateFin)).append("\n");
    sb.append("    anneeUniversitaire: ").append(toIndentedString(anneeUniversitaire)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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

