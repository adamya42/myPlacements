package com.example.demo.controller;

import java.util.ArrayList;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.model.helper_classes.AddMultipleUser;
import com.example.demo.model.helper_classes.AddSingleUser;
import com.example.demo.model.helper_classes.DeleteMultipleUsers;
import com.example.demo.model.helper_classes.UserInfo;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AdminServices;
import com.example.demo.services.MessageServices;
import com.example.demo.services.OTPServices;
import com.example.demo.services.ValidationErrorServices;
import com.sun.mail.iap.Response;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

import io.micrometer.core.instrument.util.StringUtils;

@RestController
@CrossOrigin
@RequestMapping("/admin/api")
public class ControllerAdmin {


	@Autowired
	private AdminServices adminServices;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OTPServices otpServices;

	@Autowired
	private MessageServices messageServices;

	@Autowired
	private ValidationErrorServices validationErrorServices;

	// private static final Logger logger= (Logger)
	// LogFactory.getLog(Controller.class);

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	//##################################### 	Welcome		##########################################

	@RequestMapping("/welcome")
	public String display() {

		log.debug("welcome window");

		return "WELCOME......!!!!YES!!!! Its Working Appliaction , Go Ahead";


	}

	//##################################### 	Display All Users & User by ID		####################################	

	@GetMapping(value = {"/users/{id}","/users"})
	public ResponseEntity<?> getAllUser(@Valid @PathVariable(name = "id",required = false)Integer id) throws ParseException {
		//if(StringUtils.isNotEmpty(id)) {
		if(id != null) {
			List<Users> userList = adminServices.toList(adminServices.getUserById(id));
			return new ResponseEntity<>(adminServices.displayUsersDetails(userList),HttpStatus.OK);
		}else {
			List<Users> userList = adminServices.getAllUsers();
			if (userList.isEmpty()) {
				return new ResponseEntity<>("No Details Found In Database!", HttpStatus.NOT_FOUND);
			}
			log.debug("Users found");
			return new ResponseEntity<>(adminServices.displayUsersDetails(userList), HttpStatus.OK);

		}
	}


	//##################################### 	Add Single User		##########################################

	@PostMapping("/addSingleUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody AddSingleUser addUser, BindingResult result) {//throws MessagingException {

		ResponseEntity<?> errorMap = validationErrorServices.ValidationServiceHandler(result);
		if(errorMap!=null) return errorMap;
		Optional<Users> optUser =  adminServices.getUserByMail(addUser.getEmail());



		if (optUser.isPresent()) {
			System.out.println("User Name already exist.");
			return new ResponseEntity<>("User Name already exist.", HttpStatus.FORBIDDEN);
		}
		else {

			//	user.setCreatedBy('session user');	


			String credential =	adminServices.addUser(addUser);

			try {
				String mail = addUser.getEmail();
				String subject = messageServices.getMailSubjectCredentials();
				String text = messageServices.getMailtextCredentials();

				//String credential=user.getPassword();

				//---------Send Credentials---------- 	
				otpServices.mailDelivery(mail, credential,subject,text);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new ResponseEntity<>("User << " + addUser.getEmail() + " >> Registered and credentials Send Successfully!", HttpStatus.OK);
		}
	}



	//##################################### 	Add Multiple Users		##########################################

	@PostMapping("/addMultipleUsers")
	public ResponseEntity<?> addMultiUser(@Valid @RequestBody AddMultipleUser addMultipleUsers, BindingResult result) throws MessagingException {

		ResponseEntity<?> errorMap = validationErrorServices.ValidationServiceHandler(result);
		if(errorMap!=null) return errorMap;

		Iterator<String> iter = addMultipleUsers.getEmailList().iterator();
	//	List<String> emailList = new ArrayList<>();
		while(iter.hasNext()){
			AddSingleUser addEachUser = new AddSingleUser();
			String temp = iter.next();
			if(temp.split(",").length>1)
			{
				String[] item = temp.split(",");
				for(int i=0 ; i<item.length;i++) {
					addEachUser.setEmail(item[i]);
					//emailList.add(item[i]);
					addEachUser.setRoleId(addMultipleUsers.getRoleId());
					ResponseEntity<?> response = addUser(addEachUser,result);
				}
			}
			else {
				addEachUser.setEmail(temp);
				//addEachUser.setId("s");
				//emailList.add(temp);
				addEachUser.setRoleId(addMultipleUsers.getRoleId());					  						  

				ResponseEntity<?> response = addUser(addEachUser,result);
			}
		}
		//addMultipleUsers.setEmailList(emailList);
	//	List<AddMultipleUser> multipleUsers = new ArrayList<>();
//		List<String> multipleUsers = new ArrayList<>();
//		multipleUsers.add(addMultipleUsers);		
		return new ResponseEntity<>("Added Successfully", HttpStatus.OK);

	}
	//########################## 	Update User API is in UserController 	#################################

	//##################################### 	Delete User By Id		##########################################


	@PostMapping("/users/delete")
	public ResponseEntity<?> deleteUserByID(@Valid @RequestBody DeleteMultipleUsers deleteMultipleUsers,BindingResult result) {
	
		ResponseEntity<?> errorMap = validationErrorServices.ValidationServiceHandler(result);
		if(errorMap!=null) return errorMap;

		Iterator<String> iter = deleteMultipleUsers.getId().iterator();
			while(iter.hasNext()){
				String userID = iter.next();
		Optional<Users> user = adminServices.getUserById(Integer.parseInt(userID));
//		if (!user.isPresent()) {
//			return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
//		}
		adminServices.deleteUserByID(user.get().getUserID(),user.get().getRoleId());
			}
		return new ResponseEntity<>("User with USERID <<  " + "user.get().getUserID()" + "  >> Deleted successfully!",
				HttpStatus.OK);


	}

	//##################################### 	E		##########################################

	@DeleteMapping("/users/deleteAll")
	public ResponseEntity<?> deleteAllUsers() {

		adminServices.deleteAllUsers();;
		return new ResponseEntity<>("All Users  Deleted successfully!",
				HttpStatus.OK);			
	}





















	//##################################### 	END OF ADMIN CONTROLLER		######################################################################
}
