package Others;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UltilServices {
   public static String convertToDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }

    public static String convertFromDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }
    public static void main(String[] args) {
        String inputDate1 = "2022-04-24"; // Ngày nhập vào ở dạng yyyy/MM/dd
        String convertedDate1 = convertToDate(inputDate1);
        System.out.println("Ngày sau khi chuyển đổi: " + convertedDate1);

        String inputDate2 = "28-04-2024"; // Ngày nhập vào ở dạng dd/MM/yyyy
        String convertedDate2 = convertFromDate(inputDate2);
        System.out.println("Ngày sau khi chuyển đổi: " + convertedDate2);
    }
}
