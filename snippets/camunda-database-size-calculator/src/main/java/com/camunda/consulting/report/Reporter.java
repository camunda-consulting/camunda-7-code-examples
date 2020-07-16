package com.camunda.consulting.report;

import com.camunda.consulting.dto.TableMetadataDto;

import java.io.IOException;
import java.util.List;

public interface Reporter {

    void writeReport(String size, List<TableMetadataDto> tableMetadataDtos) throws IOException;
}
