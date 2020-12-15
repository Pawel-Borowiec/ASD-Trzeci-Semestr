package pakiet1;

import java.util.ArrayList;

public class Tile {
    ArrayList<String> PATH = new ArrayList<>();
    int x;
    int y;
    TileType type;
    double Gvalue;
    double Hvalue;
    double Fvalue;
    Tile parent;

    Tile(int x, int y,int k) {
        this.x=x;
        this.y=y;
        this.type = TileType.values()[k];
    }
    public String toString() {
        switch (type) {
            case NOT_ALLOWED:
                return ("0");
            case ALLOWED:
                return ("1");
            case STARTING_POINT:
                return ("S");
            case OPEN:
                return ("O");
            case ENDING_POINT:
                return ("K");
            case VISITED:
                return ("V");
                default:
                    return " ";
        }
    }
    // set neighbouring tiles open and choose best option to visit
    public void setNeighbours(Board board) {
        if (x != 0 && condition(board.Data[this.x - 1][this.y])) {
            check(board.Data[this.x - 1][this.y],board);
        }
        if (x != 9 &&condition(board.Data[this.x + 1][this.y])) {
           check(board.Data[this.x + 1][this.y],board);
        }
        if (y != 0 && condition(board.Data[this.x][this.y-1])) {
            check(board.Data[this.x ][this.y-1],board);
        }
        if (y != 9 && condition(board.Data[this.x ][this.y+1])) {
            check(board.Data[this.x][this.y+1],board);
        }
        if(board.Data[x][y].type!=TileType.STARTING_POINT)
        {
            board.Data[this.x][this.y].setStatus(TileType.VISITED);
        }
       board.openTiles.sort((Tile o1, Tile o2)-> (int) (o1.Fvalue-o2.Fvalue));
       board.openTiles.sort((Tile o1, Tile o2)-> (int) (o1.Hvalue-o2.Hvalue));
       if(board.openTiles.size()>=1) {
           board.openTiles.get(0).parent = board.Data[this.x][this.y];
           Tile temp = board.openTiles.get(0);
           if (temp.type==TileType.ENDING_POINT) {
               finalResult(temp);
           } else {
               if(board.openTiles.size()>=1)
               {
                   board.openTiles.remove(0);
                   temp.setNeighbours(board);
               }else {
                   try {
                       throw new Exception("Path can't be established");
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           }
       }else {
           try {
               throw new Exception("Path can't be established");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }
    public void check(Tile tile, Board board) {
        tile.setValues(board.STARTER, board.KONIEC);
        if( tile.type!=TileType.ENDING_POINT) {
            tile.setStatus(TileType.OPEN);
            board.openTiles.add(tile);
        }else if(tile.type==TileType.ENDING_POINT){
            board.openTiles.add(tile);
        }
    }
    public void finalResult(Tile tile) {
        showPath(tile);
        for(int i=0;i<PATH.size();i++)
        {
            System.out.print(PATH.get(PATH.size()-1-i)+",");
        }
    }
    public boolean condition( Tile tile)
    {
        return tile.type == TileType.ALLOWED || tile.type == TileType.ENDING_POINT;
    }
    public void showPath(Tile tile) {

        PATH.add(tile.getcoordinates());
        if(tile.parent!=null)
        {
            showPath(tile.parent);
        }
    }
    public String getcoordinates()
    {
        return "("+(this.y+1)+","+(this.x+1)+")";
    }
    public void setValues(Tile starter, Tile ending) {

        this.Gvalue=setValue(starter);
        this.Hvalue=setValue(ending);
        this.Fvalue=Gvalue+Hvalue;

    }

    public int setValue(Tile compared) {
        int a=Math.abs(this.x-compared.x);
        int b=Math.abs(this.y-compared.y);
        int Distance;
        if(b>a)
        {
         Distance=100*(b-a)+140*a;
        }else if(b<a)
        {
            Distance=100*(a-b)+140*b;
        }else {
            Distance=140*a;
        }
        return Distance;
    }
    public void setStatus(TileType newTileType){
        type = newTileType;
    }
}
