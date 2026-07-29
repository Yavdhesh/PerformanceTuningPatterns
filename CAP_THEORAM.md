# CAP THEORAM

If there’s a glitch in node communication (a network partition), the database cluster or load balancer can’t reach one of the nodes. At that moment, the system has to decide:

**Consistency first (CP)**:  
Reject or delay some requests until all nodes agree. This keeps the data correct, but sacrifices availability — some clients may see errors or timeouts.
→ This is what financial/trading systems usually choose, because correctness is more important than uptime.

**Availability first (AP)**:  
Keep serving requests from the reachable nodes, even if they can’t talk to the others. This ensures the system is responsive, but some reads may be stale or inconsistent.
→ This is what social media or shopping carts often choose, because user experience matters more than strict correctness.

**CA (Consistency + Availability)**:  
Only possible if you assume no partitions will ever happen — which is unrealistic in distributed systems. That’s why CAP says: during a partition, you can’t have all three.


So most of the systems would end up choosing one over the other in case of communications failures between databases.


**Consistency-first (CP systems)**
**Behavior**:

* If one node is unreachable, the cluster refuses to accept writes that can’t be replicated.

* Reads may be blocked until the system can guarantee the latest value.

* The database prioritizes correctness over uptime.

**Examples**:

Oracle RAC, PostgreSQL with synchronous replication, traditional RDBMS clusters.

**Result**:
Clients may see errors or timeouts, but no stale or conflicting data.
This is the choice for financial/trading systems — correctness is worth temporary unavailability.






**Availability-first (AP systems)**
**Behavior**:

* If one node is unreachable, the cluster continues serving requests from the available nodes.

* Writes are accepted locally and later reconciled (eventual consistency).

* The database prioritizes responsiveness over strict correctness.

**Examples**:

Cassandra, DynamoDB, Riak.

**Result**:

Clients always get a response, but some reads may be stale.
Conflicts are resolved later (e.g., “last write wins” or vector clocks).
This is common in social media, shopping carts, or apps where user experience matters more than strict accuracy.
