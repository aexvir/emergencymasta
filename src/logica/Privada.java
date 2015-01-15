package logica;

	public class Privada extends Ambulancia
	{
		private String comp;

		public Privada(){super(); comp = null;}
		public Privada(String c, String nr, int t, int lt, int ln, String eq, int disp){super(nr, t, lt, ln, eq, disp); comp = c;}

		public void setComp(String c){comp = c;}
		public String getComp(){return comp;}
	}
