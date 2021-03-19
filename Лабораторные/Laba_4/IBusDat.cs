using System.Collections.Generic;

namespace Laba_4
{
	public interface IBusDat
	{
		List<IBus> Buses { get; set; }
		string CountsBusOnRoute { get; }

		IEnumerable<int> GetBusNumberByRoute(IRoute route);
		int GetCountCityBus();
		IRoute GetRouteByBusNumber(int busNumber);
		IEnumerable<IRoute> GetRoutesByAutoBaseNumber(int autoBaseNumber);
	}
}
