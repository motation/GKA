package elements;

public class AttributedVertex extends AbstractVertex implements IAttributedVertex {
	
	private String attribute;
	
	public AttributedVertex(String name, String attribute) {
		super(name);
		this.attribute = attribute;
	}

	@Override
	public String getAttribute() {
		return attribute;
	}
	
	@Override
	public String toString() {
		return name+","+attribute;
	}

	@Override
	public boolean isAttributed() {
		return true;
	}

}
