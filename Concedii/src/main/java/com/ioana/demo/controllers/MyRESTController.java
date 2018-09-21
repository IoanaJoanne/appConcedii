package com.ioana.demo.controllers;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ioana.demo.auth2.DB.entities.User;
import com.ioana.demo.auth2.DB.services.MyUserService;
import com.ioana.demo.models.Employee;
import com.ioana.demo.models.LeaveRequest;
import com.ioana.demo.models.LeaveStatus;
import com.ioana.demo.models.LeaveType;

@RestController
public class MyRESTController {

	@Autowired
    private TokenStore tokenStore;
	
	@Autowired
    private MyUserService userService;
	
	public static List<LeaveRequest> lista;
	static {
		lista = new ArrayList<>();

		Employee e1 = new Employee("kiki");
		Employee e2 = new Employee("miki");

		LeaveRequest l1 = new LeaveRequest(LeaveType.holiday, new Date("02/09/2018"), new Date("12/09/2018"),
				LeaveStatus.approved, e1);
		LeaveRequest l2 = new LeaveRequest(LeaveType.sick_leave, new Date("03/09/2018"), new Date("05/09/2018"),
				LeaveStatus.approved, e2);

		lista.add(l1);
		lista.add(l2);

	}


	// spring REST
	
	//VARIANTA 2- TRIMIT ACCESS TOKEN CA PARAMETRU PE REQUEST de tip post
	
	@RequestMapping(value = "/signoutOfAcc", method = RequestMethod.POST, consumes = "application/json")
//ii impun sa nu intre in metoda sa inceapa sa execute decat dc are un input de tip json- consumes e necesar
	@ResponseStatus (HttpStatus.OK)
	public String signout(@RequestBody String access_token) throws JSONException {
		//pe request body am ce am transmis ca date pe requestul de tip post, adica am transmis tokenul de access
		//am ales metoda de tip post ca sa nu transmit tokenul ca parametru in url sa fie vizibil
		System.out.println(access_token);
	//	String token_value = access_token.split("=")[1];//dc trim datele pe post ca form url encoded, pt ca access_token are valoarea access_token = hfgdssgfgshdfg adica access_token = token_value si eu vreau sa extrag token value
//dc trimit datele ca json pe reuqets at request body va fi un json pe care l procesez in felul urmator
		JSONObject myjson = new JSONObject(access_token);
		String token_value = myjson.getString("access_token");
		System.out.println("token before removal: "+tokenStore.readAccessToken(token_value));
		tokenStore.removeAccessToken(tokenStore.readAccessToken(token_value));
		System.out.println("token after removal from token store: "+tokenStore.readAccessToken(token_value));
		
		return "You have been successfully logged out of your account";

	}
	@RequestMapping(value = "/newUser", method = RequestMethod.POST, consumes = "application/json")
	//consumes e esential ca sa nu mi execute metoda decat dc primesc input json pt ca logica din metoda e facuta pt un input te tip json; pt altfel de input crapa si mi da internal server error
	public ResponseEntity<String> register(@RequestBody String accInfo) throws JSONException {
		// inside <> i specify the type of body
		System.out.println(accInfo);
		JSONObject myjson = new JSONObject(accInfo);
		String username = myjson.getString("username");
		String password = myjson.getString("password");
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<String> responseEntity = null;
		String body  = null;
		 if  (userService.findByUsername(username) != null) {
			 body =  "username already taken by another user";
			 responseEntity = new ResponseEntity<String>(body, httpHeaders, HttpStatus.BAD_REQUEST);
			 //response entity are in componenta body, hhtp headers si http status
	//deci un response body, the response headers and the response status
			 
			 //dc am ales sa folosesc response entity- pt uri location- dc imi salveaza user-ul, in response imi trimite si locatia-url ul la care pot sa accesez resursa tocmai creata
	//dc nu am nevoie sa setez uri location, pot sa renunt la response entity si sa folosesc doar adnotarea @ResponseStatus
		}
		else {
			
			userService.save(new User(username, password));
			
			body =  "new employee user has been registered";
			responseEntity = new ResponseEntity<String>(body, httpHeaders, HttpStatus.CREATED);
			URI location = null;
			UriComponentsBuilder builder =  UriComponentsBuilder.fromPath("/employeeAcc/"+username);
			UriComponents components = builder.build();
			location = components.toUri();
			httpHeaders.setLocation(location);
		}
		return responseEntity;

	}
	
	
	@RequestMapping(value = "/companyLeaves", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// sau puteam sa zic produces = "application/json"
	//produces nu e neaparat necesar- poate fi omis
	@ResponseStatus(HttpStatus.OK)
	public List<LeaveRequest> showLeavesCompanyWide() {
		
		return lista;

	}


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test message: this rest url is not secured with security mechanism";
	}
	@RequestMapping(value = "/notSignedIn", method = RequestMethod.GET)
	public String displayMessage() {
		return "You need to authenticate first to access the content of this section.";
	}
	
	//VARIANTA 1 -NU TRIMIT access token pe request ca parametru, dar extrag token din authentication
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK) //asta cu ok e oricum added by default ca response status dc se executa fara probl
	public String signoutOfAcc(HttpServletRequest req, HttpServletResponse res, OAuth2Authentication authentication) {
		
		//tokenu tre sters si din backend din tokenStore
	//	 tokenStore.removeAccessToken(tokenStore.readAccessToken("accessToken"));- n am de unde sa iau valoarea access_token pt ca nu l-am trimis ca param pe request
//=> fac cu oauth2 authentication cu tokenStore.getAccessToken(authentication) pt a obtine tokenul userului logat
		System.out.println("Token before removal: "+ tokenStore.getAccessToken(authentication));
		
		
	//	verific dc mi intra pe cazul /login?logout, care ma intereseaza, vreau sa verific dc parametrul logout exista pe request si este singurul parametru
//si nu are nicio valoare- e un string empty
		System.out.println(req.getParameterMap().containsKey("logout"));
		System.out.println(req.getParameterMap().size());
		System.out.println(req.getParameterMap().get("logout")[0]);
	if (req.getParameterMap().containsKey("logout") && req.getParameterMap().size() ==1 && req.getParameterMap().get("logout")[0] == "")
	{   tokenStore.removeAccessToken(tokenStore.getAccessToken(authentication));
	System.out.println("token after removal from token store: "+ tokenStore.getAccessToken(authentication));
		return "You have been successfully logged out of your account";
	}
	else return ""; //return an empty String, but not a null value ca sa nu crape back-endu cu NullPointerException
	}
	
	@RequestMapping(value = "/retrieveCurrentUser", method = RequestMethod.GET)
	public String showUser(Principal user, Authentication auth) {
//principal in spring security is the current logged in user
	//alternativa la  Principal sau Authentication
		System.out.println("cu security context username-ul este: "+SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("tot cu security context sau cu authentication.getPrincipal(), returneaza o implementare a userDetails, adica un obiect de tip CustomUserDetails: "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//SecurityContextHolder.getContext().getAuthentication() returneaza tot un obiect de tip Authentication
			
		System.out.println("with Principal username is: "+user.getName());
		//authentication e alternativa la principal
	//	System.out.println("with authentication credentials are: "+auth.getCredentials().toString());-- not working, nu afiseaza nimic
		System.out.println("with authentication username is: "+auth.getName());
		System.out.println("with authentiation authorities are: "+auth.getAuthorities());
		return user.getName();
	}

}
