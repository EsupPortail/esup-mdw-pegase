/*
 * API INS V1
 *  # Introduction  Liste l'ensemble des services et des opérations disponibles dans le module INS  Description service INS  # Gestion des erreurs  ## StatusCode  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | |         | Cas particulier: Dans le cas d'APIs de     | |         | type bulk, un 200 peut aussi être retourné | |         | si des données de la requête sont          | |         | considérées en erreur                      | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 403     | Accès refusé                               | | 404     | Ressource inexistante                      | | 409     | donnée déjà existante                      | | 500     | Erreur technique rencontrée par le serveur |   ## Codes d'erreurs  | Code      | Description                                | |-----------|--------------------------------------------| | notNull   | la propriété est obligatoire               | | notBlank  | la propriété ne doit pas être vide         | | size      | la longueur de la propriété est invalide   | | pattern   | les caractères ou la syntaxe de            | |           | la propriété est invalide                  | | genre     | le genre de la personne est invalide       | | dateEntre | la date est invalide                       | | telephone | le téléphone est invalide                  | | email     | le mail est invalide                       | 
 *
 * The version of the OpenAPI document: 1.0.0-rc.20250414083300
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.ins.model;

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
 * DemandeAccord
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-28T10:21:40.910954400+02:00[Europe/Paris]")
public class DemandeAccord {
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_CODE_STRUCTURE = "codeStructure";
  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE = "libelleAffichage";
  public static final String SERIALIZED_NAME_CONTENU = "contenu";
  public static final String SERIALIZED_NAME_LIBELLE_ACCEPTATION = "libelleAcceptation";
  public static final String SERIALIZED_NAME_LIBELLE_REFUS = "libelleRefus";
  public static final String SERIALIZED_NAME_TEMOIN_BLOQUANT = "temoinBloquant";
  public static final String SERIALIZED_NAME_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  public static final String SERIALIZED_NAME_DATE_FIN_VALIDITE = "dateFinValidite";
  public static final String SERIALIZED_NAME_PRIORITE_AFFICHAGE = "prioriteAffichage";
  public static final String SERIALIZED_NAME_TEMOIN_VISIBLE = "temoinVisible";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_CODE_STRUCTURE)
  private String codeStructure;
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE)
  private String libelleAffichage;
  @SerializedName(SERIALIZED_NAME_CONTENU)
  private String contenu;
  @SerializedName(SERIALIZED_NAME_LIBELLE_ACCEPTATION)
  private String libelleAcceptation;
  @SerializedName(SERIALIZED_NAME_LIBELLE_REFUS)
  private String libelleRefus;
  @SerializedName(SERIALIZED_NAME_TEMOIN_BLOQUANT)
  private Boolean temoinBloquant;
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT_VALIDITE)
  private String dateDebutValidite;
  @SerializedName(SERIALIZED_NAME_DATE_FIN_VALIDITE)
  private String dateFinValidite;
  @SerializedName(SERIALIZED_NAME_PRIORITE_AFFICHAGE)
  private Integer prioriteAffichage;
  @SerializedName(SERIALIZED_NAME_TEMOIN_VISIBLE)
  private Boolean temoinVisible;

  public DemandeAccord() { 
  }

  public DemandeAccord code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code metier en saisie libre
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code metier en saisie libre")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public DemandeAccord codeStructure(String codeStructure) {
    
    this.codeStructure = codeStructure;
    return this;
  }

   /**
   * Le code de l&#39;établissement (structure)
   * @return codeStructure
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'établissement (structure)")

  public String getCodeStructure() {
    return codeStructure;
  }


  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public DemandeAccord libelleAffichage(String libelleAffichage) {
    
    this.libelleAffichage = libelleAffichage;
    return this;
  }

   /**
   * Le libellé d&#39;affichage
   * @return libelleAffichage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé d'affichage")

  public String getLibelleAffichage() {
    return libelleAffichage;
  }


  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }


  public DemandeAccord contenu(String contenu) {
    
    this.contenu = contenu;
    return this;
  }

   /**
   * Le contenu
   * @return contenu
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le contenu")

  public String getContenu() {
    return contenu;
  }


  public void setContenu(String contenu) {
    this.contenu = contenu;
  }


  public DemandeAccord libelleAcceptation(String libelleAcceptation) {
    
    this.libelleAcceptation = libelleAcceptation;
    return this;
  }

   /**
   * Le libellé d&#39;acceptation
   * @return libelleAcceptation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé d'acceptation")

  public String getLibelleAcceptation() {
    return libelleAcceptation;
  }


  public void setLibelleAcceptation(String libelleAcceptation) {
    this.libelleAcceptation = libelleAcceptation;
  }


  public DemandeAccord libelleRefus(String libelleRefus) {
    
    this.libelleRefus = libelleRefus;
    return this;
  }

   /**
   * Le libellé de refus
   * @return libelleRefus
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé de refus")

  public String getLibelleRefus() {
    return libelleRefus;
  }


  public void setLibelleRefus(String libelleRefus) {
    this.libelleRefus = libelleRefus;
  }


  public DemandeAccord temoinBloquant(Boolean temoinBloquant) {
    
    this.temoinBloquant = temoinBloquant;
    return this;
  }

   /**
   * Si true, impose une réponse pour pouvoir continuer l&#39;inscription
   * @return temoinBloquant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Si true, impose une réponse pour pouvoir continuer l'inscription")

  public Boolean getTemoinBloquant() {
    return temoinBloquant;
  }


  public void setTemoinBloquant(Boolean temoinBloquant) {
    this.temoinBloquant = temoinBloquant;
  }


  public DemandeAccord dateDebutValidite(String dateDebutValidite) {
    
    this.dateDebutValidite = dateDebutValidite;
    return this;
  }

   /**
   * La date de début de validité
   * @return dateDebutValidite
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La date de début de validité")

  public String getDateDebutValidite() {
    return dateDebutValidite;
  }


  public void setDateDebutValidite(String dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
  }


  public DemandeAccord dateFinValidite(String dateFinValidite) {
    
    this.dateFinValidite = dateFinValidite;
    return this;
  }

   /**
   * La date de fin de validité
   * @return dateFinValidite
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La date de fin de validité")

  public String getDateFinValidite() {
    return dateFinValidite;
  }


  public void setDateFinValidite(String dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
  }


  public DemandeAccord prioriteAffichage(Integer prioriteAffichage) {
    
    this.prioriteAffichage = prioriteAffichage;
    return this;
  }

   /**
   * La priorité d&#39;affichage : Plus le nombre est élevé, plus le document apparaît en priorité dans la liste de valeurs. 
   * @return prioriteAffichage
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La priorité d'affichage : Plus le nombre est élevé, plus le document apparaît en priorité dans la liste de valeurs. ")

  public Integer getPrioriteAffichage() {
    return prioriteAffichage;
  }


  public void setPrioriteAffichage(Integer prioriteAffichage) {
    this.prioriteAffichage = prioriteAffichage;
  }


  public DemandeAccord temoinVisible(Boolean temoinVisible) {
    
    this.temoinVisible = temoinVisible;
    return this;
  }

   /**
   * Si le document sera visible par l&#39;étudiant ou non.
   * @return temoinVisible
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Si le document sera visible par l'étudiant ou non.")

  public Boolean getTemoinVisible() {
    return temoinVisible;
  }


  public void setTemoinVisible(Boolean temoinVisible) {
    this.temoinVisible = temoinVisible;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DemandeAccord demandeAccord = (DemandeAccord) o;
    return Objects.equals(this.code, demandeAccord.code) &&
        Objects.equals(this.codeStructure, demandeAccord.codeStructure) &&
        Objects.equals(this.libelleAffichage, demandeAccord.libelleAffichage) &&
        Objects.equals(this.contenu, demandeAccord.contenu) &&
        Objects.equals(this.libelleAcceptation, demandeAccord.libelleAcceptation) &&
        Objects.equals(this.libelleRefus, demandeAccord.libelleRefus) &&
        Objects.equals(this.temoinBloquant, demandeAccord.temoinBloquant) &&
        Objects.equals(this.dateDebutValidite, demandeAccord.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, demandeAccord.dateFinValidite) &&
        Objects.equals(this.prioriteAffichage, demandeAccord.prioriteAffichage) &&
        Objects.equals(this.temoinVisible, demandeAccord.temoinVisible);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, codeStructure, libelleAffichage, contenu, libelleAcceptation, libelleRefus, temoinBloquant, dateDebutValidite, dateFinValidite, prioriteAffichage, temoinVisible);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DemandeAccord {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    contenu: ").append(toIndentedString(contenu)).append("\n");
    sb.append("    libelleAcceptation: ").append(toIndentedString(libelleAcceptation)).append("\n");
    sb.append("    libelleRefus: ").append(toIndentedString(libelleRefus)).append("\n");
    sb.append("    temoinBloquant: ").append(toIndentedString(temoinBloquant)).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
    sb.append("    prioriteAffichage: ").append(toIndentedString(prioriteAffichage)).append("\n");
    sb.append("    temoinVisible: ").append(toIndentedString(temoinVisible)).append("\n");
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

