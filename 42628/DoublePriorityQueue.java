package al1023;


public class DoublePriorityQueue {
	public static void main(String[] args) {
//		String[] operations = {"I 7","I 5","I -5","D -1", "D 1"};
//		String[] operations = {"I 16", "D 1"};
//		String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
		String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		System.out.println(solution(operations));
	}
	public static int[] solution(String[] operations) {
        int[] answer = {0, 0};
        DPQ dpq = new DPQ();
        for(String operation:operations) {
        	String[] parse = operation.split(" ");
        	if(parse[0].equals("I")) {
        		dpq.add(Integer.parseInt(parse[1]));
        	}
        	else {
        		dpq.remove(Integer.parseInt(parse[1]));
        	}
        	dpq.print();
        	answer[0] = (dpq.end == null) ? 0 : dpq.end.data;
        	answer[1] = (dpq.start == null) ? 0 : dpq.start.data;
        }
        return answer;
    }
	
	static class Node{
		int data;
		Node next;
		Node prev;
		public Node(int data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	static class DPQ{
		Node start;
		Node end;
		int size = 0;
		public void add(int n) {
			size++;
			if(start == null && end == null) {
				Node p = new Node(n, null, null);
				start = p;
				end = p;
			}
			else if(start.data > n) {
				Node p = new Node(n, null, start);
				start.prev = p;
				start = p;
			}
			else {
				boolean added = false;
				for(Node p = start; p.next != null; p=p.next) {
					if(n < p.data) {
						p = new Node(n, p.prev, p);
						p.prev.next = p;
						p.next.prev = p;
						added = true;
						break;
					}
				}
				if(!added) {
					end = new Node(n, end, null);
					end.prev.next = end;
				}
				
			}
		}
		public void remove(int what) {
			if(size == 0) return;
			size--;
			if(end.equals(start)) {
				end = null;
				start = null;
				return;
				
			}
			if(what == 1) {
				if(end == null || end.prev == null) {
					end = null;
					return;
				}
				end.prev.next = null;
				end = end.prev;
			}
			else {
				if(start == null || start.next == null) {
					start = null;
					return;
				}
				start.next.prev = null;
				start = start.next;
			}
		}
		
		public void print() {
			for(Node p = start; p != null; p = p.next) {
				System.out.print(p.data + " ");
			}
			System.out.println();
			
		}
	}
}
