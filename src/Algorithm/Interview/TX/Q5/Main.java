package Algorithm.Interview.TX.Q5;

import Utils.Dump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 由于业绩优秀，公司给小Q放了 n 天的假，身为工作狂的小Q打算在在假期中工作、
 * 锻炼或者休息。他有个奇怪的习惯：不会连续两天工作或锻炼。
 * 只有当公司营业时，小Q才能去工作，只有当健身房营业时，小Q才能去健身，
 * 小Q一天只能干一件事。给出假期中公司，健身房的营业情况，求小Q最少需要休息几天。
 *
 * 输入描述:
 * 第一行一个整数  表示放假天数
 * 第二行 n 个数 每个数为0或1,第 i 个数表示公司在第 i 天是否营业
 * 第三行 n 个数 每个数为0或1,第 i 个数表示健身房在第 i 天是否营业
 * （1为营业 0为不营业）
 *
 * 输出描述:
 * 一个整数，表示小Q休息的最少天数
 *
 * 输入例子1:
 * 4
 * 1 1 0 0
 * 0 1 1 0
 *
 * 输出例子1:
 * 2
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("E:\\learnJava\\src\\Algorithm\\Algorithm.Interview\\TX\\Q5\\test.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

    }

}
