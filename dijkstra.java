package greedy.mst;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class dijkstra {

    static int INF = 10000000;
    public static void main(String[] args) {

        int [][] adjacent = {{  },  // 노드 번호로 직관적으로 참조하기위해 0번째는 그냥 인위적으로 생성
                {INF , 2, 3, 6, INF,INF},
                {INF,INF,INF,INF,INF,4},
                {INF,INF,INF,1,INF,INF},
                {INF,INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF}};

        int [] dist = solve(adjacent,1);
        for (int i = 1; i<dist.length; i++){   // 0번째는 필요없는 인위적인 index라 1부터 참조
            if (dist[i] != INF){
                System.out.print(dist[i]+" ");
            }
            else {
                System.out.print("INF ");
            }
        }
    }

    public static int[] solve(int[][] adjacent, int k){

        int [] dist = new int [adjacent.length];
        Arrays.fill(dist, INF);

        dist[k] = 0;  //   [INF], [0], [INF], [INF] ,[INF], [INF], [INF]
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>();
        minHeap.add(new Node(0, k));

        while (!minHeap.isEmpty()){
            Node edge = minHeap.poll();  // 가장 적은놈을 빼서 edge에 저장
            int current_dist = edge.dist;
            int here = edge.loca;

            for (int i = 0; i<adjacent.length-1; i++){
                if (adjacent[here][i]!= INF){   // 이동 할 수 있는 노드라면
                    int there = i+1 ;  // 0번째부터이기에 1더해준다 (0은 인위적인 인덱스라)
                    int length = adjacent[here][i];
                    int next_dist = dist[here]+length;


                    if (next_dist<dist[there]){
                        dist[there] = next_dist;
                        minHeap.add(new Node(next_dist,there));
                    }
                }
            }
        }
        return dist;
    }

    public static class Node implements Comparable<Node> {
        int dist;
        int loca;

        public Node(int dist, int loca) {
            this.dist = dist;
            this.loca = loca;
        }

        // 저장 할 때 어떤식으로 나올지 사용하는 메서드이지만 차피 .dist로 접근하기에 필요 X
        @Override
        public String toString() {
            return loca+"";
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
