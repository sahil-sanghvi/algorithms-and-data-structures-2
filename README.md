[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Algorithm](https://img.shields.io/badge/algorithm-Edmonds--Karp%20max--flow-informational)]()

# Network Flow Matching

Solves a pilot/flight assignment feasibility problem by building a flow network from flight-pilot pairing constraints and running **Edmonds-Karp max-flow** (BFS augmenting paths over residual capacities) to determine whether a feasible assignment exists and, if so, what it is.

## Running

```bash
javac Main.java
java Main < input1.txt
```

Verified against the provided fixtures — output matches `output1.txt`/`output2.txt` exactly for `input1.txt`/`input2.txt`.

## 🎓 Project Context

Built as part of **CSC 226: Algorithms and Data Structures II** at the University of
Victoria.

## ⚠️ Academic Integrity Notice

This repository is maintained for portfolio and educational purposes only. If you are
currently enrolled in CSC 226 at the University of Victoria or a similar algorithms
course, please note that using this code in your own assignments may constitute a
violation of Academic Integrity policies.
