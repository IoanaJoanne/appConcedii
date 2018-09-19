Vue.component('registerAcc', {
	template: '<div class="row"><center>'
		+ '<label for="THEusername">Pick a username</label>'
		+ '<input v-model="username"  type="text"  id="THEusername" />'
		+ '<label for="THEpassword">Pick a password</label>'
		+ '<input v-model="password"  type="password" id="THEpassword" />'
		+ '<label for="THEconfirm">Confirm password</label>'
		+ '<input v-model="confirm_password"  type="password" id="THEconfirm" />'
		+ '<br>'
		+ '<input  type="submit" v-on:click="registerMethod" value="register" /> <br>'
		+ '<div id="registerErrorPasswordConfirm" class="alert alert-error six columns" style="display:none;">'
		+ "Password and password confirm fields don't match. Please try again.</div>"
		+ '<div id="registerErrorUsernameTaken" class="alert alert-error six columns" style="display:none;">'
		+ "Username already in use. Please pick another username.</div></center></div>",
		

	data: function() { 
		return {
			username: '',
			password: '',
			confirm_password: ''
        };
	},
	 
    methods: {
    	registerMethod() { //now i send the post request with a json body params for the form, not url encoded params
  		    
    		if (this.password == this.confirm_password)
    			{
    		var myjson = JSON.stringify({
                
                username: this.username,
                password: this.password
                
            });
    	
     		axios({
                   method:'post',
                   url:'/newUser', 
                     //no authorization required
                   headers: { 
            "Content-type": "application/json; charset=utf-8"},
            data: myjson
                 	        
               })
               .then(function(response){
            	if(response.data == "username already taken by another user")
            		   { 
            		   var error_msg_username = document.getElementById("registerErrorUsernameTaken");
            		   var error_msg_confirm = document.getElementById("registerErrorPasswordConfirm");
                   	   error_msg_username.style.display = "block";
                   	   error_msg_confirm.style.display = "none"; //in caz ca fusese deja displayed
            		   }
            	   else {
            		   
            		   document.location.replace("/#/signin");
            		   
            	   }
       		  });
     		
    			}
    		else {
    			var error_msg_username = document.getElementById("registerErrorUsernameTaken");
     		   var error_msg_confirm = document.getElementById("registerErrorPasswordConfirm");
            	   error_msg_confirm.style.display = "block";
            	   error_msg_username.style.display = "none"; //in caz ca fusese deja displayed
    			
    		}
    		
        }
    } 
});
