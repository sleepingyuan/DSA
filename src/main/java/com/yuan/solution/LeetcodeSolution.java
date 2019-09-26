package com.yuan.solution;

import com.yuan.entity.ListNode;

import java.io.Serializable;
import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class LeetcodeSolution implements Serializable {

    public int findPeakElement(int[] nums){
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){
            int mid = (lo + hi) / 2;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static int compareVersion(String version1, String version2) {
        String[] componentOfVer1 = version1.split("\\.");
        String[] componentOfVer2 = version2.split("\\.");
        int index = 0;
        while (index < componentOfVer1.length && index < componentOfVer2.length){
            if (Integer.parseInt(componentOfVer1[index]) < Integer.parseInt(componentOfVer2[index])) {
                return -1;
            } else if (Integer.parseInt(componentOfVer1[index]) > Integer.parseInt(componentOfVer2[index])) {
                return 1;
            } else {
                index++;
            }
        }
        if (index < componentOfVer1.length){
            while (index < componentOfVer1.length){
                if (Integer.parseInt(componentOfVer1[index]) != 0) {
                    return 1;
                }
                index++;
            }
        }
        else if (index < componentOfVer2.length){
            while (index < componentOfVer2.length){
                if (Integer.parseInt(componentOfVer2[index]) != 0) {
                    return -1;
                }
                index++;
            }
        }
        return 0;
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        HashMap<Long, Integer> dividend = new HashMap<>();
        StringBuffer resultBuffer = new StringBuffer();
        long dividendNum = numerator;
        long toBeDividedNum = denominator;
        if (dividendNum < 0 ^ toBeDividedNum < 0 && dividendNum != 0){
            resultBuffer.append('-');
        }
        dividendNum = Math.abs(dividendNum);
        toBeDividedNum = Math.abs(toBeDividedNum);
        resultBuffer.append(dividendNum / toBeDividedNum);
        if (dividendNum % toBeDividedNum == 0){
            return resultBuffer.toString();
        }
        resultBuffer.append('.');
        int offset = resultBuffer.length();
        int occurLocation = 0;
        dividendNum = dividendNum % toBeDividedNum;
        while (dividendNum != 0 && !dividend.containsKey(dividendNum)){
            dividend.put(dividendNum, occurLocation++);
            resultBuffer.append(dividendNum * 10 / toBeDividedNum);
            dividendNum = (dividendNum * 10) % toBeDividedNum;
        }
        if (dividend.containsKey(dividendNum)){
            occurLocation = dividend.get(dividendNum);
            resultBuffer.insert(offset + occurLocation, '(');
            resultBuffer.append(')');
            return resultBuffer.toString();
        }
        else {
            return resultBuffer.toString();
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi && numbers[lo] + numbers[hi] != target){
            if (numbers[lo] + numbers[hi] < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[] {lo + 1, hi + 1};
    }

    public String convertToTitle(int n){
        if (n <= 0) {
            return null;
        }
        StringBuffer resultBuffer = new StringBuffer();
        while (n != 0) {
            n--;
            resultBuffer.append((char)(n % 26 + 'A'));
            n /= 26;
        }
        return resultBuffer.reverse().toString();
    }


    public int titleToNumber(String s){
        int result = 0;
        int pos = 1;
        for (int idx = s.length() - 1; idx >= 0 ; idx--) {
            char charToHandle = s.charAt(idx);
            if (charToHandle < 'A' || charToHandle > 'Z') {
                return -1;
            }
            result += (charToHandle - 'A' + 1) * pos;
            pos *= 26;
        }
        return result;
    }

    public int trailingZeroes(int n) {
        int result = 0;
        int num = 5;
        if (n < num) {
            return result;
        }
        result++;
        while (num < n) {
            num += 5;
            if (n < num) {
                return result;
            }
            int temp = num;
            while (temp % 10 != 0){
                result++;
                temp /= 10;
            }
            num += 5;
            if (n < num) {
                return result;
            }
            temp = num;
            while (temp % 5 != 0){
                result++;
                temp /= 5;
            }
        }
        return result;
    }

    public String largestNumber(int[] nums) {
        StringBuilder result = new StringBuilder();
        String [] numsInStrForm = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsInStrForm[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numsInStrForm, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                if (Long.parseLong(s1) < Long.parseLong(s2)) {
                    return 1;
                } else if (Long.parseLong(s1) == Long.parseLong(s2)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        if (numsInStrForm[0].equals("0")) {
            return "0";
        }
        for (String str : numsInStrForm) {
            result.append(str);
        }
        return result.toString();
    }

    /**
     * 188-买卖股票最佳时机 IV
     * 动态规划，维护一个局部最优以及全局最优
     *
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
     * global[i][j] = max(local[i][j], global[i - 1][j])
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        if (prices.length < k) {
            return getAllDiff(prices);
        }
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        local[0] = 0;
        global[0] = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[k];
    }

    public int getAllDiff(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }

    /**
     * 91-解码方法
     * 动态规划
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        if (s.charAt(0) == '0') {
            return 0;
        }
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if ((int) s.charAt(i - 1) != 0 && Integer.valueOf(s.substring(i - 1, i + 1)) <= 26) {
                if (s.charAt(i) != '0') {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1];
                }
            } else {
                if (s.charAt(i) == '0') {
                    return 0;
                }
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length() - 1];
    }

    /**
     * 221-最大正方形
     * 动态规划
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxEdgeLen = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] == '1'? 1: 0;
            maxEdgeLen = Math.max(maxEdgeLen, dp[0][i]);
        }
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] == '1'? 1: 0;
            maxEdgeLen = Math.max(maxEdgeLen, dp[i][0]);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int edgeLen = 1 + Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]);
                    maxEdgeLen = Math.max(edgeLen, maxEdgeLen);
                    dp[i][j] = edgeLen;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxEdgeLen * maxEdgeLen;
    }


    /**
     * 134 加油站
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return 0;
        }
        int stIdx = 0;
        for (; stIdx < gas.length; ) {
            int result = stIdx;
            int remainingGas = 0;
            int endIdx = stIdx;
            for (; endIdx < gas.length; endIdx++) {
                remainingGas += gas[endIdx] - cost[endIdx];
                if (remainingGas < 0) {
                    endIdx++;
                    stIdx = endIdx;
                    break;
                }
            }
            if (result != stIdx) {
                continue;
            }
            endIdx = 0;
            for (; endIdx < stIdx; endIdx++) {
                remainingGas += gas[endIdx] - cost[endIdx];
                if (remainingGas < 0) {
                    return -1;
                }
            }
            return result;
        }
        return -1;
    }

    /**
     * 202 快乐数
     */
    public boolean isHappy(int n) {
        Set<Integer> occuredNums = new HashSet<>();
        int squrSum = n;
        while (true) {
            if (occuredNums.contains(squrSum)) {
                return false;
            } else {
                occuredNums.add(squrSum);
            }
            n = squrSum;
            squrSum = 0;
            while (n != 0) {
                squrSum += (n % 10) * (n % 10);
                n = n / 10;
            }
            if (squrSum == 1) {
                return true;
            }
        }
    }

    /**
     * 371 两整数之和
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    /**
     * 152 乘积最大子序列
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] maxProd = new int[nums.length];
        int[] minProd = new int[nums.length];
        maxProd[0] = minProd[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxProd[i] = Math.max(Math.max(nums[i], nums[i] * maxProd[i - 1]), nums[i] * minProd[i - 1]);
            minProd[i] = Math.min(Math.min(nums[i], nums[i] * maxProd[i - 1]), nums[i] * minProd[i - 1]);
            result = Math.max(maxProd[i], result);
        }
        return result;
    }

    /**
     * 189 旋转数组
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return;
        }
        int preVal, curVal = nums[0], startIdx = 0, idx = 0;
        for (int iter = 0; iter < nums.length; iter++) {
            idx = (idx + k) % nums.length;
            if (idx == startIdx){
                nums[idx] = curVal;
                startIdx++;
                if (startIdx > nums.length) {
                    return;
                }
                idx = startIdx;
                curVal = nums[idx];
            }
            preVal = nums[idx];
            nums[idx] = curVal;
            curVal = preVal;
        }
    }

    /**
     * 283 移动零
     */
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        int zeroPt = 0, nonZeroPt = 0;
        while (zeroPt < nums.length && nonZeroPt < nums.length) {
            while (zeroPt < nums.length && nums[zeroPt] != 0) {
                zeroPt++;
            }
            nonZeroPt = zeroPt + 1;
            while (nonZeroPt < nums.length && nums[nonZeroPt] == 0) {
                nonZeroPt++;
            }
            if (zeroPt < nums.length && nonZeroPt < nums.length) {
                int temp = nums[zeroPt];
                nums[zeroPt] = nums[nonZeroPt];
                nums[nonZeroPt] = temp;
            }
        }
    }

    /**
     * 334 递增三元子序列
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null) {
            return false;
        }
        int min = MAX_VALUE;
        int secondMin = MAX_VALUE;
        for (int num : nums) {
            if (min < num && secondMin < num) {
                return true;
            } else if (num < min) {
                min = num;
            } else if (min < num && num < secondMin){
                secondMin = num;
            }
        }
        return false;
    }

    /**
     * 215 第k大元素
     */
    public int findKthLargest(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1, size = nums.length;
        int pivort = getPivort(nums, lo, hi);
        while (size - k != pivort) {
            if (pivort < size - k) {
                lo = pivort + 1;
                pivort = getPivort(nums, lo, hi);
            } else {
                hi = pivort - 1;
                pivort = getPivort(nums, lo, hi);
            }
        }
        return nums[pivort];
    }
    private int getPivort(int[] nums, int stIdx, int endIdx) {
        Random random = new Random();
        int pivortIdx = stIdx + random.nextInt(endIdx - stIdx + 1) % (stIdx - endIdx + 1);
        int pivortVal = nums[pivortIdx];
        nums[pivortIdx] = nums[stIdx];
        int lo = stIdx, hi = endIdx;
        while (lo < hi) {
            while (lo < hi && nums[hi] >= pivortVal) {
                hi--;
            }
            nums[lo] = nums[hi];
            while (lo < hi && pivortVal >= nums[lo]) {
                lo++;
            }
            nums[hi] = nums[lo];
        }
        nums[lo] = pivortVal;
        return lo;
    }
}
