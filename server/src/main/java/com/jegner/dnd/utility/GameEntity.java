package com.jegner.dnd.utility;

import java.net.URL;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.modify.Modify;

import lombok.Data;

@Entity
@Data
public class GameEntity {

	@GeneratedValue
	@Id
	private Long id;
	private String name;
	@Column(length = 1000)
	private String description;
	private URL referenceURL;

	@OneToOne(cascade = CascadeType.ALL)
	private Modify modify;

}
