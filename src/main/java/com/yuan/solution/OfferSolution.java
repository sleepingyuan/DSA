package com.yuan.solution;

import com.yuan.entity.ListNode;
import com.yuan.entity.RandomListNode;
import com.yuan.entity.TreeNode;

import java.util.*;

/**
 * @author yuan
 */
public class OfferSolution {

    /**
     * 剑指offer-01 对每一行进行二分查找，后续查找的行大小逐渐递减
     * @param target 目标值
     * @param array 搜索的二维数组
     */
    public boolean find(int target, int [][] array) {
        if(array.length == 0 || array[0].length == 0){
            return false;
        }
        int rowId = 0, inRowCnt = array[0].length;
        while(rowId < array.length){
            int lo = 0;
            while(lo < inRowCnt){
                int mid = (lo + inRowCnt) / 2;
                if(target < array[rowId][mid]){
                    inRowCnt = mid;
                }else if(target == array[rowId][mid]){
                    return true;
                }else{
                    lo = mid + 1;
                }
            }
            rowId++;
        }
        return false;
    }

    /**
     * 剑指offer-02，遍历StringBuffer即可，注意遍历的方式
     * @param str 给定的string
     */
    public String replaceSpace(StringBuffer str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                sb.append("%20");
            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 剑指offer-03，翻转链表
     * @param listNode 头结点
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>();
        }
        ListNode guard = new ListNode(0);
        guard.next = listNode;
        while (listNode.next != null) {
            ListNode cur = listNode.next;
            listNode.next = listNode.next.next;
            cur.next = guard.next;
            guard.next = cur;
        }
        ArrayList<Integer> result = new ArrayList<>();
        while(guard.next != null){
            guard = guard.next;
            result.add(guard.val);
        }
        return result;
    }

    /**
     * 剑指offer-04 重建二叉树,写一个辅助函数或者用一个函数进行递归
     * @param pre 前序遍历
     * @param in 中序遍历
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre.length == 0) {
            return null;
        }
        if (pre.length == 1) {
            return new TreeNode(pre[0]);
        }
        TreeNode result = new TreeNode(pre[0]);
        int idxIn = 0;
        while (idxIn < pre.length) {
            if (in[idxIn] == result.val) {
                break;
            }else {
                idxIn++;
            }
        }
        result.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, idxIn + 1),
                                            Arrays.copyOfRange(in, 0, idxIn));
        result.right = reConstructBinaryTree(Arrays.copyOfRange(pre, idxIn+1, pre.length),
                                             Arrays.copyOfRange(in, idxIn+1, in.length));
        return result;
    }

    /**
     * 剑指offer-05 用两个栈来实现一个队列
     */
    class queueToStack {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

    /**
     * 剑指offer-06 旋转数组的最小数字，二分,比较中间的值与lo值的大小，注意边界情况
     * @param array 旋转数组
     */
    public int minNumberInRotateArray(int [] array) {
        int lo = 0, hi = array.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (mid + 1 == array.length) {
                return array[mid];
            } else if (array[mid + 1] < array[mid]) {
                return array[mid + 1];
            } else if (array[lo] <= array[mid]){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return array[lo];
    }

    /**
     * 剑指offer-07 斐波那契数列第n项，动归
     * @param n 第n项
     */
    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 2) {
            return 1;
        }
        int[] fiboArr = new int[n];
        fiboArr[0] = fiboArr[1] = 1;
        for (int i = 2; i < fiboArr.length; i++) {
            fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
        }
        return fiboArr[n - 1];
    }

    /**
     * 剑指offer-08 跳台阶，跳第n级台阶共多少种方法，动归
     * @param target 第n级台阶
     */
    public int jumpFloor(int target) {
        if (target == 0) {
            return 0;
        }
        if (target <= 2) {
            return target == 2? 2: 1;
        }
        int[] jumpNums = new int[target];
        jumpNums[0] = 1;
        jumpNums[1] = 2;
        for (int i = 2; i < jumpNums.length; i++) {
            jumpNums[i] = jumpNums[i - 1] + jumpNums[i - 2];
        }
        return jumpNums[target - 1];
    }

    /**
     * 剑指offer-09
     */
    public int jumpFloorII(int target) {
        if (target == 0) {
            return 0;
        }
        return (int)Math.pow(2, target - 1);
    }

    /**
     * 剑指offer-10 同斐波那契数列
     */
    public int rectCover(int target) {
        if (target == 0) {
            return 0;
        }
        if (target <= 2) {
            return target == 2? 2: 1;
        }
        int[] methods = new int[target];
        methods[0] = 1;
        methods[1] = 2;
        for (int i = 2; i < target; i++) {
            methods[i] = methods[i - 2] + methods[i - 1];
        }
        return methods[target - 1];
    }

    /**
     * 剑指offer-11 输出二进制中1的个数
     */
    public int numberOf1(int n) {
        int cnts = 0;
        while (n != 0) {
            cnts++;
            n = n & (n - 1);
        }
        return cnts;
    }


    /**
     * 剑指offer-12 注意负指数
     * @param base 基数
     * @param exponent 指数
     */
    public double power(double base, int exponent) {
        boolean negaFlag = false;
        if (exponent < 0) {
            exponent = -1 * exponent;
            negaFlag = true;

        }
        if (exponent == 0) {
            return 1;
        } else if (exponent == 1) {
            if (negaFlag) {
                return 1 / base;
            }
            return base;
        } else if (exponent % 2 == 1) {
            if (negaFlag) {
                return 1 / (power(base, (exponent - 1) / 2 ) * power(base, (exponent - 1) / 2 ) * base);
            }
            return power(base, (exponent - 1) / 2 ) * power(base, (exponent - 1) / 2 ) * base;
        } else {
            if (negaFlag) {
                return 1 / (power(base, exponent / 2) * power(base, exponent / 2));
            }
            return power(base, exponent / 2) * power(base, exponent / 2);
        }
    }

    /**
     * 剑指offer-13 改变数组中的顺序，奇数在前 偶数在后 注意算法的稳定性
     */
    public void reOrderArray(int [] array) {
        if (array.length == 0) {
            return;
        }
        int odd = 0, even = 0;
        while (even < array.length && array[even] % 2 == 1) {
            even++;
        }
        odd = even;
        while (odd < array.length) {
            while (odd < array.length && array[odd] % 2 == 0) {
                odd++;
            }
            if (odd < array.length) {
                int evenLen = odd - even;
                int oddIdx = odd;
                while (odd < array.length && array[odd] % 2 == 1) {
                    odd++;
                }
                int oddLen = odd - oddIdx;
                changeLocalOrder(array, even, evenLen, oddIdx, oddLen);

                even = even + oddLen;
            }
        }
    }

    private void changeLocalOrder(int [] array, int evenIdx, int evenLen, int oddIdx, int oddLen) {
        int [] tempArr = new int[oddLen];
        System.arraycopy(array, oddIdx, tempArr, 0, oddLen);
        System.arraycopy(array, evenIdx, array, evenIdx + oddLen, evenLen);
        System.arraycopy(tempArr, 0, array, evenIdx, oddLen);
    }

    /**
     * 剑指offer-14 倒数第k个节点 两个指针即可
     * @param head 头结点
     * @param k 倒数第k个
     */
    public ListNode findKthToTail(ListNode head,int k) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode fol = head;
        for (int i = 0; i < k; i++) {
            if (fol == null) {
                return null;
            } else {
                fol = fol.next;
            }
        }
        while (fol != null) {
            pre = pre.next;
            fol = fol.next;
        }
        return pre;
    }

    /**
     * 剑指offer-15 反转链表
     * @param head 头结点
     * @return 新链表的表头
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        while (head.next != null) {
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = sentinel.next;
            sentinel.next = cur;
        }
        return sentinel.next;
    }

    /**
     * 剑指offer-16 合成单调链表
     * @param list1 单调递增链表1
     * @param list2 单调递增链表2
     * @return 新链表表头
     */
    public ListNode merge(ListNode list1,ListNode list2) {
        ListNode sentinel = new ListNode(0);
        ListNode cur = sentinel;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }
        return sentinel.next;
    }

    /**
     * 剑指offer-17 判断root2是不是root1的子结构，规定空树不是任何树的子结构
     * 递归判断
     */
    public boolean hasSubtree(TreeNode root1,TreeNode root2) {
        if (root2 == null || root1 == null) {
            return false;
        }
        return hasSubTreeDFS(root1, root2) || hasSubTreeDFS(root1.left, root2)
                || hasSubTreeDFS(root1.right, root2);
    }

    public boolean hasSubTreeDFS(TreeNode root1,TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val && hasSubTreeDFS(root1.left, root2.left) && hasSubTreeDFS(root1.right, root2.right)) {
            return true;
        } else {
            return hasSubTreeDFS(root1.left, root2) || hasSubTreeDFS(root1.right, root2);
        }
    }

    /**
     * 剑指offer-18 镜像反转二叉树
     * 递归反转
     */
    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    /**
     * 剑指offer-19 顺时针打印矩阵
     * 注意处理特殊情况
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rowSt = 0, rowEnd = matrix.length - 1, colSt = 0, colEnd = matrix[0].length - 1;
        ArrayList<Integer> result = new ArrayList<>();
        while (rowSt < rowEnd && colSt < colEnd) {
            int colId = colSt;
            while (colId < colEnd) {
                result.add(matrix[rowSt][colId]);
                colId++;
            }
            int rowId = rowSt;
            while (rowId < rowEnd) {
                result.add(matrix[rowId][colEnd]);
                rowId++;
            }
            while (colSt < colId) {
                result.add(matrix[rowEnd][colId]);
                colId--;
            }
            while (rowSt < rowId) {
                result.add(matrix[rowId][colSt]);
                rowId--;
            }

            rowSt++;
            colSt++;
            rowEnd--;
            colEnd--;
        }
        if (rowSt == rowEnd && colSt == colEnd) {
            result.add(matrix[rowSt][colSt]);
        } else if (rowSt == rowEnd && colSt < colEnd) {
            while (colSt <= colEnd) {
                result.add(matrix[rowSt][colSt]);
                colSt++;
            }
        } else if (rowSt < rowEnd && colSt == colEnd) {
            while (rowSt <= rowEnd) {
                result.add(matrix[rowSt][colSt]);
                rowSt++;
            }
        }
        return result;
    }

    /**
     * 剑指offer-20 最小元素栈
     * 只用一个栈实现，注意最小值
     */
    class minStack{

        private Stack<Integer> stack = new Stack<>();
        private int minVal = 0;

        public void push(int node) {
            if (stack.isEmpty()) {
                stack.push(node);
                minVal = node;
            } else {
                if (node < minVal) {
                    stack.push(minVal);
                    stack.push(node);
                    minVal = node;
                } else {
                    stack.push(node);
                }
            }
        }

        public void pop() {
            int popVal = stack.pop();
            if (popVal == minVal) {
                minVal = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minVal;
        }
    }

    /**
     * 剑指offer-21 栈的压入序列
     * 辅助栈
     */
    public boolean isPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int popIdx = 0;
        for (int i = 0; i < pushA.length; i++) {
            if (pushA[i] != popA[popIdx]) {
                stack.push(pushA[i]);
            } else {
                popIdx++;
            }
        }
        while (popIdx != popA.length) {
            if (popA[popIdx] != stack.pop()) {
                return false;
            } else {
                popIdx++;
            }
        }
        return true;
    }

    /**
     * 剑指offer-22 从上到下打印二叉树
     */
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            TreeNode curNode = nodes.poll();
            if (curNode.left != null) {
                nodes.add(curNode.left);
            }
            if (curNode.right != null) {
                nodes.add(curNode.right);
            }
            result.add(curNode.val);
        }
        return result;
    }

    /**
     * 剑指offer-23 判断是否是二叉搜索树的后序遍历
     * 后序遍历的最后一个节点为根节点
     */
    public boolean verifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length <= 1) {
            return true;
        }
        int rootVal = sequence[sequence.length - 1];
        int leftSubTreeIdx = 0;
        while (leftSubTreeIdx < sequence.length - 1) {
            if (rootVal < sequence[leftSubTreeIdx]) {
                break;
            } else {
                leftSubTreeIdx++;
            }
        }
        int rightSubTreeIdx = leftSubTreeIdx;
        while (rightSubTreeIdx < sequence.length - 1) {
            if (sequence[rightSubTreeIdx] < rootVal) {
                return false;
            } else {
                rightSubTreeIdx++;
            }
        }
        rightSubTreeIdx--;
        if (leftSubTreeIdx == sequence.length - 1 || leftSubTreeIdx == 0) {
            int [] subTree = new int[leftSubTreeIdx + 1];
            System.arraycopy(sequence, 0, subTree, 0, subTree.length);
            return verifySquenceOfBST(subTree);
        } else {
            int [] leftSubTree = new int[leftSubTreeIdx];
            int [] rightSubTree = new int[rightSubTreeIdx - leftSubTreeIdx + 1];
            System.arraycopy(sequence, 0, leftSubTree, 0, leftSubTree.length);
            System.arraycopy(sequence, leftSubTreeIdx, rightSubTree, 0, rightSubTree.length);
            return verifySquenceOfBST(leftSubTree) && verifySquenceOfBST(rightSubTree);
        }
    }

    /**
     * 剑指offer-24 打印出满足条件的所有路径
     * 广度优先，下面的方法能通过测试，但是是错的
     */
    private ArrayList<ArrayList<Integer>> fpResult = new ArrayList<>();
    private ArrayList<Integer> fpPath = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        if (root == null) {
            return fpResult;
        }
        fpPath.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            fpResult.add(new ArrayList<>(fpPath));
        } else {
            findPath(root.left, target);
            findPath(root.right, target);
        }
        fpPath.remove(fpPath.size() - 1);
        return fpResult;
    }

    /**
     * 剑指offer-25 复杂链表的复制
     * 维护一个新旧节点的映射关系，两轮遍历
     */
    public RandomListNode clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> oldToNew = new HashMap<>();
        RandomListNode oldNode = pHead;
        RandomListNode newHead = new RandomListNode(oldNode.label);
        RandomListNode newNode = newHead;
        oldToNew.put(oldNode, newNode);
        oldNode = oldNode.next;
        while (oldNode != null) {
            newNode.next = new RandomListNode(oldNode.label);
            newNode = newNode.next;
            oldToNew.put(oldNode, newNode);
            oldNode = oldNode.next;
        }
        oldNode = pHead;
        newNode = newHead;
        while (oldNode != null) {
            newNode.random = oldToNew.getOrDefault(oldNode.random, null);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return newHead;
    }

    /**
     * 剑指offer-26 二叉树转双向链表
     * 递归
     */
    public TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        return convertHelper(pRootOfTree)[0];
    }

    private TreeNode[] convertHelper(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new TreeNode[]{node, node};
        } else if (node.left != null && node.right == null) {
            TreeNode[] left = convertHelper(node.left);
            left[1].right = node;
            node.left = left[1];
            return new TreeNode[]{left[0], node};
        } else if (node.left == null) {
            TreeNode[] right = convertHelper(node.right);
            right[0].left = node;
            node.right = right[0];
            return new TreeNode[]{node, right[1]};
        } else {
            TreeNode[] left = convertHelper(node.left);
            TreeNode[] right = convertHelper(node.right);
            left[1].right = node;
            node.left = left[1];
            right[0].left = node;
            node.right = right[0];
            return new TreeNode[]{left[0], right[1]};
        }
    }

    /**
     * 剑指offer-27 按字典序打印所有排列
     */
    public ArrayList<String> permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            permutationHelper(str.toCharArray(), i, result);
            Collections.sort(result);
        }
        return result;
    }

    private void permutationHelper(char[] cs, int idx , ArrayList<String> result) {
        if (idx == cs.length - 1) {
            String curRes = String.valueOf(cs);
            if (!result.contains(curRes)) {
                result.add(curRes);
            }
        } else {
            for (int j = idx; j < cs.length; ++j) {
                swap(cs, j, idx);
                permutationHelper(cs, idx+1, result);
                swap(cs, j, idx);
            }
        }
    }

    private void swap(char [] arr, int i ,int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 剑指offer-28 出现次数超过一半的数字
     * 投票算法
     */
    public int moreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        Map<Integer, Integer> numToCnts = new HashMap<>();
        int size = array.length;
        for (int num: array) {
            numToCnts.put(num, numToCnts.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry: numToCnts.entrySet()) {
            if (entry.getValue() > size / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
