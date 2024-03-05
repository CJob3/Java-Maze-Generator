
public class Character
{

	String[ ][ ] playerPosition = new String[15][15]; // array of dots to move player

	int playerX = 1;
	int playerY = 1;

	public int getPlayerX( )
	{
		return playerX;
	}

	public void setPlayerX( int playerX )
	{
		this.playerX = playerX;
	}

	public int getPlayerY( )
	{
		return playerY;
	}

	public void setPlayerY( int playerY )
	{
		this.playerY = playerY;
	}

	public Character()
	{
		for ( int i = 0; i < playerPosition.length; i++ )
		{
			for ( int j = 0; j < playerPosition.length; j++ )
			{
				playerPosition[i][j] = "1";
			}
		}

	}

}
