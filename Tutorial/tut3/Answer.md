# Tutorial 3

## Problem 1. QuickSort Review

(a) Suppose that the pivot choice is the median of the first, middle and last keys, can you find a bad input for QuickSort?

**Bad Input:**

- already sorted array
- descend array
- array has duplicated elements

(b) Are any of the partitioning algorithms we have seen for QuickSort stable? Can you design a stable partitioning algorithm? Would it be efficient?

(c) Consider a QuickSort implementation that uses the 3-way partitioning scheme (i.e. elements equal to the pivot are partitioned into their own segment).

- If an input array of size n contains all identical keys, what is the asymptotic bound for QuickSort?
  For example, you are sorting the array [3, 3, 3, 3, 3, 3]

  $T(n) = O(n)$ when the contents are all duplicated, after the first partition, the unsorted array size reduce to 0, hence we do not need to do $T(k) $ and $T(n-k)$.
- If an input array of size n contains k < n distinct keys, what is the asymptotic bound for QuickSort?
  For example, with n = 6, k = 3, sort the array [a, b, a, c, b, c]

  Note that for array of size $n$ contains all identical keys, the quick sort asymptotic bound is $O(n)$. Then, if there are $k$ distinct keys, the bound

  $T(n) \le \sum_{i = 1}^{k} O(n) = O(kn)$

## Problem 2

(a) Given an array A, decide if there are any duplicated elements in the array.

- `Step 1` Sort the array, it will take $O(n\log{n})$ time
- `Step 2` Traverse the array from 0 to n-2, find if `A[i] = A[i+1]` and return

Hence, the time complexity of this algorithm is $T(n) = O(n\log{n}) + O(n) = O(n\log{n})$

(b) Given an array A, output another array B with all the duplicates removed. Note the order of the elements in B does not need to follow the same order in A. That means if array A is `{3, 2, 1, 3, 2, 1}`, then your algorithm can output `{1, 2, 3}`.

- `Step 1` Sort the array, it will take $O(n\log{n})$ time
- `Step 2` Declare a new array `B`
- `Step 3` Traverse the array `A` from 1 to n-1 and if `A[i] != A[i - 1]` copy the element from array `A` to array `B`

Hence, the time complexity of this algorithm is $T(n) = O(n\log{n}) + O(n) = O(n\log{n})$

(c) Given arrays A and B, output a new array C containing all the distinct items in both A and B. You are given that array A and array B already have their duplicates removed.

- `Step 1` Sort the array `A` anf `B`
- `Step 2` Merge Array `A` and `B`, much similar to the merge function in merge sort, note that when we encounter `A[0] = B[0]` we just copy one element to the array `C` and move both pointers of array `A` and `B`

Hence, the time complexity of this algorithm is $T(n) = 2O(n\log{n}) + O(k + m) = O(n\log{n})$, where k and m are the size of array `A` and `B`

(d) Given array A and a target value, output two elements x and y in A where (x + y) equals the target value.

## Problem 3.

- `Step 1` Choose a pair of shoes randomly, try to find the right boy that match this shoes
- `Step 2` Set that boy and shoes as pivot and partition kids by "smaller feet" and "bigger feet"
- `Step 3` Keep recursion like this and until every kids get their shoes
- `Time Complexity` $T(n) = T(k) + T(n - k) + O(n) = O(n\log{n})$, where $k \in \left [  \frac1{10}n, \frac9{10}n \right ]$

## Problem 4. More Pivots!

(a) Divide the array into k + 1 part, like
`[<pivot1, >=pivot1 && <=pivot2, ... , >=pivot(n - 1) && <=pivot(n), >pivot(n)]`

(b) What is the asymptotic running time of your partitioning algorithm? Give your answer in terms of the number of elements, n and the number of pivots, k.

- `Step 1` Sort the k pivots: $T(n) = O(k\log{k})$
- `Step 2`
  - partition(0, pivot(k / 2))
  - partition(pivot(k / 2) + 1, n)

Hence, in step 2 we have $O(n) \times O(\log{k})$

Therefore, the time complexity is $T(n) = O(k\log{k}) + O(n\log{k}) = O(n\log{k})$

(c) $T(n) = kT(\frac nk) + O(n\log{k})$

(d) $T(n) = kT(\frac nk) + O(n\log{k})= k^2T(\frac n{k^2}) + kO(\frac nk \log{k}) + O(n\log{k}) = ... =\log_k{n}O(n\log{k})=O(n\log{n}) $. Hence using more pivots will not result in an improvement in the asymptotic running time.

## Problem 5. Integer Sort

(a) We can use the partition in QuickSort. For binary array contains only 0 and 1, it will take $O(n)$ time to "sort" them. However, when we conduct this process in-place, this algorithm is unstable.

(b) We can sort this array by declare a assistent array B to store the information of array A and use array C to store the output

- `Step 1` Traverse the array and record the number of each integer from 0 to M
- `Step 2` According to the number of each component we can find the size of the array C
- `Step 3` Assign C[i] in order
- `Time Complexity` $O(n)$

(c) $T(n) \le 64O(n) = O(n)$

// TODO

(d) 
