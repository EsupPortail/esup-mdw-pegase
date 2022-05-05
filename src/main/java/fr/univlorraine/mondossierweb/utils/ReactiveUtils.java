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
package fr.univlorraine.mondossierweb.utils;

import java.util.function.Function;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.Command;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class ReactiveUtils {

	public static void addDisposableWhenAttached(final Component component, final Function<AttachEvent, Disposable> disposableFunction) {
		component.addAttachListener(attachEvent -> {
			Disposable disposable = disposableFunction.apply(attachEvent);
			component.addDetachListener(detachEvent -> disposable.dispose());
		});
	}

	public static void subscribeWhenAttached(final Component component, final Flux<Command> commandFlux) {
		addDisposableWhenAttached(component, attachEvent -> commandFlux.subscribe(attachEvent.getUI()::access));
	}
}
