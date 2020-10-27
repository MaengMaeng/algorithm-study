package al1023;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class DoublePriorityQueue2 {
	public static void main(String[] args) {
//		String[] operations = {"I 7","I 5","I -5","D -1", "D 1"};
//		String[] operations = {"I 16", "D 1"};
		String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
//		String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		System.out.println(solution(operations)[0] + " " + solution(operations)[1]);
	}
	public static int[] solution(String[] operations) {
        int[] answer = {0, 0};
        HashMap<Integer, Integer> map = new HashMap();
       
        Comparator minC = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		};
        for(String operation:operations) {
        	String[] parse = operation.split(" ");
        	if(parse[0].equals("I")) {
        		int value = Integer.parseInt(parse[1]);
        		if(map.containsKey(value)) {
        			map.put(value, map.get(value) + 1);
        		}
        		else {
        			map.put(value, 1);
        		}
        	}
        	else {
        		int value = Integer.parseInt(parse[1]);
        		if(map.isEmpty()) {
        			continue;
        		}
        		if(value == 1) {
        			ArrayList<Integer> array = new ArrayList(map.keySet());
        			array.sort(minC);
        			int min = array.get(0);
        			if(map.get(min) == 1) {
        				map.remove(min);
        			}
        			else {
        				map.put(min, map.get(min) - 1);
        			}
        		}
        		else {
        			ArrayList<Integer> array = new ArrayList(map.keySet());
        			array.sort(minC);
        			int max = array.get(array.size()-1);
        			if(map.get(max) == 1) {
        				map.remove(max);
        			}
        			else {
        				map.put(max, map.get(max) - 1);
        			}
        		}
        	}
        }
        ArrayList<Integer> array = new ArrayList(map.keySet());
        System.out.println(array.size());
    	array.sort(minC);
    	if(map.size() == 0) {
    		return answer;
    	}
    	answer[0] = array.get(0);
    	answer[1] = array.get(array.size()-1);
        return answer;
    }
}
