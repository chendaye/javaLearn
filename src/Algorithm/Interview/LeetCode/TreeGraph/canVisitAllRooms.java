package Algorithm.Interview.LeetCode.TreeGraph;

import Grammar.LanguageElement.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 *
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。
 * 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 *
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 *
 * 你可以自由地在房间之间来回走动。
 *
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 *
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keys-and-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class canVisitAllRooms {

    /**
     * todo: 思路：
     *      - 用 dfs/bfs 遍历图, 收集到达的每一个节点上的钥匙
     *      - 图遍历完之后，看是否拥有所有的钥匙
     * @param rooms
     * @return
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size(); // 房间数
        if(N == 1) return true;
        int[] visited = new int[N];
        int[] keys = new int[N];
        keys[0] = 1;
        dfs(rooms, visited, keys, 0);
        // 是否有没有被访问到的房间
        for (int i : keys){
            System.out.println(i);
            if (i == 0) return false;
        }
        return true;
    }

    // todo: 图的深度优先遍历（回溯）
    public static void dfs(List<List<Integer>> rooms, int[] visited, int[] keys, int room){
        visited[room] = 1;
        List<Integer> list = rooms.get(room);
        for (int i : list){
            if (visited[i] == 0){
                for (int k : list) // 收集当前房间的钥匙
                    keys[k] = 1;
                dfs(rooms, visited, keys, i);
            }
        }

    }

    public static void main(String[] args) {
//        int[][] rooms = {{1,3},{3,0,1},{2},{0}};
        ArrayList<List<Integer>> list = new ArrayList<>();

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list.add(list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(0);
        list2.add(1);
        list.add(list2);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list.add(list3);

        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(0);
        list.add(list4);
        System.out.println(canVisitAllRooms(list));
    }
}
