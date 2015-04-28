package elements;

public class Vertex extends AbstractVertex implements IVertex {

	
	
	public Vertex(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public boolean isAttributed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString(){
		return this.getName();
	}

}
