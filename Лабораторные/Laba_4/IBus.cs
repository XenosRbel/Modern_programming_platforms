namespace Laba_4
{
	public interface IBus
	{
		int AutoBaseNumber { get; set; }
		int Number { get; set; }
		IRoute Route { get; set; }
		string RouteEnd { get; set; }
		string RouteStart { get; set; }
		BusType Type { get; set; }
	}
}
