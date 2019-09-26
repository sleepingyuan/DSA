package com.yuan.solution;

public class SortAlgo {

    /**
     * 归并排序
     */
    public static void mergeSort(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        mergeSort(nums, lo, hi);
    }

    private static void mergeSort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);
        merge(nums, lo, mid + 1, hi);
    }

    private static void merge(int[] nums, int lo, int mid, int hi) {
        int[] sorted = new int[hi - lo + 1];
        int leftIdx = lo, rightIdx = mid, sortedIdx = 0;
        while (leftIdx < mid && rightIdx <= hi) {
            if (nums[leftIdx] < nums[rightIdx]) {
                sorted[sortedIdx++] = nums[leftIdx++];
            } else {
                sorted[sortedIdx++] = nums[rightIdx++];
            }
        }
        while (leftIdx < mid) {
            sorted[sortedIdx++] = nums[leftIdx++];
        }
        while (rightIdx <= hi) {
            sorted[sortedIdx++] = nums[rightIdx++];
        }
        for (int value : sorted) {
            nums[lo++] = value;
        }
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            percDown(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i >= 0 ; i--) {
            swap(nums, 0, i);
            percDown(nums, 0, i);
        }
    }
    private static void percDown(int[] nums, int idx, int size) {
        int val = nums[idx];
        int child = idx;
        for (; 2 * idx + 1 < size; idx = child) {
            child = 2 * idx + 1;
            if (child + 1 < size && nums[child] < nums[child + 1]) {
                child = child + 1;
            }
            if (val < nums[child]) {
                nums[idx] = nums[child];
                nums[child] = val;
            } else {
                break;
            }
        }
        nums[child] = val;
    }

    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 5, 0, 10, 2};
        heapSort(nums);
        System.out.println("cs");
    }
}
