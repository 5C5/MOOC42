/**
 * Script pour la gestion du diagram de l'exercice
 */

$(document).on('click', '.ui-diagram > .ui-diagram-element', function(info){
  console.log(info.target.id); // change this to call the remote comm
  var elem = document.getElementById(info.target.id); 
  elem.innerHTML=5;
});
