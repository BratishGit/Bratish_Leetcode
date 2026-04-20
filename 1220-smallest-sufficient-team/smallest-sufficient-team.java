public class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        int n = req_skills.length;
        int m = people.size();

        // Map skill → bit index
        Map<String, Integer> skillIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skillIndex.put(req_skills[i], i);
        }

        int totalStates = 1 << n;

        // dp[mask] = best team (list of people indices)
        List<Integer>[] dp = new List[totalStates];
        dp[0] = new ArrayList<>();

        for (int i = 0; i < m; i++) {

            // Convert person's skills to bitmask
            int personMask = 0;
            for (String skill : people.get(i)) {
                if (skillIndex.containsKey(skill)) {
                    personMask |= 1 << skillIndex.get(skill);
                }
            }

            // Try updating all existing states
            for (int prev = 0; prev < totalStates; prev++) {

                if (dp[prev] == null) continue;

                int newMask = prev | personMask;

                // If new state is better, update
                if (dp[newMask] == null || dp[newMask].size() > dp[prev].size() + 1) {
                    dp[newMask] = new ArrayList<>(dp[prev]);
                    dp[newMask].add(i);
                }
            }
        }

        // Final answer (all skills covered)
        List<Integer> ans = dp[totalStates - 1];
        return ans.stream().mapToInt(x -> x).toArray();
    }
}