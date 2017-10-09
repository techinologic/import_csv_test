package com.findcircles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


class ParseCSVLineByLine {
    static int[][] myArray;

    static int tolerance = 400;
    static int thickness = 3;

    static void setUpMyCSVArray() {
        myArray = new int[2160][2560];
        Scanner scanner;

        int rowc = 0;
        String xfileLocation;
        //xfileLocation = "C:\\Users\\Paopao\\IdeaProjects\\import_csv_test\\src\\com\\findcircles\\aas6.csv";
        xfileLocation = "/Users/Paolo/IdeaProjects/import_csv_test/csv_25mm.csv";

        System.out.println("Array loaded.");
        System.out.println("Size: " + myArray.length + " " + myArray[myArray.length-1].length);

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

            System.out.println("Error loading CSV. " + e);
        }
    }

    public static void detectCircle(int x0, int y0, int radius, int tolerance) {
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

        }


    }


    public static int searchCircle(int r, int maxRadius) {
        int count = 0;
        int radius = r;

        int counter = 0;

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
                        && (myArray[x120][y120] > tolerance)
                        && (myArray[x135][y135] > tolerance)
                        && (myArray[x150][y150] > tolerance)
                        && (myArray[x225][y225] > tolerance)
                        && (myArray[x315][y315] > tolerance))
                { // if statement open

                    count+=1;
                    System.out.println("Center: " + i + " " +j);


                } // if close


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


    }

}