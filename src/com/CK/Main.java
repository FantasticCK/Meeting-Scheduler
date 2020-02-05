package com.CK;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

// PQ
class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[0] - i2[0]);
        for (int[] s : slots1)
            if (s[1] - s[0] >= duration)
                pq.offer(s);
        for (int[] s : slots2)
            if (s[1] - s[0] >= duration)
                pq.offer(s);
        while (pq.size() > 1)
            if (pq.poll()[1] >= pq.peek()[0] + duration)
                return Arrays.asList(pq.peek()[0], pq.peek()[0] + duration);
        return Arrays.asList();
    }
}

// Two Pointers
class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        if (slots1.length == 0 || slots2.length == 0)
            return res;
        Arrays.sort(slots1, (s1, s2) -> s1[0] - s2[0]);
        Arrays.sort(slots2, (s1, s2) -> s1[0] - s2[0]);
        int s1 = 0, s2 = 0;
        int[] temp = new int[]{-1, -1};
        while (s1 < slots1.length && s2 < slots2.length) {
            if (slots1[s1][0] <= slots2[s2][0]) {
                if (foundDuration(temp, slots1, s1, duration, res))
                    return res;
                temp = slots1[s1];
                s1++;
            } else {
                if (foundDuration(temp, slots2, s2, duration, res))
                    return res;
                temp = slots2[s2];
                s2++;
            }
        }

        while (s1 < slots1.length) {
            if (foundDuration(temp, slots1, s1, duration, res))
                return res;
            temp = slots1[s1];
            s1++;
        }

        while (s2 < slots2.length) {
            if (foundDuration(temp, slots2, s2, duration, res))
                return res;
            temp = slots2[s2];
            s2++;
        }
        return res;
    }

    private boolean foundDuration(int[] temp, int[][] slots, int itr, int duration, List<Integer> res){
        if (Math.min(temp[1], slots[itr][1]) - Math.max(temp[0], slots[itr][0]) >= duration) {
            int resSt = slots[itr][0], resEd = slots[itr][0] + duration;
            res.add(resSt);
            res.add(resEd);
            return true;
        }
        return false;
    }
}