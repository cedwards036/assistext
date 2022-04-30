package dev.christopheredwards.assistext;

import java.util.Arrays;

/**
 * A text transformation pipeline step that joins the lines of the given string.
 * By default, text will be joined with no separator (i.e. the empty string).
 */
public class JoinStep implements PipelineStep {

    private String separator;
    private boolean includeEmptyLines;

    /**
     * Class constructor
     */
    public JoinStep() {
        setSeparator("");
        setIncludeEmptyLines(false);
    }

    /**
     * Specify the string that will be used as the separator/delimiter for the join operation.
     *
     * @param separator a string used to separate the transformed strings lines after joining
     */
    public void setSeparator(String separator) {
        if (separator == null) {
            separator = "";
        }
        this.separator = separator;
    }

    public void setIncludeEmptyLines(boolean includeEmptyLines) {
        this.includeEmptyLines = includeEmptyLines;
    }

    /**
     * Produce a copy of the given string that is joined on a pre-specified separator.
     *
     * @param s the string being joined
     * @return a joined copy of the string
     */
    @Override
    public String transform(String s) {
        if (s == null) {
            throw new InvalidInputException("null");
        }
        String[] splitLines = s.split("\\R", 0);
        if (!includeEmptyLines) {
            splitLines = Arrays.stream(splitLines).filter(str -> !str.isEmpty()).toArray(String[]::new);
        }
        return String.join(separator, splitLines);
    }
}
