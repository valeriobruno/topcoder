import java.util.*;

public class TheSquareCityDiv2{

	public int[] find(int r, int[] t)
	{
		int results[] =new int[2];
		results[1] = 1;

		int n = (int) Math.sqrt(t.length);

		HashMap<House,House> warmestHouse = new HashMap<>();
		HashMap<House,Integer> inhabitants = new HashMap<>();

		for(int x=0; x<n; x++)
		{
			for(int y=0;y<n;y++)
			{
				int wx=x;
				int wy=y;

				for(int i=-r;i<=r;i++)
				{
					for(int j=-r;j<=r;j++)
					{
						int a = x+i;
						int b = y+j;
					//System.out.println(x+","+y+" : "+a+","+b);

						if(a >=0 && a<n && b>=0 && b<n && (Math.abs(x-a) + Math.abs(y-b)) <= r && t[a*n+b] > t[wx*n+wy] )
						{
							wx = a;
							wy = b;
						}

					}
				}
				House house = new House(x,y);
				//System.out.println("Warmest house for "+house+" is "+ new House(wx,wy));
				warmestHouse.put(house,new House(wx,wy));
				inhabitants.put(house,1);
			}
		}

		int moves = 1;

		while(moves>0)
		{
			moves=0;

			Set<House> housesWithPeople = new HashSet<>(inhabitants.keySet());

			for(House h : housesWithPeople)
			{
				House nextHouse = warmestHouse.get(h);

				if(nextHouse.equals(h))
					continue;
				else
				{
					inhabitants.put(nextHouse, inhabitants.getOrDefault(nextHouse,0)+ inhabitants.get(h) );
					inhabitants.remove(h);
					moves++;

					if(results[1] < inhabitants.get(nextHouse))
						results[1] = inhabitants.get(nextHouse);
				}

			}
		//System.out.println(inhabitants);
		}

		results[0] = inhabitants.size();

		return results;
	}

	class House
	{
		int x,y;
		public House(int x,int y)
		{
			this.x = x;
			this.y = y;
		}

		public int hashCode()
		{
			return 17*x+31*y;
		}
		public boolean equals(Object o)
		{
			if(o != null && o instanceof House)
			{
				House other = (House) o;
				return this.x == other.x && this.y == other.y;
			}
			return false;
		}

		public String toString()
		{
			return x+","+y;
		}
	}
}