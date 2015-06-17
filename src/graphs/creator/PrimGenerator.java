package graphs.creator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;
import io.FileGraphReader;
import org.jgraph.graph.Edge;

public class PrimGenerator {


    public static char createLetter() {
        return (char) createRandomInt(65, 90);
    }

    public static int createRandomInt(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high - low + 1) + low;
        return result;
    }

    private static String trippleLetter(){
        return String.valueOf(createLetter()) + String.valueOf(createLetter()) + String.valueOf(createLetter());
    }

    public static IGraph createPrimGraphWithoutLoop(int numVertexes) {
        if (numVertexes < 2) return null;
        IGraph graph = UndirectedWeightedGraph.createNewGraph();

        List<IVertex> vertexList1 = new ArrayList<>();

        for(int i=0;i<numVertexes;i++){
            IVertex vertex = new Vertex(trippleLetter());
            if(i>0){
                IVertex source = vertexList1.get(i-1);
                if(source.equals(vertex) || graph.getGraph().containsEdge(source,vertex) || vertexList1.contains(vertex)){
                    i--;
                    continue;
                }
                vertexList1.add(vertex);
                double weight = createRandomInt(0,300);
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
           int numOfEdgesToAdd = Math.abs(numVertexes-1 - createRandomInt(numVertexes-1, (int) maxEdges));

            int numOfEdgesAdded = 0;
            List<IVertex> vertexList = new ArrayList<>(graph.getGraph().vertexSet());
            int countAddFail = 0;
            while(numOfEdgesAdded <= numOfEdgesToAdd && countAddFail < 100000){
                IVertex source = vertexList.get(createRandomInt(0,vertexList.size()-1));
                IVertex target = vertexList.get(createRandomInt(0,vertexList.size()-1));
                if((!graph.getGraph().containsEdge(source,target) && !source.equals(target))){
                    double weight = createRandomInt(0,300);
                    Edge edge = new WeightedEdge(source,target,weight);
                    graph.addEdge(source,target,edge);
                    numOfEdgesAdded++;
                } else {
                    countAddFail++;
                }
            }
        }

        List<Edge> edgeList = new ArrayList<>();
        for(Edge edge : graph.getGraph().edgeSet()){
            IVertex source = (IVertex) edge.getSource();
            IVertex target = (IVertex) edge.getTarget();
            WeightedEdge weightedEdge = (WeightedEdge) edge;
            Edge doubleEdge = new WeightedEdge(target,source,weightedEdge.getWeight());
            edgeList.add(doubleEdge);
        }
        for(Edge edge : edgeList){
            WeightedEdge weightedEdge = (WeightedEdge) edge;
            graph.getGraph().addEdge((IVertex) edge.getSource(), (IVertex) edge.getTarget(), weightedEdge);
        }
        return graph;
    }
}
