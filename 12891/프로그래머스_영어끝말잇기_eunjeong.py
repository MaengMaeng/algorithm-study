def solution(n, words):
    answer = []
    num, round = 2, 1
    words_len = len(words)
    used_words = set([words[0]])
    flag = False

    # 앞단어의 끝 -뒷단어의 앞글자가 같은지 비교 해야 한다
    for i in range(1, words_len):
        # 앞단어의 끝 -뒷단어의 앞글자가 같은지 비교 해야 한다
        # print(used_words)
        if words[i - 1][-1] == words[i][0] and words[i] not in used_words:
            # print(words[i])
            used_words.add(words[i])
        else:
            answer = [num, round]
            flag = True
            break

        if num == n:
            round += 1
            num = 0

        num = num + 1

    if len(answer) == 0:
        return [0, 0]
    else:
        return answer