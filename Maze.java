import becker.robots.*;

/**
 * CS1A - Assignment 3 - "The Maze" 
 * Quarter: Winter2021 
 * Summary: This code is to navigate a Robot through a maze that could sightly change every time it's running, 
 * as well as put down a thing in the intersection where it goes through without duplication<br>
 */
/**
 * The Mazebot class inheritance the RobotSE class with defined methods below:
 * putThing, counter, move, check directions and print everything. The RobotSE
 * will finally exit the maze although the maze could slightly change and print
 * out the counters.
 * 
 * @author Yuxi Sang
 * @author Tina Shao
 * 
 * 
 */
class MazeBot extends RobotSE
{
   // TODO: Instance Variables will be declared and initialized here
   // one each for totalMoves, movesWest, movesEast, movesSouth, and MovesNorth
   /**
    * Define the variable of totalMoves to count the total moves of the robot.
    */
   private int totalMoves = 0;
   /**
    * Define the variable of moveWest to count the total west moves of the robot.
    */
   private int moveWest = 0;
   /**
    * Define the variable of moveWest to count the total east moves of the robot.
    */
   private int moveEast = 0;
   /**
    * Define the variable of moveWest to count the total north moves of the robot.
    */
   private int moveNorth = 0;
   /**
    * Define the variable of moveWest to count the total south moves of the robot.
    */
   private int moveSouth = 0;

   /**
    * The constructor,MazeBot, is to construct a robot with the define
    * city,street,avenue,direction, and numbers of things in back pack.
    * 
    * @param theCity
    *           The name of city where the robot locate.
    * @param str
    *           The street of robot initially at.
    * @param ave
    *           The avenue of the robot initially at.
    * @param dir
    *           The initial direction of the robot.
    * @param numThings
    *           The numbers of things in robot's backpack.
    */
   public MazeBot(City theCity, int str, int ave, Direction dir, int numThings)
   {
      super(theCity, str, ave, dir, 1000);
   }

   /*
    * TODO: Override here the move method and make it count everything it is
    * supposed to by adding to the instance variables as well as moving the
    * MazeBot. This overridden move method must be called by the NavigateMaze
    * method or by some method(s) called from NavigateMaze.
    */

   // TODO: You must override the putThing method here.

   /**
    * The method "putThing" is to check if there is a thing in the intersection,
    * pick it if true. Then put down a thing in every intersection where it moves
    * towards if there are still things in the backpack.
    * 
    */
   public void putThing()
   {
      if (this.canPickThing())
         this.pickAllThings();

      if (this.countThingsInBackpack() > 0)
         super.putThing();
   }

   /**
    * The method "counter" is to increase the counter of total moving steps and
    * each directions' moving.
    * 
    *
    */
   public void counter()
   {
      totalMoves++;
      if (this.isFacingWest())
      {
         moveWest++;
      }
      if (this.isFacingEast())
      {
         moveEast++;
      }
      if (this.isFacingNorth())
      {
         moveNorth++;
      }
      if (this.isFacingSouth())
      {
         moveSouth++;
      }
   }

   /**
    * The method "move" is to override the RobotSE's move method. Other than just
    * move the robot, the overriding method can also put a thing on current
    * intersection and increase the counter.
    * 
    * 
    */
   public void move()
   {
      this.putThing();

      super.move();

      this.counter();
   }

   /**
    * The method "checkdirection" is to do everything to exit the maze. * The robot
    * will decide whether to move forward, turn right, turn left or turn around
    * depend on its location.
    * 
    * 
    */
   public void checkdirection()
   {
      this.turnRight();
      if (this.frontIsClear())
      {

         this.move();
      } else
      {

         this.turnLeft();
         if (this.frontIsClear())
         {
            this.move();
         } else
         {
            this.turnLeft();
         }
      }
   }

   /**
    * The method "printEverthing" is to display messages after the maze is solved.
    * It indicates the total moves of the robot and exact moves towards each
    * direction.
    * 
    * 
    */
   public void printEverything()// Or printTotalNumberOfSpacesMoved(),
   // whichever you decide
   {
      System.out.println("Hooray! don has sloved the maze.\n");
      System.out.println("Total number of spaces moved: " + totalMoves);
      System.out.println("Total number of westward moved: " + moveWest);
      System.out.println("Total number of eastern moved: " + moveEast);
      System.out.println("Total number of northern moved: " + moveNorth);
      System.out.println("Total number of southern moved: " + moveSouth);
   }

   // The isAtEndSpot() method below is what's called a 'helper method' It
   // exists just to make another command (in this case, NavigateMaze) easier
   // to understand. It does this by replacing some code that otherwise would
   // be in NavigateMaze with it's name, and doing that work here, instead.
   // Declaring it "private" means that only the MazeBot is allowed to call
   // upon it.

