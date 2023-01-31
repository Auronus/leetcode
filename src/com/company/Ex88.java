package com.company;

import java.util.Arrays;

class Ex88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.out.println("Merge: " + Arrays.toString(nums1) + " with " + Arrays.toString(nums2));

        for (int i=m; i < m+n; i ++) {
            nums1[i] = nums2[i-m];
        }

        for (int i=n+m; i>0; i --) {
            for (int j=0; j<i-1; j ++) {
                if (nums1[j] > nums1[j+1]) {
                    int temp=nums1[j+1];
                    nums1[j+1]=nums1[j];
                    nums1[j]=temp;
                }
            }
        }
        System.out.print("Result output: " + Arrays.toString(nums1));
        System.out.println();
    }
}
