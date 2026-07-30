# Redundancy and Quoram


# Redundancy

**Definition**: Having multiple copies of data or multiple servers that can serve the same workload.

**Why it matters**:

* Protects against hardware failures (disk crash, node down).
* Ensures high availability — if one node fails, another can take over.
* Improves scalability — read replicas can absorb traffic.

**Forms of redundancy**:

**Active‑Passive**: One write node, others standby.

**Active‑Active**: Multiple write nodes, eventual consistency.

**Geo‑redundancy**: Copies across regions for disaster recovery.



# Replication
**Replication**: Synchronous (strong consistency) vs. asynchronous (eventual consistency).

# Quorum

**Definition**: The minimum number of nodes that must agree on a transaction before it’s considered committed.

**Why it matters**: 
Prevents “split‑brain” scenarios where different nodes think they are the leader.

**How it works**:

In a 3‑node cluster, quorum = 2.

A write is only accepted if at least 2 nodes confirm.

This ensures consistency even if one node is down.

Trade‑off: Higher quorum = stronger consistency but lower availability. Lower quorum = higher availability but risk of stale data.

Example: Cassandra lets you configure QUORUM, ONE, or ALL for reads/writes depending on your consistency vs. availability needs.

**Active‑Passive**: Quorum = commit correctness (transaction is committed only if majority agrees).

**Active‑Active**: Quorum = durability (data won’t be lost), but conflict resolution is still required because multiple leaders can diverge.
