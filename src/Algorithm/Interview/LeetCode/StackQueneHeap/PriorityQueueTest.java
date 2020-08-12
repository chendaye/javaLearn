package Algorithm.Interview.LeetCode.StackQueneHeap;

import Utils.Dump;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * todo:优先队列底层是堆
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        Queue<String> q = new PriorityQueue<String>();
        // 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // null,因为队列为空


        Dump.dump("++++++++++++++++++++++++++如果元素没有实现Comparable接口；自定义比较器+++++++++++++++++++++++++");
        Queue<User> queue = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:
        queue.offer(new User("Bob", "A1"));
        queue.offer(new User("Alice", "A2"));
        queue.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
    }
}

class UserComparator implements Comparator<User> {
    public int compare(User u1, User u2) {
        if (u1.number.charAt(0) == u2.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小:
            return u1.number.compareTo(u2.number);
        }
        if (u1.number.charAt(0) == 'V') {
            // u1的号码是V开头,优先级高:
            return -1;
        } else {
            return 1;
        }
    }
}

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String toString() {
        return name + "/" + number;
    }
}
