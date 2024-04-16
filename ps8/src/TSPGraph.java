import java.util.ArrayList;

public class TSPGraph implements IApproximateTSP {

    @Override
    public void MST(TSPMap map) {
        // TODO: implement this method
        // Use prim algorithm to get MST
        int numOfCities = map.getCount();
        TreeMapPriorityQueue<Double,Integer> pq = new TreeMapPriorityQueue<>();
        int start = 0;
        // implement a parent array to track parents of nodes
        int[] parent = new int[numOfCities];
        parent[0] = start;
        // add all vertices to priority queue and initialize the parents array
        for (int i = 0; i < numOfCities; i++) {
            pq.add(i, map.pointDistance(start, i));
            parent[i] = start;
        }
        // perform MST on graph
        while (!pq.isEmpty()) {
            int visit = pq.extractMin();
            map.setLink(visit, parent[visit]);
            for (int i = 0; i < numOfCities; i++) {
                if (map.getLink(i) >= 0) {
                    continue;
                }
                if (map.pointDistance(visit, i) < pq.lookup(i)) {
                    pq.decreasePriority(i, map.pointDistance(visit, i));
                    parent[i] = visit;
                }
            }
        }
    }


    // DFS
    public void TSPDFS(TSPMap map, ArrayList<Integer> list, int start) {
        if (map.getCount() == list.size()) {
            // visit all cities
            return;
        }
        for (int i = 0; i < map.getCount(); i++) {
            if (map.getLink(i) == start) {
                list.add(i);
                TSPDFS(map, list, i);
            }
        }
    }

    @Override
    public void TSP(TSPMap map) {
        MST(map);
        // TODO: implement the rest of this method.
        int numOfCities = map.getCount();
        int start = 0;
        for (int i = 0; i < numOfCities; i++) {
            if (map.getLink(i) < 0) {
                start = i;
                break;
            }
        }
        ArrayList<Integer> City = new ArrayList<>();
        City.add(start);
        TSPDFS(map, City, start);

        int len = City.size() - 1;
        // setLink in the DFS seq
        for (int i = 0; i < len; i++) {
            int from = City.get(i);
            int to = City.get(i + 1);
            map.setLink(from, to, false);
        }
        int end = City.get(len);
        // finally, call redraw to draw the tour
        map.setLink(end, 0, true);
    }

    @Override
    public boolean isValidTour(TSPMap map) {
        // Note: this function should with with *any* map, and not just results from TSP().
        // TODO: implement this method
        if (map == null) {
            return false;
        }

        int numOfCities = map.getCount();
        boolean[] visit = new boolean[numOfCities];
        int curNode = 0;

        for (int i = 0; i < numOfCities; i++) {
            curNode = map.getLink(curNode);
            if (curNode < 0 || visit[curNode] == true) {
                return false;
            }
            visit[curNode] = true;
        }

        return true;
    }

    @Override
    public double tourDistance(TSPMap map) {
        // Note: this function should with with *any* map, and not just results from TSP().
        // TODO: implement this method
        if (!isValidTour(map)) {
            return -1;
        }

        double total = 0.0;
        int numOfCities = map.getCount();
        int start = 0;

        for (int i = 0; i < numOfCities; i++) {
            int next = map.getLink(start);
            total += map.pointDistance(start, next);
            start = next;
        }

        return total;
    }

    public static void main(String[] args) {
        TSPMap map = new TSPMap(args.length > 0 ? args[0] : "./fiftypoints.txt");
        TSPGraph graph = new TSPGraph();

        graph.MST(map);
        graph.TSP(map);
        System.out.println(graph.isValidTour(map));
        // System.out.println(graph.tourDistance(map));
    }
}
