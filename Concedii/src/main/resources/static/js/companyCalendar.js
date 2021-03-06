var companyCalendar = {
	template: '<div><p>{{ welcomeuser }}</p>'
				+ '<h4>Calendar displayed company-wide</h4>'
				+ '<ul><li v-for="leave in leaves">'
					+ '<p>Type: {{ leave.type}} </p>'
					+ '<p>Start date: {{ leave.startDate }}</p>'
					+ '<p>End date: {{ leave.endDate }}</p>'
				+ '<hr>'
				+'</li></ul></div>',
		
	/* tot html ul din teplate trebuie sa fie in linie ca un string, dc nu este si am spatii pt rand nou atunci da eroare 
 	la ghilimele sau dc vreau mai multe linii fac cu + concatenare de stringuri, cate un string pe linie
 	tot html ul din template trebuie bagat in interiorul unor tag uri de <div></div> altfel da error compiling template */
		
	data: function() {
		return { leaves:[] }; 
	}, 
	
	props: ['welcomeuser'],
	
	/* you use props to pass data from parent to component, signInRequired_msg is defined as data in the parent and 
	 * i want to use it in the child component as well. props is passed to the component via props definition in 
	 * component. also in parent, props property is set in router-view tag where component template will appear, 
	 * instead of writing  the component tag with the props property */
		
	mounted() {	
		this.fetchLeavesList();
	},

	/* PT CA PROPS SA FIE DEFINED SI  IN CHILD SCRIPT, NU DOAR IN CHILD TEMPLATE, TOATE METODELE SI DIN PARENT SI DIN CHILD 
	 * SUNT APELATE CU CREATED */
	
	methods: {
		fetchLeavesList() {
			// axios makes the request to the rest api, binds the vue data to
			// the rest data
		//	console.log("props shown inside child script: " + this.loggedin);
		
			//this means that the user has signed in and the token is valid (the session hasn't timed out)
			console.log ("intra in fctia componentei");
			
			if (getCookie("access_token")) {
				axios({
					method: 'get',
					url: '/companyLeaves',
					headers: {'Authorization': "Bearer " + getCookie("access_token")}
			        // asa apare si in postman preview request headers
				}).then(function(response) {
					this.leaves = response.data;
            	}.bind(this));
				/* .catch(function (error) {
        	 		console.log(error);
         		}); */
			}  		  
        }
	}	
};