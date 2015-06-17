package graphs.creator;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;
import org.jgraph.graph.Edge;
import org.jgrapht.alg.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 17.06.2015.
 */
public class EulerGenerator {

    private static String createTrippleLetter() {
        return String.valueOf(PrimGenerator.createLetter()) + String.valueOf(PrimGenerator.createLetter()) + String.valueOf(PrimGenerator.createLetter());
    }

    public static IGraph generateEulerCircle(int numVertexes) {
        if (numVertexes < 2) return null;
        IGraph graph = UndirectedWeightedGraph.createNewGraph();
        if (numVertexes % 2 == 0) {
            // start with 4 vertexes
            for (int i = 0; i < 4; i++) {
                IVertex vertex = new Vertex(createTrippleLetter());
                if (graph.getGraph().containsVertex(vertex)) {
                    i--;
                    continue;
                }
                graph.addVertex(vertex);
            }
            // adding edges to this vertexes
            IVertex[] vertexes = graph.getGraph().vertexSet().toArray(new IVertex[graph.getGraph().vertexSet().size()]);
            graph.addVertex(vertexes[0]);
            graph.addVertex(vertexes[1]);
            graph.addVertex(vertexes[2]);
            graph.addVertex(vertexes[3]);

            graph.addEdge(vertexes[0], vertexes[1], new WeightedEdge(vertexes[0], vertexes[1], PrimGenerator.createRandomInt(5, 200)));
            graph.addEdge(vertexes[1], vertexes[2], new WeightedEdge(vertexes[1], vertexes[2], PrimGenerator.createRandomInt(5, 200)));
            graph.addEdge(vertexes[2], vertexes[3], new WeightedEdge(vertexes[2], vertexes[3], PrimGenerator.createRandomInt(5, 200)));
            graph.addEdge(vertexes[3], vertexes[0], new WeightedEdge(vertexes[3], vertexes[0], PrimGenerator.createRandomInt(5, 200)));


        } else {
            // start with 3 vertexex
            for (int i = 0; i < 3; i++) {
                IVertex vertex = new Vertex(createTrippleLetter());
                if (graph.getGraph().containsVertex(vertex)) {
                    i--;
                    continue;
                }
                graph.addVertex(vertex);
            }
            // adding edges to this vertexes
            IVertex[] vertexes = graph.getGraph().vertexSet().toArray(new IVertex[graph.getGraph().vertexSet().size()]);
            graph.addVertex(vertexes[0]);
            graph.addVertex(vertexes[1]);
            graph.addVertex(vertexes[2]);

            graph.addEdge(vertexes[0], vertexes[1], new WeightedEdge(vertexes[0], vertexes[1], PrimGenerator.createRandomInt(5, 200)));
            graph.addEdge(vertexes[1], vertexes[2], new WeightedEdge(vertexes[1], vertexes[2], PrimGenerator.createRandomInt(5, 200)));
            graph.addEdge(vertexes[2], vertexes[0], new WeightedEdge(vertexes[2], vertexes[0], PrimGenerator.createRandomInt(5, 200)));
        }
        // choose one vertex and add two edges - but before create two new vertexes
        // example create C; create D; A connect C ; A connect D; C connect D;
        while (graph.getGraph().vertexSet().size() < numVertexes) {
            int size = graph.getGraph().vertexSet().size();
            int randomNumber = PrimGenerator.createRandomInt(0, size - 1);
            IVertex randomVertex = graph.getGraph().vertexSet().toArray(new IVertex[size])[randomNumber];
            IVertex newVertexOne = new Vertex(createTrippleLetter());
            IVertex newVertexTwo = new Vertex(createTrippleLetter());
            graph.addVertex(newVertexOne);
            graph.addVertex(newVertexTwo);
            WeightedEdge edgeOne = new WeightedEdge(randomVertex, newVertexOne, PrimGenerator.createRandomInt(5, 200));
            WeightedEdge edgeTwo = new WeightedEdge(randomVertex, newVertexTwo, PrimGenerator.createRandomInt(5, 200));
            WeightedEdge edgeThree = new WeightedEdge(newVertexOne, newVertexTwo, PrimGenerator.createRandomInt(5, 200));
            graph.addEdge(randomVertex, newVertexOne, edgeOne);
            graph.addEdge(randomVertex, newVertexTwo, edgeTwo);
            graph.addEdge(newVertexOne, newVertexTwo, edgeThree);
        }
        return graph;
    }

