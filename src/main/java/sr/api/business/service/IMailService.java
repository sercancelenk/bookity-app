package sr.api.business.service;

import java.util.Locale;

/**
 * Created by sercan on 10/02/16.
 */
public interface IMailService {
    void sendRegistrationMail(String email,String activationUrl, Locale locale);
}
