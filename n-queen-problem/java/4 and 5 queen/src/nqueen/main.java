package nqueen;
import java.util.ArrayList;
import java.util.List;

class Square {
 int row, col, value;

 Square(int row, int col) {
  this.row = row;
  this.col = col;
 }
 Square() {}

 public void set(int row, int col) {
  this.row = row;
  this.col = col;
 }

 public void setValue(int value) {
  this.value = value;
 }

}
class Queen {

 Square position;

 Queen(Square position) {
  this.position = position;
 }
 public void queen() {

 }
 public Square get() {
  return this.position;

 }
 public void set(Square position) {
  this.position = position;
 }
}

class Board {
 static int size;
 static ArrayList < ArrayList < Square >> squares = new ArrayList < ArrayList < Square >> ();
 static ArrayList < Queen > queens = new ArrayList();

 Board(int n) {
  size = n;
  for (int i = 0; i < size; i++) {
   ArrayList < Square > row = new ArrayList < Square > ();
   for (int j = 0; j < size; j++) {
    Square sq = new Square(i, j);
    sq.setValue(0);
    row.add(sq);
   }
   squares.add(row);
  }
 }
 void display() {
  for (int i = 0; i < size; i++) {
   for (int j = 0; j < size; j++) {
    System.out.print("    " + this.squares.get(i).get(j).value);
   }
   System.out.println("\n");
  }
  System.out.println(queens.get(0).position.row + "-" + queens.get(0).position.col);
 }

 public static Board reset(Square initialPosition) {
     //System.out.println("test"+initialPosition.row+"-"+initialPosition.col);
  queens.clear();
  squares.clear();
  Board temp = new Board(size);
     //System.out.println("test"+initialPosition.row+"-"+initialPosition.col);
  temp.squares.get(initialPosition.row).get(initialPosition.col).setValue(1);
  Queen q = new Queen(initialPosition);
  queens.add(q);
  placeQueen(initialPosition);
  if(queens.size()<size){
      Square sq;
      if(initialPosition.col==size-1&&initialPosition.row<size-1){
          initialPosition.col=0;
          initialPosition.row++;
      }else if(initialPosition.col<size-1){
          initialPosition.col++;
      }else{
          System.out.println(" sorry :( ");
          return temp;
      }
      sq=new Square(initialPosition.row,initialPosition.col);
      
      Board.reset(sq);
  }
  return temp;
 }

 private static void placeQueen(Square lastQueenPosition) {
  Boolean flag = true;
  for (int i = lastQueenPosition.row + 1; i < size; i++) {
   for (int j = 0; j < size; j++) {
    if (checkValid(i, j)) {
     squares.get(i).get(j).setValue(1);
     Square sq = new Square(i, j);
     Queen q = new Queen(sq);
     queens.add(q);
     
    }
   }
  }
 }

 private static Boolean checkValid(int row, int col) {
  Boolean flag = true;
  int my_diff = col - row;
  int my_sum = col + row;
  for (int q = 0; q < queens.size(); q++) {
   int q_diff = queens.get(q).position.col - queens.get(q).position.row;
   int q_sum = queens.get(q).position.col + queens.get(q).position.row;
   if (queens.get(q).position.row == row || queens.get(q).position.col == col) {
    flag = false;
   }
   if (q_diff == my_diff || q_sum == my_sum) {
    flag = false;
   }
  }
  return flag;
 }
}

public class main {

 public static void init(int size) {
  Board.size = size;
 }

 public static void main(String[] args) {
  init(6);
  Square sq = new Square(0, 0);
  Board br = Board.reset(sq);
  br.display();
 }

}