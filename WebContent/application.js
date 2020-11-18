let mId = 'tdy365y4';

function findUserInformation(id){
	document.getElementById(id).disabled = true;
	let applicationIdData = document.getElementById(id).value;
	console.log(applicationIdData );
	sessionStorage.setItem("managerId", mId);
	
	getRequest(GET_LOAN_APP + applicationIdData,  (error,result)=>{
		if( error ){
			information.innerText = "Application Id doest Not exist" 
		}else {
			console.log(result);
			displayData(result);
		}
	});	
}

function displayData(value){
	
	let applicationId = constructApplicationInfo(value);
	
	let button = createButton('Click Here for More Info');
	
	let container = document.getElementById('moreInfo');
	container.appendChild(button);
	
	button.onclick = function() {
		getRequest( GET_CUST_AID + applicationId,(error,result)=>{
			if( error ){
				console.log(error);
			}
			else{
				constructCustomerInfo(result);
				container.removeChild(button);
				status(container, applicationId)
			}
		});
	};
}

function status(container, applicationId){
	let approve = createButton("Approve");
	let disapprove = createButton("Disapprove");
	
	container.appendChild(approve);
	container.appendChild(disapprove);
	
	approve.onclick = function(){
		console.log('value');
		let text;
		let managerId = prompt("Please enter managerId: ","ManagerId");
		if ( (managerId != null || managerId != "") && managerId == 'tdy365y4' ) {
		    console.log( "Loan Approved" );
		    postRequest( APPROVE_LOAN, JSON.stringify(new approveLoan(applicationId, 3)), (error,result)=>{
		    	if( error ){
		    		console.log(error);
		    	}else{
		    		console.log("Loan Approved successfully");
		    	}
		    });
		    disapprove.disabled = true;
		}else{
			alert("Inavlid Manager Id");
		}
	};
	
	disapprove.onclick = function(){
		console.log('value');
		let text;
		let managerId = prompt("Please enter managerId: ","ManagerId");
		if ( (managerId != null || managerId != "") && managerId == 'tdy365y4' ) {
		    postRequest( APPROVE_LOAN , JSON.stringify(new approveLoan(applicationId, 2)), (error,result)=>{
		    	if( error ){
		    		console.log(error);
		    	}else{
		    		console.log("Loan Disapproved successfully");
		    	}
		    });
		    approve.disabled = true;
		}else{
			alert("Inavlid Manager Id");
		}
	};
}

function approveLoan( applicationId, status ){
	this.applicationId = applicationId,
	this.status = status
}

function createButton(value){
	let button = document.createElement('button');
	button.type = 'button';
	button.innerHTML = value;
	return button;
}

function constructCustomerInfo( value){
	let displayMoreInfo = document.getElementById("displayUserInformation");
	let customerData = JSON.parse(value);
	let result = "CustomerId: " + customerData.customerId
	+"\nName: " + customerData.name
	+"\nAddress: " + customerData.address
	+"\nContact Detail: " + customerData.contactDetails
	+"\nEmail: " + customerData.email
	+"\nAge: " + customerData.age
	+"\nGender: " + customerData.gender;
	
	displayMoreInfo.innerText = result;
	
	
}

function constructApplicationInfo( value){
	let information = document.getElementById("displayInformation");
	let applicationData = JSON.parse(value);
	let result = "ApplicationId: " + applicationData.applicationId 
				+"\nCustomerId: "+ applicationData.customerId 
				+"\nLoanId: " + applicationData.loanId
				+"\nAmount Sanctioned: " + applicationData.amount
				+"\nApplied Date: " + applicationData.appliedDate;
	
	if( applicationData.applicationStatus == 1 ){
		result += "\nApplication Status: Processing"
	}else if( applicationData.applicationStatus == 2 ){
		result += "\nApplication Rejected";
	}
	else if( applicationData.applicationStatus == 3 ){
		result += "\nApproved Date: " + applicationData.approvedDate;
	}
	information.innerText = result;
	return applicationData.applicationId

}

function getMoreInformation(){
	let information = document.getElementById("displayFurtherMoreInfo");
	let applicationId = document.getElementById(id).value;
	
}