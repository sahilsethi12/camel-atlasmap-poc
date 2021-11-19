package io.atlasmap.service.my;

import io.atlasmap.spi.AtlasActionProcessor;
import io.atlasmap.spi.AtlasFieldAction;



public class PaulsFieldActions implements AtlasFieldAction {

	
	@AtlasActionProcessor
	public static String concur(PaulsFieldActionsModel action, String userInput) {
		return "concur " + action.getPaulsParam() + "payload :  "+ userInput;
	}

}
