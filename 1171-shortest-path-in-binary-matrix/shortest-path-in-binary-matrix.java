class Solution {
    private int bfs(int[][] grid,boolean[][] vis,int R,int C,int row,int col,int steps,Queue<int[]> que){
        int[] rdiff = {-1, 0, 1, 0, -1, -1, 1,  1};
        int[] cdiff = { 0, 1, 0, -1, -1,  1, -1, 1};
        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0;i<size;i++){
                int[] val = que.poll();
                row = val[0];
                col = val[1];
                if (row == R - 1 && col == C - 1) return steps;
                for (int ind = 0; ind < 8; ind++){
                    int ar = row + rdiff[ind];
                    int ac = col + cdiff[ind];
                    if (ar >= 0 && ar < R && ac >= 0 && ac < C && grid[ar][ac] == 0 &&!vis[ar][ac]) {
                        vis[ar][ac] = true;
                        que.offer(new int[]{ar,ac});
                    }
            }
        }
        steps++;
    }
    return -1;
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        boolean [][] vis = new boolean[R][C];
        Queue<int[]> que = new LinkedList<>();
        if (grid[0][0] == 1 || grid[R-1][C-1] == 1) return -1;
        if (R == 1 && C == 1 && grid[0][0] == 0) return 1;
        que.offer(new int[]{0,0});
        vis[0][0] = true;
        int steps = 1;
        return bfs(grid,vis,R,C,0,0,steps,que);
    }
}