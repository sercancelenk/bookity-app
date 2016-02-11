package sr.api.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import sr.api.Util.HashUtil;
import sr.api.Util.Utility;
import sr.api.Util.enums.RoleEnums;
import sr.api.business.service.IMailService;
import sr.api.business.service.IUserService;
import sr.api.persistence.dao.IUserDao;
import sr.api.persistence.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * Created by sercan on 10/02/16.
 */


@Service(value = "userService")
public class UserService implements IUserService {

    @Autowired
    IUserDao iUserDao;

    @Autowired
    IMailService mailService;

    @Override
    public User findByUsername(String username) {
        return iUserDao.findByUserName(username);
    }

    @Override
    public Boolean createUser(String namesurname, String email, String password, RoleEnums role, Locale locale, HttpServletRequest request) {
        String activationCode = HashUtil.generateHashCode(email);
        User newUser = new User();
        newUser.setName(namesurname);
        newUser.setPass(password);
        newUser.setUserName(email);
        newUser.setRole(role.getValue());
        newUser.setActivationCode(activationCode);
        newUser.setStatus(false);
        User user = iUserDao.save(newUser);
        if(user!=null){
            String serverIp = Utility.getServerBaseUrlFromRequest(request);
            String activationUrl = serverIp + "/register/activation.do" + "?c=" + activationCode;
            mailService.sendRegistrationMail(email, activationUrl, locale);
        }
        return user != null ? true : false;
    }

    @Override
    public User checkUserBy(String activationCode) {
        return iUserDao.findByActivationCode(activationCode);
    }

    @Override
    public User updateUser(User user){
        return iUserDao.save(user);
    }


    @Override
    public Integer totalUserCount() {
        List<User> users = (List<User>) iUserDao.findAll();
        return users == null ? 0 : users.size();
    }

    @Override
    public List<User> loadAll(){
        return (List<User>) iUserDao.findAll();
    }
}
