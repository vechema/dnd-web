package com.jegner.dnd.utility;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	@OneToMany(cascade = CascadeType.ALL)
	private List<Modify> modifys;

	public GameEntity() {
		modifys = new ArrayList<>();
	}

	public void setModify(Modify dexMod) {
		modifys = new ArrayList<>(Arrays.asList(dexMod));
	}

	public void addModify(Modify weaponMagicModify) {
		modifys.add(weaponMagicModify);
	}

}
