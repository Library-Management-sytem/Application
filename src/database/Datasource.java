package database;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Datasource {
    public static DataSource getMySQLDataSource() {
        MysqlDataSource mysqlDS = null;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(MySQL.url);
        mysqlDS.setUser(MySQL.username);
        mysqlDS.setPassword(MySQL.password);
        return mysqlDS;
    }

    public static DataSource getOracleDataSource(){
//        OracleDataSource oracleDS = null;
//        try {
//            oracleDS = new OracleDataSource();
//            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
//            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
//            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return oracleDS;
        return null;
    }
}
