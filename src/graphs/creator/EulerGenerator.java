package graphs.creator;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;
import org.jgraph.graph.Edge;
import org.jgrapht.alg.DijkstraShortestPath;

/**
 * Created by Ole on 17.06.2015.
 */
public class EulerGenerator {
    public static IGraph generateEulerCircle(){
        IGraph graph = UndirectedWeightedGraph.createNewGraph();
        //OF TODO generate EulerCircle
        return graph;
    }

    public static IGraph generateWrongEulerCircle(){
        IGraph graph = UndirectedWeightedGraph.createNewGraph();
        //OF TODO generate WrongEulerCircle

        return graph;
    }

    public static boolean isEulerCircle(IGraph graph){
        boolean isEuler=true;
        IVertex randomVertex = graph.getGraph().vertexSet().iterator().next();
        for(IVertex vertex : graph.getGraph().vertexSet()){
            if(graph.getGraph().edgesOf(vertex).size()%2!=0){
                return false;
            }
            if(!vertex.equals(randomVertex)){
                try {
                    if(DijkstraShortestPath.findPathBetween(graph.getGraph(),vertex,randomVertex).size() == 0){
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
