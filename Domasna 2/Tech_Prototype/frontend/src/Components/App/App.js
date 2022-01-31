import '../../App.css';
import React from 'react';
import Dashboard from '../Dashboard/Dashboard';
import MapContent from '../Map/MapContent';
import Navigation from '../Navigation/Navigation';
import LandingPage from '../Landing/LandingPage';
import PrivateWrapper from '../Routing/PrivateWrapper'
import LoginComponent from "../Authentication/LoginComponent";
import RegisterComponent from "../Authentication/RegisterComponent"
import {BrowserRouter, Route, Switch} from 'react-router-dom';

function App() {

  return (
    <div className="wrapper">
      <Navigation/>
      <BrowserRouter>
        <Switch>
          <Route path="/" element={<LandingPage/>}/>
          <Route path="/login" element={<LoginComponent/>}/>
          <Route path="/register" element={<RegisterComponent/>}/>
          <Route element={<PrivateWrapper/>}>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/mapcontent" element={<MapContent />} />
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
    
  );
}

export default App;
