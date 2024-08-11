import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> profit = new ArrayList<>();
        List<Double> rdSpend = new ArrayList<>();
        List<Double> marketingSpend = new ArrayList<>();

        try {
            FileReader archivoFisico = new FileReader("startup-profit.csv");
            BufferedReader archivoLogico = new BufferedReader(archivoFisico);

            String registro;
            archivoLogico.readLine(); // Leer la primera línea (encabezado)

            while ((registro = archivoLogico.readLine()) != null) {
                String[] campos = registro.split(",");
                if (campos.length > 3) {
                    profit.add(Double.parseDouble(campos[4]));
                    rdSpend.add(Double.parseDouble(campos[0]));
                    marketingSpend.add(Double.parseDouble(campos[2]));
                }
            }

            archivoLogico.close();
            archivoFisico.close();

            double corrProfitRd = calcularCorrelacion(profit, rdSpend);
            double corrProfitMarketing = calcularCorrelacion(profit, marketingSpend);

            System.out.println("Coeficiente de correlación entre Profit y R&D Spend: " + corrProfitRd);
            System.out.println("Coeficiente de correlación entre Profit y Marketing Spend: " + corrProfitMarketing);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double calcularCorrelacion(List<Double> x, List<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Las listas deben tener el mismo tamaño");
        }

        int n = x.size();
        double sumX = 0.0, sumY = 0.0, sumXY = 0.0;
        double sumX2 = 0.0, sumY2 = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += x.get(i);
            sumY += y.get(i);
            sumXY += x.get(i) * y.get(i);
            sumX2 += Math.pow(x.get(i), 2);
            sumY2 += Math.pow(y.get(i), 2);
        }

        double numerator = (n * sumXY) - (sumX * sumY);
        double denominator = Math.sqrt((n * sumX2 - Math.pow(sumX, 2)) * (n * sumY2 - Math.pow(sumY, 2)));

        if (denominator == 0) {
            return 0;
        } else {
            return numerator / denominator;
        }
    }
}
