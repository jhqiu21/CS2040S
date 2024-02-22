# Tutorial 4

## Problem 1. Trees Review

(a)

- Find `successor(70)` which is `72`
- Delete `65` and substitute it with its successor which is `72`

(b) `23,55,60,66`

???



(c) We can define a balance factor for each nodes in ALV tree. Balance factor of a node in an AVL tree is the difference between the height of its left subtrees and right subtrees, which should be -1, 0, 1. Hence, the extra space required for each node is reduced to 2 bits.

(d) Pre-order traverse: `65, 52, 39, 23, 58, 57, 55, 60, 70, 68, 66, 72`

use stack $O(n)$

- push if it is smaller than the top => top < N
- pop if it is bigger than the top => top > N
  $t(n) = n \cdot O(1) = O(n)$
- `Step 1` let the first element be the root of the tree
- `Step 2` Start from the first element add element to its left subtree, until we encounter `A[i] < A[i+1]`
- `Step 3` Recurse like `A[2]:A[i]` until we encounter `A[n] > A[i+1] > A[n + 1]` and set the right subtree of `A[n+1]` as `A[i+1]`
- `Step 4` Keep doing `Step 1` to `Step 3` until we traverse all the result array

## Problem 2. Iterative BFS and DFS

use a queue and stack

- BFS (like the level order traverse)
- DFS (like preorder/in-order/postorder traverse)

## Problem 3. Chicken Rice

(a) Worst Case: The bast one is the first plate on your table, you have to compare it with the other $n-1$ plates. Therefore when you ends up your comparision. There is $\frac1n$ left on the winner plate

(b)

- Have a taste on the first plate of the remaining plates, if its taste is worse than the plate on your table, mark it as pivot
- Then compare the remaining $n-2$ plates with the first plate on the remaining table, can classify them by if they have a better flavor than `pivot`
- then choose the better group, can keep doing like step 1 and step 2, until you determine which plate is the best chicken rice
- On the worst case, the best one is the plate on your table, and it will take about $O(\log{n})$ time to identify which one is the best. Hence, it will remain about $n-\log{n}$ bites

(c) Use Quick Select?

- As it will take $T(n) = T(\frac nk) + O(n) = O(n)$ bite to find the median plate, therefore, it will consume $O(n)$ meal in total
- We will take $O(\log{n})$ bite of the median plate as $\frac{n}{k^i} = 1 → i = \log_k{n}$

## Problem 4. Economic Research

- First find the total average and divide
- Construct an AVL tree
-

## Problem 5. Height of Binary Tree After Subtree Removal Queries

// what's the question?
