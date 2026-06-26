import java.util.*;

public class ManifestSettlement {

    // Stores dispatched carriers
    private Stack<CarrierInfo> dispatchedStack;

    public ManifestSettlement() {
        // Initialize stack
        this.dispatchedStack = new Stack<>();
    }

    public void processSettlement(QueueRouter router) {

        // Phase 3 header
        System.out.println("\n===============================================");
        System.out.println("PHASE 3 : Manifest Settlement Pipeline ");
        System.out.println("==============================================");
        System.out.println("\nProcessing carrier objects systematically in fixed batches of 5 across lanes...");
        System.out.println(".................Loading.........................................");

        // Get all routing queues
        Queue<CarrierInfo> q1 = router.getRegionalQueue();
        Queue<CarrierInfo> q2 = router.getCrossBorderQueue();
        Queue<CarrierInfo> q3 = router.getIndustrialQueue();

        System.out.println("Result:");

        // Process until all queues are empty
        while (!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty()) {

            int count = 0;

            // Move 5 carriers from Queue 1
            while (!q1.isEmpty() && count < 5) {
                dispatchedStack.push(q1.poll());
                count++;
            }

            count = 0;

            // Move 5 carriers from Queue 2
            while (!q2.isEmpty() && count < 5) {
                dispatchedStack.push(q2.poll());
                count++;
            }

            count = 0;

            // Move 5 carriers from Queue 3
            while (!q3.isEmpty() && count < 5) {
                dispatchedStack.push(q3.poll());
                count++;
            }
        }

        // Settlement completed
        System.out.println("All distribution pipelines read empty! All manifests logged as 'cleared for departure'.");
    }

    public void generateDepartureLog() {

        // Display departure log
        System.out.println("\n=========================================================================================");
        System.out.println("                                TERMINAL DEPARTURE LOG                                  ");
        System.out.println("=========================================================================================");
        System.out.printf("%-22s | %-25s | %-20s | %-15s%n",
            "Carrier Name", "Fleet Classification Type", "Total Shipments", "Total Carbon Cost");
        System.out.println("-----------------------------------------------------------------------------------------");

        // Display carriers in LIFO order
        while (!dispatchedStack.isEmpty()) {

            // Remove top carrier
            CarrierInfo clearedCarrier = dispatchedStack.pop();

            // Calculate total carbon cost
            double totalCarbonCost = 0.0;
            for (ShipmentInfo shipment : clearedCarrier.getShipments()) {
                totalCarbonCost += shipment.getCarbonTaxCost();
            }

            // Display carrier details
            System.out.printf("%-22s | %-25s | %-20d | RM %,.2f%n",
                clearedCarrier.getCarrierName(),
                clearedCarrier.getFleetType(),
                clearedCarrier.getTotalShipments(),
                totalCarbonCost);
        }

        // End of report
        System.out.println("=========================================================================================");
    }
}