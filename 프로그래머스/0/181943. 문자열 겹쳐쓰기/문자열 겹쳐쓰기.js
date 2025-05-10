function solution(my_string, overwrite_string, s) {
    // my_string의 앞부분 + overwrite_string + my_string의 뒷부분
    return my_string.slice(0, s) + overwrite_string + my_string.slice(s + overwrite_string.length);
}
