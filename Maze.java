
import java.util.ArrayList;
import java.util.Random;

class Maze
{
	String[ ][ ] mazeArray = new String[15][15];
	String[ ][ ] emptySpace = new String[15][15];
	Boolean[ ][ ] visitedLocation = new Boolean[15][15];
	Integer[ ][ ] test = new Integer[15][15];
	ArrayList<Integer> previousX = new ArrayList<Integer> ( );

	ArrayList<Integer> previousY = new ArrayList<Integer> ( );
	ArrayList<Integer> solveX = new ArrayList<Integer> ( );
	ArrayList<Integer> solveY = new ArrayList<Integer> ( );
	ArrayList<Integer> lastHoleX = new ArrayList<Integer> ( );
	ArrayList<Integer> lastHoleY = new ArrayList<Integer> ( );
	Character player = new Character ( );
	Random rand = new Random ( );

	public Maze() // Default Constructor
	{

		for ( int i = 0; i < visitedLocation.length; i++ )
		{
			for ( int j = 0; j < visitedLocation.length; j++ )
			{
				visitedLocation[i][j] = false;
				test[i][j] = -1;
			}
		}
		solveX.add ( player.getPlayerX ( ) );
		solveY.add ( player.getPlayerY ( ) );
		for ( int i = 0; i < emptySpace.length; i++ )
		{
			for ( int j = 0; j < emptySpace.length; j++ )
			{
				emptySpace[i][j] = ".";
				mazeArray[i][j] = ".";

			}
		}
		for ( int i = 0; i < mazeArray.length; i++ )
		{

			for ( int j = 0; j < mazeArray.length; j++ )
			{
				mazeArray[0][j] = "0";
				mazeArray[j][0] = "0";
				mazeArray[14][j] = "0";
				mazeArray[j][14] = "0";

			}
		}

	}

	/**************************************
	 * generateMaze outputs a computer generated maze in the console.
	 * 
	 * Parameters: None
	 * 
	 * Returns nothing
	 */
	public void generateMaze( )
	{

		int pointerX = player.getPlayerX ( );
		int pointerY = player.getPlayerY ( );
		int lastXIndex = 0;
		int lastYIndex = 0;
		Boolean playing = gameOverCheck ( );
		String moveReturn; // change to solved later
		Tile[ ][ ] tiles = new Tile[15][15];
		if ( playing == false )
		{
			return;
		}
		for ( int i = 0; i < mazeArray.length; i++ )
		{
			for ( int j = 0; j < mazeArray.length; j++ ) // length maybe off
			{
				Tile tile = new Tile ( i, j );
				tiles[i][j] = tile;
			}
		}
		tiles[pointerY][pointerX].setVisited ( true );
		previousX.add ( tiles[pointerY][pointerX].getTileX ( ) ); // Arraylist of coordinates
		previousY.add ( tiles[pointerY][pointerX].getTileY ( ) );
		lastXIndex = previousX.size ( ) - 1;
		lastYIndex = previousY.size ( ) - 1;
		// "You hit a wall dummy"

		moveReturn = move ( 'w' );

		if ( moveReturn == "You hit a wall dummy" )
		{

			if ( tiles[pointerY][pointerX - 1].getVisited ( ) != true || mazeArray[pointerY][pointerX - 1] != "0" )
			{
				moveReturn = move ( 'a' );
				pointerX = player.getPlayerX ( );
				pointerY = player.getPlayerY ( );
				mazeArray[pointerY][pointerX] = "1";
				previousX.add ( tiles[pointerY][pointerX].getTileX ( ) );
				previousY.add ( tiles[pointerY][pointerX].getTileY ( ) );

			}

		}

		else if ( moveReturn == "You hit a wall dummy" || tiles[pointerY + 1][pointerX].getVisited ( ) != true
				|| mazeArray[pointerY + 1][pointerX] != "0" )
		{
			moveReturn = move ( 's' );
			pointerX = player.getPlayerX ( );
			pointerY = player.getPlayerY ( );
			mazeArray[pointerY][pointerX] = "1";
			previousX.add ( tiles[pointerY][pointerX].getTileX ( ) );
			previousY.add ( tiles[pointerY][pointerX].getTileY ( ) );

		}

		else if ( moveReturn == "You hit a wall dummy" || tiles[pointerY][pointerX + 1].getVisited ( ) != true
				|| mazeArray[pointerY][pointerX + 1] != "0" )
		{
			moveReturn = move ( 'd' );
			pointerX = player.getPlayerX ( );
			pointerY = player.getPlayerY ( );
			mazeArray[pointerY][pointerX] = "1";
			previousX.add ( tiles[pointerY][pointerX].getTileX ( ) );
			previousY.add ( tiles[pointerY][pointerX].getTileY ( ) );

		}

		else if ( moveReturn == "You hit a wall dummy" )
		{

			lastXIndex = previousX.size ( );
			lastYIndex = previousX.size ( );
			previousX.remove ( lastXIndex );
			previousY.remove ( lastYIndex );
			player.setPlayerX ( previousY.get ( lastXIndex ) );
			player.setPlayerY ( previousY.get ( lastYIndex ) );
			pointerX = player.getPlayerX ( );
			pointerY = player.getPlayerY ( );
			// lastXIndex = previousX.size ( ) - 1;
			// lastYIndex = previousY.size ( ) - 1;

		}

		mazeArray[pointerY][pointerX] = "1";
		System.out.println ( );
		printArray ( );

	} // End of generateMaze

