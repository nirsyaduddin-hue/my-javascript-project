# Eco-Friendly Supply Chain Logistics Management System

A Java terminal application that simulates an automated cargo sorting and dispatch system. This project demonstrates how to use **LinkedLists, Queues, and Stacks** to read transport data, sort shipping carriers, and calculate carbon tax compliance.

---

## How Data Moves (Processing Phases)

The system processes data step-by-step through three phases, moving information across different data structures:

1. **Phase 1: Load Data (LinkedList)**
   * Reads data from an external text file (`supply_chain_manifest.txt`).
   * Loads all cargo carriers and their shipment items into a master **LinkedList**.

2. **Phase 2: Sort Carriers (Queues)**
   * Carriers are sent into 3 different conveyor lanes (**Queues**) based on how many shipments they are carrying:
     * **Queue 1 (Regional)** & **Queue 2 (Cross-Border)**: For smaller carriers with $\le$ 3 shipments (alternates between lanes to keep processing fair).
     * **Queue 3 (Industrial Bulk)**: For heavy carriers with > 3 shipments.

3. **Phase 3: Final Departure (Stack)**
   * Carriers are cleared from the queue lanes in fixed batches of 5.
   * Finalized carriers are pushed onto a tracking stack named **`dispatchedStack`**.
   * The system pops items from the stack to print the final **Terminal Departure Log** in Last-In, First-Out (LIFO) order.

---

##  Data Structures Used
* **`LinkedList`**: Used in Phase 1 to store the main list of carriers read from the file.
* **`Queue` (LinkedList implementation)**: Used in Phase 2 to simulate physical conveyor sorting lanes.
* **`Stack`**: Used in Phase 3 (`dispatchedStack`) to prepare the final departure order.

---

## How to Run the Project

### Prerequisites
Make sure you have **BlueJ or any JVM ** installed on your computer.

### Steps to Run
1. Open your terminal or command prompt.
2. Make sure your code files (`Main.java`, `QueueRouter.java`, `ManifestSettlement.java`, etc.) and `supply_chain_manifest.txt` are in the **same folder**.
3. Compile the Java files:
   ```bash
   javac *.java

##  Repository Structure

```text
├── Main.java                 # Ingestion driver, file reader, and coordinator
├── CarrierInfo.java          # Aggregates identity metadata and shipment bundles
├── ShipmentInfo.java         # Payload class keeping granular attributes and carbon costs
├── QueueRouter.java          # Implements multi-channel sorting and queue operations
├── ManifestSettlement.java   # Implements fair cyclic batch scheduling & dispatchedStack
└── supply_chain_manifest.txt # Raw comma-delimited data source text manifest




