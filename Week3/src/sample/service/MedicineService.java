
package sample.service;

import sample.model.Medicine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicineService {

    // Use the existing Data folder in the project
    private final String filePath = "Data/medicines.txt";

    // =====================================================
    // LOAD MEDICINES
    // =====================================================

    public  List<Medicine> loadMedicines() {

        List<Medicine> medicines = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 3) {
                    continue;
                }

                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int quantity = Integer.parseInt(parts[2]);

                medicines.add(
                        new Medicine(name, price, quantity)
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return medicines;
    }

    // =====================================================
    // ADD MEDICINE
    // =====================================================

    public  void addMedicine(Medicine medicine) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(filePath, true))) {

            writer.write(
                    medicine.getName() + ","
                            + medicine.getPrice() + ","
                            + medicine.getQuantity()
            );

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // UPDATE MEDICINE
    // =====================================================

    public  void updateMedicine(
            Medicine oldMedicine,
            Medicine updatedMedicine) {

        List<Medicine> medicines = loadMedicines();

        for (int i = 0; i < medicines.size(); i++) {

            Medicine medicine = medicines.get(i);

            if (medicine.getName().equalsIgnoreCase(
                    oldMedicine.getName())) {

                medicines.set(i, updatedMedicine);
                break;
            }
        }

        try {
            saveMedicines(medicines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     // Without Sync

    public boolean sellMedicineWithoutSync(
            String medicineName,
            int requestedQuantity) {

        if (requestedQuantity <= 0) {
            return false;
        }

        List<Medicine> medicines = loadMedicines();

        for (int i = 0; i < medicines.size(); i++) {

            Medicine medicine = medicines.get(i);

            if (medicine.getName().equalsIgnoreCase(medicineName)) {

                if (medicine.getQuantity() < requestedQuantity) {
                    return false;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }

                int remainingQuantity =
                        medicine.getQuantity() - requestedQuantity;

                medicines.set(i, new Medicine(
                        medicine.getName(),
                        medicine.getPrice(),
                        remainingQuantity
                ));

                try {
                    saveMedicines(medicines);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return false;
    }




    // =====================================================
    //   SELL MEDICINE   with sync
    // =====================================================

    public synchronized boolean sellMedicine(
            String medicineName,
            int requestedQuantity) {

        if (requestedQuantity <= 0) {
            return false;
        }

        // Read the latest stock
        List<Medicine> medicines = loadMedicines();

        for (int i = 0; i < medicines.size(); i++) {

            Medicine medicine = medicines.get(i);

            if (medicine.getName().equalsIgnoreCase(medicineName)) {

                // Check available quantity
                if (medicine.getQuantity() < requestedQuantity) {
                    return false;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }

                // Calculate remaining stock
                int remainingQuantity =
                        medicine.getQuantity() - requestedQuantity;

                // Create updated medicine object
                Medicine updatedMedicine = new Medicine(
                        medicine.getName(),
                        medicine.getPrice(),
                        remainingQuantity
                );

                medicines.set(i, updatedMedicine);

                // Save the updated stock
                try {
                    saveMedicines(medicines);
                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        // Medicine not found
        return false;
    }

    // =====================================================
    // SAVE ALL MEDICINES
    // =====================================================

    private void saveMedicines(
            List<Medicine> medicines) throws IOException {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filePath))) {

            for (Medicine medicine : medicines) {

                writer.write(
                        medicine.getName() + ","
                                + medicine.getPrice() + ","
                                + medicine.getQuantity()
                );

                writer.newLine();
            }
        }
    }
}
