import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader archivoFisico = new FileReader("codigos_postales_hmo.csv");
            BufferedReader archivoLogico = new BufferedReader(archivoFisico);

            String registro;
            String campos[];

            Map<String, Integer> conteoAsentamientos = new TreeMap<>();

            while ((registro = archivoLogico.readLine()) != null) {
                campos = registro.split("\t");
                if (campos.length > 1) {
                    String codigoPostal = campos[0];

                    conteoAsentamientos.put(codigoPostal, conteoAsentamientos.getOrDefault(codigoPostal, 0) + 1);
                }
            }
            System.out.println("codigo postal | total de asentamientos ");
            for (Map.Entry<String, Integer> entrada : conteoAsentamientos.entrySet()) {
                System.out.println( " " + entrada.getKey() + "             " + entrada.getValue());
            }

            archivoLogico.close();
            archivoFisico.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
Describe la estrategía general que vas a seguir para desarrollar el programa.
El objetivo es contar cuántos asentamientos existen para cada código postal en Hermosillo,
el archivo codigos_postales_hmo.csv contiene dos columnas con el código postal y el nombre
del asentamiento. El conteo de asentamientos se realiza con un Map, en este caso
implementado como TreeMap para mantener ordenados los códigos postales y para almacenar el
conteo de asentamientos por cada código postal.

¿Cuántas horas estimas que requieres invertir en el proyecto?
    Estimado: 4 horas
    Tiempo real: 6 horas

¿Qué aspecto consideras pudiera ser un reto significativo?
    El reto que se me presento fue encontrar una libreria que mostrara los codigos postales
    del menor al mayor y adaptarlo para usar un delimitador diferente de las comas, en este caso el "tap"
*/