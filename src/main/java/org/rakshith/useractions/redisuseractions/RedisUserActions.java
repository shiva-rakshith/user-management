package org.rakshith.useractions.redisuseractions;

import org.rakshith.useractions.UserActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RedisUserActions extends RedisDBConfiguration implements UserActions {

    String userDetails,phoneNumber,name,location;
    int age;

    private static final Logger logger = LoggerFactory.getLogger(RedisUserActions.class);

    @Override
    public String createUser(String phoneNumber, String name, int age, String location){
        if (phoneNumber.length() == 10) {
            userDetails = "{\"name\":" +"\""+ name +"\""+ ",\"age\":" + age + ",\"location\":" +"\""+ location+"\"" +"}";
            jedis.set(phoneNumber, userDetails);
            logger.info("Created user details successfully: " + phoneNumber + " " + name + " " + age + " " + location);
            return "Created user details successfully";
        } else {
            return "Invalid phone number";
        }
    }

    @Override
    public String updateUser (String phoneNumber, String name,int age, String location){
        if (jedis.exists(phoneNumber)) {
            userDetails = "{\"name\":" +"\""+ name +"\""+ ",\"age\":" + age + ",\"location\":" +"\""+ location+"\"" +"}";
            jedis.set(phoneNumber, userDetails);
            logger.info("New details are successfully updated: " + phoneNumber + " " + name + " " + age + " " + location);
            return "User details updated successfully";
        } else {
            return "User do not exit";
        }
    }

    @Override
    public String deleteUser (String phoneNumber){
        if (jedis.exists(phoneNumber)) {
            jedis.del(phoneNumber);
            logger.info(phoneNumber + " user details deleted successfully.");
            return "Deleted user details successfully";
        } else {
            return "User do not exit";
        }
    }

    @Override
    public String showUser (String phoneNumber){
        if (jedis.exists(phoneNumber)) {
            userDetails = jedis.get(phoneNumber);
            JSONObject obj = new JSONObject(userDetails);
            this.phoneNumber =  phoneNumber;
            this.name = obj.getString("name");
            this.age  = obj.getInt("age");
            this.location = obj.getString("location");
            return userDetails;
        } else {
            return "User details do not exist.";
        }
    }

}
