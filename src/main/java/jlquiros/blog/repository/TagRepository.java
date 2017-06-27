package jlquiros.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jlquiros.blog.model.Post;
import jlquiros.blog.model.Tag;
import jlquiros.blog.model.TagPK;

@Repository
public interface TagRepository extends JpaRepository<Tag, TagPK>{

	public List<Tag> findAll();
	
	Post findByIdIdPost(Long id_post);


}