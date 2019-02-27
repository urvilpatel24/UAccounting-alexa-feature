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

public class TellMeIntent  implements RequestHandler {
	
	public static final String INFO_KEY = "INFO";
    public static final String INFO_SLOT = "info";
	
    public static final String NAME_KEY = "NAME";
    public static final String NAME_SLOT = "name";
	
	@Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("TellMeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        
    	Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot infoSlot = slots.get(INFO_SLOT);
        Slot nameSlot = slots.get(NAME_SLOT);
        String speechText = "", repromptText = "";

        if(infoSlot != null
         /*   && infoSlot.getResolutions() != null
            && infoSlot.getResolutions().toString().contains("ER_SUCCESS_MATCH")*/
            && nameSlot != null
         /*   && nameSlot.getResolutions() != null
            && nameSlot.getResolutions().toString().contains("ER_SUCCESS_MATCH")*/) 
        {
            String name = nameSlot.getValue();
            String info = infoSlot.getValue();
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(INFO_KEY,info));
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(NAME_KEY, name));
            
            if(info.equals("contact number") || info.equals("mobile number") || info.equals("contactnumber") || info.equals("mobilenumber")) 
            {
            	speechText = String.format("Contact Number of %s is 7 3 5 9 6 0 2 2 0 2", name);
            	repromptText = "repromt";
            }
            else if(info.equals("gstnumber") || info.equals("gstnumber") || info.equals("gst number") || info.equals("gst number")) 
		    {
            	speechText = String.format("GST Number of %s is 2 4 A Z 1 2 3 4 5 6 A B C D E", name);
            	repromptText = "repromt";
		    }
            else if(info.equals("location") || info.equals("city")) 
		    {
            	speechText = String.format("%s is living in Ahmedabad", name);
            	repromptText = "repromt";
		    }
            else if(info.equals("mail address") || info.equals("email address") || info.equals("email") || info.equals("mail id") || info.equals("mail")) 
            {
            	speechText = String.format("Email address of %s is demo@gmail.com", name);
            	repromptText = "repromt";
            }
            else if(info.equals("current balance") || info.equals("balance")) 
            {
            	speechText = String.format("Balance of %s is 500 ruppess", name);
            	repromptText = "repromt";
            }
        } 
        else 
        {
            speechText = "Please ask for valid information";
            repromptText = "I'm not sure about this information Please try again.";	        
        }

        return input.getResponseBuilder()
                .withSimpleCard("UAccounting", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }

}
