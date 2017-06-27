package jlquiros.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TagPK  implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_post")
	private Post idPost;
	
	@Column(name = "tag")
	private String tag;

	public Post getIdPost() {
		return idPost;
	}

	public void setIdPost(Post idPost) {
		this.idPost = idPost;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
	
}
