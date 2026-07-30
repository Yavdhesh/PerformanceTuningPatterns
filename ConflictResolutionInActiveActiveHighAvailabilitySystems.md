# Conflict Resolution Flow in Active‑Active

**Local writes**:
* Node A writes row1 = valueA.
* Node B writes row1 = valueB.
* Both commits are accepted locally (availability first).

**Replication begins**:

* Node A sends its version (valueA) to Node B and other replicas.

* Node B sends its version (valueB) to Node A and other replicas.

* Each node receives the other’s update.

**Conflict detection**:

* When Node B receives valueA, it sees that row1 already has valueB.

* When Node A receives valueB, it sees that row1 already has valueA.

* Both nodes detect a conflict (same row, different versions).

**Conflict resolution (at nodes)**:

* Each node applies the configured resolution protocol:

* LWW (Last Write Wins): Compare timestamps, keep the newer one.

* Business logic: Merge values according to domain rules (e.g., merge shopping carts).

* CRDTs/vector clocks: Use causal history to merge both versions mathematically.

* After resolution, each node updates its local copy of row1 to the resolved value.

**Acknowledgement**:

* Once resolution is applied, the node acknowledges the replication message back to the sender (and cluster manager/coordinator).

* These acknowledgements are what the cluster manager uses to mark quorum durability.

**Cluster convergence**:

* Eventually, all nodes apply the same resolution logic.

* The cluster converges to a single consistent state for row1.
