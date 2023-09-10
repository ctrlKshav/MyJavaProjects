import  java.util.*;

class Coordinates{
    int x,y;
    Coordinates parent;
    Coordinates(int x,int y,Coordinates parent){
        this.x=x;
        this.y=y;
        this.parent=parent;
    }
}

class ChessBoard{

    int boardSize;
    char[][] chessboard ;


    ChessBoard(){
        boardSize=8;
        chessboard= new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                chessboard[i][j] = '-';
            }
        }
    }

    void printChessBoard(Coordinates inital,Coordinates target){
        // Display the empty chessboard
        System.out.println("  \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500");
        for (int i = 0; i < boardSize; i++) {
            System.out.print((boardSize - i) + " | ");
            for (int j = 0; j < boardSize; j++) {

                if (inital != null) {
                    if(i==inital.x && j==inital.y){
                        System.out.print("♞ ");
                        continue;
                    }
                }
                if (target != null) {
                    if(i==target.x && j==target.y){
                        System.out.print("♞ ");
                        continue;
                    }
                }
                    //I can't print this knight for now because it takes more than one space and messes up the ui
                    //we will add it later
//                    System.out.print("♞");
                //Possible: printChessBoardWithKnight()
                System.out.print(chessboard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("  \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500");
        System.out.println("    A B C D E F G H");
    }

}

public class KnightTravails {
    
    static Coordinates inital,target;

    static void convertChessNotationToCoords(){
    
        ChessBoard cb=new ChessBoard();
        Scanner sc=new Scanner(System.in);
        cb.printChessBoard(null,null);
        System.out.println("Knight's Starting Position");
        char rank,file;
        while (true) {
            String user = sc.nextLine().trim().toUpperCase();
            if(user.length()==2) {
                file = user.charAt(0);
                rank = user.charAt(1);
                if ((file >= 'A' && file <= 'H') && (rank > '0' && rank < '9'))
                    break;
            }
            System.out.println("Please Enter Valid Chess Notation");
        }
        //Chess coordinates go bottom to up whereas array go up to bottom
        int rowStart = '8' - rank;
        int columnStart = file - 'A';
        inital=new Coordinates(rowStart,columnStart,null);

        cb.printChessBoard(inital,null);

        System.out.println("Knight's Ending Position");
        while (true) {
            String user = sc.nextLine().trim().toUpperCase();
            if(user.length()==2) {
                file = user.charAt(0);
                rank = user.charAt(1);
                if ((file >= 'A' && file <= 'H') && (rank > '0' && rank < '9'))
                    break;
            }
            System.out.println("Please Enter Valid Chess Notation");
        }
        //Chess coordinates go bottom to up whereas array go up to botoom
        int rowEnd = '8' - rank;
        int columnEnd = file - 'A';
        target=new Coordinates(rowEnd,columnEnd,null);
        cb.printChessBoard(inital,target);
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public static List<Coordinates> findShortestPath(Coordinates initial,Coordinates target) {
        int[][] possibleMoves = {
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
                {1, -2}, {2, -1}, {2, 1}, {1, 2}
        };

        Queue<Coordinates> queue = new LinkedList<>();
        boolean[][] visited = new boolean[8][8];

        Coordinates startNode = initial;
        queue.add(startNode);
        visited[initial.x][initial.y] = true;

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            if (current.x == target.x && current.y == target.y)
                return reconstructPath(current);


            for (int[] move : possibleMoves) {
                int newX = current.x + move[0];
                int newY = current.y + move[1];

                if (isValid(newX, newY) && !visited[newX][newY]) {
                    Coordinates newPoint = new Coordinates(newX, newY, current);
                    queue.add(newPoint);
                    visited[newX][newY] = true;
                }
            }
        }

        return null; // No path found
    }

    public static List<Coordinates> reconstructPath(Coordinates target) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = target;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ChessBoard cb=new ChessBoard();
        convertChessNotationToCoords();
        List<Coordinates> path=findShortestPath(inital,target);
        if(path!=null) {
            System.out.println("The Minimum number of moves required by the Knight to reach the target square is : " + (path.size() - 1));
            System.out.println("Press Enter to see the Move Sequence");
            sc.nextLine();
            System.out.println("Starting Position");

            for (Coordinates c : path) {
                if (c.parent != null) {
                    char rank = (char) ('8' - (char) c.x);
                    char file = (char) ('A' + (char) c.y);
                    System.out.println("Press Enter to Play " + file + "" + rank);
                    sc.nextLine();
                }
                cb.printChessBoard(c, null);

            }
        }
        else{
            System.out.println("No Path Found");
        }


    }
}
