package com.yuan;


import com.yuan.solution.OfferSolution;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        OfferSolution offSol = new OfferSolution();
        int[] input = new int[]{4,5,1,6,2,7,3,8,-3};
        ArrayList<Integer> leastNumbers = offSol.getLeastNumbers(input, 6);
        System.out.println(leastNumbers);
    }
}
