/**
 * Script pour la gestion du diagram de l'exercice
 */

$(document).unbind('click').on('click', '.ui-diagram > .ui-diagram-element', function(info){

	console.log(info.target.id);
	solutionRecalculer(info.target.id);
	return;
	
  var elem = document.getElementById(info.target.id); 
  var porte =  $(elem);
  if(porte.hasClass('entree')){
	  // Si entree, switch entre 0 et 1
	  if(elem.innerHTML == "0"){
		  elem.innerHTML = "1";
	  } else {
		  elem.innerHTML = "0";
	  }
  } else if(porte.hasClass('porte')){
	  // Si porte binaire, switch entre les portes possibles
	  var old = document.getElementById('notionSwitch').value.split(';');
	  console.log(old);
	  console.log(elem.innerHTML);
	  if(elem.innerHTML == ""){
		  elem.innerHTML = old[0];
	  } else {
		  var i=0;
		  var change = true;
		  while(i<old.length && change){
			  if(elem.innerHTML == old[i]){
				  change = false;
				  if(i==old.length-1){
					  elem.innerHTML = old[0];
				  } else {
					  elem.innerHTML = old[i+1];
				  }
			  }
			  i++;
		  }
	  } 
  } else if(porte.hasClass('porteNot')){
	  // Si porte not, switch entre vide et not
	  if(elem.innerHTML == ""){
		  elem.innerHTML = "NOT";
	  } else {
		  elem.innerHTML = "";
	  }
  } 

  // Recalculer la solution
  var diag = $(document.getElementById('diag'));
  console.log(diag);
  var x = document.getElementsByClassName("entree").length;
  console.log("entree "+x);
  solutionRecalculer(JSON.stringify(diag));
});
