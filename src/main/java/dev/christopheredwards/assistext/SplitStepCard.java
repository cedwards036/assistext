package dev.christopheredwards.assistext;

public class SplitStepCard extends StepCard {
    public SplitStepCard(SplitStep step) {
        super("Split");

        String splitStringKey = "Split string";
        if (step.usesRegex()) {
            splitStringKey += " (regex)";
        } else {
            splitStringKey += " (plain text)";
        }
        addKeyValueAttribute(splitStringKey, step.getSplitString());

        if (step.usesWindowsLineEnding()) {
            addValueAttribute("Output uses Windows line endings");
        } else {
            addValueAttribute("Output uses Unix line endings");
        }
    }
}
