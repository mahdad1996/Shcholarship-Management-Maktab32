package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.LogUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.DashboardUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Log;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class DashboardUseCaseImpl implements DashboardUseCase {
    @Override
    public void dashboard() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Manager")) {

                System.out.println("status : Requested By Student Count : " + getCountStatus("RequestedByStudent"));
                System.out.println("status : Accepted By Supervisor Count : " + getCountStatus("AcceptedBySupervisor"));
                System.out.println("status : Rejected By Supervisor Count : " + getCountStatus("RejectedBySupervisor"));
                System.out.println("status : Accepted By Manager Count : " +  getCountStatus("AcceptedByManager"));
                System.out.println("status : Rejected By Manager Count : " +  getCountStatus("RejectedByManager"));


            }
            String desc = "dashboard loaded by " + AuthenticationService.getInstance().getLoginUser().getUsername();
            //Date date = new Date();
            java.sql.Date date = Date.valueOf(LocalDate.now());
            String action = "Dashboard";
            Long id = AuthenticationService.getInstance().getLoginUser().getId();
            Log log = new Log(action,date,id,id,desc);
            LogUseCaseImpl logUseCase = new LogUseCaseImpl();
            logUseCase.commitLog(log);
        }
    }

    private int getCountStatus(String status){
        // connection
        Connection connection = null;
        int x=0;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            String sql = "select Count(*) as count from scholarship where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                x = resultSet.getInt("count");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
}
