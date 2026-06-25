import java.util.ArrayList;
import java.util.List;

public class CarrierInfo {
    private String carrierId;
    private String carrierName;
    private String fleetType;
    private List<ShipmentInfo> shipments; // Has-a relationship

    // Constructor
    public CarrierInfo(String carrierId, String carrierName, String fleetType) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.fleetType = fleetType;
        this.shipments = new ArrayList<>();
    }

    // Method untuk menambah shipment ke dalam carrier
    public void addShipment(ShipmentInfo shipment) {
        this.shipments.add(shipment);
    }

    // Getters
    public String getCarrierId() { return carrierId; }
    public String getCarrierName() { return carrierName; }
    public String getFleetType() { return fleetType; }
    public List<ShipmentInfo> getShipments() { return shipments; }
    
    // Mengira jumlah shipment yang dipegang
    public int getTotalShipments() {
        return shipments.size();
    }
}