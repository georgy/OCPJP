package exam.jdbc;

import org.h2.Driver;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 25.11.11
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public class Example1 {
    public static void main(String[] args) throws Exception {
        new Example1().run();
    }

    private void run() throws SQLException, IOException {
        new Driver();
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:", "sa", "");
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("create table test (id int not null, name varchar(255), primary key (id))");

            //JdbcRowSet rsp = RowSetProvider.newFactory().createJdbcRowSet();

/*
            JdbcRowSet rowset = new JdbcRowSetImpl(conn);
            rowset.setCommand("select * from test");
            rowset.execute();

            for (int i = 0; i < 10; i++) {
                rowset.moveToInsertRow();
                rowset.setInt(1, i + 1);
                rowset.setString(2, "test" + i);

                rowset.insertRow();
            }
*/

            WebRowSet wrs = RowSetProvider.newFactory().createWebRowSet();
            wrs.setCommand("select * from test");
            wrs.execute(conn);

            for (int i = 0; i < 10; i++) {
                wrs.moveToInsertRow();
                wrs.setInt(1, i + 1);
                wrs.setString(2, "test" + i);

                wrs.insertRow();
                //System.out.println(rowset.getInt(1));
            }
            //wrs.populate(rowset);
            wrs.writeXml(System.out);
        }
    }

}
