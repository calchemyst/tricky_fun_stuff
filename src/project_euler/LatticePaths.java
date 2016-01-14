package project_euler;

import java.util.*;

/**
 * Created by annafuller on 12/13/15.
 */
public class LatticePaths {

    public static void main(String[] args) {
        Graph g = makeGraph(2);
        int numWays = 0;
        Set<Graph.Vertex> initialEdges = g.getVertex(0, 0).getEdges();
        long paths = findPath(initialEdges, 2);
        System.out.println(paths);

    }

    private static long findPath(Set<Graph.Vertex> vertices,  int gridSize) {
        Iterator<Graph.Vertex> verticesToSearch = vertices.iterator();
        long paths = 0;
        while (verticesToSearch.hasNext()) {
            Graph.Vertex v = verticesToSearch.next();
            if (v.getPoint().getX() == gridSize -1 && v.getPoint().getY() == gridSize - 1) {
                paths++;
            } else {
                findPath(v.getEdges(), gridSize);
            }
        }
        return paths;
    }

    private static Graph makeGraph(int gridSize) {
        Map<Graph.Point, Graph.Vertex> graphMapping = new HashMap<>();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // Detect if we are on an edge
                Set<Graph.Vertex> edges = new HashSet<>();
                if (i == 0 && j ==0) {
                    edges.add(new Graph.Vertex(new Graph.Point(i+1, j)));
                    edges.add(new Graph.Vertex(new Graph.Point(i, j + 1)));
                } else if (i < gridSize - 1) {
                    edges.add(new Graph.Vertex(new Graph.Point(i+1, j)));
                } else if (j < gridSize - 1) {
                    edges.add(new Graph.Vertex(new Graph.Point(i, j + 1)));
                } else {
                    edges.add(new Graph.Vertex(new Graph.Point(i+1, j)));
                    edges.add(new Graph.Vertex(new Graph.Point(i, j + 1)));
                }

                Graph.Point p = new Graph.Point(i, j);
                Graph.Vertex v = new Graph.Vertex(p, edges);
                graphMapping.put(p, v);
            }
        }
        return new Graph(graphMapping);
    }



}
