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

import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class BadgeList extends HorizontalLayout {

    // private List<Badge> badges;
    private NativeLabel label;
    private boolean closable;

    public BadgeList(String value, String titre, Boolean closable) {
        super();
        if(titre != null) {
            label = new NativeLabel(titre);
            label.getStyle().set(CSSColorUtils.FONT_SIZE, CSSColorUtils.FONT_SIZE_SMALL);
            label.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
            label.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_TXT_COLOR);
        } else {
            label = null;
        }
        this.closable = closable;
        if(value != null) {
            setValue(value);
        }
    }

    public void addValue(String value) {
        Badge filterBadge = new Badge(value, this.closable);
        //badges.add(filterBadge);
        this.add(filterBadge);
    }

    public void setReadOnly(boolean readOnly) {
        this.closable = !readOnly;
        for(Badge b : getBadges()) {
            b.setReadOnly(readOnly);
        }
    }

    private List<Badge> getBadges() {
        List<Badge> lb = new LinkedList<Badge>();
        this.getChildren().filter(c -> c instanceof Badge).forEach(e -> lb.add((Badge) e));
        return lb;
    }

    public String getValue() {
        String value = null;
        List<Badge> badges = getBadges();
        if(badges != null && !badges.isEmpty()){
            for(Badge b : badges) {
                if(value == null) {
                    value = b.getValue();
                }else {
                    value += ";" + b.getValue();
                }
            }
        }
        return value;
    }

    public void setValue(String value) {
        this.removeAll();
        // AJOUT DU LABEL ET DES BADGES
        if(label != null) {
            this.add(label);
        }
        // Initialisation des badges avec les valeurs en BDD
        if(StringUtils.hasText(value)) {
            for(String v : Arrays.asList(value.split(";"))){
                addValue(v);
            }
        }
    }
}
