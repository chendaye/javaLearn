package Algorithm.Interview.NowCoder;

/**
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
 * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
 * 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,
 * 继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * 如果没有小朋友，请返回-1
 */
public class LastRemaining_Solution {
    public int LastRemaining(int n, int m) {
        if (n == 0) return -1;
        int[] record = new int[n];
        int out = 0; // 出列人数
        int k = 0; // 游戏从0开始
        while (out < n - 1){ // out = n-1 结束
            int cnt = 0; // 本轮报数的人数
            while (cnt < m){ // 一轮报数
                if (record[k] != -1){
                    cnt++; // 报数一次
                    if (cnt == m) record[k] = -1; // -1 代表出列
                }
                k = (k + 1) % n; //todo: 下一个
            }
            out++; // 0 ~ m-1 报完出列
        }
        for (int i = 0; i < n; i++)
           if (record[i] == 0) return i;
        return -1;
    }


    /**
     * todo:用环形链表来模拟
     */
    class ListNode {

        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public int LastRemaining2(int n, int m) {

        if (n <= 0 || m <= 0) {
            return -1;
        }

        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < n; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        node.next = head;

        int k = 0;
        while (node.next != node) { //todo:环形链表
            if (++k == m) {
                node.next = node.next.next; // 出队
                k = 0;
            } else {
                node = node.next;
            }
        }

        return node.val;
    }

    public static void main(String[] args) {
        System.out.println(new LastRemaining_Solution().LastRemaining(5, 3));
    }
}
