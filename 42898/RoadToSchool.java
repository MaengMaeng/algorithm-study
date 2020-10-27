package al1022;

public class RoadToSchool {
	public static void main(String[] args) {
		int[][] puddles = {{2,2}};
		System.out.println(solution(4,3,puddles));
	}
	public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] loads = new int[m][n];
//        loads[m][n] = -1;
        for(int[] puddle:puddles) {
        	loads[puddle[0]-1][puddle[1]-1] = -1;
        }
        loads[0][0] = 1;
        for(int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		if(i == 0 && j == 0) {
        			continue;
        		}
        		if(loads[i][j] == -1) {
        			loads[i][j] = 0;
        			continue;
        		}
        		int left = (i-1 < 0) ? 0 : loads[i-1][j];
        		int up = (j-1 < 0) ? 0 : loads[i][j-1];
        		loads[i][j] = (int)(((long)left + up) % 1000000007);
        	}
        }
        return loads[m-1][n-1];
    }
}
