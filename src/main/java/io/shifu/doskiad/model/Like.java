package io.shifu.doskiad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="likes")
public class Like {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	

	@Column(name="item_id")
	private Long item;
	
	@Column(name="user_id")
	private Long user;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

}
