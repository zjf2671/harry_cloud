package com.zjf;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int len = 0;
        for(int i=0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(checkUniq(s, i , j)){
                    len = Math.max(len, j - i);
                }
            }
        }
        return len;
        
    }

    private boolean checkUniq(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);

        }
        return true;
    }
}