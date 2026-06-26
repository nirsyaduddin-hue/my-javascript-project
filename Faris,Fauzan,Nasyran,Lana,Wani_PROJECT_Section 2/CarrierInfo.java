import java.util.ArrayList;
import java.util.List;

public class CarrierInfo {

    // Carrier details
    private String carrierId;
    private String carrierName;
    private String fleetType;

    // Stores carrier shipments
    private List<ShipmentInfo> shipments;

    // Initialize carrier object
    public CarrierInfo(String carrierId, String carrierName, String fleetType) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.fleetType = fleetType;
        this.shipments = new ArrayList<>();
    }

    // Add shipment to carrier
    public void addShipment(ShipmentInfo shipment) {
        this.shipments.add(shipment);
    }

    // Return carrier ID
    public String getCarrierId() {
        return carrierId;
    }

    // Return carrier name
    public String getCarrierName() {
        return carrierName;
    }

    // Return fleet type
    public String getFleetType() {
        return fleetType;
    }

    // Return shipment list
    public List<ShipmentInfo> getShipments() {
        return shipments;
    }

    // Count total shipments
    public int getTotalShipments() {
        return shipments.size();
    }
}