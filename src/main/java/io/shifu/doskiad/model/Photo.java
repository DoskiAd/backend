package io.shifu.doskiad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="photos", uniqueConstraints = 
    @UniqueConstraint(columnNames={"file_name"}))
public class Photo {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="file_name")
	private String file;
	
	@Column(name="item_id")
	@JsonIgnore
	private Long item;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public Long getItem() {
		return item;
	}
	public void setItem(Long item) {
		this.item = item;
	}

}
