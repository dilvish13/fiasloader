package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.fias.Object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *  компонент предназначен для занесения объектов типа AddrObj в базу данных
 */
@Component
public class AddrObjDAOBatchInsert {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${work.query.addrobj_merge:\"update or insert into FIAS_ADDROBJ(aoguid,aoid,aolevel,citycode,currstatus,formalname,nextid,offname,operstatus,parentguid,placecode,previd,regioncode,shortname,streetcode,livestatus, plaincode, divtype)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) matching(aoid)\"}")
    private String addrobjMergeQuery;

    @Value("${work.batch_size.sql:1000}")
    private int BATCH_SIZE;

    public void insertAddrObjArray(List<Object> objectList, Connection connection) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int count = 0;

        try(PreparedStatement statement = connection.prepareStatement(addrobjMergeQuery)){
            connection.setAutoCommit(false);
            // foreach плохо работает
            for (Object addrobj : objectList) {
                // update
                statement.setString(1, addrobj.getAOGUID());
                statement.setString(2, addrobj.getAOID());
                statement.setBigDecimal(3, new BigDecimal(addrobj.getAOLEVEL()));
                statement.setString(4, addrobj.getFORMALNAME());
                statement.setString(5, addrobj.getOFFNAME());
                statement.setString(6, addrobj.getPARENTGUID());
                statement.setString(7, addrobj.getREGIONCODE());
                statement.setString(8, addrobj.getSHORTNAME());
                // insert
                statement.setString(9, addrobj.getAOGUID());
                statement.setString(10, addrobj.getAOID());
                statement.setBigDecimal(11, new BigDecimal(addrobj.getAOLEVEL()));
                statement.setString(12, addrobj.getFORMALNAME());
                statement.setString(13, addrobj.getOFFNAME());
                statement.setString(14, addrobj.getPARENTGUID());
                statement.setString(15, addrobj.getREGIONCODE());
                statement.setString(16, addrobj.getSHORTNAME());
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
            logger.error("Error inserting Object", e);
            e.printStackTrace();
        }
    }
}
