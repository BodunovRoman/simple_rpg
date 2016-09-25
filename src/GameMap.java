/**
 * Created by BodunovRA on 25.09.2016.
 */
public class GameMap {

    private final int nsx = 21;
    private final int nsy = 7;
    private String[][] map = new String[nsy][nsx];
    private int[][] dangMap = new int[nsy][nsx];
    private char[][] obstMap = new char[nsy][nsx];


    public GameMap()
    {
        obstMap[5][8] = 'S';
        obstMap[4][9] = 'X';
    }

    public char getObstValue(int x, int y)
    {
        return obstMap[y][x];
    }

    public int getDangerous(int _x, int _y)
    {
        return dangMap[_y][_x];
    }

    public boolean isCellEmpty (int _x, int _y)
    {
        if ( _x < 0 || _y < 0 || _x > nsx-1 || _y>nsy-1) return false;
        if(obstMap[_y][_x] == 'X') return false;
        return true;
    }

    public void updateMap( int _hx, int _hy)
    {
        for(int i=0;i<nsy;i++)
            for(int j=0; j<nsx;j++){
                map[i][j] = "*";
                if(obstMap[i][j] == 'S') map[i][j] = "S";
                if(obstMap[i][j] == 'X') map[i][j] = "X";
            }

        map[_hy][_hx] = "H";

    }

    public void showMap()
    {
        for(int i=0;i<nsy;i++){
            for(int j=0;j<nsx;j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void buildDangeMap(int _cx, int _cy)
    {
        for(int i=0;i<nsy;i++){
            for(int j=0;j<nsx;j++){
                int dng = (int)Math.sqrt(Math.pow(_cy - i, 2) + Math.pow(_cx - j, 2));
                dangMap[i][j] = dng;
            }
        }
    }
}
