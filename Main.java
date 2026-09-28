import java.util.*;

public class Main {


    private static List<Pair<String, String>> flightPilotPairings = new ArrayList<>();

    //implementing an arraylist to read the input and put it into a readable format for the code
    private static List<String> inputLines = new ArrayList<>();

    private static class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey()   { return key; }
        public V getValue() { return value; }
    }
    
    private static void solveAllocationProblem() {
        int numFlightNodes = flightPilotPairings.size();
        char[] flightLetter = new char[numFlightNodes + 1]; 
        int totalDemand = 0;
        int[] flightCapacity = new int[numFlightNodes + 1];

        for (int i = 0; i < numFlightNodes; i++) {
            String flightInfo = flightPilotPairings.get(i).getKey();
            char route = flightInfo.charAt(0);
            int flights = flightInfo.charAt(1) - '0';
            flightLetter[i+1] = route;
            flightCapacity[i+1] = flights;
            totalDemand += flights;
        }
        
        //declaring variables for totalNodes, Source node and Sink Node
        int V = numFlightNodes + 10 + 2; 
        int S = 0;
        int T = V - 1;
        
        int[][] capacity = new int[V][V];
        
        for (int i = 1; i <= numFlightNodes; i++) {
            capacity[S][i] = flightCapacity[i];
        }
        
        for (int i = 0; i < numFlightNodes; i++) {
            String qualifiedPilots = flightPilotPairings.get(i).getValue();
        
            for (int j = 0; j < qualifiedPilots.length(); j++) {
                char pilotChar = qualifiedPilots.charAt(j);
                int pilotId = pilotChar - '0';
        
                int pilotNode = numFlightNodes + 1 + pilotId;
                capacity[i+1][pilotNode] = 1;
            }
        }
        
        for (int pilotId = 0; pilotId < 10; pilotId++) {
            int pilotNode = numFlightNodes + 1 + pilotId;
            capacity[pilotNode][T] = 1;
        }
        
        int flow = 0;
        int[][] flowPassed = new int[V][V];
        
        while (true) {
            int[] parent = new int[V];
            Arrays.fill(parent, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(S);
            parent[S] = S;
            
            while (!queue.isEmpty() && parent[T] == -1) {
                int cur = queue.poll();
                for (int next = 0; next < V; next++) {
                    if (parent[next] == -1 && capacity[cur][next] - flowPassed[cur][next] > 0) {
                        parent[next] = cur;
                        queue.offer(next);
                    }
                }
            }
            if (parent[T] == -1) {
                break; 
            }
            
            int bottleNeck = Integer.MAX_VALUE;
            int cur = T;
            while (cur != S) {
                int prev = parent[cur];
                bottleNeck = Math.min(bottleNeck, capacity[prev][cur] - flowPassed[prev][cur]);
                cur = prev;
            }
            
            cur = T;
            while (cur != S) {
                int prev = parent[cur];
                flowPassed[prev][cur] += bottleNeck;    
                flowPassed[cur][prev] -= bottleNeck;
                cur = prev;
            }
            
            flow += bottleNeck;
        }
        
        // printing a "!" character if there is no possible allocation
        if (flow != totalDemand) {
            System.out.print("!");
            return;
        }
        
        // printing the underscore character for when no route is assigned to the pilot
        char[] assignment = new char[10];
        Arrays.fill(assignment, '_'); 
        for (int pilotId = 0; pilotId < 10; pilotId++) {
            int pilotNode = numFlightNodes + 1 + pilotId;
            
            for (int i = 1; i <= numFlightNodes; i++) {
                if (flowPassed[i][pilotNode] > 0) {
                    assignment[pilotId] = flightLetter[i];
                    break;
                }
            }
        }
        System.out.print(new String(assignment));
    }
    
    private static void processInputData(List<String> lines) {
        int i = 0;
        while (i < lines.size()) {
            flightPilotPairings.clear();
            
            while (i < lines.size() && !lines.get(i).isEmpty()) {
                String line = lines.get(i++);
                
                if (line.trim().length() == 0) continue;
                String[] tokens = line.split("\\s+");
                if (tokens.length < 2) continue;
                
                String flightInfo = tokens[0];
                String pilotInfo = tokens[1];
                if (!pilotInfo.isEmpty() && pilotInfo.charAt(pilotInfo.length()-1) == ';') {
                    pilotInfo = pilotInfo.substring(0, pilotInfo.length()-1);
                }
                
                flightPilotPairings.add(new Pair<>(flightInfo, pilotInfo));
            }
            
            if (!flightPilotPairings.isEmpty()) {
                solveAllocationProblem();
                System.out.println();
            }
            
            while (i < lines.size() && lines.get(i).isEmpty()) {
                i++;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            inputLines.add(sc.nextLine());
        }
        sc.close();
        processInputData(inputLines);
    }
}
