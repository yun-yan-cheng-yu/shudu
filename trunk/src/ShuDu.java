import java.awt.*;

public class ShuDu {
    Frame frame = new Frame();//一个框架
    Grid[][] grids = new Grid[9][9];//9*9的格子

    ShuDu(int[][] array){
        frame.setLayout(new GridLayout(9,9));
        frame.setVisible(true);
        frame.setLocation(600,300);

        for (int i = 0;i < 9; i++){
            for(int j = 0; j < 9;j++){
                Grid g = new Grid();
                g.setNum(array[i][j]);

                grids[i][j] = g;
                frame.add(grids[i][j]);
            }
        }
        frame.pack();
    }


}
