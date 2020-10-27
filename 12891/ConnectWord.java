package al1021;

import java.util.HashMap;

public class ConnectWord {
	public static void main(String[] args) {
//		String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//		String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
		String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
		int[] sol = solution(2, words);
		System.out.println(sol[0]);
		System.out.println(sol[1]);
	}
	public static int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        String word = "" + words[0].charAt(0);
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        for(int i = 0; i < words.length; i++) {
        	if(words[i].charAt(0) != word.charAt(word.length()-1)) {
        		answer[0] = (i % n) + 1;
        		answer[1] = (i / n) + 1;
        		break;
        	}
        	if(map.containsKey(words[i])) {
        		answer[0] = (i % n) + 1;
        		answer[1] = (i / n) + 1;
        		break;
        	}
        	map.put(words[i], true);
        	word = words[i];
        }
        return answer;
    }
}
