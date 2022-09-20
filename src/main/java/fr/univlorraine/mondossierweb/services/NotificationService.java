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

import org.springframework.stereotype.Service;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

@Service
public class NotificationService {

	private static final int NOTIFICATION_DURATION_IN_MILLIS = 5000;

	public void success(final String text, final int durationInMillis) {
		notify(VaadinIcon.CHECK.create(), text, "var(--lumo-success-text-color)", durationInMillis);
	}

	public void success(final String text) {
		success(text, NOTIFICATION_DURATION_IN_MILLIS);
	}

	public void warning(final String text, final int durationInMillis) {
		notify(VaadinIcon.WARNING.create(), text, "var(--lumo-body-text-color)", durationInMillis);
	}

	public void warning(final String text) {
		warning(text, NOTIFICATION_DURATION_IN_MILLIS);
	}

	public void error(final String text, final int durationInMillis) {
		notify(VaadinIcon.CLOSE.create(), text, "var(--lumo-error-text-color)", durationInMillis);
	}

	public void error(final String text) {
		error(text, NOTIFICATION_DURATION_IN_MILLIS);
	}

	public void notify(final String text) {
		Notification.show(text, NOTIFICATION_DURATION_IN_MILLIS, Position.BOTTOM_END);
	}

	private void notify(final Icon icon, final String text, final String color, final int durationInMillis) {
		icon.getStyle().set("margin-right", "0.5em");

		final Div divText = new Div();
		divText.getElement().setProperty("innerHTML", text);

		final Div notificationContent = new Div(icon, divText);
		notificationContent.getStyle().set("color", color).set("display", "flex");

		final Notification notification = new Notification(notificationContent);
		notification.setPosition(Position.TOP_CENTER);
		notification.setDuration(durationInMillis);
		notification.open();
	}

}
