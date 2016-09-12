$.get(downloadPath, function(data){
 	viewer.importXML(data);
  	viewer.get('canvas').zoom('fit-viewport');
});