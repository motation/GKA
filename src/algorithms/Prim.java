package algorithms;

import elements.IGraph;
import elements.IVertex;
import elements.MyVertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;

import org.jgraph.graph.Edge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Ole on 27.05.2015.
 */
public class Prim {
    private PriorityQueue<MyVertex> queue;
    private IGraph graph;
    private final int INFINITY = 999;
    private IVertex startVertex;
    private List<MyVertex> finalList;
    private List<MyVertex> myVertexList;
    private long startTime;
    private long endTime;
   

    public Prim(IGraph graph, IVertex startVertex) {
        this.graph = graph;
        this.startVertex = startVertex;
        myVertexList = new ArrayList<>();
        finalList = new ArrayList<>();
    }

    public void init() {
    	startTime = System.currentTimeMillis();
        queue = new PriorityQueue<>(20, new Comparator<MyVertex>() {
            @Override
            public int compare(MyVertex o1, MyVertex o2) {
                return new Double(o1.value).compareTo(o2.value);
            }
        });

        for (IVertex vertex : graph.getGraph().vertexSet()) {
            MyVertex myVertex = new MyVertex();
            if (vertex.equals(startVertex)) {
                myVertex.parent = startVertex;
                myVertex.value = 0;
                myVertex.vertex = startVertex;
                finalList.add(myVertex);
                queue.add(myVertex);
            } else {
                myVertex.parent = null;
                myVertex.value = INFINITY;
                myVertex.vertex = vertex;
            }
            myVertexList.add(myVertex);
        }
    }

    public void loop() {
        while (!queue.isEmpty()) {
            MyVertex vertex = queue.remove();
            if (vertex.vertex != vertex.parent) {
                finalList.add(vertex);
            }

            for (Edge edge : graph.getGraph().edgesOf(vertex.vertex)) {
                double distanceToNeighbor = ((WeightedEdge) edge).getWeight();
                MyVertex neighbor = new MyVertex();
                neighbor.vertex = (IVertex) edge.getTarget();
                neighbor = myVertexList.get(myVertexList.indexOf(neighbor));
                if (queue.contains(neighbor) && distanceToNeighbor < neighbor.value) {
                    neighbor.value = distanceToNeighbor;
                    neighbor.parent = vertex.vertex;
                } else if (neighbor.parent == null) {
                    neighbor.value = distanceToNeighbor;
                    neighbor.parent = vertex.vertex;
                    queue.add(neighbor);
                }
            }

        }
        endTime = System.currentTimeMillis();
    }
    
    public double getEdgeSum(){
    	double sum = 0;
    	for(MyVertex myVertex : finalList){
    		sum+=myVertex.value;
    	}
    	return sum;
    }
    
    public long getTime(){
    	return endTime - startTime;
    }

    public IGraph createGraph(){
        IGraph graph = UndirectedWeightedGraph.createNewGraph();
        for(MyVertex myVertex : finalList){
            if(myVertex.value == 0){
                graph.addVertex(myVertex.vertex);
            } else {
                graph.addVertex(myVertex.vertex);
                graph.addVertex(myVertex.parent);
                WeightedEdge edge = new WeightedEdge(myVertex.parent,myVertex.vertex,myVertex.value);
                graph.addEdge((IVertex)edge.getSource(),(IVertex)edge.getTarget(),edge);
            }
        }
        return graph;
    }
}
