package dev.christopheredwards.assistext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * A text transformation pipeline step that sorts the lines of the given string.
 * By default, text will be sorted in ascending order, sensitive to case, and the resulting
 * sorted string will be given Linux-style line endings. These options can be individually
 * configured, however.
 */
public class SortStep implements PipelineStep {

    private String lineEnding;
    private boolean sortDescending;
    private boolean caseInsensitive;
    private Comparator<String> comparator;

    /**
     * Class constructor
     */
    public SortStep() {
        lineEnding = "\n";
        sortDescending = false;
        caseInsensitive = false;
        setComparator();
    }

    /**
     * Set whether the transformation will output a string with Windows-style line endings or not.
     *
     * @param useWindowsLineEnding if true, transformation result will have Windows-style (\r\n) line endings; Linux-style (\n) otherwise
     */
    public void setUseWindowsLineEnding(boolean useWindowsLineEnding) {
        if (useWindowsLineEnding) {
            this.lineEnding = "\r\n";
        } else {
            this.lineEnding = "\n";
        }
    }

    /**
     * Set whether the output will be sorted in descending order or not.
     *
     * @param sortDescending if true, result will be sorted in descending order; ascending order otherwise
     */
    public void setSortDescending(boolean sortDescending) {
        this.sortDescending = sortDescending;
        setComparator();
    }

    /**
     * Set whether the output will be sorted in a case-insensitive manner or not.
     *
     * @param caseInsensitive if true, output will be sorted case-insensitively; case-sensitively otherwise
     */
    public void setCaseInsensitive(boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
        setComparator();
    }

    /**
     * Produce a sorted copy of the given string.
     *
     * @param s the string being sorted
     * @return a sorted copy of the string
     */
    @Override
    public String transform(String s) {
        if (s == null) {
            throw new InvalidInputException("null");
        }
        return Arrays.stream(s.split("\\R")).sorted(comparator).collect(Collectors.joining(lineEnding));
    }

    private void setComparator() {
        if (sortDescending) {
            if (caseInsensitive) {
                comparator = String.CASE_INSENSITIVE_ORDER.reversed();
            } else {
                comparator = Comparator.reverseOrder();
            }
        } else {
            if (caseInsensitive) {
                comparator = String.CASE_INSENSITIVE_ORDER;
            } else {
                comparator = Comparator.naturalOrder();
            }
        }
    }
}
