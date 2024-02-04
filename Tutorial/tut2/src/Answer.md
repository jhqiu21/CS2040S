# Tutorial 2

## Problem 1. Time Complexity Analysis

(a) $T(n) = O(n)$

(b) $T(n) = 2T(\frac{n}{2}) + O(n) = O(n\log{}n)$

(c) $T(n) = \sum_{i=0}^{n}O(i) = O(1) + O(2) + O(3) + ... + O(n) = O(\frac{n(n+1)}{2}) = O(n^2)$

(d) $T(n) = T(\frac{n}{2}) + T(\frac{n}{2}) + O(n) = O(n\log{}n)$

(e) $T(n) = T(n - 1) + T(n - 2) + O(1) = O(\frac{\Phi^n - (1-\Phi)^n}{\sqrt{5}}) $, where $\Phi = \frac{1+\sqrt{}5}{2}$

(f) $T(n) = \sum_{i = 1}^{n} O(\lfloor \log{}i + 1\rfloor) = O(\log_{2}\prod_{i=i}^{n}i)+O(n)=O(\log{}n!)$

(g) $T(n) = O(n)$

## Problem 2. Sorting Review

(a) $T(n) = T(n - 1) + O(n) = O(n^2)$

But, if we use binary search to find the insert position

$$
T(n) = T(n-1) + O(\log{n})= \sum_{i = 0}^{n} O(\log{(n-i)}) \le \sum_{i = 0}^{n} O(\log{(n)}) =O(n\log{n})
$$

(b) `[(2, 1), (1, 4), (1, 3)]` should be sorted into `[(1, 3), (1, 4), (2, 1)]`

- Use select sort to sort the pair by `b` first
- Then use the merge sort to sort the pair by head `a`, as merge sort is stable, it will not affect the order of `b` when they have the same `a`

(c)

- Time Complexity: $T(n) = O(n\log{n})$
- Space Complexity: $S(n) = O(n)$


## Problem 3. Queues and Stacks Review
