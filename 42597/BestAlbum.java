package al1102;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BestAlbum {
	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 500, 1000, 1500};
		int[] sol = solution(genres, plays);
		for(int i:sol) {
			System.out.print(i + " ");
		}
	}
	public static int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, PriorityQueue<Album>> pqMap = new HashMap<>();
        Comparator<Album> c = new Comparator<Album>() {
			@Override
			public int compare(Album o1, Album o2) {
				// TODO Auto-generated method stub
				if(o1.play != o2.play) {
					return o2.play - o1.play;
				}
				return o1.index - o2.index;
			}
		};
        for(int i = 0; i < genres.length; i++) {
        	if(!map.containsKey(genres[i])) {
        		map.put(genres[i], plays[i]);
        	}
        	else {
        		map.put(genres[i], map.get(genres[i]) + plays[i]);
        	}
        	if(!pqMap.containsKey(genres[i])) {
        		PriorityQueue<Album> pq = new PriorityQueue(c);
        		pq.add(new Album(genres[i], plays[i], i));
        		pqMap.put(genres[i], pq);
        	}
        	else {
        		PriorityQueue<Album> pq = pqMap.get(genres[i]);
        		pq.add(new Album(genres[i], plays[i], i));
        		pqMap.put(genres[i], pq);
        	}
        }
        ArrayList<String> keys = new ArrayList(map.keySet());
        
        keys.sort(new Comparator<String>() {
        	@Override
        	public int compare(String o1, String o2) {
        		// TODO Auto-generated method stub
        		return map.get(o2) - map.get(o1);
        	}
		});
        ArrayList<Integer> array = new ArrayList();
        for(String key:keys) {
        	array.add(pqMap.get(key).poll().index);
        	if(!pqMap.get(key).isEmpty()) {//한곡만 수록되있을 수 있다고 나옴
        		array.add(pqMap.get(key).poll().index);	
        	}
        }
        int[] answer = new int[array.size()];
        for(int i = 0; i < array.size(); i++) {
        	answer[i] = array.get(i);
        }
        return answer;
    }
	
	static class Album{
		String genre;
		int play;
		int index;
		
		public Album(String genre, int play, int index) {
			this.genre = genre;
			this.play = play;
			this.index = index;
		}
	}
}
