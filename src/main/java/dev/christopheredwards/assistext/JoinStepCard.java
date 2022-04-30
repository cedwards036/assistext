package dev.christopheredwards.assistext;

public class JoinStepCard extends StepCard {
    public JoinStepCard(JoinStep step) {
        super("Join");

        addKeyValueAttribute("Separator", step.getSeparator());

        if (step.includesEmptyLines()) {
            addValueAttribute("Includes empty lines");
        } else {
            addValueAttribute("Doesn't include empty lines");
        }
    }
}
