package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.LogUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LogoutUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LogoutUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Log;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (! command.equals("exit")) {

            if (AuthenticationService.getInstance().getLoginUser() == null) {
                System.out.println("|--------------------|");
                System.out.println("|Enter command :     |");
                System.out.println("|   Login : Login    |");
                System.out.println("|--------------------|");
                command = scanner.nextLine();
                // Login
                if (command.equals("login")) {
                    System.out.println("Username : ");
                    String username = scanner.nextLine();
                    System.out.println("Password : ");
                    String password = scanner.nextLine();
//                    Console console = System.console();
//
//                    String username = scanner.nextLine();
//                    System.out.println("Enter the password: ");
//
//                    char[] ps = console.readPassword();
//                    String password = String.valueOf(ps);


                    LoginUseCase loginUseCase = new LoginUseCaseImpl();
                    User user = loginUseCase.login(username, password);
                    if (user != null) {
                        System.out.println(" Login successful by " + user.getRole());
                    }
                }
            }

         while (AuthenticationService.getInstance().getLoginUser()!=null) {
                if(AuthenticationService.getInstance().getLoginUser().getRole().equals("Student")){
                    System.out.println("|--------------------|");
                    System.out.println("|Enter command :     |");
                    System.out.println("| Request:   request |");
                    System.out.println("| Status:    status  |");
                    System.out.println("| Logout:    logout  |");
                    System.out.println("|--------------------|");
                    System.out.println("what do you want? ");

                    command = scanner.next();
                    switch (command) {
                        case "request": {
                            //request by student
                            RequestScholarshipByStudentUseCase requestScholarshipByStudentUseCase = new RequestScholarshipByStudentUseCaseImpl();
                            boolean x = requestScholarshipByStudentUseCase.request();
                            if (x) {
                                System.out.println("Done.");
                            }
                            break ;
                        }
                        case "status": {
                            //get status of student
                            FindScholarshipByStudentUseCase findScholarshipByStudentUseCase = new FindScholarshipByStudentUseCaseImpl();
                            System.out.println("Scholarship Id: ");
                            String scholarshipId = scanner.next();
                            Scholarship scholarship = findScholarshipByStudentUseCase.find(Long.parseLong(scholarshipId));
                            System.out.println(scholarship.toString());
                            LogUseCaseImpl logUseCase = new LogUseCaseImpl();
                            List<Log> logs =new ArrayList<>();
                            logs = logUseCase.showLogs(Long.parseLong(scholarshipId));
                            for (Log l: logs
                                 ) {
                                System.out.println(l.toString());

                            }


                            break ;
                        }
                        case "logout": {
                            logout();
                            break;

                        }
                    }
                }
                else if(AuthenticationService.getInstance().getLoginUser().getRole().equals("Supervisor")){
                        System.out.println("|--------------------|");
                        System.out.println("|Enter command :     |");
                        System.out.println("| List: svlist       |");
                        System.out.println("| Accept: svaccept   |");
                        System.out.println("| Reject: svreject   |");
                        System.out.println("| Dashboard: svdb    |");
                        System.out.println("| Logout:    logout  |");
                        System.out.println("|--------------------|");
                        System.out.println("what do you want? ");

                        command = scanner.next();
                        switch (command) {
                            case "svlist": {
                                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                                        = new FindScholarshipBySupervisorUseCaseImpl();

                                List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                                        .listScholarships();
                                for (int i = 0; i < scholarships.size(); i++) {
                                    System.out.println(scholarships.get(i));
                                }
                                break ;
                            }
                            case "svaccept": {
                                //accept by supervisor
                                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                                System.out.println("Scholarship Id: ");
                                String scholarshipId = scanner.next();
                                acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                                System.out.println("Done.");
                                break ;
                            }
                            case "svreject": {
                                //reject by supervisor
                                RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase = new RejectScholarshipBySupervisorUseCaseImpl();
                                System.out.println("Scholarship Id: ");
                                String scholarshipId = scanner.next();
                                rejectScholarshipBySupervisorUseCase.reject(Long.parseLong(scholarshipId));
                                System.out.println("Done.");
                                break ;
                            }
                            case "svdb": {

                            }
                            case "logout": {
                                logout();
                                break;
                            }
                        }

                    }
                else if(AuthenticationService.getInstance().getLoginUser().getRole().equals("Manager")){
                        System.out.println("|--------------------|");
                        System.out.println("|Enter command :     |");
                        System.out.println("| List: mglist       |");
                        System.out.println("| Accept: mgaccept   |");
                        System.out.println("| Reject: mgreject   |");
                        System.out.println("| Dashboard: mgdb    |");
                        System.out.println("| Logout:    logout  |");
                        System.out.println("|--------------------|");
                        System.out.println("what do you want? ");
                        command = scanner.next();
                        switch (command) {
                            case "mglist": {
                                //list all scholarships by manager
                                FindScholarshipByManagerUseCase findScholarshipByManagerUseCase = new FindScholarshipByManagerUseCaseImpl();

                                List<Scholarship> scholarships = findScholarshipByManagerUseCase.listScholarships();
                                for (int i = 0; i < scholarships.size(); i++) {
                                    System.out.println(scholarships.get(i));
                                }
                                break;
                            }
                            case "mgaccept": {
                                //accept by manager
                                AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase = new AcceptScholarshipByManagerUseCaseImpl();
                                System.out.println("Scholarship Id: ");
                                String scholarshipId = scanner.next();
                                acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                                System.out.println("Done.");
                                break ;
                            }
                            case "mgreject": {
                                RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase = new RejectScholarshipByManagerUseCaseImpl();

                                System.out.println("Scholarship Id: ");
                                String scholarshipId = scanner.next();
                                rejectScholarshipByManagerUseCase.reject(Long.parseLong(scholarshipId));
                                System.out.println("Done.");
                                break ;
                            }
                            case "mgdb": {
                                DashboardUseCase dashboardUseCase = new DashboardUseCaseImpl();
                                dashboardUseCase.dashboard();
                                break ;
                            }
                            case "logout": {
                                logout();
                                break;
                            }
                        }
                    }
                else if(AuthenticationService.getInstance().getLoginUser().getRole().equals("University")){
                        System.out.println("|--------------------|");
                        System.out.println("|Enter command :     |");
                        System.out.println("| List:   unilist    |");
                        System.out.println("| Logout: logout     |");
                        System.out.println("|--------------------|");
                        System.out.println("what do you want? ");
                        command = scanner.next();
                        switch (command) {
                            case "unilist": {
                                FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase = new FindScholarshipByUniversityUseCaseImpl();
                                List<Scholarship> scholarships = findScholarshipByUniversityUseCase.findScholarships();
                                for (int i = 0; i < scholarships.size(); i++) {
                                    System.out.println(scholarships.get(i));
                                }
                                break ;
                            }
                            case "logout": {
                                logout();
                                break;
                            }
                        }
                    }
                }



            }




    }
    private static void logout(){
        LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
        logoutUseCase.logout();
    }
}
