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
    public static IGraph generateEulerCircle(){
        IGraph graph = UndirectedWeightedGraph.createNewGraph();
        //OF TODO generate EulerCircle
        return graph;
    }

    public static IGraph generateWrongEulerCircle(){
        IGraph graph = UndirectedWeightedGraph.createNewGraph();
        int numVertexes = PrimGenerator.createRandomInt(10,30);

        List<IVertex> vertexList1 = new ArrayList<>();

        for(int i=0;i<numVertexes;i++){
            IVertex vertex = new Vertex(String.valueOf(PrimGenerator.createLetter()+PrimGenerator.createLetter()+PrimGenerator.createLetter()));
            if(i>0){
                IVertex source = vertexList1.get(i-1);
                if(source.equals(vertex) || graph.getGraph().containsEdge(source,vertex) || vertexList1.contains(vertex)){
                    i--;
                    continue;
                }
                vertexList1.add(vertex);
                double weight = PrimGenerator.createRandomInt(0, 300);
                Edge edge = new WeightedEdge(source,vertex,weight);
                graph.addVertex(source);
                graph.addVertex(vertex);
                graph.addEdge(source, vertex, edge);
            } else {
                vertexList1.add(vertex);
                graph.addVertex(vertex);
            }


        }
        if(numVertexes!=graph.getGraph().vertexSet().size()){
            System.out.println(false);
        }

        double maxEdges = (Math.pow(numVertexes,2) - numVertexes) / 2;

        if(maxEdges != numVertexes){
            int numOfEdgesToAdd = Math.abs(numVertexes-1 - PrimGenerator.createRandomInt(numVertexes - 1, (int) maxEdges));

            int numOfEdgesAdded = 0;
            List<IVertex> vertexList = new ArrayList<>(graph.getGraph().vertexSet());
            int countAddFail = 0;
            while(numOfEdgesAdded <= numOfEdgesToAdd && countAddFail < 100000){
                IVertex source = vertexList.get(PrimGenerator.createRandomInt(0, vertexList.size() - 1));
                IVertex target = vertexList.get(PrimGenerator.createRandomInt(0, vertexList.size() - 1));
                if((!graph.getGraph().containsEdge(source,target) && !source.equals(target))){
                    double weight = PrimGenerator.createRandomInt(0, 300);
                    Edge edge = new WeightedEdge(source,target,weight);
                    graph.addEdge(source,target,edge);
                    numOfEdgesAdded++;
                } else {
                    countAddFail++;
                }
            }
        }
        IVertex randomVertex = graph.getGraph().vertexSet().iterator().next();
        for(IVertex vertex : graph.getGraph().vertexSet()){
            if(graph.getGraph().edgesOf(vertex).size()%2==0 && !randomVertex.equals(vertex)){
                IVertex source = vertex;
                IVertex target = randomVertex;
                WeightedEdge edge = new WeightedEdge(source,target,2.0);
                graph.addEdge(source,target,edge);
            }
        }

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
