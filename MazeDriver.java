
import java.util.Scanner;

public class MazeDriver
{

	// 0 is a wall
	// 1 is the player
	// 2 is the goal
	// . or period is the pathway

	public static void main( String[ ] args )
	{
		Scanner input = new Scanner ( System.in );
		Maze maze = new Maze ( );
		char answer;
		char moveVal;
		System.out.println ( "Do you want to play? y/n" );
		answer = input.nextLine ( ).charAt ( 0 );

		Boolean playing = true;
		maze.setupGame ( );
		maze.generateWall ( );
		maze.generateWall ( );
		if ( answer == 'n' )
		{
			while ( playing == true )
			{

				System.out.println ( );

				maze.solveMaze ( );
				playing = maze.gameOverCheck ( );
			}
		}

		if ( answer == 'y' )
		{
			while ( playing == true )
			{
				maze.printArray ( );
				System.out.println ( "enter movement w, a, s, d" );
				moveVal = input.nextLine ( ).charAt ( 0 );
				System.out.println ( maze.move ( moveVal ) );
				playing = maze.gameOverCheck ( );
				if ( playing == false )
				{
					System.out.println ( "You win!" );
				}
			}
		}
		input.close ( );
	}
}
