from collections import deque
def solution(n, computers):
    answer, count = 0, n
    visited = [0] * n
    q = deque()
    
    # 이중 while문을 사용하며 같은 네트워크에 있는 edge들을 연결
    # 연결을 모두 확인했으면 미방문 edge확인하여 다시 연결여부 확인
    while count > 0:      
        q.append(visited.index(0))
        while q:
            node = q.popleft()
            count -= 1
            visited[node] = 1
            for i, v in enumerate(computers[node]):
                if v == 1 and i != node and visited[i] == 0:
                    q.append(i)
                    visited[i] = 1        
        answer +=1

    return answer
