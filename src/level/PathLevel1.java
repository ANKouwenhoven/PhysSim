package level;

/**
 * Created by Arnoud on 13-11-2016.
 */
public class PathLevel1 extends Path {

    public PathLevel1(int side) {
        if (side == 1) {
            setPath1();
        } else {
            setPath2();
        }
    }

    /**
     * Sets the first (left) path in the level.
     */
    private void setPath1() {
        setVerLine(0, 15, 2);
        setHorLine(2, 10, 15);
        setVerLine(8, 15, 10);
        setHorLine(10, 20, 8);
        setVerLine(8, 25, 20);
        setHorLine(20, 29, 25);
    }

    /**
     * Sets the second (right) path in the level.
     */
    private void setPath2() {
        setVerLine(0, 14, 3);
        setHorLine(3, 9, 14);
        setVerLine(7, 14, 9);
        setHorLine(9, 21, 7);
        setVerLine(7, 24, 21);
        setHorLine(21, 29, 24);
    }
}
