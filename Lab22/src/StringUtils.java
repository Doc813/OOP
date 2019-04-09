public class StringUtils {
    public static boolean compare(String first, String second) {
        int right = 0;
        boolean ans = false;
        for (int i = 0; i < first.length(); ++i) {
            if (right == second.length()) {
                ans = true;
                break;
            }
            if (second.charAt(right) == first.charAt(i)) {
                right++;

            } else {
                right = 0;
                if (second.charAt(right) == first.charAt(i)) {
                    right++;
                }
            }
        }
        if (right == second.length())
            ans = true;
        return ans;
    }
}