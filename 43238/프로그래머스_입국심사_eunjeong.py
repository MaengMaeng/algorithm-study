def solution(n, times):
    times = sorted(times)
    time_length = len(times)
    left, right = int(times[0] * n / time_length), times[-1] * n
    answer, mid = 0, 0

    while left <= right:
        mid = int((left + right) / 2)

        result = 0
        for t in times:
            result += int(mid / t)

        if result < n:
            left = mid + 1
        else:
            right = mid - 1
            answer = mid

    return answer