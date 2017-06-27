package jlquiros.blog.custom;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {

	/**
	 * Saves the password recovery token for the user with the given email address.
	 * 
	 * @param email
	 *            Email address of the user.
	 * @param token
	 *            Recovery token.
	 */
	public boolean savePasswordRecoveryToken(String email, String token);

	/**
	 * Checks whether the given password recovery token exists and is active for the given user.
	 * 
	 * @param idUser
	 *            Identifier of the user.
	 * @param token
	 *            Recovery token to check.
	 * 
	 * @return True if the token is valid, false otherwise.
	 */
	public boolean checkPasswordRecoveryToken(Long idUser, String token);

	/**
	 * Saves a new password for the user and clears the recovery token.
	 * 
	 * @param email
	 *            Email of the user.
	 * @param password
	 *            New password (already encrypted!).
	 */
	public void saveNewPasswordAndClearToken(String email, String password);
	
	/**
	 * Saves a new LinkedIn id
	 * 
	 * @param email
	 *            Email of the user.
	 * @param password
	 *            New LinkedIn id (already encrypted!).
	 */	
	public void setLinkedIn_id(String email, String linkedIn_id) ;

}
