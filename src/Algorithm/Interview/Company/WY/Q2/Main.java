package Algorithm.Interview.Company.WY.Q2;

import Grammar.LanguageElement.Array;
import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/Algorithm/Interview/Company/WY/Q2/test.txt"));
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        int cnt = 0;
        ArrayList<char[]> list = new ArrayList<>();
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            char[] chars = s.replace(" ", "").toCharArray();
            list.add(chars);
        }

        char[][] record = new  char[list.size()][list.get(0).length];
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.get(0).length; j++){
                record[i][j] = list.get(i)[j];
            }
        }

        System.out.println(exist(record, word));
    }

    public static boolean exist(char[][] board, String word) {
        int[][] dire = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        int m = board.length; // m 行 y
        int n = board[0].length; // n 列 x
        int[][] visit = new int[m][n];

        for (int y=0; y<board.length; y++){
            for (int x=0; x<board[y].length; x++){
                if (searchWord(board, word,  dire, m, n, visit, 0, x, y))
                    return true;
            }
        }
        return false;
    }


    public static boolean searchWord(char[][] board, String word,  int[][] dire, int m, int n, int[][] visit, int index, int start_x, int start_y){
        if (index == word.length() -1)
            return board[start_y][start_x] == word.charAt(index);
        if (board[start_y][start_x] == word.charAt(index)){
            visit[start_y][start_x] = 1; //todo: 标记访问
            for (int i=0;i<3;i++){
                int new_x = start_x + dire[i][0];
                int new_y = start_y + dire[i][1];
                if (new_x>=0 && new_x<n && new_y>=0 && new_y<m && visit[new_y][new_x] == 0){
                    if (searchWord(board, word, dire,  m, n, visit, index+1, new_x, new_y))
                        return true;
                }
            }
            visit[start_y][start_x] = 0;
        }
        return false;
    }
}
