package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.model.SiteModel.Schools;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.SiteRepository.SchoolRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;


@Service 
public class AdminServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private RoleServices roleServices;
	
	

//########################### 	Password Generator 	#################################
	
	 public  String generatePassword() {
		 
		 final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
		     final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
		     final String NUMBER = "0123456789";
		    
		     final String PASSWORD_ALLOW = CHAR_UPPER + NUMBER ;
		 
		     SecureRandom random = new SecureRandom();
		 
	         StringBuilder sb = new StringBuilder(5);
	        for (int i = 0; i < 5; i++) {

	            int rndCharAt = random.nextInt(PASSWORD_ALLOW.length());
	            char rndChar = PASSWORD_ALLOW.charAt(rndCharAt);
	           
	            sb.append(rndChar);

	        }

	        return sb.toString();

	    }

	 

		
	//################## Fetching all user 	#################################
		
			public List<Users> getAllUsers(){
				List<Users> users = userRepository.findAll(); 
				return users;
			}
			
		
	//#################### Fetch Users by ID 	###############################
		
		  public Users getUserById(int userID) { 
			 Optional<Users> optUser =  userRepository.findById(userID);
		  
			  return optUser.orElse(null); 
		  
		  }
		 
		  
		  
	//################### 	find user by mail 	####################################
		  
		  public Optional<Users> getUserByMail(String email) { 
			  Optional<Users> optUserByMail = userRepository.findByEmail(email); 
			  return  optUserByMail;
		  
		  }

		  
		
	//################### Add Single User  #####################################
		  
			public void addUser(Users users) {
				
//				users.setCreatedBy(session value);
				String pass=generatePassword();
				users.setPassword(pass);
				
				userRepository.save(users);
				
			}
			
//	//##############	 updating user by id 	###################################3
//		
//		  public User updateUser(User user, int userID){
//			  Optional<User> optUser = userRepository.findById(userID);
////			  if(userID == user.getUserID())
////			  User update = new User();
////			  update = optUser
//			  
//		
//			  if(optUser!= null)
//		      { 
//				  if(user.getUserName()!=null) {
//					  optUser.get().setUserName(user.getUserName());
//					
//				  }
//				  if(user.getMobileNo()!=0) {
//					  optUser.get().setMobileNo(user.getMobileNo());
//				  }
//				  if(user.getEmail()!=null) {
//					  optUser.get().setEmail(user.getEmail());
//				  }
//				  if(user.getFirstName()!=null) {
//					  optUser.get().setFirstName(user.getFirstName());
//				  }
//				  if(user.getMiddleName()!=null) {
//					  optUser.get().setMiddleName(user.getMiddleName());
//				  }
//				  if(user.getLastName()!=null) {
//					  optUser.get().setLastName(user.getLastName());
//				  }
//				  if(user.getGender()!=null) {
//					  optUser.get().setGender(user.getGender());
//				  }
//
//			  }	
//			
//			  return optUser.orElse(null);
//		  }
		 
		
			
	//############	 deleting all user 	###############################
			
			public void deleteAllUsers(){
				userRepository.deleteAll();
			}
			
	//############# 	 deleting user by id 	#####################
			
			public void deleteUserByID(Users users){
				int userID=users.getUserID();
				userRepository.deleteById(userID);
			}



	//############# 	 login authentication	#####################
			
			public ResponseEntity<?> authenticateLogin(Optional<Users> optUser, Users user) {
				if (optUser.isPresent() && user.getPassword()!=null && (user.getPassword().equals(optUser.get().getPassword()))) {
					optUser.get().setVerified(true);
					optUser.get().setActive(true);
					userRepository.save(optUser.get());
					int role = optUser.get().getRoleId();
					Optional<Role> optRole = roleServices.getRoleById(role);
					if (role==0) {			
						return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
					}
					
					else if (role==2) {			
						return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
					}
					
					else if (role==4) {			
						return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
					}
					
					else if (role==6) {			
						return new ResponseEntity<>("Login Successful..Hello"+" "+ optRole.get().getRoleAs(), HttpStatus.OK);
					}
					
					else {
					return new ResponseEntity<>("User << " + user.getEmail() + " >> Login Failed", HttpStatus.NOT_FOUND);
				
				}
				}
				else {
					
				return new ResponseEntity<>("User << " + optUser.get().getEmail() + " >> Login Failed", HttpStatus.NOT_FOUND);
			}
				
			}
//##########################################################################################################
//###################################### 	Site Services 	#######################################
//##########################################################################################################

			
	//################ 	 Find School by Code 	###########################
			
			public Optional<Schools> getSchoolbyCode(String schoolCode) {
				
				 Optional<Schools> optSchoolbyCode = schoolRepository.findBySchoolCode(schoolCode); 
				  return  optSchoolbyCode;				
				
			}


	//################ 	 Add School  	###########################			

			public void addSchool(Schools school) {
				schoolRepository.save(school);
				
			}
			
			
			
		



















































































































//########################## 	END OF ADMIN SERVICES 	############################################################
}	


















