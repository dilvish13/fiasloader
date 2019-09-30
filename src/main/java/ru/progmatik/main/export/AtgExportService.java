package ru.progmatik.main.export;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.progmatik.main.export.entity.City;
import ru.progmatik.main.export.repository.CityRepository;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * AtgExportService.
 *
 * @author M. Golovatiy {@literal <mgolovatiy@at-consulting.ru>}
 * @see <a href="https://attacomsian.com/blog/export-download-data-csv-file-spring-boot">Doc 1: export CSV</a>
 */
@Service
public class AtgExportService {
    private static final char DELIMITER = ',';
    private static final String NEW_LINE = "\n";
    private static final char QUOTE = '\"';
    @Autowired
    private CityRepository cityRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String exportString(String value) {
        String res = value;
        boolean needQuote = res.indexOf('"') >= 0;
        if (needQuote) {
            res = res.replace("\"", "\"\"");
        }
        needQuote = needQuote || (res.indexOf(',') >= 0);
        return needQuote ? String.format("\"%s\"", res) : res;
    }

    public void export(Writer writer, String area) throws IOException {
        writer.append("/atg/commerce/catalog/SecureProductCatalog:city,,,,,LOCALE=ru_RU,\n")
                .append("ID,name,suzId,areas,slug,fiasCode,cityType,searchableName,fullName\n");
        Consumer<City> write = city -> {
            try {
                writer.append(city.getId()).append(DELIMITER)
                        .append(exportString(city.getName())).append(DELIMITER)
                        .append(city.getSuzId()).append(DELIMITER)
                        .append(city.getAreas()).append(DELIMITER)
                        .append(city.getSlug()).append(DELIMITER)
                        .append(city.getFiasCode()).append(DELIMITER)
                        .append(city.getCityType()).append(DELIMITER)
                        .append(exportString(city.getSearchableName())).append(DELIMITER)
                        .append(exportString(city.getFullName())).append(NEW_LINE);
            } catch (IOException e) {
                logger.error("Error while exporting cities into ATG CSV format: ", e);
            }
        };
        if (StringUtils.isEmpty(area)) {
            Pageable pageRequest = PageRequest.of(0, 1000);
            Slice<City> citiesSlice;
            do {
                citiesSlice = cityRepository.streamAll(pageRequest);
                // int sliceN = citiesSlice.getNumber();
                citiesSlice.getContent().forEach(write);
                pageRequest = citiesSlice.nextPageable();
            } while (citiesSlice.hasNext());
        } else {

            try (Stream<City> cities = cityRepository.streamByArea(area)) {
                cities.forEach(write);
            }
        }
    }
}
