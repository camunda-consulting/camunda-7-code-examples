package com.camunda.consulting.db;

import com.camunda.consulting.dto.TableMetadataDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableMetadataRowMapper implements RowMapper<TableMetadataDto> {

    @Override
    public TableMetadataDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return TableMetadataDto.builder()
                .oid(resultSet.getString("oid"))
                .tableName(resultSet.getString("table_name"))
                .totalSize(resultSet.getString("total"))
                .tableSize(resultSet.getString("table"))
                .indexSize(resultSet.getString("index"))
                .toastSize(resultSet.getString("toast"))
                .indexSize(resultSet.getString("index"))
                .build();
    }
}
