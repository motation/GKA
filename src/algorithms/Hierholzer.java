package algorithms;

import org.jgraph.graph.Edge;
import org.jgrapht.GraphPath;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import elements.IGraph;
import elements.IVertex;

public class Hierholzer {

	IGraph graph;
	List<List<IVertex>> paths;
	
	public boolean compute(IGraph graph1) {
		
		if(!(legitGraph(graph1))){
		return false;
		}
		// TODO Auto-generated method stub
		return false;
		
	}

	public List<IVertex> getResultPath() {
		
		List<IVertex> path = this.paths.get(0);
		this.paths.remove(0);
		while (!this.paths.isEmpty())
			{
				for (int i = 0; i < path.size(); i++)
				{
					if (path.get(i).equals(this.paths.get(0).get(0)))
						{
							path.remove(i);
							path.addAll(i, this.paths.get(0));
						this.paths.remove(0);
						break;
						}
				}
			}
		return path;
		
	}
	
	public List<IVertex> getEulertour(IVertex start,IGraph graph){
		IGraph tempGraph = graph;
		List<IVertex> returnList = null;
		IVertex currentVertex = null;
		List<Edge> tempEdgeset = new LinkedList<Edge>();
			
		tempEdgeset.addAll(graph.getGraph().edgesOf(start));
		
		if(start != tempEdgeset.get(0).getTarget()){
			currentVertex = (IVertex) tempEdgeset.get(0).getTarget();
		}
		if(start != tempEdgeset.get(0).getSource()){
			currentVertex = (IVertex) tempEdgeset.get(0).getSource();
		}
		while(currentVertex != start)
		{
			graph.getGraph().edgesOf(start);
			returnList.add(currentVertex);
			
			if(start != tempEdgeset.get(0).getTarget()){
				currentVertex = (IVertex) tempEdgeset.get(0).getTarget();
			}
			if(start != tempEdgeset.get(0).getSource()){
				currentVertex = (IVertex) tempEdgeset.get(0).getSource();
			}
		}
		return returnList;
	}
	
	public boolean legitGraph(IGraph graph1){
		
		 
		 ConnectivityInspector ci = new ConnectivityInspector((UndirectedGraph) graph1.getGraph());
		 
		 if (!ci.isGraphConnected())
		 	{
			 	return false;
		 	}
		 for (IVertex vertex : graph1.getGraph().vertexSet())
		 	{
			 	if (((UndirectedGraph) graph1.getGraph()).degreeOf(vertex) % 2 == 1)
			 		{
			 			return false;
			 		}
		 	}
		 return true;
		 
	}
}
