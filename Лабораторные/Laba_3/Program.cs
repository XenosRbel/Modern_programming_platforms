using System;

namespace Laba_3
{
	class Program
	{
        private const int N = 4;

		static void Main(string[] args)
		{
			var A = Helper.FillRandomAdjacencyMatrix(N, N);
			var vMin = 0;
			var TS = int.MaxValue;

			A.Print();

			// Алгоритм Флойда-Уоршелла
			for (int k = 0; k < N; ++k)
				for (int i = 0; i < N; ++i)
					for (int j = 0; j < N; ++j)
						A[i,j] = Math.Min(A[i,j], A[i,k] + A[k,j]);

			// Ищем вершину с минимальной суммой
			for (int i = 0; i < N; ++i)
			{
				var S = 0;
				for (int j = 0; j < N; ++j)
					S += A[i,j];
				if (S < TS)
				{
					TS = S;
					vMin = i;
				}
			}

			Console.WriteLine($"вывод города с минимальным расстоянием (нумерация с 0)\n{vMin}");
		}
	}
}
