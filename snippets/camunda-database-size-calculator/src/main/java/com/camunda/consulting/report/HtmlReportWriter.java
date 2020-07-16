package com.camunda.consulting.report;

import com.camunda.consulting.dto.TableMetadataDto;
import org.camunda.bpm.engine.RepositoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static j2html.TagCreator.*;
import static j2html.TagCreator.td;

@Component
@Profile({"html", "default"})
public class HtmlReportWriter implements Reporter {

    @Override
    public void writeReport(String size, List<TableMetadataDto> tableMetadataDtos) throws IOException {
        String renderedHtml = html(
                head(
                        title("Database size report: ")
                ),
                body(
                        h1("Database total size: " + size),
                        h2("List of tables"),
                        table(
                                thead(
                                        tr(
                                                th("oid"), th("tableName"), th("totalRows"),
                                                th("totalSize"), th("indexSize"), th("toastSize"), th("tableSize")
                                        )
                                ),
                                each(tableMetadataDtos, tableMetadataDto -> tr(
                                        td(tableMetadataDto.getOid()), td(tableMetadataDto.getTableName()), td(tableMetadataDto.getTotalRows().toString()),
                                        td(tableMetadataDto.getTotalSize()), td(tableMetadataDto.getIndexSize()), td(tableMetadataDto.getToastSize()), td(tableMetadataDto.getTableSize())
                                ))
                        )

                )).renderFormatted();

        Files.createDirectories(Paths.get("database-report"));
        Files.write(Paths.get("database-report/report.html"), renderedHtml.getBytes());
    }
}
