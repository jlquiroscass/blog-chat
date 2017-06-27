package jlquiros.blog.custom;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import jlquiros.blog.custom.*;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


	@Transactional
	public boolean savePasswordRecoveryToken(String email, String token) {
		// @formatter:off
		String sql = "UPDATE user SET recovery_token = ? WHERE email = ?";
		// @formatter:on

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, token);
		query.setParameter(2, email);

		return (query.executeUpdate() == 1);
	}

	public boolean checkPasswordRecoveryToken(Long idUser, String token) {
		// @formatter:off
		String sql = "SELECT COUNT(*) FROM user WHERE id = ? AND recovery_token = ?";
		// @formatter:on

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, idUser);
		query.setParameter(2, token);

		BigInteger count = (BigInteger) query.getSingleResult();

		return (count.intValue() > 0);
	}

	@Transactional
	public void saveNewPasswordAndClearToken(String email, String password) {
		// @formatter:off
		String sql = "UPDATE user SET password = ?, recovery_token = NULL WHERE email = ?";
		// @formatter:on

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, password);
		query.setParameter(2, email);

		query.executeUpdate();
	}
	
	
	@Transactional
	public void setLinkedIn_id(String email, String linkedIn_id) {
		// @formatter:off
		String sql = "UPDATE user SET linkedin_id = ? WHERE email = ?";
		// @formatter:on
		
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, linkedIn_id);
		query.setParameter(2, email);

		query.executeUpdate();
	}

	
	@Transactional
	public void updateUserProfilePhoto(Integer idUser, String profilePhotoId)  {
		// @formatter:off
		String sql = "UPDATE user SET photo_id = ? where id = ?";
		// @formatter:on

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, profilePhotoId);
		query.setParameter(2, idUser);

		query.executeUpdate();

	}

	
	@Transactional
	public void updateUserCoverPhoto(Integer idUser, String profileCoverId) {
		// @formatter:off
		String sql = "UPDATE user SET cover_id = ? where id = ?";
		// @formatter:on

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, profileCoverId);
		query.setParameter(2, idUser);

		query.executeUpdate();

	}

}
