package fr.univlorraine.mondossierweb.model.app.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * La class converter LocalDateTime et Timestamp
 * @author Kevin Hergalant
 */
@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(final LocalDateTime entityValue) {
		if (entityValue == null) {
			return null;
		}
		return Timestamp.valueOf(entityValue);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(final Timestamp databaseValue) {
		if (databaseValue == null) {
			return null;
		}
		return databaseValue.toLocalDateTime();
	}
}