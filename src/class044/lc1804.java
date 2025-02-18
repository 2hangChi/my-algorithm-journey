package class044;

public class lc1804 {

    class Trie {
        class TrieNode {
            int pass;
            int end;
            TrieNode[] next;

            public TrieNode() {
                pass = 0;
                end = 0;
                next = new TrieNode[26];
            }
        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root; // cur可以起名node，这样cur可以用作其它表示
            cur.pass++;
            for (int i = 0, n; i < word.length(); i++) { // n起名叫path更好
                n = word.charAt(i) - 'a';
                if (cur.next[n] == null) {
                    cur.next[n] = new TrieNode();
                }
                cur = cur.next[n];
                cur.pass++;
            }
            cur.end++;
        }

        public int countWordsEqualTo(String word) {
            TrieNode cur = root;
            for (int i = 0, n; i < word.length(); i++) {
                n = word.charAt(i) - 'a';
                if (cur.next[n] == null) {
                    return 0;
                }
                cur = cur.next[n];
            }
            return cur.end;
        }

        public int countWordsStartingWith(String prefix) {
            TrieNode cur = root;
            for (int i = 0, n; i < prefix.length(); i++) {
                n = prefix.charAt(i) - 'a';
                if (cur.next[n] == null) {
                    return 0;
                }
                cur = cur.next[n];
            }
            return cur.pass;
        }

        public void erase(String word) {
            if (countWordsEqualTo(word) > 0) {
                TrieNode cur = root;
                cur.pass--; // 一开始忘记写了
                for (int i = 0, n; i < word.length(); i++) {
                    n = word.charAt(i) - 'a';
                    if (--cur.next[n].pass == 0) {
                        cur.next[n] = null; // 忘记写了，这一行很重要
                        return;
                    }
                    cur = cur.next[n];
                }
                cur.end--; // 一开始忘记写了
                // 不是可有可无
            }
        }
    }

}
