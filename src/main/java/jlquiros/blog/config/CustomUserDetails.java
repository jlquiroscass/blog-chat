package jlquiros.blog.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

	private static final long serialVersionUID = 5595224948812084090L;

	/** Identifier of the user. */
	private Long idUser;

	/** First name of the user. */
	private String firstName;

	/** Last name of the user. */
	private String lastName;


	public Long getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	/**
	 * Constructor.
	 * 
	 * @param username
	 * @param password
	 * @param authorities
	 * @param idUser
	 * @param firstName
	 * @param lastName
	 */
	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Long idUser, String firstName, String lastName) {
		super(username, password, authorities);
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
