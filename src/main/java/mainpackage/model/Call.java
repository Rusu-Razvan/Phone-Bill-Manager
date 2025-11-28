package mainpackage.model;

import java.time.LocalDateTime;

public class Call {
    private double duration; // Duration in seconds
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Call(int duration, LocalDateTime startTime, LocalDateTime endTime) {
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Call() {
				
		this.duration = 0;
		this.startTime = LocalDateTime.now();
		this.endTime = LocalDateTime.now();
	}
	

	
    public double getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    
    public void setDuration(double d) {
        this.duration = d;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

	public void setId(int int1) {
		
		
	}

	public void setPhoneNumber(String string) {
		
		
	}
}
