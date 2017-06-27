package jlquiros.blog.service;

import org.springframework.stereotype.Service;

import jlquiros.blog.form.PostForm;
import jlquiros.blog.model.Post;

@Service
public interface PostService {
	
	public boolean post (Post toPost);
	
	public boolean post (PostForm toPost);

}
