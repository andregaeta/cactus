import java.util.ArrayList;

public class CactusValidator
{
    public static boolean is_cactus(Graph graph)
    {
        if(graph.is_connected() == false)
            return false;

        ICycleFinder finder = new CycleFinder();
        ArrayList<Vertex[]> cycles = finder.find_cycles(graph);

        for (int i = 0; i < cycles.size() - 1; i++)
        {
            for (int j = i + 1; j < cycles.size(); j++)
            {
                if(check_intersections(cycles.get(i), cycles.get(j)) > 1)
                    return false;
            }
        }

        return true;
    }

    public static int check_intersections(Vertex[] cycleA, Vertex[] cycleB)
    {
        int intersections = 0;

        for (int i = 0; i < cycleA.length; i++)
        {
            for (int j = 0; j < cycleB.length; j++)
            {
                if(cycleA[i].get_id() == cycleB[j].get_id())
                    intersections++;
            }
        }

        return intersections;
    }
}
