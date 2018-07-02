import java.util.*;

public class TheRoundCityDiv2 {

	public int find(int r)
	{
		int counter=0;
		for(int x=-r; x<=r; x++)
			for(int y=-r; y<=r; y++)
			{
				if(x == 0 && y == 0) continue;

				if( Math.sqrt(x*x+y*y) <= ((double)r))
					counter++;
			}
		return counter;
	}
}