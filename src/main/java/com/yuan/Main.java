package com.yuan;

import com.yuan.solution.LeetcodeSolution;
import com.yuan.solution.OfferSolution;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LeetcodeSolution lcSol = new LeetcodeSolution();
        OfferSolution offSol = new OfferSolution();

        offSol.isPopOrder(new int[]{1,2,3,4,5}, new int[]{4,5,1,2,3});
    }

}
