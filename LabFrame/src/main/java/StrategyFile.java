import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StrategyFile implements Strategy {

    @Override
    public void load(File file) {
        Repository repository = Repository.getInstance();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean readingNodes = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("NODE_COORD_SECTION")) {
                    readingNodes = true;
                    continue;
                }
                if (readingNodes && line.trim().equals("EOF")) break;

                if (readingNodes) {
                    String[] parts = line.trim().split("\\s+");
                    int x = (int) Double.parseDouble(parts[1]);
                    int y = (int) Double.parseDouble(parts[2]);
                    repository.addCity(new Point(x, y));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findStatistics() {
        List<Point> hull = getConvexHull(Repository.getInstance().getCities());

        Point minX = null, maxX = null, minY = null, maxY = null;

        for (Point city : hull) {
            if (minX == null || city.getX() < minX.getX()) minX = city;
            if (maxX == null || city.getX() > maxX.getX()) maxX = city;
            if (minY == null || city.getY() < minY.getY()) minY = city;
            if (maxY == null || city.getY() > maxY.getY()) maxY = city;
        }

        List<Point> corners = new ArrayList<>();
        if (minX != null) corners.add(minX);
        if (maxX != null) corners.add(maxX);
        if (minY != null) corners.add(minY);
        if (maxY != null) corners.add(maxY);
        Repository.getInstance().setCorners(corners);

        int sumX = 0, sumY = 0;
        for (Point cities : corners) {
            sumX += cities.getX();
            sumY += cities.getY();
        }
        Repository.getInstance().setCenter(new Point(sumX/4, sumY/4));
    }

    private List<Point> getConvexHull(List<Point> points) {
        if (points.size() < 3) return points;

        List<Point> sorted = new ArrayList<>(points);
        sorted.sort((a, b) -> a.getX() == b.getX() ?
                Integer.compare((int) a.getY(), (int) b.getY()) :
                Integer.compare((int) a.getX(), (int) b.getX()));

        List<Point> lower = new ArrayList<>();
        for (Point p : sorted) {
            while (lower.size() >= 2 && cross(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }

        List<Point> upper = new ArrayList<>();
        for (int i = sorted.size() - 1; i >= 0; i--) {
            Point p = sorted.get(i);
            while (upper.size() >= 2 && cross(upper.get(upper.size() - 2), upper.get(upper.size() - 1), p) <= 0) {
                upper.remove(upper.size() - 1);
            }
            upper.add(p);
        }

        lower.remove(lower.size() - 1);
        upper.remove(upper.size() - 1);
        lower.addAll(upper);
        return lower;
    }

    private long cross(Point a, Point b, Point c) {
        return (long) ((long) (b.getX() - a.getX()) * (c.getY() - a.getY()) - (long) (b.getY() - a.getY()) * (c.getX() - a.getX()));
    }
}
