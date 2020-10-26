def solution(jobs):
    answer = 0
    job_count = len(jobs)
    job_time = 0
    jobs = sorted(jobs, key=lambda x: x[1])

    # 그러면 그냥 jobs -> q로 넣어주는 부분 뭔가 이상하니가
    # 즉시즉시 q에서 빼주기
    # 작업시간이 작은 순으로 이미 정렬은 되어있음.
    # 여기서 가능한 각 작업들의 요청시간이 job_time(현재작업시간)보다 이전에 요청되면 작업 짧은순으로 빼기
    while len(jobs) > 0:
        # 아 고려 안된 점. 해당 작업 처리시간 - 해장 작업 요청시간 + 이전 작업들의 처리시간
        #jobs = sorted(jobs, key=lambda x: x[1])
        for index, job in enumerate(jobs):
            # 작업 요청시간이 job_time(현재작업시간) 이전이라면
            if job[0] <= job_time:
                job_time += job[1]
                # 해당 작업 처리시간 - 해장 작업 요청시간
                answer += job_time - job[0]
                break

            # ㅅㅂ 예제 안돌아가서 ... 왜그런가 했더니
            # 현재 작업시간에 그 다음 작업들이 아직 안들어 올 수도 있음;
            # 시간 증가가 필요함
            if index == len(jobs)-1:
                job_time += 1

    return (answer // job_count)

jobs = [[0, 10], [4, 10], [5, 11], [15, 2]]
print(solution(jobs))