	/*******************************************
	 * setEmptySpace outputs nothing, it sets the empty space of the maze to a period.
	 * 
	 * Parameters: both integers x and y the coordinates
	 * 
	 * returns nothing
	 *
	 */
	public void setEmptySpace( int x, int y )
	{
		mazeArray[y][x] = ".";
	}// End of setEmptySpace

	/**********************************************
	 * generateWall outputs nothing it creates walls within the array so that when the array is printed, it is printed
	 * with walls
	 * 
	 * Parameters: None
	 * 
	 * Returns nothing
	 */
	public void generateWall( )

	// maybe later add makeHole Method

	{
		Integer wallX = rand.nextInt ( 11 ) + 2;
		Integer wallY = rand.nextInt ( 11 ) + 1;
		Integer holeX = rand.nextInt ( 12 ) + 1;
		Integer holeY = rand.nextInt ( 12 ) + 1;
		lastHoleX.add ( holeX );
		lastHoleY.add ( holeY );
		previousX.add ( wallX );
		previousY.add ( wallY );
		for ( int i = 0; i < previousX.size ( ); i++ )
		{
			if ( wallX == previousX.get ( i ) + 1 && wallX == previousX.get ( i ) + -1 )
				generateWall ( );

		}
		for ( int i = 0; i < previousY.size ( ); i++ )
		{
			if ( wallY == previousY.get ( i ) + 1 && wallY == previousY.get ( i ) + 1 )
			{
				generateWall ( );
			}

		}

		previousX.add ( wallX );
		previousY.add ( wallY );
		for ( int i = 2; i < mazeArray.length; i++ ) // make verticle line
		{
			if ( mazeArray[i][wallX] != "1" && mazeArray[i][wallX] != "2" )
			{
				mazeArray[i][wallX] = "0";
				mazeArray[holeX][wallX] = ".";
				mazeArray[holeX - 1][wallX] = ".";
				mazeArray[holeX + 1][wallX] = ".";
				// diagnol

				mazeArray[holeX + 1][wallX + 1] = ".";
				mazeArray[holeX - 1][wallX - 1] = ".";
				mazeArray[holeX + 1][wallX - 1] = ".";
				mazeArray[holeX - 1][wallX + 1] = ".";
			}
		}
		for ( int i = 2; i < mazeArray.length - 1; i++ ) // make horizontal line
		{
			if ( mazeArray[wallY][i] != "1" && mazeArray[wallY][i] != "2" )
			{

				mazeArray[wallY][i] = "0";
				mazeArray[wallY][holeY] = ".";

				mazeArray[wallY][holeY - 1] = ".";
				mazeArray[wallY][holeY + 1] = ".";

				// diagnol

				mazeArray[wallY + 1][holeY + 1] = ".";
				mazeArray[wallY - 1][holeY - 1] = ".";
				mazeArray[wallY + 1][holeY - 1] = ".";
				mazeArray[wallY - 1][holeY + 1] = ".";
			}
		}
		for ( int i = 1; i < mazeArray.length - 1; i++ )
		{

			setupGame ( );
		}

	}// End of generateWall

	/******************************************
	 * psuedoGen outputs nothing
	 * 
	 * Parameters: None
	 * 
	 * Returns nothing
	 */
	public void psuedoGen( )
	{
		for ( int i = 0; i < test.length; i++ )
		{
			for ( int j = 0; j < test.length; j++ )
			{
				if ( test[i][j] != 1 )
				{
					mazeArray[i][j] = ".";
				}
			}
		}
	} // End of psuedoGen

