package com.yuan.solution;

import com.yuan.entity.ListNode;

import java.util.ArrayList;

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

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode == null) {
            return null;
        }
        return null;
    }
}
