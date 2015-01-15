package logica;

public class BHospital extends Ambulancia
{
	private Hospital hospitalAsignado;

	public BHospital(){
		super();
		hospitalAsignado = null;
	}

	public BHospital(Hospital ha, String nr, int t, float lt, float ln, String eq, int disp){
		super(nr, t, lt, ln, eq, disp);
		hospitalAsignado = ha;
	}

	public Hospital getHospitalAsignado(){return this.hospitalAsignado;}
	public void setHospitalAsignado(Hospital h){this.hospitalAsignado = h;}

}