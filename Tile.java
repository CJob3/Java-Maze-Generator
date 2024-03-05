
public class Tile
{
	int tileX = 1;
	int tileY = 1;
	Boolean visited = false;

	public Boolean getVisited( )
	{
		return visited;
	}

	public void setVisited( Boolean visited )
	{
		this.visited = visited;
	}

	public Tile(int y, int x)
	{
		tileX = x;
		tileY = y;
	}

	public int getTileX( )
	{
		return tileX;
	}

	public void setTileX( int tileX )
	{
		this.tileX = tileX;
	}

	public int getTileY( )
	{
		return tileY;
	}

	public void setTileY( int tileY )
	{
		this.tileY = tileY;
	}

}
