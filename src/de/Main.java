package de;

import java.util.List;

public class Main {

    private static int day1_1() {
        List<Integer> inputList = IO.readInts();

        for(int i = 0; i < inputList.size(); i++){
            for(int j = 0; j < inputList.size(); j++){
                if(i != j && inputList.get(i) + inputList.get(j) == 2020){
                    return inputList.get(i) * inputList.get(j);
                }
            }
        }
        return -1;
    }

    private static int day1_2() {
        List<Integer> inputList = IO.readInts();

        for(int i = 0; i < inputList.size(); i++){
            for(int j = 0; j < inputList.size(); j++){
                for(int k = 0; k < inputList.size(); k++){
                    if(i != j && i != k &&
                            inputList.get(i) + inputList.get(j) + inputList.get(k) == 2020){
                        return inputList.get(i) * inputList.get(j) * inputList.get(k);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int result = day1_2();
        System.out.println("result = " + result);
    }
}
