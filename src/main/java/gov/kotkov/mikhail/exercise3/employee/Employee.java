package gov.kotkov.mikhail.exercise3.employee;

import java.math.BigDecimal;
import java.math.RoundingMode;

import gov.kotkov.mikhail.exercise3.util.Job;

public abstract class Employee {

	private final String firstName, lastName;
	
	private final Job job;
	//wage for standard hours worked
	private BigDecimal wageRate;
	
	//actual hours worked
	private int actualWorkhours;
	
	//standard amount of hours employee has to work 
	private static int standardWorkhours;
	
	public Employee(String firstName, String lastName, Job job, BigDecimal wageRate) {
		validateName(firstName, lastName);
		this.job = job;
		this.firstName = firstName;
		this.lastName = lastName;
		this.wageRate = wageRate.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getWageRate() {
		return wageRate;
	};
	
	public void setWageRate(BigDecimal wageRate) {
		if(wageRate.signum() == -1) {
			throw new IllegalArgumentException("wage rate can't be negative");
		}
		this.wageRate = wageRate.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void setActualWorkhours(int actualWorkhours) {
		if(actualWorkhours < 1) {
			throw new IllegalArgumentException("actual workhours can't be negative");
		}
		this.actualWorkhours = actualWorkhours;
	}

	public static void setStandardWorkhours(int standardWorkhours) {
		if(standardWorkhours < 1) {
			throw new IllegalArgumentException("standard workhours can't be negative");
		}
		Employee.standardWorkhours = standardWorkhours;
	}
	
	public double getWorkhoursPercentage() {
		return ((double)actualWorkhours/standardWorkhours) * 100;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", job=" + job
				+ ", wageRate=" + wageRate + ", actualWorkhours=" + actualWorkhours + "]";
	}
	
	private void validateName(String firstName, String lastName) {
		if(firstName.isEmpty() || lastName.isEmpty()) {
			throw new IllegalArgumentException("Employee must have first and last names");
		} else if(!firstName.chars().allMatch(Character::isLetter) || !lastName.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException("Name must consist of alphabetic characters");
		}
	}

	public Job getJob() {
		return job;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getActualWorkhours() {
		return actualWorkhours;
	}

	public static int getStandardWorkhours() {
		return standardWorkhours;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (job != other.job)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

}
