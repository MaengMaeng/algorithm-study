def solution(operations):
    answer = []
    q = list()
    for op in operations:
        op = op.split()
        # print(op)
        if op[0] == 'I':
            q.append(int(op[1]))

        if op[0] == 'D':
            if len(q) == 0:
                continue

            if op[1] == '1':
                m = max(q)
                q.remove(m)

            if op[1] == '-1':
                m = min(q)
                q.remove(m)

    if len(q) == 0:
        answer = [0, 0]
    else:
        answer.append(max(q))
        answer.append(min(q))

    return answer