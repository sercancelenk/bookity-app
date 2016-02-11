package sr.api.persistence.domain;

import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author sercan
 *
 */

@Document(collection="user")
public class User extends Base{
	private String name;
	private String userName;
	private String pass;
	private String activationCode;
	private int role;
	private Boolean status;
	
	public User(){
		super();
	}
	
	

	public User(String name, String userName, String pass, int role, String activationCode, Boolean status) {
		super();
		this.name = name;
		this.userName = userName;
		this.pass = pass;
		this.role = role;
		this.activationCode = activationCode;
		this.status = status;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
