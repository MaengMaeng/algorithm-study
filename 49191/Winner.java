package graph;

public class Winner {
	static boolean[][] graph;
	public static void main(String[] args) {
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
		System.out.println(solution(5, results));
	}
	public static int solution(int n, int[][] results) {
	        int answer = 0;
	        graph = new boolean[n+1][n+1];
	        for(int[] result: results) {
	        	graph[result[0]][result[1]] = true;
	        }
	        int[][] rankGraph = new int[n+1][n+1];
	        for(int i = 1; i < graph.length; i++) {
	        	for(int j = 1; j < graph[i].length; j++) {
	        		if(graph[i][j]) {
	        			findWinNum(rankGraph, i, j);
	        		}
	        	}
	        }
	        
	        for(int i = 1; i < rankGraph.length; i++) {
	        	int sum = 0;
	        	for(int j = 1; j < rankGraph[i].length; j++) {
	        		sum += rankGraph[i][j] + rankGraph[j][i];
	        	}
	        	if(sum == n-1) {
	        		answer++;
	        	}
	        }
	       
	        return answer;
	}
	
	public static int findWinNum(int[][] rankGraph, int winner, int loser) {
		int win = 0;
		rankGraph[winner][loser] = 1;
		for(int i = 1; i < graph.length; i++) {
			if(graph[i][winner] && rankGraph[i][loser] == 0) {
				findWinNum(rankGraph, i, loser);
			}
		}
		return win;
	}
}
