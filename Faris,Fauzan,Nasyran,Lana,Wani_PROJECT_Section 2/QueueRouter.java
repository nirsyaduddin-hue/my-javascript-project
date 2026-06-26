import java.util.*;

public class QueueRouter {
    // Three Queue objects for conveyor distribution
    private Queue<CarrierInfo> regionalQueue;    // Q1
    private Queue<CarrierInfo> crossBorderQueue; // Q2
    private Queue<CarrierInfo> industrialQueue;  // Q3
    
    // Alternating flag for low-to-moderate volume distribution between Q1 and Q2
    private boolean toggle = true;
    private static final int Volume_ThresHold = 3; // Constant volume threshold

    // Text constant to prevent code redundancy (Clean Code)
    private static final String FMT_ROUTING = "✓ %-25s → QUEUE %d: %s (%d shipments)%n";

    // Constructor - Initializes all Queue objects
    public QueueRouter() {
        this.regionalQueue = new LinkedList<>();
        this.crossBorderQueue = new LinkedList<>();
        this.industrialQueue = new LinkedList<>();
    }
    
    // Getters for Queue objects to allow external access (Phase 3)
    public Queue<CarrierInfo> getRegionalQueue() { return regionalQueue; }
    public Queue<CarrierInfo> getCrossBorderQueue() { return crossBorderQueue; }
    public Queue<CarrierInfo> getIndustrialQueue() { return industrialQueue; }

    /**
     * Routes carriers into automated conveyor sorting lanes based on shipment volume.
     */
    public void routeCarriers(LinkedList<CarrierInfo> carrierList) {
        System.out.println("\n===============================================");
        System.out.println("PHASE 2 : Distribution Routing Using Queues ");
        System.out.println("==============================================");
        System.out.println("Routing carriers based on shipment volume");
        System.out.println("Threshold: ≤ " + Volume_ThresHold + " shipments → Queue 1/2 (alternating)");
        System.out.println("           > " + Volume_ThresHold + " shipments → Queue 3 (Industrial Bulk)");
        System.out.println("-------------------------------------------------------------------------");
        
        int queue1Count = 0;
        int queue2Count = 0;
        int queue3Count = 0;
        
        for (CarrierInfo carrier : carrierList) {  
            int shipmentCount = carrier.getTotalShipments();
            
            if (shipmentCount <= Volume_ThresHold) {
                // Low-to-Moderate Volume: Alternate between Queue 1 and Queue 2
                if (toggle) {
                    regionalQueue.add(carrier);  // Enqueue operation
                    queue1Count++;
                    System.out.printf(FMT_ROUTING, carrier.getCarrierName(), 1, "Regional Micro-Distribution", shipmentCount);
                } else {
                    crossBorderQueue.add(carrier); // Enqueue operation
                    queue2Count++;
                    System.out.printf(FMT_ROUTING, carrier.getCarrierName(), 2, "Cross-Border Express", shipmentCount);
                }
                toggle = !toggle; // Toggle flag for the next carrier
            } else {
                // High Volume: Direct routing to Queue 3
                industrialQueue.add(carrier); // Enqueue operation
                queue3Count++;
                System.out.printf(FMT_ROUTING, carrier.getCarrierName(), 3, "Industrial Bulk Logistics", shipmentCount);
            }
        }
        
        // Display final routing stats
        System.out.println("\nRouting complete! Queue Statistics:");
        System.out.println("  Queue 1 (Regional): " + queue1Count + " carriers");
        System.out.println("  Queue 2 (Cross-Border): " + queue2Count + " carriers");
        System.out.println("  Queue 3 (Industrial): " + queue3Count + " carriers");
    }   
    
    /**
     * Displays all active queue data streams.
     */
    public void displayAllQueues() {
        System.out.println("\n=================== QUEUE DISPLAY ===================");
        displaySingleQueue("Regional Micro-Distribution Queue (Queue 1)", regionalQueue);
        System.out.println();
        displaySingleQueue("Cross-Border Express Queue (Queue 2)", crossBorderQueue);
        System.out.println();
        displaySingleQueue("Industrial Bulk Logistics Fleet (Queue 3)", industrialQueue);
        System.out.println("=======================================================");
    }
    
    /**
     * Traverses and prints details of a single queue without modifying its contents.
     */
    private void displaySingleQueue(String queueName, Queue<CarrierInfo> queue) {
        System.out.println("\n========== " + queueName + " ==========");
        
        if (queue.isEmpty()) {  // Check if the conveyor lane is empty
            System.out.println("Queue is empty.");
            return;
        }
        
        int carrierCount = 0;
        double totalCarbonTax = 0.0;
        
        for (CarrierInfo carrier : queue) {  
            carrierCount++;
            System.out.printf("\nCarrier %d: %s%n", carrierCount, carrier.getCarrierName());
            System.out.println("  Fleet Classification: " + carrier.getFleetType());
            System.out.println("  Total Shipments: " + carrier.getTotalShipments());
            
            if (!carrier.getShipments().isEmpty()) {
                System.out.println("  Assigned Shipments:");
                for (ShipmentInfo shipment : carrier.getShipments()) {
                    System.out.printf("    - %s | %s | EcoScore: %d | Carbon Tax: RM %.2f%n",
                                     shipment.getShipmentId(),
                                     shipment.getPackageType(),
                                     shipment.getEcoPriorityScore(),
                                     shipment.getCarbonTaxCost());
                    totalCarbonTax += shipment.getCarbonTaxCost();
                }
            }
        }
        
        // Summarized metrics for the current lane
        System.out.printf("%n  Total Carbon Tax Costs in Lane: RM %.2f%n", totalCarbonTax);
        System.out.println("  Total Carriers in Lane: " + carrierCount);
        System.out.println("  Queue Size: " + queue.size());  
    }
    
    /**
     * Checks whether all distribution conveyor pipelines are entirely cleared.
     */
    public boolean areAllQueuesEmpty() {
        return regionalQueue.isEmpty() && crossBorderQueue.isEmpty() && industrialQueue.isEmpty();
    }
}