//Array que contêm todos os checkboxs
var checkBoxs = new Array();
//Números selecionados
var selecionados = new Array();
//Capturando todos os checkBox's
for (let i = 1; i < 26; i++) {
   checkBoxs[i] = document.getElementById("inlineCheckbox" + i);
}

var buttonSaveAposta = document.getElementById("btSaveAposta");
buttonSaveAposta.style.display = "none";


function selectNumber(){
   selecionados.length = 0;
   
   //Vai verificar todos os checkBoxs
   for (let index = 1; index < checkBoxs.length; index++) {
      
      //Verificar quais são os checkBoxs que já foram selecionados
      if(checkBoxs[index].checked){
         //Só adiciona o número selecionado uma única vez, verificar se já está incluso no Array
         if(!selecionados.includes(checkBoxs[index])){
            selecionados.push(checkBoxs[index]);
         }
      }
   }


   if(selecionados.length > 4){
      //Ao selecionar os 5 números desabilita todos os outros não selecionados checkBox
      for (let i = 1; i < checkBoxs.length; i++) {
         if(!checkBoxs[i].checked){
            checkBoxs[i].setAttribute("disabled", "disabled");
         }
      }
      buttonSaveAposta.style.display = "block";
   }else{
      //Liberar todos os checks aqui
      //Mesmo depois de selecionar os 5 caso desmarque algum ele volta a liberar todos os checkBox's
      for (let i = 1; i < checkBoxs.length; i++) {
         checkBoxs[i].disabled = false;
      }
      buttonSaveAposta.style.display = "none";
   }
}