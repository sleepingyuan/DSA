package com.yuan.solution;

import com.yuan.entity.ListNode;
import com.yuan.entity.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
}
