from collections import deque
def solution(n, vertex):
    graph = [ [] for _ in range(n+1)]
    distance = [-1 for _ in range(n+1)]
    answer = 0

    # 그래프 만들기
    for v in vertex:
        if v[1] not in graph[v[0]]:
            graph[v[0]].append(v[1])
        if v[0] not in graph[v[1]]:
            graph[v[1]].append(v[0])

    start = 1
    q = deque([start])

    while q:
        node = q.popleft()
        distance[node] += 1

        for v in graph[node]:
            if distance[v] == -1:
                distance[v] = distance[node]
                q.append(v)

    max_value = max(distance)

    for index, d in enumerate(distance):
        if max_value == d:
            answer += 1

    return answer