package com.camunda.consulting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableMetadataDto {

    private String oid;
    private String tableName;
    private Long totalRows;
    private String totalSize;
    private String indexSize;
    private String toastSize;
    private String tableSize;
}
