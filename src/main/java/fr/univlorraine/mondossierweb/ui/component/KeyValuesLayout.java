package fr.univlorraine.mondossierweb.ui.component;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class KeyValuesLayout extends VerticalLayout {

    private static final String PREFIX_SAISIE_CLE = "_TFK";
    private static final String PREFIX_BADGES = "_V";
    private static final String PREFIX_SAISIE_BADGES = "_TFV";
    private static final String PARAMETRES_BADGES_TEXTFIELD = "parametres.badges-tf";
    private static final String PARAMETRES_BADGES_LABEL = "parametres.badges-label";

    private TextField keyTextField;
    private BadgeList badges;
    private TextField badgeField;

    public KeyValuesLayout(String id, String titre, String valeur) {
        super();
        setId(id);
        this.getStyle().set(CSSColorUtils.PADDING, "0");

        // Le champ de saisie de la clé
        keyTextField = new TextField(titre);
        keyTextField.setWidthFull();
        keyTextField.setId(id + PREFIX_SAISIE_CLE);
        keyTextField.setReadOnly(true);
        add(keyTextField);

        // La liste des badges
        badges = new BadgeList(null,getTranslation(PARAMETRES_BADGES_LABEL), false);
        badges.setId(id + PREFIX_BADGES);
        badges.getStyle().set("flex-wrap", "wrap");
        add(badges);

        setValue(valeur);

        // Le champ de saisie des badges
        badgeField = new TextField();
        badgeField.setWidthFull();
        badgeField.setPlaceholder(getTranslation(PARAMETRES_BADGES_TEXTFIELD));
        badgeField.setId(id + PREFIX_SAISIE_BADGES);
        badgeField.addKeyDownListener(Key.ENTER, e -> {
            badges.addValue(badgeField.getValue());
            badgeField.clear();
        });
        badgeField.setReadOnly(true);
        badgeField.setVisible(false);
        add(badgeField);

    }

    public void setReadOnly(boolean readOnly) {
        keyTextField.setReadOnly(readOnly);
        badges.setReadOnly(readOnly);
        badgeField.setReadOnly(readOnly);
        badgeField.setVisible(!readOnly);
    }

    public String getValue() {
        if(StringUtils.hasText(keyTextField.getValue()) || StringUtils.hasText(badges.getValue())) {
            String k = StringUtils.hasText(keyTextField.getValue()) ? keyTextField.getValue() : "";
            String v = StringUtils.hasText(badges.getValue()) ? badges.getValue() : "";
            return k + "=" + v;
        }
        return null;
    }

    public void setValue(String valeur) {
        if(StringUtils.hasText(valeur) && valeur.contains("=")) {
            String[] t = valeur.split("=");
            if(t.length > 0) {
                keyTextField.setValue(t[0]);
            } else{
                keyTextField.setValue("");
            }
            if(t.length > 1) {
                badges.setValue(t[1]);
            } else {
                badges.setValue("");
            }
        }
    }
}
