package it.polimi.application.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plutoeditor.model.classes.Mission;
<imp>

public class MyTemplate {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<Mission> missions;
		// declaration placeholder
		<dec>

		System.out.print("Enter number of missions:");
		int number = scanner.nextInt();

		missions = new ArrayList<Mission>();
		for( int n=0; n<number; n++){
			missions.add(new Mission());
		}
		
		System.out.println("Start");

		// usare i thread per avere esecuzioni parallele
		for (int i = 0; i < missions.size(); i++) {
			System.out
					.println("Mission" + missions.get(i).getId() + " started");
			// execution placeholder
			<exe>
		}

		System.out.println("End");

		scanner.close();
	}
}
