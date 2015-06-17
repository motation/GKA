package algorithms;

import java.util.*;
import java.util.Collections;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.util.*;

//update

public class Kruskal<V, E>
    implements MinimumSpanningTree<V, E>
{
    

    private double spanningTreeCost;
    private Set<E> edgeList;

    

    
    public Kruskal(final Graph<V, E> graph)
    {
        UnionFind<V> forest = new UnionFind<V>(graph.vertexSet());
        ArrayList<E> allEdges = new ArrayList<E>(graph.edgeSet());
        Collections.sort(
            allEdges,
            new Comparator<E>() {
                public int compare(E edge1, E edge2)
                {
                    return Double.valueOf(graph.getEdgeWeight(edge1)).compareTo(
                        graph.getEdgeWeight(edge2));
                }
            });

        spanningTreeCost = 0;
        edgeList = new HashSet<E>();

        for (E edge : allEdges) {
            V source = graph.getEdgeSource(edge);
            V target = graph.getEdgeTarget(edge);
            if (forest.find(source).equals(forest.find(target))) {
                continue;
            }

            forest.union(source, target);
            edgeList.add(edge);
            spanningTreeCost += graph.getEdgeWeight(edge);
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
