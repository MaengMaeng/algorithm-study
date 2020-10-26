def solution(n, results):
    answer = 0
    # 그래프처럼 풀기...
    # 진애들 이긴애들 다 찾아서 풀었다..
    # A에게 진사람은 a를 이긴사람들에게 질 것

    winner = {x:set() for x in range(n + 1)}
    loser = {x:set() for x in range(n + 1)}

    # 이긴애랑 진애들 다 기록하기
    # winner -> 누구한테 이겼는지, loser -> 누구한테 졌는지
    for r in results:
        winner[r[0]].add(r[1])
        loser[r[1]].add(r[0])

    # 이긴 애들은 내가 이긴애들에 대해 다이김
    # 진 애들은 다짐
    for i in range(1, n + 1):
        for l in loser[i]:
            winner[l].update(winner[i])
        for w in winner[i]:
            loser[w].update(loser[i])

    for i in range(1, n+1):
        if len(winner[i]) + len(loser[i]) == n-1:
            answer += 1

    return answer

solution(6, [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]])