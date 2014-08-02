/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aspen.capstone.estimation.relative.service;

import edu.aspen.capstone.estimation.relative.domain.UserDO;
import edu.aspen.capstone.estimation.relative.entity.ApplicationUser;
import edu.aspen.capstone.estimation.relative.utils.JSONResponseWrapper;

/**
 *
 * @author jaiishankar
 */
public interface UsersService {

    JSONResponseWrapper signup(UserDO usr); //adds a user to db

    JSONResponseWrapper deactivate(Integer id); //removes a user from db

    JSONResponseWrapper logon(UserDO usr); // validates the given uname & pass

    JSONResponseWrapper checkAvailablitiy(String name);  // checks if the unmae is already taken or not

    JSONResponseWrapper listAllLoggedUsers(); //lists all the users those are currently logged in

    JSONResponseWrapper logout(UserDO usr); //Logs the current user out form the system

    JSONResponseWrapper getUserById(Integer id); //Gets the specific user

    JSONResponseWrapper updateUser(UserDO usr); //Updates the specific user

    //internal functions for developers
    JSONResponseWrapper listAll(); //lists all the current user data from DB
}
