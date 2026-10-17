



# Basic Hints on Database Performance Tuning techniques

# 1. CPU high, RAM low → optimize first
# State: CPU high, RAM low, I/O low

# Action:

* Optimize queries & schema based on access patterns.

* Goal: Bring CPU down without touching infra.

# If CPU is still high after serious optimization:
# State: CPU high, RAM still low, I/O low

# Action:

* Add cores (vertical scaling).

# Result: More parallelism, CPU usage drops.

# 2. Traffic grows → RAM becomes the bottleneck
# State: Traffic increases, RAM high, CPU stable or moderate, buffer hit ratio falling, I/O rising

# Meaning: Working set no longer fits fully in memory.

# Action:

* Increase RAM (vertical scaling).

# Result: Higher buffer hit ratio, fewer disk reads, lower latency.

# 3. RAM high + CPU high → vertical first, then replicas
# If you see:
# State: RAM high, CPU high, buffer hit ratio okay, I/O not crazy

# Action:

* First: vertical scaling (more cores + RAM).
* Then, if reads dominate: add read replicas.
* Check read/write ratio first.

# If reads ≫ writes → replicas.

# If writes ≫ reads → sharding (after vertical scaling).

# Read replicas:

* Primary: handles all writes.

* Replicas: handle most reads.

# Trade‑off: replication lag vs read scalability.

# Benefit: lower read latency, higher read throughput.

4. Read traffic heavy → caching + connection multiplexing
You nailed this part:

State: Read traffic increases, replicas busy, many connections.

Actions:

Connection multiplexing/pooling (PgBouncer, ProxySQL, ORM pools).

Redis / app‑level cache for hot reads.

Result:

Fewer DB round‑trips.

Fewer active DB connections.

Lower CPU per request.

Read traffic stabilizes.

5. Write traffic becomes the bottleneck → sharding
But now if write traffic increases -> we are left with no options but to shard.

Yes—once:

Primary is saturated on writes.

Vertical scaling is near max or no longer cost‑effective.

Replicas don’t help (they only scale reads).

Then:

Choose a partition key that keeps related data colocated.

Shard by that key so writes are spread across multiple primaries.

Sharding is the last resort because it adds:

Cross‑shard complexity.

Operational overhead.

More moving parts.


* CPU high, RAM low → optimize queries.

* CPU still high, RAM low → add cores (vertical).

* Traffic grows, RAM high, CPU okay, I/O up → add RAM (vertical).

* RAM high, CPU high, reads dominate → add read replicas.

* Replicas busy, many connections → pooling + Redis/app cache.

* Writes dominate, primary saturated, vertical maxed → shard.
