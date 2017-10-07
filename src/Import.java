import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class ParseCSVLineByLine {
    static String xStrPath;
    static int[][] myArray;
    static int[][] x_arr = new int[64][16];
    static int[][] y_arr = new int[64][16];
    static int tolerance = 400;

    static int startingY;
    static int startingX;

    static int endingY;
    static int endingX;


    static void setUpMyCSVArray() {
        myArray = new int[300][300];
        Scanner scanner;

        int rowc = 0;
        int row = 0;
        int colc = 0;
        int col = 0;
        String InputLine;
        double xnum = 0;
        String xfileLocation;
        xfileLocation = "C:\\Users\\Paopao\\IdeaProjects\\import_csv_test\\aas.csv";
        //xfileLocation = "/Users/Paolo/IdeaProjects/import_csv_test/aas.csv";

        System.out.println("Array loaded.");

        try {
            //set up scanner
            scanner = new Scanner(new BufferedReader(new FileReader(xfileLocation)));

            while (scanner.hasNext()) {
                String[] InArray = scanner.nextLine().split(",");

                for (int i = 0; i < InArray.length; i++) {
                    myArray[rowc][i] = Integer.parseInt(InArray[i]);
                }
                rowc++;
            }
        } catch (Exception e) {

            System.out.println("Error! " + e);
        }
    }


    public static boolean detectCircle(int x0, int y0, int radius, int tolerance) {
        int x = radius - 1;
        int y = 0;
        int dx = 1;
        int dy = 1;
        int err = dx - (radius << 1);

        int vote = 0;

        while (x >= y) {

            if (myArray[(x0 + x)][(y0 + y)] >= tolerance) { //1
                vote += 1;
            }
            if (myArray[(x0 + y)][(y0 + x)] >= tolerance) { //2
                vote += 1;
            }
            if (myArray[(x0 - y)][(y0 + x)] >= tolerance) { //3
                vote += 1;
            }
            if (myArray[(x0 - x)][(y0 + y)] >= tolerance) { //4
                vote += 1;
            }
            if (myArray[(x0 - x)][(y0 - y)] >= tolerance) { //5
                vote += 1;
            }
            if (myArray[(x0 - y)][(y0 - x)] >= tolerance) { //6
                vote += 1;
            }
            if (myArray[(x0 + y)][(y0 - x)] >= tolerance) { //7
                vote += 1;
            }
            if (myArray[(x0 + x)][(y0 - y)] >= tolerance) { //8
                vote += 1;
            }

            if (err <= 0) {
                y++;
                err += dy;
                dy += 2;
            }
            if (err > 0) {
                x--;
                dx += 2;
                err += (-radius << 1) + dx;
            }

            //counter += 1;
        }

//        if (vote>=300) {
//            circleCount+=1;
//        }
//        System.out.println();
//        System.out.println("Counter: "+counter);
//        System.out.println("Votes here: " + vote + "/" + counter*8);
//        System.out.println("Circle Count: "+circleCount);

        if (vote < 1) {
            return false;
        } else {
            return true;
        }

    }


    public static void printValues(int[][] ar) {
        int counter = 0;
        for (int i = 0; i < myArray.length; i++) {
            System.out.println();
            for (int j = 0; j < myArray.length; j++) {
                if (myArray[i][j] >= tolerance) {
                    if (detectCircle(129, 129, 93, tolerance)) {
                        System.out.print(myArray[i][j] + " ");
                    }
                    //detectCircle(129,129,93);
                } else {
                    System.out.print("    ");
                }
            }
        }
    }

    public static int getRadius(int[][] array) {
        int r = 0;
        int circleTopY = 0;
        int circleBottomY = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] >= tolerance) {
                    circleTopY = i;
                }
            }
        }

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] >= tolerance) {
                    circleBottomY = i + 1;
                    break;
                }
            }
        }

        System.out.println("first row of circle: " + circleTopY);
        System.out.println("last row of circle: " + circleBottomY);
        return (circleTopY - circleBottomY) / 2;
    }

    public static int getCircleThickness(int[][] array) {

        int start_x = startingX;
        int start_y = startingY;

        while (array[start_y][start_x] >= tolerance) {
            start_y += 1;
        }
        System.out.println("Ending X, Y: " + start_y + ", " + start_x);

        while (array[start_y][start_x] <= tolerance) {

        }
        return start_y - startingY;
    }


    public static int getTopY(int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] >= tolerance) {
                    startingY = i;
                    startingX = j;
                    System.out.println("Starting X, Y: " + startingY + ", " + startingX);
                    return -1;
                }
            }
        }
        return -1;
    }

    public static int getBottom(int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] >= tolerance) return i;

            }
        }
        return -1;
    }

    public static int searchCircle(int r, int maxRadius) {
        int count = 0;
        int radius = r;

        double xRad =  Math.toRadians(45);
        double yRad =  Math.toRadians(45);
        //while (radius != maxRadius) {

        int x45 = (int) (126 + (radius * Math.cos(45/(180/Math.PI))));
        int y45 = (int) (133 + (radius * Math.sin(45/(180/Math.PI))));

        double x135 = 126 + (radius * Math.cos(135/(180/Math.PI)));
        double y135 = 133 + (radius * Math.sin(135/(180/Math.PI)));

        double x225 = 126 + (radius * Math.cos(225/(180/Math.PI)));
        double y225 = 133 + (radius * Math.sin(225/(180/Math.PI)));

        double x315 = 126 + (radius * Math.cos(315/(180/Math.PI)));
        double y315 = 133 + (radius * Math.sin(315/(180/Math.PI)));


        for (int i = radius + 1; i < myArray.length - radius - 1; i++) {
            for (int j = radius + 1; j < myArray.length - radius - 1; j++) {
                if ((myArray[i + radius][j] > tolerance)
                        && (myArray[i + radius + 1][j] > tolerance)
                        && (myArray[i + radius - 1][j] > tolerance)

                        && (myArray[i - radius][j] > tolerance)
                        && (myArray[i - radius + 1][j] > tolerance)
                        && (myArray[i - radius - 1][j] > tolerance)

                        && (myArray[i][j + radius] > tolerance)
                        && (myArray[i][j + radius + 1] > tolerance)
                        && (myArray[i][j + radius - 1] > tolerance)


                        && (myArray[i][j - radius] > tolerance)
                        && (myArray[i][j - radius + 1] > tolerance)
                        && (myArray[i][j - radius - 1] > tolerance)
                        && (myArray[x45][y45] > tolerance)
                        && (myArray[(int) x135][(int) y135] > tolerance)
                        && (myArray[(int) x225][(int) y225] > tolerance)
                        && (myArray[(int) x315][(int) y315] > tolerance)) {



                    count += 1;
                    System.out.println((i) + " " + j);



//                    System.out.println("x45: " + x45 + " " + "y45: " + y45);
//                    System.out.println("x135: " + x135 + " " + "y135: " + y135);
//                    System.out.println("x225: " + x225 + " " + "y225: " + y225);
//                    System.out.println("x315: " + x315 + " " + "y315: " + y315);




                    //System.out.println("Here: " +  (radius * (Math.sin(xRad))) + " " +  (radius * (Math.cos(yRad))));


                }
            }
            //radius++;
            //}
        }
        System.out.println("radius: " + radius);
        return count;
    }


    public static void main(String[] args) throws Exception {

        setUpMyCSVArray();
        //printValues(myArray);
        //traverseArrayDiagonally(myArray);

        //detectCircle(129,129,93, tolerance);

        //System.out.println("Radius: " + getRadius(myArray));
        //System.out.println("Circle thickness: " + getCircleThickness(myArray));

        //testArray(myArray);

        getTopY(myArray);
        System.out.println("Circles found: " + searchCircle(91, 96));
        System.out.println("Test completed");

    }

}