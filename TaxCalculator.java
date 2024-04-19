package com.demo123;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TaxCalculator {
    
    // Tax slabs
    private static final double SLAB_1 = 250000;
    private static final double SLAB_2 = 500000;
    private static final double SLAB_3 = 1000000;
    private static final double SLAB_4_RATE = 0.20; // 20%
    private static final double CESS_RATE = 0.02; // 2%
    
    // Function to calculate tax
    public static double calculateTax(double yearlySalary, LocalDate dateOfJoining) {
        // Calculate total salary based on DOJ
        double totalSalary = calculateTotalSalary(yearlySalary, dateOfJoining);
        
        // Calculate tax without cess
        double taxWithoutCess = 0;
        if (totalSalary <= SLAB_1) {
            // No tax
            taxWithoutCess = 0;
        } else if (totalSalary <= SLAB_2) {
            // 5% tax for the amount above 250000
            taxWithoutCess = (totalSalary - SLAB_1) * 0.05;
        } else if (totalSalary <= SLAB_3) {
            // 10% tax for the amount above 500000
            taxWithoutCess = (SLAB_2 - SLAB_1) * 0.05 + (totalSalary - SLAB_2) * 0.10;
        } else {
            // 20% tax for the amount above 1000000
            taxWithoutCess = (SLAB_2 - SLAB_1) * 0.05 + (SLAB_3 - SLAB_2) * 0.10 + (totalSalary - SLAB_3) * SLAB_4_RATE;
        }
        
        // Calculate cess if applicable
        double cess = 0;
        if (totalSalary > 2500000) {
            cess = (totalSalary - 2500000) * CESS_RATE;
        }
        
        // Total tax including cess
        double totalTax = taxWithoutCess + cess;
        
        return totalTax;
    }
    
    // Function to calculate total salary based on DOJ
    private static double calculateTotalSalary(double yearlySalary, LocalDate dateOfJoining) {
        // Calculate the number of months worked in the year
        long monthsWorked = ChronoUnit.MONTHS.between(dateOfJoining.withDayOfMonth(1), LocalDate.now().withDayOfMonth(1)) + 1;
        double totalSalary = yearlySalary * monthsWorked / 12;
        return totalSalary;
    }
    
    public static void main(String[] args) {
        // Example usage
        double yearlySalary = 750000;
        LocalDate dateOfJoining = LocalDate.of(2023, 5, 16); // Example DOJ
        double tax = calculateTax(yearlySalary, dateOfJoining);
        System.out.println("Total tax: " + tax);
    }
}
