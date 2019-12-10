package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.LogUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Log;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class RejectScholarshipBySupervisorUseCaseImpl implements RejectScholarshipBySupervisorUseCase {
    public void reject(Long scholarshipId) {
        User user = AuthenticationService.getInstance().getLoginUser();

        if (user != null && user.getRole().equals("Supervisor")) {

            // connection
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                // sql
                String sql = "update scholarship set status = 'RejectedBySupervisor' " +
                        "where id = ?";
                // execute
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, scholarshipId);
                int i = preparedStatement.executeUpdate();
                if(i==1){
                    String action ="Scholarship By id " + scholarshipId + " rejected by " + AuthenticationService.getInstance().getLoginUser().getUsername();
                    String date = LocalDateTime.now().toString();
                    Long id = AuthenticationService.getInstance().getLoginUser().getId();
                    Log log = new Log(action,date,id);
                    LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                    logUseCase.commitLog(log);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
