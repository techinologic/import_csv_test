import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class ParseCSVLineByLine {
    static String xStrPath;
    static int[][] myArray;
    static int[][] x_arr = new int[64][16];
    static int[][] y_arr = new int[64][16];
    static int tolerance = 350;

    static int startingY;
    static int startingX;


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
        xfileLocation = "C:\\Users\\Paopao\\Desktop\\import_csv_test\\aas.csv";

        System.out.println("Array Setup");

        try {
            //set up scanner
            scanner = new Scanner(new BufferedReader(new FileReader(xfileLocation)));

            while (scanner.hasNext()) {
                String[] InArray = scanner.nextLine().split(",");

                for (int i = 0; i < InArray.length; i++) {
                    myArray[rowc][i] = Integer.parseInt(InArray[i]);
                }
                rowc++;
                System.out.println();
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

        int counter = 0;
        int vote = 0;
        int circleCount = 0;

        while (x >= y) {

            if (myArray[(x0 + x)][(y0 + y)]>=tolerance){ //1
                vote+=1;
            }
            if (myArray[(x0 + y)][(y0 + x)]>=tolerance){ //2
                vote+=1;
            }
            if (myArray[(x0 - y)][(y0 + x)]>=tolerance){ //3
                vote+=1;
            }
            if (myArray[(x0 - x)][(y0 + y)]>=tolerance){ //4
                vote+=1;
            }
            if (myArray[(x0 - x)][(y0 - y)]>=tolerance){ //5
                vote+=1;
            }
            if (myArray[(x0 - y)][(y0 - x)]>=tolerance){ //6
                vote+=1;
            }
            if (myArray[(x0 + y)][(y0 - x)]>=tolerance){ //7
                vote+=1;
            }
            if (myArray[(x0 + x)][(y0 - y)]>=tolerance){ //8
                vote+=1;
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

            counter+=1;
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

    public static void traverseArrayDiagonally(int[][] ar) {
        for (int i = 0; i < ar.length; i++) {
            if (ar[i][i]>=350) {
                System.out.println(ar[i][i] + " " + i);
            }
        }
    }

    public static void printValues(int[][] ar) {
        int counter = 0;
        for (int i = 0; i < myArray.length; i++) {
            System.out.println();
            for (int j = 0; j < myArray.length; j++) {
                if (myArray[i][j]>=tolerance) {
                    if (detectCircle(129,129,93, tolerance)) {
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
        int largestNum = 0;
        int circleTopY = 0;
        int circleBottomY = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > tolerance) {
                    circleTopY = i;
                    break;
                }
            }
        }

        for (int i = array.length-1; i > 0; i--) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > tolerance) {
                    circleBottomY = i+1;
                    break;
                }
            }
        }

        System.out.println("first row of circle: " + circleTopY);
        System.out.println("last row of circle: " + circleBottomY);
        return (circleTopY-circleBottomY)/2;
    }

    public static int getCircleThickness(int[][] array){
        int r = 0;
        int largestNum = 0;
        int circleTopY = 0;
        int circleBottomY = 0;
        int thickness = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > tolerance) {
                    startingY = i;
                    startingX = j;
                    break;
                }
            }
        }
        for (int i = startingY; i < array.length; i++) {
            for (int j = startingX; j < array.length; j++) {
                if (array[j][startingX]>=tolerance) {
                    thickness+=1;
                }
            }
        }
        return thickness;
    }

    public static void main(String[] args) throws Exception {

        setUpMyCSVArray();
        //printValues(myArray);
        //traverseArrayDiagonally(myArray);

        //detectCircle(129,129,93, tolerance);

        System.out.println("Radius: " + getRadius(myArray));
        System.out.println("Circle thickness: " + getCircleThickness(myArray));





    }

}