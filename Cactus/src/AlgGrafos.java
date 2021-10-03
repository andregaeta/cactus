public class AlgGrafos
{
    public static void main(String[] args)
    {
        Graph graph = new Graph("src/myfiles/grafo01.txt", false);

        boolean is_cactus = CactusValidator.is_cactus(graph);
        System.out.println(is_cactus ? "Sim, o grafo é cacto." : "Não, o grafo não é cacto.");
    }

}