   /**
    * The method "isAtEndSpot" is to check if the Robot has arrived the final by
    * obtaining its avenue and street location.
    * 
    * @return Robot's final street and avenue.
    * 
    */
   private boolean isAtEndSpot()
   {

      return getAvenue() == 9 && getStreet() == 10;

   }

   // THIS IS THE METHOD WE WILL USE TO DO EVERYTHING (IT WILL CALL
   // OTHER METHODS LIKE isAtEndSpot, ETC)

   /**
    * The method "navigateMaze" is to guide the Robot to the exit of the maze by
    * checking the locations of Robot and Walls, deciding the direction then
    * printing everything once arrived.
    * 
    */

   public void navigateMaze()
   {
      // While your robot hasn't yet reached the 'end spot', keep navigating
      // through the Maze and doing its thing
      while (!isAtEndSpot())
      {
         // TODO: The robot will navigate the maze until it reaches the end spot.
         // What will you have the robot do at each step?
         this.checkdirection();

      }
      // TODO: After completing Maze, print total number of spaces moved and how
      // many times robot moved East, South, West, North.
      if (isAtEndSpot())
      {
         this.printEverything();
      }

   }

}

// ###################################################################################################
// NO NEED TO TOUCH ANYTHING FROM HERE ON DOWN, EXCEPT TO CHANGE NUMBER OF
// THINGS IN BACKPACK IN MAIN AND ADDING JavaDoc
// The NavigateMaze() method is already set up and called by don the robot down
// in main
// ###################################################################################################
public class Maze extends Object
{
   private static void makeMaze(City theCity)
   {
      for (int i = 1; i < 11; i++)
      {
         // north wall
         new Wall(theCity, 1, i, Direction.NORTH);

         // Second to north wall
         if (i <= 9)
            new Wall(theCity, 1, i, Direction.SOUTH);

         // Third to north wall
         if (i >= 4)
            new Wall(theCity, 4, i, Direction.SOUTH);

         // south wall
         if (i != 9) // (9, 10, SOUTH), is where the 'exit' is
            new Wall(theCity, 10, i, Direction.SOUTH);

         // west wall
         if (i != 1) // (1, 1, WEST) is where the 'entrance' is
            new Wall(theCity, i, 1, Direction.WEST);

         // second to most western wall
         if (i >= 3 && i < 6)
            new Wall(theCity, i, 6, Direction.WEST);

         // east wall
         new Wall(theCity, i, 10, Direction.EAST);
      }

      // Cul de Sac
      new Wall(theCity, 3, 10, Direction.WEST);
      new Wall(theCity, 3, 10, Direction.SOUTH);

      new Wall(theCity, 2, 8, Direction.WEST);
      new Wall(theCity, 2, 8, Direction.SOUTH);

      new Wall(theCity, 10, 8, Direction.NORTH);
      new Wall(theCity, 10, 9, Direction.EAST);
      new Wall(theCity, 10, 9, Direction.NORTH);
      makeSpiral(theCity, 8, 9, 3);
      new Wall(theCity, 8, 10, Direction.SOUTH);

      makeSpiral(theCity, 10, 5, 4);
   }

   public static void makeSpiral(City theCity, int st, int ave, int size)
   {
      // We start out building the wall northward
      // the walls will be built on the east face of the current
      // intersection
      Direction facing = Direction.EAST;

      while (size > 0)
      {
         int spacesLeft = size;
         int aveChange = 0;
         int stChange = 0;
         switch (facing)
         {
         case EAST:
            stChange = -1;
            break;
         case NORTH:
            aveChange = -1;
            break;
         case WEST:
            stChange = 1;
            break;
         case SOUTH:
            aveChange = 1;
            break;
         }

         while (spacesLeft > 0)
         {
            new Wall(theCity, st, ave, facing);
            ave += aveChange;
            st += stChange;
            --spacesLeft;
         }
         // back up one space
         ave -= aveChange;
         st -= stChange;

         switch (facing)
         {
         case EAST:
            facing = Direction.NORTH;
            break;
         case NORTH:
            facing = Direction.WEST;
            size--;
            break;
         case WEST:
            facing = Direction.SOUTH;
            break;
         case SOUTH:
            facing = Direction.EAST;
            size--;
            break;
         }
      }
   }

   // ###########################################################################################
   // Main Method
   // ###########################################################################################
   public static void main(String[] args)
   {
      City calgary = new City(12, 12);
      MazeBot don = new MazeBot(calgary, 1, 1, Direction.EAST, 0); // TODO: <-- YOU WILL NEED TO CHANGE THIS FROM ZERO

      Maze.makeMaze(calgary);

      calgary.showThingCounts(true); // This will help you see if you incorrectly put more than 1 thing down in any
                                     // intersections

      don.navigateMaze(); // <-- HERE'S WHERE THE NavigateMaze() method is
      // called. NO NEED TO TOUCH AT ALL
      don.printEverything();
   }
}
