package dev.christopheredwards.assistext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JoinStepTest {

    @Test
    void throws_error_when_transforming_null_input() {
        JoinStep joinStep = new JoinStep();
        assertThrows(PipelineStep.InvalidInputException.class, () -> joinStep.transform(null));
    }

    @Test
    void does_nothing_to_empty_string() {
        JoinStep joinStep = new JoinStep();
        assertEquals("", joinStep.transform(""));
    }

    @Test
    void does_nothing_to_single_line_string() {
        JoinStep joinStep = new JoinStep();
        String input = "I'm a string with only one line.";
        assertEquals("I'm a string with only one line.", joinStep.transform(input));
    }

    @Test
    void joins_multiline_string_with_no_separator_by_default() {
        JoinStep joinStep = new JoinStep();
        String input = "a\nb\r\nc";
        assertEquals("abc", joinStep.transform(input));
    }

    @Test
    void joins_multiline_string_with_specified_separator() {
        JoinStep joinStep = new JoinStep();
        joinStep.setSeparator("---~");
        String input = "a\nb\r\nc";
        assertEquals("a---~b---~c", joinStep.transform(input));
    }

    @Test
    void null_separator_is_treated_as_empty_string() {
        JoinStep joinStep = new JoinStep();
        joinStep.setSeparator(null);
        String input = "a\nb\r\nc";
        assertEquals("abc", joinStep.transform(input));
    }

    @Test
    void includes_non_trailing_empty_lines_in_output_if_option_is_specified() {
        JoinStep joinStep = new JoinStep();
        joinStep.setSeparator(",");
        joinStep.setIncludeEmptyLines(true);
        String input = "a\n\n\n\nb\nc\n\n\n\n";
        assertEquals("a,,,,b,c", joinStep.transform(input));
    }

    @Test
    void ignores_empty_lines_in_output_if_option_is_not_specified() {
        JoinStep joinStep = new JoinStep();
        joinStep.setSeparator(",");
        String input = "a\n\n\n\nb\nc\n\n\n\n";
        assertEquals("a,b,c", joinStep.transform(input));
    }
}
