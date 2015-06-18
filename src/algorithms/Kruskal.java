package algorithms;

import java.util.*;

import org.jgraph.graph.Edge;
import org.jgrapht.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.util.*;

import elements.IGraph;

//update

public class Kruskal<V, E>
    implements MinimumSpanningTree<V, E>
{
    

    private double spanningTreeCost;
    private Set<E> edgeList;

    

    
    public Kruskal(final IGraph graph)
    
    
    {
        UnionFind<V> forest = new UnionFind<V>((Set<V>) graph.getGraph().vertexSet()); // Unionfind, schöne darstellung von Kanten da unverbundenen Subsets
        ArrayList<E> allEdges = new ArrayList<E>((Collection<? extends E>) graph.getGraph().edgeSet());
        
        Collections.sort(
            allEdges,
            new Comparator<E>() {
                public int compare(E edge1, E edge2)
                {
                    return Double.valueOf(graph.getGraph().getEdgeWeight((Edge) edge1)).compareTo(
                        ((IGraph) graph).getGraph().getEdgeWeight((Edge) edge2));
                }
            });

        spanningTreeCost = 0;
        edgeList = new HashSet<E>();

        
        

        for (E edge : allEdges) {
            V source = (V) graph.getGraph().getEdgeSource((Edge) edge);
            V target = (V) graph.getGraph().getEdgeTarget((Edge) edge);
            if (!(forest.find(source).equals(forest.find(target)))) {
                forest.union(source, target);
                edgeList.add(edge);
                spanningTreeCost += graph.getGraph().getEdgeWeight((Edge) edge);
            	
            }
        }
    }

    
    
    

    @Override public Set<E> getMinimumSpanningTreeEdgeSet()
    {
        return edgeList;
    }

    @Override public double getMinimumSpanningTreeTotalWeight()
    {
        return spanningTreeCost;
    }


    @Deprecated public Set<E> getEdgeSet()
    {
        return getMinimumSpanningTreeEdgeSet();
    }


    @Deprecated public double getSpanningTreeCost()
    {
        return getMinimumSpanningTreeTotalWeight();
    }
}

// End KruskalMinimumSpanningTree.java
