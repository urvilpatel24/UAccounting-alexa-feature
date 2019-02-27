package com.test.uact;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.test.uact.handlers.CancelandStopIntentHandler;
import com.test.uact.handlers.FallbackIntentHandler;
import com.test.uact.handlers.HelpIntentHandler;
import com.test.uact.handlers.LaunchRequestHandler;
import com.test.uact.handlers.ModuleNameIntent;
import com.test.uact.handlers.SessionEndedRequestHandler;
import com.test.uact.handlers.TellMeIntent;

public class UactStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard().addRequestHandlers(
        				new ModuleNameIntent(),
        				new TellMeIntent(),
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public UactStreamHandler() {
        super(getSkill());
    }

}
