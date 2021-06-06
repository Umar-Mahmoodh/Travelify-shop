package com.company;

public class Queue extends Hotel {

    private static String[] queue = new String[10];
    private static int first = -1;
    private static int no = 0;
    private static int last = 0;

    Queue(int size) {

        queue = new String[size];

    }

    public static void main(String[] args) {

    }

    public static void addQueue(String data) {

        first++;

        if (first < 11) {

            queue[first] = data;

        }

        else {

            System.err.println("Booking is Full");

            queue[first - 7] = data;

        }

    }

    public static void delete(String name) {

        if (first > last) {

            queue[last] = " ";

            last++;

            no--;


        }

        else {

            System.out.println("Sorry There are No Booking Made For The Particular Room");

        }

    }

    public static void displayQueue() {

        if (first >= last) {

            System.out.println("The Queue is : ");

            for (int j = last; j < 3; j++) {

                System.out.println(queue[j]);

            }

        }

    }

}

