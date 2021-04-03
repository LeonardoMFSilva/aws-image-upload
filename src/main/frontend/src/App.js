import React, {useState, userEffect, useEffect} from "react";
import logo from './logo.svg';
import './App.css';
import axios from "axios";


const UserProfiles = () => {
  
  const [UserProfiles, setUserProfile] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
    setUserProfile(res.data);
    });

  }

  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return UserProfiles.map((userProfile, index) => {

    return (
      <div key={index}>
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userProfileID}</p>
      </div>
    )
    
  });
}

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
