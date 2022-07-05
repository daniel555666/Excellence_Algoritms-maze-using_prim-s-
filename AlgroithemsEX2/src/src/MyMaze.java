import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyMaze extends JFrame {
    public int M;
    public int N;
    public ArrayList<Point> NewEdges;
    public ArrayList<Point> EdgesSave;



    public MyMaze(int N, int M, ArrayList NewEdges,ArrayList EdgesSave) throws HeadlessException {
        this.N = N;
        this.M = M;
        this.NewEdges=NewEdges;
        this.EdgesSave=EdgesSave;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenSize.width, screenSize.height);
        this.add(new myPanel());
        this.setVisible(true);
    }

    public class myPanel extends JPanel {
        public myPanel() {


        }

        @Override
        protected void paintComponent(Graphics g) {
            Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
            double width=(sz.width*0.8)/N;
            double height=(sz.height*0.8)/M;
            int min=(int)Math.min(width,height);

            super.paintComponent(g);
            g.setColor(Color.black);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    g.drawRect((int)(10)+i*min,10+j*min,min,min);
                }
            }
            g.setColor(Color.yellow);
            g.drawLine((int)(10),10 ,(int)(10),10+min);
            g.drawLine((int)(10)+N*min,10+min*M-min,(int)(10)+N*min,10+min*M);

            while(EdgesSave.size()!=0) {
                boolean b = true;
                Point p = EdgesSave.remove(0);
                if (!NewEdges.contains(p)) {
                    if (p.x != p.y - 1) b = false;
                    Point p1 = new Point((int) p.x % N, p.x / N);
                    Point p2 = new Point((int) p.y % N, p.y / N);

                    g.setColor(new Color(239, 235, 235));
                    if (b)
                        g.drawLine((int) (10) + (p2.x * min), (10) + (p1.y * min) + min, (int) (10) + (p2.x * min), 10 + (p2.y * min));
                    else
                        g.drawLine((int) (10) + (p1.x * min) + min, (10) + (p2.y * min), (int) (10) + (p2.x * min), 10 + (p2.y * min));


                    int a = 5;

                }
            }
        }


    }
    public static void main(String[] args) {
        ArrayList<Point> NewEdges = new ArrayList<Point>();
        NewEdges.add(new Point(1, 6));
        new MyMaze(5,5, NewEdges,NewEdges);
    }
}
