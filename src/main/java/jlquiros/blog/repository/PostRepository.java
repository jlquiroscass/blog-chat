package jlquiros.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jlquiros.blog.model.Post;
import jlquiros.blog.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	public List<Post> findAllByOrderByDateDesc();
	
	Post findById(Long id);
	
	public List<Post> findByAuthor(User author);
	
	@Query("SELECT p FROM Post p WHERE p.author.id = ?1 order by p.date desc ")
	public List<Post> findByAuthorId(Long author);
	


}