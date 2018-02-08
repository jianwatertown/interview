package bfs;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 给一个二维网格，每一个格子都有一个值，2 代表墙，1 代表僵尸，0 代表人类(数字 0, 1, 2)。
// 僵尸每天可以将上下左右最接近的人类感染成僵尸，但不能穿墙。
// 将所有人类感染为僵尸需要多久，如果不能感染所有人则返回 -1。
public class ZombieInMatrix {

    public int PEOPLE = 0;
    public int ZOMBIE = 1;
    public int WALL = 2;

    public int[] deltaX = {1, 0, 0, -1};
    public int[] deltaY = {0, 1, -1, 0};


    public int zombie(int[][] grid) {

        int day = 0;
        Set<Coordinate> human = new HashSet<>();

        // 1. init count human and first set of Zombies
        Queue<Coordinate> q = new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==PEOPLE) {human.add(new Coordinate(i,j));}
                else if(grid[i][j]==ZOMBIE) {
                    Coordinate oneZombie = new Coordinate(i,j);
                    q.add(oneZombie);
                }
            }
        }

        // 2. BFS
        while(!q.isEmpty()){
            int size = q.size();
            day++;
            for(int i=0;i<size;i++){
                Coordinate oneZombie = q.poll();
                for(int x1: deltaX){
                    for(int y1: deltaY){
                        int newX = oneZombie.x+x1;
                        int newY = oneZombie.y+y1;
                        Coordinate humanToZombie = new Coordinate(newX,newY);
                        if(isPeople(humanToZombie,grid)){
                            if(human.contains(humanToZombie)){
                                human.remove(humanToZombie);
                                q.add(humanToZombie);
                            }
                        }
                    }
                }
            }
        }
        return human.size()==0?day:-1;
    }

    private boolean isPeople(Coordinate coor, int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (coor.x < 0 || coor.x >= n) {
            return false;
        }
        if (coor.y < 0 || coor.y >= m) {
            return false;
        }
        return (grid[coor.x][coor.y] == PEOPLE);
    }

    public static class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
