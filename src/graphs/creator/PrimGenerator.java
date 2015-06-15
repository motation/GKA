package graphs.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;
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

        IVertex[] vertexes = new Vertex[numVertexes];

        for(int i=0;i<numVertexes;i++){
            IVertex vertex = new Vertex(String.valueOf(createLetter()));
            if(i>0){
                vertexes[i] = vertex;
                IVertex source = vertexes[i-1];
                IVertex target = vertexes[i];
                double weight = createRandomInt(0,300);
                Edge edge = new WeightedEdge(source,target,weight);
                graph.addVertex(target);
                graph.addEdge(source,target,edge);
            } else {
                vertexes[0] = vertex;
                graph.addVertex(vertexes[0]);
            }


        }
        double maxEdges = (Math.pow(numVertexes,2) - numVertexes) / 2;

        if(maxEdges != numVertexes){
           int numOfEdgesToAdd = Math.abs(numVertexes-1 - createRandomInt(numVertexes-1, (int) maxEdges));

            int numOfEdgesAdded = 0;
            List<IVertex> vertexList = new ArrayList<>(graph.getGraph().vertexSet());
//            System.out.println("adding extra edges");
            int countAddFail = 0;
            while(numOfEdgesAdded <= numOfEdgesToAdd){
                IVertex source = vertexList.get(createRandomInt(0,vertexList.size()-1));
                IVertex target = vertexList.get(createRandomInt(0,vertexList.size()-1));
                if(!graph.getGraph().containsEdge(source,target) && !source.equals(target) || countAddFail > 100000){
                    double weight = createRandomInt(0,300);
                    Edge edge = new WeightedEdge(source,target,weight);
                    graph.addEdge(source,target,edge);
                    numOfEdgesAdded++;
                } else {
//                    System.out.println("trying to existing edge :(");
                    countAddFail++;
                }
            }
            System.out.println("count add fail= " + countAddFail);
        }
        return graph;
    }

    public static void main(String[] args) {
        IGraph graph = createPrimGraphWithoutLoop(20);
        System.out.println(graph.getGraph().edgeSet().size());
    }
}
