class programmers_42898_eunjeong {
    public int solution(int m, int n, int[][] puddles) {
    	//	puddles: 물에 잠긴 지역의 좌표
        int answer = 0;
        
        int map[][] = new int[n+1][m+1];
        
        // 1. 물웅덩이 표시하기
        for (int i =0; i< puddles.length; i++) {
        	map[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        // 기준점 설정
        map[1][1] = 1;
        // 2. map 돌면서 memoization
        for (int x=1; x < n+1; x++ ) {
        	for (int y=1; y < m+1; y++) {
                if(x ==1 && y == 1){
                    map[x][y] = 1;
                    continue;
                }
                
                if (map[x][y] == -1){
                    map[x][y] = 0;
                    continue;
                }       			

        		int down = map[x - 1][y] == -1 ? 0 : map[x - 1][y];
        		int right = map[x][y - 1] == -1 ? 0 : map[x][y - 1];
        		  		
                map[x][y] = (int)(((long) down + right) % 1000000007);
        		
        	}
        }
     
        return map[n][m];
    }
}
