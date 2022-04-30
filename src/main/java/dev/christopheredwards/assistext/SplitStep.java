package dev.christopheredwards.assistext;

import java.util.regex.Pattern;

/**
 * A text transformation pipeline step that splits the given string into multiple lines.
 * By default, text will be split on the comma character (","). This split string can be
 * specified as either a literal string or as a regular expression.
 */
public class SplitStep implements PipelineStep {

    private String rawSplitString;
    private String lineEnding;
    private boolean useRegex;
    private boolean useWindowsLineEnding;

    /**
     * Class constructor
     */
    public SplitStep() {
        setSplitString(",");
        setUseWindowsLineEnding(false);
        setUseRegex(false);
    }

    public String getSplitString() {
        return rawSplitString;
    }

    public boolean usesWindowsLineEnding() {
        return useWindowsLineEnding;
    }

    public boolean usesRegex() {
        return useRegex;
    }

    /**
     * Set whether the transformation will output a string with Windows-style line endings or not.
     *
     * @param useWindowsLineEnding if true, transformation result will have Windows-style (\r\n) line endings; Unix-style (\n) otherwise
     */
    public void setUseWindowsLineEnding(boolean useWindowsLineEnding) {
        this.useWindowsLineEnding = useWindowsLineEnding;
        if (useWindowsLineEnding) {
            this.lineEnding = "\r\n";
        } else {
            this.lineEnding = "\n";
        }
    }

    /**
     * Specify the string on which the step input will be split.
     *
     * @param splitString a string to replace with an appropriate newline character in the step input
     */
    public void setSplitString(String splitString) {
        this.rawSplitString = splitString;
    }

    /**
     * Set whether the split string will be interpreted as a regular expression or not.
     *
     * @param useRegex if true, output will be split on any string matching the regex split string; otherwise, split string will be interpreted as plain text
     */
    public void setUseRegex(boolean useRegex) {
        this.useRegex = useRegex;
    }

    /**
     * Produce a copy of the given string that is split on a pre-specified substring.
     *
     * @param s the string being split
     * @return a split copy of the string
     */
    @Override
    public String transform(String s) {
        if (s == null) {
            throw new InvalidInputException("null");
        }
        String splitString;
        if (useRegex) {
            splitString = rawSplitString;
        } else {
            splitString = Pattern.quote(rawSplitString);
        }
        return String.join(lineEnding, s.split(splitString));
    }
}
