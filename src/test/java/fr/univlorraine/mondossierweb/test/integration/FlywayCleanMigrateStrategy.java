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

