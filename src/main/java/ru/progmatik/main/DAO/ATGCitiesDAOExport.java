package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  компонент предназначен для экспорта в CSV справочника городов в формате ATG
 */
@Component
public class ATGCitiesDAOExport {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${work.extract.need:true}")
    private Boolean needExport;
    @Value("${work.extract.dir:csv}")
    private String exportDir;
    @Value("${work.query.extract_cities:select 1 from dual}")
    private String extractCitiesQuery;

    public void modifyAddrObj(Connection connection) {
        try(PreparedStatement statement = connection.prepareStatement(extractCitiesQuery)){
            logger.info("Start address objects exported.");
            statement.execute();
            connection.commit();
            logger.info("Address objects exported.");
        } catch ( SQLException e) {
            logger.error("Error exporting Object", e);
            e.printStackTrace();
        }
    }
}
