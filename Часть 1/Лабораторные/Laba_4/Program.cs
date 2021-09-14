using System;
using System.Linq;

namespace Laba_4
{
	class Program
	{
		static void Main(string[] args)
		{
			var buses = BusDat.FakeBuses(10).ToList();
			var busDat = new BusDat { Buses = buses };
			Console.WriteLine(string.Join('\n', buses));

			Console.WriteLine($"Количество автобусов на маршруте:\n{busDat.CountsBusOnRoute}\n");

			Console.WriteLine($"Cписок маршрутов, которые обслуживает автобаза с указанным номером \"{buses.First().AutoBaseNumber}\":" +
				$"\n{string.Join('\n', busDat.GetRoutesByAutoBaseNumber(buses.First().AutoBaseNumber))}\n");

			Console.WriteLine($"Выдать список номеров автобусов, маршруты которых проходят через заданный пункт \"{buses.First().Route}\":" +
				$"\n{string.Join('\n', busDat.GetBusNumberByRoute(buses.First().Route))}\n");

			Console.WriteLine($"По номеру автобуса определить его маршрут \"{buses.Last().Number}\":" +
				$"\n{busDat.GetRouteByBusNumber(buses.Last().Number)}\n");

			Console.WriteLine($"Определить общее число городских автобусов:" +
			$"\n{busDat.GetCountCityBus()}\n");

			busDat.Save();
			busDat.PrintDatFile();
		}
	}
}
