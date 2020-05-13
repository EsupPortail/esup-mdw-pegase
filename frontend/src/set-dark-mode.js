window.setDarkMode = function(darkMode) {
	const themeAttribute = document.body.getAttribute('theme') || '';
	const themeAttributeArray = themeAttribute.split(' ').filter(theme => theme != '');
	const isDarkModeSet = themeAttributeArray.includes('dark');
	if (darkMode) {
		if (!isDarkModeSet) {
			document.body.setAttribute('theme', themeAttributeArray.join(' ') + ' dark');
		}
	} else {
		if (isDarkModeSet) {
			document.body.setAttribute('theme', themeAttributeArray.filter(theme => theme != 'dark').join(' '));
		}
	}
}

const prefersDarkMode = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
if (prefersDarkMode) {
	window.setDarkMode(true);
}
