# Basic Hints on Database Performance Tuning techniques

* CPU high, RAM low → optimize queries.

* CPU still high, RAM low → add cores (vertical).

* Traffic grows, RAM high, CPU okay, I/O up → add RAM (vertical).

* RAM high, CPU high, reads dominate → add read replicas.

* Replicas busy, many connections → pooling + Redis/app cache.

* Writes dominate, primary saturated, vertical maxed → shard.
