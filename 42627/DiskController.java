package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DiskController {
	public static void main(String[] args) {
		int[][] jobs = {{0,3},{1,9},{2,6}};
		System.out.println(solution(jobs));
	}
	public static int solution(int[][] jobs) {
        int answer = 0;
        Job[] myJobs = new Job[jobs.length];
        for(int i = 0; i < jobs.length; i++) {
        	myJobs[i] = new Job(jobs[i][0], jobs[i][1]);
        	answer += jobs[i][1];
        }
        //들어온 시간을 기준으로 오름차순 정렬
        Comparator c = new Comparator<Job>() {
        	@Override
        	public int compare(Job o1, Job o2) {
        		if(o1.request < o2.request) {
        			if(o1.time < o2.time) {
        				
        			}
        			return -1;
        		}
        		return 1;
        	}
		};
		//처리시간을 기준으로 오름차순 정렬
		Comparator c1 = new Comparator<Job>() {
        	@Override
        	public int compare(Job o1, Job o2) {
        		if(o1.time < o2.time) {
        			return -1;
        		}
        		return 1;
        	}
		};
		// {{0,10}, {4,10},{6,8}} 10초 -> {6,8}
        Arrays.sort(myJobs, c);
        ArrayList<Job> queue = new ArrayList();
        int jobIndex = 0;
        int time = 0;
        int waitingTime = 0;
        Job executeJob = null;
        do {
        	if(jobIndex < myJobs.length && myJobs[jobIndex].request == time) {
        		queue.add(myJobs[jobIndex++]);
        		queue.sort(c1);
        		continue;
        	}
        	if(executeJob == null && queue.size() > 0) {
        		executeJob = queue.remove(0);	
        	}
        	if(executeJob != null) {
        		executeJob.time--;
        		if(executeJob.time == 0) {
        			executeJob = null;
        		}
        	}
        	waitingTime += queue.size();
        	System.out.println(time + " " + queue.size());
        	time++;
        }while(time <= 2000 || queue.size() > 0);
        
        return (answer + waitingTime) / jobs.length;
    }
	
	public static class Job{
		int request;
		int time;
		public Job(int request, int time) {
			this.request = request;
			this.time = time;
		}
	}
	
}
