package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.fias.Socr;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *  компонент предназначен для занесения объектов типа SOCR в базу данных
 */
@Component
public class SocrDAOBatchInsert {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${work.query.socr_insert:\"update or insert into FIAS_SOCR (kod_t_st, code, socr_level, name) values(?, ?, ?, ?)matching(kod_t_st)\"}")
    private String socrQuery;

    @Value("${work.batch_size.sql:1000}")
    private int BATCH_SIZE;

    @Autowired
    DBService dbService;

    public void insertSocrArray(List<Socr> socrList, Connection connection) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int count = 0;

        try(PreparedStatement statement = connection.prepareStatement(socrQuery)){
            connection.setAutoCommit(false);
            // Справочник небольшой, цикл со счетчиком и foreach должны работать одинаково
            for (Socr socr : socrList) {
                // Level=0 -> code=null
                if (!StringUtils.isEmpty(socr.getSCNAME())) {
                    //update
                    statement.setString(1, socr.getKOD_T_ST());
                    statement.setString(2, socr.getSCNAME());
                    statement.setBigDecimal(3, new BigDecimal(socr.getLEVEL()));
                    statement.setString(4, socr.getSOCRNAME());
                    // insert
                    statement.setString(5, socr.getKOD_T_ST());
                    statement.setString(6, socr.getSCNAME());
                    statement.setBigDecimal(7, new BigDecimal(socr.getLEVEL()));
                    statement.setString(8, socr.getSOCRNAME());

                    statement.addBatch();

                    count++;

                    if (count % BATCH_SIZE == 0) {
                        statement.executeBatch();
                        connection.commit();
                        count = 0;
                    }
                }
            }

            if(count > 0){
                statement.executeBatch();
                connection.commit();
            }
        } catch ( SQLException e) {
            logger.error("Error inserting Socr", e);
            e.printStackTrace();
        }
    }

}
