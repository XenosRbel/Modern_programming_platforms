using Newtonsoft.Json;
using System;
using System.IO;

namespace Laba_4
{
	public static class Helper
	{
		public static string ToJson(this IBus model)
		{
			return JsonConvert.SerializeObject(model);
		}


		public static void Save(this IBusDat busDat)
		{
			var serializeData = JsonConvert.SerializeObject(busDat, Formatting.Indented);
			File.WriteAllText(BusDat.BusDatFilePath, serializeData);
			Console.WriteLine("Успешно сохранено");
		}

		public static void PrintDatFile(this IBusDat busDat)
		{
			var fileData = File.ReadAllText(BusDat.BusDatFilePath);
			Console.WriteLine(fileData);
		}
	}
}
