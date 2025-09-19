def solution(numbers, hand):
    # 숫자 → 좌표 매핑
    pos = {
        1:(0,0), 2:(0,1), 3:(0,2),
        4:(1,0), 5:(1,1), 6:(1,2),
        7:(2,0), 8:(2,1), 9:(2,2),
        '*':(3,0), 0:(3,1), '#':(3,2)
    }

    # 초기 손가락 위치
    left = pos['*']
    right = pos['#']

    answer = []

    for num in numbers:
        if num in [1,4,7]:
            answer.append('L')
            left = pos[num]
        elif num in [3,6,9]:
            answer.append('R')
            right = pos[num]
        else:  # 2,5,8,0
            target = pos[num]
            left_dist = abs(left[0]-target[0]) + abs(left[1]-target[1])
            right_dist = abs(right[0]-target[0]) + abs(right[1]-target[1])

            if left_dist < right_dist:
                answer.append('L')
                left = target
            elif right_dist < left_dist:
                answer.append('R')
                right = target
            else:  # 거리 같음 → hand 선호
                if hand == "left":
                    answer.append('L')
                    left = target
                else:
                    answer.append('R')
                    right = target

    return ''.join(answer)
