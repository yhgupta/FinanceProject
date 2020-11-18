const postRequest = (url, value,callback)=>{
//    console.log(value)

    let xml = getXMLHTTPObject();
    xml.onreadystatechange = () => {
        if( xml.readyState === 4 && xml.status === 200 ){
            callback(undefined,xml.response);
        }
        else if( xml.readyState === 4 ){
            callback('Could Not fetch Data', undefined)
        }
    }

    xml.open("POST", url ,true);
    xml.setRequestHeader("Content-Type","application/json");
    xml.send(value);

}

const getRequest = (url,callback)=>{
//  console.log(value)
  let xml = getXMLHTTPObject();
  xml.onreadystatechange = () => {
      if( xml.readyState === 4 && xml.status === 200 ){
          callback(undefined,xml.responseText);
      }
      else if( xml.readyState === 4 ){
          callback('Could Not fetch Data', undefined)
      }
  }

  xml.open("GET", url ,true);
  xml.setRequestHeader("Content-Type","application/json");
  xml.send();
}


function getXMLHTTPObject(){
    return new XMLHttpRequest();
}

