package org.example;

public class Payroll {
    private int weekNumber;
    private int employeeId;
    private String department;
    private double hoursWorked;
    private double salesTotal;
    private double rrspContribution;
    private double aiContribution;
    private double grossPay;

    public Payroll(int weekNumber, int employeeId,String department, double hoursWorked,  double salesTotal) {
        this.weekNumber = weekNumber;
        this.employeeId = employeeId;
        this.department = department;
        this.hoursWorked = hoursWorked;
        this.salesTotal = salesTotal;
        this.rrspContribution = calculateRrspContribution();
        this.aiContribution = calculateAiContribution();
        this.grossPay = calculateGrossPay();
    }

    // Getters for weekNumber, employeeId, salesTotal, overtimeHours, rrspContribution, eiContribution, and grossPay

    public double getHoursWorked() {
        return hoursWorked;
    }


    public int getWeekNumber() {
        return weekNumber;
    }

    public String getDepartment() {
        return department;
    }
    public int getEmployeeId() {
        return employeeId;
    }




    public double getSalesTotal() {
        return salesTotal;
    }



    public double getRrspContribution() {
        return rrspContribution;
    }

    public double getAiContribution() {
        return aiContribution;
    }

    public double getGrossPay() {
        return grossPay;
    }

    // Method to calculate RRSP contribution


    //Method to calculate grossPay
    private double calculateGrossPay() {
        double regularPay = 0.0;
        double overtimePay = 0.0;
        double commissionPay = 0.0;
        double hourlyRate = 0.0;

        switch (department) {
            case "restaurant":
                hourlyRate = 8.50;
                break;
            case "Maintenance":
                hourlyRate = 12.50;
                break;
            case "commis ou paysagistes":
                hourlyRate = 15.75;
                break;

            case "ventes":
                hourlyRate = 15.00;
                break;
            default:
                hourlyRate = 0.0;
                break;
        }

        if (!department.equals("ventes")) {
            if (hoursWorked <= 44) {
                regularPay = hoursWorked * hourlyRate;
            } else {
                regularPay = 44 * hourlyRate;
                overtimePay = (hoursWorked - 44) * 1.5 * hourlyRate;

            }
        } else if (department.equals("ventes")) {
            regularPay = hoursWorked * hourlyRate;
            commissionPay = 0.015 * salesTotal;
        }

        double grossPay = regularPay + overtimePay + commissionPay;
        return grossPay;
    }
    private double calculateRrspContribution() {
        double rrspDeduction = 0.0495 * calculateGrossPay();
        return rrspDeduction;
    }

    // Method to calculate AI contribution
    private double calculateAiContribution() {
        double aiContribution = 0.0198 * calculateGrossPay();
        return aiContribution;
    }


}