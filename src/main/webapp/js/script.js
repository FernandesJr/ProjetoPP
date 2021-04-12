/**
 * 
 */

function mostrarsenha(){
  var campoSenha = document.getElementById("exampleInputPassword");
  if(campoSenha.type === "password"){
    campoSenha.setAttribute("type","text");
  }else{
    campoSenha.setAttribute("type","password");
  }
}