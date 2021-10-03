import java.util.ArrayList;
import java.util.HashMap;

class CycleFinder implements ICycleFinder
{
    private HashMap< Integer, Vertex> graph;

    private ArrayList<Vertex[]> cycles;

    public ArrayList<Vertex[]> find_cycles(Graph graph_in)
    {
        this.graph = graph_in.get_vertices();

        this.cycles = new ArrayList<>();

        for (Vertex v : graph.values())
        {
            try_find_new_cycles(new Vertex[] {v});
        }

        return cycles;
    }

    private void try_find_new_cycles(Vertex[] path)
    {
        Vertex v = path[0];
        Vertex[] sub = new Vertex[path.length + 1];

        for(Vertex x : v.get_neighbour_vertices())
        {
            if (!visited(x, path))
            {
                sub[0] = x;
                System.arraycopy(path, 0, sub, 1, path.length);
                try_find_new_cycles(sub);
            }
            else if ((path.length > 2) && (x == path[path.length - 1]))
            {
                Vertex[] p = normalize_path(path);
                Vertex[] inv = invert_path(p);
                if (is_new(p) && is_new(inv))
                {
                    cycles.add(p);
                }
            }
        }
    }

    private static Boolean path_equals(Vertex[] a, Vertex[] b)
    {
        Boolean ret = (a[0] == b[0]) && (a.length == b.length);

        for (int i = 1; ret && (i < a.length); i++)
        {
            if (a[i].get_id() != b[i].get_id())
            {
                ret = false;
            }
        }

        return ret;
    }

    private static Vertex[] invert_path(Vertex[] path)
    {
        Vertex[] p = new Vertex[path.length];

        for (int i = 0; i < path.length; i++)
        {
            p[i] = path[path.length - 1 - i];
        }

        return normalize_path(p);
    }

    private static Vertex[] normalize_path(Vertex[] path)
    {
        Vertex[] p = new Vertex[path.length];
        Vertex x = get_lowest(path);
        Vertex n;

        System.arraycopy(path, 0, p, 0, path.length);

        while (p[0] != x)
        {
            n = p[0];
            System.arraycopy(p, 1, p, 0, p.length - 1);
            p[p.length - 1] = n;
        }

        return p;
    }

    private Boolean is_new(Vertex[] path)
    {
        for(Vertex[] p : cycles)
        {
            if (path_equals(p, path))
            {
                return false;
            }
        }

        return true;
    }

    private static Vertex get_lowest(Vertex[] path)
    {
        Vertex min = path[0];

        for (Vertex p : path)
        {
            if (p.get_id() < min.get_id())
            {
                min = p;
            }
        }

        return min;
    }

    private static Boolean visited(Vertex n, Vertex[] path)
    {
        for (Vertex v : path)
        {
            if (v == n)
            {
                return true;
            }
        }

        return false;
    }

}