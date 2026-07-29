# ACTIVE-PASSIVE(CONSISTENCY FIRST) vs ACTIve-ACTIVE (AVAILABILITY FIRST) SYSTEMS


| Aspect | **Active‑Passive** | **Active‑Active (Passive‑Passive)** |
| --- | --- | --- |
| **Write Handling** | Only one **active leader** accepts writes. Passive nodes replicate but don’t accept writes. | Multiple nodes accept writes simultaneously. Each node can be a leader. |
| **Read Handling** | Reads can be served from passive replicas (often synchronous). | Reads can be served from any node, but may be stale until reconciliation. |
| **Consistency** | Strong consistency — all writes go through one leader, replicas are in sync. | Eventual consistency — nodes may diverge, conflicts resolved later. |
| **Availability** | If the leader fails, passive node is promoted (automatic failover). Some downtime during failover. | High availability — if one node fails, others continue serving immediately. |
| **Replication** | Synchronous replication from leader to replicas. | Asynchronous replication between leaders and replicas. |
| **Conflict Handling** | No conflicts (single write path). | Conflicts possible — resolved via **Last Write Wins, business rules, or CRDTs**. |
| **Use Cases** | Banking, trading, financial systems where correctness is critical. | Social media, e‑commerce, global apps where responsiveness matters more. |
| **Complexity** | Simpler to manage, safer for correctness. | More complex — requires reconciliation logic and careful design. |
| **Example Scenario** | 🏦 **Bank account update:** One active DB processes a $1M deposit. Replicas confirm synchronously. If leader fails mid‑transaction, it rolls back to preserve correctness. | 🌍 **Social media “like”:** Two DBs in different regions both accept a “like” at the same time. One shows count = 101, the other = 102. Later reconciliation merges them to 102 (or resolves via LWW). |
| **Real Systems** | Oracle RAC (active‑passive), PostgreSQL synchronous replication clusters. | Cassandra, DynamoDB, Riak (active‑active, eventual consistency). |
