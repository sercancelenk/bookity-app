package sr.api.business.service;


import sr.api.Util.enums.RoleEnums;
import sr.api.persistence.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * @author sercan
 *
 */
public interface IUserService {
    User findByUsername(String username);
    Boolean createUser(String namesurname, String email, String password, RoleEnums role, Locale locale, HttpServletRequest request);
    User checkUserBy(String activationCode);
    User updateUser(User user);
    Integer totalUserCount();

    List<User> loadAll();
}
