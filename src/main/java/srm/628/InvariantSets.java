


public class InvariantSets {

	public long countSets(int[] function) {

		Graph graph = new Graph(function);

		graph.removeStronglyConnectedComponents();

		return graph.countSubTreesWithRoots();
	} 
}