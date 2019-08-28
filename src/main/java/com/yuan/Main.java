package com.yuan;

import com.yuan.solution.LeetcodeSolution;
import com.yuan.solution.OfferSolution;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LeetcodeSolution lcSol = new LeetcodeSolution();
        OfferSolution offSol = new OfferSolution();

        System.out.println(offSol.verifySquenceOfBST(new int[]{4,8,6,12,16,14,10}));
    }
}
