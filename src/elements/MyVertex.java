package elements;

/**
 * Created by Ole on 27.05.2015.
 */
public class MyVertex {
    public IVertex vertex;
    public double value;
    public IVertex parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyVertex myVertex = (MyVertex) o;

        return vertex.equals(myVertex.vertex);

    }

    @Override
    public int hashCode() {
        return vertex.hashCode();
    }

    @Override
    public String toString() {
        return "MyVertex{" +
                "vertex=" + vertex +
                ", value=" + value +
                ", parent=" + parent +
                '}';
    }
}
