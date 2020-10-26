# 디스크 컨트롤러

![image-20201019221454675](/Users/eunjeong/Library/Application Support/typora-user-images/image-20201019221454675.png)



## 틀린코드1

```python
# 프로그래머스 디스크 컨트롤러
from collections import deque
def solution(jobs):
    answer = 0
    length = len(jobs)

    jobs.sort()
    jobs = deque(jobs)
    job = jobs.popleft()
    print(job)
    q = deque([job])

    avg_time = 0
    current_time = 0
    while q:
        print(q)
        start, job_time_quantum = q.popleft()
        print(job)
        # 해당 작업 처리시간 - 해장 작업 요청시간 + 이전 작업들의 처리시간
        avg_time += (start - job_time_quantum + avg_time)
        current_time += start + job_time_quantum

        # 작업 가능한 내역을 queue에 넣기
        for i in range(len(jobs)):
            # 요청시간이 현재 진행중인 작업 안에 들어와야하고
            j = jobs[0]
            if j[0] <= current_time:
                q.append(jobs.popleft())
            else:
                break

        # 큐에서 정렬 및 작업시간 더해주기
        sorted(q, key=lambda x: x[1])
        # 아시발.. 파이썬 큐는 작업 람다식 정렬이 안먹네;
        # 해당 작업 처리시간 - 해장 작업 요청시간 + 이전 작업들의 처리시간


    print(avg_time)
    return avg_time // length
```



### 틀린이유

- 파이썬 deque lambda식 정렬이 안된다... (지금알았음);
- 리스트로 정렬해야겠다

## 틀린코드2

```python
def solution(jobs):
    answer = 0
    job_count = len(jobs)

    # 전에 망한이유가.. 큐 정렬이 안되는거니까 그냥 리스트그대로 쓰자;
    # list pop() <- 얘를 이용해서 큐처럼 씀;
    # 1. 큐에서 작업처리시간 짧은 순으로 정렬해서 사용하기
    jobs.sort()
    # 맨 앞에꺼 꺼내기
    q = list([jobs.pop(0)])
    jobs = sorted(jobs, key=lambda x: x[1])
    job_time = 0


    while len(q) > 0:
        q = sorted(q, key=lambda x: x[1])
        start, job_time_quantum = q.pop(0)
        # 해당 작업 처리시간 - 해장 작업 요청시간 + 이전 작업들의 처리시간
        job_time += job_time_quantum
        answer += job_time - start

        # 작업 가능한 내역을 queue에 넣기
        # 여기서 작업시간만 작고. 들어오는 경우가 고려 안된거였음
        while len(jobs) >0:
            # 요청시간이 현재 진행중인 작업 안에 들어와야하고
            if jobs[0][0] <= job_time:
                q.append(jobs.pop(0))
            else:
                break

    return (answer // job_count)
```

![image-20201019213413610](/Users/eunjeong/Library/Application Support/typora-user-images/image-20201019213413610.png)

### 틀린이유

- while len(jobs) >0 에서 작업시간만 작고 들어오는 경우가 고려 안된거였음
- 아예.. 1. 작업시간이 현재 작업시간 이전에 요청되고 2. 요청이 짧은 순으로 풀자

## 솔루션

```python
def solution(jobs):
    answer = 0
    job_count = len(jobs)
    job_time = 0
    jobs = sorted(jobs, key=lambda x: x[1])

    # 그러면 그냥 jobs -> q로 넣어주는 부분 뭔가 이상하니까 즉시즉시 q에서 빼주기
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

```

## 솔루션2

```python
def solution(jobs):
    answer = 0
    job_count = len(jobs)
    job_time = 0
    jobs = sorted(jobs, key=lambda x: x[1])

    # 그러면 그냥 jobs -> q로 넣어주는 부분 뭔가 이상하니까 즉시즉시 q에서 빼주기
    # 작업시간이 작은 순으로 이미 정렬은 되어있음.
    # 여기서 가능한 각 작업들의 요청시간이 job_time(현재작업시간)보다 이전에 요청되면 작업 짧은순으로 빼기
    while len(jobs) > 0:
        # 아 고려 안된 점. 해당 작업 처리시간 - 해장 작업 요청시간 + 이전 작업들의 처리시간
        #jobs = sorted(jobs, key=lambda x: x[1])
      while len(jobs) != 0:
              for i in range(len(jobs)):
            		# 작업 시간은 짧은데 나중에 들어오는 것들
                  if jobs[i][0] <= job_time:
                      job_time += jobs[i][1]
                      answer += job_time - jobs[i][0]
                      jobs.pop(i)
                      break
                  # 해당시점에 아직 작업이 들어오지 않았으면 시간 ++
                 	if i == len(jobs) - 1:
                      job_time += 1

            # ㅅㅂ 예제 안돌아가서 ... 왜그런가 했더니 
            # 현재 작업시간에 그 다음 작업들이 아직 안들어 올 수도 있음;
            # 시간 증가가 필요함


    return (answer // job_count)


```

