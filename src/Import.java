import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class ParseCSVLineByLine {
    static String xStrPath;
    static int[][] myArray;

    static int tolerance = 400;

    static void setUpMyCSVArray() {
        myArray = new int[300][300];
        Scanner scanner;

        int rowc = 0;
        String xfileLocation;
        xfileLocation = "C:\\Users\\Paopao\\IdeaProjects\\import_csv_test\\aas.csv";
        //xfileLocation = "/Users/Paolo/IdeaProjects/import_csv_test/25mm.csv";

        System.out.println("Array loaded.");
        System.out.println("Length: " + myArray.length);

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


    public static int searchCircle(int r, int maxRadius) {
        int count = 0;
        int radius = r;
        int thickness = 2;

        int counter = 0;



        //while (radius != maxRadius) {


        for (int i = radius + 2; i < myArray.length - radius - 1; i++) { //x
            for (int j = radius + 2; j < myArray[myArray.length-1].length - radius - 1; j++) { //y

                int x45 = (int) Math.round(i + (radius * Math.cos(45/(180/Math.PI))));
                int y45 = (int) Math.round(j + (radius * Math.sin(45/(180/Math.PI))));

                int x120 = (int) Math.round(i + (radius * Math.cos(120/(180/Math.PI))));
                int y120 = (int) Math.round(j + (radius * Math.sin(120/(180/Math.PI))));

                int x135 = (int) Math.round(i + (radius * Math.cos(135/(180/Math.PI))));
                int y135 = (int) Math.round(j + (radius * Math.sin(135/(180/Math.PI))));

                int x150 = (int) Math.round(i + (radius * Math.cos(150/(180/Math.PI))));
                int y150 = (int) Math.round(j + (radius * Math.sin(150/(180/Math.PI))));

                int x225 = (int) Math.round(i + (radius * Math.cos(225/(180/Math.PI))));
                int y225 = (int) Math.round(j + (radius * Math.sin(225/(180/Math.PI))));

                int x315 = (int) Math.round(i + (radius * Math.cos(315/(180/Math.PI))));
                int y315 = (int) Math.round(j + (radius * Math.sin(315/(180/Math.PI))));


                counter+=1;

                if ((myArray[i + radius][j] > tolerance)
                        && (myArray[i + radius + thickness][j] > tolerance) // south
                        && (myArray[i + radius - thickness][j] > tolerance)

                        && (myArray[i - radius][j] > tolerance)
                        && (myArray[i - radius + thickness][j] > tolerance) //north
                        && (myArray[i - radius - thickness][j] > tolerance)

                        && (myArray[i][j + radius] > tolerance)
                        && (myArray[i][j + radius + thickness] > tolerance) // east
                        && (myArray[i][j + radius - thickness] > tolerance)

                        && (myArray[i][j - radius] > tolerance)
                        && (myArray[i][j - radius + thickness] > tolerance) // west
                        && (myArray[i][j - radius - thickness] > tolerance)

                        && (myArray[x45][y45] > tolerance)
//                        && (myArray[x120][y120] > tolerance)
                        && (myArray[x135][y135] > tolerance)
//                        && (myArray[x150][y150] > tolerance)
                        && (myArray[x225][y225] > tolerance)
                        && (myArray[x315][y315] > tolerance))
                { // if statement open

                    count+=1;
                    System.out.println("Center: " + i + " " +j);


                } // if close


//                    System.out.println("x45: " + x45 + " " + "y45: " + y45);
//                    System.out.println("x135: " + x135 + " " + "y135: " + y135);
//                    System.out.println("x225: " + x225 + " " + "y225: " + y225);
//                    System.out.println("x315: " + x315 + " " + "y315: " + y315);
            }
        }

        System.out.println("radius: " + radius);
        System.out.println("points searched: " + counter);
        return count;
    }



    public static void main(String[] args) throws Exception {

        setUpMyCSVArray();

        System.out.println("\nCircles found: " + searchCircle(91, 96));
        System.out.println("\nSearch completed");

        int testRadius = 9;
        int xTest = 256;
        int yTest = 216;

        double x45 = Math.round(xTest + (testRadius * Math.cos(45/(180/Math.PI))));
        double y45 = Math.round(yTest + (testRadius * Math.sin(45/(180/Math.PI))));

        double x120 = (xTest + (testRadius * Math.cos(120/(180/Math.PI))));
        double y120 = (yTest + (testRadius * Math.sin(120/(180/Math.PI))));
        double x150 = (xTest + (testRadius * Math.cos(150/(180/Math.PI))));
        double y150 = (yTest + (testRadius * Math.sin(150/(180/Math.PI))));

        double x135 = Math.round(xTest + (testRadius * Math.cos(135/(180/Math.PI))));
        double y135 = Math.round(yTest + (testRadius * Math.sin(135/(180/Math.PI))));

        double x225 = Math.round(xTest + (testRadius * Math.cos(225/(180/Math.PI))));
        double y225 = Math.round(yTest + (testRadius * Math.sin(225/(180/Math.PI))));

        double x315 = Math.round(xTest + (testRadius * Math.cos(315/(180/Math.PI))));
        double y315 = Math.round(yTest + (testRadius * Math.sin(315/(180/Math.PI))));


        System.out.println(x45 + " " + y45);
        //System.out.println(x120 + " " + y120);
        //System.out.println(x150 + " " + y150);
        System.out.println(x135 + " " + y135);
        System.out.println(x225 + " " + y225);
        System.out.println(x315 + " " + y315);


    }

}