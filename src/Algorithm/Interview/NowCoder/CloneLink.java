package Algorithm.Interview.NowCoder;

import java.util.HashMap;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 * todo: 关键 用 HashMap 记录新旧 对象 的映射关系。 活用 HashMap
 */
public class CloneLink {
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    //todo: HashMap
    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null) return null;
        RandomListNode target = new RandomListNode(pHead.label); //头节点
        RandomListNode point = target;
        RandomListNode raw = pHead;

        //todo: 用map保存新旧节点的 映射关系
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        while (pHead != null){
            //todo: 旧节点 => 新节点
            map.put(pHead, new RandomListNode(pHead.label));
            pHead = pHead.next;
        }
        //todo: raw => target
        while (raw != null){
            target.next = map.get(raw.next);
            target.random = map.get(raw.random);
            raw = raw.next;
            target = target.next;
        }
        return point;
    }

    public static void main(String[] args) {

    }
}
