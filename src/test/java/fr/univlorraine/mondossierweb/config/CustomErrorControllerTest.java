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
package fr.univlorraine.mondossierweb.config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tests unitaires pour CustomErrorController.
 */
@ExtendWith(MockitoExtension.class)
class CustomErrorControllerTest {

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private CustomErrorController errorController;

	@BeforeEach
	void setUp() {
		errorController = new CustomErrorController();
	}

	@Test
	void handleError_shouldReturnErrorRoute_whenStatusCodeIsPresent() {
		when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(404);
		when(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).thenReturn("Not Found");
		when(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).thenReturn(null);

		String result = errorController.handleError(request);

		assertEquals("redirect:/erreur", result);
	}

	@Test
	void handleError_shouldReturnErrorRoute_whenStatusCodeIsNull() {
		when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(null);
		when(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).thenReturn(null);
		when(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).thenReturn(null);

		String result = errorController.handleError(request);

		assertEquals("redirect:/erreur", result);
	}

	@Test
	void handleError_shouldUseDefaultStatusCode_whenStatusCodeIsNull() {
		when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(null);
		when(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).thenReturn("Internal Error");
		when(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).thenReturn(new RuntimeException("Test"));

		String result = errorController.handleError(request);

		assertEquals("redirect:/erreur", result);
	}

	@Test
	void handleError_shouldReturnErrorRoute_whenStatusCodeIs500() {
		when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());
		when(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).thenReturn("Internal Server Error");
		when(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).thenReturn(new RuntimeException("Test"));

		String result = errorController.handleError(request);

		assertEquals("redirect:/erreur", result);
	}

	@Test
	void handleError_shouldReturnErrorRoute_whenStatusCodeIs403() {
		when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(HttpStatus.FORBIDDEN.value());
		when(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).thenReturn("Forbidden");
		when(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).thenReturn(null);

		String result = errorController.handleError(request);

		assertEquals("redirect:/erreur", result);
	}

	@Test
	void handleError_shouldHandleNonIntegerStatusCode() {
		when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn("404");
		when(request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).thenReturn("Not Found");
		when(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION)).thenReturn(null);

		String result = errorController.handleError(request);

		assertEquals("redirect:/erreur", result);
	}

}
