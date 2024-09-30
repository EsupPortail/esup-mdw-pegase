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
package fr.univlorraine.mondossierweb.services;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@SuppressWarnings("serial")
@Service
@Slf4j
public class CssService implements Serializable {

	@Autowired
	private transient ConfigController configController;
	
	private String mainColor;
	private String secondColor;
	private String headerCardSepColor;
	private String btnColor;
	private String txtColor;
	private String backgroundColor;

	public void refreshCssParameters() {
		mainColor = configController.getCssMainColor();
		secondColor = configController.getCssSecondColor();
		headerCardSepColor = configController.getCssHeaderCardSepColor();
		btnColor = configController.getCssBtnColor();
		txtColor = configController.getCssTextColor();
		backgroundColor = configController.getCssBackgroundColor();
	}
	
	public String getMainColor() {
		if(mainColor == null) {
			refreshCssParameters();
		}
		return mainColor;
	}
	
	public String getSecondColor() {
		if(secondColor == null) {
			refreshCssParameters();
		}
		return secondColor;
	}
	
	public String getHeaderCardSepColor() {
		if(headerCardSepColor == null) {
			refreshCssParameters();
		}
		return headerCardSepColor;
	}
	
	public String getBtnColor() {
		if(btnColor == null) {
			refreshCssParameters();
		}
		return btnColor;
	}
	
	public String getTxtColor() {
		if(txtColor == null) {
			refreshCssParameters();
		}
		return txtColor;
	}
	
	public String getBackgroundColor() {
		if(backgroundColor == null) {
			refreshCssParameters();
		}
		return backgroundColor;
	}
}
