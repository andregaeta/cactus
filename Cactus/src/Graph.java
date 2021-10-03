import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

public class Graph
{
    private HashMap< Integer, Vertex> vertices;

    public Graph()
    {
        vertices = new HashMap<Integer, Vertex>();
    }

    public Graph(String file_path, boolean is_weighted)
    {
        vertices = new HashMap<Integer,Vertex>();

        try
        {
            FileReader file_in = new FileReader( file_path );
            BufferedReader reader = new BufferedReader( file_in );

            String thisLine;
            while ((thisLine = reader.readLine()) != null)
            {
                thisLine = thisLine.replaceAll("\\s+", " ");
                String input_line_split[] = thisLine.split(" ");

                if(is_weighted)
                    load_input_line_weighted(input_line_split);
                else
                    load_input_line(input_line_split);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void load_input_line(String[] input_line_split)
    {
        try
        {
            int v1 = Integer.parseInt(input_line_split[0]);
            this.add_vertex(v1);

            for (int i = 2; i < input_line_split.length; i++) {
                int v2 = Integer.parseInt(input_line_split[i]);

                this.add_vertex(v2);
                this.add_edge(v1, v2);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void load_input_line_weighted(String[] input_line_split)
    {
        try
        {
            int v1 = Integer.parseInt(input_line_split[0]);
            this.add_vertex(v1);

            for (int i = 2; i < input_line_split.length - 1; i += 2) {
                int v2 = Integer.parseInt(input_line_split[i]);
                int weight = Integer.parseInt(input_line_split[i + 1]);

                this.add_vertex(v2);
                this.add_edge(v1, v2);
                this.add_weight(v1, v2, weight);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void add_vertex(int id)
    {
        if(this.vertices.containsKey(id))
            return;

        this.vertices.put(id, new Vertex(id));
    }

    public void add_edge(Integer id1, Integer id2)
    {
        Vertex v1 = vertices.get( id1 );
        Vertex v2 = vertices.get( id2 );

        if ( v1 == null || v2 == null )
        {
            System.out.printf("Vértice inexistente!\n");
            return;
        }

        v1.add_neighbor( v2 );
        v2.add_neighbor( v1 );
    }

    public void add_weight(Integer id1, Integer id2, Integer weight)
    {
        Vertex v1 = vertices.get( id1 );
        Vertex v2 = vertices.get( id2 );

        if ( v1 == null || v2 == null )
        {
            System.out.printf("Vértice inexistente!\n");
            return;
        }

        v1.add_weight(id2, weight);
        v2.add_weight(id1, weight);
    }

    public HashMap< Integer, Vertex> get_vertices()
    {
        return vertices;
    }

    public boolean is_connected()
    {
        HashSet<Vertex> visited = new HashSet<>();

        dfs_visited(vertices.values().iterator().next(), visited);

        for (Vertex v : vertices.values())
        {
            if(visited.contains(v) == false)
            {
                return false;
            }
        }
        return true;
    }

    public void dfs_visited(Vertex v, HashSet<Vertex> visited)
    {
        visited.add(v);

        for (Vertex neighbour : v.get_neighbour_vertices())
        {
            if(visited.contains(neighbour) == false)
            {
                dfs_visited(neighbour, visited);
            }
        }
    }

}
