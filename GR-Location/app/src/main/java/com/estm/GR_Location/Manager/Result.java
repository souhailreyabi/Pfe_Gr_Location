package com.estm.GR_Location.Manager;

import java.util.List;

public class Result {
	
	    private Autocar autocar ;
	    
	    private List<Pause> pauses ;
	    
	    private Societe societe ;
	    
	    private Trajet trajet ;

		public Result(Autocar autocar, List<Pause> pauses, Societe societe, Trajet trajet
				) {
			super();
			this.autocar = autocar;
			this.pauses = pauses;
			this.societe = societe;
			this.trajet = trajet;

		}

		public Autocar getAutocar() {
			return autocar;
		}

		public void setAutocar(Autocar autocar) {
			this.autocar = autocar;
		}

		public List<Pause> getpauses() {
			return pauses;
		}

		public void setpauses(List<Pause> pauses) {
			this.pauses = pauses;
		}

		public Societe getSociete() {
			return societe;
		}

		public void setSociete(Societe societe) {
			this.societe = societe;
		}

		public Trajet getTrajet() {
			return trajet;
		}

		public void setTrajet(Trajet trajet) {
			this.trajet = trajet;
		}



		@Override
		public String toString() {
			return "Result [autocar=" + autocar + ", pauses=" + pauses + ", societe=" + societe + ", trajet=" + trajet
					+ "]";
		}

		
	    
	    
	    

}
