package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.LogUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RequestScholarshipByStudentUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Log;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {
    public boolean request() {

        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Student")) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter name:");
                String name = scanner.next();
                System.out.println("Enter Familly:");
                String familly = scanner.next();
                System.out.println("Enter National Code:");
                String nationalCode = scanner.next();
                System.out.println("Enter name of your Last University:");
                String lastUni = scanner.next();
                System.out.println("Enter your Last Degree:");
                String lastDegree = scanner.next();
                System.out.println("Enter your Last Field:");
                String lastField = scanner.next();
                System.out.println("Enter your Last Score:");
                Float lastScore = scanner.nextFloat();
                System.out.println("Which university you want to apply:");
                String applyUni = scanner.next();
                System.out.println("Which Degree you wish to get:");
                String applyDegree = scanner.next();
                System.out.println("Which field you want to apply:");
                String applyField = scanner.next();
                System.out.println("Enter Apply Date:");
                String applyDate = scanner.next();
                String status = "RequestedByStudent";
                Scholarship scholarship = new Scholarship(status,name,familly,nationalCode,lastUni,lastDegree,lastField,lastScore,applyUni,applyDegree,applyField,applyDate);

                Connection connection=null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    String sql = "INSERT INTO scholarship(status, name, family, nationalCode, lastUni, lastDegree, lastField, lastScore, applyUni, applyDegree, applyField, applyDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
                        String action = scholarship.getName() + " " + scholarship.getFamily() + " Requested a scolarship";
                        String date = LocalDateTime.now().toString();
                        Long id = AuthenticationService.getInstance().getLoginUser().getId();
                        Log log = new Log(action,date,id);
                        LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                        logUseCase.commitLog(log);

                        ResultSet rs = preparedStatement.getGeneratedKeys();
                        Long generatedKey = null;
                        if (rs.next()) {
                            generatedKey = rs.getLong(1);
                        }
                        scholarship.setId(generatedKey);

                        System.out.println(scholarship);
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
