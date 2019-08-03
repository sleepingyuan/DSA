package com.yuan;

import com.yuan.solution.LeetcodeSolution;
import com.yuan.solution.OfferSolution;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LeetcodeSolution lcSol = new LeetcodeSolution();
        OfferSolution offSol = new OfferSolution();

        int[] arr1 = new int[]{1,2,3,4,5,6,7};
        int[] arr2 = new int[]{3,2,4,1,6,5,7};
        offSol.reConstructBinaryTree(arr1, arr2);
        System.out.println("helloworld");
    }
}
