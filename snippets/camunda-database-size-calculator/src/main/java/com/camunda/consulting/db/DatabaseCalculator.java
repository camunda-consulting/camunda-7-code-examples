package com.camunda.consulting.db;

import java.io.IOException;

public interface DatabaseCalculator<D> {

    void calculateDatabaseSize() throws IOException;
}
