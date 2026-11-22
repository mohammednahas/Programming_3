
package sample.service;

import sample.model.Medicine;

import java.util.List;

public class SalesService {

    private final MedicineService medicineService;

    public SalesService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // =====================================================
    // LOAD MEDICINES
    // =====================================================

    public List<Medicine> loadMedicines() {
        return medicineService.loadMedicines();
    }

    // =====================================================
    // SELL MEDICINE
    // =====================================================

    public boolean sellMedicine(
            String medicineName,
            int quantity,
            boolean useSynchronization) {

        if (useSynchronization) {

            return medicineService.sellMedicine(
                    medicineName,
                    quantity
            );

        } else {

            return medicineService.sellMedicineWithoutSync(
                    medicineName,
                    quantity
            );
        }
    }
}
