package GeomatryPrimitives;
//Daniel Boianju 315113159
import java.util.ArrayList;
import java.util.List;
/**
 * author: Daniel Boianju.
 */
public class Line {
    public static final double EPSILON = 0.00000000001;
    private Point start;
    private Point end;
    /**
     * first constructor function. constructs a line of two Points.
     * @param start - the starting point of a line.
     * @param end - the ending point of a line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * second constructor function. constructs a line from two Points - given their x and y values.
     * @param x1 - the x value of the starting point.
     * @param y1 - the y value of the starting point.
     * @param x2 - the x value of the ending point.
     * @param y2 - the y value of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * this function returns the closest intersection point to a rectangle.
     * @param rect - the rectangle.
     * @return Point - the closest intersection point to the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List l1 = new ArrayList<Point>();
        l1 = rect.intersectionPoints(this);
        //if the list is empty, return null.
        if (l1.size() == 0) {
            return null;
        }
        double[] distancesFromStart = new double[l1.size()];
        int j = 0;
        for (Object i : l1) {
            distancesFromStart[j] = this.start.distance((Point) i);
            j++;
        }
        int minIndex = 0;
        for (int i = 0; i < distancesFromStart.length; i++) {
            if (distancesFromStart[minIndex] > distancesFromStart[i]) {
                minIndex = i;
            }
        }
        return (Point) l1.get(minIndex);
    }
    /**
     * this function returns the length of a line.
     * @return double - the length.
     */
    public double length() {
        double distance = this.start.distance(this.end);
        return distance;
    }
    /**
     * this function returns the Point in the middle of the line.
     * @return Point - the middle of the line.
     */
    public Point middle() {
        Point middle = new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
        return middle;
    }
    /**
     * this is the Getter function for the line's starting point.
     * @return point - the start of the line.
     */
    public Point start() {
        return this.start;
    }
    /**
     * this is the Getter function for the line's ending point.
     * @return point - the end of the line.
     */
    public Point end() {
        return this.end;
    }
    /**
     * this function checks weather the point's Y value is between the lines start and end Y values.
     * @param p1 - the point to check.
     * @return boolean - true if in the domain, false otherwise.
     */
    public boolean pointYOnDomain(Point p1) {
        if ((p1.getY() >= this.start.getY() && p1.getY() <= this.end.getY())
            || (p1.getY() >= this.end.getY() && p1.getY() <= this.start.getY())) {
            return true;
        } else if (Math.abs(this.start().getY() - p1.getY()) <= Line.EPSILON
                || Math.abs(p1.getY() - this.end().getY()) <= Line.EPSILON) {
            return true;
        }
        return false;
    }
    /**
     * this function checks weather the point's Y value is between the lines start and end Y values,
     * not including the edges of the domain.
     * @param p1 - the point to check.
     * @return boolean - true if in the domain, false otherwise.
     */
    public boolean pointYOnDomainNotEquals(Point p1) {
        if ((p1.getY() > this.start.getY() && p1.getY() < this.end.getY())
                || (p1.getY() > this.end.getY() && p1.getY() < this.start.getY())) {
            return true;
        }
        return false;
    }
    /**
     * this functon checks weather the point's X value is between the lines start and end X values.
     * @param p1 - the point to check.
     * @return boolean - true if in the domain, false otherwise.
     */
    public boolean pointXOnDomain(Point p1) {
        if ((p1.getX() >= this.start.getX() && p1.getX() <= this.end.getX())
                || (p1.getX() >= this.end.getX() && p1.getX() <= this.start.getX())) {
            return true;
        } else if (Math.abs(this.start().getX() - p1.getX()) <= Line.EPSILON
            || Math.abs(p1.getX() - this.end().getX()) <= Line.EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * this function checks weather the point is on the given line's edges.
     * @param p1 - the point to check.
     * @return boolean - true if on edges, false otherwise.
     */
    public boolean pointOnEdges(Point p1) {
        if (p1.equals(this.start()) || p1.equals(this.end())) {
            return true;
        }
        return false;
    }

    /**
     * this functon checks weather the point's X value is between the lines start and end X values.
     * without the edges
     * @param p1 - the point to check.
     * @return boolean - true if in the domain, false otherwise.
     */
    public boolean pointXOnDomainNotEquals(Point p1) {
        if ((p1.getX() > this.start.getX() && p1.getX() < this.end.getX())
                || (p1.getX() > this.end.getX() && p1.getX() < this.start.getX())) {
            return true;
        }
        return false;
    }

    /**
     * this function computes the line equation and returns the Y value based on the X input.
     * @param x - the X value.
     * @return double - the Y value.
     */
    public double funcCalc(double x) {
        //compute the line parameters (y=mx+n).
        double m = this.computeSlope();
        double n = -m * this.start.getX() + this.start.getY();
        //return the y value.
        return m * x + n;
    }
    /**
     * this function returns the intersection Point value of two lines, regardless of their domain.
     * it assumes that they intersect only once.
     * @param other - the other intersecting line.
     * @return the intersection Point.
     */
    public Point intersectionPoint(Line other) {
        //return the point if both the lines have slopes.
        if (this.doesHaveSlope() && other.doesHaveSlope()) {
            //computing the parameters of both the lines (y=mx+n)
            double m1 = this.computeSlope(), m2 = other.computeSlope();
            double n1 = -m1 * this.start.getX() + this.start.getY();
            double n2 = -m2 * other.start.getX() + other.start.getY();
            //the m1-m2 statement is correctly calculated because the function assumes the lines are not parallel.
            double x = (n2 - n1) / (m1 - m2);
            Point p1 = new Point(x, this.funcCalc(x));
            return p1;
        } else if (this.doesHaveSlope() && !other.doesHaveSlope()) {
            Point p1 = new Point(other.start.getX(), this.funcCalc(other.start.getX()));
            return p1;
        } else if (!this.doesHaveSlope() && other.doesHaveSlope()) {
            Point p1 = new Point(this.start.getX(), other.funcCalc(this.start.getX()));
            return p1;
        } else {
            return null;
        }
    }

    /**
     * this function gets a line that is parallel to this line and checks weather they share
     * a certain domain on the same height - not including the edges of the domain.
     * @param other - the other parallel line.
     * @return - boolean - yes if the parallel lines share the same domain on the same height, false othereise.
     */
    public boolean parallelAndShareDomainAndHeightNotEquals(Line other) {
        //check if the parallel lines are on the same height
        if (!this.isPointOnLine(other.start)) {
            return false;
        } else if (this.equals(other)) {
            return true;
        } else {
            if (this.doesHaveSlope()) {
                /*
                if they are parallel and are on the same relative height, check if they share some
                domain. if so, return true. return false otherwise.
                */
                boolean con1 = this.pointXOnDomainNotEquals(other.start) || this.pointXOnDomainNotEquals(other.end);
                boolean con2 = other.pointXOnDomainNotEquals(this.start) || other.pointXOnDomainNotEquals(this.end);
                return con1 || con2;
            } else {
                boolean con3 = this.pointYOnDomainNotEquals(other.start) || this.pointYOnDomainNotEquals(other.end);
                boolean con4 = other.pointYOnDomainNotEquals(this.start) || other.pointYOnDomainNotEquals(this.end);
                return con3 || con4;
            }

        }
    }
    /**
     * this function gets a line that is parallel to this line and checks weather they share
     * a certain domain on the same height.
     * @param other - the other parallel line.
     * @return - boolean - yes if the parallel lines share the same domain on the same height, false othereise.
     */
    public boolean parallelAndShareDomainAndHeight(Line other) {
        //check if the parallel lines are on the same height
        if (!this.isPointOnLine(other.start)) {
            return false;
        } else {
            /*
            if they are parallel and are on the same relative height, check if they share some
            domain. if so, return true. return false otherwise.
             */
            boolean con1 = this.pointXOnDomain(other.start) || this.pointXOnDomain(other.end);
            boolean con2 = other.pointXOnDomain(this.start) || other.pointXOnDomain(this.end);
            return con1 || con2;
        }
    }
    /**
     * this function checks weather two lines intersect.
     * @param other - the other line of which we want to check if intersects.
     * @return boolean - true of intersects, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //firstly, check if the lines are equal
        if (this.equals(other)) {
            return true;
        }
        //next, check if one or more of the lines don't have slopes
        if (!this.doesHaveSlope() && !other.doesHaveSlope()) {
            if (this.start.getX() == other.start.getX()) {
                //if the lines Y values are on the same Y domain, return true.
                if (this.pointYOnDomain(other.start) || this.pointYOnDomain(other.end)) {
                    return true;
                }
            }
            return false;
        } else if (!this.doesHaveSlope() || !other.doesHaveSlope()) {
            //else if one of the lines still does not have a slope, check if intersection exists.
            if (this.pointXOnDomain(other.start) || other.pointXOnDomain(this.start)) {
                //the X's are on the same domain. check if the Yws are on the same domain.
                if (this.pointYOnDomain(other.start) || this.pointYOnDomain(other.end)
                    || other.pointYOnDomain(this.start) || other.pointYOnDomain(this.end)) {
                    return true;
                }
            }
            return false;
        }
        //if reached here, the lines have slopes, now check if are parallel.
        if (this.isParallel(other)) {
            //if they are parallel, check if they are on the same height
            if (this.parallelAndShareDomainAndHeight(other)) {
                return true;
            }
            return false;
        }
        //if reached here, the lines are not parallel and both have slopes.
        Point p1 = this.intersectionPoint(other);
        if (this.pointXOnDomain(p1) && other.pointXOnDomain(p1)) {
            return true;
        }
        return false;
    }
    /**
     * this function checks weather the point is on the given line's equation.
     * @param p1 - the point to check.
     * @return boolean - true if on the line, false otherwise.
     */
    public boolean isPointOnLine(Point p1) {
        //first, check if the line doesn't have a slope.
        if (!this.doesHaveSlope()) {
            if (p1.getX() == this.start().getX()) {
                return true;
            }
            if (Math.abs(p1.getX() - this.start().getX()) <= Line.EPSILON) {
                return true;
            }
            return false;
        }
        /*
        compute a new slope out of the point and the current line's starting point.
        if the slope is equal to the previous slope, point is on the line (or on the continuation of it).
         */
        Line l1 = new Line(this.start, p1);
        Line l2 = new Line(this.end, p1);
        double slope1 = l1.computeSlope(), slope2 = l2.computeSlope();
        if (this.computeSlope() == slope1) {
            return true;
        } else if (Math.abs(this.computeSlope() - slope1) <= Line.EPSILON) {
            return true;
        } else if (this.computeSlope() == slope2) {
            return true;
        } else if (Math.abs(this.computeSlope() - slope2) <= Line.EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * check if a point is on the real line within its start an end point.
     * @param p1 - the point to check weather is on the line.
     * @param type - an indicator - assumed to be 1 or 0 - to indicate weather
     *             the user wants to check including the edges or otherwise.
     *             1 including edges, 0 otherwise.
     * @return - boolean - true if is on the line, false otherwise.
     */
    public boolean isPointOnPhysicalLine(Point p1, int type) {
        //first, check if a point is on the given line's equation.
        if (!this.isPointOnLine(p1)) {
            return false;
        }
        /*
        now that the point is on the line, check the type that the use specified.
        if type == 1, check if the point is on the line including the edges.
         */
        if (type == 1) {
            if (this.pointXOnDomain(p1)) {
                if (this.pointYOnDomain(p1)) {
                    return true;
                }
            }
            return false;
        }
        //otherwise, check without the edges.
        if (type == 0) {
            if (this.pointXOnDomainNotEquals(p1)) {
                if (this.pointYOnDomainNotEquals(p1)) {
                    return true;
                }
            }
            return false;
        }
        //if reached here, the input for the "type" is incorrect. return false.
        return false;
    }
    /**
     * this function checks weather the line's slope exists.
     * @return boolean - true if exists, false otherwise.
     */
    public boolean doesHaveSlope() {
        if ((this.end.getX() - this.start.getX()) == 0) {
            return false;
        }
        return true;
    }
    /**
     * this function computes the slope of the current line.
     * @return double - the slope.
     */
    public double computeSlope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }
    /**
     * this function checks weather the current line is parallel to the other line.
     * @param other - the other line of which we want to check is parallel.
     * @return boolean - true of parallel, false otherwise.
     */
    public boolean isParallel(Line other) {
        if (this.computeSlope() == other.computeSlope()) {
            return true;
        } else if (Math.abs(this.computeSlope() - other.computeSlope()) <= Line.EPSILON) {
            return true;
        }
        return false;
    }
    /**
     * this function returns the intersection point of the two lines. if they don't intersect - returns null.
     * @param other - the other line to check the intersection point.
     * @return Point - if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        //if the lines aren't intersecting, return null.
        if (!this.isIntersecting(other)) {
            return null;
        }
        //if the lines are equal, return null
        if (this.equals(other)) {
            return null;
        }
        /*
        if they are intersecting but are parallel, they have infinite intersection points. return null.
        but first, check if they share only a single point
         */
        if (this.isParallel(other)) {
            //check if the lines share more than a single point.
            if (this.parallelAndShareDomainAndHeightNotEquals(other)) {
                return null;
            } else if (this.end.equals(other.start)) {
                return this.end;
            } else if (this.start.equals(other.end)) {
                return this.start;
            } else if (this.end.equals(other.end)) {
                return this.end;
            } else if (this.start.equals(other.start)) {
                return this.start;
            }
        }
        //check if two of the lines don't have slopes
        if (!this.doesHaveSlope() && !other.doesHaveSlope()) {
            if (this.pointYOnDomainNotEquals(other.start) || this.pointYOnDomainNotEquals(other.end)
            || other.pointYOnDomainNotEquals(this.start) || other.pointYOnDomainNotEquals(this.end)) {
                return null;
            } else if (this.end.equals(other.start)) {
                return this.end;
            } else if (this.start.equals(other.end)) {
                return this.start;
            } else if (this.end.equals(other.end)) {
                return this.end;
            } else if (this.start.equals(other.start)) {
                return this.start;
            }
        }
        /*
        in this case, they are intersecting and are not parallel, and also they have slopes.
        so they have exactly one intersection point.
         */
        return this.intersectionPoint(other);
    }
    /**
     * this function checks weather the lines are equal.
     * @param other - the line to check if is equal.
     * @return boolean - true if equal, false otherwise.
     */
    public boolean equals(Line other) {
        //check if the lines starting and ending points are equal or opposites. if so, return true. otherwise, false.
        if ((this.start.equals(other.start) && this.end.equals(other.end)
                || (this.start.equals(other.end) && this.end.equals(other.start)))) {
            return true;
        }
        return false;
    }
}
