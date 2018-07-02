import java.util.*;
import java.util.stream.*;

public class TeleportationMaze
{

	private String[] maze;
  private int goal_x;
  private int goal_y;

	public int pathLength(String[] a, int r1, int c1, int r2, int c2)
	{
		this.maze = a;
    this.goal_x = c2;
    this.goal_y = r2;

		PriorityQueue<Position> stack = new PriorityQueue<>();
		HashSet<Position> visitedPositions = new HashSet<>();
		stack.add(new Position(c1,r1,0));

		while(!stack.isEmpty())
		{
			Position currentPos = stack.remove();

			if( currentPos.x == c2 && currentPos.y == r2)
				return currentPos.cost;
			System.out.println("pick "+currentPos);

			visitedPositions.add(currentPos);

			//find left moves
			stack.addAll(findLeftMoves(currentPos).stream().filter( p -> !(visitedPositions.contains(p)) ).collect(Collectors.toList()) );
			//System.out.println("stack: "+stack);
			stack.addAll(findRightMoves(currentPos).stream().filter( p -> !(visitedPositions.contains(p) ) ).collect(Collectors.toList()) );
			//System.out.println("stack: "+stack);
			stack.addAll(findUpMoves(currentPos).stream().filter( p -> !(visitedPositions.contains(p) ) ).collect(Collectors.toList()) );
			//System.out.println("stack: "+stack);
			stack.addAll(findDownMoves(currentPos).stream().filter( p -> !(visitedPositions.contains(p)) ).collect(Collectors.toList()) );
			//System.out.println("stack: "+stack);

		}

		return -1;

	}

	private Collection<Position> findLeftMoves(Position pos)
	{
		List<Position> moves = new ArrayList<>(2);


		if(valid(pos.x-1,pos.y))
			moves.add(new Position(pos.x-1,pos.y,pos.cost+1));
		else{

			for(int x = pos.x-2; x>=0; x--)
			{
				if(valid(x,pos.y))
				{
					moves.add(new Position(x,pos.y,pos.cost+2));
					break;
				}
			}
		}
		return moves;
	}

	private Collection<Position> findRightMoves(Position pos)
	{
		List<Position> moves = new ArrayList<>(2);


		if(valid(pos.x+1,pos.y))
			moves.add(new Position(pos.x+1,pos.y,pos.cost+1));
		else{

			Position teleport;

			for(int x = pos.x+2; x< maze[0].length(); x++)
			{
				if(valid(x,pos.y))
				{
					moves.add(new Position(x,pos.y,pos.cost+2));
					break;
				}
			}
		}
		return moves;
	}

	private Collection<Position> findUpMoves(Position pos)
	{
		List<Position> moves = new ArrayList<>(2);
		if(valid(pos.x,pos.y-1))
			moves.add(new Position(pos.x,pos.y-1,pos.cost+1));
		else{

			Position teleport;

			for(int y = pos.y-2; y>=0; y--)
			{
				if(valid(pos.x,y))
				{
					moves.add(new Position(pos.x,y,pos.cost+2));
					break;
				}
			}
		}

		return moves;
	}

	private Collection<Position> findDownMoves(Position pos)
	{
		List<Position> moves = new ArrayList<>(2);

		if(valid(pos.x,pos.y+1))
			moves.add(new Position(pos.x,pos.y+1,pos.cost+1));
		else{

			Position teleport;

			for(int y = pos.y+2; y< maze.length; y++)
			{
        if(valid(pos.x,y))
				{
					moves.add(new Position(pos.x,y,pos.cost+2));
					break;
				}
			}
		}
		return moves;
	}
	boolean valid(int x, int y)
	{
		int xmax = maze[0].length();
		int ymax = maze.length;
		return  y >=0 && y < ymax && x >=0 && x < xmax &&  maze[y].charAt(x)== '.';
	}

	static class Position implements Comparable<Position>
	{
		int x, y, cost;

		public Position(int x, int y, int cost)
		{
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		public int hashCode()
		{
			return 31* x+ 31*y;
		}

		public boolean equals(Object other)
		{
			if(other !=null && other instanceof Position)
			{
				Position otherP = (Position) other;
				return this.x == otherP.x && this.y == otherP.y;
			}else
			return false;
		}

		public String toString()
		{
			return x+","+y+"("+cost+")";
		}

		public int compareTo(Position other)
		{
			return this.cost - other.cost;
		}
	}
}
