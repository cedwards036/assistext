package dev.christopheredwards.assistext;

/**
 * A text transformation pipeline step that joins the lines of the given string.
 * By default, text will be joined with no separator (i.e. the empty string).
 */
public class JoinStep implements PipelineStep {

    private String separator;

    /**
     * Class constructor
     */
    public JoinStep() {
        separator = "";
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

    /**
     * Produce a copy of the given string that is join on a pre-specified separator.
     *
     * @param s the string being joined
     * @return a joined copy of the string
     */
    @Override
    public String transform(String s) {
        if (s == null) {
            throw new InvalidInputException("null");
        }
        return String.join(separator, s.split("\\R"));
    }
}
