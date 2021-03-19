using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Laba_2
{
	class Program
	{
		private const string CALCULATOR_RESULT_FILE_PATH = "report.txt";
		private const string exitKey = "-q";

		static void Main(string[] args)
		{
			PrintMenu();

			var key = string.Empty;
			while (key != exitKey)
			{
				Console.WriteLine("Введите значение меню");
				key = Console.ReadLine().ToLower();

				switch (key)
				{
					case "calc":
						Calculate();
						break;
					case "print":
						PrintResult();
						break;
					default:
						PrintMenu();
						break;
				}
			}
		}

		private static void Calculate()
		{
			PrintCalcMenu();

			var key = string.Empty;
			var result = double.NaN;
			var logsTmp = new List<double>();

			while (key != exitKey)
			{
				Console.WriteLine("Введите значение меню");
				key = Console.ReadLine().ToLower();

				switch (key)
				{
					case "add":
						var expr = InputLog();
						logsTmp.Add(expr);
						break;
					case "op":
						if (logsTmp.Count > 2 || logsTmp.Count <= 1)
						{
							Console.WriteLine("Invalid data!");
							break;
						}

						Console.Write("Введите оператор:\t");
						var op = Console.ReadLine().ToLower();

						if (op.Length > 1)
						{
							Console.WriteLine("Invalid operator");
							break;
						}

						result = ExecuteWithOperator(logsTmp.First(), logsTmp.Last(), op);
						key = "-q";
						break;
					default:
						PrintCalcMenu();
						break;
				}
			}

			SaveResult(result.ToString());
		}

		private static double ExecuteWithOperator(double val1, double val2, string op)
		{
			var result = double.NaN;
			switch (op)
			{
				case "+":
					result = val1 + val2;
					break;
				case "-":
					result = val1 - val2;
					break;
				case "/":
					result = val1 / val2;
					break;
				case "*":
					result = val1 * val2;
					break;
				default:
					break;
			}

			return result;
		}

		private static double InputLog()
		{
			var logResult = double.NaN;

			Console.WriteLine("Форма записи: log число основание");
			Console.Write("Введите log:\t");
			var log = Console.ReadLine().ToLower().Split();

			if (log.Length == 2)
			{
				logResult = Math.Log(Convert.ToInt32(log[1]), 10);
			}
			else if (log.Length == 3)
			{
				logResult = Math.Log(Convert.ToInt32(log[1]), Convert.ToInt32(log[2]));
			}

			return logResult;
		}

		private static void PrintMenu()
		{
			Console.WriteLine("For enter log enter:\tcalc");
			Console.WriteLine("For print result enter:\tprint");
			Console.WriteLine("For exit program enter:\t-q");
		}

		private static void PrintCalcMenu()
		{
			Console.WriteLine("Для применения оператора [+ - * /] к логорифмам введите:\top");
			Console.WriteLine("Для ввода логорифма введите:\tadd");
			Console.WriteLine("For exit program enter:\t-q");
		}

		private static void SaveResult(string result)
		{
			File.WriteAllText(CALCULATOR_RESULT_FILE_PATH, result);
			Console.WriteLine("Successful saved");
		}

		private static void PrintResult()
		{
			var fileLines = File.ReadAllLines(CALCULATOR_RESULT_FILE_PATH);
			var result = string.Join(' ', fileLines);
			Console.WriteLine($"Result:\t{result}");
		}
	}
}
