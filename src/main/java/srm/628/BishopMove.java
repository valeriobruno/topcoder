
public class BishopMove {

	public int howManyMoves(int r1, int c1, int r2, int c2)
	{
		if(checkForZeroMove(r1,c1,r2,c2))
		{
			return 0;
		}
		
		if(checkForOneMove(r1,c1,r2,c2))
		{
			return 1;
		}
		
		if( ! isTheSameColor(r1,c1,r2,c2))
		{
			return -1;
		}
		
		
		return 2;
	}

	private boolean isTheSameColor(int r1, int c1, int r2, int c2) {
		int steps = Math.abs(r1 + r2) + Math.abs(c2+c1);
		return steps % 2 == 0;
	}

	private boolean checkForOneMove(int r1, int c1, int r2, int c2) {
		 int r = r2 - r1;
		 int c = c2 - c1;
		return Math.abs(r) == Math.abs(c);
	}

	private boolean checkForZeroMove(int r1, int c1, int r2, int c2) {
		return r1 == r2 && c1 == c2;
	}
}
