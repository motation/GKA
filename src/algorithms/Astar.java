package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgraph.graph.Edge;

import elements.IAttributedVertex;
import elements.IGraph;
import elements.IVertex;

public class Astar {

	public Astar(IGraph searchGraph, IAttributedVertex startVertex, IAttributedVertex endVertex) {
		super();
		this.searchGraph = searchGraph;
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		
		ArrayList<IAttributedVertex> tempArray = new ArrayList<IAttributedVertex>();
		
		
		while(!tempArray.isEmpty())
		{
			Table.put(tempArray.get(0), new Values(Double.parseDouble(tempArray.get(0).getAttribute())));			
			tempArray.remove(0);
		}
		Table.get(startVertex).t = Table.get(startVertex).h;
		Table.get(startVertex).g = 0;
		Table.get(startVertex).vorgeangerVertx = startVertex;
		
		
		
		//this.searchGraph.getGraph().vertexSet();
		
		OL.add(startVertex);
		
		
	}


	private IGraph searchGraph;
	
	private HashMap<IAttributedVertex, Values> Table = new HashMap<IAttributedVertex, Values>();
	
	private ArrayList<IAttributedVertex> OL = new ArrayList<IAttributedVertex>();
	private ArrayList<IAttributedVertex> CL = new ArrayList<IAttributedVertex>();
	//List OL = new HashSet();
	//HashSet CL = new HashSet();
	private IAttributedVertex startVertex;
	private IAttributedVertex endVertex;
	
	 class Values{

		 public Values(double h){
			 this(h,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,null);
		 }
		 
		public Values(double h, double g, double t, IVertex vorgeangerVertx) {
			super();
			this.h = h;
			this.g = g;
			this.t = t;
			this.vorgeangerVertx = vorgeangerVertx;
		}
		private double h;
		private double g;
		private double t;
		private IVertex vorgeangerVertx;	
	}
		

	
	
	public void Calculate(){
		
		
		double smalestValue = Table.get(OL.get(0)).t; 	// Ersten wert t der OL in smallest value
		IAttributedVertex tempVertex = OL.get(0);		// // vertex gehörend zu smalestValue
		
		for(int i = 1; i > OL.size();i++){
			
			
			if((Table.get(OL.get(i)).t) < smalestValue){ // verleiche jeden OL mit OL[0] 
				
				smalestValue = (Table.get(OL.get(i)).t);
				tempVertex = OL.get(i); 				// Beinhaltet nach termination der schleife den kelinsten wert aus OL
			}
		}
		
		CL.add(tempVertex);								// kleinster wert der OL in CL
		
		ArrayList<Edge> tempArray = new ArrayList<Edge>(); // arraylist, überraschung

		tempArray.addAll(searchGraph.getOutgoingEdges(tempVertex)); // alle ausgehenden Kanten 
																	// von Kleinsten aus OL
		 ArrayList<IAttributedVertex> tempArrayVertex = new ArrayList<IAttributedVertex>();	// hier sollen mal alle OL verbunden
		 															// Knoten drinnen sein
		while(!tempArray.isEmpty()){
			
			if(!(CL.contains(0))){
				
			OL.add((IAttributedVertex) tempArray.get(0).getTarget());	// alle verbunden in OL 
			Table.get(tempArray.get(0).getTarget()).vorgeangerVertx = tempVertex; //Vorgänger ist die OL
			Table.get(tempArray.get(0).getTarget()).t = Table.get(tempVertex).t + searchGraph.getGraph().
			//tempArrayVertex.add((IAttributedVertex) tempArray.get(0).getTarget()); 
			tempArray.remove(0);	
			
			}
		}		
	}
}