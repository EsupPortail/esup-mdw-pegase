/*
 * PAI v1 - Paiement
 * API pour la gestion des paiements
 *
 * The version of the OpenAPI document: 21.0.0
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
import fr.univlorraine.pegase.model.pai.ObjetLibelle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DroitCritere
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:45:05.470760100+01:00[Europe/Paris]")
public class DroitCritere {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_LIBELLE = "libelle";
  @SerializedName(SERIALIZED_NAME_LIBELLE)
  private String libelle;

  public static final String SERIALIZED_NAME_LIBELLE_COURT = "libelleCourt";
  @SerializedName(SERIALIZED_NAME_LIBELLE_COURT)
  private String libelleCourt;

  public static final String SERIALIZED_NAME_LIBELLE_LONG = "libelleLong";
  @SerializedName(SERIALIZED_NAME_LIBELLE_LONG)
  private String libelleLong;

  /**
   * Le type du critère
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    BOOLEEN("BOOLEEN"),
    
    LISTE("LISTE");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeEnum type;

  public static final String SERIALIZED_NAME_VALEUR_AUCUN_PROPOSEE = "valeurAucunProposee";
  @SerializedName(SERIALIZED_NAME_VALEUR_AUCUN_PROPOSEE)
  private Boolean valeurAucunProposee;

  public static final String SERIALIZED_NAME_OPERATEUR_AUCUN_PROPOSE = "operateurAucunPropose";
  @SerializedName(SERIALIZED_NAME_OPERATEUR_AUCUN_PROPOSE)
  private Boolean operateurAucunPropose;

  public static final String SERIALIZED_NAME_LIBELLE_AUCUN = "libelleAucun";
  @SerializedName(SERIALIZED_NAME_LIBELLE_AUCUN)
  private String libelleAucun;

  /**
   * operateur par defaut, null si indifferent
   */
  @JsonAdapter(OperateurDefautEnum.Adapter.class)
  public enum OperateurDefautEnum {
    EGAL("EGAL"),
    
    DIFFERENT("DIFFERENT");

    private String value;

    OperateurDefautEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static OperateurDefautEnum fromValue(String value) {
      for (OperateurDefautEnum b : OperateurDefautEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<OperateurDefautEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OperateurDefautEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OperateurDefautEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return OperateurDefautEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_OPERATEUR_DEFAUT = "operateurDefaut";
  @SerializedName(SERIALIZED_NAME_OPERATEUR_DEFAUT)
  private OperateurDefautEnum operateurDefaut;

  public static final String SERIALIZED_NAME_VALEUR_DEFAUT = "valeurDefaut";
  @SerializedName(SERIALIZED_NAME_VALEUR_DEFAUT)
  private String valeurDefaut;

  public static final String SERIALIZED_NAME_LISTE_VALEUR = "listeValeur";
  @SerializedName(SERIALIZED_NAME_LISTE_VALEUR)
  private List<ObjetLibelle> listeValeur = null;

  public DroitCritere() { 
  }

  public DroitCritere code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Le code du critère
   * @return code
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le code du critère")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public DroitCritere libelle(String libelle) {
    
    this.libelle = libelle;
    return this;
  }

   /**
   * Le libellé du critère
   * @return libelle
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé du critère")

  public String getLibelle() {
    return libelle;
  }


  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  public DroitCritere libelleCourt(String libelleCourt) {
    
    this.libelleCourt = libelleCourt;
    return this;
  }

   /**
   * Le libellé court du critère
   * @return libelleCourt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé court du critère")

  public String getLibelleCourt() {
    return libelleCourt;
  }


  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }


  public DroitCritere libelleLong(String libelleLong) {
    
    this.libelleLong = libelleLong;
    return this;
  }

   /**
   * Le libellé long du critère
   * @return libelleLong
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le libellé long du critère")

  public String getLibelleLong() {
    return libelleLong;
  }


  public void setLibelleLong(String libelleLong) {
    this.libelleLong = libelleLong;
  }


  public DroitCritere type(TypeEnum type) {
    
    this.type = type;
    return this;
  }

   /**
   * Le type du critère
   * @return type
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Le type du critère")

  public TypeEnum getType() {
    return type;
  }


  public void setType(TypeEnum type) {
    this.type = type;
  }


  public DroitCritere valeurAucunProposee(Boolean valeurAucunProposee) {
    
    this.valeurAucunProposee = valeurAucunProposee;
    return this;
  }

   /**
   * Est-ce que la valeur \&quot;(Aucun)\&quot; est proposée comme choix pour ce critère ?
   * @return valeurAucunProposee
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Est-ce que la valeur \"(Aucun)\" est proposée comme choix pour ce critère ?")

  public Boolean getValeurAucunProposee() {
    return valeurAucunProposee;
  }


  public void setValeurAucunProposee(Boolean valeurAucunProposee) {
    this.valeurAucunProposee = valeurAucunProposee;
  }


  public DroitCritere operateurAucunPropose(Boolean operateurAucunPropose) {
    
    this.operateurAucunPropose = operateurAucunPropose;
    return this;
  }

   /**
   * Est-ce que l&#39;operateur \&quot;(Aucun)\&quot; est proposée comme choix pour ce critère ?
   * @return operateurAucunPropose
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Est-ce que l'operateur \"(Aucun)\" est proposée comme choix pour ce critère ?")

  public Boolean getOperateurAucunPropose() {
    return operateurAucunPropose;
  }


  public void setOperateurAucunPropose(Boolean operateurAucunPropose) {
    this.operateurAucunPropose = operateurAucunPropose;
  }


  public DroitCritere libelleAucun(String libelleAucun) {
    
    this.libelleAucun = libelleAucun;
    return this;
  }

   /**
   * Le libellé de la valeur ou de l&#39;opérateur (aucun)
   * @return libelleAucun
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Le libellé de la valeur ou de l'opérateur (aucun)")

  public String getLibelleAucun() {
    return libelleAucun;
  }


  public void setLibelleAucun(String libelleAucun) {
    this.libelleAucun = libelleAucun;
  }


  public DroitCritere operateurDefaut(OperateurDefautEnum operateurDefaut) {
    
    this.operateurDefaut = operateurDefaut;
    return this;
  }

   /**
   * operateur par defaut, null si indifferent
   * @return operateurDefaut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "operateur par defaut, null si indifferent")

  public OperateurDefautEnum getOperateurDefaut() {
    return operateurDefaut;
  }


  public void setOperateurDefaut(OperateurDefautEnum operateurDefaut) {
    this.operateurDefaut = operateurDefaut;
  }


  public DroitCritere valeurDefaut(String valeurDefaut) {
    
    this.valeurDefaut = valeurDefaut;
    return this;
  }

   /**
   * liste (entre crochet) de codes séparées par des virgules, code &#39;&#39; pour (aucun) ou \&quot;true\&quot;/\&quot;false\&quot;
   * @return valeurDefaut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "liste (entre crochet) de codes séparées par des virgules, code '' pour (aucun) ou \"true\"/\"false\"")

  public String getValeurDefaut() {
    return valeurDefaut;
  }


  public void setValeurDefaut(String valeurDefaut) {
    this.valeurDefaut = valeurDefaut;
  }


  public DroitCritere listeValeur(List<ObjetLibelle> listeValeur) {
    
    this.listeValeur = listeValeur;
    return this;
  }

  public DroitCritere addListeValeurItem(ObjetLibelle listeValeurItem) {
    if (this.listeValeur == null) {
      this.listeValeur = new ArrayList<ObjetLibelle>();
    }
    this.listeValeur.add(listeValeurItem);
    return this;
  }

   /**
   * La liste des valeurs disponibles pour ce critère
   * @return listeValeur
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "La liste des valeurs disponibles pour ce critère")

  public List<ObjetLibelle> getListeValeur() {
    return listeValeur;
  }


  public void setListeValeur(List<ObjetLibelle> listeValeur) {
    this.listeValeur = listeValeur;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DroitCritere droitCritere = (DroitCritere) o;
    return Objects.equals(this.code, droitCritere.code) &&
        Objects.equals(this.libelle, droitCritere.libelle) &&
        Objects.equals(this.libelleCourt, droitCritere.libelleCourt) &&
        Objects.equals(this.libelleLong, droitCritere.libelleLong) &&
        Objects.equals(this.type, droitCritere.type) &&
        Objects.equals(this.valeurAucunProposee, droitCritere.valeurAucunProposee) &&
        Objects.equals(this.operateurAucunPropose, droitCritere.operateurAucunPropose) &&
        Objects.equals(this.libelleAucun, droitCritere.libelleAucun) &&
        Objects.equals(this.operateurDefaut, droitCritere.operateurDefaut) &&
        Objects.equals(this.valeurDefaut, droitCritere.valeurDefaut) &&
        Objects.equals(this.listeValeur, droitCritere.listeValeur);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, libelle, libelleCourt, libelleLong, type, valeurAucunProposee, operateurAucunPropose, libelleAucun, operateurDefaut, valeurDefaut, listeValeur);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DroitCritere {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
    sb.append("    libelleCourt: ").append(toIndentedString(libelleCourt)).append("\n");
    sb.append("    libelleLong: ").append(toIndentedString(libelleLong)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    valeurAucunProposee: ").append(toIndentedString(valeurAucunProposee)).append("\n");
    sb.append("    operateurAucunPropose: ").append(toIndentedString(operateurAucunPropose)).append("\n");
    sb.append("    libelleAucun: ").append(toIndentedString(libelleAucun)).append("\n");
    sb.append("    operateurDefaut: ").append(toIndentedString(operateurDefaut)).append("\n");
    sb.append("    valeurDefaut: ").append(toIndentedString(valeurDefaut)).append("\n");
    sb.append("    listeValeur: ").append(toIndentedString(listeValeur)).append("\n");
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
