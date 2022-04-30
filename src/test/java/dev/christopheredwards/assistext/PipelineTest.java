package dev.christopheredwards.assistext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PipelineTest {

    @Test
    void throws_error_when_transforming_null_input() {
        Pipeline pipeline = new Pipeline();
        assertThrows(Pipeline.NullInputException.class, () -> pipeline.run(null));
    }

    @Test
    void does_nothing_if_pipeline_has_no_steps() {
        Pipeline pipeline = new Pipeline();
        String input = "Some complicated\nstring\r\nexample 123!!!";
        assertEquals(input, pipeline.run(input));
    }

    @Test
    void runs_single_step() {
        Pipeline pipeline = new Pipeline();
        pipeline.appendStep(new StepA());
        assertEquals("a", pipeline.run(""));
    }

    @Test
    void runs_steps_in_the_order_they_were_appended() {
        Pipeline pipeline = new Pipeline();
        pipeline.appendStep(new StepA());
        pipeline.appendStep(new StepB());
        pipeline.appendStep(new StepC());
        assertEquals("abc", pipeline.run(""));
    }

    @Test
    void runs_steps_in_the_correct_order_when_a_steps_are_inserted_at_arbitrary_positions() {
        Pipeline pipeline = new Pipeline();
        pipeline.insertStep(new StepC(), 0);
        pipeline.insertStep(new StepA(), 0);
        pipeline.insertStep(new StepB(), 1);
        assertEquals("abc", pipeline.run(""));
    }

    @Test
    void throws_error_when_inserting_step_with_invalid_index() {
        Pipeline pipeline = new Pipeline();
        assertThrows(Pipeline.InvalidIndexException.class, () -> pipeline.insertStep(new StepA(), 1));
    }

    @Test
    void removed_steps_are_no_longer_executed_as_part_of_the_pipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.appendStep(new StepA());
        pipeline.appendStep(new StepB());
        pipeline.appendStep(new StepC());
        pipeline.removeStep(2);
        pipeline.removeStep(1);
        assertEquals("a", pipeline.run(""));
    }

    @Test
    void throws_error_when_removing_step_with_invalid_index() {
        Pipeline pipeline = new Pipeline();
        assertThrows(Pipeline.InvalidIndexException.class, () -> pipeline.removeStep(1));
    }
}

/*
 Simple PipelineStep implementations to be used as helper classes to test the overall Pipeline class
 */

class StepA implements PipelineStep {

    @Override
    public String transform(String s) {
        return s + "a";
    }
}

class StepB implements PipelineStep {

    @Override
    public String transform(String s) {
        return s + "b";
    }
}

class StepC implements PipelineStep {

    @Override
    public String transform(String s) {
        return s + "c";
    }
}
