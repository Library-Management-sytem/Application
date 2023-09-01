package utility;

import javax.swing.table.DefaultTableModel;
import java.io.PrintStream;
import java.sql.*;
import java.util.ResourceBundle;

public class DBUtility {
    public static String driver;
    public static String url;
    public static String username;
    public static String password;


    static {
        ResourceBundle rd =ResourceBundle.getBundle("db");
        driver=rd.getString("db.driver");
        url=rd.getString("db.url");
        username=rd.getString("db.username");
        password=rd.getString("db.password");
    }
    public Connection provideConnection(){
        Connection connection = null;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        String url="jdbc:mysql://localhost:3306/library";

        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }

    public ResultSet execute(String query) throws SQLException {
        ResultSet resultSet = null;
        Connection connection = this.provideConnection();
        if (connection != null) {
            Statement stmt = connection.createStatement();
            try {
                resultSet = stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            throw new SQLException();
        }
        return resultSet;
    }

    public static DefaultTableModel resultSetToTableModel(DefaultTableModel model, ResultSet row) throws SQLException
    {
        ResultSetMetaData meta= row.getMetaData();
        if(model==null) model= new DefaultTableModel();
        String cols[]=new String[meta.getColumnCount()];
        for(int i=0;i< cols.length;++i)
        {
            cols[i]= meta.getColumnLabel(i+1);
        }

        model.setColumnIdentifiers(cols);

        while(row.next())
        {
            Object data[]= new Object[cols.length];
            for(int i=0;i< data.length;++i)
            {
                data[i]=row.getObject(i+1);
            }
            model.addRow(data);
        }
        return model;
    }

}