Vue.component('signIn', {
	template: '<div><div class="row">'
				+ '<label for="THEusername">Username</label>'
				+ '<input v-model="username"  type="text"  id="THEusername" />'
				+ '<label for="THEpassword">Password</label>'
				+ '<input v-model="password"  type="password" id="THEpassword" />'
				+ '<br>'
				+ '<input  type="submit" v-on:click="loginMethod" value="submit credentials" /></div><br>'
				+ '<div id="signinError" class="alert alert-error six columns" style="display:none;">'
				+ 'Username and/or password provided are not valid. Please try again.</div></div>',

	data: function() { 
		return {
			username: '',
			password: ''
        };
	},
	 
    methods: {
    	loginMethod() {
  		     var params = new URLSearchParams();
             params.append('grant_type', 'password');
             params.append('username', this.username);
             params.append('password', this.password);
             var credentials = "my-client:mysecretvalue"; //sub forma username:password
             // acuma tre sa le fac encoded sa-mi returneze un cod asa cum apare si in postman preview request headers
             var encoded_credentials = window.btoa(credentials); //this is supported by most browsers
             // btoa encodes a string in in base-64
             // asta cu search params e cand in postman trimit pe /oath/token parametri ca form-url-encoded nu ca fiind vizibili in url ca la get
             // pt ca nu vreau sa fie vizibil pe url request valoarea credentialelor de login(parola de exemplu)
             // axios makes the request to the rest api, binds the vue data to the rest data
             axios({
            	 method: 'post',
            	 url: 'oauth/token',
                 // auth:{username:'my-client',password:'mysecretvalue'}, this is for basic authorization only
                 // ca aletrnativa la hearders: authorization: Basic encoded_credentials
                 headers: {"Content-type": "application/x-www-form-urlencoded; charset=utf-8",
                          'Authorization': "Basic " + encoded_credentials
              	  // asa apare si in postman preview request headers
                 },  
                 data:params
             }).then(function(response) {
            	 document.cookie = "access_token=" + response.data.access_token + ";path=/";
            	 // path=/ indicates that the cookie applies to the whole document- this cookie will be set to all urls
          	     // so that later i can retrieve it when i'm on another page and use it to access restricted stuff
          	     // with bearer token authentication using the cookie   	  
          	     // redirect to mvc requst to display the view
          	     document.location.replace("/");
          	     // this is for the home page-it redirects
          	 }).catch(function(error) {
          		 console.log(error.response);
          		 var error_msg = document.getElementById("signinError");
          		 error_msg.style.display = "block";
          	 });
        }
    } 
});
