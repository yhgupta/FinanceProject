function convertToPanJson(value){
	let json = {"panCard":value};
    return JSON.stringify(json);
}

function convertToApplicationJson(value){
	let json = {"applicationId": value}
	return JSON.stringify(json);
}
