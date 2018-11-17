package com.alexlew.skemail.expressions.EmailServices.Yahoo;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skemail.types.EmailService;
import org.bukkit.event.Event;

@Name("Yahoo Au service")
@Description("Returns the yahoo au service as EmailService.")
@Examples({
        "set {_service} to new yahoo au service"
})
@Since("1.4")

public class YahooAu extends SimpleExpression<EmailService> {

    static {
        Skript.registerExpression(YahooAu.class, EmailService.class, ExpressionType.SIMPLE,
                "[new] yahoo (australian|au) [(service|session)]");
    }

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        return true;
    }

    @Override
    protected EmailService[] get( Event e ) {
        EmailService service = new EmailService();
        service.setName("yahoo au");
        service.setSmtp_address("smtp.mail.yahoo.au");
        service.setSmtp_port("465");
        service.setImap_address("imap.mail.yahoo.au");
        service.setImap_port("993");
        EmailService.lastEmailService = service;
        return new EmailService[] {service};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends EmailService> getReturnType() {
        return EmailService.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "yahoo au";
    }

}