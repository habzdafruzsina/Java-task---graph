package elte.hu.eltecom.graph;

public class GraphFactory {

    private GraphFactory(){}

    public static Graph initGraph(){
        UnlimitedGraph unlimitedGraph = new UnlimitedGraph();
        return unlimitedGraph;
    }

    public static Graph initGraph(int graphSize){
        if (graphSize<1){
            return null;
        } else {
            LimitedGraph limitedGraph = new LimitedGraph(graphSize);
            return limitedGraph;
        }
    }

}
