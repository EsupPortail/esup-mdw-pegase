/*
 * INSCRIPTION Externe V1 [EXTERNE]
 * Liste l'ensemble des services et des opérations Ins (Module Inscription) marquées comme [EXTERNE].  Ils peuvent être utilisés par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l'aide d'un [token jwt](https://jwt.io/). Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`.  Lien vers la documentation qui décrit l'authentification aux APIs de Pegase : [pdf](https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1)  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :   * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)     * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés     * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)   * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)   * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)   * boolean - Un booléen représenté par `true` ou `false`  ### Code retour  * 200 - Ok : L'opération s'est déroulée avec succès * 201 - Created : L'opération a aboutie à la création d'une ressource * 400 - Bad request :   * Un ou des paramètres d'entrées sont erronées   * Une erreur fonctionnelle s'est produite * 401 - Unauthorized - Vous n'êtes pas authentifié   * Il n'y a pas de token passé dans le header HTTP `Authorization`   * Le token passé n'est pas au bon format (Bearer <[token-jwt](https://jwt.io/)>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération   * La signature du token est incorrecte / n'a pas pû être vérifiée   * Le token est expiré   * Les habilitations de l'utilisateur ne permettent pas d'exécuter cette opération. Vérifier les droits de l'utilisateur * 404 - Not Found : La ressource demandée n'est pas trouvé     * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 1.2.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insext;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.model.insext.VoieAdmission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Admission
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T10:08:44.291562300+01:00[Europe/Paris]")
public class Admission {
  public static final String SERIALIZED_NAME_VOIE = "voie";
  @SerializedName(SERIALIZED_NAME_VOIE)
  private VoieAdmission voie;

  public static final String SERIALIZED_NAME_ANNEE_CONCOURS = "anneeConcours";
  @SerializedName(SERIALIZED_NAME_ANNEE_CONCOURS)
  private BigDecimal anneeConcours;

  public static final String SERIALIZED_NAME_CONCOURS = "concours";
  @SerializedName(SERIALIZED_NAME_CONCOURS)
  private String concours;

  public static final String SERIALIZED_NAME_RANG_CONCOURS = "rangConcours";
  @SerializedName(SERIALIZED_NAME_RANG_CONCOURS)
  private BigDecimal rangConcours;

  public static final String SERIALIZED_NAME_ANNEE_PRECEDENTE = "anneePrecedente";
  @SerializedName(SERIALIZED_NAME_ANNEE_PRECEDENTE)
  private BigDecimal anneePrecedente;

  public static final String SERIALIZED_NAME_TEMOIN_CLASSE_PREPA = "temoinClassePrepa";
  @SerializedName(SERIALIZED_NAME_TEMOIN_CLASSE_PREPA)
  private Boolean temoinClassePrepa;

  public static final String SERIALIZED_NAME_TYPE_PREPA = "typePrepa";
  @SerializedName(SERIALIZED_NAME_TYPE_PREPA)
  private String typePrepa;

  public static final String SERIALIZED_NAME_CONTEXTE_CONSOMMATION_TYPE_PREPA = "contexteConsommationTypePrepa";
  @SerializedName(SERIALIZED_NAME_CONTEXTE_CONSOMMATION_TYPE_PREPA)
  private Date contexteConsommationTypePrepa;

  public static final String SERIALIZED_NAME_PUISSANCE_PREPA = "puissancePrepa";
  @SerializedName(SERIALIZED_NAME_PUISSANCE_PREPA)
  private String puissancePrepa;

  public static final String SERIALIZED_NAME_TYPE_ETABLISSEMENT_PRECEDENT = "typeEtablissementPrecedent";
  @SerializedName(SERIALIZED_NAME_TYPE_ETABLISSEMENT_PRECEDENT)
  private String typeEtablissementPrecedent;

  public static final String SERIALIZED_NAME_DEPARTEMENT_ETABLISSEMENT_PRECEDENT = "departementEtablissementPrecedent";
  @SerializedName(SERIALIZED_NAME_DEPARTEMENT_ETABLISSEMENT_PRECEDENT)
  private String departementEtablissementPrecedent;

  public static final String SERIALIZED_NAME_PAYS_ETABLISSEMENT_PRECEDENT = "paysEtablissementPrecedent";
  @SerializedName(SERIALIZED_NAME_PAYS_ETABLISSEMENT_PRECEDENT)
  private String paysEtablissementPrecedent;

  public static final String SERIALIZED_NAME_ETABLISSEMENT_PRECEDENT = "etablissementPrecedent";
  @SerializedName(SERIALIZED_NAME_ETABLISSEMENT_PRECEDENT)
  private String etablissementPrecedent;

  public static final String SERIALIZED_NAME_ETABLISSEMENT_PRECEDENT_ETRANGER = "etablissementPrecedentEtranger";
  @SerializedName(SERIALIZED_NAME_ETABLISSEMENT_PRECEDENT_ETRANGER)
  private String etablissementPrecedentEtranger;

  public Admission() { 
  }

  public Admission voie(VoieAdmission voie) {
    
    this.voie = voie;
    return this;
  }

   /**
   * Get voie
   * @return voie
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public VoieAdmission getVoie() {
    return voie;
  }


  public void setVoie(VoieAdmission voie) {
    this.voie = voie;
  }


  public Admission anneeConcours(BigDecimal anneeConcours) {
    
    this.anneeConcours = anneeConcours;
    return this;
  }

   /**
   * L&#39;année de passage du concours d&#39;admission au format AAAA  Obligatoire si VoieAdmission &#x3D; concours 
   * @return anneeConcours
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'année de passage du concours d'admission au format AAAA  Obligatoire si VoieAdmission = concours ")

  public BigDecimal getAnneeConcours() {
    return anneeConcours;
  }


  public void setAnneeConcours(BigDecimal anneeConcours) {
    this.anneeConcours = anneeConcours;
  }


  public Admission concours(String concours) {
    
    this.concours = concours;
    return this;
  }

   /**
   * Le code du concours d&#39;admission issu de la nomenclature Concours d&#39;admission.  Codes de la nomenclature sur 6 caractères. Préfixe des codes : CON Obligatoire si le champ anneeConcours est complété 
   * @return concours
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du concours d'admission issu de la nomenclature Concours d'admission.  Codes de la nomenclature sur 6 caractères. Préfixe des codes : CON Obligatoire si le champ anneeConcours est complété ")

  public String getConcours() {
    return concours;
  }


  public void setConcours(String concours) {
    this.concours = concours;
  }


  public Admission rangConcours(BigDecimal rangConcours) {
    
    this.rangConcours = rangConcours;
    return this;
  }

   /**
   * Le rang obtenu au concours d&#39;admission
   * @return rangConcours
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le rang obtenu au concours d'admission")

  public BigDecimal getRangConcours() {
    return rangConcours;
  }


  public void setRangConcours(BigDecimal rangConcours) {
    this.rangConcours = rangConcours;
  }


  public Admission anneePrecedente(BigDecimal anneePrecedente) {
    
    this.anneePrecedente = anneePrecedente;
    return this;
  }

   /**
   * L&#39;année universitaire correspondant au cursus CPGE au format AAAA
   * @return anneePrecedente
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'année universitaire correspondant au cursus CPGE au format AAAA")

  public BigDecimal getAnneePrecedente() {
    return anneePrecedente;
  }


  public void setAnneePrecedente(BigDecimal anneePrecedente) {
    this.anneePrecedente = anneePrecedente;
  }


  public Admission temoinClassePrepa(Boolean temoinClassePrepa) {
    
    this.temoinClassePrepa = temoinClassePrepa;
    return this;
  }

   /**
   * Le témoin indiquant que l&#39;étudiant a suivi un cursus en CPGE (classe préparatoire) l&#39;année dernière.  Obligatoire si le champ anneeConcours est complété. 
   * @return temoinClassePrepa
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le témoin indiquant que l'étudiant a suivi un cursus en CPGE (classe préparatoire) l'année dernière.  Obligatoire si le champ anneeConcours est complété. ")

  public Boolean getTemoinClassePrepa() {
    return temoinClassePrepa;
  }


  public void setTemoinClassePrepa(Boolean temoinClassePrepa) {
    this.temoinClassePrepa = temoinClassePrepa;
  }


  public Admission typePrepa(String typePrepa) {
    
    this.typePrepa = typePrepa;
    return this;
  }

   /**
   * Le code du type de classe préparatoire fréquenté issu de la nomenclature Types de classe préparatoire Codes de la nomenclature sur 6 caractères. Préfixe des codes : TCP  Obligatoire si temoinClassePrepa &#x3D; true 
   * @return typePrepa
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du type de classe préparatoire fréquenté issu de la nomenclature Types de classe préparatoire Codes de la nomenclature sur 6 caractères. Préfixe des codes : TCP  Obligatoire si temoinClassePrepa = true ")

  public String getTypePrepa() {
    return typePrepa;
  }


  public void setTypePrepa(String typePrepa) {
    this.typePrepa = typePrepa;
  }


  public Admission contexteConsommationTypePrepa(Date contexteConsommationTypePrepa) {
    
    this.contexteConsommationTypePrepa = contexteConsommationTypePrepa;
    return this;
  }

   /**
   * date contexte admission type classe preparatoire
   * @return contexteConsommationTypePrepa
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "date contexte admission type classe preparatoire")

  public Date getContexteConsommationTypePrepa() {
    return contexteConsommationTypePrepa;
  }


  public void setContexteConsommationTypePrepa(Date contexteConsommationTypePrepa) {
    this.contexteConsommationTypePrepa = contexteConsommationTypePrepa;
  }


  public Admission puissancePrepa(String puissancePrepa) {
    
    this.puissancePrepa = puissancePrepa;
    return this;
  }

   /**
   * La puissance de la classe préparatoire Les valeurs P1, P2, P22 et P32 sont acceptées 
   * @return puissancePrepa
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La puissance de la classe préparatoire Les valeurs P1, P2, P22 et P32 sont acceptées ")

  public String getPuissancePrepa() {
    return puissancePrepa;
  }


  public void setPuissancePrepa(String puissancePrepa) {
    this.puissancePrepa = puissancePrepa;
  }


  public Admission typeEtablissementPrecedent(String typeEtablissementPrecedent) {
    
    this.typeEtablissementPrecedent = typeEtablissementPrecedent;
    return this;
  }

   /**
   * - F : français en France, - E : français à l&#39;étranger, - R : étranger Obligatoire si temoinClassePrepa &#x3D; true 
   * @return typeEtablissementPrecedent
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "- F : français en France, - E : français à l'étranger, - R : étranger Obligatoire si temoinClassePrepa = true ")

  public String getTypeEtablissementPrecedent() {
    return typeEtablissementPrecedent;
  }


  public void setTypeEtablissementPrecedent(String typeEtablissementPrecedent) {
    this.typeEtablissementPrecedent = typeEtablissementPrecedent;
  }


  public Admission departementEtablissementPrecedent(String departementEtablissementPrecedent) {
    
    this.departementEtablissementPrecedent = departementEtablissementPrecedent;
    return this;
  }

   /**
   * Le code du département de l&#39;établissement de la CPGE si &#39;français en France&#39; issu de la nomenclature Départements Obligatoire si typeEtablissementPrecedent &#x3D; F 
   * @return departementEtablissementPrecedent
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du département de l'établissement de la CPGE si 'français en France' issu de la nomenclature Départements Obligatoire si typeEtablissementPrecedent = F ")

  public String getDepartementEtablissementPrecedent() {
    return departementEtablissementPrecedent;
  }


  public void setDepartementEtablissementPrecedent(String departementEtablissementPrecedent) {
    this.departementEtablissementPrecedent = departementEtablissementPrecedent;
  }


  public Admission paysEtablissementPrecedent(String paysEtablissementPrecedent) {
    
    this.paysEtablissementPrecedent = paysEtablissementPrecedent;
    return this;
  }

   /**
   * Le code du pays de l&#39;établissement de la CPGE si &#39;français à l&#39;étranger&#39; ou &#39;étranger&#39; issu de la nomenclature Pays et Nationalités  Obligatoire si typeEtablissementPrecedent &#x3D; E ou R 
   * @return paysEtablissementPrecedent
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du pays de l'établissement de la CPGE si 'français à l'étranger' ou 'étranger' issu de la nomenclature Pays et Nationalités  Obligatoire si typeEtablissementPrecedent = E ou R ")

  public String getPaysEtablissementPrecedent() {
    return paysEtablissementPrecedent;
  }


  public void setPaysEtablissementPrecedent(String paysEtablissementPrecedent) {
    this.paysEtablissementPrecedent = paysEtablissementPrecedent;
  }


  public Admission etablissementPrecedent(String etablissementPrecedent) {
    
    this.etablissementPrecedent = etablissementPrecedent;
    return this;
  }

   /**
   * Le code de l&#39;établissement français de la CPGE si &#39;français en France&#39; ou &#39;français à l&#39;étranger&#39; issu de la nomenclature Etablissements français  Obligatoire 
   * @return etablissementPrecedent
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'établissement français de la CPGE si 'français en France' ou 'français à l'étranger' issu de la nomenclature Etablissements français  Obligatoire ")

  public String getEtablissementPrecedent() {
    return etablissementPrecedent;
  }


  public void setEtablissementPrecedent(String etablissementPrecedent) {
    this.etablissementPrecedent = etablissementPrecedent;
  }


  public Admission etablissementPrecedentEtranger(String etablissementPrecedentEtranger) {
    
    this.etablissementPrecedentEtranger = etablissementPrecedentEtranger;
    return this;
  }

   /**
   * Le code de l&#39;établissement étranger de la CPGE si &#39;étranger&#39; issu de la nomenclature Etablissements étrangers  Obligatoire 
   * @return etablissementPrecedentEtranger
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de l'établissement étranger de la CPGE si 'étranger' issu de la nomenclature Etablissements étrangers  Obligatoire ")

  public String getEtablissementPrecedentEtranger() {
    return etablissementPrecedentEtranger;
  }


  public void setEtablissementPrecedentEtranger(String etablissementPrecedentEtranger) {
    this.etablissementPrecedentEtranger = etablissementPrecedentEtranger;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Admission admission = (Admission) o;
    return Objects.equals(this.voie, admission.voie) &&
        Objects.equals(this.anneeConcours, admission.anneeConcours) &&
        Objects.equals(this.concours, admission.concours) &&
        Objects.equals(this.rangConcours, admission.rangConcours) &&
        Objects.equals(this.anneePrecedente, admission.anneePrecedente) &&
        Objects.equals(this.temoinClassePrepa, admission.temoinClassePrepa) &&
        Objects.equals(this.typePrepa, admission.typePrepa) &&
        Objects.equals(this.contexteConsommationTypePrepa, admission.contexteConsommationTypePrepa) &&
        Objects.equals(this.puissancePrepa, admission.puissancePrepa) &&
        Objects.equals(this.typeEtablissementPrecedent, admission.typeEtablissementPrecedent) &&
        Objects.equals(this.departementEtablissementPrecedent, admission.departementEtablissementPrecedent) &&
        Objects.equals(this.paysEtablissementPrecedent, admission.paysEtablissementPrecedent) &&
        Objects.equals(this.etablissementPrecedent, admission.etablissementPrecedent) &&
        Objects.equals(this.etablissementPrecedentEtranger, admission.etablissementPrecedentEtranger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(voie, anneeConcours, concours, rangConcours, anneePrecedente, temoinClassePrepa, typePrepa, contexteConsommationTypePrepa, puissancePrepa, typeEtablissementPrecedent, departementEtablissementPrecedent, paysEtablissementPrecedent, etablissementPrecedent, etablissementPrecedentEtranger);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Admission {\n");
    sb.append("    voie: ").append(toIndentedString(voie)).append("\n");
    sb.append("    anneeConcours: ").append(toIndentedString(anneeConcours)).append("\n");
    sb.append("    concours: ").append(toIndentedString(concours)).append("\n");
    sb.append("    rangConcours: ").append(toIndentedString(rangConcours)).append("\n");
    sb.append("    anneePrecedente: ").append(toIndentedString(anneePrecedente)).append("\n");
    sb.append("    temoinClassePrepa: ").append(toIndentedString(temoinClassePrepa)).append("\n");
    sb.append("    typePrepa: ").append(toIndentedString(typePrepa)).append("\n");
    sb.append("    contexteConsommationTypePrepa: ").append(toIndentedString(contexteConsommationTypePrepa)).append("\n");
    sb.append("    puissancePrepa: ").append(toIndentedString(puissancePrepa)).append("\n");
    sb.append("    typeEtablissementPrecedent: ").append(toIndentedString(typeEtablissementPrecedent)).append("\n");
    sb.append("    departementEtablissementPrecedent: ").append(toIndentedString(departementEtablissementPrecedent)).append("\n");
    sb.append("    paysEtablissementPrecedent: ").append(toIndentedString(paysEtablissementPrecedent)).append("\n");
    sb.append("    etablissementPrecedent: ").append(toIndentedString(etablissementPrecedent)).append("\n");
    sb.append("    etablissementPrecedentEtranger: ").append(toIndentedString(etablissementPrecedentEtranger)).append("\n");
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
