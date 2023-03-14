//Daniel Boianju 315113159
package GeomatryPrimitives;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Daniel boianju.
 */
public class Rectangle {
    private Point upperLeftP;
    private double w;
    private double h;
    /**
     * this is a constructor function for the rectangle.
     * @param upperLeft - the point of the upper left corner of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftP = upperLeft;
        this.w = width;
        this.h = height;
    }
    /**
     * this is another constructor function for the rectangle.
     * @param x - the x value of the upper left point.
     * @param y -the y valud o the upper left point.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        Point p1 = new Point(x, y);
        this.upperLeftP = p1;
        this.w = width;
        this.h = height;
    }

    /**
     * this is the set function to set a new upper left point to a rectangle.
     * @param p1 - the new point.
     */
    public void setUpperLeftP(Point p1) {
        this.upperLeftP = p1;
    }
    /**
     * this function returns a list of intersection points with the line.
     * @param line - the line to check the intersections of.
     * @return - java.util.List -  list (possible empty) of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List intersectionPoints = new ArrayList<Point>();
        List linesToCheck = new ArrayList<Line>();
        linesToCheck.add(this.getLeftLine());
        linesToCheck.add(this.getUpperLine());
        linesToCheck.add(this.getRightLine());
        linesToCheck.add(this.getLowerLine());
        for (int i = 0; i < linesToCheck.size(); i++) {
            Point p1 = line.intersectionWith((Line) linesToCheck.get(i));
            if (p1 != null && ((Line) linesToCheck.get(i)).isPointOnPhysicalLine(p1, 1)) {
                intersectionPoints.add(p1);
            }
        }
        //if the list isn't empty, check for duplicates in the list.
        if (intersectionPoints.size() != 0) {
            List intersectionPointsNoDups = new ArrayList<Point>();
            for (int i = 0; i < intersectionPoints.size(); i++) {
                boolean flag = false;
                for (int j = 0; j < intersectionPointsNoDups.size(); j++) {
                    if (((Point) intersectionPointsNoDups.get(j)).equals((Point) intersectionPoints.get(i))) {
                        flag = true;
                    }
                }
                if (!flag) {
                    intersectionPointsNoDups.add(intersectionPoints.get(i));
                }
            }
            return intersectionPointsNoDups;
        }
        return intersectionPoints;
    }

    /**
     * the getter function for the rectangle width.
     * @return - double - the width of the rectangle.
     */
    public double getWidth() {
        return this.w;
    }

    /**
     * the getter function for the rectangle height.
     * @return - double - the height of the rectangle.
     */
    public double getHeight() {
        return this.h;
    }

    /**
     * the getter function for the rectangle upper left Point.
     * @return - Point - the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeftP;
    }

    /**
     * this function returns the Line of the upper side of the rectangle.
     * @return - Line - the upper line of the rectangle.
     */
    public Line getUpperLine() {
        Line l1 = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        return l1;
    }
    /**
     * this function returns the Line of the lower side of the rectangle.
     * @return - Line - the lower line of the rectangle.
     */
    public Line getLowerLine() {
        Line l1 = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
        return l1;
    }
    /**
     * this function returns the Line of the right side of the rectangle.
     * @return - Line - the right line of the rectangle.
     */
    public Line getRightLine() {
        Line l1 = new Line(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY(), this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        return l1;
    }
    /**
     * this function returns the Line of the left side of the rectangle.
     * @return - Line - the left line of the rectangle.
     */
    public Line getLeftLine() {
        Line l1 = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        return l1;
    }
}
