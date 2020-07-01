/*
 * Swagger Gestion - INS
 * Il s'agit de l'API de gestion - INS.
 *
 * The version of the OpenAPI document: 1.0.0
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
import fr.univlorraine.pegase.model.insgestion.BourseOuAide;
import fr.univlorraine.pegase.model.insgestion.Calendrier;
import fr.univlorraine.pegase.model.insgestion.Formation;
import fr.univlorraine.pegase.model.insgestion.ObjetFormationOuGroupement;
import fr.univlorraine.pegase.model.insgestion.Periode;
import fr.univlorraine.pegase.model.insgestion.RegimeInscription;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Décrit une Formation ou un chemin depuis une Formation vers un ObjetFormation sur lequel on peut s&#39;inscrire
 */
@ApiModel(description = "Décrit une Formation ou un chemin depuis une Formation vers un ObjetFormation sur lequel on peut s'inscrire")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2020-06-30T15:25:34.956+02:00[Europe/Paris]")
public class CiblePourInscription {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_CODE_CHEMIN = "codeChemin";
  @SerializedName(SERIALIZED_NAME_CODE_CHEMIN)
  private String codeChemin;

  public static final String SERIALIZED_NAME_CODE_STRUCTURE = "codeStructure";
  @SerializedName(SERIALIZED_NAME_CODE_STRUCTURE)
  private String codeStructure;

  public static final String SERIALIZED_NAME_FORMATION = "formation";
  @SerializedName(SERIALIZED_NAME_FORMATION)
  private Formation formation = null;

  public static final String SERIALIZED_NAME_PERIODE = "periode";
  @SerializedName(SERIALIZED_NAME_PERIODE)
  private Periode periode = null;

  public static final String SERIALIZED_NAME_CALENDRIER = "calendrier";
  @SerializedName(SERIALIZED_NAME_CALENDRIER)
  private Calendrier calendrier = null;

  public static final String SERIALIZED_NAME_CHEMIN = "chemin";
  @SerializedName(SERIALIZED_NAME_CHEMIN)
  private List<ObjetFormationOuGroupement> chemin = null;

  public static final String SERIALIZED_NAME_REGIMES_INSCRIPTION = "regimesInscription";
  @SerializedName(SERIALIZED_NAME_REGIMES_INSCRIPTION)
  private List<RegimeInscription> regimesInscription = null;

  public static final String SERIALIZED_NAME_BOURSES_OU_AIDES = "boursesOuAides";
  @SerializedName(SERIALIZED_NAME_BOURSES_OU_AIDES)
  private List<BourseOuAide> boursesOuAides = null;

  public static final String SERIALIZED_NAME_OUVERTE_A_INSCRIPTION = "ouverteAInscription";
  @SerializedName(SERIALIZED_NAME_OUVERTE_A_INSCRIPTION)
  private Boolean ouverteAInscription;


  public CiblePourInscription code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code de l&#39;objet
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'objet")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public CiblePourInscription codeChemin(String codeChemin) {
    
