/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var irUrl=function(url){
    window.open(url,'Comparte Comentario','height=400,width=650');
};

var getComentario=function(comentario){
    
    if(comentario !== ''){
        return comentario;
    }
    return 'Sin comentario';
}



function Alerta(comentario){
 
if (comentario.length > 140) {
 alert('Tu Tweet debe tener menos de 140 Caracteres');
 }
 else {
 //cambiar por nombre del local
 var twtLink = 'http://twitter.com/home?status=' +encodeURIComponent('Nombre del local(#MuffinUNAM):'+ comentario);
//alert(twtLink);
 window.open(twtLink,'twittea','height=400,width=650');
 }
alert(jobValue);
}