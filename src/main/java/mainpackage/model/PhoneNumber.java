package mainpackage.model;

public class PhoneNumber {
	private String number;
	private Program program;
	
	public PhoneNumber(String number, Program program){
		this.number = number;
		this.program = program;
	}
	
	public PhoneNumber() {
		
	}

	public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Program getProgram() {
        return this.program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
