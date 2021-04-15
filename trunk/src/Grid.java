import java.awt.*;

public class Grid extends Button {
    int num = 0;

    /**
     * 设置一个格子的数字，一旦设置成功，格子将无法操作
     * @param num 要设置的数字，位于1-9之间
     */
    void setNum(int num){
        if(num <= 0 || num > 9){
            System.err.println("Grid 设置数字异常:"+num);
            return;
        }
        this.num = num;
        this.setLabel(num+"");
        this.setEnabled(false);
    }
}
