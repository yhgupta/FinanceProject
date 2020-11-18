
function checkUserExistOrNot(id){

    document.getElementById(id).disabled = true;
    let panCardValue = document.getElementById(id).value;
    if ( validatePAN(panCardValue) === false ){
        document.getElementById(id).value = '';
    }else{
        getRequest( VERIFY + panCardValue , (err,data)=>{
            if( err){
                console.log(err);
            }else{
            	if( data == 'no' ){
            		window.location = USER_APPLY + panCardValue;
            		let customerId = document.getElementById("customerId");
            		customerId.value = panCardValue;
            	}else{
            		window.location = LOAN_APPLICATION + panCardValue;
            	}
            }
        });
    }
}


function required(){
	let panCard = document.getElementById("panCard");
	let type = document.getElementById("typeOfLoan");
	if( panCard.value == "" ){
		alert("Fill the Required Fields")
		return false;
	}
	if( type.value == "" ){
		alert("Fill the Required Fields")
		return false;
	}
	if( panCard.value != "" && type.value != "" ){
		return true;
	}
}


function validatePAN(pan_value) {
    let regex = /([A-Z]){5}([0-9]){4}([A-Z])$/;
    if (regex.test(pan_value.toUpperCase())) {
        return true;
    } else {
        alert("Invalid PAN Card");
        return false;
    }
}
