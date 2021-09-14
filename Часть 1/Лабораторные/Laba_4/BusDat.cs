using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Laba_4
{
	internal class BusDat : IBusDat
	{
		public const string BusDatFilePath = "bus_dat.json";

		public List<IBus> Buses { get; set; }
		public string CountsBusOnRoute { get => CountBusOnRoute(); }

		public int GetCountCityBus()
		{
			return Buses.Count(bus => bus.Type == BusType.City);
		}

		public IRoute GetRouteByBusNumber(int busNumber)
		{
			var bus = Buses.First(bus => bus.Number == busNumber);
			return bus.Route;
		}

		public IEnumerable<int> GetBusNumberByRoute(IRoute route)
		{
			return Buses.Where(bus => bus.Route == route).Select(bus => bus.Number);
		}

		public IEnumerable<IRoute> GetRoutesByAutoBaseNumber(int autoBaseNumber)
		{
			return Buses.Where(bus => bus.AutoBaseNumber == autoBaseNumber).Select(bus => bus.Route);
		}

		public static IList<IBus> FakeBuses(byte count)
		{
			return Enumerable.Range(0, count).Select(bus => Bus.Fake()).ToList();
		}

		private string CountBusOnRoute()
		{
			var result = string.Empty;
			var query = Buses.GroupBy(bus => bus.Route.Number, bus => bus);

			foreach (var busgroup in query)
			{
				int busCountInGroup = busgroup.Count();
				result += $"{busgroup.Key}:{busCountInGroup}\n";
				Console.WriteLine($"Номер маршрута:\t{busgroup.Key}\tКоличество автобусов на маршруте:\t{busCountInGroup}");
			}

			return result;
		}
	}
}
