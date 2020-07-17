package com.camunda.consulting.report;

import com.camunda.consulting.dto.TableSizeReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Profile({"html", "default"})
@RequiredArgsConstructor
public class HtmlReportWriter implements Reporter {

    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Override
    public void writeReport(TableSizeReportDto tableSizeReportDto) throws IOException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("size", tableSizeReportDto.getSize());
        thymeleafContext.setVariable("dbType", tableSizeReportDto.getDatabaseType());
        thymeleafContext.setVariable("tableMetadataDtos", tableSizeReportDto.getTableMetadataDtoList());

        String htmlBody = thymeleafTemplateEngine.process("database-size-report.html", thymeleafContext);

        Files.createDirectories(Paths.get("database-report"));
        Files.write(Paths.get("database-report/report.html"), htmlBody.getBytes());
    }
}
