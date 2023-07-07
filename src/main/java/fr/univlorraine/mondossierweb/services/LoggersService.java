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

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vaadin.flow.server.VaadinService;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/** Loggers Service
 *
 * @author Matthieu Manginot */
@SuppressWarnings("serial")
@Service
@Slf4j
public class LoggersService implements Serializable {

	@Resource
	private transient LoggingSystem loggingSystem;
	@Autowired
	private transient NotificationService notificationService;

	/* Événements Projet */
	private final transient DirectProcessor<LoggingEvent<LoggerLevel>> LoggerLevelEventsProcessor = DirectProcessor.create();
	private final transient FluxSink<LoggingEvent<LoggerLevel>> LoggerLevelEventsSink = LoggerLevelEventsProcessor.sink();
	@Getter
	private final transient Flux<LoggingEvent<LoggerLevel>> LoggerLevelEvents = LoggerLevelEventsProcessor;

	/** List of logger levels supported by the logging system.
	 *
	 * @return list of logger levels */
	public NavigableSet<LogLevel> getAllLevels() {
		Set<LogLevel> levels = this.loggingSystem.getSupportedLogLevels();
		return new TreeSet<>(levels).descendingSet();
	}

	/** Configure the level for a logger.
	 *
	 * @param loggerName
	 *            loggerName
	 * @param configuredLevel
	 *            configuredLevel */
	private void configureLogLevel(final String loggerName, final LogLevel configuredLevel) {
		Assert.notNull(loggerName, "Name must not be empty");
		log.info("\"{}\" set to {}", loggerName, configuredLevel);
		this.loggingSystem.setLogLevel(loggerName, configuredLevel);
	}

	/** Configure the level for multiple loggers.
	 *
	 * @param loggers
	 *            loggers
	 * @param configuredLevel
	 *            configuredLevel */
	private void configureLogLevel(final Set<LoggerLevel> loggers, final LogLevel configuredLevel) {
		Assert.notNull(loggers, "loggers must not be empty");
		loggers.forEach(l -> configureLogLevel(l.getLoggerName(), configuredLevel));

		/* Notification */
		LoggerLevelEventsSink.next(new LoggingEvent<>(LoggingEvent.Action.CHANGED, null));
	}

	/** @param configurations
	 *            configurations
	 * @return map loggerName and LoggerLevel */
	private Map<String, LoggerLevel> getLoggers(final Collection<LoggerConfiguration> configurations) {
		Map<String, LoggerLevel> loggers = new LinkedHashMap<>(configurations.size());
		for (LoggerConfiguration configuration : configurations) {
			loggers.put(configuration.getName(), new LoggerLevel(configuration));
		}
		return loggers;
	}

	/** @return list of Loggers levels */
	public Set<LoggerLevel> getAllLoggers() {
		return getLoggers(this.loggingSystem.getLoggerConfigurations()).values().stream().collect(Collectors.toSet());
	}

	/** Set level on many loggers.
	 *
	 * @param level
	 *            level
	 * @param loggers
	 *            loggers */
	public void setLoggerLevel(final LogLevel level, final Set<LoggerLevel> loggers) {
		if (!loggers.isEmpty()) {
			configureLogLevel(loggers, level);
			/* Notification */
			notificationService.success(VaadinService.getCurrent().getInstantiator().getI18NProvider().getTranslation("loggers.changeLevel.success", Locale.getDefault(), loggers.size(), level));
		}
	}

	/** Logger Level (app representation logger). */
	@Data
	@ToString
	@EqualsAndHashCode(of = "loggerName")
	public static class LoggerLevel {
		private String loggerName = "";

		private String configuredLevel = "";

		private String effectiveLevel = "";

		public LoggerLevel() {
		}

		public LoggerLevel(final LoggerConfiguration configuration) {
			this.loggerName = configuration.getName();
			this.configuredLevel = getName(configuration.getConfiguredLevel());
			this.effectiveLevel = getName(configuration.getEffectiveLevel());
		}

		private String getName(final LogLevel level) {
			return (level != null ? level.name() : "");
		}
	}

	/** Logging Event class.
	 *
	 * @param <T>
	 *            event object */
	@Data
	@AllArgsConstructor
	public static class LoggingEvent<T> {

		public enum Action {
			CHANGED
		}

		private Action action;

		private T object;
	}
}
