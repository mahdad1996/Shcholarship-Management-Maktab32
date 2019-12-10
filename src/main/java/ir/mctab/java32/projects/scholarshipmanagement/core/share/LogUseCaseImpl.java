package ir.mctab.java32.projects.scholarshipmanagement.core.share;

import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;

import ir.mctab.java32.projects.scholarshipmanagement.model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogUseCaseImpl {

    public void commitLog(Log log) {
        Connection connection=null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "INSERT INTO log(action ,date, action_by) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,log.getAction());
            preparedStatement.setString(2,log.getDate());
            preparedStatement.setLong(3,log.getAction_by());

            int i = preparedStatement.executeUpdate();
            if(i==1){

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
