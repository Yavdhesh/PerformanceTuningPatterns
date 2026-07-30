# Write Flow
* Client sends update → Coordinator receives it.

* Coordinator identifies responsible node (via range partition or hash/token ring).

* **Replication begins**:

   * * **Primary‑based model**: Coordinator/primary applies the write, then replicates to others.

  * *  **Coordinator‑broadcast model(**: Coordinator sends the write to all nodes directly.

* **Quorum check**: Waits for enough ACKs (e.g., 3 of 5) to satisfy the write quorum.

* **Conflict resolution**: Vector clocks, CRDTs, or Last‑Write‑Wins ensure consistency if multiple writers update the same key.

* **Commit**: Once quorum is met, client gets success; remaining replicas sync asynchronously.


# Types of Sharding on Hash Rings
1. **Range‑based sharding (simple ranges)**
* Keys are divided into contiguous ranges (e.g., IDs 1–1000 → Node A, 1001–2000 → Node B).

* * Pros: Easy to reason about, deterministic.

* * Cons: Adding a new node requires large migrations (ranges must be split and redistributed).


2. **Consistent Hashing (classic hash ring)**
* Nodes are placed on a circular hash ring.

* Each node owns a segment of the ring.

* A key is hashed, and whichever node’s segment it falls into is the primary owner.

* Replicas for quorum are chosen by walking clockwise around the ring.

* * Pros: Adding/removing nodes only redistributes a fraction of keys.

* * Cons: Still some rebalancing required, but much less than range‑based.


3. **Token‑based hash ring (used in Cassandra, DynamoDB)**
*Instead of segments, nodes own specific tokens on the ring.

* A key is hashed to a token; the node owning that token is the primary.

* Replicas are again chosen clockwise for quorum.

* Pros: Fine‑grained control of distribution, avoids hotspots.

* Cons: Token assignment must be managed carefully; adding nodes requires token reallocation.


4. **Coordinator‑broadcast (multi‑leader / CRDT style)**
* Coordinator sends writes to all nodes, not just the primary.

* Waits until enough ACKs meet the write quorum.

* Conflict resolution (vector clocks, LWW, CRDTs) ensures convergence.

* * Pros: Any node can accept writes, high availability.

* * Cons: More complex conflict resolution, higher replication traffic.


**Cost Effectiveness**
* **Range‑based**: Simple but costly when scaling.

* **Hash ring (segments)**: Nodes own portions of the ring; replicas chosen clockwise.

* **Token‑based ring**: Nodes own tokens; keys map directly to tokens; replicas chosen clockwise.

* **Broadcast**: All nodes receive writes; quorum ACKs determine commit.


# Scalability vs Complexity
**Broadcast model**:

* ✅ Scales horizontally (any node can take writes).

* ✅ High availability (no single point of ownership).

* ❌ More replication traffic.

* ❌ Needs sophisticated conflict resolution (vector clocks, CRDTs).

**Range/Hash ring models**:

* ✅ Less replication traffic (only quorum subset involved).

* ✅ Simpler conflict handling (primary‑based ordering).

* ❌ Scaling requires rebalancing (ranges or tokens).

* ❌ Node ownership must be managed carefully.
