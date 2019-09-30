package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.fias.ObjectGuid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *  компонент предназначен для модифицирования объектов типа AddrObj в БД
 */
@Component
public class CreateAtgCitiesTable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${work.query.createAtgCities:\"begin updateAtgAreasCodes; recreateAtgCitiesTable; end;\"}")
    private String createAtgCitiesTableQuery;

    public void createAtgCities(Connection connection) {
        try(PreparedStatement statement = connection.prepareStatement(createAtgCitiesTableQuery)){
            logger.info("Start creating ATG cities table.");
            statement.execute();
            logger.info("ATG cities table created.");
        } catch ( SQLException e) {
            logger.error("Error reating ATG cities table", e);
            e.printStackTrace();
        }
    }
}
