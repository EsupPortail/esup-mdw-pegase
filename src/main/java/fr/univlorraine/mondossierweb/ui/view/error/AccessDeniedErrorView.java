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
package fr.univlorraine.mondossierweb.ui.view.error;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;

@Tag(Tag.DIV)
@SuppressWarnings("serial")
public class AccessDeniedErrorView extends Component implements HasErrorParameter<AccessDeniedException> {

	/**
	 * @see com.vaadin.flow.router.HasErrorParameter#setErrorParameter(com.vaadin.flow.router.BeforeEnterEvent, com.vaadin.flow.router.ErrorParameter)
	 */
	@Override
	public int setErrorParameter(final BeforeEnterEvent event, final ErrorParameter<AccessDeniedException> parameter) {
		/* Redirige */
		event.rerouteTo(AccessDeniedView.class);

		/* Renvoie le statut forbidden */
		return HttpServletResponse.SC_FORBIDDEN;
	}

}
