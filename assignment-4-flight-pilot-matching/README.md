# Assignment 4 — Flight/Pilot Matching

Solves a pilot/flight assignment feasibility problem by building a flow
network from flight-pilot pairing constraints and running
**Edmonds-Karp max-flow** (BFS augmenting paths over residual capacities)
to determine whether a feasible assignment exists and, if so, what it is.

## Running

```bash
javac Main.java
java Main < input1.txt
```

Verified against the provided fixtures — output matches `output1.txt`/`output2.txt`
exactly for `input1.txt`/`input2.txt`.
