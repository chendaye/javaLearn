package Algorithm.Interview.LeetCode.Trie;

/**
 * todo: 字典树
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * Trie 是一颗非典型的多叉树模型，多叉好理解，即每个结点的分支数量可能为多个。
 *
 * 为什么说非典型呢？因为它和一般的多叉树不一样，尤其在结点的数据结构设计上，比如一般的多叉树的结点是这样的
 * struct TreeNode {
 *     VALUETYPE value;    //结点值
 *     TreeNode* children[NUM];    //指向孩子结点
 * };
 *而 Trie 的结点是这样的(假设只包含'a'~'z'中的字符)：
 * struct TrieNode {
 *     bool isEnd; //该结点是否是一个串的结束
 *     TrieNode* next[26]; //字母映射表
 * };
 *字母映射表next 的妙用就体现了，TrieNode* next[26]中保存了对当前结点而言下一个可能出现的所有字符的链接，
 * 因此我们可以通过一个父结点来预知它所有子结点的值
 *for (int i = 0; i < 26; i++) {
 *     char ch = 'a' + i;
 *     if (parentNode->next[i] == NULL) {
 *         说明父结点的后一个字母不可为 ch
 *     } else {
 *         说明父结点的后一个字母可以是 ch
 *     }
 * }
 *Trie 中一般都含有大量的空链接，因此在绘制一棵单词查找树时一般会忽略空链接，（26个位置上并不是每一个都有）
 * 作者：huwt
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/trie-tree-de-shi-xian-gua-he-chu-xue-zhe-by-huwt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Trie {
    private boolean isEnd=false; // 该结点是否是一个串的结束
    private Trie next[]=new Trie[26]; // 字母映射表

    public Trie(){}

    /**
     * todo: 插入字典树
     *         - Trie节点中 并不保存，节点的值
     *         - 节点的值由它 在父节点的 next[] 字母表中的位置决定
     *         - char - 'a' = char 在 next[] 中的位置
     * @param word
     */
    public void insert(String word){//插入单词
        Trie root = this; // 指向自己
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++){
            //todo： 如果当前节点 字母表里面，chars[i] 位置为空，新建一个节点;chars[i]- 'a' 是 当前字符在next[] 中的位置
            if (root.next[chars[i] - 'a'] == null) root.next[chars[i]- 'a'] = new Trie();
            root = root.next[chars[i] - 'a'];
        }
        root.isEnd = true; // 插入完，标记字符串结尾
    }

    public boolean search(String word){//查找单词
        Trie root = this;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if (root.next[chars[i] - 'a'] == null) return false;
            root = root.next[chars[i] - 'a'];
        }
        return root.isEnd; // 最总的字符是结尾字符，就找到了单词
    }

    // todo: 和找单词类似
    public boolean startsWith(String prefix){//查找前缀
        Trie root=this;
        char p[]=prefix.toCharArray();
        for(int i=0;i<p.length;++i){
            if(root.next[p[i]-'a']==null)return false;
            root=root.next[p[i]-'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
