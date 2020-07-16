package com.camunda.consulting.report;

import com.camunda.consulting.dto.TableSizeReportDto;

import java.io.IOException;

public interface Reporter {

    void writeReport(TableSizeReportDto tableSizeReportDto) throws IOException;
}
