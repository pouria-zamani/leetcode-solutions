package main.java.com.pouria.leetcode.string;

public class P005LongestPalindromicSubstring {

    // Using manacher's algorithm
    // Time complexity: O(n)
    // Space complexity: O(n)
    public String longestPalindrome(String s) {
        StringBuilder sPrime = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            sPrime.append(c).append("#");
        }

        int n = sPrime.length();
        int[] palindromeRadii = new int[n];
        int center = 0;
        int radius = 0;

        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;

            if (i < radius)
                palindromeRadii[i] = Math.min(radius - i, palindromeRadii[mirror]);

            while (i + 1 + palindromeRadii[i] < n &&
                    i - 1 - palindromeRadii[i] >= 0 &&
                    sPrime.charAt(i + 1 + palindromeRadii[i]) ==
                            sPrime.charAt(i - 1 - palindromeRadii[i]))
                palindromeRadii[i]++;

            if (i + palindromeRadii[i] > radius) {
                center = i;
                radius = i + palindromeRadii[i];
            }
        }

        int maxLength = 0;
        int centerIndex = 0;
        for (int i = 0; i < n; i++)
            if (palindromeRadii[i] > maxLength) {
                maxLength = palindromeRadii[i];
                centerIndex = i;
            }

        int startIndex = (centerIndex - maxLength) / 2;

        return s.substring(startIndex, startIndex + maxLength);
    }


    // Using center expansion technique
    // Time complexity: O(n^2)
    // Space complexity: O(1)
    public String longestPalindromeUsingCenterExpansion(String s) {
        int[] ans = new int[]{0, 0};

        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(i, i, s);
            if (oddLength > ans[1] - ans[0] + 1) {
                int dist = oddLength / 2;
                ans[0] = i - dist;
                ans[1] = i + dist;
            }

            int evenLength = expand(i, i + 1, s);
            if (evenLength > ans[1] - ans[0] + 1) {
                int dist = (evenLength / 2) - 1;
                ans[0] = i - dist;
                ans[1] = i + 1 + dist;
            }
        }

        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }

    private int expand(int i, int j, String s) {
        int left = i;
        int right = j;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    // Using dynamic programming
    // Time complexity: O(n^2)
    // Space complexity: O(n^2)
    public String longestPalindromeUsingDynamicProgramming(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] ans = new int[]{0, 0};

        for (int i = 0; i < n; i++)
            dp[i][i] = true;

        for (int i = 0; i < n - 1; i++)
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (ans[1] - ans[0] < 1) {
                    ans[0] = i;
                    ans[1] = i + 1;
                }
            }

        for (int diff = 2; diff < n; diff++)
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (ans[1] - ans[0] < j - i + 1) {
                        ans[0] = i;
                        ans[1] = j;
                    }
                }
            }

        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }

    // Using brute force and checking all substrings
    // Time complexity: O(n^3)
    // Space complexity: O(1)
    public String longestPalindromeUsingBruteForce(String s) {
        for (int length = s.length(); length > 0; length--)
            for (int start = 0; start <= s.length() - length; start++)
                if (check(start, start + length, s))
                    return s.substring(start, start + length);
        return "";
    }

    private boolean check(int i, int j, String s) {
        int left = i;
        int right = j - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;

            left++;
            right--;
        }
        return true;
    }
}
