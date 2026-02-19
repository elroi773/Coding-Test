def solution(routes):
    # 진출 지점 기준으로 정렬
    routes.sort(key=lambda x: x[1])

    cameras = 0
    cam_pos = -30001  # 가능한 범위 밖에서 시작

    for start, end in routes:
        # 현재 카메라 위치가 이 차량 구간에 없으면 새로 설치
        if cam_pos < start:
            cameras += 1
            cam_pos = end  # 이 차량의 진출 지점에 설치

    return cameras