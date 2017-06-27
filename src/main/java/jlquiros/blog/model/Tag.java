package jlquiros.blog.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
@NamedQuery(name = "tag.findAll", query = "SELECT r FROM Tag r")
public class Tag implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	TagPK id;

	public TagPK getId() {
		return id;
	}

	public void setId(TagPK id) {
		this.id = id;
	}
	
	
	
	
}