import java.util.*;

public class AgeCalculator {
    public static void dobToAge(int birthDay, int birthMonth, int birthYear, int refDay, int refMonth, int refYear) {
        if (!isValidDate(birthDay, birthMonth, birthYear) || !isValidDate(refDay, refMonth, refYear) || !isAfter(refDay, refMonth, refYear, birthDay, birthMonth, birthYear)) {
            System.out.println("Invalid input dates.");
            return;
        }

        int year, month, day;

        year = refYear - birthYear;
        month = refMonth - birthMonth;
        day = refDay - birthDay;
        
        if (day < 1) {
            month--;
            day = day + daysInMonth(refMonth - 1, refYear);
        } 

        if (month < 0) {
            year--;
            month += 12;
        }

        System.out.print("The Age is (YYYY-MM-DD) : ");
        System.out.println(year + "-" + month + "-" + day);
    }

    public static void ageToDOB(int ageDay, int ageMonth, int ageYear, int refDay, int refMonth, int refYear) {
        if (!isValidDate(refDay, refMonth, refYear)) {
            System.out.println("Invalid input date.");
            return;
        }

        int year = refYear - ageYear;
        int month = refMonth - ageMonth;
        int day = refDay - ageDay;

        if (day < 1) {
            month--;
            day += daysInMonth(refMonth - 1, refYear);
        }

        if (month < 1) {
            year--;
            month += 12;
        }

        if (!isValidDate(day, month, year)) {
            System.out.println("Resulting date is invalid.");
            return;
        }

        System.out.print("The Date of Birth is (YYYY-MM-DD) : ");
        System.out.println(year + "-" + month + "-" + day);
    }

    private static boolean isValidDate(int day, int month, int year) {
        if (year < 0 || month < 1 || month > 12 || day < 1 || day > daysInMonth(month, year)) {
            return false;
        }
        return true;
    }

    private static int daysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (isLeapYear(year)) ? 29 : 28;
            default:
                return 31;
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static boolean isAfter(int refDay, int refMonth, int refYear, int birthDay, int birthMonth, int birthYear) {
        if (refYear > birthYear) return true;
        if (refYear == birthYear && refMonth > birthMonth) return true;
        return refYear == birthYear && refMonth == birthMonth && refDay > birthDay;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("**** WELCOME ****");
        System.out.println("Enter the number\n1. Calculate Age\n2. Calculate Date Of Birth");
        int n = sc.nextInt();
        sc.nextLine();
        switch(n) {
            case 1: 
                System.out.println("Enter Reference Date in format (YYYY-MM-DD): ");
                String[] refParts = sc.nextLine().split("-");
                System.out.println("To Calculate AGE, Enter the Date Of Birth of the person in format (YYYY-MM-DD): ");
                String[] ageParts = sc.nextLine().split("-");
                int ageYear = Integer.parseInt(ageParts[0]);
                int ageMonth = Integer.parseInt(ageParts[1]);
                int ageDay = Integer.parseInt(ageParts[2]);
                int refYear = Integer.parseInt(refParts[0]);
                int refMonth = Integer.parseInt(refParts[1]);
                int refDay = Integer.parseInt(refParts[2]);
                dobToAge(ageDay, ageMonth, ageYear, refDay, refMonth, refYear);
                break;

            case 2:
                System.out.println("Enter Reference Date in format (YYYY-MM-DD): ");
                refParts = sc.nextLine().split("-");
                System.out.println("To Calculate Date of Birth, Enter the age of the person in format (YYYY-MM-DD): ");
                ageParts = sc.nextLine().split("-");
                ageYear = Integer.parseInt(ageParts[0]);
                ageMonth = Integer.parseInt(ageParts[1]);
                ageDay = Integer.parseInt(ageParts[2]);
                refYear = Integer.parseInt(refParts[0]);
                refMonth = Integer.parseInt(refParts[1]);
                refDay = Integer.parseInt(refParts[2]);
                ageToDOB(ageDay, ageMonth, ageYear, refDay, refMonth, refYear);
                break;

            default:
                System.out.println("Invalid Input");
        }

        System.out.println("hello");
        
        sc.close();
    }
}