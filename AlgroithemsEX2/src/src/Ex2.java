import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ex2 {


    public static ArrayList<Point> mazeAlgo(Point[][] S, int M, int N, ArrayList<Point> E) {
        ArrayList<Point> NewEdges = new ArrayList<Point>();


        int counter = 0;
        while (E.size() > 0) {
            int index = (int) (Math.random() * E.size());
            Point p1 = new Point((E.get(index).y / M), (E.get(index).y % M));
            Point p2 = new Point((E.get(index).x / M), (E.get(index).x % M));
            if (find(S, p1).x != find(S, p2).x || find(S, p1).y != find(S, p2).y) {      //if in the same group add the edge between them
                union(S, p1, p2);
                counter++;
                E.remove(index);
            } else NewEdges.add(E.remove(index));
        }
        return NewEdges;
    }

    public static void union(Point[][] S, Point index1, Point index2) {

        Point a = find(S, index1);
//        while(S[index1.x][index1.y].x!=-1){
//            Point temp=S[index1.x][index1.y];
//            S[index1.x][index1.y]=a;
//            index1=temp;
//        }

        Point b = find(S, index2);
//        while(S[index2.x][index2.y].x!=-1){
//            Point temp=S[index2.x][index2.y];
//            S[index2.x][index2.y]=a;
//            index2=temp;
//        }
        S[a.x][a.y] = new Point(b.x, b.y);

    }

    public static Point find(Point[][] S, Point index) {
        if (S[index.x][index.y].y != -1) {
            return find(S, S[index.x][index.y]);
        } else {
            return index;
        }
    }


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter N");
        int N =scanner.nextInt();
        System.out.println("Enter M");
        int M =scanner.nextInt();
        Point [][]S = new Point[N][M];

        for (int i=0;i<N;i++)
            for (int j=0;j<M;j++)
                S[i][j]=new Point(-1,-1);

        ArrayList <Point> Edges=new ArrayList<Point>();
        ArrayList <Point> EdgesSave=new ArrayList<Point>();
        for (int i=0;i<N;i++){                                              //made all the possible edges
            for (int j=0;j<M;j++) {
                if (i>=0&&i<N&&j+1>=0&&j+1<M) {
                    Edges.add(new Point((i * M) + j, (i * M) + j + 1));
                    EdgesSave.add(new Point((i * M) + j, (i * M) + j + 1));
                }
                if (i+1>=0&&i+1<N&&j>=0&&j<M) {
                    Edges.add(new Point((i * M) + j, ((i + 1) * M) + j));
                    EdgesSave.add(new Point((i * M) + j, ((i + 1) * M) + j));

                }
            }
        }
        ArrayList<Point> NewEdges=mazeAlgo(S,M,N,Edges);
        MyMaze m=new MyMaze(M,N,NewEdges,EdgesSave);
        int n;
    }

}
