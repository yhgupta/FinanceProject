function showApplicationDetailDate(){
	
	let container = document.getElementById("container");
	let data = document.getElementById("Display");
	let value ;
	getRequest(GET_LOAN_APP_DATE , (error,data)=>{
		if( error ){
			console.log(error);
		}else{
			data.value = data;
			container.value ='';
			display(data, container);
		}
	});
}


function createButton(value){
	let button = document.createElement('button');
	button.type = 'button';
	button.innerHTML = value;
	return button;
}

function display(value, container){
	
	let result = JSON.parse(value);
	console.log("Parsed");
	console.log(result);
	for( let i = 0 ; i < result.length ; i++ ){
		let p = createNode();
		p.innerHTML = result[i];
		container.appendChild( p );
	}
	
}

function createNode(){
	
	let p = document.createElement('p');
	return p;
	
}

function showApplicationDetailStatus(){
	
	let container = document.getElementById("container");
	let data = document.getElementById("Display");
	let value ;
	getRequest(GET_LOAN_STATUS, (error,data)=>{
		if( error ){
			console.log(error);
		}else{
			data.value = data;
			console.log(data);
			container.value ='';
			display(data, container);
		}
	});
}