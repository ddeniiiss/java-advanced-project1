$('.message a').click(function() {
	loginRegisterSwitch();
	$(".alert").hide();
});

function showAlertAfterRegistration() {
	$('div.alert.alert-success').show();
};

function showAlertDanger(message) {
	$('div.alert.alert-danger').html(`
		<b>Failed!</b> ${message}
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	`).show();
}

function loginRegisterSwitch() {
	$('form').animate({ height: "toggle", opacity: "toggle" }, "slow");
};

$("button.register").click(function() {
	var firstName = $("form.register-form input.firstName").val();
	var lastName = $("form.register-form input.lastName").val();
	var email = $("form.register-form input.email").val();
	var password = $("form.register-form input.password").val();
	var confirmPassword = $("form.register-form input.confirmPassword").val();

	if (firstName == '' || lastName == '' || email == '' || password == '' || confirmPassword == '') {
		showAlertDanger("Please fill all the fields.");
	} else if ((password.length) < 8) {
		showAlertDanger("Password should have at least 8 characters.");
	} else if (!(password).match(confirmPassword)) {
		showAlertDanger("Your passwords don't match. Try again");
	} else {

		var userRegister = {
			firstName: firstName,
			lastName: lastName,
			email: email,
			password: password
		};

		$.post("register", userRegister, function(data) {
			if (data == 'Success') {
				$("form")[0].reset();
				$("form")[1].reset();
				loginRegisterSwitch();
				showAlertAfterRegistration();
			} else if(data == 'Failed'){
				showAlertDanger("The user with this email already exists.");
				$("form")[0].reset();
			}
		});
	}
});

$("button.login").click(function() {
	var email = $("form.login-form input.email").val();
	var password = $("form.login-form input.password").val();

	if (email == "" || password == "") {
		showAlertDanger("Please fill all the fields.");
	} else {
		var userLogin = {
			email: email,
			password: password
		};

		$.post("login", userLogin, function(data) {
			 if (data == "Success") { 
				window.location.href = "cabinet.jsp"; 
			} else if(data == 'Failed'){
				showAlertDanger("Incorrect email or password. Please try again.");
				$("form")[1].reset();
			}
		 });
		
	}
});