package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.LogUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptScholarshipBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Log;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class AcceptScholarshipBySupervisorUseCaseImpl implements AcceptScholarshipBySupervisorUseCase {
    public void accept(Long scholarshipId) {
        User user = AuthenticationService.getInstance().getLoginUser();

        if (user != null && user.getRole().equals("Supervisor")) {

            // connection
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                // sql
                String sql = "update scholarship set status = 'AcceptedBySupervisor' " +
                        "where id = ?";
                // execute
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, scholarshipId);
                int i = preparedStatement.executeUpdate();
                if(i==1){
                    String desc = "Scholarship by id " + scholarshipId + " accepted by " + AuthenticationService.getInstance().getLoginUser().getUsername();
                    //Date date = new Date();
                    java.sql.Date date = Date.valueOf(LocalDate.now());
                    String action="Accept";
                    Long id = AuthenticationService.getInstance().getLoginUser().getId();
                    Log log = new Log(action,date,id,scholarshipId,desc);
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
