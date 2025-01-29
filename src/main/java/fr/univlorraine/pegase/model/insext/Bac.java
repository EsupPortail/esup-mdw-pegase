/*
 * INSCRIPTION Externe V1 [EXTERNE]
 * Liste l'ensemble des services et des opérations Ins (Module Inscription) marquées comme [EXTERNE].  Ils peuvent être utilisés par des outils externes à Pegase.  ### Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l'aide d'un [token jwt](https://jwt.io/). Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`.  Lien vers la documentation qui décrit l'authentification aux APIs de Pegase : [pdf](https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1)  ### Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :   * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)     * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés     * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)   * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)   * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)   * boolean - Un booléen représenté par `true` ou `false`  ### Code retour  * 200 - Ok : L'opération s'est déroulée avec succès * 201 - Created : L'opération a aboutie à la création d'une ressource * 400 - Bad request :   * Un ou des paramètres d'entrées sont erronées   * Une erreur fonctionnelle s'est produite * 401 - Unauthorized - Vous n'êtes pas authentifié   * Il n'y a pas de token passé dans le header HTTP `Authorization`   * Le token passé n'est pas au bon format (Bearer <[token-jwt](https://jwt.io/)>) * 403 - Forbidden - Vous êtes authentifié mais pas autorisé à exécuter cette opération   * La signature du token est incorrecte / n'a pas pû être vérifiée   * Le token est expiré   * Les habilitations de l'utilisateur ne permettent pas d'exécuter cette opération. Vérifier les droits de l'utilisateur * 404 - Not Found : La ressource demandée n'est pas trouvé     * Remarque : Dans le cas des opérations retournant une liste, on recevra un code 200 avec en résultat une liste vide * 500 - Internal server error : Erreur inattendue et non gérés 
 *
 * The version of the OpenAPI document: 1.3.1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.insext;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * Bac
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-29T09:55:35.009513400+01:00[Europe/Paris]")
public class Bac {
  public static final String SERIALIZED_NAME_TITRE_ACCES = "titreAcces";
  public static final String SERIALIZED_NAME_ANNEE_OBTENTION = "anneeObtention";
  public static final String SERIALIZED_NAME_SERIE = "serie";
  public static final String SERIALIZED_NAME_LIBELLE_SERIE = "libelleSerie";
  public static final String SERIALIZED_NAME_PREMIERE_SPECIALITE_BAC = "premiereSpecialiteBac";
  public static final String SERIALIZED_NAME_LIBELLE_PREMIERE_SPECIALITE_BAC = "libellePremiereSpecialiteBac";
  public static final String SERIALIZED_NAME_DEUXIEME_SPECIALITE_BAC = "deuxiemeSpecialiteBac";
  public static final String SERIALIZED_NAME_LIBELLE_DEUXIEME_SPECIALITE_BAC = "libelleDeuxiemeSpecialiteBac";
  public static final String SERIALIZED_NAME_MENTION = "mention";
  public static final String SERIALIZED_NAME_LIBELLE_MENTION = "libelleMention";
  public static final String SERIALIZED_NAME_TYPE_ETABLISSEMENT = "typeEtablissement";
  public static final String SERIALIZED_NAME_PAYS = "pays";
  public static final String SERIALIZED_NAME_LIBELLE_PAYS = "libellePays";
  public static final String SERIALIZED_NAME_DEPARTEMENT = "departement";
  public static final String SERIALIZED_NAME_LIBELLE_DEPARTEMENT = "libelleDepartement";
  public static final String SERIALIZED_NAME_ETABLISSEMENT = "etablissement";
  public static final String SERIALIZED_NAME_INE = "ine";
  public static final String SERIALIZED_NAME_STATUT_INE = "statutIne";
  public static final String SERIALIZED_NAME_CODE_ERREUR_INE = "codeErreurIne";
  public static final String SERIALIZED_NAME_MESSAGE_ERREUR_INE = "messageErreurIne";
  public static final String SERIALIZED_NAME_DATE_DERNIER_APPEL_INES = "dateDernierAppelInes";
  public static final String SERIALIZED_NAME_ETABLISSEMENT_LIBRE = "etablissementLibre";
  public static final String SERIALIZED_NAME_DETAIL_TITRE = "detailTitre";
  @SerializedName(SERIALIZED_NAME_TITRE_ACCES)
  private String titreAcces;
  @SerializedName(SERIALIZED_NAME_ANNEE_OBTENTION)
  private String anneeObtention;
  @SerializedName(SERIALIZED_NAME_SERIE)
  private String serie;
  @SerializedName(SERIALIZED_NAME_LIBELLE_SERIE)
  private String libelleSerie;
  @SerializedName(SERIALIZED_NAME_PREMIERE_SPECIALITE_BAC)
  private String premiereSpecialiteBac;
  @SerializedName(SERIALIZED_NAME_LIBELLE_PREMIERE_SPECIALITE_BAC)
  private String libellePremiereSpecialiteBac;
  @SerializedName(SERIALIZED_NAME_DEUXIEME_SPECIALITE_BAC)
  private String deuxiemeSpecialiteBac;
  @SerializedName(SERIALIZED_NAME_LIBELLE_DEUXIEME_SPECIALITE_BAC)
  private String libelleDeuxiemeSpecialiteBac;
  @SerializedName(SERIALIZED_NAME_MENTION)
  private String mention;
  @SerializedName(SERIALIZED_NAME_LIBELLE_MENTION)
  private String libelleMention;
  @SerializedName(SERIALIZED_NAME_TYPE_ETABLISSEMENT)
  private String typeEtablissement;
  @SerializedName(SERIALIZED_NAME_PAYS)
  private String pays;
  @SerializedName(SERIALIZED_NAME_LIBELLE_PAYS)
  private String libellePays;
  @SerializedName(SERIALIZED_NAME_DEPARTEMENT)
  private String departement;
  @SerializedName(SERIALIZED_NAME_LIBELLE_DEPARTEMENT)
  private String libelleDepartement;
  @SerializedName(SERIALIZED_NAME_ETABLISSEMENT)
  private String etablissement;
  @SerializedName(SERIALIZED_NAME_INE)
  private String ine;
  @SerializedName(SERIALIZED_NAME_STATUT_INE)
  private StatutIne statutIne;
  @SerializedName(SERIALIZED_NAME_CODE_ERREUR_INE)
  private String codeErreurIne;
  @SerializedName(SERIALIZED_NAME_MESSAGE_ERREUR_INE)
  private String messageErreurIne;
  @SerializedName(SERIALIZED_NAME_DATE_DERNIER_APPEL_INES)
  private Date dateDernierAppelInes;
  @SerializedName(SERIALIZED_NAME_ETABLISSEMENT_LIBRE)
  private String etablissementLibre;
  @SerializedName(SERIALIZED_NAME_DETAIL_TITRE)
  private String detailTitre;

  public Bac() { 
  }

  public Bac titreAcces(String titreAcces) {
    
    this.titreAcces = titreAcces;
    return this;
  }

   /**
   * Le titre d&#39;accès du baccalaureat ou équivalent
   * @return titreAcces
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le titre d'accès du baccalaureat ou équivalent")

  public String getTitreAcces() {
    return titreAcces;
  }


  public void setTitreAcces(String titreAcces) {
    this.titreAcces = titreAcces;
  }


  public Bac anneeObtention(String anneeObtention) {
    
    this.anneeObtention = anneeObtention;
    return this;
  }

   /**
   * L&#39;annee d&#39;obtention du baccalaureat ou équivalent
   * @return anneeObtention
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "L'annee d'obtention du baccalaureat ou équivalent")

  public String getAnneeObtention() {
    return anneeObtention;
  }


  public void setAnneeObtention(String anneeObtention) {
    this.anneeObtention = anneeObtention;
  }


  public Bac serie(String serie) {
    
    this.serie = serie;
    return this;
  }

   /**
   * Le code de la série du baccalauréat ou équivalent issu de la nomenclature Séries du baccalauréat et équivalences  Codes de la nomenclature sur 6 caractères. Préfixe des codes : BAC 
   * @return serie
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la série du baccalauréat ou équivalent issu de la nomenclature Séries du baccalauréat et équivalences  Codes de la nomenclature sur 6 caractères. Préfixe des codes : BAC ")

  public String getSerie() {
    return serie;
  }


  public void setSerie(String serie) {
    this.serie = serie;
  }


  public Bac libelleSerie(String libelleSerie) {
    
    this.libelleSerie = libelleSerie;
    return this;
  }

   /**
   * Le libelle de la série du baccalauréat ou équivalent issu de la nomenclature Séries du baccalauréat et équivalences 
   * @return libelleSerie
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libelle de la série du baccalauréat ou équivalent issu de la nomenclature Séries du baccalauréat et équivalences ")

  public String getLibelleSerie() {
    return libelleSerie;
  }


  public void setLibelleSerie(String libelleSerie) {
    this.libelleSerie = libelleSerie;
  }


  public Bac premiereSpecialiteBac(String premiereSpecialiteBac) {
    
    this.premiereSpecialiteBac = premiereSpecialiteBac;
    return this;
  }

   /**
   * Le code de la première spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général 
   * @return premiereSpecialiteBac
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la première spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général ")

  public String getPremiereSpecialiteBac() {
    return premiereSpecialiteBac;
  }


  public void setPremiereSpecialiteBac(String premiereSpecialiteBac) {
    this.premiereSpecialiteBac = premiereSpecialiteBac;
  }


  public Bac libellePremiereSpecialiteBac(String libellePremiereSpecialiteBac) {
    
    this.libellePremiereSpecialiteBac = libellePremiereSpecialiteBac;
    return this;
  }

   /**
   * Le libelle de la première spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général 
   * @return libellePremiereSpecialiteBac
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libelle de la première spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général ")

  public String getLibellePremiereSpecialiteBac() {
    return libellePremiereSpecialiteBac;
  }


  public void setLibellePremiereSpecialiteBac(String libellePremiereSpecialiteBac) {
    this.libellePremiereSpecialiteBac = libellePremiereSpecialiteBac;
  }


  public Bac deuxiemeSpecialiteBac(String deuxiemeSpecialiteBac) {
    
    this.deuxiemeSpecialiteBac = deuxiemeSpecialiteBac;
    return this;
  }

   /**
   * Le code de la deuxième spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général 
   * @return deuxiemeSpecialiteBac
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la deuxième spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général ")

  public String getDeuxiemeSpecialiteBac() {
    return deuxiemeSpecialiteBac;
  }


  public void setDeuxiemeSpecialiteBac(String deuxiemeSpecialiteBac) {
    this.deuxiemeSpecialiteBac = deuxiemeSpecialiteBac;
  }


  public Bac libelleDeuxiemeSpecialiteBac(String libelleDeuxiemeSpecialiteBac) {
    
    this.libelleDeuxiemeSpecialiteBac = libelleDeuxiemeSpecialiteBac;
    return this;
  }

   /**
   * Le libelle de la deuxième spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général 
   * @return libelleDeuxiemeSpecialiteBac
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libelle de la deuxième spécialité du nouveau baccalaureat (NBGE) issu de la nomenclature Specialités Bac Général ")

  public String getLibelleDeuxiemeSpecialiteBac() {
    return libelleDeuxiemeSpecialiteBac;
  }


  public void setLibelleDeuxiemeSpecialiteBac(String libelleDeuxiemeSpecialiteBac) {
    this.libelleDeuxiemeSpecialiteBac = libelleDeuxiemeSpecialiteBac;
  }


  public Bac mention(String mention) {
    
    this.mention = mention;
    return this;
  }

   /**
   * Le code de la mention du candidat issu de la nomenclature des Mentions obtenues au baccalauréat  Codes de la nomenclature sur 6 caractères. Préfixe des codes : MEN 
   * @return mention
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code de la mention du candidat issu de la nomenclature des Mentions obtenues au baccalauréat  Codes de la nomenclature sur 6 caractères. Préfixe des codes : MEN ")

  public String getMention() {
    return mention;
  }


  public void setMention(String mention) {
    this.mention = mention;
  }


  public Bac libelleMention(String libelleMention) {
    
    this.libelleMention = libelleMention;
    return this;
  }

   /**
   * Le libelle de la mention du candidat issu de la nomenclature des Mentions obtenues au baccalauréat 
   * @return libelleMention
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libelle de la mention du candidat issu de la nomenclature des Mentions obtenues au baccalauréat ")

  public String getLibelleMention() {
    return libelleMention;
  }


  public void setLibelleMention(String libelleMention) {
    this.libelleMention = libelleMention;
  }


  public Bac typeEtablissement(String typeEtablissement) {
    
    this.typeEtablissement = typeEtablissement;
    return this;
  }

   /**
   * Le type de l&#39;établissement dans lequel le diplôme a été obtenu  Les valeurs &#x60;F&#x60; ou &#x60;E&#x60; sont acceptées: * la valeur &#x60;F&#x60; correspond au type établissement d&#39;un établissement   français en France, * la valeur &#x60;E&#x60; correspond au type établissement d&#39;un établissement français   à l&#39;étranger ou d&#39;un établissement étranger. 
   * @return typeEtablissement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le type de l'établissement dans lequel le diplôme a été obtenu  Les valeurs `F` ou `E` sont acceptées: * la valeur `F` correspond au type établissement d'un établissement   français en France, * la valeur `E` correspond au type établissement d'un établissement français   à l'étranger ou d'un établissement étranger. ")

  public String getTypeEtablissement() {
    return typeEtablissement;
  }


  public void setTypeEtablissement(String typeEtablissement) {
    this.typeEtablissement = typeEtablissement;
  }


  public Bac pays(String pays) {
    
    this.pays = pays;
    return this;
  }

   /**
   * Le code du pays délivrant le diplôme issu de la nomenclature Pays et nationalités. 
   * @return pays
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du pays délivrant le diplôme issu de la nomenclature Pays et nationalités. ")

  public String getPays() {
    return pays;
  }


  public void setPays(String pays) {
    this.pays = pays;
  }


  public Bac libellePays(String libellePays) {
    
    this.libellePays = libellePays;
    return this;
  }

   /**
   * Le libelle du pays délivrant le diplôme issu de la nomenclature Pays et nationalités. 
   * @return libellePays
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libelle du pays délivrant le diplôme issu de la nomenclature Pays et nationalités. ")

  public String getLibellePays() {
    return libellePays;
  }


  public void setLibellePays(String libellePays) {
    this.libellePays = libellePays;
  }


  public Bac departement(String departement) {
    
    this.departement = departement;
    return this;
  }

   /**
   * Le code du département de l&#39;établissement dans lequel le diplôme a été obtenu issu de la nomenclature Départements.  Codes de la nomenclature sur 3 caractères. Ex : 001 &#x3D; AIN 
   * @return departement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le code du département de l'établissement dans lequel le diplôme a été obtenu issu de la nomenclature Départements.  Codes de la nomenclature sur 3 caractères. Ex : 001 = AIN ")

  public String getDepartement() {
    return departement;
  }


  public void setDepartement(String departement) {
    this.departement = departement;
  }


  public Bac libelleDepartement(String libelleDepartement) {
    
    this.libelleDepartement = libelleDepartement;
    return this;
  }

   /**
   * Le libelle du département de l&#39;établissement dans lequel le diplôme a été obtenu issu de la nomenclature Départements. 
   * @return libelleDepartement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libelle du département de l'établissement dans lequel le diplôme a été obtenu issu de la nomenclature Départements. ")

  public String getLibelleDepartement() {
    return libelleDepartement;
  }


  public void setLibelleDepartement(String libelleDepartement) {
    this.libelleDepartement = libelleDepartement;
  }


  public Bac etablissement(String etablissement) {
    
    this.etablissement = etablissement;
    return this;
  }

   /**
   * Le numéro UAI de l&#39;établissement dans lequel le diplôme a été obtenu issu de la nomenclature Etablissements français.  Codes de la nomenclature &#x3D; code UAI 
   * @return etablissement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le numéro UAI de l'établissement dans lequel le diplôme a été obtenu issu de la nomenclature Etablissements français.  Codes de la nomenclature = code UAI ")

  public String getEtablissement() {
    return etablissement;
  }


  public void setEtablissement(String etablissement) {
    this.etablissement = etablissement;
  }


  public Bac ine(String ine) {
    
    this.ine = ine;
    return this;
  }

   /**
   * le code INE de l&#39;étudiant
   * @return ine
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le code INE de l'étudiant")

  public String getIne() {
    return ine;
  }


  public void setIne(String ine) {
    this.ine = ine;
  }


  public Bac statutIne(StatutIne statutIne) {
    
    this.statutIne = statutIne;
    return this;
  }

   /**
   * Get statutIne
   * @return statutIne
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StatutIne getStatutIne() {
    return statutIne;
  }


  public void setStatutIne(StatutIne statutIne) {
    this.statutIne = statutIne;
  }


  public Bac codeErreurIne(String codeErreurIne) {
    
    this.codeErreurIne = codeErreurIne;
    return this;
  }

   /**
   * Valorisé si il y a eu une erreur lors de la vérification du numéro INE, et si un code erreur est disponible 
   * @return codeErreurIne
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Valorisé si il y a eu une erreur lors de la vérification du numéro INE, et si un code erreur est disponible ")

  public String getCodeErreurIne() {
    return codeErreurIne;
  }


  public void setCodeErreurIne(String codeErreurIne) {
    this.codeErreurIne = codeErreurIne;
  }


  public Bac messageErreurIne(String messageErreurIne) {
    
    this.messageErreurIne = messageErreurIne;
    return this;
  }

   /**
   * Valorisé si il y a eu une erreur lors de la vérification du numéro INE 
   * @return messageErreurIne
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Valorisé si il y a eu une erreur lors de la vérification du numéro INE ")

  public String getMessageErreurIne() {
    return messageErreurIne;
  }


  public void setMessageErreurIne(String messageErreurIne) {
    this.messageErreurIne = messageErreurIne;
  }


  public Bac dateDernierAppelInes(Date dateDernierAppelInes) {
    
    this.dateDernierAppelInes = dateDernierAppelInes;
    return this;
  }

   /**
   * Date de la dernière vérification INES 
   * @return dateDernierAppelInes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Date de la dernière vérification INES ")

  public Date getDateDernierAppelInes() {
    return dateDernierAppelInes;
  }


  public void setDateDernierAppelInes(Date dateDernierAppelInes) {
    this.dateDernierAppelInes = dateDernierAppelInes;
  }


  public Bac etablissementLibre(String etablissementLibre) {
    
    this.etablissementLibre = etablissementLibre;
    return this;
  }

   /**
   * Etablissement en saisie libre dans le cas - d&#39;un Titre admis en dispense, - ou par équivalence du baccalauréat, - ou d&#39;un Titre étranger, - ou baccalauréat international. 
   * @return etablissementLibre
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Etablissement en saisie libre dans le cas - d'un Titre admis en dispense, - ou par équivalence du baccalauréat, - ou d'un Titre étranger, - ou baccalauréat international. ")

  public String getEtablissementLibre() {
    return etablissementLibre;
  }


  public void setEtablissementLibre(String etablissementLibre) {
    this.etablissementLibre = etablissementLibre;
  }


  public Bac detailTitre(String detailTitre) {
    
    this.detailTitre = detailTitre;
    return this;
  }

   /**
   * Précision du titre admis en dispense du baccalauréat (texte libre).  Valeur demandée lorsque l’on choisit le type ou série « 0032/Titre français admis en dispense » pour un titre d’accès à l’enseignement supérieur « TITRE admis en dispense ou par équivalence du baccalauréat ». 
   * @return detailTitre
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Précision du titre admis en dispense du baccalauréat (texte libre).  Valeur demandée lorsque l’on choisit le type ou série « 0032/Titre français admis en dispense » pour un titre d’accès à l’enseignement supérieur « TITRE admis en dispense ou par équivalence du baccalauréat ». ")

  public String getDetailTitre() {
    return detailTitre;
  }


  public void setDetailTitre(String detailTitre) {
    this.detailTitre = detailTitre;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bac bac = (Bac) o;
    return Objects.equals(this.titreAcces, bac.titreAcces) &&
        Objects.equals(this.anneeObtention, bac.anneeObtention) &&
        Objects.equals(this.serie, bac.serie) &&
        Objects.equals(this.libelleSerie, bac.libelleSerie) &&
        Objects.equals(this.premiereSpecialiteBac, bac.premiereSpecialiteBac) &&
        Objects.equals(this.libellePremiereSpecialiteBac, bac.libellePremiereSpecialiteBac) &&
        Objects.equals(this.deuxiemeSpecialiteBac, bac.deuxiemeSpecialiteBac) &&
        Objects.equals(this.libelleDeuxiemeSpecialiteBac, bac.libelleDeuxiemeSpecialiteBac) &&
        Objects.equals(this.mention, bac.mention) &&
        Objects.equals(this.libelleMention, bac.libelleMention) &&
        Objects.equals(this.typeEtablissement, bac.typeEtablissement) &&
        Objects.equals(this.pays, bac.pays) &&
        Objects.equals(this.libellePays, bac.libellePays) &&
        Objects.equals(this.departement, bac.departement) &&
        Objects.equals(this.libelleDepartement, bac.libelleDepartement) &&
        Objects.equals(this.etablissement, bac.etablissement) &&
        Objects.equals(this.ine, bac.ine) &&
        Objects.equals(this.statutIne, bac.statutIne) &&
        Objects.equals(this.codeErreurIne, bac.codeErreurIne) &&
        Objects.equals(this.messageErreurIne, bac.messageErreurIne) &&
        Objects.equals(this.dateDernierAppelInes, bac.dateDernierAppelInes) &&
        Objects.equals(this.etablissementLibre, bac.etablissementLibre) &&
        Objects.equals(this.detailTitre, bac.detailTitre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titreAcces, anneeObtention, serie, libelleSerie, premiereSpecialiteBac, libellePremiereSpecialiteBac, deuxiemeSpecialiteBac, libelleDeuxiemeSpecialiteBac, mention, libelleMention, typeEtablissement, pays, libellePays, departement, libelleDepartement, etablissement, ine, statutIne, codeErreurIne, messageErreurIne, dateDernierAppelInes, etablissementLibre, detailTitre);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Bac {\n");
    sb.append("    titreAcces: ").append(toIndentedString(titreAcces)).append("\n");
    sb.append("    anneeObtention: ").append(toIndentedString(anneeObtention)).append("\n");
    sb.append("    serie: ").append(toIndentedString(serie)).append("\n");
    sb.append("    libelleSerie: ").append(toIndentedString(libelleSerie)).append("\n");
    sb.append("    premiereSpecialiteBac: ").append(toIndentedString(premiereSpecialiteBac)).append("\n");
    sb.append("    libellePremiereSpecialiteBac: ").append(toIndentedString(libellePremiereSpecialiteBac)).append("\n");
    sb.append("    deuxiemeSpecialiteBac: ").append(toIndentedString(deuxiemeSpecialiteBac)).append("\n");
    sb.append("    libelleDeuxiemeSpecialiteBac: ").append(toIndentedString(libelleDeuxiemeSpecialiteBac)).append("\n");
    sb.append("    mention: ").append(toIndentedString(mention)).append("\n");
    sb.append("    libelleMention: ").append(toIndentedString(libelleMention)).append("\n");
    sb.append("    typeEtablissement: ").append(toIndentedString(typeEtablissement)).append("\n");
    sb.append("    pays: ").append(toIndentedString(pays)).append("\n");
    sb.append("    libellePays: ").append(toIndentedString(libellePays)).append("\n");
    sb.append("    departement: ").append(toIndentedString(departement)).append("\n");
    sb.append("    libelleDepartement: ").append(toIndentedString(libelleDepartement)).append("\n");
    sb.append("    etablissement: ").append(toIndentedString(etablissement)).append("\n");
    sb.append("    ine: ").append(toIndentedString(ine)).append("\n");
    sb.append("    statutIne: ").append(toIndentedString(statutIne)).append("\n");
    sb.append("    codeErreurIne: ").append(toIndentedString(codeErreurIne)).append("\n");
    sb.append("    messageErreurIne: ").append(toIndentedString(messageErreurIne)).append("\n");
    sb.append("    dateDernierAppelInes: ").append(toIndentedString(dateDernierAppelInes)).append("\n");
    sb.append("    etablissementLibre: ").append(toIndentedString(etablissementLibre)).append("\n");
    sb.append("    detailTitre: ").append(toIndentedString(detailTitre)).append("\n");
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

