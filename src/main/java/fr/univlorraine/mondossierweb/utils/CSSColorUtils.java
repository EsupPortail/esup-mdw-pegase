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
package fr.univlorraine.mondossierweb.utils;

import com.helger.css.decl.CSSHSL;
import com.helger.css.decl.CSSRGB;
import com.helger.css.utils.CSSColorHelper;

/**
 * Outils pour la gestion des couleurs CSS.
 * @author Adrien Colson
 */
public final class CSSColorUtils {

	private static final String PATTERN_HEX_COLOR_SHORT = "^#[0-9a-fA-F]{3}$";
	private static final String PATTERN_HEX_COLOR_LONG = "^#[0-9a-fA-F]{6}$";

	private CSSColorUtils() {
	}

	/**
	 * @param  color chaine de caractères
	 * @return       true si la chaine passée est une couleur css supportée
	 */
	public static boolean isSupportedColor(final String color) {
		return color != null && (color.matches(PATTERN_HEX_COLOR_SHORT)
			|| color.matches(PATTERN_HEX_COLOR_LONG)
			|| CSSColorHelper.isRGBColorValue(color)
			|| CSSColorHelper.isHSLColorValue(color));
	}

	/**
	 * @param  color couleur hex, rgb ou hsl
	 * @return       couleur rgb correspondante
	 */
	public static CSSRGB getRGBColor(final String color) {
		if (color.matches(PATTERN_HEX_COLOR_SHORT)) {
			return new CSSRGB(
				Integer.parseInt(color.substring(1, 2).repeat(2), 16),
				Integer.parseInt(color.substring(2, 3).repeat(2), 16),
				Integer.parseInt(color.substring(3).repeat(2), 16));
		}
		if (color.matches(PATTERN_HEX_COLOR_LONG)) {
			return new CSSRGB(
				Integer.parseInt(color.substring(1, 3), 16),
				Integer.parseInt(color.substring(3, 5), 16),
				Integer.parseInt(color.substring(5), 16));
		}
		if (CSSColorHelper.isRGBColorValue(color)) {
			return CSSColorHelper.getParsedRGBColorValue(color);
		}
		if (CSSColorHelper.isHSLColorValue(color)) {
			CSSHSL hslColor = CSSColorHelper.getParsedHSLColorValue(color);
			int[] rgbValues = CSSColorHelper.getHSLAsRGBValue(
				Float.parseFloat(hslColor.getHue()),
				Float.parseFloat(hslColor.getSaturation().replace("%", "")),
				Float.parseFloat(hslColor.getLightness().replace("%", "")));
			return new CSSRGB(rgbValues[0], rgbValues[1], rgbValues[2]);
		}
		throw new IllegalArgumentException(String.format("La couleur %s n'est pas de type hex, rgb ou hsl.", color));
	}

	/**
	 * @param  color   couleur hex, rgb ou hsl
	 * @param  percent taux d'opacité
	 * @return         couleur rgba ou hsla correspondante
	 */
	public static String getAlphaColor(final String color, final float percent) {
		if (color.matches(PATTERN_HEX_COLOR_SHORT)) {
			return CSSColorHelper.getRGBAColorValue(
				Integer.parseInt(color.substring(1, 2).repeat(2), 16),
				Integer.parseInt(color.substring(2, 3).repeat(2), 16),
				Integer.parseInt(color.substring(3).repeat(2), 16),
				percent);
		}
		if (color.matches(PATTERN_HEX_COLOR_LONG)) {
			return CSSColorHelper.getRGBAColorValue(
				Integer.parseInt(color.substring(1, 3), 16),
				Integer.parseInt(color.substring(3, 5), 16),
				Integer.parseInt(color.substring(5), 16),
				percent);
		}
		if (CSSColorHelper.isRGBColorValue(color)) {
			return CSSColorHelper.getParsedRGBColorValue(color).getAsRGBA(percent).getAsCSSString();
		}
		if (CSSColorHelper.isHSLColorValue(color)) {
			return CSSColorHelper.getParsedHSLColorValue(color).getAsHSLA(percent).getAsCSSString();
		}
		throw new IllegalArgumentException(String.format("La couleur %s n'est pas de type hex, rgb ou hsl.", color));
	}

}
