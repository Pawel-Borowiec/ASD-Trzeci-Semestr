package pakiet1;

import java.util.Scanner;
public class Algorithm {
    public int phase=0;
    public Board board;
    static int x;
    static int y;
    Algorithm()
    {
        board =new Board();
    }

    public void startAlgorithm()
    {
        board.show();
    }
    public void startPathFinding() {
        setS(board);
        System.out.println();
        setK(board);
    }
    public void setS( Board board)
    {
        System.out.println("Wprowadz wspolrzedne punktu startowego rozdzielone przecinkiem");
        enterValue(x,y);
        System.out.println("DX: "+x+" "+y);
        setStarter(x,y);

    }
    public void setS( Board board, int x, int y)
    {
        board.Data[y][x].setStatus(TileType.STARTING_POINT);
        board.STARTER=board.Data[y][x];
    }
    public void setK( Board board)
    {
        enterValue(x,y);
        if(board.Data[y][x].type==TileType.ALLOWED)
        {
            board.Data[y][x].setStatus(TileType.ENDING_POINT);
            board.KONIEC=board.Data[y][x];
            board.show();
            System.out.println();
            board.KONIEC.setValue(board.STARTER);
            board.STARTER.setValues(board.STARTER,board.KONIEC);
            board.STARTER.setNeighbours(board);

        }else
        {
            try {
                throw new Exception("This element can't be ending point");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setK( Board board, int x, int y)
    {
        board.Data[y][x].setStatus(TileType.ENDING_POINT);
        board.KONIEC=board.Data[y][x];
        board.KONIEC.setValue(board.STARTER);
        board.STARTER.setValues(board.STARTER,board.KONIEC);
        board.STARTER.setNeighbours(board);
    }
    public void enterValue(int x, int y)
    {
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
        String splitted[] = response.split(",");
        this.x=Integer.parseInt(splitted[0])-1;
        this.y=Integer.parseInt(splitted[1])-1;
    }
    public void setStarter(int x, int y)
    {
        if(board.Data[y][x].type==TileType.ALLOWED)
        {
            board.Data[y][x].setStatus(TileType.STARTING_POINT);
            board.STARTER=board.Data[y][x];
        }else {
            try {
                throw new Exception("This point cat't be starting point");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setS(board);
        }
    }
}
