package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Employee> employees = new ArrayList<>();
    private static List<Payroll> payrolls = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        do {
            // Print the menu
            System.out.println("Menu:");
            System.out.println("1- Ajouter un employé");
            System.out.println("2- Ajouter une paye");
            System.out.println("3- Afficher le total des contributions au Régime de Retraite du Canada");
            System.out.println("4- Afficher le total des contributions à l'assurance emploi");
            System.out.println("5- Liste des employés à taux fixe (ID, nom et heures supplémentaires)");
            System.out.println("6- Liste des employés à commission (ID, nom et ventes brutes)");
            System.out.println("7- Quitter");

            // Get the user's choice
            System.out.print("Votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    addPayroll(scanner);
                    break;
                case 3:
                    displayTotalRrspContribution();
                    break;
                case 4:
                    displayTotalAiContribution();
                    break;
                case 5:
                    displayFixedRateEmployees();
                    break;
                case 6:
                    displayCommissionEmployees();
                    break;
                case 7:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide!");
            }

        } while (choice != 7);
    }

    private static void addEmployee(Scanner scanner) {
        System.out.println("Ajout d'un employé");
        try {
            System.out.print("Entrez l'ID de l'employé: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Entrez le nom de l'employé: ");
            String name = scanner.nextLine();
            System.out.print("Entrez le département de l'employé: ");
            String department = scanner.nextLine();

            // Vérifier que le département est valide
            if (!isValidDepartment(department)) {
                throw new IllegalArgumentException("Département invalide. Veuillez choisir un département valide parmi : restaurant, maintenance, commis ou paysagistes, ventes.");
            }

            Employee employee = new Employee(id, name, department);
            employees.add(employee);

            System.out.println("Employé ajouté avec succès!");
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Entrée invalide. Veuillez entrer une valeur numérique pour l'ID de l'employé.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    // Vérifier si le département est valide
    private static boolean isValidDepartment(String department) {
        String[] validDepartments = {"restaurant", "maintenance", "commis ou paysagistes", "ventes"};
        for (String validDepartment : validDepartments) {
            if (department.equalsIgnoreCase(validDepartment)) {
                return true;
            }
        }
        return false;
    }


    private static void addPayroll(Scanner scanner) {
        System.out.println("Ajout d'une paye");
        System.out.print("Entrez l'ID de l'employé: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrez le numéro de la semaine: ");
        int weekNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrez le nombre d'heures travaillées: ");
        int hoursWorked = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrez le total des ventes: ");
        double salesTotal = scanner.nextDouble();
        scanner.nextLine();

        Employee employee = getEmployeeById(employeeId);

        if (employee != null) {
            Payroll payroll = new Payroll(weekNumber, employeeId, employee.getDepartment(), hoursWorked, salesTotal);
            payrolls.add(payroll);
            System.out.println("Paye ajoutée avec succès!");
        } else {
            System.out.println("Employé introuvable!");
        }
    }

    private static Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    private static void displayTotalRrspContribution() {
        double totalRrspContribution = 0;
        for (Payroll payroll : payrolls) {
            totalRrspContribution += payroll.getRrspContribution();
        }
        System.out.println("Total des contributions au Régime de Retraite du Canada: $" + totalRrspContribution);
    }

    private static void displayTotalAiContribution() {
        double totalAiContribution = 0;
        for (Payroll payroll : payrolls) {
            totalAiContribution += payroll.getAiContribution();
        }
        System.out.println("Total des contributions à l'assurance emploi: $" + totalAiContribution);
    }

    private static void displayFixedRateEmployees() {
        double heures = 0;
        System.out.println("Liste des employés à taux fixe");
        for (Employee employee : employees) {
            if (!employee.getDepartment().equals("ventes")) {
                System.out.print("ID: " + employee.getId() + ", Nom: " + employee.getName() );
            }
        }
        for (Payroll payroll : payrolls) {
            heures += payroll.getHoursWorked() - 44;
        }
        System.out.println(", Heures supplémentaires: "+ heures);
    }

    private static void displayCommissionEmployees() {
        double vente = 0;
        System.out.println("Liste des employés à commission");
        for (Employee employee : employees) {
            if (employee.getDepartment().equals("ventes")) {
                //CommissionEmployee commissionEmployee = (CommissionEmployee) employee;
                System.out.print("ID: " + employee.getId() + ", Nom: " + employee.getName());
            }
            for (Payroll payroll : payrolls) {
                vente += payroll.getSalesTotal();
            }
            System.out.println(", Ventes brutes: $"+ vente);
        }

    }
}