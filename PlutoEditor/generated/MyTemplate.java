package it.polimi.application.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plutoeditor.model.classes.Mission;

public class MyTemplate {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<Mission> missions;

		System.out.print("Enter number of missions:");
		int number = scanner.nextInt();
		System.out.println("You just entered:" + number);

		System.out.println("Start");

		missions = new ArrayList<Mission>();
		for( int n=0; n<number; n++){
			missions.add(new Mission());
		}

		for (int i = 0; i < missions.size(); i++) {
			System.out
					.println("Mission" + missions.get(i).getId() + " started");
		}

		System.out.println("End");

		scanner.close();
	}
}
