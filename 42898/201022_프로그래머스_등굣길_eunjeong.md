# 등굣길

## DP(다이나믹 프로그래밍)

### 다이나믹 프로그래밍을 사용하는 순간

1. 큰 문제를 작은 문제로 나눌 수 있다.
2. 작은 문제에서 구한 정답은 그것을 포함하는 큰 문제에서도 동일하다. 

## 풀이 과정

![image-20201022213919355](/Users/eunjeong/Library/Application Support/typora-user-images/image-20201022213919355.png)

![image-20201022213940110](/Users/eunjeong/Library/Application Support/typora-user-images/image-20201022213940110.png)



## 코드

```python
def solution(money):
    money_len = len(money)
    dp, dp2 = [0] * money_len, [0] * money_len
    dp[0] = money[0]
    dp[1] = max(money[0], money[1])

    # 첫번째 집과 마지막 집은 상보적인관계(왜냐? 인접해있으니까)
    # case -> 첫집
    for i in range(2, money_len - 1): 
        dp[i] = max(dp[i-1], money[i]+dp[i-2])

    dp2[0] = 0
    dp2[1] = money[1]
    for i in range(2, money_len): # 마지막 집선택 ㅇㅋ 가능성
        dp2[i] = max(dp2[i-1], money[i]+dp2[i-2])

    return max(max(dp), max(dp2))
```



