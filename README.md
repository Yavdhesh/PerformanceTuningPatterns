# PerformanceTuningPatterns


# Database Performance Cheat Sheet

# Partitioning
* Range → time-series, logs, telemetry

* List → categorical (product_type, region)

* Hash → uniform distribution for heavy writes

* Sub-partitioning → isolate hot subsets (e.g., product_type + time range)

# Index Strategy
* **Fewer indexes** on write-heavy tables

* **Covering indexes** for read-heavy queries

* **Rebuild/reorganize** → defragmentation, faster scans

* **Local indexes** scoped to partitions/sub-partitions

# Caching
* **Redis cluster**: write-through (critical), write-behind (non-critical)

* **Memcached**: lightweight key-value caching

* **Materialized views**: refresh off-peak, cache aggregates

# Concurrency & Transactions
* **Optimistic concurrency** → high-read workloads (retry on conflict)

* **Pessimistic locking** → high-write workloads (lock rows)

* Batch commits, short transactions, connection pooling


# Replication & Sharding
* **Read replicas**: async (fast, eventual consistency), sync (strong consistency, slower)

* **Sharding**: distribute geographically; adding shards may require rehash/migration unless using consistent hashing

* **CQRS**: separate write (commands) and read (queries) paths.

* **Denormalization** for archived/read-only OLAP loads


# Storage & Compression
* **NVMe vs SSD**: NVMe = ultra-low latency, expensive; SSD = balanced cost/performance

* **Columnar storage**: OLAP, read-heavy workloads

* **Compression**: table/partition-level to reduce I/O


# Monitoring & Alerts
* Query plans → cost, scans, filters, index usage

* Statistics updates (Oracle, SQL Server, Postgres) → keep optimizer accurate

* Slow query logs → set thresholds, trigger alerts

* Email reports → AWR (Oracle), Extended Events (SQL Server), pgBadger (Postgres)



# Application Layer
* Tune connection pool size vs DB max connections

* Java sharding → in-memory maps, bulk commit while threads continue writing


# >Quick Whys<
* **Index rebuild**: reorganizes fragmented pages, improves scan speed.

* **Oracle statistics updates**: refresh optimizer metadata; can schedule jobs + email reports.

* **Slow query alerts**: monitoring tools send email when thresholds exceeded.

* **MVCC (Oracle)**: readers don’t block writers; isolation is per-session, not per-table.

* **Partition + indexes**: global indexes span all partitions; local indexes tied to sub-partitions.

* **Connection pool scaling**: increase pool size in app + DB max connections; load balance across replicas.

* **Columnar storage**: stores data by column, speeds up aggregations.

* **Compression**: reduces disk footprint, improves read performance.
