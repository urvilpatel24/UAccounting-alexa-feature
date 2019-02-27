package com.test.uact.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class ModuleNameIntent  implements RequestHandler {
	
	public static final String MODULE_KEY = "MODULE";
    public static final String MODULE_SLOT = "module";
	
	 	@Override
	    public boolean canHandle(HandlerInput input) {
	        return input.matches(intentName("ModuleNameIntent"));
	    }

	    @Override
	    public Optional<Response> handle(HandlerInput input) {
	        
	    	Request request = input.getRequestEnvelope().getRequest();
	        IntentRequest intentRequest = (IntentRequest) request;
	        Intent intent = intentRequest.getIntent();
	        Map<String, Slot> slots = intent.getSlots();

	        Slot moduleNameSlot = slots.get(MODULE_SLOT);
	        String speechText, repromptText;

	        if(moduleNameSlot != null
	            && moduleNameSlot.getResolutions() != null
	            && moduleNameSlot.getResolutions().toString().contains("ER_SUCCESS_MATCH")) 
	        {
	        	
	            String moduleName = moduleNameSlot.getValue();
	            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(MODULE_KEY, moduleName));
	            
	            if(moduleName.equals("customers") || moduleName.equals("customer")) 
	            {
	            	speechText = String.format("Okay let's continue with %s module. You can ask me anything about %s like, ", moduleName,moduleName);
	            	speechText += "\n tell me contact number of urvil";
	            	repromptText = "You can ask me your favorite color by saying, what's my favorite color?";
	            }
	            else 
	            {
	            	speechText = "Right now we are only supporting customer module, so you can say connect to customer module.";
		            repromptText = "I'm not sure about this module, you can say connect to customer module.";	        
		        }
	        } 
	        else 
	        {
	            speechText = "Please provide a valid module name" + "Please try again.";
	            repromptText = "I'm not sure about this module, you can tell me connect to x y z module to connect any module.";	        
	        }

	        return input.getResponseBuilder()
	                .withSimpleCard("UAccounting", speechText)
	                .withSpeech(speechText)
	                .withReprompt(repromptText)
	                .withShouldEndSession(false)
	                .build();
	    }

}
