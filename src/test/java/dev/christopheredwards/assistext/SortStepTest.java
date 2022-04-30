package dev.christopheredwards.assistext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SortStepTest {

    @Test
    void throws_error_when_transforming_null_input() {
        SortStep sortStep = new SortStep();
        assertThrows(PipelineStep.InvalidInputException.class, () -> sortStep.transform(null));
    }

    @Test
    void does_nothing_to_empty_string() {
        SortStep sortStep = new SortStep();
        assertEquals("", sortStep.transform(""));
    }

    @Test
    void does_nothing_to_single_line_string() {
        SortStep sortStep = new SortStep();
        String input = "I'm a string with only one line.";
        assertEquals("I'm a string with only one line.", sortStep.transform(input));
    }

    @Test
    void sorts_multiline_string_in_ascending_alphabetical_order_by_default() {
        SortStep sortStep = new SortStep();
        String unixInput = "d\nc\nb\na";
        assertEquals("a\nb\nc\nd", sortStep.transform(unixInput));
        String windowsInput = "d\r\nc\r\nb\r\na";
        assertEquals("a\nb\nc\nd", sortStep.transform(windowsInput));
    }

    @Test
    void outputs_windows_line_endings_if_specified() {
        SortStep sortStep = new SortStep();
        sortStep.setUseWindowsLineEnding(true);
        String input = "d\nc\nb\na";
        assertEquals("a\r\nb\r\nc\r\nd", sortStep.transform(input));
    }

    @Test
    void sorts_in_reverse_order_if_specified() {
        SortStep sortStep = new SortStep();
        sortStep.setSortDescending(true);
        String input = "a\nb\nc\nd";
        assertEquals("d\nc\nb\na", sortStep.transform(input));
    }

    @Test
    void sorts_insensitive_to_case_if_specified() {
        SortStep sortStep = new SortStep();
        sortStep.setCaseInsensitive(true);
        String input = "D\nc\nB\na";
        assertEquals("a\nB\nc\nD", sortStep.transform(input));
    }

    @Test
    void sorts_insensitive_to_case_and_reversed_if_both_are_specified() {
        SortStep sortStep = new SortStep();
        sortStep.setCaseInsensitive(true);
        sortStep.setSortDescending(true);
        String input = "a\nB\nc\nD";
        assertEquals("D\nc\nB\na", sortStep.transform(input));
    }
}
