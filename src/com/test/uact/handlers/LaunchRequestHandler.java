package com.test.uact.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to UAccounting. choose one module by saying, connect to the x y z module";
        String repromptText = "Please select any module by saying, connect to the x y z module.";
        return input.getResponseBuilder()
                .withSimpleCard("UAccounting", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .build();
    }
}
