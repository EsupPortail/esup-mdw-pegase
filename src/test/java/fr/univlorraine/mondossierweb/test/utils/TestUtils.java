package fr.univlorraine.mondossierweb.test.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vaadin.flow.component.textfield.TextField;

import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import lombok.extern.slf4j.Slf4j;

/** Tests Utils.
*
* @author Charlie Dubois */
@ExtendWith(SpringExtension.class)
@Slf4j
public class TestUtils {
	
	/** Teste de la class Utils. */
	@Test
	void testUtils() {
		TextField tf = new TextField();
		CmpUtils.formatTextField(tf);
		assertThat(tf.getStyle().get(CSSColorUtils.MARGIN), is(equalTo("0em")));
	}

}
