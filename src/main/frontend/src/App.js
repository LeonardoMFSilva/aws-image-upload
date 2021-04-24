import React, {useState, userEffect, useEffect, useCallback} from "react";
import logo from './logo.svg';
import './App.css';
import axios from "axios";
import { useDropzone } from "react-dropzone";


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
        {/*Todo profile image*/}
        <br />
        <br />
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userProfileID}</p>
        <Dropzone />
        <br />
      </div>
    )
    
  });
};

function Dropzone() {
  const onDrop = useCallback (acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file)

  }, [])
  const { getRootProps, getInputProps, isDragActive } = useDropzone ({ onDrop })

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ? (
        <p>Drop the image here ...</p>
        ) : (
        <p>Drag 'n' drop profile image here, or click to select profile image</p>
        )
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
