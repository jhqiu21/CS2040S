# Tutorial 2

## Problem 1. Time Complexity Analysis

(a) $T(n) = O(n)$

(b) $T(n) = T(\frac{n}{2}) + O(n) = O(n)$

(c) $T(n) = \sum_{i=0}^{n}O(i) = O(1) + O(2) + O(3) + ... + O(n) = O(\frac{n(n+1)}{2}) = O(n^2)$

(d) $T(n) = T(\frac{n}{2}) + T(\frac{n}{2}) + O(n) = O(n\log{}n)$

(e) $T(n) + c = (T(n-1) + c) + (T(n-2) + c) → F(n) = F(n-1)+F(n-2)$
$T(n) = T(n - 1) + T(n - 2) + O(1) = O(\frac{\Phi^n - (1-\Phi)^n}{\sqrt{5}}) = O(\Phi^n) $, where $\Phi = \frac{1+\sqrt{}5}{2}$

(f) $T(n) = \sum_{i = 1}^{n} O(\lfloor \log{}i + 1\rfloor) = O(\log_{2}\prod_{i=i}^{n}i)+O(n)=O(\log{}n!) \le O(n\log{n}) $

!!!!!! $O(n!) = O(n\log{n})$

(g) $T(n) = O(n) \times O(n)$

!!!!!! Note that to append two array in Java. First create a new array len = len1 + len2

populate it the original two str and RT = $O(len1 + len2)$

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

- **Split the array**: Divide the input array into smaller subarrays. Initially, each element of the array is considered as a subarray of size 1.
- **Merge neighboring subarrays**: Merge adjacent pairs of subarrays iteratively until there is only one sorted subarray remaining.
- **Repeat merging**: Continue merging adjacent pairs of subarrays until there's only one sorted array left, which is the sorted version of the original input array.
- **Merge function**: Implement a merge function to merge two sorted arrays into a single sorted array. This function is crucial for merging subarrays during the iterative merging process.
- Time Complexity: $T(n) = O(n\log{n})$
- Space Complexity: $S(n) = O(n)$

## Problem 3. Queues and Stacks Review

(a) Both can be implemented by a fixed-size array and all the operations have O(1) Time Complexity.

(b) A Deque (double-ended queue) is an extension of a queue, which allows enqueueing and dequeueing from both ends of the queue. So the operations it would suppport are `enqueue front`, `dequeue front`, `enqueue back`, `dequeue back`. How can we implement a Deque with a fixed-size array in Java?

Much similar to the queue operation.Or much like a combination of two stacks.

We can set `head_front`, `tail_front`, `head_back` and `tail_back` to keep track of the peak element and pop new element in the queue

- `enqueue_front`
- `dequeue_front`
- `enqueue_back`
- `dequeue_back`

(c) What sorts of error handling would we need, and how can we best handle these situations?

We need to check if the current collection size is equal to the array’s capacity. If it is greater than the capacity, we may need to System out "invalid input".

(d) Use stack

- `Step 1` push the parentheses into the stack
- `Step 2` When the element push in is closing to the peak element i.e. when we push "(" into the stack and the original peak element is a ")", pop both element out.
- `Step 3` Keep this operation until we push all the character into the stack, check if the stack is empty. If the stack is empty, the string of parentheses are balanced

(e) Similar to the question d. Use stack and pop the pair of parentheses when the push one is closed to the peak one. When we push all the characters in the string check whether the stack is empty. If there is still elements in the stack. The parentheses are not hyperbalanced.

## Problem 4. Mountain stack

- `Step 1`: push the first element in array to the stack then push the second one, compare them.
- `Step 2`: if `a[1] < a[0]` pop out and let a[1] = 0; if `a[1] < a[0]` keep pushing element until we encounter the push one is less than the peak one. i.e. if input a[i] is less than a[j], which is the peak originally, we pop out a[i] and assign it with is `i - j - 1`
- `Step 3`: keep doing step until we run all the array
- `Step 4`: pop out all element in stack now and assign them with infinity

## Problem 5. Sorting with Queues
