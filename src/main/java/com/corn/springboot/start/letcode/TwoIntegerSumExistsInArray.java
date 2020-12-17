package com.corn.springboot.start.letcode;

import java.util.Arrays;

public class TwoIntegerSumExistsInArray {

    public static void main(String[] args) {
        int arr[] = {-5,-1,3,5,6,8,9,11};
        int sum = 18;
        Arrays.sort(arr);
        int middle = arr.length/2;
        int toLeft = middle - 1;
        int toRight = middle;
        System.out.println(Find(arr,sum));

//        while(toLeft>0 && toRight< arr.length-1){
//            if(arr[toLeft] + arr[toRight] == sum){
//                System.out.println("find sum in index:"+toLeft+","+toRight);
//                break;
//            }
//            if(arr[toLeft] + arr[toRight] < sum){
//                //往右移动
//                ++toRight;
//            }
//            if(arr[toLeft] + arr[toRight] > sum){
//                //往右移动
//                --toLeft;
//            }
//        }
    }

    static Boolean Find(int[] arr, int sum)
    {
        // 对数组排序
        Arrays.sort(arr, 0, arr.length);

        int i = ( arr.length - 1 ) / 2;
        int j = i + 1;

        while ( ( i >= 0 ) && (j < arr.length))
        {
            // 找到
            if(arr[i] + arr[j]  == sum)
            {
                System.out.println(arr[i]+"+"+arr[j]+"="+sum);
                return true;
            }
            else if(arr[i] + arr[j] > sum)
            {
                --i;
            }
            else // (arr[i] + arr[j]  < sum)
            {
                ++j;
            }
        }

        // goes here
        System.out.println("未找到！ ");
        return false;
    }
}
