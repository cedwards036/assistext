package dev.christopheredwards.assistext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SplitStepTest {

    @Test
    void throws_error_when_transforming_null_input() {
        SplitStep splitStep = new SplitStep();
        assertThrows(PipelineStep.InvalidInputException.class, () -> splitStep.transform(null));
    }

    @Test
    void does_nothing_to_empty_string() {
        SplitStep splitStep = new SplitStep();
        assertEquals("", splitStep.transform(""));
    }

    @Test
    void splits_on_comma_by_default() {
        SplitStep splitStep = new SplitStep();
        String input = "a,b,c";
        assertEquals("a\nb\nc", splitStep.transform(input));
    }

    @Test
    void splits_on_user_specified_string() {
        SplitStep splitStep = new SplitStep();
        splitStep.setSplitString("SP");
        String input = "aSPbSPc";
        assertEquals("a\nb\nc", splitStep.transform(input));
    }

    @Test
    void outputs_windows_line_endings_if_specified() {
        SplitStep splitStep = new SplitStep();
        splitStep.setUseWindowsLineEnding(true);
        String input = "a,b,c";
        assertEquals("a\r\nb\r\nc", splitStep.transform(input));
    }

    @Test
    void windows_line_ending_setting_does_not_override_any_existing_line_ending_characters_in_input() {
        SplitStep splitStep = new SplitStep();
        splitStep.setUseWindowsLineEnding(true);
        String input = "a\n1,b\n2,c\n3";
        assertEquals("a\n1\r\nb\n2\r\nc\n3", splitStep.transform(input));
    }

    @Test
    void split_string_is_interpreted_as_regex_if_option_is_set() {
        SplitStep splitStep = new SplitStep();
        splitStep.setUseRegex(true);
        splitStep.setSplitString("[,;]");
        String input = "a,b;c";
        assertEquals("a\nb\nc", splitStep.transform(input));
    }
}
