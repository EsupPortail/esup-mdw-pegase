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

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.config.location = src/test/resources/application.properties")
@Import({ RequestParameterUserFilter.class, FlywayCleanMigrateStrategy.class })
@Slf4j
public abstract class AbstractIT {

	protected static final String TEST_USER = "user";

	@LocalServerPort
	private transient int port;

	protected static WebDriver webDriver;

	protected static InetAddress ip = getIP();

	protected static InetAddress getIP() {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress("univ-lorraine.fr", 80));
			return socket.getLocalAddress();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	@BeforeAll
	public static void setup() {
		ChromeOptions chromeOptions = new ChromeOptions().setHeadless(true);

		String seleniumRemoteUrl = System.getenv("SELENIUM_REMOTE_URL");
		if (seleniumRemoteUrl == null) {
			log.info("Propriété SELENIUM_REMOTE_URL non renseignée, utilise le ChromeDriver local.");
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver(chromeOptions);
		} else {
			log.info("Connexion au Selenium distant {}...", seleniumRemoteUrl);
			try {
				webDriver = new RemoteWebDriver(new URL(seleniumRemoteUrl), chromeOptions);
			} catch (MalformedURLException e) {
				e.setStackTrace(new StackTraceElement[] {});
				log.error("Propriété SELENIUM_REMOTE_URL incorrecte : {}", seleniumRemoteUrl, e);
			}
		}
	}

	protected String getUrl(final String route, final String user, final String... authorities) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath(route)
			.scheme("http")
			.host(ip.getHostAddress())
			.port(port)
			.queryParam(RequestParameterUserFilter.USERNAME_PARAMETER, user);
		for (String authority : authorities) {
			builder.queryParam(RequestParameterUserFilter.AUTHORITY_PARAMETER, authority);
		}
		return builder.build().toUriString();
	}

	protected void webDriverGet(final String route, final String... authorities) {
		webDriver.get(getUrl(route, TEST_USER, authorities));
	}

	protected WebDriverWait webDriverWait(final long timeOutInSeconds) {
		return new WebDriverWait(webDriver, timeOutInSeconds);
	}

	@AfterAll
	public static void tear() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
