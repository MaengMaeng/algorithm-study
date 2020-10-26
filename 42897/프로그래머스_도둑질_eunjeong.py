def solution(money):
    money_len = len(money)
    dp, dp2 = [0] * money_len, [0] * money_len
    dp[0] = money[0]
    dp[1] = money[1]

    # 첫번째 집과 마지막 집은 상보적인관계(왜냐? 인접해있으니까)
    # case -> 첫집
    for i in range(2, money_len - 1):
        dp[i] = max(dp[i-1], money[i]+dp[i-2])

    dp2[0] = 0
    dp2[1] = money[1]
    for i in range(2, money_len): # 마지막 집을 무조건 터는 경우
        dp2[i] = max(dp2[i-1], money[i]+dp2[i-2])

    return max(max(dp), max(dp2))