package dev.christopheredwards.assistext;

/**
 * A single step in a text transformation pipeline.
 */
public interface PipelineStep {
    /**
     * Apply implementer-specific transformation logic to the given string to produce a new string.
     *
     * @param s the string being transformed
     * @return a new string that has been transformed according to the rules of the implementing class
     * @throws InvalidInputException if given input is invalid in some way (e.g. if it is null)
     */
    String transform(String s);

    class InvalidInputException extends RuntimeException {
        public InvalidInputException(String input) {
            super("Input \"" + input + "\" is not valid for this pipeline step");
        }
    }
}
