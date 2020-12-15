package pakiet1;

import java.util.ArrayList;
import java.util.List;

class Board {
    List<Tile> openTiles= new ArrayList<>();
    Tile Data[][]=new Tile[10][10];
    Tile STARTER;
    Tile KONIEC;

    Board() {
         for(int i=0;i<10;i++) {
             for(int j=0;j<10;j++) {
                 Data[i][j]=new Tile(i,j,(int)(Math.random()*2));
             }
         }
     }
}
