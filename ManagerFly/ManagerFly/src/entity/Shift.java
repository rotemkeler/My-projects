package entity;

import java.time.LocalDateTime;

public class Shift {
	private LocalDateTime start;
	private LocalDateTime end;
	
	
	public Shift(LocalDateTime start, LocalDateTime end) {
		super();
		this.start = start;
		this.end = end;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Shift [start=" + start + ", end=" + end + "]";
	}
	
	
}
