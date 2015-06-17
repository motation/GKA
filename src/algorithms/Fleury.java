package algorithms;

import elements.IVertex;
import graphs.UndirectedWeightedGraph;
import graphs.creator.EulerGenerator;
import org.jgraph.graph.Edge;
import org.jgrapht.GraphPath;

import elements.IGraph;

public class Fleury {

    public boolean compute(IGraph graph1) {
        return false;
        // TODO Auto-generated method stub

    }

    public GraphPath getResultPath() {
        // TODO Auto-generated method stub
        return null;
    }

    private static IGraph deepCopyGraph(IGraph graph){
        IGraph deepCopyGraph = UndirectedWeightedGraph.createNewGraph();
        for(IVertex vertex : graph.getGraph().vertexSet()){
            deepCopyGraph.addVertex(vertex);
        }

        for(Edge edge : graph.getGraph().edgeSet() ){
            deepCopyGraph.addEdge((IVertex)edge.getSource(),(IVertex)edge.getTarget(),edge);
        }
        return deepCopyGraph;
    }

}
