package com.pgj.s2bplantsimulator.controller;

public class RandomPrice {
    public static int randomPrice(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
