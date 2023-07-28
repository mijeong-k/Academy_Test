package p230608_2;

public class MemberVo {
	private String empno;
	private String ename;
	private int sal;
	private int comm;
	private int realsal;
	
	public MemberVo() {
		
	}
	
	public MemberVo(String empno, String ename, int sal, int comm, int realsal) {
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
		this.comm = comm;
		this.realsal = realsal;
	}
	
	
	public String getEname() {
		return ename;
	}

	public int getSal() {
		return sal;
	}

	public String getEmpno() {
		return empno;
	}
	
	public int getComm() {
		return comm;
	}	
	
	public int getReal() {
		return realsal;
	}	
}
