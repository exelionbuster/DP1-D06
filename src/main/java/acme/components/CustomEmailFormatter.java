
package acme.components;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.Formatter;

import acme.datatypes.CustomEmail;
import acme.framework.helpers.MessageHelper;
import acme.framework.helpers.StringHelper;

public class CustomEmailFormatter implements Formatter<CustomEmail> {

	public CustomEmailFormatter() {
		super();
	}

	@Override
	public String print(final CustomEmail object, final Locale locale) {
		assert object != null;
		assert locale != null;

		String res;
		String emailText, displayName;

		if (object.getDisplayName() == "") {
			emailText = String.format("%s", object.getEmail());
			res = String.format("%s", emailText);
		} else {
			displayName = String.format("%s", object.getDisplayName());
			emailText = String.format("%s", object.getEmail());
			res = String.format("%s <%s>", displayName, emailText);
		}

		return res;
	}

	@Override
	public CustomEmail parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;

		CustomEmail res;
		String displayName, email, errorMessage, emailRegex, customEmailRegex, displayNameRegex;
		Pattern pattern;
		Matcher matcher;

		emailRegex = "([a-zA-Z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)";
		displayNameRegex = ".{1,} ";
		customEmailRegex = String.format("^((?<E>%1$s))|((?<DN>%2$s)\\<(?<EN>%1$s)\\>)$", emailRegex, displayNameRegex);

		pattern = Pattern.compile(customEmailRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcher = pattern.matcher(text);

		if (!matcher.find()) {
			errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
			throw new ParseException(errorMessage, 0);
		} else {
			displayName = matcher.group("DN").trim();
			if (displayName != "") {
				email = matcher.group("EN");
			} else {
				email = matcher.group("E");
			}

			res = new CustomEmail();
			res.setDisplayName(displayName);
			res.setEmail(email);
		}

		return res;
	}

}
