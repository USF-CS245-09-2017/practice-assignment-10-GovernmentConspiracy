import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph {
    private boolean[][] adjMatrix; //row will represent the vertex and column will represent pointer of that vertex
    public GraphImplementation(int vertices) {
        adjMatrix = new boolean[vertices][vertices];
    }

    /**
     * Adds a directed edge between two vertices from v1 to v2.
     * @param v1 The source vertex
     * @param v2 The target vertex
     */
    @Override
    public void addEdge(int v1, int v2) {
        adjMatrix[v1][v2] = true;
    }

    /**
     * Returns an array of vertex IDs such that
     * each ID represents a vertex which is
     * the destination of the edge origination from the argument vertex).
     * @param vertex The given vertex
     * @return The array of vertices pointed by the vertex.
     */
    @Override
    public int[] neighbors(int vertex) {
        ArrayList<Integer> vertexIDs = new ArrayList<>();
        for (int i = 0; i < adjMatrix[vertex].length; i++) {
            if (adjMatrix[vertex][i])
                vertexIDs.add(i);
        }

        int[] temp = new int[vertexIDs.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = vertexIDs.get(i);
        }
        return temp;
    }

    @Override
    public List<Integer> topologicalSort() {
        return depthFirstSearch();
    }

    public List<Integer> depthFirstSearch() {
        if (adjMatrix.length > 0) {
            boolean[] visited = new boolean[adjMatrix.length];
            LinkedList<Integer> ordering = new LinkedList<>();
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    depthFirstSearch(ordering, visited, i);
                }
            }
            return ordering;
        }
        return null;
    }

    private void depthFirstSearch(LinkedList<Integer> ordering, boolean[] visited, int currentVertex) {
        visited[currentVertex] = true;
        int[] adjVertices = neighbors(currentVertex);
        for (int adj: adjVertices) {
            if (!visited[adj]) {
                depthFirstSearch(ordering, visited, adj);
            }
        }
        ordering.addFirst(currentVertex);
    }


}
