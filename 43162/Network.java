package al1102;

import java.util.HashMap;

public class Network {
	static int[] parent;
	public static int solution(int n, int[][] computers) {
        parent = new int[computers.length];
        for(int i = 0; i < parent.length; i++) {
        	parent[i] = i;
        }
        for(int i = 0; i < computers.length; i++) {
        	for(int j = i+1; j < computers[i].length; j++) {
        		if(i != j && computers[i][j] == 1) {
        			union(i, j);
        		}
        	}
        }
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(int p:parent) {
        	if(!map.containsKey(p)) {
        		map.put(p, true);
        	}
        }
        return map.size();
    }
	
	public static void union(int c1, int c2) {
		int x = find(c1);
		int y = find(c2);
		if(x < y) {
			parent[c2] = x;
			for(int i = 0; i < parent.length; i++) {
				if(parent[i] == y) {
					parent[i] = x;
				}
			}
		}
		else {
			parent[c1] = y;
			for(int i = 0; i < parent.length; i++) {
				if(parent[i] == x) {
					parent[i] = y;
				}
			}
		}
	}
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return find(parent[x]);
	}
}
