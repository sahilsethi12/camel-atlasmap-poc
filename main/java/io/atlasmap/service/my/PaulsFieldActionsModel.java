package io.atlasmap.service.my;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.atlasmap.v2.Action;
import io.atlasmap.v2.AtlasActionProperty;
import io.atlasmap.v2.FieldType;

import java.io.Serializable;

public class PaulsFieldActionsModel extends Action implements Serializable {

	private static final long serialVersionUID = 1L;
	private String paulsParam = "";
	
	
	@JsonPropertyDescription("Paul's custom field action parameter to display")
	@AtlasActionProperty(title = "Paul's custom field action string parameter", type = FieldType.STRING)
	public void setPaulsParam(String value) {
		paulsParam = value;
	}

	public String getPaulsParam() {
		return paulsParam;
	}
}
