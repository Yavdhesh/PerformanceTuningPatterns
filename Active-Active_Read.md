
# Read Operation over cluster with replication factor and read Quoram
------------------------------------------------------------------------------------------------------------------------------------------------------

# Range‑based Reads
Primary identified by the range (e.g., IDs 1–1000 → Node A).

Replicas are fixed by cluster metadata (RF decides how many).

Read quorum (R): Coordinator queries primary + replicas until R responses are collected.

Version comparison: Responses are compared (timestamps, vector clocks, or version numbers).

Result: The value agreed upon by quorum is returned to the client.

# Hash/Token Ring Reads
Primary identified by hashing the key → token owner.

Replicas chosen by walking clockwise until RF is met.

Read quorum (R): Coordinator queries primary + replicas until R responses are collected.

Version comparison: Vector clocks or similar metadata resolve conflicts.

Result: Majority/quorum value is returned.

# Broadcast Reads
All nodes are peers (RF = total cluster size).

Coordinator queries all nodes (or a subset).

Read quorum (R): Coordinator waits until R responses are collected.

Version comparison: Vector clocks/CRDTs/LWW ensure convergence.

Result: The consistent value from quorum is returned.

# Key Takeaway
Replication Factor (RF): How many nodes store the data.

Write Quorum (W): How many ACKs needed for durability.

Read Quorum (R): How many responses needed for consistency.

Range: Primary + fixed replicas.

Hash ring: Primary + clockwise replicas.

Broadcast: All nodes are replicas.

------------------------------------------------------------------------------------------------------------------------------------------------------
# Short Tips

Range → replicas fixed by metadata.

Hash ring → replicas chosen clockwise.

Broadcast → all nodes replicate.

Reads use read quorum with version comparison to ensure consistency.
