package dev.christopheredwards.assistext;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private NormalText pipelineTitle;

    @FXML
    private StepCardList steps;

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    public void initialize() {

        Pipeline pipeline = buildPipeline();

        pipelineTitle.setText(pipeline.getName());

        for (PipelineStep step : pipeline.getSteps()) {
            if (step instanceof JoinStep) {
                steps.addCard(new JoinStepCard((JoinStep) step));
            } else if (step instanceof SortStep) {
                steps.addCard(new SortStepCard((SortStep) step));
            } else if (step instanceof SplitStep) {
                steps.addCard(new SplitStepCard((SplitStep) step));
            } else {
                throw new RuntimeException("Missing presentation logic for type " + step.getClass());
            }
        }

        inputText.textProperty().addListener(((observable, oldValue, newValue) -> {
            outputText.setText(pipeline.run(newValue));
        }));
        inputText.setPromptText("Enter input text to be transformed...");

        outputText.setEditable(false);
        outputText.setPromptText("Transformation output...");
    }

    private Pipeline buildPipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.setName("Example pipeline 1");

        SplitStep splitStep = new SplitStep();
        splitStep.setSplitString("$$");

        SortStep sortStep = new SortStep();
        sortStep.setCaseInsensitive(true);

        JoinStep joinStep = new JoinStep();
        joinStep.setSeparator(",");

        pipeline.appendStep(splitStep);
        pipeline.appendStep(sortStep);
        pipeline.appendStep(joinStep);
        return pipeline;
    }
}