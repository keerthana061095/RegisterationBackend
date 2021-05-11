package com.RegisterationAndLogin.response;

public class RegisterResponse {
	
	    private int id;
	
	    private String firstName;
		
		private String lastName;
		
		private String Weight;
		
		private String Height;

		private String country;

		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getWeight() {
			return Weight;
		}

		public void setWeight(String weight) {
			Weight = weight;
		}

		public String getHeight() {
			return Height;
		}

		public void setHeight(String height) {
			Height = height;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
		
		
}
