import java.util.Stack;

public class Func {
    static class Point{
        int row;
        int column;

        Point(int row,int column){
            this.row = row;
            this.column = column;
        }
    }

    private static final Stack<Point> stack = new Stack<>();

    private static final boolean[][] OK = new boolean[9][9];

    public static void solve(int [][] a){
        init(a);

        int row = 1;
        int column = 1;

        label:while (row>=1 && row<=9 && column>=1 && column<=9){
            while(OK[row-1][column-1]){//找到没确定下来的
                int[] nextRowAndColumn = next(row,column);
                if(nextRowAndColumn[0]==10){
                    break label;
                }
                row = nextRowAndColumn[0];
                column = nextRowAndColumn[1];
            }
            do {
                a[row-1][column-1] ++;
            }while (!isValid(a,row,column));

            if(a[row-1][column-1] != 10){//这意味着目前暂时符合条件
                stack.push(new Point(row,column));
                int[] nextRowAndColumn = next(row,column);
                row = nextRowAndColumn[0];
                column = nextRowAndColumn[1];
            }else{//这意味着这个格子填啥都不行 只能回退
                a[row-1][column-1] = 0 ;
                Point p = stack.pop();
                row = p.row;
                column = p.column;
            }
        }

        show(a);
    }

    private static void init(int [][] a){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                OK[i][j] = a[i][j] != 0;
            }
        }
    }

    private static void show(int [][] a){
        //System.out.println(Arrays.deepToString(a));
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(a[i][j]+",");
            }
            System.out.println();
        }
    }

    private static int[] next(int row,int column){
        if(row>9 || row < 1){
            System.err.println("row over"+row);
        }
        if(column>9 || column < 1){
            System.err.println("column over"+column);
        }
        if(column!=9){
            return new int[]{row,column+1};
        }
        return new int[]{row+1,1};
    }

    /**
     * row column 处的数字是否合法
     */
    private static boolean isValid(int[][] a,int row,int column){
        int num = a[row-1][column-1];
        for(int i =0;i<9;i++){
            if(num == a[row-1][i] && i!=column-1){//同一行的其他有相同的
                return false;
            }
            if(num == a[i][column-1] && i!=row-1){//同一列的其他有相同的
                return false;
            }
        }
        //以下求宫 这个比较繁琐
        int rowStart = (row-1)/3*3;
        int columnStart = (column-1)/3*3;
        for(int i=rowStart;i<rowStart+3;i++){
            for(int j=columnStart;j<columnStart+3;j++){
                if(!(i==row-1 && j == column-1)){//不是自己
                    if(num == a[i][j]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
