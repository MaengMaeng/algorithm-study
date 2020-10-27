package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NodeMostFar {
	static boolean[][] graph;
	public static void main(String[] args) {
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		System.out.println(solution(6, edge));
	}
	public static int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new boolean[n+1][n+1];
        for(int[] e :edge) {
        	graph[e[0]][e[1]] = true;
        	graph[e[1]][e[0]] = true;
        }
        int[] weightes = new int[n+1];//1로부터의 가중치(몇번째로 떨어져있는가)
        int max = 0;
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int weight = 0;
        while(!queue.isEmpty()) {//bfs
        	ArrayList<Integer> outputs = new ArrayList<>();
        	while(!queue.isEmpty()) {//1로부터 weight번째 떨어져있는 노드 전부꺼냄
        		int output = queue.poll();
        		outputs.add(output);
        		weightes[output] = weight;
        	}
        	for(int output:outputs) {//꺼낸 노드와 인접한 노드 중 방문하지 않은 노드는 다음 루프를 위해 queue에 삽입
        		for(int i = 1; i < graph.length; i++) {
        			if(graph[output][i] && !visited[i]) {
        				queue.add(i);
        				visited[i] = true;
        			}
        		}
        	}
        	weight++;//루프 횟수(1로부터 n번째 떨어짐)
        }
        for(int w: weightes) {
//        	System.out.println(w);
        	if(w == weight-1) {
        		answer++;
        	}
        }
        return answer;
    }
	
}
