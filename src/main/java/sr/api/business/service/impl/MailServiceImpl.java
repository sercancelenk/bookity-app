package sr.api.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sr.api.Util.MailUtil;
import sr.api.business.service.IMailService;

import java.util.Locale;

/**
 * Created by sercan on 10/02/16.
 */

@Service(value = "mailService")
public class MailServiceImpl implements IMailService {

    @Autowired
    MailUtil mailUtil;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    @Async
    public void sendRegistrationMail(String email, String activationUrl, Locale locale) {
        final Context ctx = new Context(locale);
        ctx.setVariable("activationLink", activationUrl);
        String htmlContent = null;
        if( locale.toString().equals("tr"))
            htmlContent = this.templateEngine.process("email-confirm-register.html", ctx);
        else
            htmlContent = this.templateEngine.process("email-confirm-register-en.html", ctx);


        mailUtil.setTo(email);
        mailUtil.setSubject("Bookity Account Activation");
        mailUtil.setContent(htmlContent);
        mailUtil.generateAndSendEmail();
    }
}
