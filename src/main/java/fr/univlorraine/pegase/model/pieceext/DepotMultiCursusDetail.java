/*
 * PIECE Externe V1
 *  # Introduction  Liste l'ensemble des services et des opérations externes disponibles dans le module Piece  La documentation d'intégration de Pégase est publiée par version dans  [ce répertoire](https://share.pc-scol.fr/d/d98bdddb6485406b9422/).  Vous y retrouverez notamment des informations sur le modèle objet métier,  le versionning des APIs, les cas d'usage des APIs externes.  # Authentification/autorisation obligatoire  Pour tout appel à une opération vous devez être authentifié/authorisé à l'aide d'un [token jwt](https://jwt.io/). Pour cela, chaque requête HTTP doit contenir un token valide dans le header HTTP `Authorization`.  Le format est `Authorization: Bearer <token-jwt>`. Par exemple `Authorization: Bearer xxxx.yyyy.zzzz`.  Lien vers [la documentation](https://share.pc-scol.fr/f/4487c726ade84022ae16/?dl=1) qui décrit l'authentification aux APIs de Pegase.  # Type de données  Sauf indications spécifiques données au niveau de l'opération, les types de données utilisés dans cette API sont les suivants :   * string - Chaîne de caractères encodée en UTF8 (ex : `Une chaîne de caractère`)     * Dans le cas des descripteurs de type `codeXxx`, seuls les caractères de A à Z, de 0 à 9 et le tiret(-) sont autorisés     * Dans le cas des descripteurs de type montant ou nombre avec une partie décimale, seuls les caractères de 0 à 9 et le point(.) sont autorisés (ex : `12525.99`)   * string($date) - Une date sous la forme d'une chaîne de caractères (ex : `2020-02-25`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * string($date-time) - Une date et heure avec fuseau horaire sous la forme d'une chaîne de caractères (ex : `2020-02-25T18:36:22+02:00`, norme [ISO-8601](https://fr.wikipedia.org/wiki/ISO_8601))   * integer($int64) - Un entier sur 64 bits (de -9 223 372 036 854 775 808 à 9 223 372 036 854 775 807) (ex : `2542`)   * integer($int32) - Un entier sur 32 bits (de –2 147 483 648 à 2 147 483 647) (ex : `2542`)   * boolean - Un booléen représenté par `true` ou `false`  # Gestion des erreurs  ## StatusCode  | Code    | Description                                | |---------|--------------------------------------------| | 200     | Opération effectuée                        | |         | Cas particulier: Dans le cas d'APIs de     | |         | type bulk, un 200 peut aussi être retourné | |         | si des données de la requête sont          | |         | considérées en erreur                      | | 201     | Ressource créée                            | | 400     | Données envoyées par le client invalides   | | 403     | Accès refusé                               | | 404     | Ressource inexistante                      | | 409     | donnée déjà existante                      | | 500     | Erreur technique rencontrée par le serveur |   ## Codes d'erreurs  | Code      | Description                                | |-----------|--------------------------------------------| | notNull   | la propriété est obligatoire               | | notBlank  | la propriété ne doit pas être vide         | | size      | la longueur de la propriété est invalide   | | pattern   | les caractères ou la syntaxe de            | |           | la propriété est invalide                  | | genre     | le genre de la personne est invalide       | | dateEntre | la date est invalide                       | | telephone | le téléphone est invalide                  | | email     | le mail est invalide                       | 
 *
 * The version of the OpenAPI document: 1.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pieceext;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * DepotMultiCursusDetail
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-04-04T15:38:18.494892400+02:00[Europe/Paris]")
public class DepotMultiCursusDetail {
  public static final String SERIALIZED_NAME_LISTE_DEPOT_CURSUS = "listeDepotCursus";
  public static final String SERIALIZED_NAME_CODE_STRUCTURE = "codeStructure";
  public static final String SERIALIZED_NAME_INE_APPRENANT = "ineApprenant";
  public static final String SERIALIZED_NAME_CODE_APPRENANT = "codeApprenant";
  public static final String SERIALIZED_NAME_DATE_DEPOT = "dateDepot";
  public static final String SERIALIZED_NAME_DATE_MODIFICATION_STATUT = "dateModificationStatut";
  public static final String SERIALIZED_NAME_STATUT_DEPOT = "statutDepot";
  public static final String SERIALIZED_NAME_SOURCE_DEPOT = "sourceDepot";
  public static final String SERIALIZED_NAME_MOTIF_REJET = "motifRejet";
  public static final String SERIALIZED_NAME_NOM_FICHIER = "nomFichier";
  public static final String SERIALIZED_NAME_TYPE_MIME = "typeMime";
  public static final String SERIALIZED_NAME_DESCRIPTEURS_DEMANDE_PIECE = "descripteursDemandePiece";
  public static final String SERIALIZED_NAME_TAILLE_FICHIER = "tailleFichier";
  @SerializedName(SERIALIZED_NAME_LISTE_DEPOT_CURSUS)
  private List<DepotCursus> listeDepotCursus = new ArrayList<DepotCursus>();
  @SerializedName(SERIALIZED_NAME_CODE_STRUCTURE)
  private String codeStructure;
  @SerializedName(SERIALIZED_NAME_INE_APPRENANT)
  private String ineApprenant;
  @SerializedName(SERIALIZED_NAME_CODE_APPRENANT)
  private String codeApprenant;
  @SerializedName(SERIALIZED_NAME_DATE_DEPOT)
  private Date dateDepot;
  @SerializedName(SERIALIZED_NAME_DATE_MODIFICATION_STATUT)
  private Date dateModificationStatut;
  @SerializedName(SERIALIZED_NAME_STATUT_DEPOT)
  private StatutDepot statutDepot = StatutDepot.NON_DEPOSE;
  @SerializedName(SERIALIZED_NAME_SOURCE_DEPOT)
  private SourceDepot sourceDepot = SourceDepot.INS_PISTE;
  @SerializedName(SERIALIZED_NAME_MOTIF_REJET)
  private String motifRejet;
  @SerializedName(SERIALIZED_NAME_NOM_FICHIER)
  private String nomFichier;
  @SerializedName(SERIALIZED_NAME_TYPE_MIME)
  private String typeMime;
  @SerializedName(SERIALIZED_NAME_DESCRIPTEURS_DEMANDE_PIECE)
  private DescripteursDemandePiece descripteursDemandePiece;
  @SerializedName(SERIALIZED_NAME_TAILLE_FICHIER)
  private Long tailleFichier;

  public DepotMultiCursusDetail() { 
  }

  public DepotMultiCursusDetail listeDepotCursus(List<DepotCursus> listeDepotCursus) {
    
    this.listeDepotCursus = listeDepotCursus;
    return this;
  }

  public DepotMultiCursusDetail addListeDepotCursusItem(DepotCursus listeDepotCursusItem) {
    this.listeDepotCursus.add(listeDepotCursusItem);
    return this;
  }

   /**
   * Get listeDepotCursus
   * @return listeDepotCursus
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public List<DepotCursus> getListeDepotCursus() {
    return listeDepotCursus;
  }


  public void setListeDepotCursus(List<DepotCursus> listeDepotCursus) {
    this.listeDepotCursus = listeDepotCursus;
  }


  public DepotMultiCursusDetail codeStructure(String codeStructure) {
    
    this.codeStructure = codeStructure;
    return this;
  }

   /**
   * Get codeStructure
   * @return codeStructure
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getCodeStructure() {
    return codeStructure;
  }


  public void setCodeStructure(String codeStructure) {
    this.codeStructure = codeStructure;
  }


  public DepotMultiCursusDetail ineApprenant(String ineApprenant) {
    
    this.ineApprenant = ineApprenant;
    return this;
  }

   /**
   * Get ineApprenant
   * @return ineApprenant
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getIneApprenant() {
    return ineApprenant;
  }


  public void setIneApprenant(String ineApprenant) {
    this.ineApprenant = ineApprenant;
  }


  public DepotMultiCursusDetail codeApprenant(String codeApprenant) {
    
    this.codeApprenant = codeApprenant;
    return this;
  }

   /**
   * Get codeApprenant
   * @return codeApprenant
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getCodeApprenant() {
    return codeApprenant;
  }


  public void setCodeApprenant(String codeApprenant) {
    this.codeApprenant = codeApprenant;
  }


  public DepotMultiCursusDetail dateDepot(Date dateDepot) {
    
    this.dateDepot = dateDepot;
    return this;
  }

   /**
   * Get dateDepot
   * @return dateDepot
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getDateDepot() {
    return dateDepot;
  }


  public void setDateDepot(Date dateDepot) {
    this.dateDepot = dateDepot;
  }


  public DepotMultiCursusDetail dateModificationStatut(Date dateModificationStatut) {
    
    this.dateModificationStatut = dateModificationStatut;
    return this;
  }

   /**
   * Get dateModificationStatut
   * @return dateModificationStatut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Date getDateModificationStatut() {
    return dateModificationStatut;
  }


  public void setDateModificationStatut(Date dateModificationStatut) {
    this.dateModificationStatut = dateModificationStatut;
  }


  public DepotMultiCursusDetail statutDepot(StatutDepot statutDepot) {
    
    this.statutDepot = statutDepot;
    return this;
  }

   /**
   * Get statutDepot
   * @return statutDepot
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StatutDepot getStatutDepot() {
    return statutDepot;
  }


  public void setStatutDepot(StatutDepot statutDepot) {
    this.statutDepot = statutDepot;
  }


  public DepotMultiCursusDetail sourceDepot(SourceDepot sourceDepot) {
    
    this.sourceDepot = sourceDepot;
    return this;
  }

   /**
   * Get sourceDepot
   * @return sourceDepot
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SourceDepot getSourceDepot() {
    return sourceDepot;
  }


  public void setSourceDepot(SourceDepot sourceDepot) {
    this.sourceDepot = sourceDepot;
  }


  public DepotMultiCursusDetail motifRejet(String motifRejet) {
    
    this.motifRejet = motifRejet;
    return this;
  }

   /**
   * le motif de rejet de la pièce par le gestionnaire
   * @return motifRejet
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le motif de rejet de la pièce par le gestionnaire")

  public String getMotifRejet() {
    return motifRejet;
  }


  public void setMotifRejet(String motifRejet) {
    this.motifRejet = motifRejet;
  }


  public DepotMultiCursusDetail nomFichier(String nomFichier) {
    
    this.nomFichier = nomFichier;
    return this;
  }

   /**
   * nom du fichier
   * @return nomFichier
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "nom du fichier")

  public String getNomFichier() {
    return nomFichier;
  }


  public void setNomFichier(String nomFichier) {
    this.nomFichier = nomFichier;
  }


  public DepotMultiCursusDetail typeMime(String typeMime) {
    
    this.typeMime = typeMime;
    return this;
  }

   /**
   * le format du fichier
   * @return typeMime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "le format du fichier")

  public String getTypeMime() {
    return typeMime;
  }


  public void setTypeMime(String typeMime) {
    this.typeMime = typeMime;
  }


  public DepotMultiCursusDetail descripteursDemandePiece(DescripteursDemandePiece descripteursDemandePiece) {
    
    this.descripteursDemandePiece = descripteursDemandePiece;
    return this;
  }

   /**
   * Get descripteursDemandePiece
   * @return descripteursDemandePiece
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DescripteursDemandePiece getDescripteursDemandePiece() {
    return descripteursDemandePiece;
  }


  public void setDescripteursDemandePiece(DescripteursDemandePiece descripteursDemandePiece) {
    this.descripteursDemandePiece = descripteursDemandePiece;
  }


  public DepotMultiCursusDetail tailleFichier(Long tailleFichier) {
    
    this.tailleFichier = tailleFichier;
    return this;
  }

   /**
   * taille du fichier
   * @return tailleFichier
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "taille du fichier")

  public Long getTailleFichier() {
    return tailleFichier;
  }


  public void setTailleFichier(Long tailleFichier) {
    this.tailleFichier = tailleFichier;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DepotMultiCursusDetail depotMultiCursusDetail = (DepotMultiCursusDetail) o;
    return Objects.equals(this.listeDepotCursus, depotMultiCursusDetail.listeDepotCursus) &&
        Objects.equals(this.codeStructure, depotMultiCursusDetail.codeStructure) &&
        Objects.equals(this.ineApprenant, depotMultiCursusDetail.ineApprenant) &&
        Objects.equals(this.codeApprenant, depotMultiCursusDetail.codeApprenant) &&
        Objects.equals(this.dateDepot, depotMultiCursusDetail.dateDepot) &&
        Objects.equals(this.dateModificationStatut, depotMultiCursusDetail.dateModificationStatut) &&
        Objects.equals(this.statutDepot, depotMultiCursusDetail.statutDepot) &&
        Objects.equals(this.sourceDepot, depotMultiCursusDetail.sourceDepot) &&
        Objects.equals(this.motifRejet, depotMultiCursusDetail.motifRejet) &&
        Objects.equals(this.nomFichier, depotMultiCursusDetail.nomFichier) &&
        Objects.equals(this.typeMime, depotMultiCursusDetail.typeMime) &&
        Objects.equals(this.descripteursDemandePiece, depotMultiCursusDetail.descripteursDemandePiece) &&
        Objects.equals(this.tailleFichier, depotMultiCursusDetail.tailleFichier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(listeDepotCursus, codeStructure, ineApprenant, codeApprenant, dateDepot, dateModificationStatut, statutDepot, sourceDepot, motifRejet, nomFichier, typeMime, descripteursDemandePiece, tailleFichier);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DepotMultiCursusDetail {\n");
    sb.append("    listeDepotCursus: ").append(toIndentedString(listeDepotCursus)).append("\n");
    sb.append("    codeStructure: ").append(toIndentedString(codeStructure)).append("\n");
    sb.append("    ineApprenant: ").append(toIndentedString(ineApprenant)).append("\n");
    sb.append("    codeApprenant: ").append(toIndentedString(codeApprenant)).append("\n");
    sb.append("    dateDepot: ").append(toIndentedString(dateDepot)).append("\n");
    sb.append("    dateModificationStatut: ").append(toIndentedString(dateModificationStatut)).append("\n");
    sb.append("    statutDepot: ").append(toIndentedString(statutDepot)).append("\n");
    sb.append("    sourceDepot: ").append(toIndentedString(sourceDepot)).append("\n");
    sb.append("    motifRejet: ").append(toIndentedString(motifRejet)).append("\n");
    sb.append("    nomFichier: ").append(toIndentedString(nomFichier)).append("\n");
    sb.append("    typeMime: ").append(toIndentedString(typeMime)).append("\n");
    sb.append("    descripteursDemandePiece: ").append(toIndentedString(descripteursDemandePiece)).append("\n");
    sb.append("    tailleFichier: ").append(toIndentedString(tailleFichier)).append("\n");
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

