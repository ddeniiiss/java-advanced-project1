package lesson10.main;

import lesson10.domain.User;
import lesson10.service.UserService;
import lesson10.service.impl.UserServiceImpl;

public class Main {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		userService.create(new User("peakyblinders@gmail.com", "Tommy", "Shelby", "Family Guy", "byorder"));
		userService.create(new User("supernatural@gmail.com", "Dean", "Winchester", "Hunter", "pudding"));
		userService.readAll().stream().forEach(System.out::println);
		userService.delete(1);
		User userById = userService.read(2);
		System.out.println();
		System.out.println(userById);
	}
}
