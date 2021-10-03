import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Vertex
{
    private Integer id;

    private HashMap< Integer, Vertex> neighbours;

    private HashMap< Integer, Integer > arc_weights;

    public Vertex ( int id )
    {
        this.id = id;
        this.neighbours = new HashMap<Integer,Vertex>();
        this.arc_weights = new HashMap<Integer,Integer>();
    }

    public void add_neighbor( Vertex viz )
    {
        neighbours.put(viz.id, viz);
    }
   
    public void add_weight( Integer id_nb, Integer weight )
    {
        arc_weights.put( id_nb, weight );
    }

    public int get_id()
    {
        return id;
    }

    public Collection<Vertex> get_neighbour_vertices()
    {
        return neighbours.values();
    }

    public Set<Integer> get_neighbour_ids()
    {
        return neighbours.keySet();
    }
}
