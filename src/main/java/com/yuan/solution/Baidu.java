package com.yuan.solution;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Character.MAX_VALUE;
import static java.lang.Math.abs;

public class Baidu {

    /**
     * 百度2017春招笔试第一题
     * https://www.nowcoder.com/questionTerminal/f3aaf8fedcea43c6b12a11710068726b
     *
     * 度度熊想去商场买一顶帽子，商场里有N顶帽子，有些帽子的价格可能相同。
     * 度度熊想买一顶价格第三便宜的帽子，问第三便宜的帽子价格是多少？
     *
     * 思路：维护一个大小为3的数组
     */
    public void getLeastThree(){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num <= 3) {
            System.out.println(30);
        }
        int[] prices = new int[]{1001, 1001, 1001};
        while (scanner.hasNext()) {
            int curPrice = scanner.nextInt();
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] == curPrice) {
                    break;
                }
                if (curPrice < prices[i]) {
                    int replacedPrice = prices[i];
                    prices[i] = curPrice;
                    i++;
                    while (i < prices.length) {
                        int temp = prices[i];
                        prices[i] = replacedPrice;
                        replacedPrice = temp;
                        i++;
                    }
                }
            }
        }
        if (prices[2] <= 1000) {
            System.out.println(prices[2]);
        } else {
            System.out.println(5);
        }
    }


    /**
     * 百度2017春招笔试第二题
     * 链接：https://www.nowcoder.com/questionTerminal/b96a7519b60748d7a77f13570776c3ac
     *
     * 一个数轴上共有N个点，第一个点的坐标是度度熊现在位置，第N-1个点是度度熊的家。现在他需要依次的从0号坐标走到N-1号坐标。
     * 但是除了0号坐标和N-1号坐标，他可以在其余的N-2个坐标中选出一个点，并直接将这个点忽略掉，问度度熊回家至少走多少距离？
     *
     * 思路：算出去除每一个点的代价
     */
    public void leastDistance() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] coordnts = new int[num];
        int distance = 0;
        for (int i = 0; i < num; i++) {
            coordnts[i] = in.nextInt();
        }
        if (num == 2) {
            System.out.println(coordnts[1] - coordnts[0]);
            return;
        }
        int[] savedDistance = new int[num - 2];
        for (int i = 1; i < coordnts.length - 1; i++) {
            savedDistance[i - 1] = abs(coordnts[i] - coordnts[i-1]) + abs(coordnts[i + 1] - coordnts[i])
                    - abs(coordnts[i + 1] - coordnts[i - 1]);
            distance += abs(coordnts[i] - coordnts[i - 1]);
        }
        distance += abs(coordnts[coordnts.length - 1] - coordnts[coordnts.length - 2]);
        Arrays.sort(savedDistance);
        if (savedDistance[savedDistance.length - 1] > 0) {
            System.out.println(distance - savedDistance[savedDistance.length - 1]);
        } else {
            System.out.println(distance);
        }
    }

    /**
     * 百度2017春招笔试第四题
     * 链接：https://www.nowcoder.com/questionTerminal/adc291e7e79f452c8b59243a5ce68d3a
     *
     * 度度熊有一个N个数的数组，他想将数组从小到大 排好序，但是萌萌的度度熊只会下面这个操作：
     * 任取数组中的一个数然后将它放置在数组的最后一个位置。
     * 问最少操作多少次可以使得数组从小到大有序？
     *
     * 思路：找出最小的需要调整的数，然后再遍历一遍数组，找出比它大的数
     *
     */
    public void leastOperationToOrdered() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = in.nextInt();
        }
        int minToChange = MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (nums[i] >= minToChange) {
                continue;
            }
            for (int j = i + 1; j < size; j++) {
                if (nums[j] < nums[i]) {
                    minToChange = nums[i];
                    break;
                }
            }
        }
        int result = 0;
        for (int num: nums) {
            if (num >= minToChange) {
                result++;
            }
        }
        System.out.println(result);
    }

}
