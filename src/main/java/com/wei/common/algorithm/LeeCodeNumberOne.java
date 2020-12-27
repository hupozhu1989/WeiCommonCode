package com.wei.common.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣第一题
 * https://leetcode-cn.com/problems/two-sum/
 */
class LeeCodeNumberOne {
    /**
     * 暴力破解
     */
    public static int[] method01(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int[] method02(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int partnerNumber = target - nums[i];
            if (map.containsKey(partnerNumber)){
                return new int[]{map.get(partnerNumber),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {3,6,10,5,8,4};
        //int[] result = method01(nums, 18);
        int[] result = method02(nums, 18);
        System.out.println(result[0]+"......"+result[1]);
    }
}