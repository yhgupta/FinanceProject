let customerId;

(function(){
	const queryString = window.location.search;	
	const urlParams = new URLSearchParams(queryString);
	const customer = urlParams.get('customerId')
	customerId = customer;
})();

function userInfo(customerId,name,email,contactDetails,address,gender,age,bankName,bankBranch,accountNumber,ifscCode){
	this.customerId = customerId;
	this.name = name;
	this.email = email;
	this.contactDetails = contactDetails;
	this.address = address;
	this.gender = gender;
	this.age = age;
	this.bankName = bankName;
	this.bankBranch = bankBranch;
	this.accountNumber = accountNumber;
	this.ifscCode = ifscCode;
}

function sendDataUser(){
	console.log(customerId);
	let name = document.getElementById("name").value;
	let email = document.getElementById("email").value;
	let contactDetails = document.getElementById("contactDetails").value;
	let address = document.getElementById("address").value;
	let genderValue = document.getElementById("gender").value;
	let gender ;
	if(document.getElementById("m").checked ){
		gender = "M";
	}else{
		gender = "F";
	}
	console.log(gender);
	let age = document.getElementById("age").value;
	let bankName = document.getElementById("bankName").value;
	let bankBranch = document.getElementById("bankBranch").value;
	let accountNumber = document.getElementById("accountNumber").value;
	let ifscCode = document.getElementById("ifscCode").value;
	
	
	let customerData = new userInfo(customerId,name, email, contactDetails, address, gender, age, bankName, bankBranch, accountNumber, ifscCode)
	console.log( JSON.stringify(customerData) );
	
	postRequest(ADD_COMPLETE_USER , JSON.stringify(customerData), (error,result)=>{
		if(error ){
			console.log(error);
		}
		else{
			console.log(result);
			window.location = LOAN_APPLICATION + customerId;
		}
	});
	
}


function createButtonField(value){
	let button = document.createElement('button');
	button.type = 'button';
	button.innerHTML = value;
	return button;
}