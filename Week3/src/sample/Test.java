package sample;

import sample.model.Medicine;
import sample.service.MedicineService;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        MedicineService service = new MedicineService();

        List<Medicine> medicines =
                service.loadMedicines();

        for (Medicine medicine : medicines) {

            System.out.println(
                    medicine.getName()
                            + " | "
                            + medicine.getPrice()
                            + " | "
                            + medicine.getQuantity()
            );
        }
    }
}