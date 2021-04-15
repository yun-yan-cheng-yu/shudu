import java.awt.*;

public class Grid extends Panel {

    int num = 0;

    int [][] nums = {{1,2,3},{4,5,6},{7,8,9}};//当num确定下来后，该数据失效


    Grid(){
        this.setLayout(new GridLayout(3,3,5,5));
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button b = new Button();
                b.setLabel(nums[i][j]+"");
                this.add(b);
            }
        }
    }

    /**
     * 设置一个格子的数字，一旦设置成功，格子将无法操作
     * @param num 要设置的数字，位于1-9之间
     */
    void setAnswer(int num){
        if(num <= 0 || num > 9){
            System.err.println("Grid 设置数字异常:"+num);
            return;
        }
        this.removeAll();
        this.setLayout(new GridLayout(1,1,5,5));
        this.num = num;
        Button b = new Button();
        b.setLabel(num+"");
        b.setEnabled(false);

        this.add(b);
    }

    /**
     * 判断该格子数字是否确定下来
     * @return 确定则true
     */
    boolean isDecided(){
        return this.num > 0 && this.num < 10;
    }

    /**
     * 根据当前数字渲染当前的button
     */
    void render(){
        if(isDecided()){
            return ;
        }
        this.removeAll();
        this.setLayout(new GridLayout(3,3,5,5));
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button b = new Button();
                b.setLabel(nums[i][j]==0?"":nums[i][j]+"");
                this.add(b);
            }
        }
    }

    int canDecide(){
        int count = 0;
        int res = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(nums[i][j] != 0){
                    count++;
                    res = nums[i][j];
                }
            }
        }
        if(count==1){
            return res;
        }
        return 0;
    }

    /**
     * 决定该出数字
     */
    void decideNum(){
        if(isDecided()){
            return ;
        }
        if(canDecide() == 0){
            return ;
        }

        num = canDecide();

        this.removeAll();
        this.setLayout(new GridLayout(1,1,5,5));

        Button b = new Button();
        b.setLabel(num+"");
        b.setEnabled(false);

        this.add(b);
    }
}