    this.codeChemin = codeChemin;
    return this;
  }

   /**
   * Le chemin vers l&#39;objet au moyen des codes
   * @return codeChemin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le chemin vers l'objet au moyen des codes")

  public String getCodeChemin() {
    return codeChemin;
  }


  public void setCodeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
  }


  public CiblePourInscription codeStructure(String codeStructure) {
    
    this.codeStructure = codeStructure;
    return this;
  }

   /**
   * Le code uai de la structure
   * @return codeStructure
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code uai de la structure")

  public String getCodeStructure() {
    return codeStructure;
  }


  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public CiblePourInscription formation(Formation formation) {
    
    this.formation = formation;
    return this;
  }

   /**
   * Get formation
   * @return formation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Formation getFormation() {
    return formation;
  }


  public void setFormation(Formation formation) {
    this.formation = formation;
  }


  public CiblePourInscription periode(Periode periode) {
    
    this.periode = periode;
    return this;
  }

   /**
   * Get periode
   * @return periode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Periode getPeriode() {
    return periode;
  }


  public void setPeriode(Periode periode) {
    this.periode = periode;
  }


  public CiblePourInscription calendrier(Calendrier calendrier) {
    
    this.calendrier = calendrier;
    return this;
  }

   /**
   * Get calendrier
   * @return calendrier
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Calendrier getCalendrier() {
    return calendrier;
  }


  public void setCalendrier(Calendrier calendrier) {
    this.calendrier = calendrier;
  }


  public CiblePourInscription chemin(List<ObjetFormationOuGroupement> chemin) {
    
    this.chemin = chemin;
    return this;
  }

  public CiblePourInscription addCheminItem(ObjetFormationOuGroupement cheminItem) {
    if (this.chemin == null) {
      this.chemin = new ArrayList<>();
    }
    this.chemin.add(cheminItem);
    return this;
  }

   /**
   * liste des ObjetFormation ou Groupement depuis la Formation racine jusqu&#39;à l&#39;ObjetFormation feuille (même code que CiblePourInscription), peut être vide
   * @return chemin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "liste des ObjetFormation ou Groupement depuis la Formation racine jusqu'à l'ObjetFormation feuille (même code que CiblePourInscription), peut être vide")

  public List<ObjetFormationOuGroupement> getChemin() {
    return chemin;
  }


  public void setChemin(List<ObjetFormationOuGroupement> chemin) {
    this.chemin = chemin;
  }


  public CiblePourInscription regimesInscription(List<RegimeInscription> regimesInscription) {
    
    this.regimesInscription = regimesInscription;
    return this;
  }

  public CiblePourInscription addRegimesInscriptionItem(RegimeInscription regimesInscriptionItem) {
    if (this.regimesInscription == null) {
      this.regimesInscription = new ArrayList<>();
    }
    this.regimesInscription.add(regimesInscriptionItem);
    return this;
  }

   /**
   * liste des regimes d&#39;inscription récupérée au moment de la mise en œuvre de la cible
   * @return regimesInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "liste des regimes d'inscription récupérée au moment de la mise en œuvre de la cible")

  public List<RegimeInscription> getRegimesInscription() {
    return regimesInscription;
  }


  public void setRegimesInscription(List<RegimeInscription> regimesInscription) {
    this.regimesInscription = regimesInscription;
  }


  public CiblePourInscription boursesOuAides(List<BourseOuAide> boursesOuAides) {
    
    this.boursesOuAides = boursesOuAides;
    return this;
  }

  public CiblePourInscription addBoursesOuAidesItem(BourseOuAide boursesOuAidesItem) {
    if (this.boursesOuAides == null) {
      this.boursesOuAides = new ArrayList<>();
    }
    this.boursesOuAides.add(boursesOuAidesItem);
    return this;
  }

   /**
   * liste des bourses ou aides récupérée au moment de la mise en œuvre de la cible
   * @return boursesOuAides
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "liste des bourses ou aides récupérée au moment de la mise en œuvre de la cible")

  public List<BourseOuAide> getBoursesOuAides() {
    return boursesOuAides;
  }


  public void setBoursesOuAides(List<BourseOuAide> boursesOuAides) {
    this.boursesOuAides = boursesOuAides;
  }


  public CiblePourInscription ouverteAInscription(Boolean ouverteAInscription) {
    
    this.ouverteAInscription = ouverteAInscription;
    return this;
  }

   /**
   * Temoin indiquant que la cible est ouverte à l&#39;inscription
   * @return ouverteAInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Temoin indiquant que la cible est ouverte à l'inscription")

  public Boolean getOuverteAInscription() {
    return ouverteAInscription;
  }


  public void setOuverteAInscription(Boolean ouverteAInscription) {
    this.ouverteAInscription = ouverteAInscription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CiblePourInscription ciblePourInscription = (CiblePourInscription) o;
    return Objects.equals(this.code, ciblePourInscription.code) &&
        Objects.equals(this.codeChemin, ciblePourInscription.codeChemin) &&
        Objects.equals(this.codeStructure, ciblePourInscription.codeStructure) &&
        Objects.equals(this.formation, ciblePourInscription.formation) &&
        Objects.equals(this.periode, ciblePourInscription.periode) &&
        Objects.equals(this.calendrier, ciblePourInscription.calendrier) &&
        Objects.equals(this.chemin, ciblePourInscription.chemin) &&
        Objects.equals(this.regimesInscription, ciblePourInscription.regimesInscription) &&
        Objects.equals(this.boursesOuAides, ciblePourInscription.boursesOuAides) &&
        Objects.equals(this.ouverteAInscription, ciblePourInscription.ouverteAInscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, codeChemin, codeStructure, formation, periode, calendrier, chemin, regimesInscription, boursesOuAides, ouverteAInscription);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CiblePourInscription {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    codeChemin: ").append(toIndentedString(codeChemin)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    formation: ").append(toIndentedString(formation)).append("\n");
    sb.append("    periode: ").append(toIndentedString(periode)).append("\n");
    sb.append("    calendrier: ").append(toIndentedString(calendrier)).append("\n");
    sb.append("    chemin: ").append(toIndentedString(chemin)).append("\n");
    sb.append("    regimesInscription: ").append(toIndentedString(regimesInscription)).append("\n");
    sb.append("    boursesOuAides: ").append(toIndentedString(boursesOuAides)).append("\n");
    sb.append("    ouverteAInscription: ").append(toIndentedString(ouverteAInscription)).append("\n");
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

