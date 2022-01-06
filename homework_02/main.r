new = c(2159, 1875, 1860, 1875, 1901, 1911, 1930, 1468, 1923, 1886, 1964, 1906, 1907, 2546, 2098, 2154, 2122, 1781, 2005, 2088, 2266, 2048, 1974, 1927, 2119, 1989, 1827, 1998, 1935, 1886)
old = c(2682, 2500, 2582, 2553, 2560, 2563, 2570, 2721, 2731, 2705, 2536, 2515, 2549, 3654, 2610, 3937, 3486, 2779, 2817, 3827, 2606, 2651, 2660, 2603, 2614, 3196, 2740, 2647, 2676, 2543)

print(mean(new))
print(mean(old))

print(length(old))

plot(1:30, new, type="l", col="blue", ylim=c(0,4000), xlim=c(1, 30), xlab = 'it', ylab='ms')
lines(1:30, old, col="red")
