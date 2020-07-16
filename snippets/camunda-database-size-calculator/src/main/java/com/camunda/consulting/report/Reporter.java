package com.camunda.consulting.report;

import com.camunda.consulting.dto.TableMetadataDto;
import com.camunda.consulting.dto.TableSizeReportDto;

import java.io.IOException;
import java.util.List;

public interface Reporter {

    void writeReport(TableSizeReportDto tableSizeReportDto) throws IOException;
}
