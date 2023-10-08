package tn.esprit.CROTUN.Entities;



public class PaymentIntentDto {
	 public enum Currency{
	        usd, eur;
	    }

	    private String description;
	     int amount;
	     Currency currency;


	    public String getDescription() {
	        return "aaa";
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getAmount() {
	        return amount;
	    }

	    public void setAmount(int amount) {
	        this.amount = amount;
	    }

	    public Currency getCurrency() {
	        return currency;
	    }

	    public void setCurrency(Currency currency) {
	        this.currency = currency;
	    }

		public PaymentIntentDto(String description, int amount, Currency currency) {
			super();
			this.description = description;
			this.amount = amount;
			this.currency = currency;
		}

		public PaymentIntentDto() {
			super();
		}


}
