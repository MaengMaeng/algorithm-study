# 가장 먼 노드

## 유형

- 그래프 문제 

## 푼 방법

- 그래프 + BFS

<img src="/Users/eunjeong/Library/Application Support/typora-user-images/image-20201020224608037.png" alt="image-20201020224608037" style="zoom:67%;" />

## 코드

```python
from collections import deque
def solution(n, vertex):
    graph = [ [] for _ in range(n+1)]
    distance = [-1 for _ in range(n+1)]
    answer = 0

    # 그래프 만들기(연결리스트 방식)
    for v in vertex:
        if v[1] not in graph[v[0]]:
            graph[v[0]].append(v[1])
        if v[0] not in graph[v[1]]:
            graph[v[1]].append(v[0])

    start = 1
    q = deque([start])

    while q:
        node = q.popleft()
        # 거리정보 update
        # distance[node] += 1

        for v in graph[node]:
            if distance[v] == -1:
              	# 이전까지 왔던 거리를 기록(큐에서 나오면서 +1 될 것임.. )
                distance[v] = distance[node] + 1
                q.append(v)

   # 최대값을 구하고 해당하는 노드들이 몇개 있는지 센다            
    max_value = max(distance)
    for index, d in enumerate(distance):
        if max_value == d:
            answer += 1

    return answer
```

