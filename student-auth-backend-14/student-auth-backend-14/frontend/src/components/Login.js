import React, { useState } from "react";
import axios from "axios";

function Login() {
  const [user, setUser] = useState({
    username: "",
    password: ""
  });

  const handleChange = (e) => {
    setUser({...user, [e.target.name]: e.target.value});
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/api/users/login", user)
      .then(res => {
        if(res.data){
          localStorage.setItem("username", res.data.username);
          window.location = "/home";
        } else {
          alert("Invalid Credentials");
        }
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>
      <input name="username" placeholder="Username" onChange={handleChange}/>
      <input name="password" type="password" placeholder="Password" onChange={handleChange}/>
      <button type="submit">Login</button>
    </form>
  );
}

export default Login;