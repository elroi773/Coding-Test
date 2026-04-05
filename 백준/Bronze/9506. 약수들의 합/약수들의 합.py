import sys

for line in sys.stdin:
    n = int(line.strip())
    if n == -1:
        break

    divisors = [1]

    i = 2
    while i * i <= n:
        if n % i == 0:
            divisors.append(i)
            if i != n // i:
                divisors.append(n // i)
        i += 1

    divisors.sort()
    total = sum(divisors)

    if total == n:
        print(f"{n} = " + " + ".join(map(str, divisors)))
    else:
        print(f"{n} is NOT perfect.")