package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.UserInfo;
import com.example.demo.model.UserLevel2;
import com.example.demo.model.UserLevel4;
import com.example.demo.model.UserLevel6;
import com.example.demo.model.Users;
import com.example.demo.model.SiteModel.Schools;
import com.example.demo.repository.Level2Repository;
import com.example.demo.repository.Level4Repository;
import com.example.demo.repository.Level6Repository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.SiteRepository.SchoolRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service 
public class AdminServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Level2Repository level2Repository;
	
	@Autowired
	private Level4Repository level4Repository;
	
	@Autowired
	private Level6Repository level6Repository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private RoleServices roleServices;
	
	@Autowired
	private GenderServices genderServices;
	
	

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
	 
	//################## DateFormat	#################################
	 
	 public String dateFormatter(LocalDateTime myDateObj) {
		 if(myDateObj != null) {

	 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy  hh:mm a");//  MMM dd yyyy  hh:mm a  //E, dd MMM yyyy z
	 String formattedDate = myDateObj.format(myFormatObj);   
	 return formattedDate;
	 }
		 else return null;
	 }
	 
	 public String dateFormatterDOB(LocalDateTime myDateObj) {
		 if(myDateObj != null) {
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy");//  MMM dd yyyy  hh:mm a  //E, dd MMM yyyy z
		 String formattedDate = myDateObj.format(myFormatObj);   
		 return formattedDate;
		 }
		 else return null;
	 }

		
	//################## Fetching all user 	#################################
		
			public List<Users> getAllUsers(){
				List<Users> users = userRepository.findAll(); 
				return users;
			}
			
		
	//#################### Fetch Users by ID 	###############################
		
		  public Optional<Users> getUserById(int userID) { 
			 Optional<Users> optUser =  userRepository.findById(userID);
					  
			  return optUser;//.orElse(null); 
		  
		  }
		 
		  
		  
	//################### 	find user by mail 	####################################
		  
		  public Optional<Users> getUserByMail(String email) { 
			  Optional<Users> optUserByMail = userRepository.findByEmail(email); 
			  return  optUserByMail;
		  
		  }
		  
		  
	//################### 	find user by rolebased 	####################################
		  		  
		  public UserInfo roleBasedDetails(Users users) {
			  UserInfo userInfo = new UserInfo();
			  if (users.getRoleId()==0){
				  return userInfo;
			  }
			  
			  else if(users.getRoleId() < 3 && users.getRoleId() > 0) {
				  Optional<UserLevel2> optUser = level2Repository.findByuserID(users.getUserID());
				 
				  userInfo.setName(optUser.get().getName());
				  userInfo.setDepartment(optUser.get().getDesignation());
				  userInfo.setGender(genderServices.getGenderById(optUser.get().getGender()).get().getGender());
				  userInfo.setDOB(dateFormatterDOB(optUser.get().getDob()));
				  userInfo.setPhoneNo(optUser.get().getPhoneNo());
				  userInfo.setUniqueId(optUser.get().getEmployeeId());
				  
				  return userInfo;
				 }
			  else if(users.getRoleId() < 5 && users.getRoleId() > 2) {
				  
				  Optional<UserLevel4> optUser = level4Repository.findByuserID(users.getUserID());
				 
				  userInfo.setName(optUser.get().getName());
				  userInfo.setDepartment(optUser.get().getDesignation());
				  userInfo.setGender(genderServices.getGenderById(optUser.get().getGender()).get().getGender());
				  userInfo.setDOB(dateFormatterDOB(optUser.get().getDob()));
				  userInfo.setPhoneNo(optUser.get().getPhoneNo());
				  userInfo.setUniqueId(optUser.get().getEmployeeId());
				 
				  return userInfo;
				  }
			  else {
				  Optional<UserLevel6> optUser = level6Repository.findByuserID(users.getUserID());
				
				  userInfo.setName(optUser.get().getName());
				  userInfo.setDepartment((optUser.get().getSchool() + " - " + optUser.get().getBranch()));
				  userInfo.setGender(genderServices.getGenderById(optUser.get().getGender()).get().getGender());
				  userInfo.setDOB(dateFormatterDOB(optUser.get().getDob()));
				  userInfo.setPhoneNo(optUser.get().getPhoneNo());
				  userInfo.setUniqueId(optUser.get().getRegistrationId());
				 
				  return userInfo;
			  }
			  }
		  
		  
	//################### 	Users details display method 	####################################
		  
		  public List<UserInfo> displayUsersDetails( List<Users> userList) { //Users users,
			  UserInfo userInfo = new UserInfo();
			  List<UserInfo> userListInfo = new ArrayList<>();
			  

			  Iterator<Users> iter = userList.iterator();
			  while(iter.hasNext())
				  {
					  Users users = iter.next();
					  userInfo = roleBasedDetails(users);					  
					 
					  userInfo.setId(users.getUserID());
					  userInfo.setRole(roleServices.getRoleById(users.getRoleId()).get().getRoleAs());
					  userInfo.setActive(users.isActive());
					  userInfo.setVerified(users.isVerified());
					 userInfo.setCreatedOn(dateFormatter(users.getCreatedOn()));
					 userInfo.setEmail(users.getEmail());
					
					  userListInfo.add(userInfo);
				  }
				  return userListInfo;
				  }
			
		  
		
	//################### Add Single User  #####################################
		  
			public String addUser(Users users) {
				
//				users.setCreatedBy(session value);
				String pass=generatePassword();
				
				String encryptPass = pass; // later on encryption method will be used
				
				users.setPassword(encryptPass);			
				
				userRepository.save(users);
				
				
				if(users.getRoleId()>0 && users.getRoleId()<=2) {
					UserLevel2 userLevel2 = new UserLevel2();
					userLevel2.setUserID(users.getUserID());
				
					level2Repository.save(userLevel2);
				}
				else if(users.getRoleId()>2 && users.getRoleId()<=4) {
					UserLevel4 userLevel4 = new UserLevel4();
					userLevel4.setUserID(users.getUserID());
				
					level4Repository.save(userLevel4);
				}
				else {
					UserLevel6 userLevel6 = new UserLevel6();
					userLevel6.setUserID(users.getUserID());
				
					level6Repository.save(userLevel6);
				}
				
				return pass;//return password generated
				
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
			
			public void deleteUserByID(int userID){
				
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

			
			
			
			
		









































































































	//########################## 	Optional to List Conversion 	############################################################
			

			public static <T> List<T> toList(Optional<T> opt) {
			    return opt.isPresent()
			            ? Collections.singletonList(opt.get())
			            : Collections.emptyList();
			}







//########################## 	END OF ADMIN SERVICES 	############################################################
}	


















