package fr.univlorraine.mondossierweb.ui.component;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;

@Component
@SuppressWarnings("serial")
public abstract class AdaptSizeLayout extends VerticalLayout implements HasDynamicTitle, LocaleChangeObserver, InitializingBean {



	// Largeur pour adaptation mobile
	@Value("${mobile.width}")
	private Integer mobileWidth;

	private Boolean isMobile = null;

	@Override
	public void afterPropertiesSet() {
		// Affichage mobile en fonction de la taille de l'écran
		UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> adaptSize(details.getWindowInnerWidth()));
		UI.getCurrent().getPage().addBrowserWindowResizeListener(event -> adaptSize(event.getWidth()));
	}

	/**
	 * @return si on est en mode mobile
	 */
	protected Boolean getIsMobile() {
		return isMobile != null && isMobile;
	}

	/**
	 * Adapte la taille de l'ecran
	 * @param width
	 */
	private void adaptSize(final int width) {
		if ((isMobile == null || !isMobile) && width <= mobileWidth) {
			isMobile = true;
			adaptSize(true);
		} else if ((isMobile == null || isMobile) && width > mobileWidth) {
			isMobile = false;
			adaptSize(false);
		}
	}

	protected abstract void adaptSize(Boolean isMobile);
}
