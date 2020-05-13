/**
 *
 *  ESUP-Portail MONDOSSIERWEB - Copyright (c) 2020 ESUP-Portail consortium
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

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.test.context.TestComponent;

import lombok.extern.slf4j.Slf4j;

/**
 * Permet de nettoyer la base avant les tests.
 */
@TestComponent
@Slf4j
public class FlywayCleanMigrateStrategy implements FlywayMigrationStrategy {

	/**
	 * @see org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy#migrate(org.flywaydb.core.Flyway)
	 */
	@Override
	public void migrate(final Flyway flyway) {
		log.info("Clean test database...");
		flyway.clean();
		log.info("Migrate test database...");
		flyway.migrate();
	}

}
