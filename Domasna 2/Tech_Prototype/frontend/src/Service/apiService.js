import axios from "../Config/axios-config";

const apiService = {
    fetchRoutes: (username) =>{
        return axios.get(`/user/${username}/routes`);
    }, 
    updateRouteStatus: (id, status) => {
        axios.post(`/route/${id}`, {
            "updateStatus": status
        });
    },
    updateRouteDestinations: (id, longitude, latitude) =>{
        axios.post(`/route/${id}/destination`, {
            "latitude": latitude,
            "longitude": longitude
        });
    },
    fetchRoute: (id) => {
        axios.get(`/route/${id}`);
    },
    addUserRoute: (username, latitude, longitude, preferences) => {
        axios.post(`/user/${username}/route`, {
            "latitude": latitude, 
            "longitude": longitude, 
            "preferences": preferences
        });
    }
};

export default apiService;