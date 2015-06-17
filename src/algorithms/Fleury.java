package algorithms;

import elements.IGraph;
import elements.IVertex;
import graphs.UndirectedWeightedGraph;
import io.FileGraphReader;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.Edge;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Fleury {

    public boolean compute(IGraph graph) {
        IGraph copy = deepCopyGraph(graph);
        Set<Edge> bridges = bridgeFinder(copy);

        IVertex start = copy.getGraph().vertexSet().iterator().next();
        IVertex current = start;


        while(copy.getGraph().edgeSet().size() != 0){
            for(Edge edge : copy.getGraph().edgesOf(current)){
                if(!bridges.contains(edge) || copy.getGraph().edgesOf((IVertex) current).size()==1){
                    current = (current.equals((IVertex)edge.getTarget()) ? (IVertex)edge.getSource() : (IVertex) edge.getTarget());
                    copy.getGraph().removeEdge(edge);
//                    System.out.println("removed " + edge.getSource() + " " + edge.getTarget() + " " + edge);
                    break;
                }
            }
            bridges = bridgeFinder(copy);
        }
        if(copy.getGraph().edgeSet().size() == 0 && current.equals(start)) return true;
        return false;
    }

    public GraphPath getResultPath() {
        // TODO Auto-generated method stub
        return null;
    }

    private static IGraph deepCopyGraph(IGraph graph) {
        IGraph deepCopyGraph = UndirectedWeightedGraph.createNewGraph();
        for (IVertex vertex : graph.getGraph().vertexSet()) {
            deepCopyGraph.addVertex(vertex);
        }

        for (Edge edge : graph.getGraph().edgeSet()) {
            deepCopyGraph.addEdge((IVertex) edge.getSource(), (IVertex) edge.getTarget(), edge);
        }
        return deepCopyGraph;
    }

    private Set<Edge> bridgeFinder(IGraph graph) {
        SimpleGraph<IVertex, Edge> deepCopyGraph1 = new SimpleGraph<IVertex, Edge>(DefaultEdge.class);
        SimpleGraph<IVertex, Edge> deepCopyGraph2 = new SimpleGraph<IVertex, Edge>(DefaultEdge.class);

        Set<Edge> bridges = new HashSet<>();
        for (IVertex vertex : graph.getGraph().vertexSet()) {
            deepCopyGraph1.addVertex(vertex);
            deepCopyGraph2.addVertex(vertex);

        }

        for (Edge edge : graph.getGraph().edgeSet()) {
            deepCopyGraph1.addEdge((IVertex) edge.getSource(), (IVertex) edge.getTarget(), edge);
            deepCopyGraph2.addEdge((IVertex) edge.getSource(), (IVertex) edge.getTarget(), edge);
        }

        int size = new ConnectivityInspector<IVertex, Edge>(deepCopyGraph2).connectedSets().size();
        for (Edge e : graph.getGraph().edgeSet()) {
            deepCopyGraph1.removeEdge(e);
            int nsize = new ConnectivityInspector<IVertex, Edge>(deepCopyGraph1).connectedSets().size();
            if (nsize > size) bridges.add(e);
            IVertex source = graph.getGraph().getEdgeSource(e);
            IVertex target = graph.getGraph().getEdgeTarget(e);
            deepCopyGraph1.addEdge(source, target);
        }
        return bridges;
    }

    private static Set<IVertex> neighborhood(IGraph graph, IVertex v) {
        Set<IVertex> vertexSet = new HashSet<>();
        for (Edge edge : graph.getGraph().edgesOf(v)) {
            vertexSet.add((IVertex) edge.getTarget());
        }
        return vertexSet;
    }

}
