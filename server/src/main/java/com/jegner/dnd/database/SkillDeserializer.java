package com.jegner.dnd.database;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.predefined.Skill;
import com.jegner.dnd.utility.GameEntity;

@SuppressWarnings("serial")
public class SkillDeserializer extends StdDeserializer<Skill> {

	public SkillDeserializer() {
		this(null);
	}

	protected SkillDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Skill deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		// Get GameEntity
		JsonNode gameEntityNode = node.get("gameEntity");
		String gameEntityName = gameEntityNode.get("name").asText();
		String gameEntityDescription = gameEntityNode.get("description").asText();
		String gameEntityRefUrl = gameEntityNode.get("referenceURL").asText();

		GameEntity gameEntity = new GameEntity();
		gameEntity.setName(gameEntityName);
		gameEntity.setDescription(gameEntityDescription);
		gameEntity.setReferenceURL(new URL(gameEntityRefUrl));

		// Get ability score
		String abilityScoreName = node.get("abilityModifier").asText();
		AbilityScore abilityScore = AbilityScore.findAbilityScoreByName(abilityScoreName);

		// Make Skill instance
		Skill skill = new Skill();
		skill.setGameEntity(gameEntity);
		skill.setAbilityModifier(abilityScore);

		return skill;
	}

}
