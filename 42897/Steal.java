package al1022;

public class Steal {
	public static void main(String[] args) {
		int[] money = {1, 2, 3, 1};
		System.out.println(solution(money));
	}
	public static int solution(int[] money) {
        int answer = 0;
        int[] bestArray = new int[money.length];
        bestArray[0] = money[0];
        bestArray[1] = Math.max(money[0], money[1]);
        boolean[] isFirstSelected = new boolean[money.length];
        isFirstSelected[0] = true;
        isFirstSelected[1] = (money[0] > money[1]) ? true:false;
        for(int i = 2; i < money.length; i++) {
//        	if(i == money.length-1) {
//        		if(isFirstSelected[i-2]) {
//        			bestArray[i] = Math.max(bestArray[i-2] + money[i] - money[0], bestArray[i-1]);	
//        		}
//        		else {
//        			bestArray[i] = Math.max(bestArray[i-2] + money[i], bestArray[i-1]);
//        		}
//        		break;
//        	}
        	// 1 3 4 2
        	// 1 3 5 5
        	// true false true ?
        	int whenSelected = (i == bestArray.length-1 && isFirstSelected[i-2])?bestArray[i-2] + money[i]-money[0]: bestArray[i-2] + money[i];
        	int whenNotSelected = bestArray[i-1];
        	if(whenSelected < whenNotSelected) {
        		bestArray[i] = whenNotSelected;
        		isFirstSelected[i] = isFirstSelected[i-2];
        	}
        	else {
        		bestArray[i] = whenSelected;
        		isFirstSelected[i] = isFirstSelected[i-1];
        	}
        }
//        if(isFirstSelected[isFirstSelected.length-1]) {
//        	int min = Math.min(money[0], money[money.length-1]);
//        	bestArray[bestArray.length-1] -= min;
//        }
//        bestArray[bestArray.length-1] = Math.max(bestArray[bestArray.length-1], bestArray[bestArray.length-2]);
        
        return bestArray[bestArray.length-1];
    }
}
