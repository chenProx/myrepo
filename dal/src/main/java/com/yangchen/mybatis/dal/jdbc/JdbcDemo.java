package com.yangchen.mybatis.dal.jdbc;

import com.yangchen.mybatis.dal.domain.table.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Types.INTEGER;

public class JdbcDemo {
    public int insert(Test test) {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://101.132.243.220:3306/gp?useUnicode=true&amp;\n" +
                    "                characterEncoding=utf-8&amp;useSSL=false&amp;useJDBCCompliantTimezoneShift=true&amp;\n" +
                    "                useLegacyDatetimeCode=false&amp;serverTimezone=UTC", "root", "Pwd@123456");
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test VALUES (?,?,?)");
            if (test.getId() == null) {
                preparedStatement.setNull(1, INTEGER);
            } else {
                preparedStatement.setInt(1, test.getId());
            }
            preparedStatement.setInt(2, test.getNums());
            preparedStatement.setString(3, test.getName());

            connection.commit();

            return preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
