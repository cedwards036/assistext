package dev.christopheredwards.assistext;

import java.util.ArrayList;

/**
 * A pipeline of text manipulation steps that are chained together to transform a given input string.
 */
public class Pipeline {

    private final ArrayList<PipelineStep> steps;

    /**
     * Class constructor
     */
    public Pipeline() {
        steps = new ArrayList<>();
    }

    /**
     * Execute the pipeline's transformations on the given string.
     *
     * @param s the string to transform
     * @return a new string with all the pipeline's transformations applied
     */
    public String run(String s) {
        if (s == null) {
            throw new NullInputException();
        }
        String result = s;
        for (PipelineStep step : steps) {
            result = step.transform(result);
        }
        return result;
    }

    /**
     * Append a new transformation step to the end of the pipeline.
     *
     * @param step the step to append
     */
    public void appendStep(PipelineStep step) {
        steps.add(step);
    }

    /**
     * Insert a new step into the pipeline at the given index.
     *
     * @param step  the step to insert
     * @param index the index at which the step should be inserted
     * @throws PipelineStep.InvalidInputException if index is invalid for the current pipeline size
     */
    public void insertStep(PipelineStep step, int index) {
        try {
            steps.add(index, step);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(index);
        }
    }

    /**
     * Remove the step at the specified index from the pipeline.
     *
     * @param index the index of the step to remove
     * @throws PipelineStep.InvalidInputException if index is invalid for the current pipeline size
     */
    public void removeStep(int index) {
        try {
            steps.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(index);
        }
    }

    public static class NullInputException extends RuntimeException {
        public NullInputException() {
            super("Input to pipeline can't be null");
        }
    }

    public class InvalidIndexException extends RuntimeException {
        public InvalidIndexException(int index) {
            super("Unable to insert step at index " + index + "; pipeline contains " + steps.size() + " steps");
        }
    }
}
