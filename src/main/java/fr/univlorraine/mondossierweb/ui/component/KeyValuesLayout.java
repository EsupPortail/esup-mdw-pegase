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
package fr.univlorraine.mondossierweb.ui.component;

import com.vaadin.flow.component.textfield.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class KeyValuesLayout extends ValuesLayout {

    private static final String PREFIX_SAISIE_CLE = "_TFK";
    private TextField keyTextField;

    public KeyValuesLayout(String id, String titre) {
        super(id, null);

        // Le champ de saisie de la clé
        keyTextField = new TextField(titre);
        keyTextField.setWidthFull();
        keyTextField.setId(id + PREFIX_SAISIE_CLE);
        keyTextField.setReadOnly(true);
        addComponentAsFirst(keyTextField);

    }

    @Override
    public void setReadOnly(boolean readOnly) {
        keyTextField.setReadOnly(readOnly);
        getBadges().setReadOnly(readOnly);
        getBadgeField().setReadOnly(readOnly);
        getBadgeField().setVisible(!readOnly);
    }

    @Override
    public String getValue() {
        if(StringUtils.hasText(keyTextField.getValue()) || StringUtils.hasText(getBadges().getValue())) {
            String k = StringUtils.hasText(keyTextField.getValue()) ? keyTextField.getValue() : "";
            String v = StringUtils.hasText(getBadges().getValue()) ? getBadges().getValue() : "";
            return k + "=" + v;
        }
        return null;
    }

    @Override
    public void setValue(String valeur) {
        if(StringUtils.hasText(valeur) && valeur.contains("=")) {
            int equalPosition = valeur.indexOf("=");
            if(equalPosition > 0) {
                keyTextField.setValue(valeur.substring(0, equalPosition));
            } else{
                keyTextField.setValue("");
            }
            if(equalPosition < (valeur.length() - 1)) {
                getBadges().setValue(valeur.substring(equalPosition + 1, valeur.length()));
            } else {
                getBadges().setValue("");
            }
        }
    }
}
