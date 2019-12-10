package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RequestScholarshipByStudentUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {
    public boolean request(Scholarship scholarship) {

        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Student")) {

                Connection connection=null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    String sql = "INSERT INTO scholarship(status, name, family, nationalCode, lastUni, lastDegree, lastField, lastScore, applyUni, applyDegree, applyField, applyDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,scholarship.getStatus());
                    preparedStatement.setString(2,scholarship.getName());
                    preparedStatement.setString(3,scholarship.getFamily());
                    preparedStatement.setString(4,scholarship.getNationalCode());
                    preparedStatement.setString(5,scholarship.getLastUni());
                    preparedStatement.setString(6,scholarship.getLastDegree());
                    preparedStatement.setString(7,scholarship.getLastField());
                    preparedStatement.setFloat(8,scholarship.getLastScore());
                    preparedStatement.setString(9,scholarship.getApplyUni());
                    preparedStatement.setString(10,scholarship.getApplyDegree());
                    preparedStatement.setString(11,scholarship.getApplyField());
                    preparedStatement.setString(12,scholarship.getApplyDate());
                    int i = preparedStatement.executeUpdate();
                    if(i==1){
                        return true;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;

    }
}
