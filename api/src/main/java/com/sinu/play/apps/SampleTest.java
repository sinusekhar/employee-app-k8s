package com.sinu.play.apps;


import java.util.*;


public class SampleTest {

    public static void main(String[] args) {

        try {

            //List<Integer>  nums = Arrays.asList(new Integer[]{3,2,1,2,7});
            List<Integer> nums = Arrays.asList(new Integer[]{5, 4, 1, 4, 4, 10, 5, 5, 2, 8, 8});


            boolean complete = false;

            Map<Integer, List<Integer>> dups = new HashMap<>();

            while (!complete) {


                //Find duplicates and put the results in a map <value, list of indexes>

                for (int i = 0; i < nums.size(); i++) {

                    if (dups.get(nums.get(i)) == null) {

                        List<Integer> indexes = new ArrayList<>();

                        indexes.add(i);

                        dups.put(nums.get(i), indexes);

                    } else {

                        List<Integer> indexesTemp = dups.get(nums.get(i));

                        indexesTemp.add(i);

                        dups.put(nums.get(i), indexesTemp);

                    }

                }


                //Parse map to find the lowest duplicate

                //value with list of size more than one has dups

                Set<Integer> keys = dups.keySet();

                int leastNum = 0;

                for (Integer key : keys) {

                    List<Integer> indexesTemp = dups.get(key);

                    if (indexesTemp.size() > 1) {

                        if ((leastNum != 0 & leastNum > key) || leastNum == 0)

                            leastNum = key;

                    }

                }


                //Increment the lowest dup, or exit if no dup

                if (leastNum == 0) {

                    complete = true;

                    int sum = 0;

                    for (Integer num : nums)

                        sum = sum + num;


                    System.out.println(sum);

                } else {

                    //Increment the least dup (first occurance)

                    for (int i = 0; i < nums.size(); i++) {

                        if (nums.get(i) == leastNum) {

                            nums.set(i, nums.get(i) + 1);

                            break;

                        }

                    }

                }


                dups = new HashMap<>();

                System.out.println("iter=" + nums);
            }
            System.out.println(nums);
        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
