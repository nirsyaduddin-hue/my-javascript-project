# Eco-Friendly Supply Chain Logistics Management System

A Java-based enterprise logistics routing and settlement pipeline that models automated material handling and terminal tracking. This application showcases practical implementations of **Linear Data Structures (LinkedLists, Queues, and Stacks)** to load, route, and finalize carrier manifests while prioritizing ecological sustainability and calculating carbon tax compliance.

---

## 🏗️ System Architecture & Data Pipeline

The application processes data sequentially through three execution phases, moving carrier and shipment objects across different data structures to mimic a real-world automated logistics warehouse:

1. **Phase 1: Data Modeling & File Ingestion (LinkedList)**
   * Reads raw manifest details dynamically from an external source text file (`supply_chain_manifest.txt`).
   * Parses logistics streams and structures them into objects using a custom **Has-A relationship** model (`CarrierInfo` owns an internal `ArrayList` of `ShipmentInfo` payloads).
   * Aggregates carriers into a master sequential **`LinkedList`** without duplicate instances.

2. **Phase 2: Automated Conveyor Sorting (Queues)**
   * Ingests the sequential master list into a `QueueRouter`.
   * Evaluates each carrier's total payload weight via an optimized **volume threshold of 3 shipments**.
   * **Low-to-Moderate Volume ($\le$ 3)**: Dispatched in a fair, round-robin format alternating between **Queue 1 (Regional Micro-Distribution)** and **Queue 2 (Cross-Border Express)** via a toggle bit.
   * **High Volume (> 3)**: Directed immediately to **Queue 3 (Industrial Bulk Logistics Fleet)**.
   * Traverses active streams to generate localized environmental processing metrics and active lane sizes.

3. **Phase 3: Manifest Settlement Pipeline (Stack)**
   * Clears the active lanes systematically in fixed batches of 5 to uphold scheduling fairness across channels.
   * Dequeues 5 items from Q1, then 5 from Q2, then 5 from Q3 sequentially, looping cyclically until all distribution networks read empty.
   * Upon successful lane clearance, finalized objects are pushed directly onto a centralized tracking stack named **`dispatchedStack`**.
   * Executes continuous `.pop()` operations to print the definitive **Terminal Departure Log** in **Last-In, First-Out (LIFO)** execution sequence.

---

## 🛠️ Tech Stack & Language Standards
* **Language:** Java (JDK 8 or higher)
* **API Utilized:** Java Collections Framework (`java.util.LinkedList`, `java.util.Queue`, `java.util.Stack`, `java.util.ArrayList`)
* **File I/O:** `java.io.File`, `java.util.Scanner`
* **Layout Design:** Terminal Character Grid Formatting (Clean-code `printf` wrappers)

---

## 📁 Repository Structure

```text
├── Main.java                 # Ingestion driver, file reader, and coordinator
├── CarrierInfo.java          # Aggregates identity metadata and shipment bundles
├── ShipmentInfo.java         # Payload class keeping granular attributes and carbon costs
├── QueueRouter.java          # Implements multi-channel sorting and queue operations
├── ManifestSettlement.java   # Implements fair cyclic batch scheduling & dispatchedStack
└── supply_chain_manifest.txt # Raw comma-delimited data source text manifest




