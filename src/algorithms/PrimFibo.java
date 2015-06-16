package algorithms;

import elements.IGraph;
import elements.IVertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;
import org.jgraph.graph.Edge;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ole on 13.06.2015.
 */
public class PrimFibo {

    private FibonacciHeap<IVertex> fibonacciHeap;
    private IGraph graph;
    private IGraph resultGraph;
    private double sum = 0;
    Map<IVertex, FibonacciHeap.Entry<IVertex>> entries;

    public PrimFibo(IGraph graph) {
        this.graph = graph;
    }

    public IVertex minCostEndpoint(IVertex vertex) {
        IVertex vertexToAdd = null;
        double leastCost = Double.POSITIVE_INFINITY;

        for (Edge edge : graph.getGraph().edgesOf(vertex)) {
            WeightedEdge weightedEdge = (WeightedEdge) edge;
            double weight = weightedEdge.getWeight();

            IVertex exploreVertexSource = (IVertex) edge.getSource();
            IVertex exploreVertexTarget = (IVertex) edge.getSource();


            if (!resultGraph.getGraph().containsVertex(exploreVertexSource) || !resultGraph.getGraph().containsVertex(exploreVertexTarget)) {
                continue;
            }
            if (weight >= leastCost) {
                continue;
            }


            vertexToAdd = resultGraph.getGraph().containsVertex(exploreVertexTarget) ? exploreVertexTarget : exploreVertexSource;
            leastCost = weight;
        }
        return vertexToAdd;
    }

    public IGraph minimumSpanningTree() {
        this.fibonacciHeap = new FibonacciHeap<>();
        this.entries = new HashMap<>();
        resultGraph = UndirectedWeightedGraph.createNewGraph();

        IVertex start = graph.getGraph().vertexSet().iterator().next();

        resultGraph.addVertex(start);

        addOutGoingEdges(start);

        for (int i = 0; i < graph.getGraph().vertexSet().size() - 1; ++i) {
            IVertex source = fibonacciHeap.dequeueMin().getValue();
            IVertex target = minCostEndpoint(source);
            resultGraph.addVertex(source);
            resultGraph.addVertex(target);
            Edge edge = graph.getGraph().getEdge(source, target);
            resultGraph.addEdge(source, target, edge);
            sum += ((WeightedEdge) edge).getWeight();
            addOutGoingEdges(source);
        }


        return resultGraph;
    }

    public double getSum() {
        return this.sum;
    }

    public void addOutGoingEdges(IVertex vertex) {
        for (Edge edge : graph.edgesFrom(vertex)) {
            WeightedEdge weightedEdge = (WeightedEdge) edge;
            double weight = weightedEdge.getWeight();
            IVertex exploreVertex = (IVertex) edge.getTarget();
            if (resultGraph.getGraph().containsVertex(exploreVertex)) {
                continue;
            }
            if (!entries.containsKey(exploreVertex)) {
                entries.put(exploreVertex, fibonacciHeap.enqueue(exploreVertex, weight));
            } else if (entries.get(exploreVertex).getPriority() > weight) {
                fibonacciHeap.decreaseKey(entries.get(exploreVertex), weight);
            }
        }
    }
}
