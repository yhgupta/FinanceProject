let imageD = [];
let customer ;

(function(){
	const queryString = window.location.search;	
	const urlParams = new URLSearchParams(queryString);
	const customerId = urlParams.get('customerId')
	customer = customerId;
})();


function previewImage(id){
	let img = document.getElementById("preview1");
	let file = document.getElementById(id).files[0];

	getImageData( file, (err,data)=>{
		if( err ){
			console.log("Error");
		}else{
			imageD.push(data);
		} 
	});
}

const getImageData = (file, callback) =>{
	const reader = new FileReader();
	reader.addEventListener("load", function() {
		callback(undefined ,reader.result);
	});
		
	if( file ){
		reader.readAsDataURL(file);
	}
}

function loanApplicationData(amount,repaymentTime,loanId){
	this.customerId = customer;
	this.amount = amount;
	this.repaymentTime = repaymentTime;
	this.loanId = loanId;
	this.image1 = imageD[0];
	this.image2 = imageD[1];
	this.image3 = imageD[2];
}

function displayR(){
	
	let amount = document.getElementById("amount").value;
	let repaymentTime = document.getElementById("repaymentTime").value;
	let loan = document.getElementById("loanId");
	let loanId = loan.options[loan.selectedIndex].id ;
	
	console.log( JSON.stringify(new loanApplicationData(amount,repaymentTime,loanId)) );
	
	postRequest( ADD_LOAN_APP , JSON.stringify(new loanApplicationData(amount,repaymentTime,loanId)), (error,result)=>{
		if(error ){
			console.log(error);
		}else{
			alert(result + "Your application for future reference");
			console.log(result);
		}
		
	});
	
	console.log(imageD);
}