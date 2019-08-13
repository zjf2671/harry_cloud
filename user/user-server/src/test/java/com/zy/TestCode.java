package com.zy;

import java.util.Arrays;

/**
 * 例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @Author Harry
 * @Date 2019/6/24 17:24
 **/
public class TestCode {

    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
//        int[] ls = twoSum(nums, target);
//        System.out.println(Arrays.toString(ls));
        TestCode testCode = new TestCode();
        ListNode l1 = testCode.new ListNode(2);
        l1.next = testCode.new ListNode(4);
        l1.next.next = testCode.new ListNode(3);

        ListNode l2 = testCode.new ListNode(5);
        l2.next = testCode.new ListNode(6);
        l2.next.next = testCode.new ListNode(4);
        addTwoNumbers(l1,l2);
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        TestCode testCode = new TestCode();
        ListNode dummyHead = testCode.new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = testCode.new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = testCode.new ListNode(carry);
        }
        return dummyHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