    public static IGraph generateWrongEulerCircle() {
        IGraph graph = UndirectedWeightedGraph.createNewGraph();
        int numVertexes = PrimGenerator.createRandomInt(10, 30);

        List<IVertex> vertexList1 = new ArrayList<>();

        for (int i = 0; i < numVertexes; i++) {
            IVertex vertex = new Vertex(String.valueOf(PrimGenerator.createLetter() + PrimGenerator.createLetter() + PrimGenerator.createLetter()));
            if (i > 0) {
                IVertex source = vertexList1.get(i - 1);
                if (source.equals(vertex) || graph.getGraph().containsEdge(source, vertex) || vertexList1.contains(vertex)) {
                    i--;
                    continue;
                }
                vertexList1.add(vertex);
                double weight = PrimGenerator.createRandomInt(0, 300);
                Edge edge = new WeightedEdge(source, vertex, weight);
                graph.addVertex(source);
                graph.addVertex(vertex);
                graph.addEdge(source, vertex, edge);
            } else {
                vertexList1.add(vertex);
                graph.addVertex(vertex);
            }


        }
        if (numVertexes != graph.getGraph().vertexSet().size()) {
            System.out.println(false);
        }

        double maxEdges = (Math.pow(numVertexes, 2) - numVertexes) / 2;

        if (maxEdges != numVertexes) {
            int numOfEdgesToAdd = Math.abs(numVertexes - 1 - PrimGenerator.createRandomInt(numVertexes - 1, (int) maxEdges));

            int numOfEdgesAdded = 0;
            List<IVertex> vertexList = new ArrayList<>(graph.getGraph().vertexSet());
            int countAddFail = 0;
            while (numOfEdgesAdded <= numOfEdgesToAdd && countAddFail < 100000) {
                IVertex source = vertexList.get(PrimGenerator.createRandomInt(0, vertexList.size() - 1));
                IVertex target = vertexList.get(PrimGenerator.createRandomInt(0, vertexList.size() - 1));
                if ((!graph.getGraph().containsEdge(source, target) && !source.equals(target))) {
                    double weight = PrimGenerator.createRandomInt(0, 300);
                    Edge edge = new WeightedEdge(source, target, weight);
                    graph.addEdge(source, target, edge);
                    numOfEdgesAdded++;
                } else {
                    countAddFail++;
                }
            }
        }
        IVertex randomVertex = graph.getGraph().vertexSet().iterator().next();
        for (IVertex vertex : graph.getGraph().vertexSet()) {
            if (graph.getGraph().edgesOf(vertex).size() % 2 == 0 && !randomVertex.equals(vertex)) {
                IVertex source = vertex;
                IVertex target = randomVertex;
                WeightedEdge edge = new WeightedEdge(source, target, 2.0);
                graph.addEdge(source, target, edge);
            }
        }

        return graph;
    }

    public static boolean isEulerCircle(IGraph graph) {
        boolean isEuler = true;
        IVertex randomVertex = graph.getGraph().vertexSet().iterator().next();
        for (IVertex vertex : graph.getGraph().vertexSet()) {
            if (graph.getGraph().edgesOf(vertex).size() % 2 != 0) {
                return false;
            }
            if (!vertex.equals(randomVertex)) {
                try {
                    if (DijkstraShortestPath.findPathBetween(graph.getGraph(), vertex, randomVertex).size() == 0) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    return false;
                }
            }
        }
        return isEuler;
    }
}
