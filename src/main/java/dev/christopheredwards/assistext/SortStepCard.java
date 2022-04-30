package dev.christopheredwards.assistext;

public class SortStepCard extends StepCard {
    public SortStepCard(SortStep step) {
        super("Sort");

        if (step.sortsDescending()) {
            addValueAttribute("Descending");
        } else {
            addValueAttribute("Ascending");
        }

        if (step.isCaseInsensitive()) {
            addValueAttribute("Case insensitive");
        } else {
            addValueAttribute("Case sensitive");
        }

        if (step.usesWindowsLineEnding()) {
            addValueAttribute("Output uses Windows line endings");
        } else {
            addValueAttribute("Output uses Unix line endings");
        }
    }
}
