package it.polimi.template.model;

import it.polimi.template.model.interfaces.IAction;

public enum Action implements IAction {

	TAKE_PHOTO {
		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return "Take photo";
		}

		@Override
		public boolean isItemRequired() {
			return false;
		}

	},
	MEASURE {

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return "Measure";
		}

		@Override
		public boolean isItemRequired() {
			return false;
		}

	},
	PICK_ITEM {

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return "Pick item";
		}

		@Override
		public boolean isItemRequired() {
			return true;
		}

	},
	RELEASE_ITEM {

		@Override
		public void doAction() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return "Release item";
		}

		@Override
		public boolean isItemRequired() {
			return true;
		}

	};

}
