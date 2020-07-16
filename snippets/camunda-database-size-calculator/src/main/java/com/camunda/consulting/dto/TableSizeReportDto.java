package com.camunda.consulting.dto;

import com.camunda.consulting.db.DatabaseTypes;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TableSizeReportDto {

    private String size;
    private List<TableMetadataDto> tableMetadataDtoList = new ArrayList<>();
    private DatabaseTypes databaseType;
}
