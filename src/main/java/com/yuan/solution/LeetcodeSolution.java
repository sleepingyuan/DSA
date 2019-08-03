package com.yuan.solution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class LeetcodeSolution {

    public int findPeakElement(int[] nums){
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){
            int mid = (lo + hi) / 2;
            if (nums[mid] < nums[mid + 1]) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static int compareVersion(String version1, String version2) {
        String[] componentOfVer1 = version1.split("\\.");
        String[] componentOfVer2 = version2.split("\\.");
        int index = 0;
        while (index < componentOfVer1.length && index < componentOfVer2.length){
            if (Integer.parseInt(componentOfVer1[index]) < Integer.parseInt(componentOfVer2[index])) return -1;
            else if (Integer.parseInt(componentOfVer1[index]) > Integer.parseInt(componentOfVer2[index])) return 1;
            else index++;
        }
        if (index < componentOfVer1.length){
            while (index < componentOfVer1.length){
                if (Integer.parseInt(componentOfVer1[index]) != 0) return 1;
                index++;
            }
        }
        else if (index < componentOfVer2.length){
            while (index < componentOfVer2.length){
                if (Integer.parseInt(componentOfVer2[index]) != 0) return -1;
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
        else return resultBuffer.toString();
    }

    public int[] twoSum(int[] numbers, int target) {
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi && numbers[lo] + numbers[hi] != target){
            if (numbers[lo] + numbers[hi] < target) lo++;
            else hi--;
        }
        return new int[] {lo + 1, hi + 1};
    }

    public String convertToTitle(int n){
        if (n <= 0) return null;
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
            if (charToHandle < 'A' || charToHandle > 'Z') return -1;
            result += (charToHandle - 'A' + 1) * pos;
            pos *= 26;
        }
        return result;
    }

    public int trailingZeroes(int n) {
        int result = 0;
        int num = 5;
        if (n < num) return result;
        result++;
        while (num < n) {
            num += 5;
            if (n < num) return result;
            int temp = num;
            while (temp % 10 != 0){
                result++;
                temp /= 10;
            }
            num += 5;
            if (n < num) return result;
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
                if (Long.parseLong(s1) < Long.parseLong(s2)) return 1;
                else if (Long.parseLong(s1) == Long.parseLong(s2)) return 0;
                else return -1;
            }
        });
        if (numsInStrForm[0].equals("0")) return "0";
        for (String str : numsInStrForm) result.append(str);
        return result.toString();
    }
}
