package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import utils.Gender;
import utils.Neighberhood;

public class Customer extends Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private Neighberhood neighberhood;
	private boolean isSensitiveToLactose;
	private boolean isSensitiveToGluten;
	private long idNumber;
	private ArrayList<Dish> dishes=new ArrayList<Dish>();
	
	

	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			utils.Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood, boolean isSensitiveToLactose, boolean isSensitiveToGluten, long idNumber) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
		this.idNumber = idNumber;
	}

	public void setDishes(ArrayList<Dish> dishes) {
		this.dishes = dishes;
	}

	public ArrayList<Dish> getDishes() {
		return dishes;
	}



	public Customer(int id) {
		super(id);
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Customer.idCounter = idCounter;
	}

	public Neighberhood getNeighberhood() {
		return neighberhood;
	}

	public void setNeighberhood(Neighberhood neighberhood) {
		this.neighberhood = neighberhood;
	}

	public boolean isSensitiveToLactose() {
		return isSensitiveToLactose;
	}

	public void setSensitiveToLactose(boolean isSensitiveToLactose) {
		this.isSensitiveToLactose = isSensitiveToLactose;
	}

	public boolean isSensitiveToGluten() {
		return isSensitiveToGluten;
	}

	public void setSensitiveToGluten(boolean isSensitiveToGluten) {
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	public Long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}

	@Override
	public String toString() {
		return getIdNumber()+" ,"+getFirstName()+" "+getLastName()+", Lactose= "+isSensitiveToLactose+", Gluten= "+isSensitiveToGluten;
	}
	
	protected Object readResolve() {
		if(this.getId() == idCounter) {
			idCounter = this.getId() +1;
		}
		return this;
	}
}
