import java.io.*;
import java.util.*;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta nama file dari pengguna
        System.out.print("Masukkan nama file teks (dengan ekstensi, e.g., input.txt): ");
        String fileName = scanner.nextLine();

        // Membaca isi file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
            return;
        }

        if (lines.isEmpty()) {
            System.out.println("File kosong atau tidak ditemukan.");
            return;
        }

        // Meminta jumlah bagian dari pengguna
        System.out.print("Masukkan jumlah bagian untuk memotong file: ");
        int numParts = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        if (numParts <= 0) {
            System.out.println("Jumlah bagian harus lebih dari 0.");
            return;
        }

        // Menggunakan Queue untuk menyimpan bagian-bagian
        Queue<String> partsQueue = new LinkedList<>();

        // Membagi isi file menjadi bagian-bagian
        int totalLines = lines.size();
        int linesPerPart = totalLines / numParts;
        int remainder = totalLines % numParts;

        int start = 0;
        for (int i = 0; i < numParts; i++) {
            int end = start + linesPerPart + (i < remainder ? 1 : 0);
            StringBuilder part = new StringBuilder();
            for (int j = start; j < end; j++) {
                part.append(lines.get(j)).append("\n");
            }
            partsQueue.add(part.toString());
            start = end;
        }

        // Menampilkan isi queue (bagian-bagian file)
        System.out.println("\nFile telah dipotong menjadi " + numParts + " bagian:");
        int partNumber = 1;
        while (!partsQueue.isEmpty()) {
            System.out.println("Bagian " + partNumber + ":");
            System.out.println(partsQueue.poll());
            partNumber++;
        }

        scanner.close();
    }
}
