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
 *  компонент предназначен для удаления объектов типа AddrObj из БД
 */
@Component
public class AddrObjDAOBatchDelete {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${work.query.addrobj_del:\"delete from FIAS_ADDROBJ where aoid=?\"}")
    private String addrobjDeleteQuery;

    @Value("${work.batch_size.sql:1000}")
    private int BATCH_SIZE;

    public void deleteAddrObjArray(List<ObjectGuid> objectList, Connection connection)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int count = 0;

        try(PreparedStatement statement = connection.prepareStatement(addrobjDeleteQuery)){
            connection.setAutoCommit(false);
            // foreach плохо работает
            for (ObjectGuid addrobj : objectList) {
                statement.setString(1, addrobj.getAOID());
                statement.addBatch();

                count++;

                if (count % BATCH_SIZE == 0) {
                    statement.executeBatch();
                    connection.commit();
                    count = 0;
                }
            }

            if(count > 0){
                statement.executeBatch();
                connection.commit();
            }
        } catch ( SQLException e) {
            logger.error("Error deleting Object", e);
            e.printStackTrace();
        }
    }
}
