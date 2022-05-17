/**
 *
 *  ESUP-Portail ESUP-MONDOSSIERWEB-PEGASE - Copyright (c) 2021 ESUP-Portail consortium
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 16.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.univlorraine.pegase.model.pai;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fr.univlorraine.pegase.model.pai.CompteImputation;
import fr.univlorraine.pegase.model.pai.ContextesInscription;
import fr.univlorraine.pegase.model.pai.ModeConfirmationPaiement;
import fr.univlorraine.pegase.model.pai.ModePaiement;
import fr.univlorraine.pegase.model.pai.Montant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ModaliteDePaiement
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-05-11T10:50:10.652+02:00[Europe/Paris]")
public class ModaliteDePaiement {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE_AFFICHAGE = "libelleAffichage";
  @SerializedName(SERIALIZED_NAME_LIBELLE_AFFICHAGE)
  private String libelleAffichage;

  public static final String SERIALIZED_NAME_NOTICE = "notice";
  @SerializedName(SERIALIZED_NAME_NOTICE)
  private String notice;

  public static final String SERIALIZED_NAME_MODE_PAIEMENT = "modePaiement";
  @SerializedName(SERIALIZED_NAME_MODE_PAIEMENT)
  private ModePaiement modePaiement;

  public static final String SERIALIZED_NAME_TEMOIN_SAISIE_REFERENCE = "temoinSaisieReference";
  @SerializedName(SERIALIZED_NAME_TEMOIN_SAISIE_REFERENCE)
  private Boolean temoinSaisieReference;

  public static final String SERIALIZED_NAME_MODE_CONFIRMATION_PAIEMENT = "modeConfirmationPaiement";
  @SerializedName(SERIALIZED_NAME_MODE_CONFIRMATION_PAIEMENT)
  private ModeConfirmationPaiement modeConfirmationPaiement;

  public static final String SERIALIZED_NAME_NOMBRE_OCCURRENCES = "nombreOccurrences";
  @SerializedName(SERIALIZED_NAME_NOMBRE_OCCURRENCES)
  private Integer nombreOccurrences = 1;

  public static final String SERIALIZED_NAME_FREQUENCE_PAIEMENT_N_FOIS = "frequencePaiementNFois";
  @SerializedName(SERIALIZED_NAME_FREQUENCE_PAIEMENT_N_FOIS)
  private Integer frequencePaiementNFois;

  public static final String SERIALIZED_NAME_TEMOIN_VISIBLE_ETUDIANT = "temoinVisibleEtudiant";
  @SerializedName(SERIALIZED_NAME_TEMOIN_VISIBLE_ETUDIANT)
  private Boolean temoinVisibleEtudiant;

  public static final String SERIALIZED_NAME_CONTEXTES_INSCRIPTION = "contextesInscription";
  @SerializedName(SERIALIZED_NAME_CONTEXTES_INSCRIPTION)
  private ContextesInscription contextesInscription;

  public static final String SERIALIZED_NAME_PRIORITE_AFFICHAGE = "prioriteAffichage";
  @SerializedName(SERIALIZED_NAME_PRIORITE_AFFICHAGE)
  private Integer prioriteAffichage = 0;

  public static final String SERIALIZED_NAME_DATE_DEBUT_VALIDITE = "dateDebutValidite";
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT_VALIDITE)
  private LocalDate dateDebutValidite;

  public static final String SERIALIZED_NAME_DATE_FIN_VALIDITE = "dateFinValidite";
  @SerializedName(SERIALIZED_NAME_DATE_FIN_VALIDITE)
  private LocalDate dateFinValidite;

  public static final String SERIALIZED_NAME_MONTANT_MINIMUM = "montantMinimum";
  @SerializedName(SERIALIZED_NAME_MONTANT_MINIMUM)
  private Montant montantMinimum;

  public static final String SERIALIZED_NAME_MONTANT_MAXIMUM = "montantMaximum";
  @SerializedName(SERIALIZED_NAME_MONTANT_MAXIMUM)
  private Montant montantMaximum;

  public static final String SERIALIZED_NAME_TEMOIN_LIVRE = "temoinLivre";
  @SerializedName(SERIALIZED_NAME_TEMOIN_LIVRE)
  private Boolean temoinLivre;

  public static final String SERIALIZED_NAME_COMPTES_IMPUTATION = "comptesImputation";
  @SerializedName(SERIALIZED_NAME_COMPTES_IMPUTATION)
  private List<CompteImputation> comptesImputation = null;

  public ModaliteDePaiement() { 
  }

  public ModaliteDePaiement code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code metier en saisie libre
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code metier en saisie libre")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public ModaliteDePaiement libelleAffichage(String libelleAffichage) {
    
    this.libelleAffichage = libelleAffichage;
    return this;
  }

   /**
   * Le libellé d&#39;affichage
   * @return libelleAffichage
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé d'affichage")

  public String getLibelleAffichage() {
    return libelleAffichage;
  }


  public void setLibelleAffichage(String libelleAffichage) {
    this.libelleAffichage = libelleAffichage;
  }


  public ModaliteDePaiement notice(String notice) {
    
    this.notice = notice;
    return this;
  }

   /**
   * Notice explicative de la modalité de paiement
   * @return notice
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Notice explicative de la modalité de paiement")

  public String getNotice() {
    return notice;
  }


  public void setNotice(String notice) {
    this.notice = notice;
  }


  public ModaliteDePaiement modePaiement(ModePaiement modePaiement) {
    
    this.modePaiement = modePaiement;
    return this;
  }

   /**
   * Get modePaiement
   * @return modePaiement
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ModePaiement getModePaiement() {
    return modePaiement;
  }


  public void setModePaiement(ModePaiement modePaiement) {
    this.modePaiement = modePaiement;
  }


  public ModaliteDePaiement temoinSaisieReference(Boolean temoinSaisieReference) {
    
    this.temoinSaisieReference = temoinSaisieReference;
    return this;
  }

   /**
   * Le témoin de saisie d&#39;un motif ou d&#39;une référence, par exemple la référence au dos d&#39;un chèque
   * @return temoinSaisieReference
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le témoin de saisie d'un motif ou d'une référence, par exemple la référence au dos d'un chèque")

  public Boolean getTemoinSaisieReference() {
    return temoinSaisieReference;
  }


  public void setTemoinSaisieReference(Boolean temoinSaisieReference) {
    this.temoinSaisieReference = temoinSaisieReference;
  }


  public ModaliteDePaiement modeConfirmationPaiement(ModeConfirmationPaiement modeConfirmationPaiement) {
    
    this.modeConfirmationPaiement = modeConfirmationPaiement;
    return this;
  }

   /**
   * Get modeConfirmationPaiement
   * @return modeConfirmationPaiement
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public ModeConfirmationPaiement getModeConfirmationPaiement() {
    return modeConfirmationPaiement;
  }


  public void setModeConfirmationPaiement(ModeConfirmationPaiement modeConfirmationPaiement) {
    this.modeConfirmationPaiement = modeConfirmationPaiement;
  }


  public ModaliteDePaiement nombreOccurrences(Integer nombreOccurrences) {
    
    this.nombreOccurrences = nombreOccurrences;
    return this;
  }

   /**
   * Get nombreOccurrences
   * minimum: 1
   * @return nombreOccurrences
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Integer getNombreOccurrences() {
    return nombreOccurrences;
  }


  public void setNombreOccurrences(Integer nombreOccurrences) {
    this.nombreOccurrences = nombreOccurrences;
  }


  public ModaliteDePaiement frequencePaiementNFois(Integer frequencePaiementNFois) {
    
    this.frequencePaiementNFois = frequencePaiementNFois;
    return this;
  }

   /**
   * Fréquence en mois des occurrences. Obligatoire si \&quot;nombreOccurrences &gt; 1\&quot;, ignoré sinon.
   * minimum: 1
   * @return frequencePaiementNFois
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Fréquence en mois des occurrences. Obligatoire si \"nombreOccurrences > 1\", ignoré sinon.")

  public Integer getFrequencePaiementNFois() {
    return frequencePaiementNFois;
  }


  public void setFrequencePaiementNFois(Integer frequencePaiementNFois) {
    this.frequencePaiementNFois = frequencePaiementNFois;
  }


  public ModaliteDePaiement temoinVisibleEtudiant(Boolean temoinVisibleEtudiant) {
    
    this.temoinVisibleEtudiant = temoinVisibleEtudiant;
    return this;
  }

   /**
   * Le témoin indiquant que cette modalité de paiement est proposée aux étudiants
   * @return temoinVisibleEtudiant
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le témoin indiquant que cette modalité de paiement est proposée aux étudiants")

  public Boolean getTemoinVisibleEtudiant() {
    return temoinVisibleEtudiant;
  }


  public void setTemoinVisibleEtudiant(Boolean temoinVisibleEtudiant) {
    this.temoinVisibleEtudiant = temoinVisibleEtudiant;
  }


  public ModaliteDePaiement contextesInscription(ContextesInscription contextesInscription) {
    
    this.contextesInscription = contextesInscription;
    return this;
  }

   /**
   * Get contextesInscription
   * @return contextesInscription
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ContextesInscription getContextesInscription() {
    return contextesInscription;
  }


  public void setContextesInscription(ContextesInscription contextesInscription) {
    this.contextesInscription = contextesInscription;
  }


  public ModaliteDePaiement prioriteAffichage(Integer prioriteAffichage) {
    
    this.prioriteAffichage = prioriteAffichage;
    return this;
  }

   /**
   * La priorité d&#39;affichage
   * @return prioriteAffichage
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La priorité d'affichage")

  public Integer getPrioriteAffichage() {
    return prioriteAffichage;
  }


  public void setPrioriteAffichage(Integer prioriteAffichage) {
    this.prioriteAffichage = prioriteAffichage;
  }


  public ModaliteDePaiement dateDebutValidite(LocalDate dateDebutValidite) {
    
    this.dateDebutValidite = dateDebutValidite;
    return this;
  }

   /**
   * La date de début de validité au format \&quot;2018-01-31\&quot;
   * @return dateDebutValidite
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "La date de début de validité au format \"2018-01-31\"")

  public LocalDate getDateDebutValidite() {
    return dateDebutValidite;
  }


  public void setDateDebutValidite(LocalDate dateDebutValidite) {
    this.dateDebutValidite = dateDebutValidite;
  }


  public ModaliteDePaiement dateFinValidite(LocalDate dateFinValidite) {
    
    this.dateFinValidite = dateFinValidite;
    return this;
  }

   /**
   * Optionnelle, la date de fin de validité au format \&quot;2020-12-31\&quot;. Si renseignée, doit-être postérieure à la date de début et à la date du jour.
   * @return dateFinValidite
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Optionnelle, la date de fin de validité au format \"2020-12-31\". Si renseignée, doit-être postérieure à la date de début et à la date du jour.")

  public LocalDate getDateFinValidite() {
    return dateFinValidite;
  }


  public void setDateFinValidite(LocalDate dateFinValidite) {
    this.dateFinValidite = dateFinValidite;
  }


  public ModaliteDePaiement montantMinimum(Montant montantMinimum) {
    
    this.montantMinimum = montantMinimum;
    return this;
  }

   /**
   * Get montantMinimum
   * @return montantMinimum
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Montant getMontantMinimum() {
    return montantMinimum;
  }


  public void setMontantMinimum(Montant montantMinimum) {
    this.montantMinimum = montantMinimum;
  }


  public ModaliteDePaiement montantMaximum(Montant montantMaximum) {
    
    this.montantMaximum = montantMaximum;
    return this;
  }

   /**
   * Get montantMaximum
   * @return montantMaximum
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Montant getMontantMaximum() {
    return montantMaximum;
  }


  public void setMontantMaximum(Montant montantMaximum) {
    this.montantMaximum = montantMaximum;
  }


  public ModaliteDePaiement temoinLivre(Boolean temoinLivre) {
    
    this.temoinLivre = temoinLivre;
    return this;
  }

   /**
   * Témoin indiquant si cette modalité de paiement est fournie avec la livraison de Pégase
   * @return temoinLivre
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Témoin indiquant si cette modalité de paiement est fournie avec la livraison de Pégase")

  public Boolean getTemoinLivre() {
    return temoinLivre;
  }


  public void setTemoinLivre(Boolean temoinLivre) {
    this.temoinLivre = temoinLivre;
  }


  public ModaliteDePaiement comptesImputation(List<CompteImputation> comptesImputation) {
    
    this.comptesImputation = comptesImputation;
    return this;
  }

  public ModaliteDePaiement addComptesImputationItem(CompteImputation comptesImputationItem) {
    if (this.comptesImputation == null) {
      this.comptesImputation = new ArrayList<>();
    }
    this.comptesImputation.add(comptesImputationItem);
    return this;
  }

   /**
   * Les Comptes d&#39;imputation liés à la modalité de paiement
   * @return comptesImputation
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Les Comptes d'imputation liés à la modalité de paiement")

  public List<CompteImputation> getComptesImputation() {
    return comptesImputation;
  }


  public void setComptesImputation(List<CompteImputation> comptesImputation) {
    this.comptesImputation = comptesImputation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModaliteDePaiement modaliteDePaiement = (ModaliteDePaiement) o;
    return Objects.equals(this.code, modaliteDePaiement.code) &&
        Objects.equals(this.libelleAffichage, modaliteDePaiement.libelleAffichage) &&
        Objects.equals(this.notice, modaliteDePaiement.notice) &&
        Objects.equals(this.modePaiement, modaliteDePaiement.modePaiement) &&
        Objects.equals(this.temoinSaisieReference, modaliteDePaiement.temoinSaisieReference) &&
        Objects.equals(this.modeConfirmationPaiement, modaliteDePaiement.modeConfirmationPaiement) &&
        Objects.equals(this.nombreOccurrences, modaliteDePaiement.nombreOccurrences) &&
        Objects.equals(this.frequencePaiementNFois, modaliteDePaiement.frequencePaiementNFois) &&
        Objects.equals(this.temoinVisibleEtudiant, modaliteDePaiement.temoinVisibleEtudiant) &&
        Objects.equals(this.contextesInscription, modaliteDePaiement.contextesInscription) &&
        Objects.equals(this.prioriteAffichage, modaliteDePaiement.prioriteAffichage) &&
        Objects.equals(this.dateDebutValidite, modaliteDePaiement.dateDebutValidite) &&
        Objects.equals(this.dateFinValidite, modaliteDePaiement.dateFinValidite) &&
        Objects.equals(this.montantMinimum, modaliteDePaiement.montantMinimum) &&
        Objects.equals(this.montantMaximum, modaliteDePaiement.montantMaximum) &&
        Objects.equals(this.temoinLivre, modaliteDePaiement.temoinLivre) &&
        Objects.equals(this.comptesImputation, modaliteDePaiement.comptesImputation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelleAffichage, notice, modePaiement, temoinSaisieReference, modeConfirmationPaiement, nombreOccurrences, frequencePaiementNFois, temoinVisibleEtudiant, contextesInscription, prioriteAffichage, dateDebutValidite, dateFinValidite, montantMinimum, montantMaximum, temoinLivre, comptesImputation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModaliteDePaiement {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelleAffichage: ").append(toIndentedString(libelleAffichage)).append("\n");
    sb.append("    notice: ").append(toIndentedString(notice)).append("\n");
    sb.append("    modePaiement: ").append(toIndentedString(modePaiement)).append("\n");
    sb.append("    temoinSaisieReference: ").append(toIndentedString(temoinSaisieReference)).append("\n");
    sb.append("    modeConfirmationPaiement: ").append(toIndentedString(modeConfirmationPaiement)).append("\n");
    sb.append("    nombreOccurrences: ").append(toIndentedString(nombreOccurrences)).append("\n");
    sb.append("    frequencePaiementNFois: ").append(toIndentedString(frequencePaiementNFois)).append("\n");
    sb.append("    temoinVisibleEtudiant: ").append(toIndentedString(temoinVisibleEtudiant)).append("\n");
    sb.append("    contextesInscription: ").append(toIndentedString(contextesInscription)).append("\n");
    sb.append("    prioriteAffichage: ").append(toIndentedString(prioriteAffichage)).append("\n");
    sb.append("    dateDebutValidite: ").append(toIndentedString(dateDebutValidite)).append("\n");
    sb.append("    dateFinValidite: ").append(toIndentedString(dateFinValidite)).append("\n");
    sb.append("    montantMinimum: ").append(toIndentedString(montantMinimum)).append("\n");
    sb.append("    montantMaximum: ").append(toIndentedString(montantMaximum)).append("\n");
    sb.append("    temoinLivre: ").append(toIndentedString(temoinLivre)).append("\n");
    sb.append("    comptesImputation: ").append(toIndentedString(comptesImputation)).append("\n");
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

