public class AgeCalculator {
    public static void dobToAge(int birthDay, int birthMonth, int birthYear, int refDay, int refMonth, int refYear) {
        if (!isValidDate(birthDay, birthMonth, birthYear) || !isValidDate(refDay, refMonth, refYear)
                || !isAfter(refDay, refMonth, refYear, birthDay, birthMonth, birthYear)) {
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
            case 4:
            case 6:
            case 9:
            case 11:
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
        if (refYear > birthYear)
            return true;
        if (refYear == birthYear && refMonth > birthMonth)
            return true;
        return refYear == birthYear && refMonth == birthMonth && refDay > birthDay;
    }

    private static int indexOf(String[] format, String dlc) {
        int index = 0;
        for (String str : format) {
            if (str.equalsIgnoreCase(dlc)) {
                return index;
            }
            ++index;
        }
        return -1;
    }

    public static void main(String[] args) {

        if (args[0].toUpperCase().contains("AGE")) {

            String[] arg1 = args[0].split("=");
            String age = arg1[1].replace("dlc", args[3]);

            String[] ageParts = age.split("dlc");

            String[] referenceDate = args[1].split("dlc");

            String[] dateFormat = args[2].split("dlc");

            int dateIndex = indexOf(dateFormat, "DD");
            int monthIndex = indexOf(dateFormat, "MM");
            int yearIndex = indexOf(dateFormat, "YYYY");

            if (dateIndex == -1 || monthIndex == -1 || yearIndex == -1) {
                System.out.println("Error has occurred :(");
                return;
            }

            int ageYear = Integer.parseInt(ageParts[yearIndex]);
            int ageMonth = Integer.parseInt(ageParts[monthIndex]);
            int ageDay = Integer.parseInt(ageParts[dateIndex]);

            int refYear = Integer.parseInt(referenceDate[yearIndex]);
            int refMonth = Integer.parseInt(referenceDate[monthIndex]);
            int refDay = Integer.parseInt(referenceDate[dateIndex]);

            ageToDOB(ageDay, ageMonth, ageYear, refDay, refMonth, refYear);
        } else if (args[0].toUpperCase().contains("DOB")) {

            String[] arg1 = args[0].split("=");
            String age = arg1[1].replace("dlc", args[3]);

            String[] ageParts = age.split(args[3]);

            String[] referenceDate = args[1].split("dlc");

            String[] dateFormat = args[2].split("dlc");

            int dateIndex = indexOf(dateFormat, "DD");
            int monthIndex = indexOf(dateFormat, "MM");
            int yearIndex = indexOf(dateFormat, "YYYY");

            if (dateIndex == -1 || monthIndex == -1 || yearIndex == -1) {
                System.out.println("Error has occurred :(");
                return;
            }

            int ageYear = Integer.parseInt(ageParts[yearIndex]);
            int ageMonth = Integer.parseInt(ageParts[monthIndex]);
            int ageDay = Integer.parseInt(ageParts[dateIndex]);

            int refYear = Integer.parseInt(referenceDate[yearIndex]);
            int refMonth = Integer.parseInt(referenceDate[monthIndex]);
            int refDay = Integer.parseInt(referenceDate[dateIndex]);

            dobToAge(ageDay, ageMonth, ageYear, refDay, refMonth, refYear);
        }

        else {
            System.out.println("The Command line arguments are not done correctly!");
        }

    }
}