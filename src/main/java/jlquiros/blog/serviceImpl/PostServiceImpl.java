package jlquiros.blog.serviceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jlquiros.blog.form.PostForm;
import jlquiros.blog.model.Post;
import jlquiros.blog.model.Tag;
import jlquiros.blog.model.TagPK;
import jlquiros.blog.repository.PostRepository;
import jlquiros.blog.repository.UserRepository;
import jlquiros.blog.service.PostService;

@Service
@Qualifier("postService")
public class PostServiceImpl implements PostService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	public boolean post(Post toPost) {
		return true;
	}
	
	public boolean post (PostForm toPost){
		Post post = new Post();
		post.setAuthor(userRepository.findOne(toPost.getAuthor()));
		post.setText(toPost.getText());
		post.setTitle(toPost.getTitle());
		post.setDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		Set<Tag> setTags = new HashSet<Tag>();
		for (String strTag : Arrays.asList(toPost.getTags().split("\\|")))
		{
			Tag tag = new Tag();
			TagPK tagPk = new TagPK();
			tagPk.setTag(strTag);	
			tagPk.setIdPost(post);
			tag.setId(tagPk);
			setTags.add(tag);
			
		}
		post.setTags(setTags);
		postRepository.save(post);
		return true;
	}

}
