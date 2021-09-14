using System.Collections.Generic;

namespace Laba_1
{
	internal class Group
	{
		private List<string> _students;

		public Group(List<string> students)
		{
			Students = students;
		}

		public List<string> Students { get => _students; set => _students = value; }

		public override string ToString()
		{
			return string.Join('\n', _students);
		}
	}
}
