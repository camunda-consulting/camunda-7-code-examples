package com.camunda.consulting.db;

import com.camunda.consulting.dto.TableMetadataDto;
import com.camunda.consulting.dto.TableSizeReportDto;
import com.camunda.consulting.report.Reporter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Component
@Profile({"postgresql", "default"})
@RequiredArgsConstructor
public class PostgresqlDatabaseCalculator implements DatabaseCalculator {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final JdbcTemplate jdbcTemplate;
    private final Reporter reporter;

    public void calculateDatabaseSize() throws IOException {
        String size = jdbcTemplate.queryForObject("SELECT PG_SIZE_PRETTY( PG_DATABASE_SIZE('camunda'))", String.class);
        logger.debug("Total database size: {}", size);

        List<TableMetadataDto> tableMetadataDtoList = jdbcTemplate.query("SELECT oid, table_name, pg_size_pretty(total_bytes) AS total\n" +
                "    , pg_size_pretty(index_bytes) AS index\n" +
                "    , pg_size_pretty(toast_bytes) AS toast\n" +
                "    , pg_size_pretty(table_bytes) AS table\n" +
                "  FROM (\n" +
                "  SELECT *, \n" +
                "    total_bytes-index_bytes-coalesce(toast_bytes,0) AS table_bytes\n" +
                "   FROM (\n" +
                "      SELECT c.oid,nspname AS table_schema, relname AS table_name\n" +
                "              , pg_total_relation_size(c.oid) AS total_bytes\n" +
                "              , pg_indexes_size(c.oid) AS index_bytes\n" +
                "              , pg_total_relation_size(reltoastrelid) AS toast_bytes\n" +
                "          FROM pg_class c\n" +
                "          LEFT JOIN pg_namespace n ON n.oid = c.relnamespace\n" +
                "          WHERE relkind = 'r'\n" +
                "          AND relname like 'act%'\n" +
                "  ) a\n" +
                ") a", new TableMetadataRowMapper());

        logger.info("Printing metadata about tables");

        tableMetadataDtoList.forEach(tableMetadataDto -> {
            Long rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + tableMetadataDto.getTableName(), Long.class);
            tableMetadataDto.setTotalRows(rowCount);
        });

        tableMetadataDtoList.sort(Comparator.comparing(TableMetadataDto::getTotalRows).reversed());

        tableMetadataDtoList.forEach(tableMetadataDto -> logger.debug(tableMetadataDto.toString()));

        TableSizeReportDto tableSizeReportDto = TableSizeReportDto.builder().size(size).tableMetadataDtoList(tableMetadataDtoList).databaseType(DatabaseTypes.POSTGRESQL).build();

        reporter.writeReport(tableSizeReportDto);
    }
}
