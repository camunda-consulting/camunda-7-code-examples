package com.camunda.consulting;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class ComposedIdentityProviderUtil {

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
