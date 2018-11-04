package com.alexlew.skemail.expressions.EmailServices.custom;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skemail.types.EmailService;
import org.apache.commons.lang.StringUtils;
import org.bukkit.event.Event;

@Name("IMAP Port of Email Service")
@Description("Returns the imap port of an email service. Can be set in a email service scope")
@Examples({
		"make new email service:",
		"\tset imap port of service to 900"
})
@Since("1.3")

public class ExprIMAPPortOfService extends SimplePropertyExpression<EmailService, Object> {

	static {
		register(ExprIMAPPortOfService.class, Object.class,
				"imap(-|_| )port", "emailservice");
	}

	@Override
	public String convert(EmailService service) {
		return service.getImap_port();
	}

	@Override
	public Class<?>[] acceptChange(final ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
			return new Class[]{String.class};
		}
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, ChangeMode mode) {
		if (delta[0] instanceof String || delta[0] instanceof Integer && StringUtils.isNumeric((String) delta[0])) {
			for (EmailService service : getExpr().getArray(e)) {
				switch (mode) {
					case SET:
						service.setImap_port((String) delta[0]);
						break;
					case DELETE:
						service.setImap_port(null);
						break;
					default:
						break;
				}
			}
		} else {
			System.out.println("[SkEmail] A imap port must to be an integer or a string like \"900\" or 900, and not " + delta[0]);
		}

	}

	@Override
	protected String getPropertyName() {
		return "imap address";
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}



