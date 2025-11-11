//Task1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TemperatureAverage {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://users.metropolia.fi/~jarkkov/temploki.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            String[] header = null;
            double sum = 0;
            int count = 0;

            // self refrence -> Finnish date format: dd.MM.yyyy HH:mm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH);

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(";");
                if (parts.length < 2) continue;

                String dateStr = parts[0].trim();  // ex: "01.01.2023 00:00"
                String tempStr = parts[1].trim();  // UlkoTalo column

                try {
                    LocalDate date = LocalDate.parse(dateStr, formatter);
                    if (date.getYear() == 2023 && date.getMonthValue() == 1 && date.getDayOfMonth() == 1) {
                        double temp = Double.parseDouble(tempStr.replace(",", "."));  // Handle comma decimal
                        sum += temp;
                        count++;
                    }
                } catch (Exception e) {
                    // Skip invalid rows
                    continue;
                }
            }

            reader.close();

            if (count == 0) {
                System.out.println("No data found for January 1, 2023.");
                return;
            }

            double average = sum / count;
            System.out.printf("Average temperature (UlkoTalo) on Jan 1, 2023: %.2f°C (%d readings)%n", average, count);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}