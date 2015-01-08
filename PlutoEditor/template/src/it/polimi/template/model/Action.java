package it.polimi.template.model;

import it.polimi.template.model.interfaces.IAction;

public enum Action implements IAction{

	TAKE_PHOTO{
		 @Override
         public void doAction() {
			// TODO Auto-generated method stub 
			 try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
		 
		 @Override
		public String toString() {
			return "Take photo";
		}

	}, MEASURE{

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		@Override
		public String toString() {
			return "Measure";
		}
		
	}, PICK_ITEM{

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		@Override
		public String toString() {
			return "Pick item";
		}
		
	}, RELEASE_ITEM{

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		@Override
		public String toString() {
			return "Release item";
		}
		
	};
	
}
