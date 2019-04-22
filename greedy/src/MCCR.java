import java.util.HashMap;

class node{
    int vertex;
    int key;
}

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // TODO
            // Whether a vertex is in mst solution or not
            Boolean[] mst_queue = new Boolean[G.numberOfV()];
            node[] e = new node[G.numberOfV()];

            IndexPQ<Integer> queue = new IndexPQ<>(G.numberOfV());

            for (int i = 0; i < G.numberOfV(); i++){
                e[i] = new node();
                e[i].key = Integer.MAX_VALUE;
                e[i].vertex = i;
                mst_queue[i] = false;
            }

            // Include the source vertex
            mst_queue[0] = true;
            e[0].key = 0;

            for(int i = 0; i < G.numberOfV(); i++){
                queue.insert(e[i].vertex,e[i].key);
            }

            while(!queue.isEmpty()){
                int start = queue.delMin();
                mst_queue[start] = true;
                for(Edge neighbour: G.edges(start)){
                    int vertex = neighbour.other(start);
                    if(!mst_queue[vertex]){
                        if(e[vertex].key > neighbour.weight()){
                            queue.decreaseKey(vertex,neighbour.weight());
                            e[vertex].key = neighbour.weight();
                        }
                    }


                }
            }
            int sum = 0;
            for(int i = 0;i < G.numberOfV();i++){
                sum += e[i].key;
            }
            return sum;
        }

    }

