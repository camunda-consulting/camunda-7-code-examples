package com.camunda.consulting;

public interface PsiScenarioCoverage {
  /**
   * The answer arrives before the process has reached the wait state
   */
  void answerBeforeProcess();

  /**
   * The answer arrives after the process has reached the wait state
   */
  void processBeforeAnswer();

  /**
   * Only an answer arrives
   */
  void answerOnly();

  /**
   * There are multiple answers that can trigger the wait state
   */
  void multipleAnswerCandidates();
}
