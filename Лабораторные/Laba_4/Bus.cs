using System;

namespace Laba_4
{
	internal class Bus : IBus
	{
		public int Number { get; set; }
		public IRoute Route { get; set; }
		public string RouteStart { get; set; }
		public string RouteEnd { get; set; }
		public BusType Type { get; set; }
		public int AutoBaseNumber { get; set; }

		public override string ToString()
		{
			return $"Номер автобуса:\t{Number}\n" +
				$"Маршрут:\t{Route}\n" +
				$"Начало маршрута:{RouteStart}\nКонец маршрута:\t{RouteEnd}\n" +
				$"Тип автобуса:\t{Type}\n" +
				$"Номер автобазы:\t{AutoBaseNumber}\n";
		}

		public static IBus Fake()
		{
			var rnd = new Random();
			return new Bus
			{
				AutoBaseNumber = Faker.NumberFaker.Number(),
				Type = (BusType)rnd.Next((int)BusType.Country + 1),
				RouteEnd = Faker.LocationFaker.Street(),
				RouteStart = Faker.LocationFaker.Street(),
				Route = Laba_4.Route.Fake(),
				Number = Faker.NumberFaker.Number()
			};
		}
	}
}
