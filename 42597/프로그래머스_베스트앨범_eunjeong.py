from collections import Counter
def solution(genres, plays):
    answer = []
    result, best_plays ={}, {}
    length = len(genres)
    
    # 딕셔너리 내부에 리스트를 가져가서 관리하기
    for i in range(length):
        # 장르가 없으면 딕셔너리에 새로 해당 장르를 생성하기
        if not result.get(genres[i]):
            result[genres[i]] = list()
            best_plays[genres[i]] = 0   
        
        result[genres[i]].append([i, plays[i]])        
        best_plays[genres[i]] += plays[i]
    
    
    # 베스트 앨범 두개 고르기 -> 아 두곡이지 두개가 아니구나... 
    #best_plays = Counter(best_plays).most_common(2)
    best_plays = Counter(best_plays).most_common(length)
    
    for b in best_plays:
        # 정렬처리: 1. 장르 내에서 많이 재생된 노래, 2. 고유 번호가 낮은 노래
        g = sorted(result[b[0]], key=lambda x: ( -x[1], x[0]))
        print(g)
        if len(g) <2:
            answer.append(g[0][0])
        else:
            answer.append(g[0][0])
            answer.append(g[1][0])
            
    return answer
