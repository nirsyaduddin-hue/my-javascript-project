import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class Main
{
    // Stores all carriers (Phase 1)
    static LinkedList<CarrierInfo> carrierList = new LinkedList<CarrierInfo>();

    public static void main(String[] args)
    {
        try
        {
            // Load data from file
            loadData();

            // Display original LinkedList
            displayCarriers();

            // Create Queue router (Phase 2)
            QueueRouter router = new QueueRouter();

            // Route carriers into queues
            router.routeCarriers(carrierList);

            // Display all queue contents
            router.displayAllQueues();

            // Create Stack settlement (Phase 3)
            ManifestSettlement settlement = new ManifestSettlement();

            // Move carriers from Queue to Stack
            settlement.processSettlement(router);

            // Display departure log (LIFO)
            settlement.generateDepartureLog();
        }
        catch(Exception e)
        {
            // Display error message
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void loadData() throws Exception
    {
        // Read input file
        Scanner file = new Scanner(new File("supply_chain_manifest.txt"));

        while(file.hasNextLine())
        {
            String line = file.nextLine();

            // Skip empty lines
            if (line.trim().isEmpty()) continue;

            // Split data by comma
            String[] data = line.split(",");

            // Carrier information
            String carrierId = data[0].trim();
            String carrierName = data[1].trim();
            String fleetType = data[2].trim();

            // Shipment information
            String shipmentId = data[3].trim();
            String packageType = data[4].trim();

            int ecoScore = Integer.parseInt(data[5].trim());
            String dispatchDate = data[6].trim();
            int transitTime = Integer.parseInt(data[7].trim());
            double carbonTax = Double.parseDouble(data[8].trim());

            // Create shipment object
            ShipmentInfo shipment = new ShipmentInfo(
                            shipmentId,
                            packageType,
                            ecoScore,
                            dispatchDate,
                            transitTime,
                            carbonTax);

            CarrierInfo carrier = null;

            // Find existing carrier
            for(CarrierInfo c : carrierList)
            {
                if(c.getCarrierId().equals(carrierId))
                {
                    carrier = c;
                    break;
                }
            }

            // Add new carrier if not found
            if(carrier == null)
            {
                carrier = new CarrierInfo(
                                carrierId,
                                carrierName,
                                fleetType);

                carrierList.add(carrier);
            }

            // Add shipment to carrier
            carrier.addShipment(shipment);
        }

        // Close file
        file.close();
    }

    public static void displayCarriers()
    {
        // Display Phase 1 output
        System.out.println("\n===============================================");
        System.out.println("   PHASE 1: RAW LOADED CARRIER LINKEDLIST      ");
        System.out.println("===============================================");

        // Display each carrier
        for(CarrierInfo carrier : carrierList)
        {
            System.out.println("\n-----------------------");
            System.out.println("Carrier ID : " + carrier.getCarrierId());
            System.out.println("Carrier Name : " + carrier.getCarrierName());
            System.out.println("Fleet Type : " + carrier.getFleetType());
            System.out.println("Total Shipments : " + carrier.getTotalShipments());

            System.out.println("\nShipment List:");

            // Display all shipments
            for(ShipmentInfo s : carrier.getShipments())
            {
                System.out.println("  " + s);
            }
        }
    }
}