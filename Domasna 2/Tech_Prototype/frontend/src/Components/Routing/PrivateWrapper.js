import React from 'react';
import {Outlet, Navigate} from 'react-router-dom';
import authenticationService from '../../Service/authenticationService'

const PrivateWrapper = ({ isAuthenticated = authenticationService.isUserLogedIn() }) => {
    return isAuthenticated ? <Outlet /> : <Navigate to="/login" />;
  };
  

export default PrivateWrapper;
