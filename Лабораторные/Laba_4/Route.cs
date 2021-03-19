using System;

namespace Laba_4
{
	internal class Route : IRoute
	{
		public int Number { get; set; }

		public override string ToString()
		{
			return $"{Number}";
		}

		public static Route Fake()
		{
			var rnd = new Random();
			return new Route { Number = rnd.Next(10) };
		}
	}
}
