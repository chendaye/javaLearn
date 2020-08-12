package Algorithm.Interview.NowCoder;

/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 */
public class multiply {
    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        for (int i = 0; i < B.length; i++){
            int cur = 1;
            for (int j = 0; j < A.length; j++){
                if (i == j) continue;
                cur *= A[j];
            }
            B[i] = cur;
        }
        return B;
    }

    /**
     * 先计算上三角的值 再计算下三角的值
     *todo: 从两边分别计算 先乘 B[i] 左边 再乘 B[i] 右边
     * @param A
     * @return
     */
    public int[] multiply2(int[] A) {
        int length=A.length;
        int[] B=new int[length];
        //边界
        if(A==null||A.length<=1){
            return null;
        }
        //计算下三角
        //初始化第一行
        B[0]=1;
        for(int i=1;i<length;i++){
            B[i]=B[i-1]*A[i-1];
        }
        //计算上三角
        //初始化最后一行
        int temp=1;
        for(int i=length-1;i>=0;i--){
            B[i]=temp*B[i];
            temp=A[i]*temp;
        }
        return B;
    }
}
