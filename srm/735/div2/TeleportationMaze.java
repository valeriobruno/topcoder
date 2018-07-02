import java.util.*;
import java.util.stream.*;

public class TeleportationMaze
{

	private String[] maze;

	public int pathLength(String[] a, int r1, int c1, int r2, int c2)
	{
		this.maze =a;

		PriorityQueue<Position> stack = new PriorityQueue<>();
		HashSet<Position> visitedPositions = new HashSet<>();
		stack.add(new Position(c1,r1,0));

		while(!stack.isEmpty())
		{
			Position currentPos = stack.remove();

			if( currentPos.x == c2 && currentPos.y == r2)
				return currentPos.cost;
			//System.out.println("pick "+currentPos);

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
		Position walkPos = new Position(pos.x-1,pos.y,pos.cost+1);

		if(valid(walkPos))
			moves.add(walkPos);
		else{

			Position teleport;

			for(int x = pos.x-2; x>=0; x--)
			{
				teleport = new Position(x,pos.y,pos.cost+2);
				if(valid(teleport))
				{
					moves.add(teleport);
					break;
				}
			}
		}
		return moves;
	}

	private Collection<Position> findRightMoves(Position pos)
	{
		List<Position> moves = new ArrayList<>(2);
		Position walkPos = new Position(pos.x+1,pos.y,pos.cost+1);

		if(valid(walkPos))
			moves.add(walkPos);
		else{

			Position teleport;

			for(int x = pos.x+2; x< maze[0].length(); x++)
			{
				teleport = new Position(x,pos.y,pos.cost+2);
				if(valid(teleport))
				{
					moves.add(teleport);
					break;
				}
			}
		}
		return moves;
	}

	private Collection<Position> findUpMoves(Position pos)
	{
		List<Position> moves = new ArrayList<>(2);
		Position walkPos = new Position(pos.x,pos.y-1,pos.cost+1);

		if(valid(walkPos))
			moves.add(walkPos);
		else{

			Position teleport;

			for(int y = pos.y-2; y>=0; y--)
			{
				teleport = new Position(pos.x,y,pos.cost+2);
				if(valid(teleport))
				{
					moves.add(teleport);
					break;
				}
			}
		}

		return moves;
	}

	private Collection<Position> findDownMoves(Position pos)
	{
		List<Position> moves = new ArrayList<>(2);
		Position walkPos = new Position(pos.x,pos.y+1,pos.cost+1);

		if(valid(walkPos))
			moves.add(walkPos);
		else{

			Position teleport;

			for(int y = pos.y+2; y< maze.length; y++)
			{
				teleport = new Position(pos.x,y,pos.cost+2);
				if(valid(teleport))
				{
					moves.add(teleport);
					break;
				}
			}
		}
		return moves;
	}
	boolean valid(Position pos)
	{
		int xmax = maze[0].length();
		int ymax = maze.length;
		return  pos.y >=0 && pos.y < ymax && pos.x >=0 && pos.x < xmax &&  maze[pos.y].charAt(pos.x)== '.';
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
			return x+y;
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