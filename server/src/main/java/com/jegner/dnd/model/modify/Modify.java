package com.jegner.dnd.model.modify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
/**
 * An object that is modified by something else
 * 
 * @author Jo
 *
 */
public class Modify {

	@GeneratedValue
	@Id
	private Long id;
	private int base;
	/**
	 * Field that is modified (AC, HP, etc)
	 */
	private ModifyField modifyField;
	/**
	 * What fields this modify affects
	 */
	@ElementCollection
	private List<ModifyField> fieldsIModify;
	/**
	 * What fields affects this modify -> Limit
	 */
	@ElementCollection
	private Map<ModifyField, Integer> fieldsThatModifyMe;

	private ModifyOperation modifyOperation;

	public Modify() {
		fieldsIModify = new ArrayList<>();
		fieldsThatModifyMe = new HashMap<>();
		modifyOperation = ModifyOperation.SUM;
		base = 0;
	}

	public void addFieldThatModifyMe(ModifyField modifyField, int limit) {
		fieldsThatModifyMe.put(modifyField, limit);
	}

	public void addFieldThatModifyMe(ModifyField modifyField) {
		addFieldThatModifyMe(modifyField, Integer.MAX_VALUE);
	}

	public void setFieldIModify(ModifyField modifyField) {
		setFieldsIModify(Arrays.asList(modifyField));
	}

	public int getFieldThatModifiesMeAmount(CharacterModifySystem characterModifySystem) {
		int result = 0;
		for (Modify modify : characterModifySystem.getModifys()) {
			if (fieldsThatModifyMe.containsKey(modify.getModifyField())) {
				int limit = fieldsThatModifyMe.get(modify.getModifyField());
				int recursiveBase = modify.getFieldThatModifiesMeAmount(characterModifySystem);
				switch (modifyOperation) {
					case MAX:
						result = Math.max(result, Math.min(limit, recursiveBase));
						break;
					case SUM:
						result += Math.min(limit, recursiveBase);
						break;
					default:
						break;
				}
			}
		}
		return result + base;
	}
}
