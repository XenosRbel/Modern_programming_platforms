using System;
using System.Collections.Generic;
using System.Linq;

namespace Laba_1
{
	class Program
	{
		static void Main()
		{
			var groups = new LinkedList<Group>();

			for (int i = 0; i < 5; i++)
			{
				groups.AddLast(BuildGroup());
			}

			groups.AddFirst(new Group(new List<string> { "Student 1", "Student 2" }));

			foreach (var item in groups)
			{
				Console.WriteLine("===== Group =====");
				Console.WriteLine(item);
				Console.WriteLine("===== END =====");
			}
		}

		static Group BuildGroup()
		{
			var rnd = new Random();
			var fakeStudents = Enumerable.Range(0, rnd.Next(1, 10)).Select(item => Faker.NameFaker.FirstName()).ToList();

			return new Group(fakeStudents);
		}
	}
}
