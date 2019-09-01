package com.yuan;

import com.yuan.entity.TreeNode;
import com.yuan.solution.LeetcodeSolution;
import com.yuan.solution.OfferSolution;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LeetcodeSolution lcSol = new LeetcodeSolution();
        OfferSolution offSol = new OfferSolution();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(1);
        System.out.println(offSol.getUglyNumber(11));
    }
}
