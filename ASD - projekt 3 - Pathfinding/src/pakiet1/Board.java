package pakiet1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Board {
    List<Tile> openTiles= new ArrayList<>();
 File file = new File("data.txt");
 Tile Data[][]=new Tile[10][10];
 Tile STARTER;
 Tile KONIEC;
 /*
 Scanner skaner = new Scanner(file);
 Plansza() throws FileNotFoundException {
     for(int i=0;i<10;i++)
     {
         for(int j=0;j<10;j++)
         {
             Data[i][j]=new Tile(i,j,skaner.nextInt());
         }
     }
 }*/
    Board() {
     for(int i=0;i<10;i++)
     {
         for(int j=0;j<10;j++)
         {
             Data[i][j]=new Tile(i,j,(int)(Math.random()*2));
         }
     }
 }
    public void show()
 {
     for(int i=0;i<10;i++)
     {
         for(int j=0;j<10;j++)
         {
             System.out.print(Data[i][j]+" ");
         }
         System.out.println();
     }
 }
 public void start(Tile tile)
 {
     int x=tile.x;
     int y=tile.y;
     if(x!=0 && Data[x-1][y].type==TileType.ALLOWED)
     {
         Data[x-1][y].type=TileType.OPEN;
         Data[x-1][y].parent=tile;
         openTiles.add(Data[x-1][y]);
     }
     if(x!=9 && Data[x+1][y].type==TileType.ALLOWED)
     {
         Data[x+1][y].type=TileType.OPEN;
         Data[x+1][y].parent=tile;
         openTiles.add(Data[x+1][y]);
     }
     if(y!=0 && Data[x+1][y].type==TileType.ALLOWED)
     {
         Data[x][y-1].type=TileType.OPEN;
         Data[x][y-1].parent=tile;
         openTiles.add(Data[x][y-1]);
     }
     if(y!=9 && Data[x][y+1].type==TileType.ALLOWED)
     {
         Data[x][y+1].type=TileType.OPEN;
         Data[x][y+1].parent=tile;
         openTiles.add(Data[x][y+1]);
     }

 }
    public void showValues()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                System.out.print("\t\t"+Data[i][j].Fvalue);
            }
            System.out.println();
        }
    }

 public void setPath()
 {
     for(int i=0;i<10;i++)
     {
         for(int j=0;j<10;j++)
         {
             if(Data[i][j].type==TileType.ENDING_POINT)
             {
                 this.KONIEC=Data[i][j];
             }
             if(Data[i][j].type==TileType.STARTING_POINT)
             {
                 this.STARTER=Data[i][j];
             }
         }
         System.out.println();
     }
 }
}
