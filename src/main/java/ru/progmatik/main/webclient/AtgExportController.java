package ru.progmatik.main.webclient;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ru.progmatik.main.export.AtgExportService;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletResponse;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.charset.Charset.forName;

/**
 * AtgExportController.
 *
 * @author M. Golovatiy {@literal <mgolovatiy@at-consulting.ru>}
 * @see <a href="https://github.com/spring-projects/spring-data-examples/tree/master/jpa/java8">Doc 1: streams in Repository</a>
 */
@Controller
public class AtgExportController {
    private static final String ATG_CITIES_CSV_FMT = "AtgCities_%s.csv";
    private static String CSV_FILE_FORMAT = "yyMMdd_HHMMss_SSS";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AtgExportService atgCsvExportService;

    @GetMapping(value = "/export/cities", params = "area")
    @Transactional(readOnly = true)
    public void exportCities(HttpServletResponse httpResponse, @RequestParam("area") String area) throws Exception {
        getExportCities(httpResponse, area);
    }

    @GetMapping(value = "/export/cities")
    @Transactional(readOnly = true)
    public void exportCities(HttpServletResponse httpResponse) throws Exception {
        getExportCities(httpResponse, null);
    }

    private void getExportCities(HttpServletResponse httpResponse, @RequestParam("area") String area) throws IOException {
        //set file name and content type
        String fileName = String.format(ATG_CITIES_CSV_FMT, LocalDateTime.now().format(DateTimeFormatter.ofPattern(CSV_FILE_FORMAT)));
        logger.info(String.format("Start cities export (area=%s) to %s.", area, fileName));
        httpResponse.setContentType("application/csv; charset=WINDOWS-1251");
        httpResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + fileName + "\"");
        atgCsvExportService.export(httpResponse.getWriter(), area);
        httpResponse.flushBuffer();
        logger.info(String.format("Finished cities export (area=%s) to %s.", area, fileName));
    }

    @GetMapping(value = "/readme")
    @Transactional(readOnly = true)
    public void exportReadme(HttpServletResponse httpResponse) throws Exception {
        getExportReadme(httpResponse);
    }

    private void getExportReadme(HttpServletResponse httpResponse) throws IOException {
        //set file name and content type
        String fileName = "Readme.txt";
        httpResponse.setContentType("text/xml; charset=UTF-8");
        httpResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + fileName + "\"");
        try {
            InputStream readme = this.getClass().getResourceAsStream("/README.md");
            Writer writer = httpResponse.getWriter();
             IOUtils.copy(readme, writer, forName("UTF-8"));
            writer.flush();
        } catch (Exception e) {
            logger.error(String.format("Cant export readme: %s.", e.getLocalizedMessage()), e);
        }
        httpResponse.flushBuffer();
    }

}
