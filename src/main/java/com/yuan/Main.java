package com.yuan;

import com.yuan.entity.ListNode;
import com.yuan.entity.TreeNode;
import com.yuan.solution.LeetcodeSolution;
import com.yuan.solution.OfferSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeetcodeSolution lcSol = new LeetcodeSolution();
        OfferSolution offSol = new OfferSolution();
        int [] arrs = new int[]{1,3,3,3,3,4,5};

        offSol.getNumberOfK(arrs, 6);
    }
}
