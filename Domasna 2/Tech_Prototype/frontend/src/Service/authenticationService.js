import axios from "../Config/axios-config";
export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';

const authenticationService = {
    registerUser: async (data) => {
        let [username, password, repeatedPassword, email] = [...data];
        axios.post(`/auth/register`, {
            "username": username, 
            "password": password, 
            "repeatedPassword": repeatedPassword, 
            "email": email
        });
    },
    loginUser: async (data) => {
        let [username, password] = [...data];
        return await axios.post(`/auth/login`,{
            "username": username, 
            "password": password, 
        }).then;
    },
    registerSuccessfulLogin: async (username, token) => {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
        this.setupAxiosInterceptors(this.createJWTToken(token));
    },
    createJWTToken: (token) => {
        return 'Bearer ' + token;
    },
    logout: () => {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    },
    setupAxiosInterceptors: async (token) => {
        axios.interceptors.request.use(
            (config) => {
                if(this.isUserLogenIn()) {
                    config.headers.authorization = token;
                }
                return config;
            }
        );
    },
    getLogedInUserName: () => {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        if(user == null) return '';
        return user;
    },
    isUserLogedIn: () => {
        return sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME) !== null;
    }
};

export default authenticationService;