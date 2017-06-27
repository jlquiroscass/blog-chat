package jlquiros.blog.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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