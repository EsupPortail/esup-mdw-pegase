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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Les données de l&#39;apprenant
 */
@ApiModel(description = "Les données de l'apprenant")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-29T09:55:35.009513400+01:00[Europe/Paris]")
public class Apprenant {
  public static final String SERIALIZED_NAME_CODE = "code";
  public static final String SERIALIZED_NAME_ETAT_CIVIL = "etatCivil";
  public static final String SERIALIZED_NAME_NAISSANCE = "naissance";
  public static final String SERIALIZED_NAME_SITUATION_PERSONNELLE = "situationPersonnelle";
  public static final String SERIALIZED_NAME_PROFESSION = "profession";
  public static final String SERIALIZED_NAME_CONTACTS = "contacts";
  public static final String SERIALIZED_NAME_BAC = "bac";
  public static final String SERIALIZED_NAME_PREMIERES_INSCRIPTIONS = "premieresInscriptions";
  public static final String SERIALIZED_NAME_DATE_CONTEXTE_APPRENANT = "dateContexteApprenant";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;
  @SerializedName(SERIALIZED_NAME_ETAT_CIVIL)
  private EtatCivil etatCivil;
  @SerializedName(SERIALIZED_NAME_NAISSANCE)
  private Naissance naissance;
  @SerializedName(SERIALIZED_NAME_SITUATION_PERSONNELLE)
  private SituationPersonnelleApprenant situationPersonnelle;
  @SerializedName(SERIALIZED_NAME_PROFESSION)
  private Profession profession;
  @SerializedName(SERIALIZED_NAME_CONTACTS)
  private List<ContactComplet> contacts = null;
  @SerializedName(SERIALIZED_NAME_BAC)
  private Bac bac;
  @SerializedName(SERIALIZED_NAME_PREMIERES_INSCRIPTIONS)
  private PremieresInscriptions premieresInscriptions;
  @SerializedName(SERIALIZED_NAME_DATE_CONTEXTE_APPRENANT)
  private Date dateContexteApprenant;

  public Apprenant() { 
  }

  public Apprenant code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * le code de l&#39;apprenant
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le code de l'apprenant")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public Apprenant etatCivil(EtatCivil etatCivil) {
    
    this.etatCivil = etatCivil;
    return this;
  }

   /**
   * Get etatCivil
   * @return etatCivil
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public EtatCivil getEtatCivil() {
    return etatCivil;
  }


  public void setEtatCivil(EtatCivil etatCivil) {
    this.etatCivil = etatCivil;
  }


  public Apprenant naissance(Naissance naissance) {
    
    this.naissance = naissance;
    return this;
  }

   /**
   * Get naissance
   * @return naissance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Naissance getNaissance() {
    return naissance;
  }


  public void setNaissance(Naissance naissance) {
    this.naissance = naissance;
  }


  public Apprenant situationPersonnelle(SituationPersonnelleApprenant situationPersonnelle) {
    
    this.situationPersonnelle = situationPersonnelle;
    return this;
  }

   /**
   * Get situationPersonnelle
   * @return situationPersonnelle
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SituationPersonnelleApprenant getSituationPersonnelle() {
    return situationPersonnelle;
  }


  public void setSituationPersonnelle(SituationPersonnelleApprenant situationPersonnelle) {
    this.situationPersonnelle = situationPersonnelle;
  }


  public Apprenant profession(Profession profession) {
    
    this.profession = profession;
    return this;
  }

   /**
   * Get profession
   * @return profession
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Profession getProfession() {
    return profession;
  }


  public void setProfession(Profession profession) {
    this.profession = profession;
  }


  public Apprenant contacts(List<ContactComplet> contacts) {
    
    this.contacts = contacts;
    return this;
  }

  public Apprenant addContactsItem(ContactComplet contactsItem) {
    if (this.contacts == null) {
      this.contacts = new ArrayList<ContactComplet>();
    }
    this.contacts.add(contactsItem);
    return this;
  }

   /**
   * Liste des contacts sous forme d&#39;objet, avec le code du contact pour clé principale
   * @return contacts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Liste des contacts sous forme d'objet, avec le code du contact pour clé principale")

  public List<ContactComplet> getContacts() {
    return contacts;
  }


  public void setContacts(List<ContactComplet> contacts) {
    this.contacts = contacts;
  }


  public Apprenant bac(Bac bac) {
    
    this.bac = bac;
    return this;
  }

   /**
   * Get bac
   * @return bac
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Bac getBac() {
    return bac;
  }


  public void setBac(Bac bac) {
    this.bac = bac;
  }


  public Apprenant premieresInscriptions(PremieresInscriptions premieresInscriptions) {
    
    this.premieresInscriptions = premieresInscriptions;
    return this;
  }

   /**
   * Get premieresInscriptions
   * @return premieresInscriptions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public PremieresInscriptions getPremieresInscriptions() {
    return premieresInscriptions;
  }


  public void setPremieresInscriptions(PremieresInscriptions premieresInscriptions) {
    this.premieresInscriptions = premieresInscriptions;
  }


  public Apprenant dateContexteApprenant(Date dateContexteApprenant) {
    
    this.dateContexteApprenant = dateContexteApprenant;
    return this;
  }

   /**
   * date de contexte de l&#39;apprenant
   * @return dateContexteApprenant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "date de contexte de l'apprenant")

  public Date getDateContexteApprenant() {
    return dateContexteApprenant;
  }


  public void setDateContexteApprenant(Date dateContexteApprenant) {
    this.dateContexteApprenant = dateContexteApprenant;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Apprenant apprenant = (Apprenant) o;
    return Objects.equals(this.code, apprenant.code) &&
        Objects.equals(this.etatCivil, apprenant.etatCivil) &&
        Objects.equals(this.naissance, apprenant.naissance) &&
        Objects.equals(this.situationPersonnelle, apprenant.situationPersonnelle) &&
        Objects.equals(this.profession, apprenant.profession) &&
        Objects.equals(this.contacts, apprenant.contacts) &&
        Objects.equals(this.bac, apprenant.bac) &&
        Objects.equals(this.premieresInscriptions, apprenant.premieresInscriptions) &&
        Objects.equals(this.dateContexteApprenant, apprenant.dateContexteApprenant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, etatCivil, naissance, situationPersonnelle, profession, contacts, bac, premieresInscriptions, dateContexteApprenant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Apprenant {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    etatCivil: ").append(toIndentedString(etatCivil)).append("\n");
    sb.append("    naissance: ").append(toIndentedString(naissance)).append("\n");
    sb.append("    situationPersonnelle: ").append(toIndentedString(situationPersonnelle)).append("\n");
    sb.append("    profession: ").append(toIndentedString(profession)).append("\n");
    sb.append("    contacts: ").append(toIndentedString(contacts)).append("\n");
    sb.append("    bac: ").append(toIndentedString(bac)).append("\n");
    sb.append("    premieresInscriptions: ").append(toIndentedString(premieresInscriptions)).append("\n");
    sb.append("    dateContexteApprenant: ").append(toIndentedString(dateContexteApprenant)).append("\n");
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

