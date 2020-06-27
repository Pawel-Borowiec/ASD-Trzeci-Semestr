package pakiet1;

import java.util.Scanner;
public class Algorithm {
    public int phase=0;
    public Plansza plansza;
    static int x;
    static int y;
    Algorithm()
    {
        plansza =new Plansza();
    }

    public void startAlgorithm()
    {
        plansza.show();
    }
    public void startPathFinding()
    {
        setS(plansza);
        System.out.println();
        setK(plansza);
    }
    public void setS( Plansza plansza)
    {
        System.out.println("Wprowadz wspolrzedne punktu startowego rozdzielone przecinkiem");
        enterValue(x,y);
        System.out.println("DX: "+x+" "+y);
        setStarter(x,y);

    }
    public void setS( Plansza plansza, int x, int y)
    {
        plansza.Data[y][x].setStatus("S");
        plansza.STARTER=plansza.Data[y][x];
    }
    public void setK( Plansza plansza)
    {
        System.out.println("Wprowadz wspolrzedne punktu koncowego rozdzielone przecinkiem");
        enterValue(x,y);
        System.out.println("XD: "+x+" "+y);
        if(plansza.Data[y][x].typ==TileType.ALLOWED)
        {
            plansza.Data[y][x].setStatus("K");
            plansza.KONIEC=plansza.Data[y][x];
            plansza.show();
            System.out.println();
            plansza.KONIEC.setValue(plansza.STARTER);
            plansza.STARTER.setValues(plansza.STARTER,plansza.KONIEC);
            plansza.STARTER.setSasiedzi(plansza);

        }else
        {
            System.out.println("Ten punkt nie moze byc koncem");
        }
    }
    public void setK( Plansza plansza, int x, int y)
    {
        plansza.Data[y][x].setStatus("K");
        plansza.KONIEC=plansza.Data[y][x];
        plansza.KONIEC.setValue(plansza.STARTER);
        plansza.STARTER.setValues(plansza.STARTER,plansza.KONIEC);
        plansza.STARTER.setSasiedzi(plansza);
    }
    public void enterValue(int x, int y)
    {
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
        String splitted[] = response.split(",");
        this.x=Integer.parseInt(splitted[0])-1;
        this.y=Integer.parseInt(splitted[1])-1;
        System.out.println("Wspolrzedne : "+x+" "+y);
        System.out.println(plansza.Data[y][x].typ);
    }
    public void setStarter(int x, int y)
    {
        if(plansza.Data[y][x].typ==TileType.ALLOWED)
        {
            plansza.Data[y][x].setStatus("S");
            plansza.STARTER=plansza.Data[y][x];
        }else {
            System.out.println("Ten punkt nie moze byc startem");
            setS(plansza);
        }
    }
}
