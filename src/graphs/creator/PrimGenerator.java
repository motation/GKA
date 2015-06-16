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
    public static IGraph createRandomPrimGraph(int numVertex, int numEdges) {
        IGraph graph = UndirectedWeightedGraph.createNewGraph();

        // add vertexes
        for (int i = 0; i < numVertex; i++) {
            IVertex source = new Vertex(String.valueOf(createLetter()));
            graph.addVertex(source);
        }

        //add Edges
        List<IVertex> vertexList = new ArrayList<IVertex>(graph.getGraph().vertexSet());

        for (int i = 0; i < numEdges; i++) {
            double weight = createRandomInt(1, 100);
            IVertex source = vertexList.get((createRandomInt(0, vertexList.size() - 1)));
            IVertex target = vertexList.get((createRandomInt(0, vertexList.size() - 1)));
            while (source.equals(target)) {
                source = vertexList.get((createRandomInt(0, vertexList.size() - 1)));
                target = vertexList.get((createRandomInt(0, vertexList.size() - 1)));
            }

            WeightedEdge edge = new WeightedEdge(source, target, weight);
            graph.addEdge(source, target, edge);
            WeightedEdge edge2 = new WeightedEdge(target, source, weight);
            graph.addEdge(target, source, edge2);
        }


        return graph;
    }

    private static char createLetter() {
        return (char) createRandomInt(65, 90);
    }

    private static int createRandomInt(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high - low + 1) + low;
        return result;
    }

    public static IGraph createPrimGraphWithoutLoop(int numVertexes) {
        if (numVertexes < 2) return null;
        IGraph graph = UndirectedWeightedGraph.createNewGraph();

        List<IVertex> vertexList1 = new ArrayList<>();

        for(int i=0;i<numVertexes;i++){
            IVertex vertex = new Vertex(String.valueOf(createLetter()));
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
        return graph;
    }
}
