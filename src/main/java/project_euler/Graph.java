package project_euler;

import java.util.*;

/**
 * Created by afuller on 11/1/15.
 */
public class Graph {
    private final Map<Point, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public Graph(Map<Point, Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addEdge(Vertex v, Vertex w) {
        v.addEdge(w);
    }

    // Number of vertices.
    public int V() {
        return vertices.size();
    }

    // Number of edges.
    public int E() {
        int numEdges = 0;
        Iterator<Map.Entry<Point, Vertex>> vertexIterator = vertices.entrySet().iterator();
        while (vertexIterator.hasNext()) {
            numEdges = numEdges + vertexIterator.next().getValue().edges();
        }
        return numEdges;
    }

    public Iterable<Vertex> adjacentTo(Vertex v) {
        return v.getEdges();
    }

    // Number of neighbors of v.
    public int degree(Vertex v) {
        return v.edges();
    }

    public boolean hasVertexAtPoint(Point p) {
        return vertices.containsKey(p);
    }

    public boolean hasEdge(Vertex v, Vertex w) {
        Set<Vertex> edgesV = v.getEdges();
        Set<Vertex> edgesW = w.getEdges();
        return edgesV.retainAll(edgesW);
    }

    public Vertex getVertex(int x, int y) {
        return vertices.get(new Point(0, 0));
    }

    public Iterator<Map.Entry<Point, Vertex>> iterator() {
        return vertices.entrySet().iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph graph = (Graph) o;

        return !(vertices != null ? !vertices.equals(graph.vertices) : graph.vertices != null);

    }

    @Override
    public int hashCode() {
        return vertices != null ? vertices.hashCode() : 0;
    }

    public static class Vertex {
        private final Set<Vertex> edges;
        private final Point point;

        public Vertex(Point point) {
            this.point = point;
            this.edges = new HashSet<>();
        }

        public Vertex(Point point, Set<Vertex> edges) {
            this.point  = point;
            this.edges = edges;
        }

        public Set<Vertex> getEdges() {
            return edges;
        }

        public Point getPoint() {
            return point;
        }

        public int edges() {
            return edges.size();
        }

        public void addEdge(Vertex e) {
            edges.add(e);
        }

        public void addEdges(Set<Vertex> edgesToAdd) {
            edges.addAll(edgesToAdd);
        }

        public boolean removeEdge(Vertex e) {
            return edges.remove(e);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            if (edges != null ? !edges.equals(vertex.edges) : vertex.edges != null) return false;
            return !(point != null ? !point.equals(vertex.point) : vertex.point != null);

        }

        @Override
        public int hashCode() {
            int result = edges != null ? edges.hashCode() : 0;
            result = 31 * result + (point != null ? point.hashCode() : 0);
            return result;
        }
    }

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

}