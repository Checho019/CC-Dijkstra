import java.util.Scanner;

public class Main {

    static String data = "8 11 7 8 99 6 8 122 1 2 153 1 4 207 2 3 40 2 6 87 3 5 62 4 5 33 4 7 59 5 6 23 5 7 18";
    static int numNodes;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner input = new Scanner(data);
        numNodes = input.nextInt();
        matrix = new int[numNodes][numNodes];

        while (input.hasNext()) {
            int a = input.nextInt() - 1, b = input.nextInt() - 1;
            int value = input.nextInt();
            matrix[a][b] = value;
            matrix[b][a] = value;
        }
        dijkstraMatrix();
        print();
    }

    // Validate list of visited nodes
    public static boolean validateVisitedList(boolean[] visitedList, int pos) {
        for (int i = pos; i < numNodes; i++){
            if (!visitedList[i]) {
                return true;
            }
        }
        return false;
    }

    public static void dijkstraMatrix() {
        for (int i = 0; i < matrix.length; i++) {

            // List of visited nodes
            boolean[] visited = new boolean[numNodes];
            for (int j = 0; j < numNodes; j++) {
                visited[j] = false;
            }

            // Find the shortest distance
            visited[i] = true;
            while (validateVisitedList(visited, i)){
                int position = 0, lowest = Integer.MAX_VALUE;
                for (int j = 0; j < numNodes; j++){
                    if (visited[j]){
                        continue;
                    }
                    if (matrix[i][j] != 0 && matrix[i][j] < lowest){
                        lowest = matrix[i][j];
                        position = j;
                    }
                }

                // Replace another ways
                visited[position] = true;
                for (int k = 0; k < numNodes; k++){
                    if (matrix[position][k] != 0){
                        int value;
                        value = matrix[position][k] + lowest;
                        if ((value <= matrix[i][k] || matrix[i][k] == 0) && k != i) {
                            matrix[i][k] = value;
                        }
                    }
                }
            }
        }
    }

    // Print matrix
    public static void print(){
        for (int i = 0; i < numNodes; i++){

            if (i == 0){
                System.out.print("\t");
                for (int j = 0; j < numNodes; j++){
                    System.out.print((j + 1) + "\t");
                }
                System.out.println();
            }
            System.out.print((i + 1) + "|\t");

            for (int j = 0; j < numNodes; j++){
                if (j <= i){
                    System.out.print("0" + "\t");
                    continue;
                }

                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}