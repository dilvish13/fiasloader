package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * компонент, предоставляющий доступ к базе данных
 */

@Component
public class DBService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static DBService dbService;
    private static Connection connection;
    private static Properties props;

    @Value("${spring.datasource.driver:org.firebirdsql.jdbc.FBDriver}")
    String driver;

    @Value("${spring.datasource.url}")
    String databaseurl;

    @Value("${spring.datasource.userName:SYSDBA}")
    String user;

    @Value("${spring.datasource.password:masterkey}")
    String password;

    @Value("${spring.datasource.role:}")
    String role;

    @Value("${spring.datasource.encoding:WIN1251}")
    String encoding;

//    public static DBService instance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//        if(dbService == null){
//            dbService = new DBService();
//        }
//        return dbService;
//    }
//    private DBService() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
//        DriverManager.registerDriver((Driver) Class.forName(db).newInstance());
//        props = new Properties();
//
//        props.setProperty("userName", username);
//        props.setProperty("password", password);
//        if(!role.isEmpty()) props.setProperty("role", role);
//        if(!encoding.isEmpty()) props.setProperty("encoding", encoding);
//
//        //        DriverManager.registerDriver((Driver) Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance());
//
//        getConnection();
//    }

    public Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(connection == null || connection.isClosed()) {
            DriverManager.registerDriver((Driver) Class.forName(driver).newInstance());
            props = new Properties();

            if(!user.isEmpty()) props.setProperty("user", user);
            if(!password.isEmpty()) props.setProperty("password", password);
            if(!role.isEmpty()) props.setProperty("role", role);
            if(!encoding.isEmpty()) props.setProperty("encoding", encoding);

            connection = DriverManager.getConnection(databaseurl, props);
        }
        return connection;
    }
}