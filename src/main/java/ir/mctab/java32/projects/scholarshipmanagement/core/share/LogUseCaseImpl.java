package ir.mctab.java32.projects.scholarshipmanagement.core.share;

import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;

import ir.mctab.java32.projects.scholarshipmanagement.model.Log;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogUseCaseImpl {

    public void commitLog(Log log) {
        Connection connection = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "INSERT INTO log(action ,date, action_by,action_for,Description) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log.getAction());
            preparedStatement.setDate(2, log.getDate());

            preparedStatement.setLong(3, log.getAction_by());
            preparedStatement.setLong(4, log.getAction_for());
            preparedStatement.setString(5,log.getDesc());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Log> showLogs(Long scholarshipId) {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Log> result = new ArrayList<Log>();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Student")) {
                // connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    // query
                    String sql = "select * from log where action_for = ? ";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setLong(1,scholarshipId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {

                        Log log = new Log(
                                resultSet.getInt("id"),
                                resultSet.getString("action"),
                                resultSet.getDate("date"),
                                resultSet.getLong("action_by"),
                                resultSet.getLong("action_for"),
                                resultSet.getString("Description")
                        );
                        result.add(log);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
