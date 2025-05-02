def solution(todo_list, finished):
    answer = [todo for todo, done in zip(todo_list, finished) if not done]
    return answer
