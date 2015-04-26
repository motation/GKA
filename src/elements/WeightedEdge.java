package elements;

import java.util.Map;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.Edge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.VertexPair;

public class WeightedEdge extends DefaultWeightedEdge implements Edge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Edge defaultEdgle = new DefaultEdge();
	private double weight;
	
	public WeightedEdge(VertexPair<IVertex> vp, double weight) {
		this(vp.getFirst(), vp.getSecond(), weight);
	}
	
	public WeightedEdge(IVertex source, IVertex target, double weight) {
		defaultEdgle.setSource(source);
		defaultEdgle.setTarget(target);
		this.weight = weight;
	}

	@Override
	public Map changeAttributes(Map arg0) {
		// TODO Auto-generated method stub
		return defaultEdgle.changeAttributes(arg0);
	}

	@Override
	public AttributeMap getAttributes() {
		// TODO Auto-generated method stub
		return defaultEdgle.getAttributes();
	}

	@Override
	public void setAttributes(AttributeMap arg0) {
		// TODO Auto-generated method stub
		defaultEdgle.setAttributes(arg0);
	}

	@Override
	public void setSource(Object arg0) {
		// TODO Auto-generated method stub
		defaultEdgle.setSource(arg0);
	}

	@Override
	public void setTarget(Object arg0) {
		// TODO Auto-generated method stub
		defaultEdgle.setTarget(arg0);
	}
	
	@Override
	public Object getSource() {
		return defaultEdgle.getSource();
	}
	@Override
	public Object getTarget() {
		return defaultEdgle.getTarget();
	}
	
	@Override
	public double getWeight() {
		return weight;
	}
	
	// to show weight
	@Override
	public String toString(){
		return Double.toString(getWeight());
	}

}
