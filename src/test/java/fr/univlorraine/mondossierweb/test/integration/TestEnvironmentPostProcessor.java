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
package fr.univlorraine.mondossierweb.test.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import lombok.extern.slf4j.Slf4j;

/**
 * Initialise les properties de test.
 * @author Adrien Colson
 */
@Slf4j
public class TestEnvironmentPostProcessor implements EnvironmentPostProcessor {

	Map<String, Object> testPropertiesMap = new HashMap<>();

	public TestEnvironmentPostProcessor() {
		/* Connexion à la base */
		addEnvPropertyToMap("MYSQL_URL", "spring.datasource.url");
		addEnvPropertyToMap("MYSQL_USER", "spring.datasource.username");
		addEnvPropertyToMap("MYSQL_PASSWORD", "spring.datasource.password");
	}

	void addEnvPropertyToMap(final String env, final String propName) {
		String value = System.getenv(env);
		if (value == null) {
			log.debug("Environment variable '{}' not found, can't set spring test property '{}'.", env, propName);
		} else {
			testPropertiesMap.put(propName, value);
			log.info("Environment variable '{}' found, spring test property '{}' set to '{}'.", env, propName, value);
		}
	}

	/**
	 * @see org.springframework.boot.env.EnvironmentPostProcessor#postProcessEnvironment(org.springframework.core.env.ConfigurableEnvironment,
	 *      org.springframework.boot.SpringApplication)
	 */
	@Override
	public void postProcessEnvironment(final ConfigurableEnvironment environment, final SpringApplication application) {
		environment.getPropertySources().addFirst(new MapPropertySource("test-properties", testPropertiesMap));
	}

}
