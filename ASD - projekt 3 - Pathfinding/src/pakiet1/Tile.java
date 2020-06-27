package pakiet1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tile {
    ArrayList<String> PATH = new ArrayList<>();
    int x;
    int y;
    TileType typ;
    double Gvalue;
    double Hvalue;
    double Fvalue;
    Tile[] Sasiedzi= new Tile[8];
    Tile parent;

    Tile(int x, int y,int k)
    {
        this.x=x;
        this.y=y;
        switch (k)
        {
            case 0:
                typ=TileType.NOTALLOWED;
                break;
            case 1:
                typ=TileType.ALLOWED;
                break;
            case 2:
                typ=TileType.START;
                break;
            case 3:
                typ=TileType.ALLOWED;
                break;
        }
    }
    public String toString()
    {
        switch (typ)
        {
            case NOTALLOWED:
                return ("0");
            case ALLOWED:
                return ("1");
            case START:
                return ("S");
            case OPEN:
                return ("O");
            case KONIEC:
                return ("K");
            case VISITED:
                return ("V");
                default:
                    return " ";
        }
    }
    public void setSasiedzi(Plansza plansza) {
        if (x != 0 && condition(plansza.Data[this.x - 1][this.y])) {
            check(plansza.Data[this.x - 1][this.y],plansza);
        }
        if (x != 9 &&condition(plansza.Data[this.x + 1][this.y])) {
           check(plansza.Data[this.x + 1][this.y],plansza);
        }
        if (y != 0 && condition(plansza.Data[this.x][this.y-1])) {
            check(plansza.Data[this.x ][this.y-1],plansza);
        }
        if (y != 9 && condition(plansza.Data[this.x ][this.y+1])) {
            check(plansza.Data[this.x][this.y+1],plansza);
        }
        if(plansza.Data[x][y].typ!=TileType.START)
        {
            plansza.Data[this.x][this.y].setStatus("V");
        }
        //plansza.show();
       // plansza.showValues();
        Collections.sort(plansza.openTiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                return (int) (o1.Fvalue - o2.Fvalue);
            }
        });
        Collections.sort(plansza.openTiles, new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                return (int)(o1.Hvalue-o2.Hvalue);
            }
        });
        System.out.println(plansza.openTiles.get(0).getcoordinates());
        /*
        for (int i = 0; i < plansza.openTiles.size(); i++) {
            System.out.println(plansza.openTiles.get(i).x + " " + plansza.openTiles.get(i).y+"  "+plansza.openTiles.get(i).Fvalue);
        }
        */
       if(plansza.openTiles.size()>=1)
       {
           plansza.openTiles.get(0).parent = plansza.Data[this.x][this.y];
           Tile temp = plansza.openTiles.get(0);

           if (temp.typ==TileType.KONIEC) {
               //System.out.println("Znaleziono");
               finalResult(temp,plansza);

           } else {
               if(plansza.openTiles.size()>=1)
               {
                   plansza.openTiles.remove(0);
                   temp.setSasiedzi(plansza);
               }else {
                   System.out.println("Nie da sie ustanowic sciezki1");
               }
           }
       }else {
           System.out.println("Nie da sie ustanowic sciezki2");
       }
        //System.out.println("KOniec iteracji");

    }
    public void check(Tile tile, Plansza plansza)
    {
        tile.setValues(plansza.STARTER, plansza.KONIEC);
        if( tile.typ!=TileType.KONIEC) {
            tile.setStatus("O");
            plansza.openTiles.add(tile);
        }else if(tile.typ==TileType.KONIEC){
            plansza.openTiles.add(tile);

        }


    }
    public void finalResult(Tile tile, Plansza plansza)
    {
        System.out.println();
        plansza.show();
        showPath(tile);
        System.out.println("START"+PATH.get(PATH.size()-1)+",KONIEC"+PATH.get(0));
        System.out.println();
        for(int i=0;i<PATH.size();i++)
        {
            System.out.print(PATH.get(PATH.size()-1-i)+",");
        }
        System.out.println();
    }
    public boolean condition( Tile tile)
    {
        return tile.typ == TileType.ALLOWED || tile.typ == TileType.KONIEC;
    }
    public void showPath(Tile tile)
    {

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
    public void setValues(Tile starter, Tile koniec)
    {

        this.Gvalue=setValue(starter);
        this.Hvalue=setValue(koniec);
        this.Fvalue=Gvalue+Hvalue;

    }

    public int setValue(Tile compared)
    {
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
    public void setStatus(String key)
    {
        switch (key)
        {
            case "0":
                typ=TileType.NOTALLOWED;
                break;
            case "1":
                typ=TileType.ALLOWED;
                break;
            case "S":
                typ=TileType.START;
                break;
            case "K":
                typ=TileType.KONIEC;
                break;
            case "O":
                typ=TileType.OPEN;
                break;
            case "V":
                typ=TileType.VISITED;
                break;
        }
    }
}
