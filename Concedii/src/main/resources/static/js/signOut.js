
/*this is a global component, meaning that it can be used inside any root vue element (adica un element de tipul var myvariable = new Vue({}) )*/
 /*a local component is the one defined as a simple javascript object with var myvariable = {}*/
/*a local component can be used only inside the current root element */

Vue.component('signOut',{
	 
	/*template: '<h5> {{signout_msg}}</h5>',*/
	data: function() {return {
		  
		  signout_msg: ''
	  }},
	 created()
	 {
		this.logoutMethod();
	 },
      methods: {
    	  logoutMethod(){
    		//dc scriu logout spring automat ma redirectioneaza catre login?logout ca apel rest in backend- pt ca logout e cuv rezervat in spring security
  //deci in rest apelul va fi pt /login?logout unde sunt redirectionata, nu pt /logout, dar apelul axios ramane catre /logout 
// apelul axios nu merge decat dc ii pun headerul pt autorizare pt bearer, pt ca /login?logout sa fie si el autorizat ulterior, altfel pt /login?logout imi afiseaza unauthorized in urma redirectionarii
//asta e singurul mod pt a autoriza /login?logout, nu merge dc ii dau permit in clasele de configurare pt securitate/autentificare

			 
    	    var token = getCookie("access_token");
    		axios({
                  method:'get',
                  url:'/logout', 
                   headers: { 'Authorization': "Bearer " + token} 
                	        
              })
          	/* 
             
              var myjson = JSON.stringify({
                 
                  access_token: token
              });
           //   var params = new URLSearchParams();//ca sa pot sa setez headeru cu form url encoded
            //  params.append('access_token', token);
              axios({
                  method:'post',
                  url:'/signoutOfAcc', 
                  headers: { 
                	 //  "Content-type": "application/x-www-form-urlencoded; charset=utf-8",
  //pot trimite request body si sub forma de json, nu neaparat url encoded
   "Content-type": "application/json; charset=utf-8", //asta cu application/json o iau din postman
   "Authorization": "Bearer " + token},
                	
               //   data:params    
               data: myjson
              })
                 */
    		  .then(function(response){
    			//set expiration date to a past date and no value for the cookie in order to delete it  
        		 document.cookie = "access_token" + "=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
        		 
    			  console.log(response.data);
    			  this.signout_msg = response.data;
    		      document.location.replace("/");
    		  }.bind(this));
    		 
    		  
    	  }
      }
	 
	 
 });