	/********************************************
	 * soveMaze outputs nothing this method is responsible for solving the maze without human input
	 * 
	 * Parameters: None
	 * 
	 * Returns ?
	 */
	public void solveMaze( ) // not finished still need to mark spots that have been visited
	{
		int randomNum = rand.nextInt ( 4 );

		String hitWall = "You moved";
		Boolean playing;
		playing = gameOverCheck ( );
		test[player.getPlayerY ( )][player.getPlayerX ( )] = 1;

		if ( playing == true )
		{
			printArray ( );
		}
		try
		{
			Thread.sleep ( 1 );
		} catch ( InterruptedException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace ( );
		}
		{
			if ( playing == true )
			{
				if ( randomNum == 0 ) // Call move up method
				{
					hitWall = move ( 'w' );
					if ( hitWall == "You moved" ) // if move up returns a different string
					{
						solveX.add ( player.getPlayerX ( ) );
						solveY.add ( player.getPlayerY ( ) );
						visitedLocation[player.getPlayerY ( )][player.getPlayerX ( )] = true;
						solveMaze ( );
					}
				}
				else if ( randomNum == 1 )
				{
					hitWall = move ( 'a' );
					if ( hitWall == "You moved" )
					{
						solveX.add ( player.getPlayerX ( ) );
						solveY.add ( player.getPlayerY ( ) );
						visitedLocation[player.getPlayerY ( )][player.getPlayerX ( )] = true;
						solveMaze ( );
					}
				}
				else if ( randomNum == 2 )
				{
					hitWall = move ( 's' );
					if ( hitWall == "You moved" )
					{
						solveX.add ( player.getPlayerX ( ) );
						solveY.add ( player.getPlayerY ( ) );
						visitedLocation[player.getPlayerY ( )][player.getPlayerX ( )] = true;
						solveMaze ( );
					}
				}
				else if ( randomNum == 3 )
				{
					hitWall = move ( 'd' );
					if ( hitWall == "You moved" )
					{
						solveX.add ( player.getPlayerX ( ) );
						solveY.add ( player.getPlayerY ( ) );
						visitedLocation[player.getPlayerY ( )][player.getPlayerX ( )] = true;
						solveMaze ( );
					}
				}
				else
				{

					if ( solveX.size ( ) > 0 )
					{
						mazeArray[player.getPlayerY ( )][player.getPlayerX ( )] = ".";
						visitedLocation[player.getPlayerY ( )][player.getPlayerX ( )] = false;
						solveX.remove ( solveX.size ( ) - 1 );
						solveY.remove ( solveY.size ( ) - 1 );
						visitedLocation[solveY.get ( solveY.size ( ) )][solveX.get ( solveX.size ( ) )] = false;

						player.setPlayerX ( solveX.get ( solveX.size ( ) ) );
						player.setPlayerY ( solveY.get ( solveY.size ( ) ) );

						visitedLocation[player.getPlayerY ( )][player.getPlayerX ( )] = false;

						mazeArray[player.getPlayerY ( )][player.getPlayerX ( )] = "1";
						solveMaze ( );
					}

				}
			}
			if ( playing == false )
			{
				return;
			}
		}
	} // End of solveMaze

	/*****************************************
	 * printArray outputs the mazeArray to display the maze that needs to be solved
	 * 
	 * Parameters: None
	 * 
	 * Returns nothing
	 */
	public void printArray( )
	{
		for ( int i = 0; i < mazeArray.length; i++ )
		{
			System.out.println ( );
			for ( int j = 0; j < mazeArray.length; j++ )
			{
				System.out.print ( mazeArray[i][j] + "  " );

			}
		}

	} // End of printArray

