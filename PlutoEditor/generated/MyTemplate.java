package it.polimi.application.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plutoeditor.model.classes.Mission;

public class MyTemplate {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<Mission> missions;
		// Fill this part with the blocks declaration
		<declaration></declaration>

		System.out.print("Enter number of missions:");
		int number = scanner.nextInt();

		missions = new ArrayList<Mission>();
		for( int n=0; n<number; n++){
			missions.add(new Mission());
		}
		
		System.out.println("Start");

		// usare un thread per ogni mission così vanno parallelamente
		for (int i = 0; i < missions.size(); i++) {
			
			// Fill this part with the ordered execution of run method of each block
			<execution></execution>
			
			System.out
					.println("Mission" + missions.get(i).getId() + " started");
		}

		System.out.println("End");

		scanner.close();
	}
}
