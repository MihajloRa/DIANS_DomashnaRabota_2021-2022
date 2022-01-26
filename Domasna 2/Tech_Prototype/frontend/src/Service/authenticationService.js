import axios from "../Config/axios-config";

const authenticationService = {
    registerUser: (username, password, repeatedPassword, email) => {
        axios.post(`/auth/register`, {
            "username": username, 
            "password": password, 
            "repeatedPassword": repeatedPassword, 
            "email": email
        });
    },
    loginUser: (username, password) => {
        axios.post(`/auth/login`,{
            "username": username, 
            "password": password, 
        })
    }
};

export default authenticationService;