	/******************************************
	 * move outputs nothing this method allows the player to move through the maze in the inputed direction
	 * 
	 * Parameters: the character dir the direction in which the player wants to move
	 * 
	 * Returns sentence tells them if they hit a wall and when they moved
	 */
	public String move( char dir )
	{
		String sentence = "You moved";

		for ( int i = 0; i < mazeArray.length; i++ )
		{
			for ( int j = 0; j < mazeArray.length; j++ )
			{
				if ( mazeArray[i][j] == "1" )
				{
					player.setPlayerX ( j );
					player.setPlayerY ( i );
				}
			}
		}
		if ( dir == 'w' )
		{
			if ( mazeArray[player.getPlayerY ( ) - 1][player.getPlayerX ( )] == "0"
					|| mazeArray[player.getPlayerY ( ) - 1][player.getPlayerX ( )] == "1" )// ||
																													// visitedLocation[player.getPlayerY
																													// ( )-1][player.getPlayerX ( )]
																													// == true
			{
				sentence = "You hit a wall dummy";
			}
			else
			{
				mazeArray[player.getPlayerY ( )][player.getPlayerX ( )] = emptySpace[player.getPlayerY ( )][player
						.getPlayerX ( )]; // sets player position to empty space
				mazeArray[player.getPlayerY ( ) - 1][player
						.getPlayerX ( )] = player.playerPosition[player.getPlayerY ( ) - 1][player.getPlayerX ( )]; // moves
				player.setPlayerX ( player.getPlayerX ( ) );
				player.setPlayerY ( player.getPlayerY ( ) - 1 );
			}
		}
		if ( dir == 'a' )
		{
			if ( mazeArray[player.getPlayerY ( )][player.getPlayerX ( ) - 1] == "0"
					|| mazeArray[player.getPlayerY ( )][player.getPlayerX ( ) - 1] == "1" )// ||
																													// visitedLocation[player.getPlayerY
																													// ( )][player.getPlayerX ( )-1]
																													// == true
			{
				sentence = "You hit a wall dummy";
			}
			else
			{
				mazeArray[player.getPlayerY ( )][player.getPlayerX ( )] = emptySpace[player.getPlayerY ( )][player
						.getPlayerX ( )];
				mazeArray[player.getPlayerY ( )][player.getPlayerX ( )
						- 1] = player.playerPosition[player.getPlayerY ( )][player.getPlayerX ( ) - 1];
				player.setPlayerX ( player.getPlayerX ( ) - 1 );
				player.setPlayerY ( player.getPlayerY ( ) );
			}
		}

		if ( dir == 's' )
		{
			if ( mazeArray[player.getPlayerY ( ) + 1][player.getPlayerX ( )] == "0"
					|| mazeArray[player.getPlayerY ( ) + 1][player.getPlayerX ( )] == "1" ) // ||
																													// visitedLocation[player.getPlayerY
																													// ( )+1][player.getPlayerX ( )]
																													// == true
			{
				sentence = "You hit a wall dummy";
			}
			else
			{
				mazeArray[player.getPlayerY ( )][player.getPlayerX ( )] = emptySpace[player.getPlayerY ( )][player
						.getPlayerX ( )];
				mazeArray[player.getPlayerY ( ) + 1][player
						.getPlayerX ( )] = player.playerPosition[player.getPlayerY ( ) + 1][player.getPlayerX ( )];
				player.setPlayerX ( player.getPlayerX ( ) );
				player.setPlayerY ( player.getPlayerY ( ) + 1 );
			}
		}
		if ( dir == 'd' )
		{
			if ( mazeArray[player.getPlayerY ( )][player.getPlayerX ( ) + 1] == "0"
					|| mazeArray[player.getPlayerY ( )][player.getPlayerX ( ) + 1] == "1" )// ||
																													// visitedLocation[player.getPlayerY
																													// ( )][player.getPlayerX ( )+1]
																													// == true
			{
				sentence = "You hit a wall dummy";
			}
			else
			{
				mazeArray[player.getPlayerY ( )][player.getPlayerX ( )] = emptySpace[player.getPlayerY ( )][player
						.getPlayerX ( )];

				mazeArray[player.getPlayerY ( )][player.getPlayerX ( )
						+ 1] = player.playerPosition[player.getPlayerY ( )][player.getPlayerX ( ) + 1];
				player.setPlayerX ( player.getPlayerX ( ) + 1 );
				player.setPlayerY ( player.getPlayerY ( ) );
			}
		}
		else
		{

		}

		return sentence;
	} // End of move

	/***************************************
	 * setupGame outputs nothing this method sets the maze to be solved
	 * 
	 * Parameters: None
	 * 
	 * Returns nothing
	 */
	public void setupGame( )
	{
		for ( int i = 0; i < mazeArray.length; i++ )
		{

			for ( int j = 0; j < mazeArray.length; j++ )
			{
				mazeArray[1][1] = "1";
				mazeArray[mazeArray.length - 2][mazeArray.length - 2] = "2";

				mazeArray[mazeArray.length - 3][mazeArray.length - 2] = ".";
				mazeArray[mazeArray.length - 2][mazeArray.length - 3] = ".";
				mazeArray[0][j] = "0";
				mazeArray[j][0] = "0";
				mazeArray[14][j] = "0";
				mazeArray[j][14] = "0";
			}
		}
		for ( int i = 6; i < 13; i++ )
		{
			// mazeArray[13][i] = ".";
			// mazeArray[i - 4][1] = ".";
		}
	} // End of setupGame

	/*********************************************
	 * gameOverCheck outputs nothing this method checks if the player has reached the end of the maze
	 * 
	 * Parameters: None
	 * 
	 * Returns playing variable which is true until the player has hit the end goal, then playing is set to false
	 */
	public Boolean gameOverCheck( )
	{
		Boolean playing = true;
		int count = 0;
		for ( int i = 0; i < mazeArray.length; i++ )
		{
			for ( int j = 0; j < mazeArray.length; j++ )
			{
				if ( mazeArray[i][j] == "2" )
				{
					count = count + 1;
				}
			}
		}
		if ( count < 1 )
		{
			playing = false;
		}
		return playing;
	} // End of gameOverCheck

}
