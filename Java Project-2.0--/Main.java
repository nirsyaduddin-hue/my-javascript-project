import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class Main
{
    // Storage untuk Phase 1 (LinkedList)
    static LinkedList<CarrierInfo> carrierList = new LinkedList<CarrierInfo>();

    public static void main(String[] args)
    {
        try
        {
            // =================================================================
            // PHASE 1: DATA MODELING & FILE LOADING
            // =================================================================
            loadData();
            displayCarriers(); // Memaparkan senarai LinkedList asal yang dibaca dari fail
            
            // =================================================================
            // PHASE 2: DISTRIBUTION ROUTING (QUEUE)
            // =================================================================
            QueueRouter router = new QueueRouter();
            
            // Sila perhatikan di sini:
            // Fungsi ini akan mencetak terus tanda '✓ Carrier Name → QUEUE X' 
            // sebijik sama seperti dalam gambar kod yang anda berikan tadi.
            router.routeCarriers(carrierList); 
            
            // Selepas selesai routing, fungsi ini akan memaparkan isi kandungan penuh 
            // bagi setiap lane queue beserta pengiraan Carbon Tax.
            router.displayAllQueues();
            
            // =================================================================
            // PHASE 3: MANIFEST SETTLEMENT (STACK)
            // =================================================================
            ManifestSettlement settlement = new ManifestSettlement();
            
            // Memindahkan data dari Queue ke Stack secara berkumpulan (batch of 5)
            settlement.processSettlement(router);
            
            // Mengeluarkan Terminal Departure Log (LIFO) menggunakan Stack Pop
            settlement.generateDepartureLog();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void loadData() throws Exception
    {
        Scanner file = new Scanner(new File("supply_chain_manifest.txt"));

        while(file.hasNextLine())
        {
            String line = file.nextLine();
            if (line.trim().isEmpty()) continue; // Abaikan jika ada baris kosong bawah fail

            String[] data = line.split(",");

            String carrierId = data[0].trim();
            String carrierName = data[1].trim();
            String fleetType = data[2].trim();

            String shipmentId = data[3].trim();
            String packageType = data[4].trim();

            int ecoScore = Integer.parseInt(data[5].trim());
            String dispatchDate = data[6].trim();
            int transitTime = Integer.parseInt(data[7].trim());
            double carbonTax = Double.parseDouble(data[8].trim());

            ShipmentInfo shipment = new ShipmentInfo(
                            shipmentId,
                            packageType,
                            ecoScore,
                            dispatchDate,
                            transitTime,
                            carbonTax);

            CarrierInfo carrier = null;

            for(CarrierInfo c : carrierList)
            {
                if(c.getCarrierId().equals(carrierId))
                {
                    carrier = c;
                    break;
                }
            }

            if(carrier == null)
            {
                carrier = new CarrierInfo(
                                carrierId,
                                carrierName,
                                fleetType);

                carrierList.add(carrier);
            }

            carrier.addShipment(shipment);
        }

        file.close();
    }

    public static void displayCarriers()
    {
        System.out.println("\n===============================================");
        System.out.println("   PHASE 1: RAW LOADED CARRIER LINKEDLIST      ");
        System.out.println("===============================================");
        for(CarrierInfo carrier : carrierList)
        {
            System.out.println("\n-----------------------");
            System.out.println("Carrier ID : " + carrier.getCarrierId());
            System.out.println("Carrier Name : " + carrier.getCarrierName());
            System.out.println("Fleet Type : " + carrier.getFleetType());
            System.out.println("Total Shipments : " + carrier.getTotalShipments());
            System.out.println("\nShipment List:");

            for(ShipmentInfo s : carrier.getShipments())
            {
                System.out.println("  " + s);
            }
        }
        
    }
}