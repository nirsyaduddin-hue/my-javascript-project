import java.util.*;

public class ManifestSettlement {
    private Stack<CarrierInfo> dispatchedStack;

    public ManifestSettlement() {
        this.dispatchedStack = new Stack<>();
    }

    public void processSettlement(QueueRouter router) {
        System.out.println("\n===============================================");
        System.out.println("PHASE 3 : Manifest Settlement Pipeline ");
        System.out.println("==============================================");
        System.out.println("\nProcessing carrier objects systematically in fixed batches of 5 across lanes...");
        System.out.println(".................Loading.........................................");
        
        // Mengambil data terus dari getter QueueRouter anda
        Queue<CarrierInfo> q1 = router.getRegionalQueue();
        Queue<CarrierInfo> q2 = router.getCrossBorderQueue();
        Queue<CarrierInfo> q3 = router.getIndustrialQueue();

        System.out.println("Result:");
        while (!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty()) {
            int count = 0;
            while (!q1.isEmpty() && count < 5) {
                dispatchedStack.push(q1.poll());
                count++;
            }
            count = 0;
            while (!q2.isEmpty() && count < 5) {
                dispatchedStack.push(q2.poll());
                count++;
            }
            count = 0;
            while (!q3.isEmpty() && count < 5) {
                dispatchedStack.push(q3.poll());
                count++;
            }
        }
        System.out.println("All distribution pipelines read empty! All manifests logged as 'cleared for departure'.");
    }

    public void generateDepartureLog() {
        System.out.println("\n=========================================================================================");
        System.out.println("                                TERMINAL DEPARTURE LOG                                  ");
        System.out.println("=========================================================================================");
        System.out.printf("%-22s | %-25s | %-20s | %-15s%n", 
                "Carrier Name", "Fleet Classification Type", "Total Shipments", "Total Carbon Cost");
        System.out.println("-----------------------------------------------------------------------------------------");

        while (!dispatchedStack.isEmpty()) {
            CarrierInfo clearedCarrier = dispatchedStack.pop();

            double totalCarbonCost = 0.0;
            for (ShipmentInfo shipment : clearedCarrier.getShipments()) {
                totalCarbonCost += shipment.getCarbonTaxCost();
            }

            System.out.printf("%-22s | %-25s | %-20d | RM %,.2f%n", 
                    clearedCarrier.getCarrierName(), 
                    clearedCarrier.getFleetType(), 
                    clearedCarrier.getTotalShipments(), 
                    totalCarbonCost);
        }
        System.out.println("=========================================================================================");
    }
}