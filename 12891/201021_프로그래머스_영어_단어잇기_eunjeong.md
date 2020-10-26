# 영어 단어 잇기

## 실패코드

```python
def solution(n, words):
    answer = []
    num, round = 0, 1
    words_len = len(words)
    # 앞단어의 끝 -뒷단어의 앞글자가 같은지 비교 해야 한다
    for i in range(0, words_len-2):
        # 앞단어의 끝 -뒷단어의 앞글자가 같은지 비교 해야 한다
        print(words[i][-1], words[i+1][0])
        num = num+1
        if words[i][-1] == words[i+1][0]:
            if num == n:
                round += 1
                num = 0
        else:
            answer = [num, round]
 
    if len(answer) == 0:
        return [0, 0]
    else:      
        return answer
```





## 코드

```python
def solution(n, words):
    answer = []
    num, round = 2, 1
    words_len = len(words) 
    used_words = set([words[0]])
    flag = False
    
    # 앞단어의 끝 -뒷단어의 앞글자가 같은지 비교 해야 한다 
    for i in range(1, words_len):
        # 앞단어의 끝 -뒷단어의 앞글자가 같은지 비교 해야 한다
        # print(i, num, round, words[i-1][-1], words[i][0])
        # print(i, num, round, words[i-1], words[i])
        #print(used_words)
        if words[i-1][-1] == words[i][0] and words[i] not in used_words:
            #print(words[i])
            used_words.add(words[i])  
        else:
            answer = [num, round]
            flag = True
            break
        
        if num == n:
            round += 1
            num = 0
            
        num = num+1    
        
    if len(answer) == 0:
        return [0, 0]
    else:       
        return answer
```

