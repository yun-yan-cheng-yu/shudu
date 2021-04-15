import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShuDu {
    Frame frame = new Frame();//一个框架
    Grid[][] grids = new Grid[9][9];//9*9的格子

    ShuDu(int[][] array){
        frame.setLayout(new GridLayout(9,9,10,10));
        frame.setLocation(600,300);

        for (int i = 0;i < 9; i++){
            for(int j = 0; j < 9;j++){
                Grid g = new Grid();
                g.setAnswer(array[i][j]);

                grids[i][j] = g;
                frame.add(grids[i][j]);
            }
        }
        MenuBar m = new MenuBar();
        Menu operation = new Menu("operation");
        MenuItem throwNumThatImpossible = new MenuItem("throwNumThatImpossible");
        MenuItem decideNum = new MenuItem("decideNum");

        frame.setMenuBar(m);
        m.add(operation);
        operation.add(throwNumThatImpossible);
        operation.add(decideNum);

        frame.pack();
        frame.setBackground(Color.PINK);

        frame.setVisible(true);
        //事件
        throwNumThatImpossible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("throwNumThatImpossible");
                throwNumThatImpossible();
                renderAll();
            }
        });

        decideNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decideNum();
            }
        });
    }

    /**
     * 去除那些不可能的数字
     * 根据其他相关格去除掉每个格子的待选数字
     */
    public void throwNumThatImpossible(){
        for (int i = 0;i < 9; i++){
            for(int j = 0; j < 9;j++){
                if(grids[i][j].isDecided()){//只有该格子数字确定下来才能去除其他格子数据
                    int num = grids[i][j].num;
                    for(int temp = 0; temp < 9; temp++){
                        grids[i][temp].nums[(num-1)/3][(num-1)%3] = 0;//去除一行
                        grids[temp][j].nums[(num-1)/3][(num-1)%3] = 0;//去除一列
                    }
                    //去除宫比较复杂
                    int start_i = (i/3) * 3;
                    int start_j = (j/3) * 3;
                    for (int m = 0; m < 3; m++){
                        for(int n = 0; n < 3;n++){
                            grids[start_i + m][start_j+n].nums[(num-1)/3][(num-1)%3] = 0;//去除一宫
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据数字渲染图形
     */
    public void renderAll(){
        frame.setVisible(false);
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9;j++){
                grids[i][j].render();
            }
        }
        frame.setVisible(true);
    }

    public void decideNum(){
        frame.setVisible(false);
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9;j++){
                grids[i][j].decideNum();
            }
        }
        frame.setVisible(true);
    }

}
