package sr.api.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sr.api.persistence.dao.IUserDao;
import sr.api.persistence.domain.User;

/**
 * @author sercan
 *
 */

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger logger = Logger
			.getLogger(AppUserDetailsService.class);

	@Autowired
	IUserDao iUserDao;

	private org.springframework.security.core.userdetails.User appUserDetails;

	@Override
	public UserDetails loadUserByUsername(String user)
			throws UsernameNotFoundException {

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		User authUser = getUserDetail(user);

		if (authUser == null) {
			logger.info("Auth user is null. Exception is throwing..");
			throw new UsernameNotFoundException("Username not found");
		}

		appUserDetails = new org.springframework.security.core.userdetails.User(
				user, authUser.getPass(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked,
				getAuthorities(authUser.getRole()));

		return appUserDetails;
	}

	public List<GrantedAuthority> getAuthorities(Integer role) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (role.intValue() == 2) {
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		} else if (role.intValue() == 1) {
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authList;
	}


	public User getUserDetail(String username) {
		User user = null;
		try {
			user = iUserDao.findByUserName(username);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return user;
	}

}
