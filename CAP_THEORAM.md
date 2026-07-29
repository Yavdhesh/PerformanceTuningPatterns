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
