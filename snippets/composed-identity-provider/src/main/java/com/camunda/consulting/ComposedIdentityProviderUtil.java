package com.camunda.consulting;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class ComposedIdentityProviderUtil {

  /**
   * This BinaryOperator will ensure that only one element is contained in the stream that should be
   * reduced
   *
   * @return the one result
   * @param <T> type of elements
   */
  public static <T> BinaryOperator<T> atMostOne() {
    return (t, t2) -> {
      if (t == null) {
        return t2;
      }
      if (t2 == null) {
        return t;
      }
      throw new IllegalStateException("Could not extract exactly one entry from list");
    };
  }

  /**
   * This BinaryOperator will ensure that only equal elements are contained in the stream that
   * should be reduced
   *
   * @param comparator the comparator that is applied in case there are multiple elements present
   * @return the one result
   * @param <T> type of elements
   */
  public static <T> BinaryOperator<T> merge(Comparator<T> comparator) {
    return (t, t2) -> {
      if (t == null) {
        return t2;
      }
      if (t2 == null) {
        return t;
      }
      if (comparator.compare(t, t2) == 0) {
        return t;
      }
      throw new IllegalStateException("Could not merge objects");
    };
  }
}
