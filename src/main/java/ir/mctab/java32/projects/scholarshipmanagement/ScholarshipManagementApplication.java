package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LogoutUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LogoutUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (! command.equals("exit")) {
            System.out.println("what do you want? ");
            command = scanner.nextLine();
            // Login
            if (command.equals("login")) {
                System.out.println("Username : ");
                String username = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                LoginUseCase loginUseCase = new LoginUseCaseImpl();
                User user = loginUseCase.login(username, password);
                if (user != null) {
                    System.out.println(" Login successful by " + user.getRole());
                }
            }
            //log out
            if (command.equals("logout")) {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                logoutUseCase.logout();
            }
            // find scholarship by supervisor
            if (command.equals("svlist")) {
                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                        = new FindScholarshipBySupervisorUseCaseImpl();

                List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }

            // accept by supervisor
            if (command.equals("svaccept")) {
                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            // reject by supervisor

            if (command.equals("svreject")) {
                RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase = new RejectScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                rejectScholarshipBySupervisorUseCase.reject(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }

            //--------------------------END Supervisor Job

            //---------------------------Start Manager Job

            // find scholarship by manager
            if (command.equals("mglist")) {
                FindScholarshipByManagerUseCase findScholarshipByManagerUseCase = new FindScholarshipByManagerUseCaseImpl();

                List<Scholarship> scholarships = findScholarshipByManagerUseCase.listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }

            // accept by manager
            if (command.equals("mgaccept")) {
                AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase = new AcceptScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            // reject by manager

            if (command.equals("mgreject")) {
                RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase =new RejectScholarshipByManagerUseCaseImpl();

                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
               rejectScholarshipByManagerUseCase.reject(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            //----------------------------------------End Manager job
            //----------------------------------------Start Student Job
            //request by student
            if (command.equals("request")) {
                RequestScholarshipByStudentUseCase requestScholarshipByStudentUseCase = new RequestScholarshipByStudentUseCaseImpl();
                boolean x = requestScholarshipByStudentUseCase.request();
                if(x){
                    System.out.println("Done.");
                }
            }
            //find by student
            if (command.equals("stfind")) {
                FindScholarshipByStudentUseCase findScholarshipByStudentUseCase = new FindScholarshipByStudentUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                Scholarship scholarship = findScholarshipByStudentUseCase.find(Long.parseLong(scholarshipId));
                System.out.println(scholarship.toString());
            }

            //----------------------------------------End Student Job
            //----------------------------------------Start University Job
            if(command.equals("unilist")){
                FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase = new FindScholarshipByUniversityUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByUniversityUseCase.findScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            //----------------------------------------End Unoversity Job
            //-----------------------------------------Start Dashboard Job

            if(command.equals("dashboard")){
                DashboardUseCase dashboardUseCase = new DashboardUseCaseImpl();
                dashboardUseCase.dashboard();
            }

            //------------------------------------------End Dashboard Job

        }
    }
}
