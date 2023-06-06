/*
 * COC Publication v1 - Contrôle du cursus - Bloc «publication» - [EXTERNE]
 * Liste l'ensemble des services et des opérations disponible pour la publication de notes et des résultats à partir des données du module COC (Contrôle du cursus)  **Note importante :** Cette API est marquée [EXTERNE], elle peut être utilisée par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé (voir le paragraphe [Authentification](#section/Authentication) pour les détails).  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :  * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)    * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés    * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)  * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))  * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)  * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)  * boolean - Un booléen représenté par `true` ou `false`  ### Code retour   * 200 - Ok : L'opération s'est déroulée avec succès  * 201 - Created : L'opération a aboutie à la création d'une ressource  * 400 - Bad request :    * Un ou des paramètres d'entrées sont erronées    * Une erreur fonctionnelle s'est produite  * 404 - Not Found : La ressource demandée n'est pas trouvé    * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide  * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 1.4.0
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
import fr.univlorraine.pegase.model.coc.Absence;
import fr.univlorraine.pegase.model.coc.Controle;
import fr.univlorraine.pegase.model.coc.MentionHonorifique;
import fr.univlorraine.pegase.model.coc.NotationEcts;
import fr.univlorraine.pegase.model.coc.NotationGpa;
import fr.univlorraine.pegase.model.coc.ObjetFeuille;
import fr.univlorraine.pegase.model.coc.Periode;
import fr.univlorraine.pegase.model.coc.Resultat;
import fr.univlorraine.pegase.model.coc.TypeAmenagement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Chemin
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-06-06T11:03:30.102+02:00[Europe/Paris]")
public class Chemin {
  public static final String SERIALIZED_NAME_CODE_CHEMIN = "codeChemin";
  @SerializedName(SERIALIZED_NAME_CODE_CHEMIN)
  private String codeChemin;

  public static final String SERIALIZED_NAME_OBJET_FEUILLE = "objetFeuille";
  @SerializedName(SERIALIZED_NAME_OBJET_FEUILLE)
  private ObjetFeuille objetFeuille;

  public static final String SERIALIZED_NAME_BAREME = "bareme";
  @SerializedName(SERIALIZED_NAME_BAREME)
  private Integer bareme;

  public static final String SERIALIZED_NAME_NOTE_SESSION1 = "noteSession1";
  @SerializedName(SERIALIZED_NAME_NOTE_SESSION1)
  private BigDecimal noteSession1;

  public static final String SERIALIZED_NAME_RESULTAT_SESSION1 = "resultatSession1";
  @SerializedName(SERIALIZED_NAME_RESULTAT_SESSION1)
  private Resultat resultatSession1;

  public static final String SERIALIZED_NAME_ABSENCE_SESSION1 = "absenceSession1";
  @SerializedName(SERIALIZED_NAME_ABSENCE_SESSION1)
  private Absence absenceSession1;

  public static final String SERIALIZED_NAME_CONCERNE_PAR_SESSION2 = "concerneParSession2";
  @SerializedName(SERIALIZED_NAME_CONCERNE_PAR_SESSION2)
  private Boolean concerneParSession2;

  public static final String SERIALIZED_NAME_NOTE_SESSION2 = "noteSession2";
  @SerializedName(SERIALIZED_NAME_NOTE_SESSION2)
  private BigDecimal noteSession2;

  public static final String SERIALIZED_NAME_RESULTAT_SESSION2 = "resultatSession2";
  @SerializedName(SERIALIZED_NAME_RESULTAT_SESSION2)
  private Resultat resultatSession2;

  public static final String SERIALIZED_NAME_ABSENCE_SESSION2 = "absenceSession2";
  @SerializedName(SERIALIZED_NAME_ABSENCE_SESSION2)
  private Absence absenceSession2;

  public static final String SERIALIZED_NAME_COEFFICIENT_SESSION1 = "coefficientSession1";
  @SerializedName(SERIALIZED_NAME_COEFFICIENT_SESSION1)
  private BigDecimal coefficientSession1;

  public static final String SERIALIZED_NAME_COEFFICIENT_SESSION2 = "coefficientSession2";
  @SerializedName(SERIALIZED_NAME_COEFFICIENT_SESSION2)
  private BigDecimal coefficientSession2;

  public static final String SERIALIZED_NAME_COEFFICIENT_FINAL = "coefficientFinal";
  @SerializedName(SERIALIZED_NAME_COEFFICIENT_FINAL)
  private BigDecimal coefficientFinal;

  public static final String SERIALIZED_NAME_NOTE_CONSOLIDEE = "noteConsolidee";
  @SerializedName(SERIALIZED_NAME_NOTE_CONSOLIDEE)
  private BigDecimal noteConsolidee;

  public static final String SERIALIZED_NAME_POINTS_JURY = "pointsJury";
  @SerializedName(SERIALIZED_NAME_POINTS_JURY)
  private BigDecimal pointsJury;

  public static final String SERIALIZED_NAME_NOTE_FINALE = "noteFinale";
  @SerializedName(SERIALIZED_NAME_NOTE_FINALE)
  private BigDecimal noteFinale;

  public static final String SERIALIZED_NAME_RESULTAT_FINAL = "resultatFinal";
  @SerializedName(SERIALIZED_NAME_RESULTAT_FINAL)
  private Resultat resultatFinal;

  public static final String SERIALIZED_NAME_ABSENCE_FINALE = "absenceFinale";
  @SerializedName(SERIALIZED_NAME_ABSENCE_FINALE)
  private Absence absenceFinale;

  public static final String SERIALIZED_NAME_NUMERO_SESSION_RETENUE_NOTE = "numeroSessionRetenueNote";
  @SerializedName(SERIALIZED_NAME_NUMERO_SESSION_RETENUE_NOTE)
  private Integer numeroSessionRetenueNote;

  public static final String SERIALIZED_NAME_NUMERO_SESSION_RETENUE_RESULTAT = "numeroSessionRetenueResultat";
  @SerializedName(SERIALIZED_NAME_NUMERO_SESSION_RETENUE_RESULTAT)
  private Integer numeroSessionRetenueResultat;

  public static final String SERIALIZED_NAME_CREDIT_ECTS = "creditEcts";
  @SerializedName(SERIALIZED_NAME_CREDIT_ECTS)
  private BigDecimal creditEcts;

  public static final String SERIALIZED_NAME_MENTION_HONORIFIQUE = "mentionHonorifique";
  @SerializedName(SERIALIZED_NAME_MENTION_HONORIFIQUE)
  private MentionHonorifique mentionHonorifique;

  public static final String SERIALIZED_NAME_NOTATION_GPA = "notationGpa";
  @SerializedName(SERIALIZED_NAME_NOTATION_GPA)
  private NotationGpa notationGpa;

  public static final String SERIALIZED_NAME_NOTATION_ECTS = "notationEcts";
  @SerializedName(SERIALIZED_NAME_NOTATION_ECTS)
  private NotationEcts notationEcts;

  public static final String SERIALIZED_NAME_DELIBERE_SESSION1 = "delibereSession1";
  @SerializedName(SERIALIZED_NAME_DELIBERE_SESSION1)
  private Boolean delibereSession1;

  public static final String SERIALIZED_NAME_DELIBERE_FINALES = "delibereFinales";
  @SerializedName(SERIALIZED_NAME_DELIBERE_FINALES)
  private Boolean delibereFinales;

  public static final String SERIALIZED_NAME_RANG_FINAL = "rangFinal";
  @SerializedName(SERIALIZED_NAME_RANG_FINAL)
  private Integer rangFinal;

  public static final String SERIALIZED_NAME_RANG_TOTAL_FINAL = "rangTotalFinal";
  @SerializedName(SERIALIZED_NAME_RANG_TOTAL_FINAL)
  private Integer rangTotalFinal;

  public static final String SERIALIZED_NAME_PUBLIE_SESSION1 = "publieSession1";
  @SerializedName(SERIALIZED_NAME_PUBLIE_SESSION1)
  private Boolean publieSession1;

  public static final String SERIALIZED_NAME_PUBLIE_SESSION2 = "publieSession2";
  @SerializedName(SERIALIZED_NAME_PUBLIE_SESSION2)
  private Boolean publieSession2;

  public static final String SERIALIZED_NAME_PUBLIE_EVALUATIONS_FINALES = "publieEvaluationsFinales";
  @SerializedName(SERIALIZED_NAME_PUBLIE_EVALUATIONS_FINALES)
  private Boolean publieEvaluationsFinales;

  public static final String SERIALIZED_NAME_LISTE_TYPE_AMENAGEMENT = "listeTypeAmenagement";
  @SerializedName(SERIALIZED_NAME_LISTE_TYPE_AMENAGEMENT)
  private List<TypeAmenagement> listeTypeAmenagement = null;

  public static final String SERIALIZED_NAME_LISTE_CONTROLE = "listeControle";
  @SerializedName(SERIALIZED_NAME_LISTE_CONTROLE)
  private List<Controle> listeControle = new ArrayList<>();

  public static final String SERIALIZED_NAME_ACQUIS_CAPITALISE = "acquisCapitalise";
  @SerializedName(SERIALIZED_NAME_ACQUIS_CAPITALISE)
  private Boolean acquisCapitalise;

  public static final String SERIALIZED_NAME_PERIODE_ACQUIS_CAPITALISE = "periodeAcquisCapitalise";
  @SerializedName(SERIALIZED_NAME_PERIODE_ACQUIS_CAPITALISE)
  private Periode periodeAcquisCapitalise;

  public Chemin() { 
  }

  public Chemin codeChemin(String codeChemin) {
    
    this.codeChemin = codeChemin;
    return this;
  }

   /**
   * Le code chemin  Un code chemin est une liste de code séparé par un &#39;&gt;&#39; (supérieur) en commençant par le code de la formation jusqu&#39;à l&#39;objet de formation le plus fin. 
   * @return codeChemin
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "LIC-CHIMIE>ANNEE1", required = true, value = "Le code chemin  Un code chemin est une liste de code séparé par un '>' (supérieur) en commençant par le code de la formation jusqu'à l'objet de formation le plus fin. ")

  public String getCodeChemin() {
    return codeChemin;
  }


  public void setCodeChemin(String codeChemin) {
    this.codeChemin = codeChemin;
  }


  public Chemin objetFeuille(ObjetFeuille objetFeuille) {
    
    this.objetFeuille = objetFeuille;
    return this;
  }

   /**
   * Get objetFeuille
   * @return objetFeuille
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ObjetFeuille getObjetFeuille() {
    return objetFeuille;
  }


  public void setObjetFeuille(ObjetFeuille objetFeuille) {
    this.objetFeuille = objetFeuille;
  }


  public Chemin bareme(Integer bareme) {
    
    this.bareme = bareme;
    return this;
  }

   /**
   * Le barème (le maximum) de la note  Obligatoirement renseigné si une note est attendue. 
   * @return bareme
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "20", value = "Le barème (le maximum) de la note  Obligatoirement renseigné si une note est attendue. ")

  public Integer getBareme() {
    return bareme;
  }


  public void setBareme(Integer bareme) {
    this.bareme = bareme;
  }


  public Chemin noteSession1(BigDecimal noteSession1) {
    
    this.noteSession1 = noteSession1;
    return this;
  }

   /**
   * La note obtenue lors de la session 1
   * @return noteSession1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "12.345", value = "La note obtenue lors de la session 1")

  public BigDecimal getNoteSession1() {
    return noteSession1;
  }


  public void setNoteSession1(BigDecimal noteSession1) {
    this.noteSession1 = noteSession1;
  }


  public Chemin resultatSession1(Resultat resultatSession1) {
    
    this.resultatSession1 = resultatSession1;
    return this;
  }

   /**
   * Get resultatSession1
   * @return resultatSession1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Resultat getResultatSession1() {
    return resultatSession1;
  }


  public void setResultatSession1(Resultat resultatSession1) {
    this.resultatSession1 = resultatSession1;
  }


  public Chemin absenceSession1(Absence absenceSession1) {
    
    this.absenceSession1 = absenceSession1;
    return this;
  }

   /**
   * Get absenceSession1
   * @return absenceSession1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Absence getAbsenceSession1() {
    return absenceSession1;
  }


  public void setAbsenceSession1(Absence absenceSession1) {
    this.absenceSession1 = absenceSession1;
  }


  public Chemin concerneParSession2(Boolean concerneParSession2) {
    
    this.concerneParSession2 = concerneParSession2;
    return this;
  }

   /**
   * Est-ce que l&#39;apprenant est concerné par (doit passer) la session 2 ?
   * @return concerneParSession2
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "false", required = true, value = "Est-ce que l'apprenant est concerné par (doit passer) la session 2 ?")

  public Boolean getConcerneParSession2() {
    return concerneParSession2;
  }


  public void setConcerneParSession2(Boolean concerneParSession2) {
    this.concerneParSession2 = concerneParSession2;
  }


  public Chemin noteSession2(BigDecimal noteSession2) {
    
    this.noteSession2 = noteSession2;
    return this;
  }

   /**
   * La note obtenue lors de la session 2
   * @return noteSession2
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La note obtenue lors de la session 2")

  public BigDecimal getNoteSession2() {
    return noteSession2;
  }


  public void setNoteSession2(BigDecimal noteSession2) {
    this.noteSession2 = noteSession2;
  }


  public Chemin resultatSession2(Resultat resultatSession2) {
    
    this.resultatSession2 = resultatSession2;
    return this;
  }

   /**
   * Get resultatSession2
   * @return resultatSession2
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Resultat getResultatSession2() {
    return resultatSession2;
  }


  public void setResultatSession2(Resultat resultatSession2) {
    this.resultatSession2 = resultatSession2;
  }


  public Chemin absenceSession2(Absence absenceSession2) {
    
    this.absenceSession2 = absenceSession2;
    return this;
  }

   /**
   * Get absenceSession2
   * @return absenceSession2
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Absence getAbsenceSession2() {
    return absenceSession2;
  }


  public void setAbsenceSession2(Absence absenceSession2) {
    this.absenceSession2 = absenceSession2;
  }


  public Chemin coefficientSession1(BigDecimal coefficientSession1) {
    
    this.coefficientSession1 = coefficientSession1;
    return this;
  }

   /**
   * Le coefficient utilisé pour le calcul de la note de session 1 de l&#39;objet parent. 
   * @return coefficientSession1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2.5", value = "Le coefficient utilisé pour le calcul de la note de session 1 de l'objet parent. ")

  public BigDecimal getCoefficientSession1() {
    return coefficientSession1;
  }


  public void setCoefficientSession1(BigDecimal coefficientSession1) {
    this.coefficientSession1 = coefficientSession1;
  }


  public Chemin coefficientSession2(BigDecimal coefficientSession2) {
    
    this.coefficientSession2 = coefficientSession2;
    return this;
  }

   /**
   * Le coefficient utilisé pour le calcul de la note de session 2 de l&#39;objet parent si l&#39;apprenant est concerné par la session 2 sur l&#39;objet parent. 
   * @return coefficientSession2
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1.5", value = "Le coefficient utilisé pour le calcul de la note de session 2 de l'objet parent si l'apprenant est concerné par la session 2 sur l'objet parent. ")

  public BigDecimal getCoefficientSession2() {
    return coefficientSession2;
  }


  public void setCoefficientSession2(BigDecimal coefficientSession2) {
    this.coefficientSession2 = coefficientSession2;
  }


  public Chemin coefficientFinal(BigDecimal coefficientFinal) {
    
    this.coefficientFinal = coefficientFinal;
    return this;
  }

   /**
   * Le coefficient utilisé pour le calcul de la note de l&#39;objet parent.  C&#39;est soit le &#x60;coefficientSession1&#x60; ou le &#x60;coefficientSession2&#x60; en fonction de la session retenue pour la note de l&#39;objet parent.  Voir aussi &#x60;numeroSessionRetenueNote&#x60;. 
   * @return coefficientFinal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1.5", value = "Le coefficient utilisé pour le calcul de la note de l'objet parent.  C'est soit le `coefficientSession1` ou le `coefficientSession2` en fonction de la session retenue pour la note de l'objet parent.  Voir aussi `numeroSessionRetenueNote`. ")

  public BigDecimal getCoefficientFinal() {
    return coefficientFinal;
  }


  public void setCoefficientFinal(BigDecimal coefficientFinal) {
    this.coefficientFinal = coefficientFinal;
  }


  public Chemin noteConsolidee(BigDecimal noteConsolidee) {
    
    this.noteConsolidee = noteConsolidee;
    return this;
  }

   /**
   * La note retenue pour l&#39;objet. C&#39;est soit la &#x60;noteSession1&#x60; ou la &#x60;noteSession2&#x60; en fonction du paramétrage de consolidation de la note. Voir aussi &#x60;numeroSessionRetenueNote&#x60;.
   * @return noteConsolidee
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "12.345", value = "La note retenue pour l'objet. C'est soit la `noteSession1` ou la `noteSession2` en fonction du paramétrage de consolidation de la note. Voir aussi `numeroSessionRetenueNote`.")

  public BigDecimal getNoteConsolidee() {
    return noteConsolidee;
  }


  public void setNoteConsolidee(BigDecimal noteConsolidee) {
    this.noteConsolidee = noteConsolidee;
  }


  public Chemin pointsJury(BigDecimal pointsJury) {
    
    this.pointsJury = pointsJury;
    return this;
  }

   /**
   * Le nombre de points de jury
   * @return pointsJury
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0.123", value = "Le nombre de points de jury")

  public BigDecimal getPointsJury() {
    return pointsJury;
  }


  public void setPointsJury(BigDecimal pointsJury) {
    this.pointsJury = pointsJury;
  }


  public Chemin noteFinale(BigDecimal noteFinale) {
    
    this.noteFinale = noteFinale;
    return this;
  }

   /**
   * La note finale (la note consolidée + les points de jury)
   * @return noteFinale
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "12.468", value = "La note finale (la note consolidée + les points de jury)")

  public BigDecimal getNoteFinale() {
    return noteFinale;
  }


  public void setNoteFinale(BigDecimal noteFinale) {
    this.noteFinale = noteFinale;
  }


  public Chemin resultatFinal(Resultat resultatFinal) {
    
    this.resultatFinal = resultatFinal;
    return this;
  }

   /**
   * Get resultatFinal
   * @return resultatFinal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Resultat getResultatFinal() {
    return resultatFinal;
  }


  public void setResultatFinal(Resultat resultatFinal) {
    this.resultatFinal = resultatFinal;
  }


  public Chemin absenceFinale(Absence absenceFinale) {
    
    this.absenceFinale = absenceFinale;
    return this;
  }

   /**
   * Get absenceFinale
   * @return absenceFinale
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Absence getAbsenceFinale() {
    return absenceFinale;
  }


  public void setAbsenceFinale(Absence absenceFinale) {
    this.absenceFinale = absenceFinale;
  }


  public Chemin numeroSessionRetenueNote(Integer numeroSessionRetenueNote) {
    
    this.numeroSessionRetenueNote = numeroSessionRetenueNote;
    return this;
  }

   /**
   * Le numéro de session retenu pour la note de l&#39;objet (voir &#x60;noteConsolidee&#x60; et &#x60;coefficientFinal&#x60;)
   * @return numeroSessionRetenueNote
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "Le numéro de session retenu pour la note de l'objet (voir `noteConsolidee` et `coefficientFinal`)")

  public Integer getNumeroSessionRetenueNote() {
    return numeroSessionRetenueNote;
  }


  public void setNumeroSessionRetenueNote(Integer numeroSessionRetenueNote) {
    this.numeroSessionRetenueNote = numeroSessionRetenueNote;
  }


  public Chemin numeroSessionRetenueResultat(Integer numeroSessionRetenueResultat) {
    
    this.numeroSessionRetenueResultat = numeroSessionRetenueResultat;
    return this;
  }

   /**
   * Le numéro de session retenu pour la note de l&#39;objet (voir &#x60;resultatFinal&#x60;)
   * @return numeroSessionRetenueResultat
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "Le numéro de session retenu pour la note de l'objet (voir `resultatFinal`)")

  public Integer getNumeroSessionRetenueResultat() {
    return numeroSessionRetenueResultat;
  }


  public void setNumeroSessionRetenueResultat(Integer numeroSessionRetenueResultat) {
    this.numeroSessionRetenueResultat = numeroSessionRetenueResultat;
  }


  public Chemin creditEcts(BigDecimal creditEcts) {
    
    this.creditEcts = creditEcts;
    return this;
  }

   /**
   * Le nombre de crédits ECTS (European Credits Transfer System) acquis.
   * @return creditEcts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "6.5", value = "Le nombre de crédits ECTS (European Credits Transfer System) acquis.")

  public BigDecimal getCreditEcts() {
    return creditEcts;
  }


  public void setCreditEcts(BigDecimal creditEcts) {
    this.creditEcts = creditEcts;
  }


  public Chemin mentionHonorifique(MentionHonorifique mentionHonorifique) {
    
    this.mentionHonorifique = mentionHonorifique;
    return this;
  }

   /**
   * Get mentionHonorifique
   * @return mentionHonorifique
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public MentionHonorifique getMentionHonorifique() {
    return mentionHonorifique;
  }


  public void setMentionHonorifique(MentionHonorifique mentionHonorifique) {
    this.mentionHonorifique = mentionHonorifique;
  }


  public Chemin notationGpa(NotationGpa notationGpa) {
    
    this.notationGpa = notationGpa;
    return this;
  }

   /**
   * Get notationGpa
   * @return notationGpa
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public NotationGpa getNotationGpa() {
    return notationGpa;
  }


  public void setNotationGpa(NotationGpa notationGpa) {
    this.notationGpa = notationGpa;
  }


  public Chemin notationEcts(NotationEcts notationEcts) {
    
    this.notationEcts = notationEcts;
    return this;
  }

   /**
   * Get notationEcts
   * @return notationEcts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public NotationEcts getNotationEcts() {
    return notationEcts;
  }


  public void setNotationEcts(NotationEcts notationEcts) {
    this.notationEcts = notationEcts;
  }


  public Chemin delibereSession1(Boolean delibereSession1) {
    
    this.delibereSession1 = delibereSession1;
    return this;
  }

   /**
   * Est-ce que la session 1 a été délibérée ?
   * @return delibereSession1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "true", value = "Est-ce que la session 1 a été délibérée ?")

  public Boolean getDelibereSession1() {
    return delibereSession1;
  }


  public void setDelibereSession1(Boolean delibereSession1) {
    this.delibereSession1 = delibereSession1;
  }


  public Chemin delibereFinales(Boolean delibereFinales) {
    
    this.delibereFinales = delibereFinales;
    return this;
  }

   /**
   * Est-ce que les évaluations finales (et session 2) ont été délibérées ?
   * @return delibereFinales
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est-ce que les évaluations finales (et session 2) ont été délibérées ?")

  public Boolean getDelibereFinales() {
    return delibereFinales;
  }


  public void setDelibereFinales(Boolean delibereFinales) {
    this.delibereFinales = delibereFinales;
  }


  public Chemin rangFinal(Integer rangFinal) {
    
    this.rangFinal = rangFinal;
    return this;
  }

   /**
   * Le rang final de l&#39;apprenant
   * @return rangFinal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le rang final de l'apprenant")

  public Integer getRangFinal() {
    return rangFinal;
  }


  public void setRangFinal(Integer rangFinal) {
    this.rangFinal = rangFinal;
  }


  public Chemin rangTotalFinal(Integer rangTotalFinal) {
    
    this.rangTotalFinal = rangTotalFinal;
    return this;
  }

   /**
   * Le rang total (le maximum) final de l&#39;apprenant
   * @return rangTotalFinal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le rang total (le maximum) final de l'apprenant")

  public Integer getRangTotalFinal() {
    return rangTotalFinal;
  }


  public void setRangTotalFinal(Integer rangTotalFinal) {
    this.rangTotalFinal = rangTotalFinal;
  }


  public Chemin publieSession1(Boolean publieSession1) {
    
    this.publieSession1 = publieSession1;
    return this;
  }

   /**
   * Est-ce que la publication des données de la session 1 est autorisée ?  Si ce booléen est à &#x60;false&#x60;, alors les attributs suivants ne sont jamais renseignés (toujours à &#x60;null&#x60;) :   * noteSession1   * resultatSession1   * absenceSession1   * coefficientSession1   * delibereSession1 
   * @return publieSession1
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "true", required = true, value = "Est-ce que la publication des données de la session 1 est autorisée ?  Si ce booléen est à `false`, alors les attributs suivants ne sont jamais renseignés (toujours à `null`) :   * noteSession1   * resultatSession1   * absenceSession1   * coefficientSession1   * delibereSession1 ")

  public Boolean getPublieSession1() {
    return publieSession1;
  }


  public void setPublieSession1(Boolean publieSession1) {
    this.publieSession1 = publieSession1;
  }


  public Chemin publieSession2(Boolean publieSession2) {
    
    this.publieSession2 = publieSession2;
    return this;
  }

   /**
   * Est-ce que la publication des données de la session 2 est autorisée ?  Si ce booléen est à &#x60;false&#x60;, alors les attributs suivants ne sont jamais renseignés (toujours à &#x60;null&#x60;) :   * noteSession2   * resultatSession2   * absenceSession2   * coefficientSession2 
   * @return publieSession2
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "true", required = true, value = "Est-ce que la publication des données de la session 2 est autorisée ?  Si ce booléen est à `false`, alors les attributs suivants ne sont jamais renseignés (toujours à `null`) :   * noteSession2   * resultatSession2   * absenceSession2   * coefficientSession2 ")

  public Boolean getPublieSession2() {
    return publieSession2;
  }


  public void setPublieSession2(Boolean publieSession2) {
    this.publieSession2 = publieSession2;
  }


  public Chemin publieEvaluationsFinales(Boolean publieEvaluationsFinales) {
    
    this.publieEvaluationsFinales = publieEvaluationsFinales;
    return this;
  }

   /**
   * Est-ce que la publication des données des évaluations finales est autorisée ?  Si ce booléen est à &#x60;false&#x60;, alors les attributs suivants ne sont jamais renseignés (toujours à &#x60;null&#x60;) :   * noteConsolidee   * pointsJury   * noteFinale   * resultatFinal   * absenceFinale   * coefficientFinal   * numeroSessionRetenueNote   * numeroSessionRetenueResultat   * creditEcts   * mentionHonorifique   * notationGpa   * notationEcts   * delibereFinales 
   * @return publieEvaluationsFinales
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(example = "false", required = true, value = "Est-ce que la publication des données des évaluations finales est autorisée ?  Si ce booléen est à `false`, alors les attributs suivants ne sont jamais renseignés (toujours à `null`) :   * noteConsolidee   * pointsJury   * noteFinale   * resultatFinal   * absenceFinale   * coefficientFinal   * numeroSessionRetenueNote   * numeroSessionRetenueResultat   * creditEcts   * mentionHonorifique   * notationGpa   * notationEcts   * delibereFinales ")

  public Boolean getPublieEvaluationsFinales() {
    return publieEvaluationsFinales;
  }


  public void setPublieEvaluationsFinales(Boolean publieEvaluationsFinales) {
    this.publieEvaluationsFinales = publieEvaluationsFinales;
  }


  public Chemin listeTypeAmenagement(List<TypeAmenagement> listeTypeAmenagement) {
    
    this.listeTypeAmenagement = listeTypeAmenagement;
    return this;
  }

  public Chemin addListeTypeAmenagementItem(TypeAmenagement listeTypeAmenagementItem) {
    if (this.listeTypeAmenagement == null) {
      this.listeTypeAmenagement = new ArrayList<>();
    }
    this.listeTypeAmenagement.add(listeTypeAmenagementItem);
    return this;
  }

   /**
   * La liste des types d&#39;aménagements (paramétrage \&quot;Types d&#39;aménagement\&quot; venant du référentiel)  S&#39;il n&#39;y a pas d&#39;aménagement, on retourne une liste vide. 
   * @return listeTypeAmenagement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La liste des types d'aménagements (paramétrage \"Types d'aménagement\" venant du référentiel)  S'il n'y a pas d'aménagement, on retourne une liste vide. ")

  public List<TypeAmenagement> getListeTypeAmenagement() {
    return listeTypeAmenagement;
  }


  public void setListeTypeAmenagement(List<TypeAmenagement> listeTypeAmenagement) {
    this.listeTypeAmenagement = listeTypeAmenagement;
  }


  public Chemin listeControle(List<Controle> listeControle) {
    
    this.listeControle = listeControle;
    return this;
  }

  public Chemin addListeControleItem(Controle listeControleItem) {
    this.listeControle.add(listeControleItem);
    return this;
  }

   /**
   * La liste des contrôles  S&#39;il n&#39;y a pas de contrôle, on retourne une liste vide. 
   * @return listeControle
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La liste des contrôles  S'il n'y a pas de contrôle, on retourne une liste vide. ")

  public List<Controle> getListeControle() {
    return listeControle;
  }


  public void setListeControle(List<Controle> listeControle) {
    this.listeControle = listeControle;
  }


  public Chemin acquisCapitalise(Boolean acquisCapitalise) {
    
    this.acquisCapitalise = acquisCapitalise;
    return this;
  }

   /**
   * Est-ce que l&#39;apprenant possède un acquis capitalisé sur ce chemin ?
   * @return acquisCapitalise
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Est-ce que l'apprenant possède un acquis capitalisé sur ce chemin ?")

  public Boolean getAcquisCapitalise() {
    return acquisCapitalise;
  }


  public void setAcquisCapitalise(Boolean acquisCapitalise) {
    this.acquisCapitalise = acquisCapitalise;
  }


  public Chemin periodeAcquisCapitalise(Periode periodeAcquisCapitalise) {
    
    this.periodeAcquisCapitalise = periodeAcquisCapitalise;
    return this;
  }

   /**
   * Get periodeAcquisCapitalise
   * @return periodeAcquisCapitalise
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Periode getPeriodeAcquisCapitalise() {
    return periodeAcquisCapitalise;
  }


  public void setPeriodeAcquisCapitalise(Periode periodeAcquisCapitalise) {
    this.periodeAcquisCapitalise = periodeAcquisCapitalise;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Chemin chemin = (Chemin) o;
    return Objects.equals(this.codeChemin, chemin.codeChemin) &&
        Objects.equals(this.objetFeuille, chemin.objetFeuille) &&
        Objects.equals(this.bareme, chemin.bareme) &&
        Objects.equals(this.noteSession1, chemin.noteSession1) &&
        Objects.equals(this.resultatSession1, chemin.resultatSession1) &&
        Objects.equals(this.absenceSession1, chemin.absenceSession1) &&
        Objects.equals(this.concerneParSession2, chemin.concerneParSession2) &&
        Objects.equals(this.noteSession2, chemin.noteSession2) &&
        Objects.equals(this.resultatSession2, chemin.resultatSession2) &&
        Objects.equals(this.absenceSession2, chemin.absenceSession2) &&
        Objects.equals(this.coefficientSession1, chemin.coefficientSession1) &&
        Objects.equals(this.coefficientSession2, chemin.coefficientSession2) &&
        Objects.equals(this.coefficientFinal, chemin.coefficientFinal) &&
        Objects.equals(this.noteConsolidee, chemin.noteConsolidee) &&
        Objects.equals(this.pointsJury, chemin.pointsJury) &&
        Objects.equals(this.noteFinale, chemin.noteFinale) &&
        Objects.equals(this.resultatFinal, chemin.resultatFinal) &&
        Objects.equals(this.absenceFinale, chemin.absenceFinale) &&
        Objects.equals(this.numeroSessionRetenueNote, chemin.numeroSessionRetenueNote) &&
        Objects.equals(this.numeroSessionRetenueResultat, chemin.numeroSessionRetenueResultat) &&
        Objects.equals(this.creditEcts, chemin.creditEcts) &&
        Objects.equals(this.mentionHonorifique, chemin.mentionHonorifique) &&
        Objects.equals(this.notationGpa, chemin.notationGpa) &&
        Objects.equals(this.notationEcts, chemin.notationEcts) &&
        Objects.equals(this.delibereSession1, chemin.delibereSession1) &&
        Objects.equals(this.delibereFinales, chemin.delibereFinales) &&
        Objects.equals(this.rangFinal, chemin.rangFinal) &&
        Objects.equals(this.rangTotalFinal, chemin.rangTotalFinal) &&
        Objects.equals(this.publieSession1, chemin.publieSession1) &&
        Objects.equals(this.publieSession2, chemin.publieSession2) &&
        Objects.equals(this.publieEvaluationsFinales, chemin.publieEvaluationsFinales) &&
        Objects.equals(this.listeTypeAmenagement, chemin.listeTypeAmenagement) &&
        Objects.equals(this.listeControle, chemin.listeControle) &&
        Objects.equals(this.acquisCapitalise, chemin.acquisCapitalise) &&
        Objects.equals(this.periodeAcquisCapitalise, chemin.periodeAcquisCapitalise);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeChemin, objetFeuille, bareme, noteSession1, resultatSession1, absenceSession1, concerneParSession2, noteSession2, resultatSession2, absenceSession2, coefficientSession1, coefficientSession2, coefficientFinal, noteConsolidee, pointsJury, noteFinale, resultatFinal, absenceFinale, numeroSessionRetenueNote, numeroSessionRetenueResultat, creditEcts, mentionHonorifique, notationGpa, notationEcts, delibereSession1, delibereFinales, rangFinal, rangTotalFinal, publieSession1, publieSession2, publieEvaluationsFinales, listeTypeAmenagement, listeControle, acquisCapitalise, periodeAcquisCapitalise);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Chemin {\n");
    sb.append("    codeChemin: ").append(toIndentedString(codeChemin)).append("\n");
    sb.append("    objetFeuille: ").append(toIndentedString(objetFeuille)).append("\n");
    sb.append("    bareme: ").append(toIndentedString(bareme)).append("\n");
    sb.append("    noteSession1: ").append(toIndentedString(noteSession1)).append("\n");
    sb.append("    resultatSession1: ").append(toIndentedString(resultatSession1)).append("\n");
    sb.append("    absenceSession1: ").append(toIndentedString(absenceSession1)).append("\n");
    sb.append("    concerneParSession2: ").append(toIndentedString(concerneParSession2)).append("\n");
    sb.append("    noteSession2: ").append(toIndentedString(noteSession2)).append("\n");
    sb.append("    resultatSession2: ").append(toIndentedString(resultatSession2)).append("\n");
    sb.append("    absenceSession2: ").append(toIndentedString(absenceSession2)).append("\n");
    sb.append("    coefficientSession1: ").append(toIndentedString(coefficientSession1)).append("\n");
    sb.append("    coefficientSession2: ").append(toIndentedString(coefficientSession2)).append("\n");
    sb.append("    coefficientFinal: ").append(toIndentedString(coefficientFinal)).append("\n");
    sb.append("    noteConsolidee: ").append(toIndentedString(noteConsolidee)).append("\n");
    sb.append("    pointsJury: ").append(toIndentedString(pointsJury)).append("\n");
    sb.append("    noteFinale: ").append(toIndentedString(noteFinale)).append("\n");
    sb.append("    resultatFinal: ").append(toIndentedString(resultatFinal)).append("\n");
    sb.append("    absenceFinale: ").append(toIndentedString(absenceFinale)).append("\n");
    sb.append("    numeroSessionRetenueNote: ").append(toIndentedString(numeroSessionRetenueNote)).append("\n");
    sb.append("    numeroSessionRetenueResultat: ").append(toIndentedString(numeroSessionRetenueResultat)).append("\n");
    sb.append("    creditEcts: ").append(toIndentedString(creditEcts)).append("\n");
    sb.append("    mentionHonorifique: ").append(toIndentedString(mentionHonorifique)).append("\n");
    sb.append("    notationGpa: ").append(toIndentedString(notationGpa)).append("\n");
    sb.append("    notationEcts: ").append(toIndentedString(notationEcts)).append("\n");
    sb.append("    delibereSession1: ").append(toIndentedString(delibereSession1)).append("\n");
    sb.append("    delibereFinales: ").append(toIndentedString(delibereFinales)).append("\n");
    sb.append("    rangFinal: ").append(toIndentedString(rangFinal)).append("\n");
    sb.append("    rangTotalFinal: ").append(toIndentedString(rangTotalFinal)).append("\n");
    sb.append("    publieSession1: ").append(toIndentedString(publieSession1)).append("\n");
    sb.append("    publieSession2: ").append(toIndentedString(publieSession2)).append("\n");
    sb.append("    publieEvaluationsFinales: ").append(toIndentedString(publieEvaluationsFinales)).append("\n");
    sb.append("    listeTypeAmenagement: ").append(toIndentedString(listeTypeAmenagement)).append("\n");
    sb.append("    listeControle: ").append(toIndentedString(listeControle)).append("\n");
    sb.append("    acquisCapitalise: ").append(toIndentedString(acquisCapitalise)).append("\n");
    sb.append("    periodeAcquisCapitalise: ").append(toIndentedString(periodeAcquisCapitalise)).append("\n");
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

