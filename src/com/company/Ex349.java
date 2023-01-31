package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Ex349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        System.out.println("Intersection: " + Arrays.toString(nums1) + " with " + Arrays.toString(nums2));
        int size1=nums1.length;
        int size2=nums2.length;
        List<Integer> list = new ArrayList<Integer>();

        //Находим все пересечения и сохраняем в список
        for(int i=0; i <size1; i++) {
            for (int j=0; j<size2; j++) {
                if (nums1[i]==nums2[j]) {
                    list.add(nums1[i]);
                }
            }
        }

        //удаляем дубликаты из списка
        return list.stream()
                .distinct()
                .collect(Collectors.toList())
                .stream()
                .mapToInt(i->i)
                .toArray();
    }
}
